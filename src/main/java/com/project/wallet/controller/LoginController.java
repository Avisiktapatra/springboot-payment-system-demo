package com.project.wallet.controller;

import com.project.wallet.dto.LoginDto;
import com.project.wallet.dto.UserDetailsDto;
import com.project.wallet.service.LoginServiceImpl;
//import com.project.wallet.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private LoginServiceImpl loginServiceImpl;


    @Autowired
    public void setLoginService(LoginServiceImpl loginServiceImpl) {
        this.loginServiceImpl = loginServiceImpl;
     //   this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> login(@RequestBody LoginDto loginDto){
        boolean checkAuthorisation = loginServiceImpl.authorise(loginDto);
    if(!checkAuthorisation)
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/signup")
    public ResponseEntity<UserDetailsDto> signUp(@RequestBody UserDetailsDto userDto){
        UserDetailsDto user = loginServiceImpl.registerUser(userDto);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }
}
