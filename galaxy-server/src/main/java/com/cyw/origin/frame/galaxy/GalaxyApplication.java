package com.cyw.origin.frame.galaxy;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * springboot启动文件
 * @author yiwen.chang
 * @version 1.0.0
 */
@SpringBootApplication(scanBasePackages = "com.cyw.origin.frame.galaxy")
@EnableApolloConfig({"weimob.arch-common", "my-jdbc", "redis", "common", "dubbo", "application-dev"})
@MapperScan("com.cyw.origin.frame.galaxy.**.dao")
public class GalaxyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(GalaxyApplication.class);
        application.setAdditionalProfiles("local");
        application.run(args);
    }

//    private static Properties getProperties() throws IOException {
//        ENV env = EnvUtil.checkEnv();
//        ClassLoader classLoader = CabbenServiceApplication.class.getClassLoader();
//        Properties properties = new Properties();
//        InputStream resourceAsStream = classLoader.getResourceAsStream("application.properties");
//        properties.load(resourceAsStream);
//        resourceAsStream.close();
//        switch (env) {
//            case DEV:
//                properties.put("cabbeen.erp.env", "dev");
//                break;
//            case ONLINE:
//                properties.put("cabbeen.erp.env", "online");
//                break;
//            default:
//                properties.put("cabbeen.erp.env", "dev");
//                break;
//        }
//        //Cat.logTransaction("start","start",1);
//        log.info("================当前环境：" + properties.get("cabbeen.erp.env") + "================");
//        log.info("================java.library.path = " + System.getProperty("java.library.path") + "================");
//        return properties;
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        try {
//            Properties properties = getProperties();
//            application.properties(properties);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return application.sources(CabbenServiceApplication.class);
//    }

//    @Bean
//    MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setLocation("D:/data/upload/temp");
//        return factory.createMultipartConfig();
//    }
}