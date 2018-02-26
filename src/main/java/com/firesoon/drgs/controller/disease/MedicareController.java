package com.firesoon.drgs.controller.disease;

import com.firesoon.drgs.Utils.MapUtil;
import com.firesoon.drgs.controller.base.BaseController;
import com.firesoon.drgs.services.disease.MedicareService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.jeecgframework.poi.word.WordExportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.Color;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;  

/**
 * @author create by yingjie.chen on 2017/10/11.
 * @version 2017/10/11 17:06
 */
@Api
@RestController
@RequestMapping(value = "/webservice/medicare", method = RequestMethod.POST)
public class MedicareController extends BaseController {


    @Autowired
    private MedicareService service;


    /**
     * 找到日报对应的图表数据
     *
     * @param map
     * @return
     */
    @ApiOperation(value = "findDateReportChart", notes = "findDateReportChart")
    @RequestMapping(value = "findDateReportChart")
    public Object findDateReportChart(@RequestBody Map<String, Object> map) {
        return success(service.findDateReportChart(map));
    }

    /**
     * 找到日报的table数据
     *
     * @param map
     * @return
     */
    @ApiOperation(value = "getMedicareDateReport", notes = "getMedicareDateReport")
    @RequestMapping(value = "getMedicareDateReport")
    public Object getMedicareDateReport(@RequestBody Map<String, Object> map) {
        List<Map<String, Object>> colMap = service.findcolType("medciare_complete_datareport");
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
        List<Map<String, Object>> data = service.findMedicareDateReport(map);
        return success(new PageInfo<>(data), colMap);
    }

    /**
     * 导出数据
     *
     * @param request
     * @param startdate
     * @param enddate
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "exportDateReport", notes = "exportDateReport")
    @GetMapping(value = "exportDateReport")
    public void exportDateReport(HttpServletRequest request, String startdate, String enddate, String medicareType, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> cols = service.findcolType("medciare_complete_datareport");
        List<ExcelExportEntity> entityList = new ArrayList<ExcelExportEntity>();
        Map<String, Object> map = new HashMap<>();
        map.put("startdate", startdate);
        map.put("enddate", enddate);
        map.put("medicareType", medicareType);
        List<Map<String, Object>> list = service.findMedicareDateReport(map);
        for (Map<String, Object> col : cols) {
            entityList.add(new ExcelExportEntity(col.get("COLUMN_NAME").toString(), col.get("COLUMN_NAME").toString()));
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), entityList, list);
        String codedFileName = "市属医院单病种付费目标病种完成情况日报表";
        if (workbook instanceof HSSFWorkbook) {
            codedFileName = codedFileName + ".xls";
        } else {
            codedFileName = codedFileName + ".xlsx";
        }

        if (this.isIE(request)) {
            codedFileName = URLEncoder.encode(codedFileName, "UTF8");
        } else {
            codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }

    /**
     * 导出数据
     *
     * @param request
     * @param type
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "exportPatient", notes = "exportPatient")
    @GetMapping(value = "exportPatient")
    public void exportPatient(HttpServletRequest request, String type, String startdate, String enddate, HttpServletResponse response) throws IOException {
        Map map = new HashMap<>();
        map.put("type", type);
        map.put("startdate", startdate);
        map.put("enddate", enddate);
        List<Map<String, Object>> cols = service.findcolType(map);
        List<ExcelExportEntity> entityList = new ArrayList<ExcelExportEntity>();
        List<Map<String, Object>> list = service.costMonitoring(map);
        for (Map<String, Object> col : cols) {
            entityList.add(new ExcelExportEntity(col.get("COLUMN_NAME").toString(), col.get("COLUMN_NAME").toString()));
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), entityList, list);
        String codedFileName = "控费病种病例分析";
        if (workbook instanceof HSSFWorkbook) {
            codedFileName = codedFileName + ".xls";
        } else {
            codedFileName = codedFileName + ".xlsx";
        }

        if (this.isIE(request)) {
            codedFileName = URLEncoder.encode(codedFileName, "UTF8");
        } else {
            codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }

    /**
     * 导出数据
     *
     * @param request
     * @param type
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "exportMonitor", notes = "exportMonitor")
    @GetMapping(value = "exportMonitor")
    public void exportMonitor(HttpServletRequest request, String type, String startdate, String enddate, HttpServletResponse response) throws IOException {
        Map map = new HashMap<>();
        map.put("type", type);
        map.put("startdate", startdate);
        map.put("enddate", enddate);
        List<Map<String, Object>> cols = service.findcolType(map);
        List<ExcelExportEntity> entityList = new ArrayList<ExcelExportEntity>();
        List<Map<String, Object>> list = service.costMonitoring(map);
        for (Map<String, Object> col : cols) {
        	if("是否预警".equals(col.get("COLUMN_NAME").toString())){
        		continue;
        	}
            entityList.add(new ExcelExportEntity(col.get("COLUMN_NAME").toString(), col.get("COLUMN_NAME").toString()));
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), entityList, list);
        String codedFileName = "在院控费病种病例监控";
        if (workbook instanceof HSSFWorkbook) {
            codedFileName = codedFileName + ".xls";
        } else {
            codedFileName = codedFileName + ".xlsx";
        }
        
        if (this.isIE(request)) {
            codedFileName = URLEncoder.encode(codedFileName, "UTF8");
        } else {
            codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }

    /**
     * 导出数据
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "export", notes = "export")
    @GetMapping(value = "export")
    public void export(HttpServletRequest request, String startdate, String enddate, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> cols = service.findColForMedicare(null);
        List<ExcelExportEntity> entityList = new ArrayList<ExcelExportEntity>();
        Map<String, Object> map = new HashMap<>();
        map.put("startdate", startdate);
        map.put("enddate", enddate);
        List<Map<String, Object>> list = service.getMedicares(map);
        for (Map<String, Object> col : cols) {
            entityList.add(new ExcelExportEntity(col.get("COLUMN_NAME").toString(), col.get("COLUMN_NAME").toString()));
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), entityList, list);
        String codedFileName = "控费病种汇总分析";
        if (workbook instanceof HSSFWorkbook) {
            codedFileName = codedFileName + ".xls";
        } else {
            codedFileName = codedFileName + ".xlsx";
        }

        if (this.isIE(request)) {
            codedFileName = URLEncoder.encode(codedFileName, "UTF8");
        } else {
            codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }

    /***
     * 全院控费  病例数 下载
     * @param map
     * @return
     */
    @ApiOperation(value = "downloadKongFeiBLS", notes = "downloadKongFeiBLS")
    @GetMapping("downloadKongFeiBLS")
    public void downloadKongFeiBLS(String type, String startdate, String enddate,HttpServletRequest request, HttpServletResponse response) throws IOException{
    	Map map = new HashMap<>();
        map.put("type", type);
        map.put("report", type);
        map.put("startdate", startdate);
        map.put("enddate", enddate);
    	List<ExcelExportEntity> entityList = new ArrayList<ExcelExportEntity>();
    	List<Map<String, Object>> cols = new ArrayList<Map<String, Object>>();
    	Map<String, Object> colmap1 = new HashMap<String, Object>();
    	colmap1.put("COLUMN_NAME", "科室");
    	cols.add(colmap1);
    	Map<String, Object> colmap2 = new HashMap<String, Object>();
    	colmap2.put("COLUMN_NAME", "医疗小组");
    	cols.add(colmap2);
    	Map<String, Object> colmap3 = new HashMap<String, Object>();
    	colmap3.put("COLUMN_NAME", "控费主诊断+控费主手术病例数");
    	cols.add(colmap3);
    	Map<String, Object> colmap4 = new HashMap<String, Object>();
    	colmap4.put("COLUMN_NAME", "控费主诊断+非控费主手术病例数");
    	cols.add(colmap4);
    	Map<String, Object> colmap5 = new HashMap<String, Object>();
    	colmap5.put("COLUMN_NAME", "非控费主诊断+控费主手术病例数");
    	cols.add(colmap5);
    	Map<String, Object> colmap6 = new HashMap<String, Object>();
    	colmap6.put("COLUMN_NAME", "非控费主诊断+非控费主手术病例数");
    	cols.add(colmap6);
    	Map<String, Object> colmap7 = new HashMap<String, Object>();
    	colmap7.put("COLUMN_NAME", "控费主诊断+控费主手术病例数比例");
    	cols.add(colmap7);
    	List<Map<String, Object>> list = service.downloadKongFeiBLS(map);
        for (Map<String, Object> col : cols) {
        	ExcelExportEntity excelExportEntity = new ExcelExportEntity(col.get("COLUMN_NAME").toString(), col.get("COLUMN_NAME").toString().replaceAll("主", ""));
        	if(col.get("COLUMN_NAME").toString().equals("科室")){
        		excelExportEntity.setMergeVertical(true);
        	}
        	excelExportEntity.setWrap(true);
        	excelExportEntity.setWidth(15);
        	excelExportEntity.setHeight(12);
        	//excelExportEntity.setStatistics(true);
            entityList.add(excelExportEntity);
        }
        ExportParams exportParams = new ExportParams();
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, entityList, list);
        String codedFileName = "report";
        if ("cykfblszb".equals(type)) {
        	codedFileName = "出院病种类别病例数分布";
        } else if ("zykfblszb".equals(type)) {
        	codedFileName = "在院病种类别病例数分布";
        }
        if (workbook instanceof HSSFWorkbook) {
            codedFileName = codedFileName + ".xls";
        } else {
            codedFileName = codedFileName + ".xlsx";
        }

        if (this.isIE(request)) {
            codedFileName = URLEncoder.encode(codedFileName, "UTF8");
        } else {
            codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }
    
    
    /***
     * 全院控费  总费用 下载
     * @param map
     * @return
     */
    @ApiOperation(value = "downloadKongFeiZFY", notes = "downloadKongFeiZFY")
    @GetMapping("downloadKongFeiZFY")
    public void downloadKongFeiZFY(String type, String startdate, String enddate,HttpServletRequest request, HttpServletResponse response) throws IOException{
    	Map map = new HashMap<>();
        map.put("type", type);
        map.put("report", type);
        map.put("startdate", startdate);
        map.put("enddate", enddate);
    	List<ExcelExportEntity> entityList = new ArrayList<ExcelExportEntity>();
    	List<Map<String, Object>> cols = new ArrayList<Map<String, Object>>();
    	Map<String, Object> colmap1 = new HashMap<String, Object>();
    	colmap1.put("COLUMN_NAME", "科室");
    	cols.add(colmap1);
    	Map<String, Object> colmap2 = new HashMap<String, Object>();
    	colmap2.put("COLUMN_NAME", "医疗小组");
    	cols.add(colmap2);
    	Map<String, Object> colmap3 = new HashMap<String, Object>();
    	colmap3.put("COLUMN_NAME", "控费主诊断+控费主手术总费用");
    	cols.add(colmap3);
    	Map<String, Object> colmap4 = new HashMap<String, Object>();
    	colmap4.put("COLUMN_NAME", "控费主诊断+非控费主手术总费用");
    	cols.add(colmap4);
    	Map<String, Object> colmap5 = new HashMap<String, Object>();
    	colmap5.put("COLUMN_NAME", "非控费主诊断+控费主手术总费用");
    	cols.add(colmap5);
    	Map<String, Object> colmap6 = new HashMap<String, Object>();
    	colmap6.put("COLUMN_NAME", "非控费主诊断+非控费主手术总费用");
    	cols.add(colmap6);
    	Map<String, Object> colmap7 = new HashMap<String, Object>();
    	colmap7.put("COLUMN_NAME", "控费主诊断+控费主手术总费用比例");
    	cols.add(colmap7);
    	List<Map<String, Object>> list = service.downloadKongFeiZFY(map);
        for (Map<String, Object> col : cols) {
        	ExcelExportEntity excelExportEntity = new ExcelExportEntity(col.get("COLUMN_NAME").toString(), col.get("COLUMN_NAME").toString().replaceAll("主", ""));
        	if(col.get("COLUMN_NAME").toString().equals("科室")){
        		excelExportEntity.setMergeVertical(true);
        	}
        	excelExportEntity.setWrap(true);
        	excelExportEntity.setWidth(15);
        	excelExportEntity.setHeight(12);
            entityList.add(excelExportEntity);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), entityList, list);
        String codedFileName = "report";
        if ("cykfblylzfyzb".equals(type)) {
        	codedFileName = "出院病种类别医疗总费用分布";
        } else if ("zykfblylzfyzb".equals(type)) {
        	codedFileName = "在院病种类别医疗总费用分布";
        }
        if (workbook instanceof HSSFWorkbook) {
            codedFileName = codedFileName + ".xls";
        } else {
            codedFileName = codedFileName + ".xlsx";
        }

        if (this.isIE(request)) {
            codedFileName = URLEncoder.encode(codedFileName, "UTF8");
        } else {
            codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }
    
    
    
    /**
     * 获取抬头
     *
     * @return
     */
    @ApiOperation(value = "getTitle", notes = "getTitle")
    @RequestMapping(value = "getTitle")
    public Object getTitle(@RequestBody Map<String, Object> map) {
        return success(service.getTitle(map));
    }


    /**
     * 按病种汇总
     *
     * @param map
     * @return
     */
    @ApiOperation(value = "getMedicares", notes = "getMedicares")
    @RequestMapping(value = "getMedicares")
    public Object getMedicares(@ApiParam(name = "{title:name}") @RequestBody Map<String, Object> map) {
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
        return success(new PageInfo<Map<String, Object>>(service.getMedicares(map)), service.findColForMedicare(null));
    }

    /**
     * 按病种汇总详情
     *
     * @param map
     * @return
     */
    @ApiOperation(value = "getDetail", notes = "getDetail")
    @RequestMapping(value = "getDetail")
    public Object getDetail(@ApiParam(name = "{title:name}") @RequestBody Map<String, Object> map) {
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
        List<Map<String, Object>> lists = service.getDetail(map);
        return success(new PageInfo<Map<String, Object>>(lists), service.findColForDetailGroup(map));
    }

    /***
     * {"pageNum":1,"pageSize":10,"title":"市级","desc":1,"orderby":"医保类别"}
     * @param map
     * @return
     */
    @ApiOperation(value = "costMonitoring", notes = "costMonitoring")
    @RequestMapping(value = "costMonitoring")
    public Object costMonitoring(@ApiParam(name = "{\"pageNum\":1,\"pageSize\":10,\"title\":\"市级\",\"desc\":1,\"orderby\":\"医保类别\"}") @RequestBody Map<String, Object> map) {
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
        return success(new PageInfo<Map<String, Object>>(service.costMonitoring(map)), service.findcolType(map));
    }


    /**
     * 获取详细信息
     *
     * @param map
     * @return
     */
    @ApiOperation(value = "getDetailMsg", notes = "getDetailMsg")
    @RequestMapping(value = "getDetailMsg")
    public Object getDetailMsg(@ApiParam(name = "{title:name,title_value:value}") @RequestBody Map<String, Object> map) {
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
        return success(new PageInfo<Map<String, Object>>(service.getDetailMsg(map)), service.findColForDetail(map));
    }

    /***
     * 全院控费病例占比图表
     * @param map
     * @return
     */
    @ApiOperation(value = "findPerHospitalReportChart", notes = "全院控费病例占比图表")
    @RequestMapping(value = "findPerHospitalReportChart")
    public Object findPerHospitalReportChart(@RequestBody Map<String, Object> map) {
        return success(service.findPerHospitalReportChart(map));
    }

    /***
     * 全院控费    病例    占比     饼图
     * @param map
     * @return
     */
    @ApiOperation(value = "findPerHospitalReportPieChart", notes = "全院控费病例占比饼图")
    @RequestMapping(value = "findPerHospitalReportPieChart")
    public Object findPerHospitalReportPieChart(@RequestBody Map<String, Object> map) {
        return success(service.findPerHospitalReportPieChart(map));
    }

    /***
     * 全院控费总费用占比图表 
     * @param map
     * @return
     */
    @ApiOperation(value = "findCostHospitalReportChart", notes = "全院控费总费用占比图表")
    @RequestMapping(value = "findCostHospitalReportChart")
    public Object findCostHospitalReportChart(@RequestBody Map<String, Object> map) {
        return success(service.findCostHospitalReportChart(map));
    }

    /***
     * 全院控费总费用占比饼图
     * @param map
     * @return
     */
    @ApiOperation(value = "findCostHospitalReportPieChart", notes = "全院控费总费用占比饼图")
    @RequestMapping(value = "findCostHospitalReportPieChart")
    public Object findCostHospitalReportPieChart(@RequestBody Map<String, Object> map) {
        return success(service.findCostHospitalReportPieChart(map));
    }

    /**
     * 医保结算单
     *
     * @param map
     * @return
     */
    @ApiOperation(value = "Medicare settlement list", notes = "Medicare settlement list")
    @RequestMapping("findMedicareSettlementList")
    public Object findMedicareSettlementList(@RequestBody Map<String, Object> map) {
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
        return success(new PageInfo<>(service.findMedicareSettlementList(map)), service.findcolType("medicare_settlement_list"));
    }

    /**
     * 退出医保
     *
     * @param map
     * @return
     */
    @ApiOperation(value = "out_medicare", notes = "out_medicare")
    @RequestMapping("outMedicare")
    public Object outMedicare(@RequestBody Map<String, Object> map) {
        service.insertOutMedicare(map);
        return success();
    }
    
    /**
     * 取消  退出医保
     *
     * @param map
     * @return
     */
    @ApiOperation(value = "cancelOut_medicare", notes = "out_medicare")
    @RequestMapping("cancelOutmedicare")
    public Object cancelOut_medicare(@RequestBody Map<String, Object> map) {
        service.cancelOut_medicare(map);
        return success();
    }

    @ApiOperation(value = "医保结算清单导出", notes = "医保结算清单导出")
    @GetMapping("exportMedicareSettlementList")
    public void exportMedicareSettlementList(HttpServletRequest request, String startdate, String enddate, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> cols = service.findcolType("medicare_settlement_list");
        List<ExcelExportEntity> entityList = new ArrayList<ExcelExportEntity>();
        Map<String, Object> map = new HashMap<>();
        map.put("startdate", startdate);
        map.put("enddate", enddate);
        //title数据
        List<Map<String, Object>> list = service.findMedicareSettlementList(map);
        for (Map<String, Object> col : cols) {
            entityList.add(new ExcelExportEntity(col.get("COLUMN_NAME").toString(), col.get("COLUMN_NAME").toString()));
        }
        ExportParams exportParam = new ExportParams();
        Workbook workbook = ExcelExportUtil.exportExcel(exportParam, entityList, list);
        String codedFileName = "医保结算清单导出";
        if (workbook instanceof HSSFWorkbook) {
            codedFileName = codedFileName + ".xls";
        } else {
            codedFileName = codedFileName + ".xlsx";
        }

        if (this.isIE(request)) {
            codedFileName = URLEncoder.encode(codedFileName, "UTF8");
        } else {
            codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }

    @ApiOperation(value = "医保结算清单明细导出", notes = "医保结算清单明细导出")
    @GetMapping("exportMedicareSettlementListDetail")
    public void exportMedicareSettlementListDetail(HttpServletRequest request, String startdate, String enddate, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> cols = service.findcolType("constmonitor1");
        List<ExcelExportEntity> entityList = new ArrayList<ExcelExportEntity>();
        Map<String, Object> map = new HashMap<>();
        map.put("startdate", startdate);
        map.put("enddate", enddate);
        //title数据
        Map<String, Object> titles = service.getTitle(map);
        List<Map<String, Object>> list = service.costMonitoring(map); //map数据默认是constmonitor1 有type为monitor才会是constmonitor2
        for (Map<String, Object> col : cols) {
            entityList.add(new ExcelExportEntity(col.get("COLUMN_NAME").toString(), col.get("COLUMN_NAME").toString()));
        }
        entityList.add(new ExcelExportEntity("状态", "状态"));
        ExportParams exportParam = new ExportParams();
        exportParam.setTitle(startdate+"明细数据");
        StringBuffer sb = new StringBuffer();
        sb.append("总病例:"+titles.get("总病例")+"        ");
        sb.append("定额结算金额:"+titles.get("定额结算金额")+"        ");
        sb.append("项目结算金额:"+titles.get("项目结算金额")+"        ");
        sb.append("项目扣款金额:"+titles.get("项目扣款金额")+"        ");
        sb.append("盈亏结算金额:"+titles.get("盈亏结算金额")+"        ");
        sb.append("亏损病例数:"+titles.get("亏损病例数")+"        ");
        sb.append("亏损定额结算金额:"+titles.get("亏损定额结算金额")+"        ");
        sb.append("盈利病例数:"+titles.get("盈利病例数")+"        ");
        sb.append("盈利定额结算金额:"+titles.get("盈利定额结算金额")+"        ");
        sb.append("实付病例数:"+titles.get("实付病例数")+"        ");
        sb.append("实付定额结算金额:"+titles.get("实付定额结算金额")+"        ");
        sb.append("可申请退出病例数:"+titles.get("可申请退出病例数")+"        ");
        sb.append("实际退出病例数:"+titles.get("实际退出病例数")+"        ");
        exportParam.setSecondTitle(sb.toString());
        Workbook workbook = ExcelExportUtil.exportExcel(exportParam, entityList, list);
        String codedFileName = "医保结算清单明细导出";
        if (workbook instanceof HSSFWorkbook) {
            codedFileName = codedFileName + ".xls";
        } else {
            codedFileName = codedFileName + ".xlsx";
        }

        if (this.isIE(request)) {
            codedFileName = URLEncoder.encode(codedFileName, "UTF8");
        } else {
            codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }
    /***
     * 出院控病病例数占比 表格
     * @param map
     * @return
     */
    @ApiOperation(value = "findCostHospitalReportForm", notes = "findCostHospitalReportForm")
    @RequestMapping("findCostHospitalReportForm")
    public Object findCostHospitalReportForm(@RequestBody Map<String, Object> map){
    	if(map.get("pageNum") != null && map.get("pageSize") != null){
    		PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
    		return success(new PageInfo<>(service.findCostHospitalReportForm(map)));
    	}
    	return success(service.findCostHospitalReportForm(map));
    }
    
    
    /***
     * 退出医保控费结算病例
     * @param map
     * @return
     */
    @ApiOperation(value = "tuichuyibaokfjsbl", notes = "tuichuyibaokfjsbl")
    @RequestMapping("tuichuyibaokfjsbl")
    public Object tuichuyibaokfjsbl(@RequestBody Map<String, Object> map){
    	if(map.get("pageNum") != null && map.get("pageSize") != null){
    		PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
    		return success(new PageInfo<>(service.tuichuyibaokfjsbl(map)));
    	}
    	return success(service.tuichuyibaokfjsbl(map));
    }
    
    /***
     * 退出医保控费结算病例详细
     * @param map
     * @return
     */
    @ApiOperation(value = "tuichuyibaokfjsblxx", notes = "tuichuyibaokfjsblxx")
    @RequestMapping(value = "tuichuyibaokfjsblxx")
    public Object tuichuyibaokfjsblxx(@ApiParam(name = "{\"pageNum\":1,\"pageSize\":10,\"title\":\"市级\",\"desc\":1,\"orderby\":\"医保类别\"}") @RequestBody Map<String, Object> map) {
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
        return success(new PageInfo<Map<String, Object>>(service.tuichuyibaokfjsblxx(map)), service.findcolType(map));
    }
    
    /***
     * 控费医疗费用  占比 表格
     * @param map
     * @return
     */
    @ApiOperation(value = "findCostHospitalReportTotalCostForm", notes = "findCostHospitalReportTotalCostForm")
    @RequestMapping("findCostHospitalReportTotalCostForm")
    public Object findCostHospitalReportTotalCostForm(@RequestBody Map<String, Object> map){
    	if(map.get("pageNum") != null && map.get("pageSize") != null){
    		PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
    		return success(new PageInfo<>(service.findCostHospitalReportTotalCostForm(map)));
    	}
    	return success(service.findCostHospitalReportTotalCostForm(map));
    }
    
    

    
    /***
     * 非控费调整预测
     * @param map
     * @return
     */
    @ApiOperation(value = "findFeiKongFeiTZ", notes = "findFeiKongFeiTZ")
    @RequestMapping("findFeiKongFeiTZ")
    public Object findFeiKongFeiZhuZhenDuanTZ(@RequestBody Map<String, Object> map){
    	PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
    	map.put("type", map.get("report"));
        return success(new PageInfo<>(service.findFeiKongFeiTZ(map)),service.findcolType(map));
    }
    
    /***
     * 非控费调整预测  表格下载
     * @param map
     * @return
     */
    @ApiOperation(value = "downloadFeiKongFeiTZ", notes = "downloadFeiKongFeiTZ")
    @GetMapping("downloadFeiKongFeiTZ")
    public void downloadFeiKongFeiZhuZhenDuanTZ(String type, String startdate, String enddate,HttpServletRequest request, HttpServletResponse response) throws IOException{
    	Map map = new HashMap<>();
        map.put("type", type);
        map.put("report", type);
        map.put("startdate", startdate);
        map.put("enddate", enddate);
    	List<ExcelExportEntity> entityList = new ArrayList<ExcelExportEntity>();
    	List<Map<String, Object>> cols = service.findcolType(map);
    	List<Map<String, Object>> list = service.findFeiKongFeiTZ(map);
        for (Map<String, Object> col : cols) {
            entityList.add(new ExcelExportEntity(col.get("COLUMN_NAME").toString(), col.get("COLUMN_NAME").toString()));
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), entityList, list);
        String codedFileName = "report";
        if("fkfzzdtzyc".equals(type)){
        	codedFileName = "单非主诊断调整评估";
        }else if("fkfzshtzyc".equals(type)){
        	codedFileName = "单非主手术调整评估";
        }else if("fkfbztzyc".equals(type)){
        	codedFileName = "双非病种病例明细";
        }
        if (workbook instanceof HSSFWorkbook) {
            codedFileName = codedFileName + ".xls";
        } else {
            codedFileName = codedFileName + ".xlsx";
        }

        if (this.isIE(request)) {
            codedFileName = URLEncoder.encode(codedFileName, "UTF8");
        } else {
            codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }
    
    
    /***
     * 测试表格下载
     * @param map
     * @return
     */
    @ApiOperation(value = "testdownload", notes = "testdownload")
    @GetMapping("testdownload")
    public void testdownload(String type, String startdate, String enddate,HttpServletRequest request, HttpServletResponse response) throws IOException{
    	Map map = new HashMap<>();
        map.put("type", type);
        map.put("report", type);
        map.put("startdate", startdate);
        map.put("enddate", enddate);
    	List<ExcelExportEntity> entityList = new ArrayList<ExcelExportEntity>();
    	List<Map<String, Object>> cols = service.findcolType(map);
    	map.put("orderby", "医保类别");
    	List<Map<String, Object>> list = service.findFeiKongFeiTZ(map);
    	int i = 0;
        for (Map<String, Object> col : cols) {
        	if(i<2){
        		i++;
        		continue;
        	}
        	ExcelExportEntity excelExportEntity = new ExcelExportEntity(col.get("COLUMN_NAME").toString(), col.get("COLUMN_NAME").toString());
        	if(col.get("COLUMN_NAME").toString().equals("医保类别")){
        		//excelExportEntity.setNeedMerge(true);
        		int[] a = new int[2];
        		a[0] = 0;
        		//excelExportEntity.setMergeRely(a);
        		excelExportEntity.setMergeVertical(true);
        	}
            entityList.add(excelExportEntity);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), entityList, list);
        String codedFileName = "非控调整";
        if (workbook instanceof HSSFWorkbook) {
            codedFileName = codedFileName + ".xls";
        } else {
            codedFileName = codedFileName + ".xlsx";
        }

        if (this.isIE(request)) {
            codedFileName = URLEncoder.encode(codedFileName, "UTF8");
        } else {
            codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }
    
    /***
     * 测试表格word下载
     * 模板类型 未测试
     * @param map
     * @return
     */
    @ApiOperation(value = "testdownloadword", notes = "testdownloadword")
    @GetMapping("testdownloadword")
    public void testdownloadword(String type, String startdate, String enddate,HttpServletRequest request, HttpServletResponse response) throws IOException{
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("department", "Easypoi");
        map.put("person", "JueYue");
        map.put("me","JueYue");
        map.put("date", "2015-01-03");
        try {
            XWPFDocument doc = WordExportUtil.exportWord07(
                "cn/afterturn/easypoi/test/word/doc/Simple.docx", map);
            FileOutputStream fos = new FileOutputStream("D:/excel/simple.docx");
            doc.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /***
     * 退出医保详细下载
     * @param map
     * @return
     */
    @ApiOperation(value = "downloadtuichuyibaokfjsblxx", notes = "downloadtuichuyibaokfjsblxx")
    @GetMapping("downloadtuichuyibaokfjsblxx")
    public void downloadtuichuyibaokfjsblxx(String type, String startdate, String enddate,HttpServletRequest request, HttpServletResponse response) throws IOException{
    		Map map = new HashMap<>();
    		map.put("type", type);
    		map.put("startdate", startdate);
    		map.put("enddate", enddate);
    	  
    		List<Map<String, Object>> listclo = service.findApplicationtemplate();
   	        List<Map<String, Object>> listdata = service.findout_medicare(map);
   	        if(listdata == null || listdata.size() == 0){
   	        	throw new RuntimeException("没有退出医保定额结算的病例");
   	        }
    	   request.setCharacterEncoding("utf-8");  
    	   response.setContentType("application/x-download");  
           String fileName = URLEncoder.encode("退出医保定额结算病例.doc", "UTF-8");  
           response.addHeader("Content-Disposition", "attachment;filename=" + fileName);  

   	    	RtfWriter2 rtfWriter2 = null;
    	   try {  
    		// 创建word文档,并设置纸张的大小
        	Document document = new Document(PageSize.A4); 
      	    ServletOutputStream out = response.getOutputStream();
    		rtfWriter2= RtfWriter2.getInstance(document,out);
    	    document.open();    
    	   
    	    // 表格的主体    
    	  /* cell = new Cell("Example cell 2");
    	    cell.setRowspan(2);//当前单元格占两行,纵向跨度   
    	    table.addCell(cell);*/    
    	    //宋体 STSong-Light
    	    
    	    Font f3  = new Font(BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED) , 13, Font.BOLD, new Color(0, 0, 0));//字段名样式
    	    Font f5  = new Font(BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED) , 13, Font.NORMAL, new Color(0, 0, 0));//内容样式
    	    //f3.setSize(13);//设置字体大小
    	    int size = listdata.size();
    	    for(int i=0;i<size;i++){
    	    	Map<String, Object> data = listdata.get(i);
    	    	//设置头 
    	    	   Paragraph ph = new Paragraph();    
    	    	   Font f  = new Font();    
    	    	   Paragraph p = new Paragraph("按病种付费申请退出表",   
    	    			   	new Font(Font.NORMAL, 18, Font.BOLD, new Color(0, 0, 0)) );    
    	    	    p.setAlignment(1);    
    	    	    document.add(p);    
    	    	    ph.setFont(f); 
    	    	    Table table = new Table(4);  
    	    	    table.setBorderColor(Color.BLACK);    
    	    	    table.setPadding(0);    
    	    	    table.setSpacing(0); 
    	    	    table.setWidth(100);
    	    	
    	    	for(Map<String, Object> col:listclo){
        	    	Paragraph fieldname = new Paragraph(col.get("FIELDNAME") == null ? null:col.get("FIELDNAME").toString());
        	    	Paragraph associationname = new Paragraph(data.get(col.get("ASSOCIATIONNAME")) == null ? null:data.get(col.get("ASSOCIATIONNAME")).toString());
        	    	fieldname.setAlignment(1);//设置段落居中，其中1为居中对齐，2为右对齐，3为左对齐
        	    	fieldname.setFont(f3);
        	        table.addCell(fieldname);
        	        associationname.setAlignment(3);//设置段落居中，其中1为居中对齐，2为右对齐，3为左对齐
        	        associationname.setFont(f5);
        	    	table.addCell(associationname);
        	    }
        	    if(listclo.size() % 2 != 0){
        	    	 // 设置垂直居中 ?
        	        //cell.setVerticalAlignment(1);
        	    	 // 设置水平居中 ?
        	        // cell.setHorizontalAlignment(1);
        	    	Cell cell = new Cell();
            	    cell.setRowspan(1);//当前单元格占1行,纵向跨度   
            	    cell.setColspan(2);
            	    table.addCell(cell);
        	    }
        	    Paragraph reason = new Paragraph("申请退出理由:");
        	    reason.setFont(f3);
        	    Cell cell = new Cell(reason);
        	    cell.setRowspan(1);//当前单元格占1行,纵向跨度   
        	    cell.setColspan(4); 
        	    cell.setBorderWidthBottom(0l);//设置边框宽度
        	    cell.setBorderWidthRight(10l);
        	    cell.setBorderWidthLeft(10l);
        	    table.addCell(cell);
        	    
        	    Cell cell0 = new Cell();
        	    Paragraph associationname = new Paragraph(data.get("退出原因") == null ? null:"    "+data.get("退出原因").toString());
        	    associationname.setAlignment(3);
        	    associationname.setFont(f5);
        	    cell0.addElement(associationname);
        	    cell0.setRowspan(1);//当前单元格占1行,纵向跨度   
        	    cell0.setColspan(4); 
        	    //cell1.setBorderColor(new Color(255,0,0));//设置边框颜色
        	    cell0.setBorderWidth(1f);
        	    cell0.setBorderWidthTop(0);
        	    cell0.setBorderWidthRight(10l);
        	    cell0.setBorderWidthLeft(10l);
        	    cell0.setBorderWidthBottom(10l);
        	    table.addCell(cell0);
        	   
        	    Cell cell1 = new Cell();
        	    Paragraph fieldnameys = new Paragraph("经治医师（签名）：");
        	    fieldnameys.setAlignment(3);
        	    fieldnameys.setFont(f3);
        	    cell1.addElement(fieldnameys);
        	    cell1.setRowspan(1);//当前单元格占1行,纵向跨度   
        	    cell1.setColspan(2); 
        	    cell1.setBorderWidth(0);//设置边框宽度
        	    table.addCell(cell1);
        	    
        	    Cell cell2 = new Cell();
        	    Paragraph fieldnamesj = new Paragraph("年    月    日 ");
        	    fieldnamesj.setAlignment(2);
        	    fieldnamesj.setFont(f3);
        	    cell2.addElement(fieldnamesj);
        	    cell2.setRowspan(1);//当前单元格占1行,纵向跨度   
        	    cell2.setColspan(2); 
        	    cell2.setBorderWidth(0);
        	    table.addCell(cell2);
        	    
        	    document.add(table);
        	    if(i != size - 1){
        	    	document.newPage();
        	    }
    	    }
    	    rtfWriter2.flush();
    	    document.close();   
    	   } catch (FileNotFoundException e) {    
    	    e.printStackTrace();    
    	   } catch (DocumentException e) {    
    	    e.printStackTrace();    
    	   } catch (IOException e) {    
    	    e.printStackTrace();    
    	   } finally {
    		   if(rtfWriter2 != null){
    			  rtfWriter2.close();
    		   }
		}
    	    
    }
    
    /**
     * 退出医保详细下载
     * 备份 
     * 日期	放在表格里面
     * @param type
     * @param startdate
     * @param enddate
     * @param request
     * @param response
     * @throws IOException
     */
    public void downloadtuichuyibaokfjsblxxBAK(String type, String startdate, String enddate,HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map map = new HashMap<>();
		map.put("type", type);
		map.put("startdate", startdate);
		map.put("enddate", enddate);
	  
	   request.setCharacterEncoding("utf-8");  
	   response.setContentType("application/x-download");  
       String fileName = URLEncoder.encode("report.doc", "UTF-8");  
       		response.addHeader("Content-Disposition", "attachment;filename="  
       + fileName);  

	    	RtfWriter2 rtfWriter2 = null;
	    	List<Map<String, Object>> listclo = service.findApplicationtemplate();
	        List<Map<String, Object>> listdata = service.findout_medicare(map);
	   try {  
		// 创建word文档,并设置纸张的大小
    	Document document = new Document(PageSize.A4); 
  	    ServletOutputStream out = response.getOutputStream();
		rtfWriter2= RtfWriter2.getInstance(document,out);
	    document.open();    
	   
	    // 表格的主体    
	  /* cell = new Cell("Example cell 2");
	    cell.setRowspan(2);//当前单元格占两行,纵向跨度   
	    table.addCell(cell);*/    
	    //宋体 STSong-Light
	    
	    Font f3  = new Font(BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED) , 13, Font.BOLD, new Color(0, 0, 0));//字段名样式
	    Font f5  = new Font(BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED) , 13, Font.NORMAL, new Color(0, 0, 0));//内容样式
	    //f3.setSize(13);//设置字体大小
	    int size = listdata.size();
	    for(int i=0;i<size;i++){
	    	Map<String, Object> data = listdata.get(i);
	    	//设置头 
	    	   Paragraph ph = new Paragraph();    
	    	   Font f  = new Font();    
	    	   Paragraph p = new Paragraph("按病种付费申请退出表",   
	    			   	new Font(Font.NORMAL, 18, Font.BOLD, new Color(0, 0, 0)) );    
	    	    p.setAlignment(1);    
	    	    document.add(p);    
	    	    ph.setFont(f); 
	    	    Table table = new Table(4);  
	    	    table.setBorderColor(Color.BLACK);    
	    	    table.setPadding(0);    
	    	    table.setSpacing(0); 
	    	    table.setWidth(100);
	    	
	    	for(Map<String, Object> col:listclo){
    	    	Paragraph fieldname = new Paragraph(col.get("FIELDNAME") == null ? null:col.get("FIELDNAME").toString());
    	    	Paragraph associationname = new Paragraph(data.get(col.get("ASSOCIATIONNAME")) == null ? null:data.get(col.get("ASSOCIATIONNAME")).toString());
    	    	fieldname.setAlignment(1);//设置段落居中，其中1为居中对齐，2为右对齐，3为左对齐
    	    	fieldname.setFont(f3);
    	        table.addCell(fieldname);
    	        associationname.setAlignment(1);//设置段落居中，其中1为居中对齐，2为右对齐，3为左对齐
    	        associationname.setFont(f5);
    	    	table.addCell(associationname);
    	    }
    	    if(listclo.size() % 2 != 0){
    	    	Cell cell = new Cell();
        	    cell.setRowspan(1);//当前单元格占1行,纵向跨度   
        	    cell.setColspan(2);
        	    table.addCell(cell);
    	    }
    	    Paragraph reason = new Paragraph("申请退出理由:");
    	    reason.setFont(f3);
    	    Cell cell = new Cell(reason);
    	    cell.setRowspan(1);//当前单元格占1行,纵向跨度   
    	    cell.setColspan(4); 
    	    cell.setBorderWidthBottom(0l);//设置边框宽度
    	    cell.setBorderWidthRight(10l);
    	    cell.setBorderWidthLeft(10l);
    	    table.addCell(cell);
    	    
    	    Cell cell0 = new Cell();
    	    Paragraph associationname = new Paragraph(data.get("退出原因") == null ? null:"    "+data.get("退出原因").toString());
    	    associationname.setAlignment(3);
    	    associationname.setFont(f5);
    	    cell0.addElement(associationname);
    	    cell0.setRowspan(1);//当前单元格占1行,纵向跨度   
    	    cell0.setColspan(4); 
    	    //cell1.setBorderColor(new Color(255,0,0));//设置边框颜色
    	    cell0.setBorderWidth(1f);
    	    cell0.setBorderWidthTop(0);
    	    cell0.setBorderWidthRight(10l);
    	    cell0.setBorderWidthLeft(10l);
    	    //cell0.setBorderWidthBottom(0);
    	    table.addCell(cell0);
    	    
    	    Cell cell1 = new Cell();
    	    Paragraph fieldnameys = new Paragraph("\n经治医师（签名）：\t\t\t");
    	    fieldnameys.setAlignment(2);
    	    fieldnameys.setFont(f3);
    	    cell1.addElement(fieldnameys);
    	    cell1.setRowspan(1);//当前单元格占1行,纵向跨度   
    	    cell1.setColspan(4); 
    	    cell1.setBorderWidth(1f);
    	    cell1.setBorderWidthTop(0);
    	    cell1.setBorderWidthRight(10l);
    	    cell1.setBorderWidthLeft(10l);
    	    cell1.setBorderWidthBottom(0);
    	    table.addCell(cell1);
    	    Cell cell2 = new Cell();
    	    Paragraph fieldnamesj = new Paragraph("年    月    日 \t");
    	    fieldnamesj.setAlignment(2);
    	    fieldnamesj.setFont(f3);
    	    cell1.addElement(fieldnamesj);
    	    cell2.setRowspan(1);//当前单元格占1行,纵向跨度   
    	    cell2.setColspan(4); 
    	    cell2.setBorderWidth(1f);
    	    cell2.setBorderWidthBottom(10l);
    	    cell2.setBorderWidthRight(10l);
    	    cell2.setBorderWidthLeft(10l);
    	    table.addCell(cell2);
    	    document.add(table);
    	    if(i != size - 1){
    	    	document.newPage();
    	    }
	    }
	    rtfWriter2.flush();
	    document.close();   
	   } catch (FileNotFoundException e) {    
	    e.printStackTrace();    
	   } catch (DocumentException e) {    
	    e.printStackTrace();    
	   } catch (IOException e) {    
	    e.printStackTrace();    
	   } finally {
		   if(rtfWriter2 != null){
			  rtfWriter2.close();
		   }
	}
	    

    }
    
    /***
     * 查询病种支付标准
     * 
     */
    @ApiOperation(value = "findZhiFuBZ", notes = "findFeiKongFeiZhuZhenDuan")
    @RequestMapping("findZhiFuBZ")
    public Object findZhiFuBZ(@RequestBody Map<String, Object> map){
        return success(service.findZhiFuBZ(map));
    }
    
    /***
     * 保存非控费调整后的信息
     * @param map
     * @return
     */
    @ApiOperation(value = "saveFeiKongFeiZhuZhenDuanTZ", notes = "saveFeiKongFeiZhuZhenDuanTZ")
    @RequestMapping("saveFeiKongFeiZhuZhenDuanTZ")
    public Object saveFeiKongFeiZhuZhenDuanTZ(@RequestBody Map<String, Object> map){
    	service.saveFeiKongFeiTZ(map);
        return success();
    }
    
    
    /***
     * 非控费调整标题
     * @param map
     * @return
     */
    @ApiOperation(value = "getFeiKongFeiTitle", notes = "getFeiKongFeiTitle")
    @RequestMapping("getFeiKongFeiTitle")
    public Object getFeiKongFeiTitle(@RequestBody Map<String, Object> map){
        return success(service.getFeiKongFeiTitle(map));
    }

    
    /***
     * 项目结算扣款病例
     * @param map
     * @return
     */
    @ApiOperation(value = "getXiangMuJieSuanKKBL", notes = "getXiangMuJieSuanKKBL")
    @RequestMapping("getXiangMuJieSuanKKBL")
    public Object getXiangMuJieSuanKKBL(@RequestBody Map<String, Object> map){
    	PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
    	map.put("type", map.get("report"));
        return success(new PageInfo<>(service.getXiangMuJieSuanKKBL(map)),service.findcolType(map));
    }
    
    /***
     * 项目结算扣款病例下载
     * 实际下载的是展开后的数据
     * @param map
     * @return
     */
    @ApiOperation(value = "downloadXiangMuJieSuanKKBL", notes = "downloadXiangMuJieSuanKKBL")
    @GetMapping("downloadXiangMuJieSuanKKBL")
    public void downloadXiangMuJieSuanKKBL(String type, String importtime, HttpServletRequest request, HttpServletResponse response) throws IOException{
    	Map map = new HashMap<>();
        map.put("type", type);
        map.put("report", type);
        map.put("importtime", importtime);
    	List<ExcelExportEntity> entityList = new ArrayList<ExcelExportEntity>();
    	List<Map<String, Object>> cols = service.findcolType(map);
    	List<Map<String, Object>> list = service.downloadXiangMuJieSuanKKBL(map);
        for (Map<String, Object> col : cols) {
        	ExcelExportEntity excelExportEntity = new ExcelExportEntity(col.get("COLUMN_NAME").toString(), col.get("COLUMN_NAME").toString());
        	if("序号".equals(col.get("COLUMN_NAME").toString())){
        		//因列名为"序号"时下载出错，故在列名为"序号"的名称后添加 空格" ".
        		excelExportEntity = new ExcelExportEntity(col.get("COLUMN_NAME").toString() + " ", col.get("COLUMN_NAME").toString());
            }
            entityList.add(excelExportEntity);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), entityList, list);
        String codedFileName = "控费病种病例项目结算扣款";
        if (workbook instanceof HSSFWorkbook) {
            codedFileName = codedFileName + ".xls";
        } else {
            codedFileName = codedFileName + ".xlsx";
        }

        if (this.isIE(request)) {
            codedFileName = URLEncoder.encode(codedFileName, "UTF8");
        } else {
            codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }
    
    /***
     * 项目结算扣款明细
     * @param map
     * @return
     */
    @ApiOperation(value = "getXiangMuJieSuanKKMX", notes = "getXiangMuJieSuanKKXX")
    @RequestMapping("getXiangMuJieSuanKKMX")
    public Object getXiangMuJieSuanKKXX(@RequestBody Map<String, Object> map){
    	if(map.get("pageNum") != null && map.get("pageSize") != null){
    		map.put("type", map.get("report"));
    		PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
    		return success(new PageInfo<>(service.getXiangMuJieSuanKKMX(map)),service.findcolType(map));
    	}
    	map.put("type", map.get("report"));
        return success(new PageInfo<>(service.getXiangMuJieSuanKKMX(map)),service.findcolType(map));
    }
    
    
    /***
     * 退出医保控费结算病例 申请单模板 列查询
     * @param map
     * @return
     */
    @ApiOperation(value = "findApplicationtemplatecol", notes = "findApplicationtemplatecol")
    @RequestMapping("findApplicationtemplatecol")
    public Object findApplicationtemplatecol(){
        return success(service.findcolType("out_medicare"));
    }
    
    
    /***
     * 退出医保控费结算病例 申请单模板 添加
     * @param map
     * @return
     */
    @ApiOperation(value = "addApplicationtemplate", notes = "addApplicationtemplate")
    @RequestMapping("addApplicationtemplate")
    public Object addApplicationtemplate(@RequestBody ArrayList<Map<String, Object>> list){
    	service.addApplicationtemplate(list);
        return success();
    }
    
    /***
     * 退出医保控费结算病例 申请单模板 查询
     * @param map
     * @return
     */
    @ApiOperation(value = "findApplicationtemplate", notes = "findApplicationtemplate")
    @RequestMapping("findApplicationtemplate")
    public Object findApplicationtemplate(){
        return success(service.findApplicationtemplate());
    }


    /**
     * 不结算病例导出
     * @param request
     * @param startdate
     * @param enddate
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "exportNoSettledPatient", notes = "exportNoSettledPatient")
    @GetMapping(value = "exportNoSettledPatient")
    public void exportNoSettledPatient(HttpServletRequest request, String startdate, String enddate, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> cols = service.findcolType("constmonitor1");
        List<ExcelExportEntity> entityList = new ArrayList<ExcelExportEntity>();
        Map<String, Object> map = new HashMap<>();
        map.put("startdate", startdate);
        map.put("enddate", enddate);
        List<Map<String, Object>> list = service.findNoSettledPatient(map);
        for (Map<String, Object> col : cols) {
            entityList.add(new ExcelExportEntity(col.get("COLUMN_NAME").toString(), col.get("COLUMN_NAME").toString()));
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), entityList, list);
        String codedFileName = "不结算病例";
        if (workbook instanceof HSSFWorkbook) {
            codedFileName = codedFileName + ".xls";
        } else {
            codedFileName = codedFileName + ".xlsx";
        }

        if (this.isIE(request)) {
            codedFileName = URLEncoder.encode(codedFileName, "UTF8");
        } else {
            codedFileName = new String(codedFileName.getBytes("UTF-8"), "ISO-8859-1");
        }

        response.setHeader("content-disposition", "attachment;filename=" + codedFileName);
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }

    /**
     * 不结算病例
     * @param map
     * @return
     */
    @ApiOperation(value = "findNoSettledPatient", notes = "findNoSettledPatient")
    @RequestMapping("findNoSettledPatient")
    public Object findNoSettledPatient(@RequestBody Map<String, Object> map){
        PageHelper.startPage(Integer.parseInt(map.get("pageNum").toString()), Integer.parseInt(map.get("pageSize").toString()));
        return success(new PageInfo<>(service.findNoSettledPatient(map)), service.findcolType("constmonitor3"));
    }

    /**
     * 不结算病例Title
     * @param map
     * @return
     */
    @ApiOperation(value = "findNoSettledPatientTitle", notes = "findNoSettledPatientTitle")
    @RequestMapping("findNoSettledPatientTitle")
    public Object findNoSettledPatientTitle(@RequestBody Map<String, Object> map){
        return success(service.findNoSettledPatientTitle(map));
    }
}