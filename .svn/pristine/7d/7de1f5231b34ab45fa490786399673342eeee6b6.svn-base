package com.firesoon.drgs.services.disease.imp;

import com.firesoon.drgs.Utils.ChoseColumn;
import com.firesoon.drgs.Utils.MapUtil;
import com.firesoon.drgs.Utils.UserUtils;
import com.firesoon.drgs.dto.user.User;
import com.firesoon.drgs.mapper.disease.MedicareMapper;
import com.firesoon.drgs.services.disease.MedicareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author create by yingjie.chen on 2017/12/8.
 * @version 2017/12/8 14:09
 */
@Service
public class MedicareServiceImpl implements MedicareService {
    @Autowired
    private MedicareMapper mapper;

    /**
     * //净盈亏
     * //总病例
     * //总病种/手术数类型
     * //最大亏损病种/手术
     * //最大盈利病种/手术
     * //亏损病例数
     * //亏金额
     * //盈利病例数
     * //盈利额
     * //实付病例数
     *
     * @return
     */
    @Override
    public Map<String, Object> getTitle(Map<String, Object> m) {
        //全部都是出院日期
        MapUtil.setOutHospitalNoCash(m);
        Map<String, Object> map = mapper.getTitle(m);
        map.putAll(mapper.getTitleMsg(m));
//        map.putAll(mapper.getTitleDate());
        return map;
    }

    @Override
    public List<Map<String, Object>> getMedicares(Map<String, Object> map) {
        String orderBy = map.get("orderby") == null ? " 疾病名称 " : "\"" + map.get("orderby") + "\"";
        String desc = "1".equals(map.get("desc") + "") ? " desc " : " ";
        map.put("order", orderBy + desc);
        MapUtil.setOutHospitalNoCash(map);
        return mapper.getMedicares(map);
    }

    @Override
    public List<Map<String, Object>> getDetail(Map<String, Object> map) {
        // 20171209添加
        String orderBy = map.get("orderby") == null ? "支付标准 " : map.get("orderby") + "";
        String desc = "1".equals(map.get("desc") + "") ? " desc " : " ";
        map.put("order", orderBy + desc);
        MapUtil.setOutHospitalNoCash(map);
        return mapper.getDetail(map);
    }


    @Override
    public List<Map<String, Object>> getDetailMsg(Map<String, Object> map) {
        String orderBy = map.get("orderby") == null ? " 最后费用日期 " : map.get("orderby") + "";
        String desc = "1".equals(map.get("desc") + "") ? " desc " : " ";
        if (map.get("desc") == null) {
            desc = " desc ";
        }
        map.put("order", orderBy + desc);
        if ("医保类别".equals(map.get("title").toString())) {
            map.put("view_name", "detailmsg1");

        } else if ("参保类型".equals(map.get("title").toString())) {
            map.put("view_name", "detailmsg2");
        } else if ("医生".equals(map.get("title").toString())) {
            map.put("view_name", "detailmsg3");
        } else {
            map.put("view_name", "detailmsg4");
        }
        MapUtil.setOutHospitalNoCash(map);
        return mapper.getDetailMsg(map);
    }

    @Override
    public List<Map<String, Object>> costMonitoring(Map<String, Object> map) {
        String view_name = "constmonitor1";
        String type = map.get("type") != null ? map.get("type").toString() : "";
        if ("monitor".equals(type)) {
            view_name = "constmonitor2";
            MapUtil.setInHospital(map);
        } else {
            /*if("cykfblsxq".equals(map.get("report") + "")){
                MapUtil.setOutHospitalBLSXQ(map);
            }else{
            	MapUtil.setOutHospitalNoCash(map);
            }*/
            if ("cykfblszb".equals(map.get("report") + "")) {
                MapUtil.setOutHospitalBLSXQ(map);
            } else if ("cykfblylzfyzb".equals(map.get("report") + "")) {
                MapUtil.setOutHospitalBLSXQ(map);
            } else if ("zykfblszb".equals(map.get("report") + "")) {
                MapUtil.setInHospitalBLSXQ(map);
            } else if ("zykfblylzfyzb".equals(map.get("report") + "")) {
                MapUtil.setInHospitalBLSXQ(map);
            } else {
                MapUtil.setOutHospitalNoCash(map);
//        		throw new RuntimeException("参数有误！");
            }
        }
        String orderBy = map.get("orderby") == null ? "最后费用日期 " : map.get("orderby") + "";
        String desc = "1".equals(map.get("desc") + "") ? " desc " : " ";
        if (map.get("desc") == null) {
            desc = " desc ";
        }
//		Map<String, Object> searchMap = new HashMap<String, Object>();
//		searchMap.put("title", map.get("title") == null ? null : map.get("title") + "");
//		searchMap.put("order", orderBy + desc);
//		searchMap.put("filter", map.get("filter"));
//		searchMap.put("view_name", view_name);
        map.put("order", orderBy + desc);
        System.out.println("在线费用监控：" + map);
        List<Map<String, Object>> listmap = mapper.costMonitoring(map);
        return listmap;
    }

    @Override
    public List<Map<String, Object>> findcolType(Map<String, Object> map) {
        String view_name = "constmonitor1";
        String type = map.get("type") != null ? map.get("type").toString() : "";
        if ("monitor".equals(type)) {
            view_name = "constmonitor2";
        } else if ("feikongfeiTZ".equals(type)) {
            view_name = "feikongfeitz";
        }else if("fkfzzdtzyc".equals(type)){
        	view_name = "feikongfeizzdtz";
        }else if("fkfzshtzyc".equals(type)){
        	view_name = "feikongfeizsstz";
        }else if("fkfbztzyc".equals(type)){
        	view_name = "feikongfeibztz";
        }else if("xmjskkbl".equals(type)){
        	view_name = "xiangmujsbl";
        }else if("xmjskkblxx".equals(type)){
        	view_name = "xiangmujsblxx";
        }else if("xmjskkmx".equals(type)){
        	view_name = "xiangmujskkmx";
        }
        
        return mapper.findcolType(view_name);
    }

    @Override
    public List<Map<String, Object>> findcolType(String view_name) {
        return mapper.findcolType(view_name);
    }

    @Override
    public List<Map<String, Object>> findColForDetail(Map<String, Object> map) {
        String view_name = "";
        if ("医保类别".equals(map.get("title").toString())) {
            view_name = "detailmsg1";
        } else if ("参保类型".equals(map.get("title").toString())) {
            view_name = "detailmsg2";
        } else if ("医生".equals(map.get("title").toString())) {
            view_name = "detailmsg3";
        } else if ("科室".equals(map.get("title").toString())) {
            view_name = "detailmsg4";
        } else {
            view_name = "detailmsg5";
        }
        return mapper.findcolType(view_name);
    }

    @Override
    public List<Map<String, Object>> findColForMedicare(Map<String, Object> map) {
        String view_name = "medicare";
        return mapper.findcolType(view_name);
    }

    /**
     * COLUMN_NAME,DATA_TYPE
     *
     * @param map
     * @return
     */
    @Override
    public List<Map<String, Object>> findColForDetailGroup(Map<String, Object> map) {
        //这块先暂定
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        String sql = "";
        if ("医保类别".equals(map.get("title").toString())) {
            sql = ChoseColumn.detail1;
        } else if ("参保类型".equals(map.get("title").toString())) {
            sql = ChoseColumn.detail2;
        } else if ("主治医生".equals(map.get("title").toString())) {
            sql = ChoseColumn.detail3;
        } else if ("科室".equals(map.get("title").toString())) {
            sql = ChoseColumn.detail4;
        } else {
            sql = ChoseColumn.detail5;
        }
        for (String key : sql.split(",")) {
            Map<String, Object> m = new HashMap<>();
            m.put("COLUMN_NAME", key);
            if ("医保类别".equals(key)) {
                m.put("DATA_TYPE", "VARCHAR2");
            } else
                m.put("DATA_TYPE", "NUMBER");
            result.add(m);
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> findMedicareDateReport(Map<String, Object> map) {
        String orderBy = map.get("orderby") == null ? "病例数" : map.get("orderby") + "";
        String desc = "0".equals(map.get("desc") + "") ? " " : " desc ";
        map.put("order", orderBy + desc);
        MapUtil.setOutHospitalNoCash(map);
        return mapper.findMedicareDateReport(map);
    }

    @Override
    public List<Map<String, Object>> findDateReportChart(Map<String, Object> map) {
        MapUtil.setDateReportChart(map);
        return mapper.findDateReportChart(map);
    }

    @Override
    public List<Map<String, Object>> findPerHospitalReportChart(Map<String, Object> map) {
        if ("cykfblszb".equals(map.get("report") + "")) {
            MapUtil.setChuYuanKongFeiBaiFenBi(map);
        } else if ("cykfblylzfyzb".equals(map.get("report") + "")) {
            MapUtil.setChuYuanKongFeiBaiFenBi(map);
        } else if ("zykfblszb".equals(map.get("report") + "")) {
            MapUtil.setZaiYuanKongFeiBaiFenBi(map);
        } else if ("zykfblylzfyzb".equals(map.get("report") + "")) {
            MapUtil.setZaiYuanKongFeiBaiFenBi(map);
        } else {
            throw new RuntimeException("参数有误！");
        }
        return mapper.findPerHospitalReportChart(map);
    }

    @Override
    public List<Map<String, Object>> findPerHospitalReportPieChart(Map<String, Object> map) {
    	Map<String, Object> header = new HashMap<String, Object>();
        if ("cykfblszb".equals(map.get("report") + "")) {
            MapUtil.setChuYuanKongFeiBaiFenBi(map);
            /*Map<String, Object> pie = new HashMap<String, Object>();
            Map<String, Object> line = new HashMap<String, Object>();
            pie.put("text", "病种类别占比分布");
            line.put("text", "控费病种比例走势");
            line.put("xaxis", "日期");
            line.put("yaxis", "控费病种比例走势");
            header.put("pie", pie);
            header.put("line", line);*/
        } else if ("cykfblylzfyzb".equals(map.get("report") + "")) {
            MapUtil.setChuYuanKongFeiBaiFenBi(map);
        } else if ("zykfblszb".equals(map.get("report") + "")) {
            MapUtil.setZaiYuanKongFeiBaiFenBi(map);
        } else if ("zykfblylzfyzb".equals(map.get("report") + "")) {
            MapUtil.setZaiYuanKongFeiBaiFenBi(map);
        } else {
            throw new RuntimeException("参数有误！");
        }

        List<Map<String, Object>> listmap = mapper.findPerHospitalReportPieChart(map);
        ;
        List<Map<String, Object>> retlistmap = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> m : listmap) {
            if ("非控费诊断+非控费手术".equals(m.get("ITEM") + "")) {
                Map<String, Object> retmap = new HashMap<String, Object>();
                retmap.put("ITEM", "非控费主诊断+非控费主手术");
                retmap.put("COUNT", m.get("COUNT"));
                retlistmap.add(retmap);
                break;
            }
            retlistmap.add(m);
        }
        //retlistmap.add(header);
        return retlistmap;
        //return mapper.findPerHospitalReportPieChart(map);
    }

    @Override
    public List<Map<String, Object>> findCostHospitalReportChart(Map<String, Object> map) {
        if ("cykfblszb".equals(map.get("report") + "")) {
            MapUtil.setChuYuanKongFeiBaiFenBi(map);
        } else if ("cykfblylzfyzb".equals(map.get("report") + "")) {
            MapUtil.setChuYuanKongFeiBaiFenBi(map);
        } else if ("zykfblszb".equals(map.get("report") + "")) {
            MapUtil.setZaiYuanKongFeiBaiFenBi(map);
        } else if ("zykfblylzfyzb".equals(map.get("report") + "")) {
            MapUtil.setZaiYuanKongFeiBaiFenBi(map);
        } else {
            throw new RuntimeException("参数有误！");
        }
        return mapper.findCostHospitalReportChart(map);
    }

    @Override
    public List<Map<String, Object>> findCostHospitalReportPieChart(Map<String, Object> map) {
        if ("cykfblszb".equals(map.get("report") + "")) {
            MapUtil.setChuYuanKongFeiBaiFenBi_YLF(map);
        } else if ("cykfblylzfyzb".equals(map.get("report") + "")) {
            MapUtil.setChuYuanKongFeiBaiFenBi_YLF(map);
        } else if ("zykfblszb".equals(map.get("report") + "")) {
            MapUtil.setZaiYuanKongFeiBaiFenBi_YLF(map);
        } else if ("zykfblylzfyzb".equals(map.get("report") + "")) {
            MapUtil.setZaiYuanKongFeiBaiFenBi_YLF(map);
        } else {
            throw new RuntimeException("参数有误！");
        }
        List<Map<String, Object>> listmap = mapper.findCostHospitalReportPieChart(map);
        List<Map<String, Object>> retlistmap = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> m : listmap) {
            if ("非控费诊断+非控费手术".equals(m.get("ITEM") + "")) {
                Map<String, Object> retmap = new HashMap<String, Object>();
                retmap.put("ITEM", "非控费主诊断+非控费主手术");
                retmap.put("COUNT", m.get("COUNT"));
                retlistmap.add(retmap);
                break;
            }
            retlistmap.add(m);
        }
        return retlistmap;
    }

    @Override
    public List<Map<String, Object>> findMedicareSettlementList(Map<String, Object> map) {
        MapUtil.setOutHospitalNoCash(map);
        return mapper.findMedicareSettlementList(map);
    }

    @Override
    public List<Map<String, Object>> findCostHospitalReportForm(Map<String, Object> map) {
        /*if("cykfblylzfyzb".equals(map.get("report")+"")){
            MapUtil.setChuYuanKongFeiBaiFenBiBG(map);
    	}else if("cykfblszb".equals(map.get("report")+"")){
    		MapUtil.setChuYuanKongFeiBaiFenBiBG(map);
    	}*/

        if ("cykfblszb".equals(map.get("report") + "")) {
            MapUtil.setChuYuanKongFeiBaiFenBiBG(map);
        } else if ("cykfblylzfyzb".equals(map.get("report") + "")) {
            MapUtil.setChuYuanKongFeiBaiFenBiBG(map);
        } else if ("zykfblszb".equals(map.get("report") + "")) {
            MapUtil.setZaiYuanKongFeiBaiFenBiBG(map);
        } else if ("zykfblylzfyzb".equals(map.get("report") + "")) {
            MapUtil.setZaiYuanKongFeiBaiFenBiBG(map);
        } else {
            throw new RuntimeException("参数有误！");
        }

        String orderBy = map.get("orderby") == null ? map.get("title") + "" : map.get("orderby") + "";
        String desc = "1".equals(map.get("desc") + "") ? " desc " : " ";
        map.put("order", orderBy + desc);
        return mapper.findCostHospitalReportForm(map);
    }


    @Override
    public List<Map<String, Object>> findCostHospitalReportTotalCostForm(Map<String, Object> map) {
        if ("cykfblszb".equals(map.get("report") + "")) {
            MapUtil.setChuYuanKongFeiBaiFenBiBG(map);
        } else if ("cykfblylzfyzb".equals(map.get("report") + "")) {
            MapUtil.setChuYuanKongFeiBaiFenBiBG(map);
        } else if ("zykfblszb".equals(map.get("report") + "")) {
            MapUtil.setZaiYuanKongFeiBaiFenBiBG(map);
        } else if ("zykfblylzfyzb".equals(map.get("report") + "")) {
            MapUtil.setZaiYuanKongFeiBaiFenBiBG(map);
        } else {
            throw new RuntimeException("参数有误！");
        }

        String orderBy = map.get("orderby") == null ? map.get("title") + "" : map.get("orderby") + "";
        String desc = "1".equals(map.get("desc") + "") ? " desc " : " ";
        map.put("order", orderBy + desc);
        return mapper.findCostHospitalReportTotalCostForm(map);
    }

    @Override
    public void insertOutMedicare(Map<String, Object> map) {
        if (map.get("medicare_type") != null && map.get("medicare_type").toString().equals("现金")) {
            throw new RuntimeException("医保类型为现金不能退回！");
        }
        mapper.insertOutMedicare(map);
    }

    @Override
    public List<Map<String, Object>> findFeiKongFeiTZ(Map<String, Object> map) {
        if ("fkfzzdtzyc".equals(map.get("report") + "")) {
            MapUtil.setFeiKongFeiZZD(map);
        } else if ("fkfzshtzyc".equals(map.get("report") + "")) {
            MapUtil.setFeiKongFeiZSH(map);
        } else if ("fkfbztzyc".equals(map.get("report") + "")) {
            MapUtil.setFeiKongFeiBZTZ(map);
        } else {
            throw new RuntimeException("参数异常！");
        }
        String orderBy = map.get("orderby") == null ? "最后费用日期" : map.get("orderby") + "";
        String desc = "1".equals(map.get("desc") + "") ? " desc " : " ";
        if (map.get("desc") == null) {
            desc = " desc ";
        }
        map.put("order", orderBy + desc);
        return mapper.findFeiKongFeiTZ(map);
    }

   /* @Override
    public List<Map<String, Object>> findFeiKongFeiTZ(Map<String, Object> map) {
        if ("fkfzzdyc".equals(map.get("report") + "")) {
            MapUtil.setFeiKongFeiZZD(map);
        } else if ("fkfzshyc".equals(map.get("report") + "")) {
            MapUtil.setFeiKongFeiZSH(map);
        } else {
            throw new RuntimeException("参数异常！");
        }
        String orderBy = map.get("orderby") == null ? "病人姓名" : map.get("orderby") + "";
        String desc = "1".equals(map.get("desc") + "") ? " desc " : " ";
        map.put("order", orderBy + desc);
        return mapper.findFeiKongFeiTZ(map);
    }*/

    @Override
    public List<Map<String, Object>> findZhiFuBZ(Map<String, Object> map) {
        return mapper.findZhiFuBZ(map);
    }

    @Override
    public void saveFeiKongFeiTZ(Map<String, Object> map) {
		User user = UserUtils.getUser();//获取登录用户
		map.put("user", user.getId());
		/*double yingli =Double.parseDouble(map.get("yingli")+"");
		if(yingli > 0){
			map.put("yingkuibs", 1);
		}else if(yingli < 0){
			map.put("yingkuibs", 0);
		}else{
			map.put("yingkuibs", 2);
		}*/
        mapper.saveFeiKongFeiTZ(map);
    }
    /*@Override
    public void saveFeiKongFeiTZ(Map<String, Object> map) {
        mapper.saveFeiKongFeiTZ(map);
    }*/

    @Override
    public List<Map<String, Object>> findNoSettledPatient(Map<String, Object> map) {
        MapUtil.setNosettledList(map);
        String orderBy = map.get("orderby") == null ? " 最后费用日期 " : map.get("orderby") + "";
        String desc = "1".equals(map.get("desc") + "") ? " desc " : " ";
        map.put("order", orderBy + desc);
        return mapper.findNoSettledPatient(map);
    }

    @Override
    public Map<String, Object> findNoSettledPatientTitle(Map<String, Object> map) {
        MapUtil.setNosettledList(map);
        String orderBy = map.get("orderby") == null ? " 最后费用日期 " : map.get("orderby") + "";
        String desc = "1".equals(map.get("desc") + "") ? " desc " : " ";
        map.put("order", orderBy + desc);
        return mapper.findNoSettledPatientTitle(map);
    }

    @Override
    public Map<String, Object> getFeiKongFeiTitle(Map<String, Object> map) {
        Map<String, Object> remap = new HashMap<String, Object>();
        if ("fkfzzdtzyc".equals(map.get("report") + "")) {
            MapUtil.setFeiKongFeiZZD(map);
        } else if ("fkfzshtzyc".equals(map.get("report") + "")) {
            MapUtil.setFeiKongFeiZSH(map);
        } else {
            throw new RuntimeException("参数异常！");
        }
        if (mapper.getFeiKongFeiTitle1(map) != null) {
            remap.putAll(mapper.getFeiKongFeiTitle1(map));
        } else {
            remap.put("调整病例数", 0);
            remap.put("调整后盈利", 0);
        }
        if (mapper.getFeiKongFeiTitle2(map) != null) {
            remap.putAll(mapper.getFeiKongFeiTitle2(map));
        } else {
            remap.put("总病例", 0);
        }
        return remap;
    }

	@Override
	public List<Map<String, Object>> downloadKongFeiBLS(Map<String, Object> map) {
		 if ("cykfblszb".equals(map.get("report") + "")) {
	            MapUtil.setChuYuanKongFeiBaiFenBiBG(map);
	        } else if ("cykfblylzfyzb".equals(map.get("report") + "")) {
	            MapUtil.setChuYuanKongFeiBaiFenBiBG(map);
	        } else if ("zykfblszb".equals(map.get("report") + "")) {
	            MapUtil.setZaiYuanKongFeiBaiFenBiBG(map);
	        } else if ("zykfblylzfyzb".equals(map.get("report") + "")) {
	            MapUtil.setZaiYuanKongFeiBaiFenBiBG(map);
	        } else {
	            throw new RuntimeException("参数有误！");
	        }
		return mapper.downloadKongFeiBLS(map);
	}

	@Override
	public List<Map<String, Object>> downloadKongFeiZFY(Map<String, Object> map) {
		if ("cykfblszb".equals(map.get("report") + "")) {
            MapUtil.setChuYuanKongFeiBaiFenBiBG(map);
        } else if ("cykfblylzfyzb".equals(map.get("report") + "")) {
            MapUtil.setChuYuanKongFeiBaiFenBiBG(map);
        } else if ("zykfblszb".equals(map.get("report") + "")) {
            MapUtil.setZaiYuanKongFeiBaiFenBiBG(map);
        } else if ("zykfblylzfyzb".equals(map.get("report") + "")) {
            MapUtil.setZaiYuanKongFeiBaiFenBiBG(map);
        } else {
            throw new RuntimeException("参数有误！");
        }
		return mapper.downloadKongFeiZFY(map);
	}

	@Override
	public List<Map<String, Object>> getXiangMuJieSuanKKBL(Map<String, Object> map) {
		MapUtil.setXiangMuJieSuanKKBL(map);
        String orderBy = map.get("orderby") == null ? " 病人姓名 " : map.get("orderby") + "";
        String desc = "1".equals(map.get("desc") + "") ? " desc " : " ";
        map.put("order", orderBy + desc);
		return mapper.getXiangMuJieSuanKKBL(map);
	}

	@Override
	public List<Map<String, Object>> getXiangMuJieSuanKKMX(Map<String, Object> map) {
		String orderBy = map.get("orderby") == null ? " 序号 " : map.get("orderby") + "";
        String desc = "1".equals(map.get("desc") + "") ? " desc " : " ";
        map.put("order", orderBy + desc);
		return mapper.getProjectsettlement(map);
	}

	@Override
	public List<Map<String, Object>> downloadXiangMuJieSuanKKBL(Map<String, Object> map) {
		MapUtil.setXiangMuJieSuanKKBL(map);
		return mapper.downloadXiangMuJieSuanKKBL(map);
	}

	@Override
	public void addApplicationtemplate(ArrayList<Map<String, Object>> list) {
		User user = UserUtils.getUser();//获取登录用户
		mapper.deleteApplicationtemplate();
		for(Map<String, Object> row:list){
			row.put("user", user.getLogin_name());
			mapper.addApplicationtemplate(row);
		}
	}

	@Override
	public List<Map<String, Object>> findApplicationtemplate() {
		return mapper.findApplicationtemplate();
	}

	@Override
	public List<Map<String, Object>> tuichuyibaokfjsbl(Map<String, Object> map) {
		MapUtil.setOutHospitalNoCash(map);
		return mapper.tuichuyibaokfjsbl(map);
	}

	@Override
	public List<Map<String, Object>> tuichuyibaokfjsblxx(Map<String, Object> map) {
		MapUtil.setOutHospitalNoCash(map);
		 String orderBy = map.get("orderby") == null ? "最后费用日期 " : map.get("orderby") + "";
	        String desc = "1".equals(map.get("desc") + "") ? " desc " : " ";
	        if (map.get("desc") == null) {
	            desc = " desc ";
	        }
	        map.put("order", orderBy + desc);
		return mapper.tuichuyibaokfjsblxx(map);
	}

	@Override
	public List<Map<String, Object>> findout_medicare(Map<String, Object> map) {
		MapUtil.setOutHospitalNoCash(map);
		return mapper.findout_medicare(map);
	}

	@Override
	public void cancelOut_medicare(Map<String, Object> map) {
		mapper.cancelOut_medicare(map);
	}

}
