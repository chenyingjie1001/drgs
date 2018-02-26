package com.firesoon.drgs.mapper.disease;

import org.apache.ibatis.annotations.Mapper;

import java.util.*;

/**
 * @author create by yingjie.chen on 2017/12/8.
 * @version 2017/12/8 14:10
 */

@Mapper
public interface MedicareMapper {


    Map<String, Object> getTitle(Map<String, Object> map);

    Map<String, Object> getTitleMsg(Map<String, Object> map);

    Map<String, Object> getTitleDate();

    List<Map<String, Object>> getMedicares(Map<String, Object> map);


    List<Map<String, Object>> getDetail(Map<String, Object> map);

    List<Map<String, Object>> getDetailMsg(Map<String, Object> map);

    List<Map<String, Object>> costMonitoring(Map<String, Object> map);

    List<Map<String, Object>> findcolType(String view_name);

    List<Map<String, Object>> findMedicareDateReport(Map<String, Object> map);

    List<Map<String, Object>> findDateReportChart(Map<String, Object> map);
    
    List<Map<String, Object>> findPerHospitalReportChart(Map<String, Object> map);

    List<Map<String, Object>> findPerHospitalReportPieChart(Map<String, Object> map);

    List<Map<String, Object>> findCostHospitalReportChart(Map<String, Object> map);

    
    /**
     * 
     * @param map
     * @return
     */
    List<Map<String, Object>> findCostHospitalReportPieChart(Map<String, Object> map);

    /**
     * 医保结算清单
     * @param map
     * @return
     */
    List<Map<String, Object>> findMedicareSettlementList(Map<String, Object> map);


    List<Map<String, Object>> findCostHospitalReportForm(Map<String, Object> map);
    
    List<Map<String, Object>> findCostHospitalReportTotalCostForm(Map<String, Object> map);
    /**
     * 退出医保
     * @param map
     */
    void insertOutMedicare(Map<String, Object> map);

    
    List<Map<String, Object>> findFeiKongFeiTZ(Map<String, Object> map);
    
    List<Map<String, Object>> findZhiFuBZ(Map<String, Object> map);
    
    void saveFeiKongFeiTZ(Map<String, Object> map);
    
    Map<String, Object> getFeiKongFeiTitle1(Map<String, Object> map);
    
    Map<String, Object> getFeiKongFeiTitle2(Map<String, Object> map);
    /**
     * 不结算病例
     * @param map
     * @return
     */
    List<Map<String, Object>> findNoSettledPatient(Map<String, Object> map);

    /**
     * 不结算病例title
     * @param map
     * @return
     */
    Map<String, Object> findNoSettledPatientTitle(Map<String, Object> map);
    
    List<Map<String, Object>> downloadKongFeiBLS(Map<String, Object> map);
    
    List<Map<String, Object>> downloadKongFeiZFY(Map<String, Object> map);
    
    List<Map<String, Object>> getXiangMuJieSuanKKBL(Map<String, Object> map);
    
    List<Map<String, Object>> getProjectsettlement(Map<String, Object> map);
    
    List<Map<String, Object>> downloadXiangMuJieSuanKKBL(Map<String, Object> map);
    
    /***
     * 添加下载模板
     */
    void addApplicationtemplate(Map<String, Object> map);
    /**
     * 删除下载模板
     */
    void deleteApplicationtemplate();
    /**
     * 查询模板
     */
    List<Map<String, Object>> findApplicationtemplate();
    
    
    List<Map<String, Object>> tuichuyibaokfjsbl(Map<String, Object> map);
    
    
    List<Map<String, Object>> tuichuyibaokfjsblxx(Map<String, Object> map);
    
    /**
     * 查询退出医保表
     * @param map
     * @return
     */
    List<Map<String, Object>>  findout_medicare(Map<String, Object> map);
    
    /**
     * 取消退出医保
     * @param map
     */
    void cancelOut_medicare(Map<String, Object> map);
}
