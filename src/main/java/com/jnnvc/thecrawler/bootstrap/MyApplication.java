package com.jnnvc.thecrawler.bootstrap;

import com.jnnvc.thecrawler.TheCrawlerApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheCrawlerApplication.class,args);
    }
}
