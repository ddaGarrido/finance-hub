package com.financehub.apigateway.config;

import com.financehub.core.service.JwtsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtsService jwtsService;

    public JwtAuthenticationFilter(JwtsService jwtsService) {
        this.jwtsService = jwtsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        // 1. Check if the Authorization header is present and correctly formatted
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Extract the token from the header
        jwt = authHeader.substring(7);
        username = jwtsService.extractUsername(jwt);
        var role = List.of(new SimpleGrantedAuthority("ROLE_USER"));

        var auth = new UsernamePasswordAuthenticationToken(username, null, role);
        SecurityContextHolder.getContext().setAuthentication(auth);

        // 3. If we have a username and the user is not already authenticated
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//            // 4. If the token is valid, update the Security Context
//            if (jwtsService.isTokenValid(jwt, userDetails)) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails,
//                        null, // Credentials are not needed as the token is our proof
//                        userDetails.getAuthorities()
//                );
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
        filterChain.doFilter(request, response);
    }
}
