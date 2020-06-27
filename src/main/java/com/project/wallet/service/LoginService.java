package com.project.wallet.service;

import com.project.wallet.dto.LoginDto;
import com.project.wallet.dto.UserDetailsDto;

public interface LoginService {

    boolean authorise(LoginDto loginDto);
    UserDetailsDto registerUser(UserDetailsDto userDetails);
}
