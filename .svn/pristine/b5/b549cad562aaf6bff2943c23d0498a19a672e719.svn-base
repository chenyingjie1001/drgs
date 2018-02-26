package com.firesoon.drgs.exe;

import com.firesoon.drgs.Utils.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

/**
 * @author create by yingjie.chen on 2018/1/16.
 * @version 2018/1/16 10:49
 */
public class Exe {

    private final static Logger logger = LoggerFactory.getLogger(Exe.class);

    public static void main(String[] args) {
        do {
            try {
                List<Map<String, Object>> rules = getRules();

                Map<Map<String, Object>, List<Map<String, Object>>> result = getWarnPatients(rules);

                JdbcTemplate conn = DBUtil.getJdbcTemplate(DBUtil.getDataSource("drgs"));

                result.forEach((k, v) -> {
                    v.forEach(patient -> {
                        String sql = getInsertSql(k, patient);
                        conn.execute(sql);
                    });
                });


                Thread.sleep(1000 * 60 * 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);


    }

    static String getInsertSql(Map<String, Object> rule, Map<String, Object> patient) {
        logger.info("---------------------开始生成sql------------------------");
        StringBuffer sql = new StringBuffer();
        sql.append("insert into warn_result values (");
        String id = UUID.randomUUID().toString();
        String telephone = rule.get("TELEPHONE").toString();
        StringBuffer sb = new StringBuffer();
        String[] messages = rule.get("MESSAGE").toString().split(",");
        sb.append("您好,[" + rule.get("NAME") + "]中,");
        for (String msg : messages) {
            sb.append(msg + ": " + patient.get(msg) + " ");
        }
        sb.append("已经超出了预警，请进行医保控费处理");
        String msg = sb.toString();
        String zhuyuanhao = patient.get("住院号").toString();
        String issend = "0";
        String createdate = "sysdate";
        String createby = "system";
        String doctorname = rule.get("DOCTORNAME").toString();

        sql.append("'" + id + "'," + "'" + telephone + "'," + "'" + msg
                + "'," + "'" + zhuyuanhao + "'," + "'" + issend + "',"
                + createdate + "," + "'" + createby + "'," + "'" + doctorname + "'");
        sql.append(")");
        logger.info("----------------------生成的sql为：" + sql.toString() + "---------------------");
        return sql.toString();
    }


    static Map<Map<String, Object>, List<Map<String, Object>>> getWarnPatients(List<Map<String, Object>> rules) {
        logger.info("---------------------开始查找需要预警的patient------------------------");
        Map<Map<String, Object>, List<Map<String, Object>>> resultMap = new HashMap<Map<String, Object>, List<Map<String, Object>>>();
        rules.forEach(rule -> {
            //1.全部0任意
            String condition = rule.get("CONDITION").toString();
            //3个条件
            String rulecode = rule.get("RULECODE").toString();
            String rulecond = rule.get("RULECOND").toString();
            String value = rule.get("VALUE").toString();
            //每个条件都可能有多个
            String[] rulecodes = rulecode.split(",");
            String[] ruleconds = rulecond.split(",");
            String[] values = value.split(",");

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < rulecodes.length; i++) {
                sb.append(rulecodes[i]);
                sb.append(ruleconds[i]);
                sb.append(values[i]);
                if (i < rulecodes.length - 1) {
                    if ("1".equals(condition)) {
                        sb.append(" and ");
                    } else {
                        sb.append(" or ");
                    }
                }
            }
            String sql = "select * from costmonitoring t " +
                    "where t.控费类型 = 1 and t.当前状态 = '在院' " +
                    " and not exists " +
                    "(select 1 from warn_result t1 where t.住院号 = t1.zhuyuanhao) and (" + sb.toString()+")";
            JdbcTemplate conn = DBUtil.getJdbcTemplate(DBUtil.getDataSource("drgs"));

            List<Map<String, Object>> result = conn.queryForList(sql);
            //返回对应的rule和数据
            resultMap.put(rule, result);

        });
        logger.info("---------------------查找需要预警的patient结束：" + resultMap.keySet().size() + "------------------------");
        return resultMap;
    }

    /**
     * @return
     */
    static List<Map<String, Object>> getPatients() {
        JdbcTemplate conn = DBUtil.getJdbcTemplate(DBUtil.getDataSource("drgs"));
        String sql = "select * from costmonitoring t " +
                "where not exists " +
                "(select 1 from warn_result t1 where t.住院号 = t1.zhuyuanhao)";
        return conn.queryForList(sql);
    }

    static List<Map<String, Object>> getRules() {
        logger.info("---------------------开始查找需要预警规则------------------------");
        JdbcTemplate conn = DBUtil.getJdbcTemplate(DBUtil.getDataSource("drgs"));
        String sql = "select * from early_warning where flag = '1'";
        logger.info("---------------------查找需要预警规则结束------------------------");
        return conn.queryForList(sql);
    }
}
