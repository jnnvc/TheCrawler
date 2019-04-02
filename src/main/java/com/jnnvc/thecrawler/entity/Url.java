package com.jnnvc.thecrawler.entity;

public class Url {

    private Integer id;

    //url
    private String url;

    //url类型
    private Integer type;

    //是否下载过
    private Integer state;

    //状态码
    private Integer code;

    public Url() {}

    /**
     * 
     * @param id id
     * @param url url
     * @param type type
     * @param state state
     * @param code code
     */
    public Url(Integer id, String url, Integer type, Integer state,Integer code) {
        this.id = id;
        this.url = url;
        this.type = type;
        this.state = state;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", type=" + type +
                ", state=" + state +
                ", code=" + code +
                '}';
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
