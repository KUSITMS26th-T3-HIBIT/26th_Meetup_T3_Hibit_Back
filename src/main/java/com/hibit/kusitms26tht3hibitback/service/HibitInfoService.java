package com.hibit.kusitms26tht3hibitback.service;

import com.hibit.kusitms26tht3hibitback.domain.HibitInfo;
import com.hibit.kusitms26tht3hibitback.repository.HibitInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HibitInfoService {
    @Autowired
    HibitInfoRepository infoRepository;
    public HibitInfo insertInfo(HibitInfo info){
        return infoRepository.save(info);
    }

    public List<HibitInfo> getAllinfo(){
        return infoRepository.findAll();
    }
}
