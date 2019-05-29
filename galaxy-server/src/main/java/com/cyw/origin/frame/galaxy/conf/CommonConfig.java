package com.cyw.origin.frame.galaxy.conf;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * @author yiwen.chang
 * date 2019/5/29
 */
@Slf4j
@SpringBootConfiguration
public class CommonConfig {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        log.info("<========== Json转换容器加载 ==========>");
        /** 1. 需要定义一个converter转换消息的对象 */
        FastJsonHttpMessageConverter fasHttpMessageConverter = new FastJsonHttpMessageConverter();
        /** 2. 添加fastjson的配置信息，比如:是否需要格式化返回的json的数据 */
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        /** 3. 在converter中添加配置信息 */
        fasHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fasHttpMessageConverter;
        return new HttpMessageConverters(converter);
    }

    /*@Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation("D:/data/upload/temp");
        return factory.createMultipartConfig();
    }*/

}
