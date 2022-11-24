package com.hibit.kusitms26tht3hibitback.service;

import com.hibit.kusitms26tht3hibitback.domain.Alarm;
import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.UserMatching;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import com.hibit.kusitms26tht3hibitback.dto.AlarmResponseDto;
import com.hibit.kusitms26tht3hibitback.dto.AlarmSaveRequestDto;
import com.hibit.kusitms26tht3hibitback.dto.MatchingResponseDto;
import com.hibit.kusitms26tht3hibitback.repository.AlarmRepository;
import com.hibit.kusitms26tht3hibitback.repository.MatchingRepository;
import com.hibit.kusitms26tht3hibitback.repository.UserMatchingRepository;
import com.hibit.kusitms26tht3hibitback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;
    private final MatchingRepository matchingRepository;
    private final UserMatchingRepository userMatchingRepository;

    @Transactional
    public int saveMatching(int writer, int mid, Users user){
        AlarmSaveRequestDto requestDto = new AlarmSaveRequestDto();
        Users write = userRepository.findByIdx(writer).orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다."));
        Matching matching = matchingRepository.findById(mid).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다."));
        String content = user.getNickname() + "님이 매칭 신청을 했습니다.";
        requestDto.setUser(write);
        requestDto.setNickname(write.getNickname());
        requestDto.setCategory();
        requestDto.setMid(mid);
        requestDto.setReaded();
        requestDto.setOpenchat(matching.getOpenchat());
        requestDto.setContent(content);
        Alarm alarm = requestDto.toEntity();
        alarmRepository.save(alarm);
        return alarm.getIdx();
    }

    public int saveMatchingTF(String nickname, int mid){
        AlarmSaveRequestDto requestDto = new AlarmSaveRequestDto();
        Users user = userRepository.findByNickname(nickname).orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다."));
        Matching matching = matchingRepository.findById(mid).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다."));
        UserMatching userMatching = userMatchingRepository.findByMidAndNickname(mid, nickname);
//        Users write = userRepository.findByIdx(userMatching.getWriter()).orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다."));
        if (userMatching.getMatching_check() == 'T'){
            String content;
            content = matching.getUser().getNickname()+"이 매칭을 수락했습니다.";
            requestDto.setContent(content);
            requestDto.setCategoryT();
        }
        else if(userMatching.getMatching_check() == 'F'){
            String content;
            content = matching.getUser().getNickname()+"이 매칭을 거절했습니다.";
            requestDto.setContent(content);
            requestDto.setCategoryF();
        }
        requestDto.setUser(user);
        requestDto.setNickname(user.getNickname());
        requestDto.setMid(mid);
        requestDto.setReaded();
        requestDto.setOpenchat(matching.getOpenchat());
        Alarm alarm = requestDto.toEntity();
        alarmRepository.save(alarm);
        return alarm.getIdx();
    }


    public List<AlarmResponseDto> findByReadedAndNickname(char flag, String nickname){
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        List<Alarm> list= alarmRepository.findByReadedAndNickname(flag, nickname, sort);
        return list.stream().map(AlarmResponseDto::new).collect(Collectors.toList());
    }

    public Map<String, Object> findById(int idx){
        Alarm entity= alarmRepository.findById(idx).orElseThrow(()-> new IllegalArgumentException("해당 알림이 없습니다."));
        Map<String, Object> response = new HashMap<>();
        if (entity.getCategory()=='M'){
            int id = entity.getMid();
            response.put("매칭 게시글 Idx", id);
        }
        else if (entity.getCategory() == 'T'){
            String url = entity.getOpenchat();
            response.put("오픈채팅 url", url);
        }
        else{
            response.put("매칭 거절", "슬프네요. 거절당하셨어요");
        }
        entity.delete();
        return response;
    }
}
