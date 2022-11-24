package com.hibit.kusitms26tht3hibitback.repository;

import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.UserMatching;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import com.hibit.kusitms26tht3hibitback.dto.UserMatchingSaveDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserMatchingRepository extends JpaRepository<UserMatching, Integer> {
    List<UserMatching> findByMatching(Matching matching, final Sort sort);
    UserMatching findByMidAndNickname(int matchingId, String nickname);
    Optional<UserMatching> findByMid(int mid);
    boolean existsByNickname(String nickname);

}
