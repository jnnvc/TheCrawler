package com.jnnvc.thecrawler.service;

import com.jnnvc.thecrawler.entity.Url;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import static org.apache.commons.io.FileUtils.copyURLToFile;

@Service
public class Spider extends Thread{

    @Value("${imagebug.filepath}")
    public String filepath;

    @Value("${imagebug.path}")
    public String path;


    public static void main(String[] args) {

        Spider spider = new Spider();

        //spider.getImageURl();
        //spider.dlFile();
        Spider s = new Spider();
        //getImageURl();//下载图片URL
        //dlFile();//跳过图片URL下载图片
    }



    @Autowired
    UrlService urlService;

    private String getPageContent(String url) {
        HttpClientBuilder builder = HttpClients.custom();

        CloseableHttpClient client = builder.build();

        HttpGet request = new HttpGet(url);
;
        String content = null;
        try {
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            content = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return content;
    }
    /**
     *
     * @param path		目标地址
     */
    public void getAllLinks(String path){
        System.out.println("一个URL:"+path);
        Document doc;
        try{
            doc = Jsoup.parse(getPageContent(path));
        }catch (Exception e){
            urlService.updateUrlCode(new Url(null,path,null,null,444));
            System.out.println("修改URL CODE："+444+",出错啦！："+path);
            Url url2 = urlService.selectOneUrl(new Url(null,null,1,1,null));
            if(url2!=null){
                getAllLinks(url2.getUrl());
            }
            return;
        }

        Elements aLinks = doc.select("a[href]");
        Elements imgLinks = doc.select("img[src]");

        for(Element element:aLinks){

            String url =element.attr("href");

            //判断链接是否包含这两个头
            if(!url.contains("http://")&&!url.contains("https://")){
                url = path+url;
            }

            //如果文件中没有这个链接，或者链接中不包含javascript:则继续(因为有的是用js语法跳转)
            if(!url.contains("javascript")){

                //路径必须包含网页主链接--->防止爬入别的网站
                if(url.contains(path)){
                    urlService.insertUrl(new Url(null,url,1,1,null));
                    System.out.println("添加了网页URL："+url);
                }
            }
        }

        //同时抓取该页面图片链接
        for(Element element:imgLinks){
            String srcStr = element.attr("src");
            if(!srcStr.contains("http://")&&!srcStr.contains("https://")){//没有这两个头
                srcStr = path+srcStr;
            }
            urlService.insertUrl(new Url(null,srcStr,1,1,null));
            System.out.println("添加了图片URL:"+srcStr);
        }


        Url url2 = urlService.selectOneUrl(new Url(null,null,1,1,null));

        if(url2!=null){
            getAllLinks(url2.getUrl());
        }



    }



    private byte[] readInputStream(InputStream inputStream) throws IOException {

        return IOUtils.toByteArray(inputStream);

        /*byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();*/
    }

    @Override
    public void run() {
        Url urlObj = urlService.selectOneUrl(new Url(null,null,2,1,null));
        String url = urlObj.getUrl();

        URL u = null;
        File file = new File("C://images/"+ UUID.randomUUID()+url.substring(url.indexOf(".")));
        try {
            u = new URL(url);
            copyURLToFile(u,file);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
