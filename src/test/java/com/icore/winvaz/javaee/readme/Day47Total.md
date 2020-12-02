Maven
翻译为“专家”，“内行”
Maven是跨平台的项目管理工具。主要服务于基于Java平台的项目构建，依赖管理和项目信息管理。
什么是理想的项目构建？
高度自动化，跨平台，可重用的组件，标准化的
什么是依赖？为什么要进行依赖管理？
自动下载，统一依赖管理
有哪些项目信息？
项目名称描述等，开发人员信息，开发者信息等

-----------------------------
项目构建
清理-->编译-->测试-->报告-->打包-->部署

--------------------------------------
Maven安装目录分析

bin：含有mvn运行的脚本
boot：含有plexus-classworlds类加载器框架
conf：含有settings.xml配置文件
lib：含有Maven运行时所需要的java类库
LICENSE.txt, NOTICE.txt, README.txt针对Maven版本，第三方软件等简要介绍

---------------------------------
Maven核心概念
坐标
依赖管理
仓库管理
生命周期
插件和目标
聚合继承

Maven 坐标
什么是坐标？
在平面几何中坐标（x,y）可以标识平面中唯一的一点
Maven坐标主要组成
groupId：定义当前Maven项目隶属项目
artifactId：定义实际项目中的一个模块
version：定义当前项目的当前版本
packaging：定义该项目的打包方式
Maven为什么使用坐标？
Maven世界拥有大量构建，我们需要找一个用来唯一标识一个构建的统一规范
拥有了统一规范，就可以把查找工作交给机器

-----------------------------------
依赖管理-依赖范围：

依赖范围(Scope)     对于主代码classpath有效        对于测试代码有效        被打包，对于运行时classpath有效        例子
compile(默认)                Y                         Y                            Y                      log4j
test                        -                         Y                            -                      Junit
provided                    Y                         Y                            -                      Servlet-api
runtime                     -                         -                            Y                      JDBC Driver

------------------------------------------------------------------------------------------------------------------------
其中依赖范围scope 用来控制依赖和编译，测试，运行的classpath的关系. 主要的是三种依赖关系如下：
1.compile： 默认编译依赖范围。对于编译，测试，运行三种classpath都有效
2.test：测试依赖范围。只对于测试classpath有效
3.provided：已提供依赖范围。对于编译，测试的classpath都有效，但对于运行无效。因为由容器已经提供，例如servlet-api
4.runtime:运行时提供。例如:jdbc驱动

------------------------------
依赖管理：
如何进行依赖配置？
传递性依赖和可选依赖,排除依赖
http://mvnrepository.com/

Two中使用One
Three中使用Two
称Two是Three的直接依赖
称One是Three的间接依赖
C->B		B->A
C直接依赖B
C间接依赖A

--------------------------
依赖管理-依赖范围对传递依赖的影响：
列是第一直接依赖，行是第二间接依赖
A->B:第一直接依赖
B->C：第二直接依赖
A->C: 间接依赖的依赖范围受到第一直接依赖和第二直接依赖的影响，遵照于PPT的表格

            compile         test        provided        runtime
            
compile     compile          -              -           runtime
test        test             -              -           test
provided    provided         -          provided        provided
runtime     runtime          -              -           runtime

----------------------------------------------------------------------
如何进行依赖配置？
传递性依赖和可选依赖,排除依赖
<optional> true/false 是否向下传递

--------------------------------
如何进行依赖配置？
传递性依赖和可选依赖,排除依赖

<exclusions>
	<exclusion>
		所包含坐标
排除依赖包中所包含的依赖关系
不需要添加版本，直接类别排除

-------------------------------------
依赖冲突

如果直接与间接依赖中包含有同一个坐标不同版本的资源依赖，以直接依赖的版本为准（就近原则）
如果直接依赖中包含有同一个坐标不同版本的资源依赖，以配置顺序下方的版本为准（就近原则）

-------------------------------
Maven生命周期
何为生命周期？
Maven生命周期就是为了对所有的构建过程进行抽象和统一
包括项目清理，初始化，编译，打包，测试，部署等几乎所有构建步骤
Maven三大生命周期
clean：清理项目的
default：构建项目的
site：生成项目站点的
-----------------------------
package cn.icore.cxf1;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
/**
 * 在同一项目中调用
 * @author 王健
 * @version 1.0 2011-11-15
 */
public class Client {
	public static void main(String[] args) {
		ClientProxyFactoryBean bean = //创建客户端类
			new ClientProxyFactoryBean();
		//设置访问地址
		bean.setAddress("http://localhost:9999/one");
		//设置服务接口,直接使用本项目中的接口
		bean.setServiceClass(CxfOne.class);
		//通过create方法返回接口实例
		CxfOne s =   (CxfOne) bean.create();
		String str = s.sayHi();//调用
		System.err.println(str);
	}
}

-------------------------------------------
生命周期Maven有三套相互独立的生命周期，请注意这里说的是“三套”，而且“相互独立”，这三套生命周期分别是： 

Clean Lifecycle 在进行真正的构建之前进行一些清理工作。 
Default Lifecycle 构建的核心部分，编译，测试，打包，部署等等。 
Site Lifecycle 生成项目报告，站点，发布站点。 
再次强调一下它们是相互独立的，你可以仅仅调用clean来清理工作目录，仅仅调用site来生成站点。当然你也可以直接运行 mvn clean install site 运行所有这三套生命周期。

================================================
生命周期clean：
clean生命周期每套生命周期都由一组阶段(Phase)组成，我们平时在命令行输入的命令总会对应于一个特定的阶段。比如，运行mvn clean ，这个的clean是Clean生命周期的一个阶段。有Clean生命周期，也有clean阶段。Clean生命周期一共包含了三个阶段： 

pre-clean 执行一些需要在clean之前完成的工作 
clean 移除所有上一次构建生成的文件 
post-clean 执行一些需要在clean之后立刻完成的工作 
mvn clean 中的clean就是上面的clean，在一个生命周期中，运行某个阶段的时候，它之前的所有阶段都会被运行，也就是说，mvn clean 等同于 mvn pre-clean clean ，如果我们运行 mvn post-clean ，那么 pre-clean，clean 都会被运行。这是Maven很重要的一个规则，可以大大简化命令行的输入。

=====================================
生命周期default：
Default生命周期Default生命周期是Maven生命周期中最重要的一个，绝大部分工作都发生在这个生命周期中。这里，只解释一些比较重要和常用的阶段： 
validate 
generate-sources 
process-sources 
generate-resources 
process-resources 复制并处理资源文件，至目标目录，准备打包。 
compile 编译项目的源代码。 
process-classes 
generate-test-sources 
process-test-sources 
generate-test-resources 
process-test-resources 复制并处理资源文件，至目标测试目录。 
test-compile 编译测试源代码。 
process-test-classes 
test 使用合适的单元测试框架运行测试。这些测试代码不会被打包或部署。 
prepare-package 
package 接受编译好的代码，打包成可发布的格式，如 JAR 。 
pre-integration-test 
integration-test 
post-integration-test 
verify 
install 将包安装至本地仓库，以让其它项目依赖。 
deploy 将最终的包复制到远程的仓库，以让其它开发人员与项目共享。 
运行任何一个阶段的时候，它前面的所有阶段都会被运行，这也就是为什么我们运行mvn install 的时候，代码会被编译，测试，打包。此外，Maven的插件机制是完全依赖Maven的生命周期的，因此理解生命周期至关重要。 

===========================================
生命周期site：
Site生命周期pre-site 执行一些需要在生成站点文档之前完成的工作 
site 生成项目的站点文档 
post-site 执行一些需要在生成站点文档之后完成的工作，并且为部署做准备 
site-deploy 将生成的站点文档部署到特定的服务器上 
这里经常用到的是site阶段和site-deploy阶段，用以生成和发布Maven站点，这可是Maven相当强大的功能，Manager比较喜欢，文档及统计数据自动生成，很好看。 

======================================
插件目标
Maven的核心仅仅定义了抽象的生命周期，具体的任务都是交由插件完成的
每个插件都能实现多个功能，每个功能就是一个插件目标
Maven的生命周期与插件目标相互绑定，以完成某个具体的构建任务
例如compile就是插件maven-compiler-plugin的一个插件目标

------------------------------------------
<build>
    <!-- 自定义插件 -->
	<plugins>
	    <!-- 具体插件 -->
		<plugin>
		    <!-- 指定插件对应的GAV -->
     	 	<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-source-plugin</artifactId>
      		<version>2.2.1</version>
      		<! -- 指定插件的执行生命周期 -->
      		<executions>
      		    <! -- 具体的执行位置 -->
            	<execution>
					<goals>
						<goal>jar-no-fork</goal>
            		</goals>
            		<!-- verify:default生命周期的插件 -->
            		<phase>verify</phase>
				</execution>
    	    </executions>
  		</plugin>
	</plugins>
</build>

=====================================
=====================================
继承：
何为继承？
继承为了消除重复，我们把很多相同的配置提取出来
例如：grouptId，version等
父工程设置为被继承
<packaging>pom</packaging>
子工程继承父工程
省略父工程中定义的坐标除访问名称中的所有设定，添加继承父工程
<parent>
	<groupId>…</groupId>
	<artifactId>… </artifactId>
	<version>… </version>
	<relativePath>../父工程项目名</relativePath>
</parent>
-------------------------------------------
父工程统一管理子工程依赖版本
<dependencyManagement>	
	<dependencies>
		//添加公共依赖包
	</dependencies>
</dependencyManagement>
子工程仅仅添加依赖包，无需添加版本，版本由父工程继承而来
为了进一步便于管理，将所有的版本管理设置在一起，设置为系统属性值
<properties>
	<junit.version>4.9</junit.version>
	……
</properties>
引用使用${junit.version}格式进行，只能在依赖范围设置
----------------------------------------------
父工程统一管理子工程依赖关系
如果所有子工程都需要依赖某些包，父工程可以通过设置依赖，将依赖关系传递到子工程中
<dependencies>
	//添加公共依赖包
</dependencies>

================================================
聚合：
何为聚合？
如果我们想一次构建多个项目模块，那我们就需要对多个项目模块进行聚合
<modules>
	<module>../子项目名称1</module>
	<module>../子项目名称2</module>
	 <module>../子项目名称3</module>
</modules>

-----------------------------------
聚合与继承的关系
聚合主要为了快速构建项目
继承主要为了消除重复

===========================================
===========================================
Nexus私服安装

Nexus私服安装

第一步：下载nexus.war包，然后拷贝到tomcat下的webapps目录中
第二步：启动tomcat
第三步：访问http://localhost:8080/nexus/显示如下：
第四步：点击右上角“log in”	，输入username：admin 和Password：admin123登录
第五步：登录成功
第六步：点击Views/Repositories 中Repositories
 
Nexus内置仓库说明：
（1）Maven Central：该仓库代理Maven中央仓库，其策略为Release，因此只会下载和缓存中央仓库中的发布版本构件。
（2）Releases：这是一种策略为Release的宿主类型仓库，用来部署组织内部的发布版本构件。
（3）Snapshots：这是一个策略为Snapshot的宿主类型仓库，用来部署组织内部的快照版本构件。
（4）3rd party：这是一个策略为Release的宿主类型仓库，用来部署无法从公共仓库获得的第三方发布版本构件。
（5）Public Repositories：该仓库组将上述所有策略为Release的仓库聚合并通过一致的地址提供服务。
第七步：创建宿主目录和代理仓库
Hosted：本地仓库，通常我们会部署自己的构件到这一类型的仓库。 包括3rd party仓库，Releases仓库，Snapshots仓库
Proxy：代理仓库，它们被用来代理远程的公共仓库，如maven中央仓库。 
Group：仓库组，用来合并多个hosted/proxy仓库，通常我们配置maven依赖仓库组。 
第八步：创建仓库组
	点击Public Repositories仓库，在Configurations栏中选取需要合并的仓库,点击箭头加到左边保存即可
第九步：下载Index索引并进行构建搜索（GAV搜索）
第十步：配置所有构建均从私服下载，在~/.m2/setting.xml中配置如下：
<settings>
 <mirrors>
	 <mirror>
		 <!--此处配置所有的构建均从私有仓库中下载 *代表所有，也可以写central -->
		 <id>nexus</id>
		 <mirrorOf>*</mirrorOf>
		 <url>http://192.168.1.100:8000/nexus/content/groups/public</url>
	 </mirror>
 </mirrors>
 <profiles>
	 <profile>
		 <id>nexus</id>
		 <!—所有请求均通过镜像 -->
		 <repositories>
			 <repository>
				 <id>central</id>
				 <url>http://central</url>
				 <releases><enabled>true</enabled></releases>
			 	 <snapshots><enabled>true</enabled></snapshots>
			 </repository>
		 </repositories>
		 <pluginRepositories>
			 <pluginRepository>
				 <id>central</id>
				 <url>http://central</url>
				 <releases><enabled>true</enabled></releases>
				 <snapshots><enabled>true</enabled></snapshots>
			 </pluginRepository>
		 </pluginRepositories>
	 </profile>
 </profiles>
<activeProfiles>
 <!--make the profile active all the time -->
 <activeProfile>nexus</activeProfile>
 </activeProfiles>
第十一步：部署构建到Nexus，包含Release和Snapshot， 在项目根目录中pom.xml中配置：
<distributionManagement> 
	<repository> 
	    <id>releases</id> 
	    <name>Internal Releases</name> 
	    <url>http://localhost:8000/nexus/content/repositories/releases/</url> 
	</repository> 
	<snapshotRepository> 
	    <id>snapshots</id> 
	    <name>Internal Snapshots</name> 
	    <url>http://localhost:8000/nexus/content/repositories/snapshots/</url> 
	</snapshotRepository> 
  </distributionManagement>
第十二步：Nexus的访问权限控制，在~/m2/setting.xml中配置如下：
<!-- 设置发布时的用户名 -->
 <servers>
 	<server>
 		<id> releases </id>
        <username>admin</username>
        <password>admin123</password>
    </server>
    <server>
        <id> snapshots </id>
        <username>admin</username>
        <password>admin123</password>
    </server>
 </servers>







 


























