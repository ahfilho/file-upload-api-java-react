//package br.com.api.cors;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Configuration
//@Order(Ordered.HIGHEST_PRECEDENCE)
//
//public class MyCorsConfiguration implements Filter {
//
////    @Override
////    public void addCorsMappings(CorsRegistry registry) {
////        registry.addMapping("/**")
////                .allowedOrigins("http://localhost:3000")
////                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
////                .allowedHeaders("'Autorization,Origin, X-Requested-With, Content-Type, Accept, XMLHttpRequest'")
////                .exposedHeaders("Authorization")
////                .allowCredentials(true)
////                .maxAge(3600);
////
////    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        final HttpServletResponse res = (HttpServletResponse) response;
//        HttpServletRequest req = (HttpServletRequest) request;
//        String origin = req.getHeader("http://localhost:3000");
//        ((HttpServletResponse) response).setHeader("Access-Control-Allow-Credentials", "true");
//        ((HttpServletResponse) response).setHeader("Access-Control-Allow-Origin", origin != null && origin.contains("ws") ? "" : origin);
//        ((HttpServletResponse) response).setHeader("Vary", "Origin");
//        ((HttpServletResponse) response).setHeader("Access-Control-Allow-Methods", "*");
//        ((HttpServletResponse) response).setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Authorization, Content-Type, Accept, X-CSRF-TOKEN");
//        ((HttpServletResponse) response).setHeader("Access-Control-Max-Age", "3600");
//        if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) req).getMethod())) {
//            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_OK);
//        } else {
//            chain.doFilter(req, res);
//        }
//    }
//
//    public void destroy() {
//    }
//
//    public void init(FilterConfig config) throws ServletException {
//    }
//
//    public void liberaCorsr(HttpServletResponse res) {
//        if (res.getHeader("Access-Control-Allow-Origin") == null) {
//            res.addHeader("Access-Control-Allow-Origin", "*");
//        }
//        if (res.getHeader("Access-Control-Allow-Headers") == null) {
//            res.addHeader("Access-Control-Allow-Headers", "*");
//        }
//        if (res.getHeader("Access-Control-Request-Headers") == null) {
//            res.addHeader("Access-Control-Request-Headers", "*");
//        }
//        if (res.getHeader("Access-Control-Allow-Methods") == null) {
//            res.addHeader("Access-Control-Allow-Methods", "*");
//        }
//
//
//    }
//
//}
