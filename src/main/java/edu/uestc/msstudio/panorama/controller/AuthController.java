/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: AuthController.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.controller 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月10日 下午4:05:46 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.uestc.msstudio.panorama.model.User;
import edu.uestc.msstudio.panorama.model.auth.JwtAuthenticationRequest;
import edu.uestc.msstudio.panorama.model.auth.JwtAuthenticationResponse;
import edu.uestc.msstudio.panorama.service.AuthService;
import io.swagger.annotations.Api;

/**
 * @ClassName: AuthController
 * @Description: This controller contains some methods do the authentication logic
 * @author: MT
 * @date: 2017年5月10日 下午4:05:46
 */
@RestController
@Api
@RequestMapping("/auth")
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest)
            throws AuthenticationException {
        final String token = authService.login(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword());
        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }
    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if (refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity
                    .ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }
    @RequestMapping(value = "${jwt.route.authentication.register}", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody User addedUser)
            throws AuthenticationException {
        User newUser = authService.register(addedUser);
        if (newUser == null)
            return ResponseEntity.status(400)
                    .body("The User Has Already been registered");
        return ResponseEntity.ok(newUser);
    }
}
