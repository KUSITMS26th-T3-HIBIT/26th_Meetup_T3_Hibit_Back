package com.hibit.kusitms26tht3hibitback.service;

import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.dto.MatchingResponseDto;
import com.hibit.kusitms26tht3hibitback.dto.MatchingSaveRequestDto;
import com.hibit.kusitms26tht3hibitback.dto.MatchingUpdateRequestDto;
import com.hibit.kusitms26tht3hibitback.repository.MatchingRepository;
import com.hibit.kusitms26tht3hibitback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MatchingService {

    private final MatchingRepository matchingRepository;
    private final UserRepository userRepository;

    @Transactional
    public int save(MatchingSaveRequestDto requestDto){
        return matchingRepository.save(requestDto.toEntity()).getIdx();
    }

    @Transactional
    public List<MatchingResponseDto> findAll(){
        List<Matching>list = matchingRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
        return list.stream().map(MatchingResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public MatchingResponseDto findById(int idx){
        Matching entity = matchingRepository.findById(idx).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+idx));
        entity.increaseView();
        return new MatchingResponseDto(entity);
    }
    @Transactional
    public int update(int idx, MatchingUpdateRequestDto requestDto){
        Matching matching = matchingRepository.findById(idx).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+idx));;
        matching.update(requestDto.getTitle(),
                requestDto.getExhibition(),
                requestDto.getContent(),
                requestDto.getNumber(),
                requestDto.getStart_date(),
                requestDto.getFinish_date(),
                requestDto.getEnd_date(),
                requestDto.getOpenchat(),
                requestDto.getWant());
        return idx;
    }

    @Transactional
    public int delete(int idx) {
        Matching entity = matchingRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+idx));;
        entity.delete();
        return idx;
    }




}
