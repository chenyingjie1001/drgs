package com.firesoon.drgs.controller.base;

import com.firesoon.drgs.dto.base.MyExceptionResponse;
import com.firesoon.drgs.dto.base.ReMsg;
import com.firesoon.drgs.dto.base.ResultMessage;
import com.firesoon.drgs.exception.base.WarnException;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 可以做一些异常控制，查询等通用方法
 *
 * @author yingjie.chen
 */

@ControllerAdvice
public class BaseController {


    /**
     * @param o
     * @return
     */
    public Object succ(Object... o) {
        ReMsg rm = new ReMsg();
        rm.setHttpCode(200);
        rm.setMsg("请求成功");
        if(o != null && o.length == 1){
            rm.setData(o[0]);
        }else{
            rm.setData(o);
        }
        return rm;
    }

    // @ExceptionHandler(RuntimeException.class)
    // @ExceptionHandler(value={RuntimeException.class,MyRuntimeException.class})
    @ExceptionHandler
    // 处理所有异常
    @ResponseBody
    // 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
    public MyExceptionResponse exceptionHandler(Exception e,
                                                HttpServletRequest request, HttpServletResponse response) {
        e.printStackTrace();
        MyExceptionResponse resp = new MyExceptionResponse();
        resp.setTimestamp(new Date());
        resp.setMsg(e.getMessage());
        if (e instanceof AuthenticationException) {
            resp.setHttpCode(401);
        } else if (e instanceof WarnException) {
            resp.setHttpCode(200);
        } else if (e instanceof RuntimeException) {
            resp.setHttpCode(300);
        } else {
            resp.setHttpCode(500);
        }
        return resp;
    }

    public <T> ResultMessage<T> success(PageInfo<T> o1, Object o2) {
        ResultMessage<T> resp = new ResultMessage<T>();
        setSuccess(resp);
        resp.setPageinfo(o1);
        resp.setCol(o2);
        return resp;
    }

    public ResultMessage<Object> success() {
        ResultMessage<Object> resp = new ResultMessage<Object>();
        setSuccess(resp);
        return resp;
    }

    public <T> ResultMessage<T> success(T t) {
        ResultMessage<T> resp = new ResultMessage<T>();
        setSuccess(resp);
        resp.setT(t);
        return resp;
    }

    public <T> ResultMessage<T> success(List<T> data, T t) {
        ResultMessage<T> resp = new ResultMessage<T>();
        setSuccess(resp);
        resp.setData(data);
        return resp;
    }

    public ResultMessage<Object> success(List<Map<String, Object>> mapdata) {
        ResultMessage<Object> resp = new ResultMessage<Object>();
        setSuccess(resp);
        resp.setMapdata(mapdata);
        return resp;
    }

    public ResultMessage<Object> success(Map<String, Object> m) {
        ResultMessage<Object> resp = new ResultMessage<Object>();
        setSuccess(resp);
        resp.setM(m);
        return resp;
    }

    public <T> ResultMessage<T> success(PageInfo<T> pageinfo) {
        ResultMessage<T> resp = new ResultMessage<T>();
        setSuccess(resp);
        resp.setPageinfo(pageinfo);
        return resp;
    }

    public Object failure() {
        throw new RuntimeException("服务器异常");
    }

    public Object success(String message) {
        ResultMessage<Object> resp = new ResultMessage<Object>();
        setSuccess(resp, message);
        return resp;
    }

    public <T> void setSuccess(ResultMessage<T> resp, String message) {
        resp.setHttpCode(200);
        resp.setTimestamp(new Date());
        resp.setMsg(message);
    }

    public <T> void setSuccess(ResultMessage<T> resp) {
        resp.setHttpCode(200);
        resp.setTimestamp(new Date());
        resp.setMsg("成功");
    }

    protected boolean isIE(HttpServletRequest request) {
        return request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0 || request.getHeader("USER-AGENT").toLowerCase().indexOf("rv:11.0") > 0;
    }
}
