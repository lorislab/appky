-- EMAIL -----------------------------------------------------------------------
-- Email configuration
INSERT INTO CORE_CONFIG (C_GUID, C_OPLOCK, C_CREATIONDATE, C_CREATIONUSER, C_MODIFICATIONDATE, C_MODIFICATIONUSER, C_MODULE) VALUES ('email', 0, '2012-09-27 20:40:02.947', NULL, '2012-09-27 20:40:02.947', NULL, 'email');
-- Email from address
INSERT INTO CORE_CONFIG_PARAM (C_PARENT_GUID, C_GUID, C_OPLOCK, C_NAME, C_VALUE, C_DEFAULT, C_TYPE, C_EDIT) VALUES ('email', 'CONFIG11', 0, 'email.from', 'family@ajka-andrej.com', 'family@ajka-andrej.com', 'STRING', true);
-- Email content charset
INSERT INTO CORE_CONFIG_PARAM (C_PARENT_GUID, C_GUID, C_OPLOCK, C_NAME, C_VALUE, C_DEFAULT, C_TYPE, C_EDIT) VALUES ('email', 'CONFIG13', 0, 'email.content.charset', 'UTF-8','UTF-8', 'STRING', true);
-- Email content type
INSERT INTO CORE_CONFIG_PARAM (C_PARENT_GUID, C_GUID, C_OPLOCK, C_NAME, C_VALUE, C_DEFAULT, C_TYPE, C_EDIT) VALUES ('email', 'CONFIG14', 0, 'email.content.typ', 'text/html;charset=\"UTF-8\"','text/html;charset=\"UTF-8\"', 'STRING', true);
-- Email transfer encoding
INSERT INTO CORE_CONFIG_PARAM (C_PARENT_GUID, C_GUID, C_OPLOCK, C_NAME, C_VALUE, C_DEFAULT, C_TYPE, C_EDIT) VALUES ('email', 'CONFIG15', 0, 'email.transfer.encoding', 'quoted-printable','quoted-printable', 'STRING', true);

-- SERVER ----------------------------------------------------------------------
-- Server configuration
INSERT INTO CORE_CONFIG (C_GUID, C_OPLOCK, C_CREATIONDATE, C_CREATIONUSER, C_MODIFICATIONDATE, C_MODIFICATIONUSER, C_MODULE) VALUES ('server', 0, '2012-09-27 20:40:02.947', NULL, '2012-09-27 20:40:02.947', NULL, 'server');
-- Server languages list
INSERT INTO CORE_CONFIG_PARAM (C_PARENT_GUID, C_GUID, C_OPLOCK, C_NAME, C_VALUE, C_DEFAULT, C_TYPE, C_EDIT) VALUES ('server', 'SERVER21', 0, 'server.langs', 'en,de', 'en,de', 'STRING', true);
-- Server default language
INSERT INTO CORE_CONFIG_PARAM (C_PARENT_GUID, C_GUID, C_OPLOCK, C_NAME, C_VALUE, C_DEFAULT, C_TYPE, C_EDIT) VALUES ('server', 'SERVER23', 0, 'server.lang', 'en', 'en', 'STRING', true);
-- Server URL (email links, ... )
INSERT INTO CORE_CONFIG_PARAM (C_PARENT_GUID, C_GUID, C_OPLOCK, C_NAME, C_VALUE, C_DEFAULT, C_TYPE, C_EDIT) VALUES ('server', 'SERVER22', 0, 'server.url', 'http://localhost:8080/store','http://localhost:8080/store', 'STRING', true);

-- PROCESS ---------------------------------------------------------------------
-- Process configuration
INSERT INTO CORE_CONFIG (C_GUID, C_OPLOCK, C_CREATIONDATE, C_CREATIONUSER, C_MODIFICATIONDATE, C_MODIFICATIONUSER, C_MODULE) VALUES ('process', 0, '2012-09-27 20:40:02.947', NULL, '2012-09-27 20:40:02.947', NULL, 'process');
-- Send registration emails
INSERT INTO CORE_CONFIG_PARAM (C_PARENT_GUID, C_GUID, C_OPLOCK, C_NAME, C_VALUE, C_DEFAULT, C_TYPE, C_EDIT) VALUES ('process', 'CONFIG36', 0, 'registration.sendEmail', 'true','true', 'BOOLEAN', true);
-- The valid registration interval in days
INSERT INTO CORE_CONFIG_PARAM (C_PARENT_GUID, C_GUID, C_OPLOCK, C_NAME, C_VALUE, C_DEFAULT, C_TYPE, C_EDIT) VALUES ('process', 'CONFIG37', 0, 'registration.interval', '30','30', 'INTEGER', true);
-- The valid invitation interval in days
INSERT INTO CORE_CONFIG_PARAM (C_PARENT_GUID, C_GUID, C_OPLOCK, C_NAME, C_VALUE, C_DEFAULT, C_TYPE, C_EDIT) VALUES ('process', 'CONFIG31', 0, 'invitation.interval', '2','2', 'INTEGER', true);
-- The valid reset password interval in days
INSERT INTO CORE_CONFIG_PARAM (C_PARENT_GUID, C_GUID, C_OPLOCK, C_NAME, C_VALUE, C_DEFAULT, C_TYPE, C_EDIT) VALUES ('process', 'CONFIG32', 0, 'resetpassword.interval', '2','2', 'INTEGER', true);
-- The email template for the registration process
INSERT INTO CORE_CONFIG_PARAM (C_PARENT_GUID, C_GUID, C_OPLOCK, C_NAME, C_VALUE, C_DEFAULT, C_TYPE, C_EDIT) VALUES ('process', 'CONFIG33', 0, 'template.registration.request', 'registration.email.request','registration.email.request', 'STRING', true);
-- The reset password email template
INSERT INTO CORE_CONFIG_PARAM (C_PARENT_GUID, C_GUID, C_OPLOCK, C_NAME, C_VALUE, C_DEFAULT, C_TYPE, C_EDIT) VALUES ('process', 'CONFIG34', 0, 'template.resetpassword.request', 'resetpassword.email.request','resetpassword.email.request', 'STRING', true);
-- The email template for the invitation process
INSERT INTO CORE_CONFIG_PARAM (C_PARENT_GUID, C_GUID, C_OPLOCK, C_NAME, C_VALUE, C_DEFAULT, C_TYPE, C_EDIT) VALUES ('process', 'CONFIG35', 0, 'template.invitation.request', 'invitation.email.request','invitation.email.request', 'STRING', true);
-- The email template for the application update process
INSERT INTO CORE_CONFIG_PARAM (C_PARENT_GUID, C_GUID, C_OPLOCK, C_NAME, C_VALUE, C_DEFAULT, C_TYPE, C_EDIT) VALUES ('process', 'CONFIG38', 0, 'template.application.update.email', 'app.update.email','app.update.email', 'STRING', true);
-- The temporary resource validation interval in minutes
INSERT INTO CORE_CONFIG_PARAM (C_PARENT_GUID, C_GUID, C_OPLOCK, C_NAME, C_VALUE, C_DEFAULT, C_TYPE, C_EDIT) VALUES ('process', 'CONFIG39', 0, 'tmp.resource.validateTo', '1','1', 'INTEGER', true);
-- The the user default role (registration process)
INSERT INTO CORE_CONFIG_PARAM (C_PARENT_GUID, C_GUID, C_OPLOCK, C_NAME, C_VALUE, C_DEFAULT, C_TYPE, C_EDIT) VALUES ('process', 'CONFIG40', 0, 'role.default', 'u-store','u-store', 'STRING', false);
-- The the user email validation role (registration process)
INSERT INTO CORE_CONFIG_PARAM (C_PARENT_GUID, C_GUID, C_OPLOCK, C_NAME, C_VALUE, C_DEFAULT, C_TYPE, C_EDIT) VALUES ('process', 'CONFIG41', 0, 'role.emailValidation', 'u-reg-store','u-reg-store', 'STRING', false);

-- APPLICATION -----------------------------------------------------------------
INSERT INTO APPKY.AY_BARN_APP (C_GUID, C_OPLOCK, C_DATE, C_NAME, C_RELEASE) VALUES ('A1', 0, '2014-06-25 19:50:14.667', 'APPKY', '1.0.0');

-- VIEW ------------------------------------------------------------------------
-- View configuration
INSERT INTO APPKY.AY_BARN_CONF (C_GUID, C_OPLOCK, C_TYPE, C_APP) VALUES ('C1', 0, 'org.lorislab.appky.process.config.ViewConfiguration', 'A1');
INSERT INTO APPKY.AY_BARN_ATTR (C_GUID, C_OPLOCK, C_NAME, C_VALUE, C_CONFIG) VALUES ('AT1', 0, 'publicRegistration', 'false', 'C1');
INSERT INTO APPKY.AY_BARN_ATTR (C_GUID, C_OPLOCK, C_NAME, C_VALUE, C_CONFIG) VALUES ('AT2', 0, 'forgotPublic', 'false', 'C2');
