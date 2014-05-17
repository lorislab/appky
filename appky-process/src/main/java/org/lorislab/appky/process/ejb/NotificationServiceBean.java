/*
 * Copyright 2014 lorislab.org.
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

package org.lorislab.appky.process.ejb;

import org.lorislab.appky.process.config.EmailConfiguration;
import org.lorislab.appky.process.config.ProcessApplicationConfiguration;
import org.lorislab.appky.process.config.UserProfileConfiguration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import org.lorislab.appky.application.criteria.UserProfileSearchCriteria;
import org.lorislab.appky.application.ejb.UserProfileServiceLocal;
import org.lorislab.appky.application.model.Platform;
import org.lorislab.appky.application.model.UserProfile;
import org.lorislab.appky.application.model.Version;
import org.lorislab.appky.application.model.enums.UserApplicationStatus;
import org.lorislab.appky.config.ejb.ConfigurationServiceLocal;
import org.lorislab.appky.email.ejb.MailServiceLocal;
import org.lorislab.appky.email.factory.EmailFactory;
import org.lorislab.appky.email.model.Email;
import org.lorislab.jel.ejb.exception.ServiceException;

/**
 *
 * @author Andrej Petras
 */
@Stateless
@Local(NotificationServiceLocal.class)
public class NotificationServiceBean implements NotificationServiceLocal {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(NotificationServiceBean.class.getName());
    
    /**
     * The configuration service.
     */
    @EJB
    private ConfigurationServiceLocal configService;
    
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
     * {@inheritDoc}
     */
    @Asynchronous
    @Override
    public void sendReleaseVersionNotification(Version version) throws ServiceException {
        if (version != null) {

            Platform platform = version.getPlatform();
            String platformGuid = platform.getGuid();

            UserProfileSearchCriteria criteria = new UserProfileSearchCriteria();
            criteria.setPlatformGuid(platformGuid);
            criteria.setUserApplicationStatus(UserApplicationStatus.INSTALLED);
            criteria.setParameterName(UserProfileConfiguration.PARAM_NOTIFI_UPDATE_APP);
            criteria.setParameterValue(Boolean.TRUE.toString());
            
            List<UserProfile> users = userProfileService.findUserProfilesByCriteria(criteria);
            if (users != null) {
                ProcessApplicationConfiguration config = configService.loadConfiguration(ProcessApplicationConfiguration.class);
                String template = config.getTemplateApplicationUpdateEmail();
                EmailConfiguration emailConfig = configService.loadConfiguration(EmailConfiguration.class);
                
                for (UserProfile profile : users) {
                    Email mail = EmailFactory.createEmail(profile.getLocale(), template, profile.getEmail(), emailConfig.getEmailFrom(), 
                            emailConfig.getContentCharset(), emailConfig.getTransferEncoding(), emailConfig.getContentType(),
                            profile, version, platform);
                    mailService.sendEmail(mail);
                    LOGGER.log(Level.INFO, "Send message to user {0}", profile.getEmail());
                }
            }
        }
    }
}
