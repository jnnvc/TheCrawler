package com.jnnvc.thecrawler;

import com.jnnvc.thecrawler.entity.Url;
import com.jnnvc.thecrawler.mapper.UrlMapper;
import com.jnnvc.thecrawler.service.UrlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TheCrawlerApplicationTests {

    @Autowired
    UrlMapper urlMapper;

    @Autowired
    UrlService urlService;

    @Test
    public void contextLoads() {

        Url url = new Url();
        url.setUrl("12345");
        url.setType(2);
        url.setState(1);
        Integer count = urlService.insertUrl(url);
        System.out.println(count);
    }

    @Test
    public void test1() {


    }



}
