package com.firesoon.drgs.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.firesoon.drgs.dto.base.ResultMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.AdviceFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author create by yingjie.chen on 2017/10/13.
 * @version 2017/10/13 17:55
 */
public class ShiroLoginFilter extends AdviceFilter {
    /**
     * 在访问controller前判断是否登录，返回json，不进行重定向。
     *
     * @param request
     * @param response
     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        Object sysUser = httpServletRequest.getSession().getAttribute("user");
        String uri = httpServletRequest.getRequestURI();
        if (null == sysUser && (StringUtils.contains(uri, "/user") /*|| StringUtils.contains(uri, "/webservice")*/)) {
//            String requestedWith = httpServletRequest.getHeader("X-Requested-With");
//            if (StringUtils.isNotEmpty(requestedWith) && StringUtils.equals(requestedWith, "XMLHttpRequest")) {//如果是ajax返回指定数据
            ResultMessage<String> rm = new ResultMessage<String>();
            rm.setMsg("未登录");
            rm.setHttpCode(401);
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write(JSONObject.toJSONString(rm));
            return false;
//            } else {//不是ajax进行重定向处理
//                httpServletResponse.sendRedirect("/login/local");
//                return false;
//            }
        }
        return true;
    }


}
