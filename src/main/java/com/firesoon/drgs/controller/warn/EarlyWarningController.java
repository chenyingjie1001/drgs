package com.firesoon.drgs.controller.warn;

import com.firesoon.drgs.controller.base.BaseController;
import com.firesoon.drgs.dto.base.Pager;
import com.firesoon.drgs.dto.warn.EarlyWarning;
import com.firesoon.drgs.services.disease.MedicareService;
import com.firesoon.drgs.services.warn.EarlyWarningService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author create by yingjie.chen on 2018/1/15.
 * @version 2018/1/15 9:53
 */
@Api
@RestController
@RequestMapping(value = "/webservice/warn", method = RequestMethod.POST)
public class EarlyWarningController extends BaseController {

    @Autowired
    private EarlyWarningService service;

    @Autowired
    private MedicareService medicareService;

    @ApiOperation(value = "find", notes = "find")
    @RequestMapping("find")
    public Object find(@RequestBody EarlyWarning earlyWarning) {
        PageHelper.startPage(earlyWarning.getPageNum(), earlyWarning.getPageSize());
        return succ(new PageInfo<>(service.find(earlyWarning)));
    }

    @RequestMapping("add")
    public Object add(@RequestBody EarlyWarning earlyWarning) {
        service.add(earlyWarning);
        return succ();
    }

    @RequestMapping("update")
    public Object update(@RequestBody EarlyWarning earlyWarning) {
        service.update(earlyWarning);
        return succ();
    }

    @RequestMapping("del")
    public Object del(@RequestBody EarlyWarning earlyWarning) {
        service.del(earlyWarning);
        return succ();
    }

    @ApiOperation(value = "满足条件", notes = "满足条件")
    @RequestMapping("getCondition")
    public Object getCondition() {
        return succ(service.getCondition("3"));
    }

    @ApiOperation(value = "参数", notes = "参数")
    @RequestMapping("getParams")
    public Object getParams() {
        return succ(service.getCondition("1"));
    }

    @ApiOperation(value = "判断条件", notes = "判断条件")
    @RequestMapping("getCondiValues")
    public Object getCondiValues() {
        return succ(service.getCondition("2"));
    }

    @ApiOperation(value = "医生", notes = "医生")
    @RequestMapping("getDoctors")
    public Object getDoctors() {
        return succ(service.getDoctors());
    }

    @RequestMapping("getNotices")
    public Object getNotices() {
        List<Map<String, Object>> notices = medicareService.findcolType("constmonitor2");
        return succ(notices.stream().filter(map -> !map.containsValue("是否预警")).toArray());
    }
}
