package com.hibit.kusitms26tht3hibitback.repository;

import com.hibit.kusitms26tht3hibitback.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsById(String id);

    boolean existsByNickname(String nickname);

    Users findUserById(String id);

    Optional<Users> findById(String id);
    Users findByNickname (String nickname);
}
