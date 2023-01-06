1.本项目开启了CRSF功能;

2.采用白名单权限机制,即数据库中(ALGZ_PERMISSION表)添加的数据(PERMISSIONNAME字段),进行访问权限控制,没有添加的默认为可访问. 所有安全配置在ALGZFilterInvocationSecurityMetadataSource.java .
	
3.自定义CXF webservice 访问路径
http://cxf.apache.org/docs/springboot.html
#自定义CXF webservice 访问路径: http://IP:端口/应用名/webservice/方法名?wsdl
cxf.path=/webservice

4.关闭CSRF
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();//关闭CSRF
	}

5.使用@Value 注解注入application.properties配置文件
(1)类必须添加@Component.
(2)静态属性或字段不能注入,但可以定义一个方法注入到参数中,在方法里对变量赋值.
	@Value("${algz.csrf.execludeUrls:}")//设置默认值为空字符串,否则没有在配置文件中定义会报异常.
	private void setExecludeUrls(String execludeUrlStr) {
		execludeUrls= new HashSet<>(Arrays.asList(execludeUrlStr.split(",")));
    }
    
6. @Controller接收数组类型
Controller接收数组类型时,客户端的ajax参数必须设置 traditional: true,否则获取不到数组数据.
 traditional,true 适用于servlet,  sql=1&sql=2; false,适用于PHP和Ruby on Rails,用于深度序列化 , sql[]=1& sql[]=1;
 客户端示例代码:
   	  $.ajax({
            type:'post',
            data:{sql : [1,2]},
             traditional: true,//必须指定为true
             ....
Controller示例代码:
@RequestMapping("executebatchnonquery")
public Integer ExecuteBatchNonQuery(String[] sql) 

7.客户端ajax示例
 $.ajax({
            type:'post',
            url: "http://localhost:8080/algz/common/sql/executebatchnonquery1",
             data :  {name: "2",cname :"2000"},
            success:function (res) {
            	alert(res);
            }
})

8.oracle ojdbc授权问题，需自行下载ojdbc8.jar再手动安装。
https://repo1.maven.org/maven2/com/oracle/database/jdbc/ojdbc8/

mvn install:install-file -Dfile=D:\ojdbc8.jar -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=12.2.0.1 -Dpackaging=jar -DgeneratePom=true

指令参数：
install:可以将项目本身编译并打包到本地仓库
install-file:安装文件
-Dfile=D:\ojdbc8.jar : 指定要打的包的文件位置
-DgroupId=com.oracle : 指定当前包的groupId为com.oracle
-DartifactId=ojdbc8 : 指定当前的artifactfactId为ojdbc8
-Dversion=12.2.0.1 : 指定当前包的版本为12.2.0.1
-DgeneratePom=true:是否生成pom文件
-DgroupId=包名
-DartifactId=项目名
-Dversion=版本号
-Dpackaging=jar
-Dfile=jar文件所在路径
pom文件：
<dependency>
            <groupId>com.oracle</groupId>
            <artifactId>ojdbc8</artifactId>
            <version>12.2.0.1</version>
        </dependency>

