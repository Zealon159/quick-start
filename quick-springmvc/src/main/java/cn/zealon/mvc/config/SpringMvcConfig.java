package cn.zealon.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

/**
 * @auther: Zealon
 * @Date: 2018-07-12 17:27
 */
@Configuration
public class SpringMvcConfig {

    @Bean
    public FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean(){
        FormattingConversionServiceFactoryBean factoryBean = new FormattingConversionServiceFactoryBean();
        return factoryBean;
    }
}
