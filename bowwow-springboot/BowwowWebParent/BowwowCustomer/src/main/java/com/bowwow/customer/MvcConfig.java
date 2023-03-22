package com.bowwow.customer;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	
		// 메인 이미지 폴더 생성 후 저장
		String productMainImagesDirName = "../product-main-images";
		Path productMainImagesDir = Paths.get(productMainImagesDirName);
		
		String productMainImagesPath = productMainImagesDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/product-main-images/**")
					.addResourceLocations("file:/" + productMainImagesPath + "/");
		
		// 서브 이미지 폴더 생성 후 저장
		String productDescImagesDirName = "../product-desc-images";
		Path productDescImagesDir = Paths.get(productDescImagesDirName);
		
		String productDescImagesPath = productDescImagesDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/product-desc-images/**")
					.addResourceLocations("file:/" + productDescImagesPath + "/");
		
	}

	
}
