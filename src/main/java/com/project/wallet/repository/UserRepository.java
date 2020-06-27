package com.project.wallet.repository;

import com.project.wallet.dto.UserDetailsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetailsDto, Integer> {

    UserDetailsDto save(UserDetailsDto userDetails);
    UserDetailsDto findByEmailId(String email);
    Optional<UserDetailsDto> findById(Integer id);
}
