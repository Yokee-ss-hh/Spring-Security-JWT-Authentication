package com.example.jwt.configurations;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.jwt.services.AuthUserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFilter extends OncePerRequestFilter{
	

	private static final String[] excludedEndpoints = new String[] {"/auth/api/signup", "/auth/api/signin"};
	
	@Autowired
	private AuthJwtUtils authJwtUtils;
	
	@Autowired
	private AuthUserService service;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {	
			System.out.println("*****************FIlter Called**********************");
		      String jwt = parseJwt(request);
		      if (jwt != null && authJwtUtils.validateJwtToken(jwt)) {
		        String username = authJwtUtils.getUserNameFromJwtToken(jwt);

		        UserDetails userDetails = service.loadUserByUsername(username);
		        UsernamePasswordAuthenticationToken authentication =
		            new UsernamePasswordAuthenticationToken(
		                userDetails,
		                null,
		                userDetails.getAuthorities());
		        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		        SecurityContextHolder.getContext().setAuthentication(authentication);
		      }
		    } 
		catch (Exception e) {
		   System.out.println("Cannot perform authentication");
		}
		filterChain.doFilter(request, response);
	}
	
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    	return Arrays.stream(excludedEndpoints)
    	        .anyMatch(e -> new AntPathMatcher().match(e, request.getServletPath()));
    }
	
	private String parseJwt(HttpServletRequest request) {
	    String headerAuth = request.getHeader("Authorization");

	    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
	      return headerAuth.substring(7);
	    }

	    return null;
	  }

}
