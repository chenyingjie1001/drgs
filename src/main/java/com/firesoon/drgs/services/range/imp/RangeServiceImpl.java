package com.firesoon.drgs.services.range.imp;


import com.firesoon.drgs.dto.range.Range;
import com.firesoon.drgs.exception.base.WarnException;
import com.firesoon.drgs.mapper.range.RangeMapper;
import com.firesoon.drgs.services.range.RangeService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * @author create by yingjie.chen on 2017/12/1.
 * @version 2017/12/1 16:14
 */
@Service
@Transactional
public class RangeServiceImpl implements RangeService {

    @Autowired
    private RangeMapper mapper;

    @Override
    public void importData(File file) {
        try {
            ImportParams params = new ImportParams();
            List<Range> list = null;

            list = ExcelImportUtil.importExcel(file, Range.class, params);

            for (Range r : list) {
                if (r.getRange() == null || "".equals(r.getRange())) {
                    continue;
                }
                if (mapper.importUpdate(r) == 0) {
                    mapper.add(r);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("文件内容错误!");
        }
    }

    @Override
    public String batchInsert(List<Range> list) {
        String result = "";
        for (Range range : list) {
            if (mapper.importUpdate(range) == 0) {
                mapper.add(range);
            } else {
                result = range.getRange() + "不能添加重复数据";
            }
        }
        return result;
    }

    @Override
    public void delete(int id) {
        mapper.delRange(id);
    }

    @Override
    public void update(Range range) {
        mapper.update(range);
    }

    @Override
    public List<Range> findRanges(Range range) {
        if (range.getTitle() != null) {
            range.setTitle(range.getTitle().replaceAll("'", "''"));
        }
        return mapper.find(range);
    }
}