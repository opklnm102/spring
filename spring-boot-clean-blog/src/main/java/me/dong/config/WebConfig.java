package me.dong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Navigation 어노테이션을 사용할 클래스
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                if(handler instanceof HandlerMethod){
                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    Navigation navSection = handlerMethod.getBean().getClass().getAnnotation(Navigation.class);
                    if(navSection != null && modelAndView != null){
                        modelAndView.addObject("navSection", navSection.value().getValue());
                    }
                }
            }
        });
    }
}
