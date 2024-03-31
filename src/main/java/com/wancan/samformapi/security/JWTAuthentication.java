package com.wancan.samformapi.security;

import com.wancan.samformapi.config.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

public class JWTAuthentication extends OncePerRequestFilter {

    @Autowired
    JWT jwt;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    private String[] urls = {"/user/register","/user/login","/"};

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("hello");
        if(!Arrays.stream(this.urls).toList().contains(request.getServletPath())) {
            String token = getJWTFromToken(request);
            if(StringUtils.hasText(token)){
                try{
                    String id = jwt.parseToken(token).getSubject();

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id,id,null);

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    filterChain.doFilter(request,response);

                }catch (Exception err){
                    response.sendError(HttpStatus.UNAUTHORIZED.value(),"Error");
                }
            }else{
                response.sendError(HttpStatus.UNAUTHORIZED.value(),"Error");
            }
        }else {
            System.out.println("1");
            filterChain.doFilter(request,response);
        }
    }

    private String getJWTFromToken(HttpServletRequest request)
    {
        String auth = request.getHeader("Authorization");
        if(StringUtils.hasText(auth) && auth.startsWith("Bearer ")){
            return auth.split(" ")[1];
        }
        return null;
    }
}
