package com.hibit.kusitms26tht3hibitback.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hibit.kusitms26tht3hibitback.domain.UploadFile;
import com.hibit.kusitms26tht3hibitback.repository.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3Service {

//    private final AmazonS3Client amazonS3Client;
    private final UploadFileRepository uploadFileRepository;

    @Value("hibit-bucket")
    private String bucket;
    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    BasicAWSCredentials awsCreds = new BasicAWSCredentials("accessKey", "secretkey");
    AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
            .withRegion(region)
            .build();
    @Transactional
    public void saveUploadFile(MultipartFile multipartFile) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());

        String originalFilename = multipartFile.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(index + 1);

        String storeFileName = UUID.randomUUID() + "." + ext;
        String key = "test/" + storeFileName;

        try (InputStream inputStream = multipartFile.getInputStream()) {
            s3Client.putObject(new PutObjectRequest(bucket, key, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        }

        String storeFileUrl = s3Client.getUrl(bucket, key).toString();
        UploadFile uploadFile = new UploadFile(originalFilename, storeFileUrl);
        uploadFileRepository.save(uploadFile);
    }

}
