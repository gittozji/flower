package me.zji.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;

/**
 * Listener、Filter配置
 * Created by imyu on 2017/2/11.
 */
public class ListenerFilterConfig implements WebApplicationInitializer{
    public void onStartup(ServletContext servletContext) throws ServletException {
        // log4j
        servletContext.setInitParameter("log4jConfigLocation","classpath:me/zji/config/log4j.properties");
        servletContext.addListener(Log4jConfigListener.class);

        // utf-8
        FilterRegistration.Dynamic filterRegistration0 = servletContext.addFilter("characterEncodingFilter", CharacterEncodingFilter.class);
        Map<String, String> iniParam0 = new HashMap<String, String>();
        iniParam0.put("encoding", "UTF-8");
        iniParam0.put("forceEncoding", "true");
        filterRegistration0.setInitParameters(iniParam0);
        filterRegistration0.addMappingForUrlPatterns(null, false, "/*");


        // shiro
        FilterRegistration.Dynamic filterRegistration1 = servletContext.addFilter("shiroFilter",DelegatingFilterProxy.class);
        filterRegistration1.addMappingForUrlPatterns(null, false, "/*");
    }
}
