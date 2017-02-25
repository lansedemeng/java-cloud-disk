package sky.controller.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sky._const.UserConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: krny
 * Date: 2017/2/23 0023
 * Time: 0:06
 * To change this template use FileMode | Settings | FileMode Templates.
 */
public class SessionInterceptor implements HandlerInterceptor {
    /**
     * 过滤掉没有登录的
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute(UserConst.USER_SESSION);
        if (user == null) {
            response.sendRedirect("/user/login");
            return false;
        }
        return true;
    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
