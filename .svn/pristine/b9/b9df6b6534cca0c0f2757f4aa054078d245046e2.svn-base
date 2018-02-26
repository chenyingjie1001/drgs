package com.firesoon.drgs.Utils;

import java.util.Map;

/**
 * @author create by yingjie.chen on 2017/12/21.
 * @version 2017/12/21 10:24
 */
public class MapUtil {

    public static String map2String(Map map){
        StringBuffer sb = new StringBuffer();
        for(Object key : map.keySet()){
            Object value = map.get(key);
            sb.append(key+":"+value+"\t ");
        }
        return sb.toString();
    }

    /**
     * 在院控费包含现金
     * @param map
     */
    public static void setInHospital(Map map){
        map.put("isouttype", "入院日期");
        map.put("chargetype", "1");
        map.put("notcontaincash", null);
        map.put("outhospitalflag", "在院");
    }

    /**
     * 出院控费不包含现金
     * @param map
     */
    public static void setOutHospitalNoCash(Map map){
        map.put("isouttype", "出院日期");
        map.put("chargetype", "1");
        map.put("notcontaincash", "医保");
        map.put("outhospitalflag", "出院");
    }

    /**
     * 在院控费不包含现金
     * @param map
     */
    public static void setInHospitalNoCash(Map map){
        map.put("isouttype", "入院日期");
        map.put("chargetype", "1");
        map.put("notcontaincash", "医保");
        map.put("outhospitalflag", "在院");
    }

    /**
     * 出院控费包含现金
     * @param map
     */
    public static void setOutHospital(Map map){
        map.put("isouttype", "出院日期");
        map.put("chargetype", "1");
        map.put("notcontaincash", null);
        map.put("outhospitalflag", "出院");
    }
    
    /***
     * 出院控费病例数占比 饼详情
     * @param map
     */
    public static void setOutHospitalBLSXQ(Map map){
        map.put("isouttype", "出院日期");
        //map.put("chargetype", "1");
        map.put("notcontaincash", "Y");
        map.put("outhospitalflag", "出院");
    }
    
    /***
     * 在院控费病例数占比 饼详情
     * @param map
     */
    public static void setInHospitalBLSXQ(Map map){
        map.put("isouttype", "入院日期");
        //map.put("chargetype", "1");
        //map.put("notcontaincash", "Y");
        map.put("outhospitalflag", "在院");
    }
    
    /***
     * 非控费主诊断调整预测
     * @param map
     */
    public static void setFeiKongFeiZZD(Map map){
    	map.put("notcontaincash", "Y");
        map.put("chargetype", "3");
        map.put("outhospitalflag", "出院");
        map.put("isouttype", "出院日期");
    }
    /***
     * 非控费主手术调整预测
     * @param map
     */
    public static void setFeiKongFeiZSH(Map map){
        map.put("chargetype", "2");
        map.put("notcontaincash", "Y");
        map.put("outhospitalflag", "出院");
        map.put("isouttype", "出院日期");
    }
    
    /***
     * 非控费病种调整预测
     * @param map
     */
    public static void setFeiKongFeiBZTZ(Map map){
        map.put("chargetype", "4");
        map.put("notcontaincash", "Y");
        map.put("outhospitalflag", "出院");
        map.put("isouttype", "出院日期");
    }
    
    /***
     * 出院控费     病例数     用百分比(饼图，折线)
     * @param map
     */
    public static void setChuYuanKongFeiBaiFenBi(Map map){
        map.put("notcontaincash", "Y");
        map.put("outhospitalflag", "出院");
        map.put("isouttype", "出院日期");
    }
    
    /***
     * 出院控费   医疗费   用百分比(饼图，折线)
     * @param map
     */
    public static void setChuYuanKongFeiBaiFenBi_YLF(Map map){
        map.put("notcontaincash", "Y");
        map.put("outhospitalflag", "出院");
        map.put("isouttype", "出院日期");
    }
    
    /***
     * 出院控费  病例 /医疗费用    百分比(表格)
     * @param map
     */
    public static void setChuYuanKongFeiBaiFenBiBG(Map map){
        map.put("notcontaincash", "Y");
        map.put("outhospitalflag", "出院");
        map.put("isouttype", "出院日期");
    }
    
    /***
     * 在院控费  病例/医疗费用   百分比(表格)
     * @param map
     */
    public static void setZaiYuanKongFeiBaiFenBiBG(Map map){
        map.put("outhospitalflag", "在院");
        map.put("isouttype", "入院日期");
    }
    
    /***
     * 在院控费    病例 数 百分比(饼图，折线)
     * @param map
     */
    public static void setZaiYuanKongFeiBaiFenBi(Map map){
        map.put("outhospitalflag", "在院");
        map.put("isouttype", "入院日期");
    }
    
    
    /***
     * 在院控费    医疗费 百分比(饼图，折线)
     * @param map
     */
    public static void setZaiYuanKongFeiBaiFenBi_YLF(Map map){
        map.put("outhospitalflag", "在院");
        map.put("isouttype", "入院日期");
    }

    /**
     * 不结算清单
     * @param map
     */
    public static void setNosettledList(Map map){
        map.put("chargetype", "1");
        map.put("notcontaincash", "Y");
    }
    
    /**
     * 项目结算扣款病例
     * @param map
     */
    public static void setXiangMuJieSuanKKBL(Map map){
    	map.put("outhospitalflag", "出院");
        map.put("notcontaincash", "Y");
    }
    
    /**
     * 日报柱形图
     * @param map
     */
    public static void setDateReportChart(Map map){
        //map.put("outhospitalflag", "出院");
        //map.put("isouttype", "出院日期");
        map.put("chargetype", "1");
        map.put("notcontaincash", "Y");
    }
}
