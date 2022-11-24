package com.hibit.kusitms26tht3hibitback.controller;

import com.hibit.kusitms26tht3hibitback.domain.HibitInfo;
import com.hibit.kusitms26tht3hibitback.service.HibitInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/info")
public class HibitInfoController {

    @Autowired
    private HibitInfoService infoService;

    @PostMapping
    public HibitInfo register(@RequestBody HibitInfo info){
        return infoService.insertInfo(info);
    }

    @GetMapping
    public List<HibitInfo> getAllInfo(){
        return infoService.getAllinfo();
    }
}
