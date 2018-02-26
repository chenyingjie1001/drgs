package com.firesoon.drgs.dto.range;

import com.firesoon.drgs.dto.base.Pager;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import java.util.Date;

/**
 * @author create by yingjie.chen on 2017/12/1.
 * @version 2017/12/1 16:11
 * 值域代码和值可做联合主键
 */
@Data
@ExcelTarget("range")
public class Range extends Pager {
    private String ids;
    private String title;
    private Integer id;
    @Excel(name = "值域代码")
    private String range;
    @Excel(name = "值域代码名称")
    private String rangename;
    @Excel(name = "值")
    private String code;
    @Excel(name = "值含义")
    private String codedesc;
    @Excel(name = "类别")
    private String rangetype;
    @Excel(name = "说明")
    private String explanation;
    @Excel(name = "扩展备注")
    private String bak;
    @Excel(name = "列1")
    private String bak1;
    @Excel(name = "列2")
    private String bak2;
    private String bak3;
    private Date createdate;
    private Date updatedate;
    private String operater;
}
