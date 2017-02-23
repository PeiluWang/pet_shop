package com.upa.pet_shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.upa.pet_shop.config.EnvConfig;

@SpringBootApplication
@EnableScheduling
public class Application extends SpringBootServletInitializer{
	
	final Logger SYSTEM_LOGGER = LoggerFactory.getLogger("system");
	
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
    	return application.sources(Application.class);
    }
	
    /**
     * 自定义跳转404错误页面
     * @return
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {

                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");

                container.addErrorPages(error404Page, error500Page);
            }
        };
    }
    
	public static void main(String[] args) {
		/*
		 * 启动网站应用
		 */
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        /*
         * 初始化环境变量
         */
        ctx.getBean(EnvConfig.class).init();
        System.out.println("All set!");
    }
}
