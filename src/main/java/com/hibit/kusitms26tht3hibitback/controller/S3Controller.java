package com.hibit.kusitms26tht3hibitback.controller;

import com.hibit.kusitms26tht3hibitback.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/upload")
public class S3Controller {

    private final S3Service s3Service;

    @PostMapping
    public void uploadFile(@RequestParam MultipartFile multipartFile)
            throws IOException {
        s3Service.saveUploadFile(multipartFile);
    }

}
