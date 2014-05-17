/*
 * Copyright 2012 Andrej Petras <andrej@ajka-andrej.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lorislab.appky.process.registration.ejb;

;
import org.lorislab.appky.process.registration.model.Registration;
import org.lorislab.appky.process.registration.model2.enums.EmailValidationAction;
import org.lorislab.appky.process.registration.resources.ErrorKeys;
import org.lorislab.appky.process.registration.util.RegistrationUtil;
import org.lorislab.appky.process.config.EmailConfiguration;
import org.lorislab.appky.process.config.ProcessCreateUserConfiguration;
import org.lorislab.appky.process.config.ProcessRegistrationConfiguration;
import org.lorislab.appky.process.config.ServerConfiguration;
import org.lorislab.appky.process.tracking.factory.StoreTrackingDataFactory;
import org.lorislab.appky.process.tracking.model.AbstractStoreTrackingData;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.*;
import org.lorislab.appky.application.criteria.EmailRequestCriteria;
import org.lorislab.appky.application.criteria.RoleSearchCriteria;
import org.lorislab.appky.application.criteria.UserProfileSearchCriteria;
import org.lorislab.appky.application.ejb.EmailRequestServiceLocal;
import org.lorislab.appky.application.ejb.RoleServiceLocal;
import org.lorislab.appky.application.ejb.UserPasswordServiceLocal;
import org.lorislab.appky.application.ejb.UserProfileServiceLocal;
import org.lorislab.appky.application.factory.UserObjectFactory;
import org.lorislab.appky.application.model.EmailRequest;
import org.lorislab.appky.application.model.Role;
import org.lorislab.appky.application.model.UserPassword;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.appky.application.model.enums.EmailRequestStatus;
import org.lorislab.appky.application.model.enums.EmailRequestType;
import org.lorislab.appky.application.util.EmailRequestUtil;
import org.lorislab.appky.config.ejb.ConfigurationServiceLocal;
import org.lorislab.appky.email.ejb.MailServiceLocal;
import org.lorislab.appky.email.factory.EmailFactory;
import org.lorislab.appky.email.model.Email;
import org.lorislab.appky.tracking.ejb.TrackingServiceLocal;
import org.lorislab.appky.tracking.model.AbstractTrackingData;
import org.lorislab.jel.base.util.DateUtil;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.jel.ejb.services.AbstractServiceBean;
import org.lorislab.jel.jpa.model.util.PersistentUtil;

/**
 * The registration service.
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
@Stateless
@Local(RegistrationServiceLocal.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RegistrationServiceBean extends AbstractServiceBean implements RegistrationServiceLocal {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(RegistrationServiceBean.class.getName());
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -462139487310130951L;
    /**
     * The email request service.
     */
    @EJB
    private EmailRequestServiceLocal registrationService;
    /**
     * The user password service.
     */
    @EJB
    private UserPasswordServiceLocal userPasswordService;
    /**
     * The user profile service.
     */
    @EJB
    private UserProfileServiceLocal userProfileService;
    /**
     * The mail service.
     */
    @EJB
    private MailServiceLocal mailService;
    /**
     * The role service.
     */
    @EJB
    private RoleServiceLocal roleService;
    /**
     * The configuration service.
     */
    @EJB
    private ConfigurationServiceLocal configService;

    /**
     * The tracking service.
     */
    @EJB
    private TrackingServiceLocal trackinService;
    
    /**
     * {@inheritDoc}
     */
    private UserProfile createUser(Registration registration, Role... roles) throws ServiceException {
        UserProfile result = null;
        try {
            // Load configuration
            ServerConfiguration serverConfig = configService.loadConfiguration(ServerConfiguration.class);
            ProcessCreateUserConfiguration config = configService.loadConfiguration(ProcessCreateUserConfiguration.class);

            // 0. get default role
            Role defaultRole = null;
            RoleSearchCriteria criteria = new RoleSearchCriteria();
            criteria.setName(config.getDefaultRole());
            List<Role> tmp = roleService.findRolesByCriteria(criteria);
            if (tmp != null && !tmp.isEmpty()) {
                defaultRole = tmp.get(0);
            }
            
            // 1. create user profile
            UserProfile profile = UserObjectFactory.createUserProfile(registration.getEmail(), registration.getFirstName(),
                    registration.getMiddleName(), registration.getLastName());

            // 1.2 set user default language
            profile.setLocale(serverConfig.getServerLang());
            // add default role
            if (defaultRole != null) {
                profile.getRoles().add(defaultRole);
            }
            // add roles
            if (roles != null) {
                profile.getRoles().addAll(Arrays.asList(roles));
            }
            // save the profile
            profile = userProfileService.saveUserProfile(profile);

            // 2. create user password
            UserPassword password = UserObjectFactory.createUserPassword(profile.getGuid(), registration.getPassword());
            userPasswordService.saveUserPassword(password);

            result = profile;
        } catch (ServiceException ex) {
            String userId = RegistrationUtil.getEmail(registration);
            throw new ServiceException(ErrorKeys.CREATE_USER_FAILED, ex, userId);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void createUserRegistration(Registration registration) throws ServiceException {
        try {
            // 1. create user
            UserProfile profile = createUser(registration);

            EmailConfiguration emailConfig = configService.loadConfiguration(EmailConfiguration.class);
            ProcessRegistrationConfiguration config = configService.loadConfiguration(ProcessRegistrationConfiguration.class);
            Date validateTo = DateUtil.add(Calendar.DAY_OF_YEAR, config.getRegistrationInterval());

            // create registration request
            String userGuid = PersistentUtil.getGuid(profile);          
            EmailRequest request = UserObjectFactory.createEmailRequest(userGuid, profile.getEmail(), EmailRequestType.REGISTRATION, null, validateTo);
            request = registrationService.saveRegistrationRequest(request);
            
            // 2.2 create email
             Email mail = EmailFactory.createEmail(profile.getLocale(), emailConfig.getEmailFrom(), profile.getEmail(), config.getTemplateRegistrationEmail(), 
                     emailConfig.getContentCharset(), emailConfig.getTransferEncoding(), emailConfig.getContentType(),
                     profile, request);             
             mailService.sendEmail(mail);
                
            // 5. save the activity
            AbstractStoreTrackingData activity = StoreTrackingDataFactory.createRequestRegistrationActivity(request);
            trackinService.saveTrackingData(activity);
            // 6. save the activity
            AbstractStoreTrackingData activity2 = StoreTrackingDataFactory.createRequestEmailValidationActivity(request);
            trackinService.saveTrackingData(activity2);        
        } catch (ServiceException ex) {
            String userId = RegistrationUtil.getEmail(registration);
            throw new ServiceException(ErrorKeys.REGISTRATION_FAILED, ex, userId);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkUserEmail(String email) throws ServiceException {
        boolean result = true;
        try {
            UserProfile profile = null;
            UserProfileSearchCriteria criteria = new UserProfileSearchCriteria();
            criteria.setEmail(email);
            List<UserProfile> profiles = userProfileService.findUserProfilesByCriteria(criteria);
            if (profiles != null && !profiles.isEmpty()) {
                profile = profiles.get(0);
            }

            if (profile != null) {
                result = false;
            }
        } catch (ServiceException ex) {
            throw new ServiceException(ErrorKeys.CHECK_USER_FAILED, ex, email);
        }
        return result;
    }

    /**
     * Find valid email request.
     *
     * @param guid the request GUID.
     * @param type the email type request.
     *
     * @return email request.
     *
     * @throws ServiceException if the method fails.
     */
    @Override
    public EmailRequest findValidEmailRequest(String guid, EmailRequestType type) throws ServiceException {
        EmailRequest result = null;
        EmailRequestCriteria searchCriteria = new EmailRequestCriteria();
        searchCriteria.setGuid(guid);
        searchCriteria.setType(type);
        searchCriteria.setValidateTo(true);
        searchCriteria.setStatus(EmailRequestStatus.OPEN);

        List<EmailRequest> requests = registrationService.findRegistrationRequestByCriteria(searchCriteria);
        if (requests != null && requests.size() == 1) {
            result = requests.get(0);
        }
        return result;
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void validateEmailByRequest(String guid, EmailValidationAction action) throws ServiceException {
        try {
            if (action != null) {

                EmailRequest request = findValidEmailRequest(guid, EmailRequestType.REGISTRATION);
                if (request != null) {
                    UserProfile profile = userProfileService.loadUserProfile(request.getUserGuid());
                    if (profile != null) {

                        AbstractTrackingData activity;
                        
                        if (EmailValidationAction.ACCEPTED.equals(action)) {
                            
                            // load the configuration
                            ProcessCreateUserConfiguration config = configService.loadConfiguration(new ProcessCreateUserConfiguration());

                            // 1.1 get default role
                            Role emailRole = null;
                            RoleSearchCriteria criteria = new RoleSearchCriteria();
                            criteria.setName(config.getEmailValidationRole());
                            List<Role> roles = roleService.findRolesByCriteria(criteria);
                            if (roles != null && !roles.isEmpty()) {
                                emailRole = roles.get(0);
                            }

                            if (emailRole != null) {
                                profile.getRoles().add(emailRole);
                            }
                            // update the profile
                            profile.setEmail(request.getEmail());
                            userProfileService.saveUserProfile(profile);
                            
                            // acccept the request
                            request.setStatus(EmailRequestStatus.CLOSED);                            
                            activity = StoreTrackingDataFactory.createRequestEmailValidationAcceptActivity(request);
                        } else {
                            // decline request
                            request.setStatus(EmailRequestStatus.DECLINED);
                            activity = StoreTrackingDataFactory.createRequestDeclineActivity(request);
                        }

                        // update request
                        registrationService.saveRegistrationRequest(request);
                        
                        // save activity
                        trackinService.saveTrackingData(activity);
                        
                    } else {
                        LOGGER.warning("No correct validation request found! Missing profile!");
                        throw new Exception("The validation request is not valid any more.");
                    }
                } else {
                    LOGGER.warning("No correct validation request found!");
                    throw new Exception("The validation request is not valid any more.");
                }
            } else {
                LOGGER.log(Level.WARNING, "The action for the request {0} is null!", guid);
                throw new Exception("The action is null!");
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorKeys.VALIDATE_EMAIL_REQUEST_FAILED, e, guid, action);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void sendInvitationEmailRequest(UserProfile profile, String email) throws ServiceException {
        String userGuid = PersistentUtil.getGuid(profile);
        try {
            // Read the configuration
            EmailConfiguration emailConfig = configService.loadConfiguration(EmailConfiguration.class);
            ProcessRegistrationConfiguration config = configService.loadConfiguration(ProcessRegistrationConfiguration.class);
            Date validateTo = DateUtil.add(Calendar.DAY_OF_YEAR, config.getInvitationInterval());

            // Create email for the invitation
            EmailRequest request = UserObjectFactory.createEmailRequest(null, email, EmailRequestType.INVITATION, profile.getGuid(), validateTo);
            request = registrationService.saveRegistrationRequest(request);
            
            // Send email
            Email mail = EmailFactory.createEmail(profile.getLocale(), emailConfig.getEmailFrom(), email, config.getTemplateInvitationEmail(),
                    emailConfig.getContentCharset(), emailConfig.getTransferEncoding(), emailConfig.getContentType(),
                    profile, request);
            mailService.sendEmail(mail);
            
            // Save the activity
            AbstractStoreTrackingData activity = StoreTrackingDataFactory.createRequestInvitationActivity(request);
            trackinService.saveTrackingData(activity);
            
        } catch (Exception e) {            
            throw new ServiceException(ErrorKeys.INVITATION_EMAIL_REQUEST_FAILED, e, userGuid, email);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void cancelRequest(EmailRequest request) throws ServiceException {
        try {
            // decline the request
            request.setStatus(EmailRequestStatus.DECLINED);
            request = registrationService.saveRegistrationRequest(request);
            
            // save the activity
            AbstractStoreTrackingData activity = StoreTrackingDataFactory.createRequestDeclineActivity(request);
            trackinService.saveTrackingData(activity);
        } catch (Exception e) {
            String guid = PersistentUtil.getGuid(request);
            String email = EmailRequestUtil.getEmail(request);
            throw new ServiceException(ErrorKeys.CANCEL_REQUEST_FAILED, e, guid, email);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void acceptInvitationRequest(EmailRequest request, Registration registration) throws ServiceException {
        try {
            ProcessCreateUserConfiguration config = configService.loadConfiguration(ProcessCreateUserConfiguration.class);

            // 0. add email validation role
            Role emailRole = null;
            RoleSearchCriteria criteria = new RoleSearchCriteria();
            criteria.setName(config.getEmailValidationRole());
            List<Role> roles = roleService.findRolesByCriteria(criteria);
            if (roles != null && !roles.isEmpty()) {
                emailRole = roles.get(0);
            }

            // 1. create user
            UserProfile profile = createUser(registration, emailRole);

            // 5. close request
            request.setUserGuid(profile.getGuid());
            request.setStatus(EmailRequestStatus.CLOSED);
            registrationService.saveRegistrationRequest(request);
            
            // save the activity
            AbstractTrackingData activity = StoreTrackingDataFactory.createRequestInvitationAcceptActivity(request);
            trackinService.saveTrackingData(activity);
            
        } catch (Exception e) {
            String guid = PersistentUtil.getGuid(request);
            String email = RegistrationUtil.getEmail(registration);
            throw new ServiceException(ErrorKeys.INVITATION_ACCEPT_REQUEST_FAILED, e, guid, email);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void sendResetPasswordEmailRequest(String email) throws ServiceException {
        String userGuid = null;
        try {
            // Find user profile by email
            UserProfile profile = null;
            UserProfileSearchCriteria criteria = new UserProfileSearchCriteria();
            criteria.setEmail(email);
            List<UserProfile> profiles = userProfileService.findUserProfilesByCriteria(criteria);
            if (profiles != null && !profiles.isEmpty()) {
                profile = profiles.get(0);
            }

            if (profile != null) {
                // load configuration
                EmailConfiguration emailConfig = configService.loadConfiguration(EmailConfiguration.class);
                ProcessRegistrationConfiguration config = configService.loadConfiguration(ProcessRegistrationConfiguration.class);
                Date validateTo = DateUtil.add(Calendar.DAY_OF_YEAR, config.getResetPasswordInterval());
                
                // Create reset password request
                userGuid = PersistentUtil.getGuid(profile);
                EmailRequest request = UserObjectFactory.createEmailRequest(userGuid, email, EmailRequestType.RESET_PASSWORD, null, validateTo);
                request = registrationService.saveRegistrationRequest(request);                
                
                // Send email
                Email mail = EmailFactory.createEmail(profile.getLocale(), emailConfig.getEmailFrom(), email, config.getTemplateResetPasswordEmail(), 
                        emailConfig.getContentCharset(), emailConfig.getTransferEncoding(), emailConfig.getContentType(),
                        profile, request);
                mailService.sendEmail(mail);
                
                // save activity
                AbstractStoreTrackingData activity = StoreTrackingDataFactory.createRequestResetPasswordActivity(request);
                trackinService.saveTrackingData(activity);
            } else {
                throw new Exception("Missing user profile for the " + email);
            }
        } catch (Exception e) {
            throw new ServiceException(ErrorKeys.RESET_PASSWORD_EMAIL_REQUEST_FAILED, e, userGuid, email);
        }
    }
   
    /**
     * {@inheritDoc}
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void acceptResetPasswordRequest(EmailRequest request, char[] password) throws ServiceException {
        try {
            userPasswordService.resetPassword(request.getUserGuid(), password);
            request.setStatus(EmailRequestStatus.CLOSED);
            registrationService.saveRegistrationRequest(request);
            
            // save the activity
            AbstractTrackingData activity = StoreTrackingDataFactory.createRequestResetPasswordAcceptActivity(request);
            trackinService.saveTrackingData(activity);            
        } catch (Exception e) {
            String guid = PersistentUtil.getGuid(request);
            throw new ServiceException(ErrorKeys.RESET_PASSWORD_ACCEPT_REQUEST_FAILED, e, guid);
        }
    }
}
