package com.firesoon.drgs.dto.base;

import lombok.Data;

/**
 * @author create by yingjie.chen on 2018/1/16.
 * @version 2018/1/16 11:00
 */
@Data
public class DataSource {
    private Integer id;
    private String sourcename;
    private String method;
    private String ip;
    private String port;
    private String dbname;
    private String schema;
    private String username;
    private String password;
    private String classname;
    private String url;
    private String testsql;
    private String type;
    private String istarget; //是否目标库 1是
    private String des;//描述  所属分类
}
