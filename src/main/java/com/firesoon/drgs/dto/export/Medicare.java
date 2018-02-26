package com.firesoon.drgs.dto.export;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import lombok.Data;

/**
 * @author create by yingjie.chen on 2017/12/14.
 * @version 2017/12/14 10:43
 */
@Data
@ExcelTarget("medicare")
public class Medicare {
    @Excel(name = "疾病编码")
    private String temp1;
    @Excel(name = "疾病名称")
    private String temp2;
    @Excel(name = "手术医保编码")
    private String temp3;
    @Excel(name = "手术医保名称")
    private String temp4;
    @Excel(name = "支付标准")
    private String temp5;
    @Excel(name = "百分之六十支付按标准起付最低价")
    private String temp6;
    @Excel(name = "医院向医保申请可支取的均费")
    private String temp7;
    @Excel(name = "总盈亏")
    private String temp8;
    @Excel(name = "总人次")
    private String temp9;
    @Excel(name = "总均费")
    private String temp10;
    @Excel(name = "CQ类材料均费")
    private String temp11;
    @Excel(name = "CG类材料均费")
    private String temp12;
    @Excel(name = "超标床位均费")
    private String temp13;
    @Excel(name = "个人自理自费均费")
    private String temp14;
    @Excel(name = "药品均费")
    private String temp15;
    @Excel(name = "耗材均费")
    private String temp16;
    @Excel(name = "手术费均")
    private String temp17;
}
