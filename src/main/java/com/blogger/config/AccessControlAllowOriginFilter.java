package com.blogger.config;//package com.blogger.config;
//
//import org.springframework.context.annotation.Configuration;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 解决跨域问题-过滤器
// */
//@Configuration
//public class AccessControlAllowOriginFilter implements Filter {
//
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        System.out.println("跨域问题-过滤器");
//        HttpServletResponse response = (HttpServletResponse) res;
//        // 允许来自所有域名请求
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        // 是否允许发送Cookie，ture为运行
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        // 设置所允许的HTTP请求方法
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        // 服务器支持的所有头信息字段，多个字段用逗号分隔
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
//        chain.doFilter(req, response);
//    }
//
//    public void init(FilterConfig filterConfig) {
//
//    }
//
//    public void destroy() {
//
//    }
//
//}
//
