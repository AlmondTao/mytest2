package com.taoqy.config;

import com.bstek.ureport.console.UReportServlet;
import com.bstek.ureport.provider.report.file.FileReportProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.*;

import javax.servlet.Servlet;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/5/26
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
@Configuration
@ImportResource({"classpath:ureport-console-context.xml"})
@EnableAutoConfiguration
//@PropertySource("classpath:config.properties")
@ComponentScan
public class UReport2Config {
    @Bean
    public ServletRegistrationBean<Servlet> buildUreportServlet(){
        return new ServletRegistrationBean<Servlet>(new UReportServlet(),"/ureport/*");
    }

//    @Bean
    public FileReportProvider createReportProvider(FileReportProvider fileReportProvider){
        fileReportProvider.setFileStoreDir("D:/ureport/");
        return fileReportProvider;
    }
//    @Value("${localtion}")
    String localtion;
}
