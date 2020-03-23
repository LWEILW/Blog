//package com.blogger.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 拦截器方法
// */
//public class LogCostInterceptor implements HandlerInterceptor {
//    private final static Logger logger = LoggerFactory.getLogger(LogCostInterceptor.class);
//    long start = System.currentTimeMillis();
//
//    /**
//     * preHandle是请求执行前执行的，
//     * postHandler是请求结束执行的，但只有preHandle方法返回true的时候才会执行，
//     * afterCompletion是视图渲染完成后才执行，同样需要preHandle返回true，该方法通常用于清理资源等工作。
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
////        logger.info("SessionInterceptor preHandle方法，在请求方法之前调用，Controller方法调用之前");
//        start = System.currentTimeMillis();
//        return true;
////        HttpServletRequest httpRequest = WebUtils.toHttp(httpServletRequest);
////        logger.info("拦截器url:" + httpRequest.getRequestURI().toString());
////        // 前端token授权信息放在请求头中传入
////        String authrozation = httpRequest.getHeader("token");
////        String userId = httpRequest.getHeader("user");
////        if (authrozation != null && authrozation != "") {
////            logger.info("有值");
////            return false;
////        }
////        return true;
////        //  代码示例
////        HttpSession session = httpServletRequest.getSession();
////        // 用户已登录
////        if (session.getAttribute("user") != null) {
////            System.out.println("自定义拦截器-用户已登录");
////            return true;
////        } else {
////            //用户未登录，直接跳转登录页面
////            httpServletResponse.sendRedirect("/login");
////            System.out.println("自定义拦截器-用户未登录—跳转到login页面");
////            return false;
////        }
//    }
//
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
////        logger.info("SessionInterceptor postHandle方法，请求处理之后调用，但是在视图被渲染之前（Controller方法调用之后）");
//        System.out.println("Interceptor cost=" + (System.currentTimeMillis() - start));
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
////        logger.info("SessionInterceptor afterCompletion方法，在整个请求结束之后调用，也就是在Dispatcher渲染了整个视图之后进行（主要进行资源清理工作）");
//
//    }
//}
