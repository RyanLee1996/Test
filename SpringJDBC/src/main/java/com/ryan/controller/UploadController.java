package com.ryan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RequestMapping
public class UploadController {
    @RequestMapping(value="/upload", method= RequestMethod.GET)
    public String showUploadPage(){
        return "uploadFile";         //view文件夹下的上传单个文件的页面
    }
}
