package com.firesoon.drgs.mapper.disease;

import com.firesoon.drgs.dto.disease.Disease;
import com.firesoon.drgs.dto.projectsettlement.Projectsettlement;
import com.firesoon.drgs.mapper.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

/**
 * @author create by yingjie.chen on 2017/12/5.
 * @version 2017/12/5 14:21
 */
@Mapper
public interface DiseaseMapper extends BaseMapper<Disease> {

    Integer checkImportData(Disease disease);

    int importUpdate(Disease disease);

    List<Map<String, Object>> findDisease(Disease disease);

    List<Map<String, Object>> findMedicare(Disease disease);

    void addProjectsettlement(Projectsettlement projectsettlement);

    List<Map<String, Object>> getFilterData(Map<String, Object> map);
}
