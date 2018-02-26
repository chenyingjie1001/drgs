package com.firesoon.drgs.dto.warn;

import com.firesoon.drgs.dto.base.Pager;
import lombok.Data;

import java.util.Date;

/**
 * @author create by yingjie.chen on 2018/1/15.
 * @version 2018/1/15 9:55
 */
@Data
public class EarlyWarning extends Pager {

    private String id;
    private String name;
    private String condition;
    private String rulecode;
    private String rulecond;
    private String value;
    private String doctorcode;
    private String doctorname;
    private Date createdate;
    private String createby;
    private String remark;
    private String message;
    private String flag;
    private String telephone;

    //for search
    private String title;
}
