package com.lwl.currency.security;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  private List<UserDetails> USER_LIST;

  @PostConstruct
  public void init() {
    USER_LIST = Arrays.asList(
        new User("admin", passwordEncoder.encode("admin@123"), Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),
        new User("user", passwordEncoder.encode("user@123"), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))
    );
  }

  public UserDetails loadUserByUsername(String username) {
    return USER_LIST.stream().filter(ele -> ele.getUsername().equals(username))
        .findFirst().orElseThrow(() -> new UsernameNotFoundException("No user was found"));
  }

}
