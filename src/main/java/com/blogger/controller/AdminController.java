package com.blogger.controller;

import com.alibaba.fastjson.JSONObject;
import com.blogger.server.UserService;
import com.blogger.server.UserService.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/loginGet", method = RequestMethod.GET)
    @ResponseBody
    public String defaultLogin() {
        return "首页";
    }

    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    @ResponseBody
    public String loginOut() {
        System.out.println("无权限");

        return "没有权限";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody String data) {
        JSONObject obj = JSONObject.parseObject(data);
        String userName = obj.getString("userName");
        String password = obj.getString("password");

        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return "未知账户";
        } catch (IncorrectCredentialsException ice) {
            return "密码不正确";
        } catch (LockedAccountException lae) {
            return "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            return "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            return "用户名或密码不正确！";
        }
        if (subject.isAuthenticated()) {
            return "登录成功";
        } else {
            token.clear();
            return "登录失败";
        }
    }


    /**
     * 身份认证测试接口
     *
     * @param request
     * @return
     */
    @RequestMapping("/admin")
    public String admin(HttpServletRequest request) {
        Object user = request.getSession().getAttribute("user");
        return "success";
    }

    /**
     * 角色认证测试接口
     *
     * @param request
     * @return
     */
    @RequestMapping("/student")
    public String student(HttpServletRequest request) {
        return "success";
    }

    /**
     * 权限认证测试接口
     *
     * @param request
     * @return
     */
    @RequestMapping("/teacher")
    public String teacher(HttpServletRequest request) {
        return "success";
    }


//    //跳转到登录表单页面
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login() {
//        return "need login";
//    }
//


    //登录成功后，跳转的页面
//    @RequestMapping("/success")
//    public String index(Model model) {
//        return "success";
//    }
//
//    //未登录，可以访问的页面
//    @RequestMapping("/index")
//    public String list(Model model) {
//        return "index";
//    }


//    //登陆验证，这里方便url测试，正式上线需要使用POST方式提交
//    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.GET)
//    public String index(UUser user) {
//        if (user.getNickname() != null && user.getPswd() != null) {
//            UsernamePasswordToken token = new UsernamePasswordToken(user.getNickname(), user.getPswd(), "login");
//            Subject currentUser = SecurityUtils.getSubject();
//            logger.info("对用户[" + user.getNickname() + "]进行登录验证..验证开始");
//            try {
//                currentUser.login(token);
//                //验证是否登录成功
//                if (currentUser.isAuthenticated()) {
//                    logger.info("用户[" + user.getNickname() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
//                    System.out.println("用户[" + user.getNickname() + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
//                    return "redirect:/";
//                } else {
//                    token.clear();
//                    System.out.println("用户[" + user.getNickname() + "]登录认证失败,重新登陆");
//                    return "redirect:/login";
//                }
//            } catch (UnknownAccountException uae) {
//                logger.info("对用户[" + user.getNickname() + "]进行登录验证..验证失败-username wasn't in the system");
//            } catch (IncorrectCredentialsException ice) {
//                logger.info("对用户[" + user.getNickname() + "]进行登录验证..验证失败-password didn't match");
//            } catch (LockedAccountException lae) {
//                logger.info("对用户[" + user.getNickname() + "]进行登录验证..验证失败-account is locked in the system");
//            } catch (AuthenticationException ae) {
//                logger.error(ae.getMessage());
//            }
//        }
//        return "login";
//    }


//    /**
//     * 用户登录接口
//     *
//     * @param user    user
//     * @param request request
//     * @return string
//     */
//    @PostMapping("/login")
//    public String login(User user, HttpServletRequest request) {
//
//        // 根据用户名和密码创建 Token
//        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
//        // 获取 subject 认证主体
//        Subject subject = SecurityUtils.getSubject();
//        try {
//            // 开始认证，这一步会跳到我们自定义的 Realm 中
//            subject.login(token);
//            request.getSession().setAttribute("user", user);
//            return "success";
//        } catch (Exception e) {
//            e.printStackTrace();
//            request.getSession().setAttribute("user", user);
//            request.setAttribute("error", "用户名或密码错误！");
//            return "login";
//        }
//    }
//
//    /**
//     * ajax登录请求接口方式登陆
//     *
//     * @param username
//     * @param password
//     * @return
//     */
//    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String, Object> submitLogin(@RequestParam(value = "nickname") String username, @RequestParam(value = "pswd") String password) {
//        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
//        try {
//
//            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//            SecurityUtils.getSubject().login(token);
//            resultMap.put("status", 200);
//            resultMap.put("message", "登录成功");
//
//        } catch (Exception e) {
//            resultMap.put("status", 500);
//            resultMap.put("message", e.getMessage());
//        }
//        return resultMap;
//    }
//
//    //登出
//    @RequestMapping(value = "/logout")
//    public String logout() {
//        return "logout";
//    }
//
//    //错误页面展示
//    @GetMapping("/403")
//    public String error() {
//        return "error ok!";
//    }
//
//    //管理员功能
//    @RequiresRoles("admin")
//    @RequiresPermissions("管理员添加")
//    @RequestMapping(value = "/admin/add")
//    public String create() {
//        return "add success!";
//    }
//
//    //用户功能
//    @RequiresRoles("user")
//    @RequiresPermissions("用户查询")
//    @RequestMapping(value = "/user/select")
//    public String detail() {
//        return "select success";
//    }
}
