package org.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.login.models.LoginRequestDTO;
import org.login.service.impl.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDTO requestDTO, HttpServletRequest request) {

        String token = loginService.login(requestDTO, request);
        return ResponseEntity.ok().body(loginService.login(requestDTO, request));
    }

}