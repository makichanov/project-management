package com.makichanov.projectmanagement.controller.filter;

import com.makichanov.projectmanagement.security.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtAccessFilter extends GenericFilter {

    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;

    @Override
    @Transactional
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String jwt = extractJwt((HttpServletRequest) request);
        if (jwt != null && tokenService.validateToken(jwt)) {
            String login = tokenService.getLoginFromToken(jwt);
            UserDetails userDetails = userDetailsService.loadUserByUsername(login);
            if (userDetails != null) {
                var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }

    private String extractJwt(HttpServletRequest request) {
        String jwt =  request.getHeader("Authorization");
        return StringUtils.hasText(jwt)
                ? jwt.replaceFirst("Bearer ", "")
                : null;
    }
}
