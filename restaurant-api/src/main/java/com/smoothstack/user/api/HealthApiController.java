package com.smoothstack.user.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HealthApiController {

  @GetMapping("/health")
  @PreAuthorize("permitAll()")
  public ResponseEntity<Void> healthCheck(){
    return ResponseEntity.ok(null);
  }
}
