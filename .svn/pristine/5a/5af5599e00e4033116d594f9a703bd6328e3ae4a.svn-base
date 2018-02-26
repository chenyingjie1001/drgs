package com.firesoon.drgs.services.disease.imp;

import com.firesoon.drgs.Utils.UserUtils;
import com.firesoon.drgs.dto.disease.Disease;
import com.firesoon.drgs.dto.projectsettlement.Projectsettlement;
import com.firesoon.drgs.mapper.disease.DiseaseMapper;
import com.firesoon.drgs.services.disease.DiseaseService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;

/**
 * @author create by yingjie.chen on 2017/12/5.
 * @version 2017/12/5 14:21
 */
@Service
@Transactional
public class DiseaseServiceImpl implements DiseaseService {

    @Autowired
    private DiseaseMapper mapper;

    @Override
    public void importData(File file) {
        try {
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            List<Disease> list = null;

            list = ExcelImportUtil.importExcel(file, Disease.class, params);

            for (Disease disease : list) {
                if (disease.getDiseasecode() == null || "".equals(disease.getDiseasecode())) {
                    continue;
                }
                disease.setId(UUID.randomUUID().toString());
                disease.setCreatedate(new Date());
                disease.setUpdatedate(new Date());
//            disease.setOperater(UserUtils.getUser().getLoginname());
                if (mapper.importUpdate(disease) == 0) {
                    mapper.add(disease);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("文件内容错误!");
        }
    }

    @Override
    public String batchInsert(List<Disease> list) {
        String result = "";
        for (Disease disease : list) {
            disease.setId(UUID.randomUUID().toString());
            disease.setCreatedate(new Date());
            disease.setUpdatedate(new Date());
//            disease.setOperater(UserUtils.getUser().getLoginname());
            if (mapper.importUpdate(disease) == 0) {
                mapper.add(disease);
            } else {
                result = disease.getDiseasecode() + "不能添加重复数据";
            }
        }
        return result;
    }

    @Override
    public void delete(Disease disease) {
        String[] ids = disease.getIds().split(",");
        for (String id : ids) {
            Disease d = new Disease();
            d.setId(id.trim());
            mapper.del(d);
        }
    }

    @Override
    public void update(Disease disease) {
        disease.setUpdatedate(new Date());
        mapper.update(disease);
    }

    @Override
    public List<Disease> find(Disease disease) {
        if (disease.getTitle() != null) {
            disease.setTitle(disease.getTitle().replaceAll("'", "''"));
        }
        return mapper.find(disease);
    }

    @Override
    public void enable(Disease disease) {
        String[] ids = disease.getIds().split(",");
        for (String id : ids) {
            Disease d = new Disease();
            d.setId(id.trim());
            d.setDelflag(disease.getDelflag());
            d.setUpdatedate(new Date());
            mapper.update(d);
        }
    }

    @Override
    public List<Map<String, Object>> findDisease(Disease disease) {
        return mapper.findDisease(disease);
    }

    @Override
    public List<Map<String, Object>> findMedicare(Disease disease) {
        return mapper.findMedicare(disease);
    }

    @Override
    public List<Map<String, Object>> getFilterData(Map<String, Object> map) {
        return mapper.getFilterData(map);
    }

	@Override
	public void importProjectsettlement(File file) {
		try {
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            List<Projectsettlement> list = null;

            list = ExcelImportUtil.importExcel(file, Projectsettlement.class, params);

            for (Projectsettlement projectsettlement : list) {
            	//projectsettlement.setId(UUID.randomUUID().toString());
            	if (projectsettlement.getShenheyijiansh() == null || "".equals(projectsettlement.getShenheyijiansh())) {
                    continue;
                }
            	projectsettlement.setId(0);
            	projectsettlement.setCreatedate(new Date());
            	projectsettlement.setUpdatedate(new Date());
//            disease.setOperater(UserUtils.getUser().getLoginname());
               /* if (mapper.importUpdate(disease) == 0) {
                    mapper.add(projectsettlement);
                }*/
            	//System.out.println(projectsettlement);
            	mapper.addProjectsettlement(projectsettlement);
            }
        } catch (Exception e) {
            throw new RuntimeException("文件内容错误!");
        }
	}
}
