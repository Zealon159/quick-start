package cn.zealon.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Spring MVC 配置
 * @auther: Zealon
 * @Date: 2018-01-22 16:38
 */
public class WebMvnConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger/**").
                addResourceLocations("/swagger/");
        super.addResourceHandlers(registry);
    }
}
