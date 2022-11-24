package com.hibit.kusitms26tht3hibitback.repository;

import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.Posts;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchingRepository extends JpaRepository<Matching, Integer> {
    List<Matching> findALlByDeleteYn(final char deleteYn, final Sort sort);

    List<Matching> findByUser_IdContaining(String id);
}
