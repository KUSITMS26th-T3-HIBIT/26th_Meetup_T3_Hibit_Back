package com.hibit.kusitms26tht3hibitback.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Entity
public class UploadFile {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String uploadFileName;
    private String storeFileUrl;

    public UploadFile(String uploadFileName, String storeFileUrl) {
        this.uploadFileName = uploadFileName;
        this.storeFileUrl = storeFileUrl;
    }
}
