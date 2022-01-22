package com.yunqi.common.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.util.Utils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;
import java.util.Collections;

/**
 * <p>
 *     Druid数据库连接池配置
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
@Configuration
@ConditionalOnWebApplication
@AutoConfigureAfter(DruidDataSourceAutoConfigure.class)
public class DruidConfiguration {

    /**
     * <p>
     *     注册Servlet信息， 配置监控视图
     * </p>
     * @return  ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet(){
        // 注册服务
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        // IP白名单 (没有配置或者为空，则允许所有访问) 127.0.0.1 只允许本机访问
        bean.addInitParameter("allow", "127.0.0.1");
        // IP黑名单 (存在共同时，deny优先于allow)
        //bean.addInitParameter("deny", "192.168.20.38");

        //设置控制台登录的用户名和密码
        bean.addInitParameter("loginUsername", "root");
        bean.addInitParameter("loginPassword", "root");
        //是否能够重置数据
        bean.addInitParameter("resetEnable", "false");
        return bean;
    }


    /**
     * <p>
     *     注册Filter信息, 监控拦截器
     * </p>
     * @return  FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter(){
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<WebStatFilter>();
        //可以设置也可以获取,设置一个阿里巴巴的过滤器
        bean.setFilter(new WebStatFilter());
        // 添加过滤规则
        bean.setUrlPatterns(Collections.singletonList("/*"));
        // 忽略过滤格式
        bean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return bean;
    }

    /**
     * <p>
     *     去掉druid底部广告
     * </p>
     * @param properties DruidStatProperties
     * @return           FilterRegistrationBean
     * @throws IOException  IO异常
     */
    @Bean
    public FilterRegistrationBean<RemoveAdFilter> removeDruidAdFilter(DruidStatProperties properties) throws IOException {
        // 获取web监控页面的参数
        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
        // 提取common.js的配置路径
        String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
        String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
        // 获取common.js 查找带有广告的common.js全路径
        String text = Utils.readFromResource("support/http/resources/js/common.js");
        // 屏蔽 this.buildFooter(); 不构建广告
        final String newJs = text.replace("this.buildFooter();", "//this.buildFooter();");
        FilterRegistrationBean<RemoveAdFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new RemoveAdFilter(newJs));
        registration.addUrlPatterns(commonJsPattern);
        return registration;
    }


    /**
     * <p>
     *     删除druid的广告过滤器
     * </p>
     */
    private static class RemoveAdFilter implements Filter {

        private final String newJs;

        public RemoveAdFilter(String newJS) {
            this.newJs = newJS;
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {
            chain.doFilter(request, response);
            // 重置缓冲区，响应头不会被重置
            response.resetBuffer();
            response.getWriter().write(newJs);
        }
    }
}
