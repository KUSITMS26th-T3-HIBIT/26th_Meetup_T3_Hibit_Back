package com.hibit.kusitms26tht3hibitback.repository;

import com.hibit.kusitms26tht3hibitback.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsById(String id);
}
