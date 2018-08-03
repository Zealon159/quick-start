package cn.zealon.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import java.util.HashSet;
import java.util.Set;

/**
 * @auther: Zealon
 * @Date: 2018-07-12 17:27

@Configuration
public class SpringMvcConfig {

    @Bean
    public FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean(){
        FormattingConversionServiceFactoryBean factoryBean = new FormattingConversionServiceFactoryBean();
        Set set = new HashSet();
        set.add(dateConvert());
        factoryBean.setConverters(set);
        return factoryBean;
    }

    @Bean
    public DateConvert dateConvert(){
        return new DateConvert();
    }
}
 */