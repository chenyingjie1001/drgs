package com.firesoon.drgs.services.disease;

import com.firesoon.drgs.dto.disease.Disease;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author create by yingjie.chen on 2017/12/5.
 * @version 2017/12/5 14:20
 */
public interface DiseaseService {
    //导入数据

    void importData(File file);

    //批量添加
    String batchInsert(List<Disease> list);

    //删除

    void delete(Disease range);

    //修改

    void update(Disease range);

    //查询
    List<Disease> find(Disease range);


    void enable(Disease disease);


    List<Map<String, Object>> findDisease(Disease disease);

    List<Map<String, Object>> findMedicare(Disease disease);
    
    //导入项目结算
    void importProjectsettlement(File file);

    //getFilterData

    List<Map<String, Object>> getFilterData(Map<String, Object> map);

}
