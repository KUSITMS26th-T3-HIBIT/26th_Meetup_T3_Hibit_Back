package com.hibit.kusitms26tht3hibitback.repository;

import com.hibit.kusitms26tht3hibitback.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsById(String id);
}
