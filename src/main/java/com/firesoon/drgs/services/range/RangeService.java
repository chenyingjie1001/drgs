package com.firesoon.drgs.services.range;

import com.firesoon.drgs.dto.range.Range;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author create by yingjie.chen on 2017/12/1.
 * @version 2017/12/1 16:14
 */
public interface RangeService {

    //导入数据

    void importData(File file);

    //批量添加
    String batchInsert(List<Range> list);

    //删除

    void delete(int id);

    //修改

    void update(Range range);

    //查询
    List<Range> findRanges(Range range);
}
