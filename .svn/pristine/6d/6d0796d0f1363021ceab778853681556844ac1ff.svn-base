package com.firesoon.drgs.services.warn.impl;

import com.firesoon.drgs.Utils.UserUtils;
import com.firesoon.drgs.dto.warn.EarlyWarning;
import com.firesoon.drgs.mapper.warn.EarlyWarningMapper;
import com.firesoon.drgs.services.warn.EarlyWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * @author create by yingjie.chen on 2018/1/15.
 * @version 2018/1/15 9:59
 */
@Service
@Transactional
public class EarlyWarningServiceImpl implements EarlyWarningService {

    @Autowired
    private EarlyWarningMapper mapper;
    @Override
    public List<EarlyWarning> find(EarlyWarning earlyWarning) {
        return mapper.find(earlyWarning);
    }

    @Override
    public void add(EarlyWarning earlyWarning) {
        earlyWarning.setId(UUID.randomUUID().toString());
        earlyWarning.setCreateby(UserUtils.getUser().getLogin_name());
        earlyWarning.setCreatedate(new Date());
        mapper.add(earlyWarning);
    }

    @Override
    public void update(EarlyWarning earlyWarning) {
        mapper.update(earlyWarning);
    }

    @Override
    public void del(EarlyWarning earlyWarning) {
        mapper.del(earlyWarning);
    }

    @Override
    public Object getCondition(String type) {
        return mapper.getSelectOption(type);
    }

    @Override
    public Object getDoctors() {
        List<Map> list = mapper.getDoctors();
        list.forEach(map -> {
            List<Map> children = mapper.getDoctorsChildren(map.get("CODE").toString());
            map.put("children", children);
        });
        return list;
    }
}
