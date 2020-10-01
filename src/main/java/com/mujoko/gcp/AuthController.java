package com.mujoko.gcp;

import java.util.Objects;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v3")
@Tag(name = "AuthController", description = " The AuthController API")
public class AuthController   {

	Logger logger = LoggerFactory.getLogger(AuthController.class);


	private    

	@Autowired
	AppUtil appUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("login")
	@Operation(summary = "Login", responses = {
		      @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json")),
		      @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
		      @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
	public ResponseEntity<String> login(@Valid @RequestBody Login login) {
		logger.info("login User : " + login);
		Objects.requireNonNull(login.getUsername());
		Objects.requireNonNull(login.getPassword());
		UserDetails userDetail= null;
		try {
			userDetail = userDetailsService.loadUserByUsername(login.getUsername());
			authenticate(login);
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("User Not Found", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
		} catch (BadCredentialsException e) {
			return new ResponseEntity<String>("Invalid Password", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Unauthorize", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
		}
		final String token = jwtTokenUtil.generateToken(userDetail);
		SecureUser secureUser = (SecureUser) userDetail;
		logger.info("secureUser : " + secureUser);
		return new ResponseEntity<String>(token, new HttpHeaders(), HttpStatus.OK);
		
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	private void authenticate(Login login) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
		} catch (DisabledException e) {
			e.printStackTrace();
			throw new Exception("USER_DISABLED", e);
			//		} catch (BadCredentialsException e) {
			//			e.printStackTrace();
			//			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
