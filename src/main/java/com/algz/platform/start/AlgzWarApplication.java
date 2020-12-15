package com.algz.platform.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 所有文件的起始类
 * (1)@SpringBootApplication 是一个包含了所有配置集合注解.
 * @ImportResource({"classpath:algz/platform/core/configure/xml/spring-shiro.xml"})
 * @ImportResource({"classpath:algz/platform/core/configure/xml/bak/cxf.xml"})
 * @ComponentScan(basePackages = {"algz.platform","com"})//扫描注解组件的包的基础位置(@Controller,@Service...)
 * @EnableWebMvc //启用 MVC Java config ，在你的 @Configuration类上增加@EnableWebMvc注解
 * @PropertySource("/conf/jdbc.properties")
 * @EnableAspectJAutoProxy(proxyTargetClass=true)  
 * 
 * (2)此文件放到所有Bean的顶层,默认从此文件所在的包及同层子包向下扫描.如果在其它包中,必须再用以下注解指定扫描路径.
 * @ComponentScan(basePackages = {"algz.platform","com"})//扫描注解组件的包的基础位置(@Controller,@Service...)
 * 
 * (3)部分注解说明.
 * @EnableWebMvc导入spring_mvc需要的诸多bean，再配合@ComponentScan扫描包里面所有@Component(@Repository @Service  @Constroller)，基本的mvc配置就完成了。
 * @EnableTransactionManagement  //启动事务管理配置
*  @ImportResource导入基于XML方式的配置文件，多个配置文件采{"",""}数组。
*  
*  如果启动类(当前类),在外层包(com.algz.platform),则仅简单的设置 @SpringBootApplication,全部自动化配置.
*  
 * @author algz
 *
 */

@SpringBootApplication(scanBasePackages= {"com","com.algz.platform"})//自定义扫描Spring mvc的@Config,@Control,@Service,@Componse等注解路径.
@EnableJpaRepositories(basePackages = {"com"}) //自定义扫描 扩展 Spring Data 的 JpaRepository 接口的所有接口.
@EntityScan(basePackages = {"com"}) //自定义扫描Entity注解路径.
public class AlgzWarApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgzWarApplication.class, args);
	}
}

///**
// * 如果启动类(当前类),在外层包(com.algz.platform),则仅简单的设置 @SpringBootApplication,全部自动化配置.
// * @author algz
// *
// */
//@SpringBootApplication
//public class AlgzWarApplication{
//	public static void main(String[] args) {
//		SpringApplication.run(AlgzWarApplication.class, args);
//	}
//}
