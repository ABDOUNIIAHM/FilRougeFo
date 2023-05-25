package com.example.filrougefo.web.logout;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

    @RequestMapping("/auth/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException {
        // Invalidate session and clear authentication
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        // Redirection vers la page de connexion
        return "redirect:/login";
    }
}