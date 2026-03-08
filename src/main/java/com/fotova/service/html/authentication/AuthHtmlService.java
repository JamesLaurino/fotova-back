package com.fotova.service.html.authentication;

public interface AuthHtmlService {
    public String buildSuccessResetPassword();
    public String buildErrorResetPassword();
    public String buildSuccessRegisterHtml();
    public String buildFailureRegisterHtml();
}
