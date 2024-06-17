package com.ndungutse.tectalk.config.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ndungutse.tectalk.config.security.CustomUserDetailService;
import com.ndungutse.tectalk.config.security.CustomUserDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilterChain extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Override
    // THis is a custom Filter
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // Extract JWT Token
            String jwt = parseJwt(request);

            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                // Extract Username
                String email = jwtUtils.getUserNameFromToken(jwt);

                // Get User from datasource
                // Returns CustomUserDetails object that has custom implementation of
                // UserDetails
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                System.out.println("USER DETAILS >>>>>>>>>>>>>>>>>>>" + userDetails);

                // Construct Authentication object that will e in Securitycontect
                // This COnstructor set isAuthenticated to true. It is the one to call in case
                // user is Authenticated
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            System.out.println("Cannot set user authentication: {}" + e);
        }

        // Continue filter chain as usual: Other filters
        filterChain.doFilter(request, response);

    }

    private String parseJwt(HttpServletRequest request) {
        String jwt = jwtUtils.getJwtFromHeader(request);
        return jwt;
    }

}
