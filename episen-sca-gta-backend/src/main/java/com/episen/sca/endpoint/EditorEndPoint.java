package com.episen.sca.endpoint;

import com.episen.sca.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping(EditorEndPoint.PATH)
public class EditorEndPoint {

    public static final String PATH = "/api/v1/editor";

    @GetMapping
    public ResponseEntity<UserDto> getCurrentUser(Authentication authentication) {
      return ResponseEntity
              .ok(
                      UserDto.builder()
                              .username(authentication.getName())
                              .roles(
                                      authentication.getAuthorities()
                                              .stream()
                                              .map(GrantedAuthority::getAuthority)
                                              .collect(Collectors.toList())
                              )
              .build());
    }
}
