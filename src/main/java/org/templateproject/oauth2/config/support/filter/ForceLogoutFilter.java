package org.templateproject.oauth2.config.support.filter;

import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.templateproject.oauth2.config.support.utils.FilterUtils;
import org.templateproject.oauth2.constant.ShiroConsts;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 强制退出拦截器
 * Created by wuwenbin on 2017/8/9.
 */
@Component
public class ForceLogoutFilter extends AccessControlFilter {

    private static Logger LOG = LoggerFactory.getLogger(ForceLogoutFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest req = WebUtils.toHttp(request);
        String URI = req.getRequestURI();
        String method = req.getMethod();
        LOG.info("--SessionTimeoutFilter，访问URI:[{}]，请求方式:[{}]", URI, method);

        Session session = getSubject(request, response).getSession(false);
        if (session != null) {
            Object forceLogoutFlag = session.getAttribute(ShiroConsts.SESSION_FORCE_LOGOUT_KEY);
            return forceLogoutFlag == null;
        }
        return false;
    }

    /**
     * 访问拒绝就停职拦截器链的执行，停止执行下一个拦截器，到此为止
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        try {
            getSubject(servletRequest, servletResponse).logout();//强制退出
        } catch (Exception e) {/*ignore exception*/}

        HttpServletRequest request = WebUtils.toHttp(servletRequest);
        request.getSession().setAttribute(ShiroConsts.SESSION_FORCE_LOGOUT_KEY, Boolean.TRUE);

        String loginUrl = getLoginUrl() + (getLoginUrl().contains("?") ? "&" : "?") + "forceLogout=" + UUID.randomUUID().toString().replace("-", "");
        FilterUtils.onAccessDenied(servletRequest, servletResponse, "您已被强制退出！", loginUrl);

        return false;
    }
}
