package com.jnnvc.thecrawler.controller;

import com.jnnvc.thecrawler.entity.Url;
import com.jnnvc.thecrawler.service.Spider;
import com.jnnvc.thecrawler.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class UrlController {


    @Autowired
    UrlService urlService;

    @Value("${imagebug.path}")
    public String path;	//雅达公司官网


    @Autowired
    Spider spider;


    @GetMapping("home")
    public String toHome() {
        return "home";
    }


    @PostMapping("go")
    @ResponseBody
    public String go(@RequestBody Map<String, Object> body) {
        spider.getAllLinks(path);
        return "go";
    }
    @PostMapping("urls")
    @ResponseBody
    public List<String> getUrls(){

        return null;
    }


}
