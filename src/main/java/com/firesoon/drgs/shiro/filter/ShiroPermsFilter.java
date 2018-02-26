package com.firesoon.drgs.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.firesoon.drgs.dto.base.ResultMessage;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author create by yingjie.chen on 2017/10/16.
 * @version 2017/10/16 14:03
 */
public class ShiroPermsFilter extends PermissionsAuthorizationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//        String requestedWith = httpServletRequest.getHeader("X-Requested-With");
//        if (StringUtils.isNotEmpty(requestedWith) &&
//                StringUtils.equals(requestedWith, "XMLHttpRequest")) {//如果是ajax返回指定格式数据
        ResultMessage<String> rm = new ResultMessage<String>();
        rm.setMsg("资源操作认证不通过");
        rm.setHttpCode(403);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(JSONObject.toJSONString(rm));
//        } else {//如果是普通请求进行重定向
//            httpServletResponse.sendRedirect("/403");
//        }
        return false;
    }
}
