package sky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sky._const.UserConst;
import sky.exception.UsernnameExistException;
import sky.pojo.User;
import sky.service.inter.UserLoginAndRegist;
import sky.service.inter.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: krny
 * Date: 2017/2/22 0022
 * Time: 23:23
 * To change this template use FileMode | Settings | FileMode Templates.
 */
@Controller
@RequestMapping("/user")
public class LoginAndRegis {
    @Autowired
    private UserLoginAndRegist loginAndRegist;

    @Autowired
    private UserManager userManager;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String userLoginGet() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String userLogin(User user, HttpServletRequest request, HttpServletResponse response) {

        if (loginAndRegist.userLogin(user)) {
//            保存session cookies
            request.getSession().setAttribute(UserConst.USER_SESSION, user);
//            response.addCookie(new Cookie(UserConst.USER_COOKIE, user.getUsername()));
            return "redirect:/user";
        }
        request.getSession().setAttribute("message", "帐号密码错误");
        return "login";
    }

    @RequestMapping(value = "/login/ajax", method = RequestMethod.POST)
    public void userLoginAjax(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("---------------------------------ajax" + user.getUsername());
        response.setContentType("text/plain;charset=UTF-8");
        if (loginAndRegist.userLogin(user)) {
//            保存session cookies
            request.getSession().setAttribute(UserConst.USER_SESSION, user);
//            response.addCookie(new Cookie(UserConst.USER_COOKIE, user.getUsername()));
            response.getWriter().write("success");
        } else {
            response.getWriter().write("帐号密码错误");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(User user, HttpServletRequest request, HttpServletResponse response) {
        try {
            loginAndRegist.addUser(user);
        } catch (UsernnameExistException e) {
            return "login";
        }
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String userIndex(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(UserConst.USER_SESSION);
        if ("admin".equals(user.getUsername())) {
            List<User> users = userManager.query();
            model.addAttribute("users", users);
            return "user";
        }
        model.addAttribute("message", "你没有权限");
        return "user";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(UserConst.USER_SESSION);
        return "redirect:/";
    }


}
