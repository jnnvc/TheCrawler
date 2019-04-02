package com.jnnvc.thecrawler.mapper;

import com.jnnvc.thecrawler.entity.Url;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UrlMapper {

    /**
     * 存储URL
     * @param url
     * @return
     */
    Integer insertUrl(Url url);

    /**
     * 获取URL
     * @param url
     * @return
     */
    Integer updateUrlStateById(Url url);

    /**
     * 查询一行URL信息
     * @return URL
     */
    Url selectUrlTopOne(Url url);

    /**
     * 查询这个URL是否存在
     * @return
     */
    Integer selectInfoIsCountByUrl(Url url);


    Integer updateUrlCode(Url url);


}
