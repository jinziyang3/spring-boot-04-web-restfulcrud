package com.example.springboot.config;

import com.example.springboot.compoment.LogingHandlerInterceptor;
import com.example.springboot.compoment.MyLocaleResolver;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author JinZiyang
 * @create 2019-11-29 - 14:28
 */
//使用WebMvcConfigurer可以来扩展springMVC的功能

@Configuration
public class MyMVC implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送“/king”请求来到success页面
        registry.addViewController("/king").setViewName("seccess");
    }

    //所有的WebMvcConfigurer组件都会一起起作用
    @Bean//将组建注册在容器中
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer configurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                     registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //springboot已经做好了静态资源映射
               // registry.addInterceptor(new LogingHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html","/","/user/login","/**/*.css","/**/*.js","/**/*.svg");
            }
        };
        return configurer;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
