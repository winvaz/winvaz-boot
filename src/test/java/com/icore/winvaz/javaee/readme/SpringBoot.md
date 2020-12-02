@Configuration注解表示这是一个配置类，在我们这里，这个配置的作用类似于applicationContext.xml
@ComponentScan注解表示配置包扫描，里边的属性和xml配置中的属性都是一一对应的，useDefaultFilters表示使用默认的过滤器，
然后又除去Controller注解，即在Spring容器中扫描除了Controller之外的其他所有Bean。
@EnableAutoConfiguration注解表示开启自动化配置。然后执行这里的main方法就可以启动一个SpringBoot工程了。

--------------------------------
在SpringBoot中，配置文件有两种不同的格式，一个是properties，另一个是yaml。
虽然properties文件比较常见，但是相对于properties而言，yaml更加简洁明了，而且使用的场景也更多，
很多开源项目都是使用yaml进行配置(例如Hexo)。除了简洁，yaml还有另外一个特点，就是yaml中的数据是有序的，properties中的数据是无序的
当我们创建一个SpringBoot工程时，默认resources目录下就有一个application.properties文件，
可以在application.properties文件中进行项目配置，但是这个文件并非唯一的配置文件，在SpringBoot中，
一共有4个地方可以存放application.properties文件。
1.当前项目根目录下的config目录下
2.当前项目的根目录下
3.resources目录下的config目录下
4.resources目录下
按如上顺序，四个配置文件的优先级依次降低。
通过spring.config.location属性来手动的指定配置文件位置，指定完成后，系统就会自动去指定目录下查找application.properties文件。
配置文件不叫application，也是可以的，但是，需要明确指定配置文件的文件名。方式和指定路径一致，只不过此时的key是spring.config.name。

------------------------------
是在Java配置中，可以通过@PropertySource来引入配置。@PropertySource("classpath:book.properties")
SpringBoot引入了类型安全的属性注入
引入@ConfigurationProperties(prefix="book")注解，并且配置了属性的前缀，此时会自动将Spring容器中对应的数据注入到对象对应的属性中

============================================
核心知识
其实Starter的核心就是条件注解@Conditional，当classpath下存在某一个Class时，某个配置才会生效

@Configuration注解表明这是一个配置类。
@EnableConfigurationProperties注解是使我们之前配置的@ConfigurationProperties生效，让配置的属性成功的进入Bean中。
@ConditionalOnClass表示当项目当前classpath下存在HelloService时，后面的配置才生效。自动配置类中首先注入HelloProperties，
这个实例中含有我们在application.properties中配置的相关数据。

@EnableAutoConfiguration表示启用Spring应用程序上下文的自动配置，该注解会自动导入一个名为AutoConfigurationImportSelector的类,
而这个类会去读取一个名为spring.factories的文件,spring.factories中则定义需要加载的自动化配置类

在Maven项目的resources目录下创建一个名为META-INF的文件夹，然后在文件夹中创建一个名为spring.factories的文件，文件内容如下:
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.icore.winvaz.autoconfigure.HelloServiceAutoConfiguration

-----------------------------
@Conditional注解，当@Conditional注解中配置的条件类的matches方法返回值为true时，对应的Bean就会生效
条件注解还有一个进化版，那就是Profile。我们一般利用Profile来实现在开发环境和生产环境之间进行快速切换。其实Profile就是利用条件注解来实现的。

============================================
https简介
Java自带的JDK管理工具keytool来生成一个免费的https证书。
进入到%JAVVA_HOME%\bin目录下，执行如下命令生成一个数字证书:
keytool-genkey-aliastomcathttps-keyalgRSA-keysize2048-keystoreD:\javaboy.p12-validity365
genkey表示要创建一个新的密钥。
alias表示keystore的别名。
keyalg表示使用的加密算法是RSA，一种非对称加密算法。keysize表示密钥的长度。
keystore表示生成的密钥存放位置。
validity表示密钥的有效时间，单位为天。

-------
引入https
key-store表示密钥文件名。
key-alias表示密钥别名。
key-store-password就是在cmd命令执行过程中输入的密码。

============================================
Thymeleaf简介
Thymeleaf是新一代Java模板引擎，它类似于Velocity、FreeMarker等传统Java模板引擎，但是与
传统Java模板引擎不同的是，Thymeleaf支持HTML原型。

SpringBoot整合thymeleaf(添加依赖即可spring-boot-starter-thymeleaf)，提供了一整套的自动化配置方案，
这一套配置类(ThymeleafAutoConfiguration)的属性在(ThymeleafProperties)类中

手动渲染
手动渲染Thymeleaf模板，这个一般在邮件发送时候有用
1.渲染时，我们需要首先注入一个TemplateEngine对象，这个对象就是在Thymeleaf的自动化配置类中配置的(即当我们引入Thymeleaf的依赖之后，这个实例就有了)。
2.然后构造一个Context对象用来存放变量。
3.调用process方法进行渲染，该方法的返回值就是渲染后的HTML字符串，然后我们将这个字符
串发送出去。

==========================================
Freemarker简介
这是一个相当老牌的开源的免费的模版引擎。Freemarker不是面向最终用户的，而是一个Java类库。可以将模版和数据渲染成HTML。
Freemarker模版后缀为.ftl(FreeMarker Template Language)

SpringBoot整合引入Freemarker依赖(spring-boot-starter-freemarker)，然后在FreeMarkerAutoConfiguration类中看到自动化配置。

SSM中的配置
<mvc:resources/>节点来配置不拦截静态资源
<mvc:resources mapping="/**" location="/"/>(/**表示可以匹配任意层级的路径)

SpringMVC在Java代码中配置，自定义一个类，继承自(WebMvcConfigurationSupport)即可

Spring Boot 中的配置
默认情况下，一共有5个位置可以放静态资源。
1. classpath:/META-INF/resources/
2. classpath:/resources/
3. classpath:/static/
4. classpath:/public/
5. /
前四个目录好理解，分别对应了resources目录下不同的目录，第5个/其实就是表示webapp目录(SpringBoot项目中默认是没有这个目录的，也可以自己添加，在使用JSP时)中的静态资源也不被拦截
如果同一个文件分 别出现在五个目录下，那么优先级也是按照上面列出的顺序。

源码解读
在WebMvcAutoConfiguration类中看到了SpringMVC自动化配置的相关的内容，找到了静态资源拦截的配置
getStaticPathPattern()方法对应的值是/**
getStaticLocations()方法返回了四个位置(对应上面四个)
getResourceLocations方法中，又添加了/(表示webapp目录)

自定义配置
spring.resources.static-locations=classpath:/(定义资源的位置)
spring.mvc.static-path-pattern=/**(定义请求URL规则)

代码定义
实现(WebMvcConfigurer)接口

注意：有很多人用了Thymeleaf之后，会将静态资源也放在resources/templates目录下。
注意，templates目录并不是静态资源目录，它是一个放页面模板的位置(你看到的Thymeleaf模板虽然后缀为 .html，其实并不是静态资源)

========================================
@ControllerAdvice(SpringMVC注解)
1. 全局异常处理
自定义异常类，添加该注解即可。
类中定义多个方法，不同方法处理不同的异常，通过@ExceptionHandler(Exception.class)注解标明异常的处理类型
2. 全局数据绑定
用来做一些初始化的数据操作，如公共的数据定义在添加了@ControllerAdvice注解的类中
使用@ModelAttribute注解标记该方法返回数据是一个全局的数据。默认情况下，这个全局数据的key为返回的变量名(name重新指定)，value为方法的返回值
3. 全局数据预处理
解决变量同名问题
1.给接口中的变量取别名，通过@ModelAttribute("b")注解实现
2.请求数据预处理，在@ControllerAdvice标记的类添加如下代码
@InitBinder("b")(表示该方法用来处理和book相关的参数，在方法中，给参数添加一个b前缀)
public void method(WebDataBinder binder) { binder.setFieldDefaultPrefix("b."); }

---------------------------------------------
可以使用Spring中@ControllerAdvice进行一场统一处理，也可以自己来定义异常处理方案。
Spring Boot中，对异常的处理有一些默认的策略。自定义error页面，一种是静态页面，一种是动态页面。

静态异常页面
1.使用HTTP响应码来命名页面，如404.html,500.html....
2.直接定义一个4xx.html，表示400-499的状态都显示这个异常页面
默认是在classpath:/static/error/路径下定义相关页面
如果异常展示页面既存在 5xx.html，也存在500.html ，此时，发生500异常时，优先展示500.html页面。

动态异常页面
定义方式和静态的基本一致，可以采用的页面模板有jsp,fremarker,thymeleaf。也支持404.html或者4xx.html
注意，动态页面模板，不需要开发者自己去定义控制器，直接定义异常页面即可 ，Spring Boot中自带的异常处理器会自动查找到异常页面。
静态页面和动态页面同事定义了异常处理页面，同时存在时，默认使用动态页面。

发生了500错误-->查找动态500.html页面-->查找静态500.html -->查找动态5xx.html-->查找静态5xx.html。

自定义异常数据
默认spring boot的所有异常都展示五条数据(path,error,message,timestamp,status)，这些定义在DefaultErrorAttribtes类的getErrorAttributes方法中
DefaultErrorAttributes 类本身则是在ErrorMvcAutoConfiguration异常自动配置类中定义的，如果开发者没有提供一个ErrorAttributes 的实例的话，
那么Spring Boot将自动提供一个ErrorAttributes的实例，也就是DefaultErrorAttributes 。
1.直接实现ErrorAttributes接口
2.继承DefaultErrorAttributes(推荐)，因对异常数据的处理已经完成，可以直接使用。
定义好的ErrorAttributes一定要注册成一个Bean ，这样，Spring Boot就不会使用默认的DefaultErrorAttributes 了

自定义异常视图
异常视图默认就是前面所说的静态或者动态页面，默认的异常视图加载逻辑在BasicErrorController类的errorHtml方法中，
这个方法用来返回异常页面+数据，还有另外一个 error 方法，这个方法用来返回 异常数据(如果是 ajax 请求，则该方法会被触发)。
resolveErrorView方法会来到DefaultErrorViewResolver类的resolveErrorView方法
开发者提供了自己的ErrorViewResolver实例后，默认的配置就会失效。

===========================================================
同源策略
同源策略是由Netscape提出的一个著名的安全策略，它是浏览器最核心也最基本的安全功能。同源是指协议，域名以及端口要相同。传统的跨域方案是JSONP(只支持GET请求)
CORS(跨域源资源共享)提供了Web服务从不同网域传来沙盒脚本的方法，以避开浏览器的同源策略，是JSONP模式的现代版。

Spring Boot实现CORS
1.使用@CrossOrigin注解配置某一个方法接受某一个域的请求。(每个方法或Controller加注解太麻烦)
2.全局配置，重写Spring MVC配置类(WebMvcConfiguer)的addCorsMapping()方法。(/** 表示本应用的所有方法都会去处理跨域请求，allowedMethods 表示允许通过的请求数， allowedHeaders 则表示允许的请求头)

存在的问题
1.CSRF(跨站请求伪造)，挟制用户当前已登录的Web应用程序上执行非本意的操作的攻击方法。
浏览器对请求进行分类
1.简单请求
2.预先请求(先发送一个options探测请求，和浏览器进行协商是否接受请求)
3.带凭证请求(默认情况跨域请求是不需要凭证的，但是服务端配置要求客户端提供凭证，有效避免CSRF攻击)

在Servlet/JSP项目中，如果涉及到系统任务，在项目启动阶段做一些数据初始化操作，容易想到web基础中的三大组件(Servlet，Filter，Litener)之一的Listener
在Spring Boot中，使用更为简便的方式解决系统启动任务。分别是CommandLineRunner和ApplicationRunner，分别来看。

1.CommandLineRunner
自定义类实现CommandLineRunner接口
@Component(注册为Spring容器中的一个Bean)
@Order(100)(启动任务的执行优先级，数字越小，优先级越大)
void run(String... args)(在run方法中写任务的核心逻辑，当项目启动时，run方法会被自动执行)(run方法的参数，来自于项目的启动参数，参数传递：1.IDEA方式配置。2.项目打包，命令行输入)

2.ApplicationRunner
自定义类实现ApplicationRunner接口
和上功能一致，区别在于参数处理上。可以接受更多类型的参数
void run(ApplicationArguments args)(参数传递：1.IDEA。2.--key=value)
1.args.getNonOptionArgs();可以用来获取命令行中的无key参数
2.args.getOptionNames();可以用来获取所有key/value形式的参数的key。
3.args.getOptionValues(key));可以根据key获取key/value 形式的参数的value。 
4.args.getSourceArgs(); 则表示获取命令行中的所有参数。

======================================
定时任务
1.使用Spring自带的定时任务处理器@Scheduled注解。
2.使用第三方框架Quartz。


=====================================
=====================================
Spring Boot自定义Spring MVC配置
自定义SpringMVC相关的类和注解主要有如下四个:
1.WebMvcConfigurerAdapter
这个是在Spring Boot 1.x中我们自定义SpringMVC时继承的一个抽象类，这个抽象类本身是实现了WebMvcConfigurer 接口，然后抽象类里边都是空方法
这个类的注释:从Spring5开始，由于我们要使用Java8，而Java8中的接口允许存在default方法，因此官方建议我们直接实现WebMvcConfigurer接口，而不是 继承 WebMvcConfigurerAdapter 。
也就是说，在Spring Boot 1.x 的时代，如果我们需要自定义 SpringMVC 配置，直接继承 WebMvcConfigurerAdapter 类即可。
-----------------------------
2.WebMvcConfigurer
WebMvcConfigurer是我们在Spring Boot 2.x中实现自定义配置的方案。
WebMvcConfigurer 是一个接口，接口中的方法和 WebMvcConfigurerAdapter 中定义的空方法其实 一样，所以用法上来说，基本上没有差别，从 Spring Boot 1.x 切换到 Spring Boot 2.x ，只需要把继承 类改成实现接口即可。
--------------------------------
3.WebMvcConfigurationSupport
放弃Spring和SpringMVC的xml配置文件，转而用Java代替这两个xml配置。就是通过继承WebMvcConfigurationSupport类来实现的。在WebMvcConfigurationSupport类中，提供了用Java配置SpringMVC所需要的所有方法。
继承这类的操作一般只在Java配置的SSM项目中使用，在Spring Boot中基本不会这么写。Spring Boot 中，SpringMVC 相关的自动化配置是在 WebMvcAutoConfiguration 配置 类中实现的
我们从这个类的注解中可以看到，它的生效条件有一条，就是当不存在 WebMvcConfigurationSupport 的实例时，这个自动化配置才会生生效。因此，如果我们在 Spring Boot 中自定义 SpringMVC 配置时选择了继承 WebMvcConfigurationSupport，就会导致 Spring Boot 中 SpringMVC 的自动化配置失效。
Spring Boot 给我们提供了很多自动化配置，很多时候当我们修改这些配置的时候，并不是要全盘否定
Spring Boot 提供的自动化配置，我们可能只是针对某一个配置做出修改，其他的配置还是按照
Spring Boot 默认的自动化配置来，而继承 WebMvcConfigurationSupport 来实现对 SpringMVC 的配置会导致所有的 SpringMVC 自动化配置失效，因此，一般情况下我们不选择这种方案。
----------------------------
4.@EnableWebMvc
最后还有一个 @EnableWebMvc 注解，这个注解很好理解，它的作用就是启用WebMvcConfigurationSupport。
可以看到，加了这个注解，就会自动导入WebMvcConfigurationSupport，所以在Spring Boot 中， 我们也不建议使用 @EnableWebMvc 注解，因为它一样会导致 Spring Boot 中的 SpringMVC 自动化配 置失效。

总结
1. Spring Boot 1.x 中，自定义 SpringMVC 配置可以通过继承 WebMvcConfigurerAdapter 来实 现。
2. Spring Boot 2.x 中，自定义 SpringMVC 配置可以通过实现 WebMvcConfigurer 接口来完成。
3. 如果在 Spring Boot 中使用继承 WebMvcConfigurationSupport 来实现自定义 SpringMVC 配
置，或者在 Spring Boot 中使用了 @EnableWebMvc 注解，都会导致 Spring Boot 中默认的SpringMVC 自动化配置失效。
4. 在纯 Java 配置的 SSM 环境中，如果我们要自定义 SpringMVC 配置，有两种办法，
第一种就是直接继承自 WebMvcConfigurationSupport 来完成 SpringMVC 配置，
还有一种方案就是实现 WebMvcConfigurer 接口来完成自定义 SpringMVC 配置，
如果使用第二种方式，则需要给 SpringMVC 的配置类上额外添加 @EnableWebMvc 注解，表示启用 WebMvcConfigurationSupport，这样配置才会生效。换句话说，在纯 Java 配置的 SSM 中
如果你需要自定义 SpringMVC 配置，你离不开 WebMvcConfigurationSupport ，所以在这种情况 下建议通过继承 WebMvcConfigurationSupport 来实现自动化配置。

=================================================
=================================================
Spring Boot整合MyBatis
引入依赖：mybatis-spring-boot-starter、druid-spring-boot-starter、mysql-connector-java
在application.properties配置基本信息(datasource.url/username/password/type)
@SelectKey 注解可以实现主键回填的功能，即当数据插入成功后，插入成功的数据 id 会赋值 到 user 对象的id 属性上。
配置 mapper 扫描
1.一种直接在mapper接口上添加@Mapper注解，此弊端就是所有的 Mapper 都要手动添加。
2.在启动类上添加 Mapper 扫描(@MapperScan(basePackages = "org.javaboy.mybatis.mapper"))
-------------------------------------
mapper 映射
开发者也可以在 XML 中写 SQL

xml文件放置位置
1.第一种和mapper接口放在一起。
ava 目录 下的 xml 资源在项目打包时会被忽略掉，所以，如果 UserMapper.xml 放在包下，需要在 pom.xml 文 件中再添加如下配置，避免打包时 java 目录下的 XML 文件被自动忽略掉
2.第二种放到resources目录下
直接放在 resources 目录下，这样就不用担心打包时被忽略了，但是放 在 resources 目录下，必须创建和 Mapper 接口包目录相同的目录层级，这样才能确保打包后 XML 和 Mapper 接口又处于在一起，否则 XML 文件将不能被自动扫描，这个时候就需要添加额外配置。
此时在 application.properties 中告诉 mybatis 去哪里扫描 mapper:

多数据源配置
首先在 application.properties 中配置数据库基本信息，然后提供两个 DataSource 即可
MyBatis 配置
要提供两个Bean(SqlSessionFactory和SqlSessionTemplate)
创建 MyBatisConfigOne 类，首先指明该类是一个配置类，配置类中要扫描的包是 org.javaboy.mybatis.mapper1 ，即该包下的 Mapper 接口将操作 dsOne 中的数据，
对应的 SqlSessionFactory 和 SqlSessionTemplate 分别是 sqlSessionFactory1 和 sqlSessionTemplate1，在 MyBatisConfigOne 内部，
分别提供 SqlSessionFactory 和 SqlSessionTemplate 即可， SqlSessionFactory 根据 dsOne 创建，然后再根据创建好的SqlSessionFactory 
创建一个 SqlSessionTemplate。

==============================================
==============================================
Spring Redis
使用Java操作Redis。1.Jedis。2.Spring Data Redis
在传统的 SSM 中，需要开发者自己来配置 Spring Data Redis ，这个配置比较繁琐，主要配置 3 个东
西:连接池、连接器信息以及 key 和 value 的序列化方案。
在 Spring Boot 中，默认集成的 Redis 就是 Spring Data Redis，默认底层的连接池使用了 lettuce ，开
发者可以自行修改为自己的熟悉的，例如 Jedis。
Spring Data Redis 针对 Redis 提供了非常方便的操作模板 RedisTemplate 。这是 Spring Data 擅长的
事情，那么接下来我们就来看看 Spring Boot 中 Spring Data Redis 的具体用法。

配置Redis信息
一方面是 Redis 的基本信息，另一方面则是连接池信息

自动配置
引入了 Spring Data Redis ，并且配置了Redis的基本信息，此时，自动化配置就会生效。
RedisAutoConfiguration配置类
首先标记这个是一个配置类，同时该配置在 RedisOperations 存在的情况下才会生效(即项目中引 入了 Spring Data Redis)
2. 然后导入在 application.properties 中配置的属性
3. 然后再导入连接池信息(如果存在的话)
4. 最后，提供了两个 Bean ，RedisTemplate 和 StringRedisTemplate ，其中 StringRedisTemplate
是 RedisTemplate 的子类，两个的方法基本一致，不同之处主要体现在操作的数据类型不同， RedisTemplate 中的两个泛型都是 Object ，意味者存储的 key 和 value 都可以是一个对象，而 StringRedisTemplate 的 两个泛型都是 String ，意味者 StringRedisTemplate 的 key 和 value 都 只能是字符串。如果开发者没有提供相关的 Bean ，这两个配置就会生效，否则不会生效。

使用
注入 StringRedisTemplate 或者 RedisTemplate 来使用
1. 针对 key 的操作，相关的方法就在 RedisTemplate 中
2. 针对具体数据类型的操作，相关的方法需要首先获取对应的数据类型，获取相应数据类型的操作方法是 opsForXXX 调用该方法就可以将数据存储到Redis中去了
RedisTemplate对key的默认序列化方案是JdkSerializationRedisSerializer(可设置)
StringRedisTemplate对key的默认序列化方案是StringRedisSerializer(不会有前缀)

==============================================
==============================================
Nginx
Nginx是一个高性能的HTTP和反向代理web服务器，同时也提供了IMAP/POP3/SMTP服务。
Nginx特点是占有内存少，并发能力强。

负载均衡服务器
就是进行请求转发，降低某一个服务器的压力。对于一些大型网站基本上从DNS就开始负债均衡策略。

反向代理服务器

----------------------------------------
分布式Session共享
将各个服务之间需要共享的数据，保存到一个公共的地方(主流的方案是Redis)
Spring Session简化重复的写入和读取数据。Spring Session 就是使用 Spring 中的代理 过滤器，将所有的 Session 操作拦截下来，自动的将数据 同步到 Redis 中，或者自动的从 Redis 中读 取数据。

引入Nginx
进入Nginx的安装目录的conf目录下修改nginx.conf文件
upstream winvaz.boot {
    server 127.0.0.1:8080 weight=1;
    server 127.0.0.1:8081 weight=2;
}

server {
    location / {
        proxy_pass http://winvaz.boot;
        proxy_redirect default;
    }
}
1. upstream 表示配置上游服务器
2. javaboy.org 表示服务器集群的名字，这个可以随意取名字
3. upstream 里边配置的是一个个的单独服务
4. weight 表示服务的权重，意味者将有多少比例的请求从 Nginx 上转发到该服务上
5. location 中的 proxy_pass 表示请求转发的地址， / 表示拦截到所有的请求，转发转发到刚刚配置
好的服务集群中
6. proxy_redirect 表示设置当发生重定向请求时，nginx 自动修正响应头数据(默认是 Tomcat 返回
重定向，此时重定向的地址是 Tomcat 的地址，我们需要将之修改使之成为 Nginx 的地址)。

在Linux启动两个Spring Boot实例
nohup java -jar sessionshare-0.0.1-SNAPSHOT.jar --server.port=8080 &

nohup 表示当终端关闭时，Spring Boot 不要停止运行
& 表示让 Spring Boot 在后台启动

=============================================
=============================================
Spring Boot Cache

引入依赖spring-boot-starter-cache

基本配置(除了Redis还有Cache)
spring.cache.cache-names=c1

在启动类开启缓存@EnableCaching之后Spring Boot自动配置一个RedisCacheManager(RedisCacheConfigurationl类)

缓存使用
@CacheConfig(cacheNames = "c1)
这个注解在类上使用，用来描述该类中所有方法使用的缓存名称，当然也可以不使用该注解，直接在具 体的缓存注解上配置名称

@Cacheable(key = "#id")
这个注解一般加在查询方法上，表示将一个方法的返回值缓存起来，默认情况下，缓存的 key 就是方法 的参数，缓存的 value 就是方法的返回值

当有多个参数时，默认就使用多个参数来做 key，如果只需要其中某一个参数做 key，则可以在 @Cacheable 注解中，通过 key 属性来指定 key，
如上代码就表示只使用 id 作为缓存的 key，如果对 key 有复杂的要求，可以自定义 keyGenerator。当然，Spring Cache 中提供了 root 对象，
可以在不定 义 keyGenerator 的情况下实现一些复杂的效果

    属性          描述                      示例
methodName      当前放名                #root.methodName
method          当前方法                #root.method.name
target          当前被调用的对象         #root.target
targetClass     当前被调用的对象的class  #root.targetClass
args            当前方法参数数组         #root.args[0]
caches          当前被调用的方法使用的Cache #root.caches[0].name

@CachePut(key = "#user.id")
这个注解一般加在更新方法上，当数据库中的数据更新后，缓存中的数据也要跟着更新，使用该注解， 可以将方法的返回值自动更新到已经存在的 key 上

@CacheEvict()
这个注解一般加在删除方法上，当数据库中的数据删除后，相关的缓存数据也要自动清除，该注解在使 用的时候也可以配置按照某种条件删除(condition 属性)或者或者配置清除所有缓存(allEntries 属 性)

总结
在 Spring Boot 中，使用 Redis 缓存，既可以使用 RedisTemplate 自己来实现，也可以使用使用这种 方式，这种方式是 Spring Cache 提供的统一接口，
实现既可以是 Redis，也可以是 Ehcache 或者其他 支持这种规范的缓存框架。从这个角度来说，Spring Cache 和 Redis、Ehcache 的关系就像 JDBC 与各 种数据库驱动的关系。

========================================
========================================
Spring Boot Ehcache

引入依赖Ehcache

添加ehcache配置文件ehcache.xml
配置含义:
公众号:江南一点雨
1. name:缓存名称。
2. maxElementsInMemory:缓存最大个数。
3. eternal:对象是否永久有效，一但设置了，timeout将不起作用。
4. timeToIdleSeconds:设置对象在失效前的允许闲置时间(单位:秒)。仅当eternal=false对象不
是永久有效时使用，可选属性，默认值是0，也就是可闲置时间无穷大。
5. timeToLiveSeconds:设置对象在失效前允许存活时间(单位:秒)。最大时间介于创建时间和失
效时间之间。仅当eternal=false对象不是永久有效时使用，默认是0.，也就是对象存活时间无穷
大。
6. overflowToDisk:当内存中对象数量达到maxElementsInMemory时，Ehcache将会对象写到磁
盘中。
7. diskSpoolBufferSizeMB:这个参数设置DiskStore(磁盘缓存)的缓存区大小。默认是30MB。每
个Cache都应该有自己的一个缓冲区。
8. maxElementsOnDisk:硬盘最大缓存个数。
9. diskPersistent:是否缓存虚拟机重启期数据。
10. diskExpiryThreadIntervalSeconds:磁盘失效线程运行时间间隔，默认是120秒。
11. memoryStoreEvictionPolicy:当达到maxElementsInMemory限制时，Ehcache将会根据指定的
策略去清理内存。默认策略是LRU(最近最少使用)。你可以设置为FIFO(先进先出)或是
LFU(较少使用)。
12. clearOnFlush:内存数量最大时是否清除。
13. diskStore 则表示临时缓存的硬盘目录。

注意：文件名默认ehcache.xml，如果更改需要在配置文件中配置(spring.cache.ehcache.config=classpath:aaa.xml)
                                    
开启缓存(在启动类添加@EnableCaching)

使用缓存(和上面的缓存注解一致)

========================================
========================================
RESTful

===========================================
===========================================
Spring Boot Security
Spring Security 是 Spring 家族中的一个安全管理框架
常见的安全管理技术栈
SSM + Shiro
Spring Boot/Spring Cloud + Spring Security
注意，这只是一个推荐的组合而已，如果单纯从技术上来说，无论怎么组合，都是可以运行的。

引入依赖(spring-boot-starter-security)

初次体验
随便访问一个接口，服务器会返回302响应码，让客户端重定向到/login页面，用户在/login页面登录，登录成功之后，就会自动跳转到奥/hello接口。
也可以使用 POSTMAN 来发送请求，使用 POSTMAN 发送请求时，可以将用户信息放在请求头中 (这样可以避免重定向到登录页面)
Spring Security 支持两种不同的认证方式:
可以通过 form 表单来认证 
可以通过 HttpBasic 来认证

用户名配置
默认情况下，登录的用户名是 user ，密码则是项目启动时随机生成的字符串，可以从启动的控制台日志中看到默认密码
这个随机生成的密码，每次启动时都会变。对登录的用户名/密码进行配置，有三种不同的方式:
1.application.properties 中进行配置
spring.security.user.name=winvaz
spring.security.user.password=123

2.Java 代码配置在内存中
创建一个Spring Security的配置类，继承自WebSecurityConfigurerAdapter类

3.Java 从数据库中加载





