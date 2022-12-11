package com.lwl.currency.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UserService userService;
  @Autowired
  private JwtUtil jwtUtil;
  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
    UserDetails user = userService.loadUserByUsername(authRequest.getUsername());
    if(user!=null){
      String jwt = jwtUtil.generateToken(user);
      AuthResponse authResponse = new AuthResponse();
      authResponse.setJwt(jwt);
      return ResponseEntity.ok(authResponse);
    }
    return ResponseEntity.badRequest().body(null);

  }
}
