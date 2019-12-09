package ink.zxu.learn_japanese.config;

import ink.zxu.learn_japanese.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;


/**
 * @author 张伟
 * @date 2019/9/15 14:55
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Resource
    private UploadConfigure uploadConfigure;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/123")
        .excludePathPatterns("/user/login","/manage/**","/image/**", "/static/**","/course/**","/video/**","/test/**","/communication/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {  //如果是Windows系统
            registry.addResourceHandler("/image/**").
                    addResourceLocations("file:"+uploadConfigure.getImageBasePath());
            registry.addResourceHandler("/course/**").
                    addResourceLocations("file:"+uploadConfigure.getCourseBasePath());
            registry.addResourceHandler("/video/**").
                    addResourceLocations("file:"+uploadConfigure.getVideoBasePath());
            registry.addResourceHandler("/voice/**").
                    addResourceLocations("file:"+uploadConfigure.getVoiceBasePath());
        }else{//linux和mac系统
            registry.addResourceHandler("/image/**").
                    addResourceLocations("file:"+uploadConfigure.getImageBasePath());
            registry.addResourceHandler("/course/**").
                    addResourceLocations("file:"+uploadConfigure.getCourseBasePath());
            registry.addResourceHandler("/video/**").
                    addResourceLocations("file:"+uploadConfigure.getVideoBasePath());
            registry.addResourceHandler("/video/**").
                    addResourceLocations("file:"+uploadConfigure.getVoiceBasePath());

        }
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/pages/login");

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

    }
}
