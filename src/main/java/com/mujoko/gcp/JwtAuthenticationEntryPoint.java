package com.mujoko.gcp;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
//In case custom response required
//@Component
public class JwtAuthenticationEntryPoint {//implements AuthenticationEntryPoint, Serializable {
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

//	@Override
//	public void commence(HttpServletRequest request, HttpServletResponse response,
//			AuthenticationException e) throws IOException {
////		 	logger.error("Responding with unauthorized error. Message - {}", e.getMessage());
//	        if(e instanceof BadCredentialsException) {
//	        	logger.error("logger.error(");
//	        	response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//	        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//	        	BaseGet invalidUserNameOrPassword = new BaseGet();
//	        	invalidUserNameOrPassword.setMessage("Incorrect account or password");
//	        	invalidUserNameOrPassword.setStatus(false);
//	            response.getOutputStream().write(new ObjectMapper()
//	                    .writeValueAsBytes(invalidUserNameOrPassword));
//	        } else {
//	        	response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//	        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////	        	UserResponse invalidToken = new UserResponse(HttpServletResponse.SC_UNAUTHORIZED);///ControllerUtils.buildErrorResult(101, "Incorrect account or password");
//	        	invalidToken.setMessage("Invalid Token");
//	        	invalidToken.setStatus(false);
//	        	invalidToken.setCount(1);
//	            response.getOutputStream().write(new ObjectMapper()
//	                    .writeValueAsBytes(invalidToken));
////	        	e.printStackTrace();
////	        	response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
////	                    "Unauthorized access");
//	        }
//	}
}
