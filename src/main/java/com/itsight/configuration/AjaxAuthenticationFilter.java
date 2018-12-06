package com.itsight.configuration;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxAuthenticationFilter extends LoginUrlAuthenticationEntryPoint {

    public AjaxAuthenticationFilter(String loginUrl) {
        super(loginUrl);
    }

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException)
            throws IOException, ServletException {
        String ajaxHeader = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(ajaxHeader);
        if (isAjax) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Ajax Request Denied (Session Expired)");
        } else {
            super.commence(request, response, authException);
        }
    }
}
