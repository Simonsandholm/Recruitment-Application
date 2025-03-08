package se.kth.iv1201.group4.recruitment.recruitmentapp.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * Custom handler for access-denied scenarios (HTTP 403).
 * Redirects the user back to the referring page if available; otherwise, redirects to the login page.
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException, ServletException {
        // Grab the referring page from the request header
        String referer = request.getHeader("Referer");
        if (referer != null) {
            // Redirect back to the same page the user was on
            response.sendRedirect(referer);
        } else {
            // If no referer header is set, redirect to login
            response.sendRedirect("/login");
        }
    }
}
