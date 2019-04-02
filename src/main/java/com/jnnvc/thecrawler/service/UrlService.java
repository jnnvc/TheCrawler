package com.jnnvc.thecrawler.service;

import com.jnnvc.thecrawler.entity.Url;
import com.jnnvc.thecrawler.mapper.UrlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {


    @Autowired
    UrlMapper urlMapper;
    /**
     * 获取一条数据
     */
    public Url selectOneUrl(Url urlP){
        Url url = urlMapper.selectUrlTopOne(urlP);
        if(url == null){
            return null;
        }
        urlMapper.updateUrlStateById(url);
        return url;
    }

    /**
     * 插入数据URL数据，如果存在，不插入
     * @param urlP
     * @return
     */
    public Integer insertUrl(Url urlP){
        int i = urlMapper.selectInfoIsCountByUrl(urlP);
        int c = 0;
        if(i==0){
            c = urlMapper.insertUrl(urlP);
        }
        return c;
    }

    public Integer updateUrlCode(Url url){

        return urlMapper.updateUrlCode(url);
    }


}
