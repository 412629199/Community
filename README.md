##项目-我的idea-springboot

##资料
https://spring.io
https://spring.io/guides/gs/serving-web-content/
https://github.com/412629199/Community
##工具
https://git-scm.com/

##问题以及解决方法

在templates中写的页面使用idea提示的浏览器访问没有问题但是idea中
springboot运行起来之后访问会丢失样式。
问题分析：出现这个问题是springboot在启动的时候没有带上static
下的js和css文件一起跑
解决方案：在springboot自身带有的那个(项目名+Application)类中继
承WebMvcConfigurationSupport重写里面的addResourceHandlers方法在
这里可以理解为配置在项目启动时扫描static下面的所有文件，也就是
告诉springboot在启动的时候带上他们全部一起跑。
附：
/*
    Application文件路径发生变化，要重新配置Bean组件的扫描信息
 */
@SpringBootApplication
public class DemoJdbcApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(DemoJdbcApplication.class, args);
    }

    //这里配置静态资源文件的路径导包都是默认的直接导入就可以
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        super.addResourceHandlers(registry);
    }
}
##jquery的引用一定要放在js引用之前
  

