package com.firesoon.drgs.services.warn;

import java.util.*;
import com.firesoon.drgs.dto.warn.EarlyWarning;

/**
 * @author create by yingjie.chen on 2018/1/15.
 * @version 2018/1/15 9:59
 */
public interface EarlyWarningService {
    List<EarlyWarning> find(EarlyWarning earlyWarning);

    void add(EarlyWarning earlyWarning);

    void update(EarlyWarning earlyWarning);

    void del(EarlyWarning earlyWarning);

    Object getCondition(String type);
    Object getDoctors();
}
