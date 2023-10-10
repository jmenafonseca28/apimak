package mye.fisio.mak.apimak.security.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mye.fisio.mak.apimak.data.ClientDetailImpl;
import mye.fisio.mak.apimak.security.jwt.JwtUtils;

@Component
public class JwtAutorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ClientDetailImpl clientDetailImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String tokenHeader = request.getHeader("auth");// Cambiar a "auth" para que funcione con el front, antes era
                                                       // "Authorization"

        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            String token = tokenHeader.replace("Bearer ", "");

            if (jwtUtils.isTokenValid(token)) {

                String username = jwtUtils.getUsernameFromToken(token);
                UserDetails userDetails = clientDetailImpl.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "El token no es válido");
            }

            filterChain.doFilter(request, response);
        } else if (tokenHeader != null && tokenHeader.contains("invited")) {
            filterChain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No hay token");
        }

    }

}
