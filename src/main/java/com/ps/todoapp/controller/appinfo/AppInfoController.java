package com.ps.todoapp.controller.appinfo;

import com.ps.todoapp.entity.appinfo.AppInfoDTO;
import com.ps.todoapp.service.appinfo.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class AppInfoController {

    @Autowired
    AppInfoService appInfoService;

    @GetMapping()
    public AppInfoDTO getInfo() {
        return appInfoService.getInfo();
    }
}
