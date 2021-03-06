package com.cyw.origin.frame.galaxy.util.lang;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

@Slf4j
public class PropertyUtil {

    public static Properties loadPropertyFileWithClassloader(String fileName, ClassLoader loader){
    	Properties prop = new Properties();
    	String commonFile = fileName;
        URL commonFileUrl = loader.getResource(commonFile);
        try{
        	prop.load(commonFileUrl.openStream());
        }catch(Exception e){
        	log.error("Can't load configuration file:" + commonFile);
        	log.error(e.getMessage(), e);
        }
        return prop;
    }
    
    public static Properties loadPropertyFile(String fileName) {
        return getProperties(fileName);
    }

	public static Properties getProperties(String fileName) {

		if (StringUtils.isEmpty(fileName)) {
			return null;
		}

		Properties p = new Properties();

		try {
			InputStream inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName);
			p.load(new InputStreamReader(inputStream, "UTF-8"));
			inputStream.close();
		} catch (IOException e) {
			log.error("Can't load configuration file:" + fileName);
        	log.error(e.getMessage(), e);
		}

		return p;
	}

}
