监听器

1. 被监听者：小偷（事件源）
2. 监听者：警察
3. 监听器：抓捕
4. 事件：偷东西

---------------------

JavaWeb中八大监听器

1. 监听器分类
  * 事件源（被监听者）
    > ServletRequest
      * 生命监听
        > 生
	    > 死
      * 属性监听
        > 添加属性
	    > 替换属性
	    > 移除属性
    > HttpSession
      * 生命监听
        > 生
	    > 死
      * 属性监听
        > 添加属性
	    > 替换属性
	    > 移除属性
    > ServletContext
      * 生命监听
        > 生
	    > 死
      * 属性监听
        > 添加属性
	    > 替换属性
	    > 移除属性

    > HttpSession（感知监听）
      * 绑定解绑监听
      * 钝化活化监听

---------------------

1. 生死监听（生命周期监听器）
  * ServletRequestListener
    > ServletRequestEvent
      * ServletRequest getServletRequest();
      * ServletContext getServletContext();
  * HttpSessionListener
    > HttpSessionEvent
      * HttpSession getSession();
  * ServletContextListener
    > ServletContextEvent
      * ServletContext getServletContext()

2. 属性监听
  * ServletRequestAttributeListener
    > ServletRequestAttributeEvent
      * String getName()：获取属性的名称
      * Object getValue()：获取属性的值
      * ServletContext getServletContext()
      * ServletRequest getServletRequest();
  * HttpSessionAttributeListener
    > HttpSessionbindingEvent
      * String getName()：获取属性的名称
      * Object getValue()：获取属性的值
      * HttpSession getSession();
  * ServletContextAttributeListener
    > ServletContextAttributeEvent
      * String getName()：获取属性的名称
      * Object getValue()：获取属性的值
      * ServletContext getServletContext()

3. session的感知监听0
  * HttpSessionBindingListener
  * HttpSessionActivationListener

---------------------

ServletContextListener使用的步骤
--------------------------------------
  * 编写一个接口实现类
  
  package cn.icore.web.listener;
  
  import javax.servlet.ServletContextEvent;
  import javax.servlet.ServletContextListener;
  
  /**
   * ServletContext生命周期监听器
   * @author cuixifan
   *
   */
  public class MyServletContextListener implements ServletContextListener {
  	/**
  	 * 在ServletContext对象在被销毁之前，这个方法会被调用
  	 */
  	public void contextDestroyed(ServletContextEvent evt) {
  		System.out.println("contextDestroyed()...");
  	}
  	
  	/**
  	 * 在ServletContext(与天地同寿(随着服务器的创建而创建，随着服务器的关闭和销毁))对象被创建之后，马上调用
  	 */
  	public void contextInitialized(ServletContextEvent evt) {
  		System.out.println("contextInitialized()...");
  	}
  }
-------------------------------------
package cn.icore.web.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * ServletContext属性监听器
 * @author cuixifan
 *
 */
public class MyServletContextAttributeListener implements
		ServletContextAttributeListener {
	public void attributeAdded(ServletContextAttributeEvent evt) {
		String name = evt.getName();
		Object value = evt.getValue();
		
		System.out.println(name + "=" + value);
	}

	public void attributeRemoved(ServletContextAttributeEvent evt) {
		String name = evt.getName();
		Object value = evt.getValue();
		
		System.out.println(name + "=" + value);
	}

	public void attributeReplaced(ServletContextAttributeEvent evt) {
		String name = evt.getName();
		Object value = evt.getValue();
		
		System.out.println(name + "=" + value);
	}
}
-------------------------------------
  * 在web.xml中进行配置
  <listener>
  	<listener-class>cn.icore.web.listener.MyServletContextListener</listener-class>
  </listener>

---------------------
session的感知监听器
  * 它们不是添加给session的，而是添加给存放到session中的javabean的！！！
  * 无需在web.xml中进行配置！！！
  * 缺点：让普通的javabean依赖javaweb环境！


1. HttpSessionBindingListener
  * 例如让User类去实现HttpSessionBindingListener接口，那么User就有了感知能力！
    > User知道自己被添加到session中！
    > User知道自己被session无情的抛弃了！


---------------------

2. HttpSessionActivationListener

  * session序列化和反序列化：
    > 在关闭tomcat时，所有的session都会保存到一个名为SESSIONS.ser的文件中！
    > 在启动Tomcat时，Tomcat会把SESSIONS.ser文件中的内容读走，用来恢复session！
    > 保存到session中的数据，如果没有实现Serializable接口，那么就不会序列化到硬盘上，也就无法恢复！！！
  * 钝化和活化
    > 钝化：session一段时间没有使用，那么保存到硬盘上
    > 活化：已被钝化的session，又活动了，那么需要先把session众硬盘上移置到内存中
<Context>
	<Manager className="org.apache.catalina.session.PersistentManager" maxIdleSwap="1" >
		<Store className="org.apache.catalina.session.FileStore" directory="mysession" />
	</Manager>
</Context>
    > 钝化文件：
      * 一个session一个文件：sessionid为名称，扩展名为session！
      * 活化时，只会把文件内容读取走，不会删除钝化文件！

---------------------

HttpSessionActivationListener
  * 有两个方法，一个在session钝化时被调用，一个是session在活化时被调用
  * 让javabean去实现这个接口，就有感知钝化和活化的能力了！！！

Session序列化(不让session保存到Tomcat目录下)
Tomcat\conf\context.xml
<Manager pathname=""/>
自动重启
<Context reloadable="true"></Context>
=========================================
=========================================
=========================================

国际化

1. 什么叫国际化
  * 我们的网站，中国人打开，看到的是中文；英国人打开，看到的是英文！

2. Locale
  * 它表示语言和国家
  * new Locale("zh", "CN")
  * new Locale("en", "US")
  
  * Locale.US --> 表示老美的Locale
  * Locale.getDefault() --> 获取默认的Locale（中国的呗）

4. ResourseBundle
  * 用来获取资源文件上的信息
  * 资源文件的命名：基本名称+Locale部分+properties！
    > 基本名称都相同，只有Locale部分不同！
  * 用法：
    > 得到ResourceBundle的实例；ResourceBundle rb = ResourceBundle.getBundle("res", Locale.getDefault());
      * 第一个参数：String baseName，表示文件的基本名称
      * 第二个参数：Locale locale，表示语言和国家
    > String username = rb.getString("username")：参数为资源key，返回值！

   * 查找资源文件的顺序：
     * 当Locale为中文中国时
       > res_zh_CN.properties
       > res_zh.properties
       > res.properties
     * 当Locale为英文美国时（当找不到前两个文件时，那么会使用默认locale进行查找）
       > res_en_US.properties
       > res_en.properties
       > res_zh_CN.properties
       > res_zh.properties
       > res.properties

----------------------------------------

JavaWeb中的国际化

1. 请求中的Locale
  HttpServletReqeust： Locale getLocale()，获取浏览器支持的Locale

