package com.project.wallet.service;

import com.project.wallet.dto.LoginDto;
import com.project.wallet.dto.UserDetailsDto;
import com.project.wallet.dto.WalletDto;
import com.project.wallet.exception.PasswordMismatchException;
import com.project.wallet.exception.UserDoesNotExistException;
import com.project.wallet.repository.UserRepository;
import com.project.wallet.util.WalletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    public UserDetailsDto registerUser(UserDetailsDto registration) {
        UserDetailsDto user = new UserDetailsDto();
        WalletDto wallet = new WalletDto(0);
        String date = WalletUtil.getCurrentTimestamp();
        wallet.setCreatedDate(date);
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmailId(registration.getEmailId());
        user.setPassword(WalletUtil.hashcode(registration.getPassword()));
        user.setCity(registration.getCity());
        user.setCountry(registration.getCountry());
        user.setPhoneNumber(registration.getPhoneNumber());
        user.setWallet(wallet);
        return userRepository.save(user);
    }

    public boolean authorise(LoginDto loginDto){
      UserDetailsDto dto = userRepository.findByEmailId(loginDto.getEmailId());
      if(dto == null)
          throw new UserDoesNotExistException(loginDto.getEmailId());
        String encryptedPwd = WalletUtil.hashcode(loginDto.getPassword());

        if(!dto.getPassword().equals(encryptedPwd)){
            throw new PasswordMismatchException();
        }
        return true;

    }

}
