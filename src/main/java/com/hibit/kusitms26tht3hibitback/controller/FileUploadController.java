package com.hibit.kusitms26tht3hibitback.controller;

import com.hibit.kusitms26tht3hibitback.dto.FileUploadResponse;
import com.hibit.kusitms26tht3hibitback.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileUploadController {
    private final S3Uploader s3Uploader;

    //유저 프로필 업로드
    @PostMapping("/users/profilePhoto")
    public String uploadProfilePhoto(@RequestParam("profilePhoto") MultipartFile multipartFile) throws IOException {
        //S3 Bucket 내부에 "/profile" 폴더

        return s3Uploader.upload(multipartFile, "profile");
        //이렇게바꾸면 된다. 대신 서비스단에서 파라미터 주의 ㅋㅋ 바까주셈
    }
}
