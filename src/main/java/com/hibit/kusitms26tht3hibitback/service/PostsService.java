package com.hibit.kusitms26tht3hibitback.service;

import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.Posts;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import com.hibit.kusitms26tht3hibitback.dto.PostResponseDto;
import com.hibit.kusitms26tht3hibitback.dto.PostUpdateRequestDto;
import com.hibit.kusitms26tht3hibitback.dto.PostsSaveRequestDto;
import com.hibit.kusitms26tht3hibitback.repository.PostsRepository;
import com.hibit.kusitms26tht3hibitback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostsService {
    @Autowired
    PostsRepository postsRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
    public int save(PostsSaveRequestDto requestDto, Users user)
    {
        requestDto.setUser(user);
        Posts post = requestDto.toEntity();
        postsRepository.save(post);
        return post.getIdx();
    }

    @Transactional
    public List<PostResponseDto> findByDeleteYn(final char deleteYn){
        Sort sort = Sort.by(Sort.Direction.DESC,"createdDate");
        List<Posts> list = postsRepository.findALlByDeleteYn(deleteYn, sort);
        return list.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto findById(int idx){
        Posts entity= postsRepository.findById(idx).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+idx));
        entity.increaseView();
        return new PostResponseDto(entity);
    }

    @Transactional
    public int update(int idx, PostUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(idx).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+idx));
        posts.update(requestDto.getTitle(),requestDto.getContent(), requestDto.getFile());
        return idx;
    }

    @Transactional
    public int delete(int idx){
        Posts entity = postsRepository.findById(idx).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+idx));
        entity.delete();
        return idx;
    }

    /* search */
    @Transactional
    public List<Posts> search(String id) {
        List<Posts> postsList = postsRepository.findByUser_IdContaining(id);
        return postsList;
    }

}
