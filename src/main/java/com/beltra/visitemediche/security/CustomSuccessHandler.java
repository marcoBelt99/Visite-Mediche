package com.beltra.visitemediche.security;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/** Classe che va usata per implementare */
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    /** Ritorna alla pagina di "/index" usando lo username dell'utente */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // response
        response.sendRedirect("/index/" + authentication.getName());
    }
}
