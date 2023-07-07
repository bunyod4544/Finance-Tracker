package uz.ba.finance.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.ba.finance.configuration.AbstractFilter;
import uz.ba.finance.response.ResponseBuilder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final ResponseBuilder responseBuilder;
    private final Set<String> whiteSet = Set.of("/api/user/login", "/api/user/register");

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = AbstractFilter.getTokenFromRequest(request);
        if (token.isBlank() || !token.startsWith("Bearer")) {
            AbstractFilter.errorResponse(response, responseBuilder.unauthorized());
            return;
        }
        token = token.substring(7);
        if (!JwtTokenUtils.validateToken(token)) {
            AbstractFilter.errorResponse(response, responseBuilder.unauthorized());
            return;
        }
        String username = JwtTokenUtils.getUsernameFromToken(token);
        UsernamePasswordAuthenticationToken passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null);
        SecurityContextHolder.getContext().setAuthentication(passwordAuthenticationToken);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return whiteSet.contains(request.getRequestURI());
    }

}