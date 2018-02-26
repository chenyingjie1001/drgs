package com.firesoon.drgs.dto.disease;

import com.firesoon.drgs.dto.base.Pager;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import java.util.Date;

/**
 * @author create by yingjie.chen on 2017/12/5.
 * @version 2017/12/5 14:19
 */
@Data
@ExcelTarget("disease")
public class Disease extends Pager {
    private String ids;
    private String title;
    private String id; //uuid
    @Excel(name = "序号")
    private String serial;
    @Excel(name = "疾病编码")
    private String diseasecode;
    @Excel(name = "疾病名称")
    private String diseasename;
    @Excel(name = "手术/操作医保编码")
    private String medicarecode;
    @Excel(name = "手术/操作医保名称")
    private String medicarename;
    @Excel(name = "支付标准")
    private String payment;
    @Excel(name = "人数")
    private String personnum;
    @Excel(name = "总均费")
    private String totalpay;
    @Excel(name = "CQ材料名称")
    private String cq;
    @Excel(name = "CG材料名称")
    private String cg;
    @Excel(name = "超标床位均费")
    private String bed;
    @Excel(name = "个人自理、自费均费")
    private String personal;
    @Excel(name = "均费费用")
    private String avgpay;
    @Excel(name = "药品均费")
    private String drugpay;
    @Excel(name = "耗材均费")
    private String suppliespay;
    @Excel(name = "手术均费")
    private String surgerypay;
    private String bak;
    private String bak1;
    private String bak2;
    private String bak3;
    private Date createdate;
    private Date updatedate;
    private String operater;
    private String delflag;
}
