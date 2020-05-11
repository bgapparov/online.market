package edu.miu.cs545.group01.online.market.config;

import edu.miu.cs545.group01.online.market.converter.StringToUserTypeEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

//        registry.addViewController("/index").setViewName("index");
//        registry.addViewController("/").setViewName("index");
//        registry.addViewController("/login").setViewName("ar/login");
//        registry.addViewController("/login").setViewName("ar/login");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler( "/img/**")//temp
                .addResourceLocations("classpath:/static/images/")
                .setCachePeriod(31556926);

        registry.addResourceHandler( "/images/**")
                .addResourceLocations("classpath:/static/images/")
                .setCachePeriod(31556926);
        registry.addResourceHandler( "/css/**")
                .addResourceLocations("classpath:/static/css/")
                .setCachePeriod(31556926);
        registry.addResourceHandler( "/js/**")
                .addResourceLocations("classpath:/static/js/")
                .setCachePeriod(31556926);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToUserTypeEnum());
    }
}
