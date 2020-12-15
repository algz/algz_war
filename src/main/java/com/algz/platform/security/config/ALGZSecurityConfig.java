/**
 * 
 */
package com.algz.platform.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.algz.platform.security.authority.userManager.AUserRepository;
import com.algz.platform.security.config.handler.ALGZAccessDeniedHandler;
import com.algz.platform.security.config.handler.ALGZAuthenticationEntryPoint;
import com.algz.platform.security.config.handler.ALGZAuthenticationFailureHandler;
import com.algz.platform.security.config.handler.ALGZAuthenticationSuccessHandler;
import com.algz.platform.security.config.handler.ALGZLogoutSuccessHandler;
import com.algz.platform.security.config.handler.ALGZSessionInformationExpiredStrategy;

/**
 * 1.SecurityMetadataSource 权限的数据源
 *    实现一个SecurityMetadataSource ("安全元数据源").getAttributes方法，加载访问时所需要的具体权限.
 *    我们这里使用他的一个子类FilterInvocationSecurityMetadataSource。
 *    作用: 拦截到当前的请求，并根据请求路径从数据库中查出当前资源路径需要哪些权限才能访问，然后将查出的需要的权限列表交给AccessDecisionManager去处理后续逻辑。
 * 
 * 2. AccessDecisionManager 权限的验证(权限决策)
 * 实现一个AccessDecisionManager（访问决策管理器），当前的访问是否能通过权限验证.
 * 有了权限资源，知道了当前访问的url需要的具体权限，接下来就是决策当前的访问是否能通过权限验证了。
 * 在里面我们对当前请求的资源进行权限判断，判断当前登录用户是否拥有该权限，如果有就放行，如果没有就抛出一个"权限不足"的异常。
 * 
 * 3.注入自定义(2个)的实现类.(以下二种方法,选一种)
 * 3..1 方法一. (本例使用方法一)
 * 编写一个拦截器，增加到Spring security默认的拦截器链中，以达到拦截的目的。
 * 
 * 3.2 方法二.
 * 在SecurityConfig 中,使用 withObjectPostProcessor 注入自定义的2个实现类.
 * 
 * @author algz
 *
 */
@EnableWebSecurity
public class ALGZSecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AUserRepository auserDao;
	
    //匿名用户访问无权限资源时的异常
    @Autowired
    private ALGZAuthenticationEntryPoint authenticationEntryPoint;
	
    //权限拒绝处理逻辑
    @Autowired
    private ALGZAccessDeniedHandler accessDeniedHandler;
    
    //登录成功处理逻辑
    @Autowired
    private ALGZAuthenticationSuccessHandler authenticationSuccessHandler;

    //登录失败处理逻辑
    @Autowired
    private ALGZAuthenticationFailureHandler authenticationFailureHandler;
    
    //会话失效(账号被挤下线)处理逻辑
    @Autowired
    private ALGZSessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    //登出成功处理逻辑
    @Autowired
    private ALGZLogoutSuccessHandler logoutSuccessHandler;
    
//    //访问决策管理器
//    @Autowired
//    private ALGZAccessDecisionManager accessDecisionManager;
//
//    //实现权限拦截
//    @Autowired
//    private ALGZFilterInvocationSecurityMetadataSource securityMetadataSource;
    
//    @Autowired
//    private ALGZAbstractSecurityInterceptor securityInterceptor;
    
	/**
	 * 通过重载,配置 user-detail 服务
	 */
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(new AUserDetailsService(auserDao))
			//spring security 密码编码方式,必须设置,不然报错.
			.passwordEncoder(NoOpPasswordEncoder.getInstance());//密码不加密
//			.passwordEncoder(new BCryptPasswordEncoder());//采用 spring推荐的加密算法: BCryptPasswordEncoder;
	}

	/**
	 * 通过重载,配置 Spring Security 的 Filter 链
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
//		super.configure(web);
		web.ignoring().antMatchers("/webservice**","/health","/hystrix.stream","/open/**");
//		web.ignoring().antMatchers(antPatterns)
	}

	/**
	 * 通过重载,配置如何通过拦截器保护http请求
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
//		    .csrf(csrf -> csrf.disable())//关闭CSRF
			.csrf(csrf->csrf.requireCsrfProtectionMatcher(new SimpleCsrfSecurityRequestMatcher()))
//			.ignoringAntMatchers("/upload**").and()//实际设置了没用,因为采用白名单机制,所有权限都以数据库设置的才有效.
		    
		   .authorizeRequests()
//		   .anyRequest().permitAll()
            .and()
//            .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                @Override
//                public <O extends FilterSecurityInterceptor> O postProcess(O o) {
//                    o.setAccessDecisionManager(accessDecisionManager);//访问决策管理器
//                    o.setSecurityMetadataSource(securityMetadataSource);//安全元数据源
//                    return o;
//                }
//            })
          //登出
                .logout().
                
                permitAll().//允许所有用户
                logoutSuccessHandler(logoutSuccessHandler).//登出成功处理逻辑
                deleteCookies("JSESSIONID")//登出之后删除cookie
            //登入
            .and().formLogin().
//	            loginPage("/login")
//	            .loginProcessingUrl("/doLogin")
//	            // 指定登录页面及成功后跳转页面
//	            .successForwardUrl("/loadIndex")
//	            .and().authorizeRequests()
//	            .antMatchers(commonProperties.getAllowList()).
            
                permitAll().//允许所有用户
                successHandler(authenticationSuccessHandler).//登录成功处理逻辑
                failureHandler(authenticationFailureHandler)//登录失败处理逻辑
            //异常处理(权限拒绝、登录失效等)
            .and().exceptionHandling().
                accessDeniedHandler(accessDeniedHandler)//权限拒绝处理逻辑
                //注入后,前后端后离,登陆不跳转到页面,以JSON格式输出.
//                authenticationEntryPoint(authenticationEntryPoint)//匿名用户访问无权限资源时的异常处理
            //会话管理
            .and().sessionManagement().
                maximumSessions(1).//同一账号同时登录最大用户数
                expiredSessionStrategy(sessionInformationExpiredStrategy);//会话失效(账号被挤下线)处理逻辑
//			http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);
	}

}
