package com.hibit.kusitms26tht3hibitback.service;

import com.hibit.kusitms26tht3hibitback.domain.Users;
import com.hibit.kusitms26tht3hibitback.dto.MatchingSaveRequestDto;
import com.hibit.kusitms26tht3hibitback.repository.MatchingRepository;
import com.hibit.kusitms26tht3hibitback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MatchingService {

    private final MatchingRepository matchingRepository;
    private final UserRepository userRepository;


    @Transactional
    public int save(MatchingSaveRequestDto requestDto){
        return matchingRepository.save(requestDto.toEntity()).getIdx();
    }
}
