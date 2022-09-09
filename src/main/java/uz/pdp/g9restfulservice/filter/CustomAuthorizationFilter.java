package uz.pdp.g9restfulservice.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/token/refresh"))
            filterChain.doFilter(request, response);
        else {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                // TODO: 05/09/22
            }
        }
    }
}
