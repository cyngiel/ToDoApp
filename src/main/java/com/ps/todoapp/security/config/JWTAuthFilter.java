package com.ps.todoapp.security.config;


import com.ps.todoapp.repository.UserRepository;
import com.ps.todoapp.security.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

/**
 * Custom filter that intercepts HTTP requests to verify the presence and validity of
 * a JSON Web Token in the Authorization header.
 */
@Component
@AllArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {

    private static final String authorizationHeader = "Authorization";
    private static final String bearerPrefix = "Bearer ";

    private HandlerExceptionResolver handlerExceptionResolver;
    private JWTService jwtService;
    private UserDetailsService userDetailsService;
    private UserRepository userRepository;


    /**
     * Filters incoming HTTP requests to verify the presence and validity of a JWT token
     * in the request header. If the token is valid and the user is not already authenticated, it sets
     * the authentication context in the SecurityContextHolder.
     *
     * <p> If the request header does not contain a valid JWT token, the filter chain is passed along.
     *
     * <p>
     * If any exception occurs, it is forwarded to the global exception handler.
     *
     * @param request     The incoming HTTP request that contains the authorization header.
     * @param response    The HTTP response to be sent back to the client.
     * @param filterChain The filter chain to pass further the request and response.
     * @throws ServletException If an error occurs during filter chain process.
     * @throws IOException      If an error occurs during filter chain process.
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String requestHeader = request.getHeader(authorizationHeader);

        if (requestHeader == null || !requestHeader.startsWith(bearerPrefix)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = requestHeader.substring(bearerPrefix.length());
            final String username = jwtService.extractUsername(jwt);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtService.validate(jwt, userDetails)) {
                    setAuthentication(request, userDetails);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }

    private void setAuthentication(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}