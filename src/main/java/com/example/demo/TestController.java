package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

  @PostMapping
  public ResponseEntity<byte[]> proxyPost(ProxyExchange<byte[]> proxy, HttpServletRequest httpServletRequest) {
    return proxy.post();
  }
}
