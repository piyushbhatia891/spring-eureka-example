package org.login.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.login.models.LoginRequestDTO;

public interface LoginService {

	String login(LoginRequestDTO requestDTO, HttpServletRequest request);
}
