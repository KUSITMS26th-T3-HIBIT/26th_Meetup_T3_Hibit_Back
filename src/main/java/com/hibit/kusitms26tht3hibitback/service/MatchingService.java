package com.hibit.kusitms26tht3hibitback.service;

import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.Posts;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import com.hibit.kusitms26tht3hibitback.dto.MatchingResponseDto;
import com.hibit.kusitms26tht3hibitback.dto.MatchingSaveRequestDto;
import com.hibit.kusitms26tht3hibitback.dto.MatchingUpdateRequestDto;
import com.hibit.kusitms26tht3hibitback.dto.UsermateResponseDto;
import com.hibit.kusitms26tht3hibitback.repository.MatchingRepository;
import com.hibit.kusitms26tht3hibitback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

//@RequiredArgsConstructor
@Service
public class MatchingService {

    @Autowired
    MatchingRepository matchingRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
    public int save(MatchingSaveRequestDto requestDto, Users user){
        requestDto.setUser(user);
        Matching matching = requestDto.toEntity();
        matchingRepository.save(matching);
        return matching.getIdx();
    }

    @Transactional
    public List<MatchingResponseDto> findAll(){
        List<Matching> list = matchingRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
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
        Matching matching = matchingRepository.findById(idx).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+idx));
        matching.update(requestDto.getTitle(),
                requestDto.getExhibition(),
                requestDto.getContent(),
                requestDto.getCategory(),
                requestDto.getNumber(),
                requestDto.getStart_date(),
                requestDto.getFinish_date(),
                requestDto.getOpenchat(),
                requestDto.getWant());
        return idx;
    }

    @Transactional
    public int delete(int idx) {
        Matching entity = matchingRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+idx));
        entity.delete();
        return idx;
    }

    public List<MatchingResponseDto> findByDeleteYn(final char deleteYn){
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        List<Matching> list= matchingRepository.findALlByDeleteYn(deleteYn, sort);
        return list.stream().map(MatchingResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public UsermateResponseDto findUserById(int idx){
        Matching entity = matchingRepository.findById(idx).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+idx));
        Users Mate = entity.getUser();
        return new UsermateResponseDto(Mate);
    }

    @Transactional
    public List<Matching> search(String id) {
        List<Matching> matchingList = matchingRepository.findByUser_IdContaining(id);
        return matchingList;
    }

    @Transactional
    public List<MatchingResponseDto> searchMatching(String keyword) {
        List<Matching> searchingList = matchingRepository.findByTitleContaining(keyword);
        return searchingList.stream().map(MatchingResponseDto::new).collect(Collectors.toList());
    }
}
