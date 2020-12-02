泛型

1. 泛型的继承或实现
  * 在父类中，得到子类传递的泛型参数！

class A<T> {
  //在这里获取子类传递的泛型参数，例如：String或Integer
}

class AA1 extends A<String> {
}

class AA2 extends A<Integer> {
}



		/*
		 * 获取子类传递给父类的泛型参数，即：得到String或Integer
		 * 1. 先得到子类的类型，即Class
		 * 2. 通过Class得到参数化类型，即包含泛型的类型，即A<String>类型
		 * 3. 通过参数化类型，得到泛型参数！
		 */
//		Class c = this.getClass();// 得到子类的类型
//		Type type = c.getGenericSuperclass();//得到子类传递的完整参数化类型
//		ParameterizedType pType = (ParameterizedType) type;//还需要强转成参数化类型
//		Type[] types = pType.getActualTypeArguments();//得到真实的类型参数们
//		Class clazz = (Class)types[0];//得到第一个类型参数
//		System.out.println(clazz.getName());

===========================================
===========================================
===========================================
注解

1. 什么是注解(充当的是配置文件的作用)
  * 它就是注解： @Test
  * 注解的真正作用：
    > 它可以替代xml的配置文件功能！
    > 注解不能完全替代xml，有很多时候都是xml和注解一起使用！

2. java中的注解
  * @Overrid：作用在方法上的注解。当方法不是重写父类的方法时会报错（javase6.0之后，它还可以检查是否在重复接口中的方法）；
  * @Deprecated：作用在方法上。标记该方法为作废方法（已过时）；
  * @SuppressWarnings：作用在方法上，压制警告。

3. 自定义注解
  * 需要学习两部分
    > 自定义注解类：public @interface A {}
    > 使用注解对象：注解可以在如下目标上作用：
      * 类
      * 成员变量
      * 方法
      * 构造器
      * 形参
      * 局部变量上
      * 包上

4. 注解的属性
  * 定义注解时，指定属性
  * 格式：属性类型 属性名()
    > 属性类型有限制
    > 属性名后面的一对圆括号不能省略，也不能添加内容
    > 例如：String name()
  * 带有属性的注解如何使用
    > @MyAnno1(name="zhangSan", age=18)
    > 使用带有注解的属性，需要在注解后给出圆括号一对，在括号内使用key=value的格式为每个属性赋值！
    > 必须给所有属性赋值

  * 属性类型的限制
    > 8种基本类型，不能是基本类型的包装类型，可以是int类型，但不能是Integer类型
    > String类型
    > Class类型
    > 枚举类型
    > 注解类型
    > 以上类型的一维数组类型：int[]、String[]、Class[]
  * 给所有类型的属性赋值：
  @MyAnno1(
		name="zhangSan",
		age=18,
		clazz=String.class, // 给Class类型的属性赋值
		abc=ABC.A, // 给枚举类型的属性赋值
		myAnno2=@MyAnno2(username="ls"), //给注解类型的属性赋值
		hobby={"hello", "world", "java"}//给数组赋值！当赋值属性只有一个元素时，可以省略大括号
//		hobby="hello" //当赋值属性只有一个元素时，可以省略大括号
 )

  * 属性的默认值
    > 可以在定义注解时，为属性指定默认值；
    > 有默认值的属性就可以不赋值了，当然也可以赋值。
    > 格式：String name() default "zhangSan"
  * 名为value属性的特权（不是在定义注解时，而是在使用注解时）
    > 若只需要给一个属性赋值时，并且这个属性的名称为value
      * 这个注解一共就一个属性，并且名为value
      * 这个注解有多个属性，但其他的属性都有默认值
    > 可以省略value=
    > 例如：MyAnno1("hello");
    > 错误例子：MyAnno1("hello", name="zhangSan");


5. 限制注解的作用目标
  * 我们知道注解的作用目标有很多：类、方法、构造器...
  * 我们有时需要限制注解的作用目标只能在方法上，或是只能在方法和类上
  * 需要使用一个注解完成！
    > 在自定义注解时，使用 @Target注解来限制注解的作用目标
    > Target注解只有一个属性，名为value，其类型为ElementType[]
    > ElementType是枚举类型，它包含一大堆的选项：
      * TYPE：表示注解可以作用在类、接口、枚举上
      * METHOD：表示注解可以使用在方法上
      * ...

6. 限制注解保留策略
  * 什么是保留策略
    > SOURCE：保留在源代码上
    > CLASS：保留在CLASS上，但JVM在加载CLASS时，不会加载注解！
    > RUNTIME：保存在CLASS上，而且JVM还会加载它！（如果注解想通过反射来使用，那么必须指定保留策略为RUNTIME）
  * 在定义注解时指定注解的保留策略
    > 使用 @Retention注解来指定注解的保留策略
    > 例如：
      @Retention(RetentionPolicy.RUNTIME)//指定当前注解的保留策略为运行时
      @interface MyAnno1 {}

7. 注解的反射
  * 所有的注解有一个公共的父接口：Annotation！
  * 创建注解，指定注解的保留策略为RUNTIME
  * 先得到作用目标，然后通过作用目标得到注解！
    > 例如一个注解作用在方法上，那么我们就需要先得到Method对象，然后在通过Method对象得到注解！
  * 作用目标类（Class、Constructor、Method、Field）都有如下方法
    > Annotation getAnnotation(Class)：获取指定类型的注解
    > Annotation[] getAnnotations()：获取所有注解
    > Annotation getDeclaredAnnotation(Class)：...

=========================================================

给出一个使用注解的环境（泛型的反射）

1. QueryRunner
  它操作数据库，一般都是两到三句
  * update：
    > sql模板
    > 给出参数
    > 调用qr.update()
  * query：
    > sql模板
    > 给出参数
    > 调用qr.query()，需要给出ResultSetHandler

2. 准备写一个类，BaseDAO
  * 目标：
  public class UserDAO extends BaseDAO<User> {
      pulbic void addUser(User user) {
          super.add(user);
      }
      pulbic void updateUser(User user) {
          super.update(user);
      }
      pulbic void deleteUser(String uid) {
          super.delete(uid);
      }

      public User load(String uid) {
          return super.load(uid);
      }

      public List<User> findAll() {
         return super.findAll();
      }
  }

public class BaseDAO<T> {
    private Class c;
    public BaseDAO() {
        // 获取子类传递的类型参数
        c = (Class)((ParameterizedType)this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
    }

    public void add(T bean) {
        // 得到User对应的Class
	// 通过Class得到 @Table注解

        String sql = "insert into 表名 values(几个?)";

	// 得到User类的所有javabean属性个数
	// 得到Field[]，再得到每个成员的值
	Object[] params = {通过bean来，bean的属性都是什么};
	qr.update(sql, params);
    }

    public void update(T bean) {
        // 得到T类型的所有Field[]，但其中谁是主键？
        String sql = "update 表名 set 属性=值, ... where 主键=值";
    }
}

@Table(value="tbl_user")
class User {
  @ID("col_uid")//它对应的是tbl_user表中的col_uid，并且它是主键
  private String uid;
  @Column("c_username")//它不是主键，它对应的是tbl_user表中的c_username列
  private String username;
  private String password;
}


---我们后期做的最多的事情，使用别人创建的注解
---框架创建注解类，反射注解类！

===================================
===================================
===================================
Servlet3.0新特性

1. 不用web.xml，使用注解替代
2. 异步处理，服务器开始响应（响应还未结束）客户端就可以看到响应数据
  > 服务器输出abcd ~ xyz，每次输出都会添加一个Thread.sleep(250);
  > 客户端会看到一个一个字母出现，而不是一下都出现！
3. 提供了上传的组件！Part接口
  > 原来我们使用commons-fileupload组件
  > Servlet3.0提供了Part接口，使用它与使用commons-fileupload很相似！

---------------------------

Servlet3.0注解替代web.xml中配置
  * 要求：
    > MyEclipse10.0以上版本！
    > tomcat7.0以上版本！

1. 使用注解来替代Servlet的配置
  @WebServlet
    * urlPatterns：String[]类型，指定多个路径
    * loadOnStartup：int类型，指定是否在服务器启动时完成创建Servlet及初始化工作
    * initParams： @WebInitParam[]，每个WebInitParam都有两个属性：name和value
@WebServlet(urlPatterns="/AServlet", loadOnStartup=1, 
	initParams={
		@WebInitParam(name="p1", value="v1"),
		@WebInitParam(name="p2", value="v2")
	}
)


2. 使用注解来替代Filter的配置
  @WebFilter(urlPatterns="/*")

3. 使用注解来替代Listener的配置
  @WebListener

==============================================

异步处理

1. 什么是异步
  * 无需服务器响应结束就可以在客户端看到响应结果

2. 步骤
  * 使用request对象创建一个异步上下文对象：AsyncContext
    > AsynContext ac = request.startAsync(request, response);
  * 创建一个任务，交给异步上下文对象来执行！
    > 什么是任务：Runnable对象！
    > 调用AysnContext的start()方法即可。
      * 异步上下文会开启一个异步线程来执行你交给它的任务

  * 注意项：
    > 必须去设置Conent-Type响应头
    > 在 @WebServlet注解中给出支持异步的值为true： @WebServlet(urlPatterns="/AServlet", asyncSupported=true)

  * 异步的作用：
    > 可以使用异步线程来完成一些比较费时的工作！例如发邮件！！！


  * 让异步上下文通知服务器，异步线程执行结束
    > ac.complete();

==============================================

上传

1. 上传对表单的要求(不变)
  * method="post"
  * enctype="multipart/form-data"
  * <input type="file" name="xxx"/>

2. Servlet
  * String username = request.getParameter("username")：可以使用了！！！
    > 获得普通表单项
  * Part part = request.getPart("myfile"); 
    > 获取文件表单项
    > Part的API
     * String getName()：获取表单字段的名称，不是文件的名称；
     * String getHeader(String name)：获取指定头的值
     * String getContentType()：获取文本的mime类型
     * InputStream getInputStream()：获取文件，以流形式返回
     * long getSize()：获取文件的字节数
     * void write(String file)：把文件保存到指定的路径下

   没有获取文件名称的方法
     * 可以获取Content-Disposition这个请求头，然后自己截取文件名称！

  * 注意项：必须给Servlet添加如下注解：
    > @MultipartConfig

============================================
============================================
============================================
动态代理

1. 学习一个方法
  Proxy类的一个static方法：
    * Object newProxyInstance(ClassLoader loader, Class[] interfaces, InvocationHandler h)
    * 在运行时，动态创建一个类，再动态创建该类的对象，这个类实现了指定的一组接口！

interface A {}
interface B {}

class AB implements A,B {}

Object o = new AB();
A a = (A)o;
B b = (B)o;

2. Proxy三大参数
  * ClassLoader：类加载器，因为我们需要动态创建一个类，这个类也需要加载到内存中，由ClassLoader完成加载！
  * Class[] interfaces：要实现的一组接口
  * InvocationHandler：调用处理器，无论调用代理对象的什么方法，最终调用的都是“调用处理器”的invoke()方法

3. InvocationHandler
  * Object invoke(Object proxy, Method method, Object[] args);
  * 调用代理对象的任何方法，执行的都是InvocationHandler的invoke()方法。
  * 在调用代理对象的方法时：
    > 代理对象传递给了invoke的Object proxy参数；
    > 调用的方法传递给了invoke的Method method参数；
    > 调用方法时的实际参数传递给了invoke的Object[] args参数；

===================================
===================================

应用动态代理

1. 接口Waiter
  > void serve()：服务方法

2. 实现类GirlWaiter
  > 给出实现的细节

3. 给GirlWaiter添加增强
  > 在服务之前说：您好！
  > 还是调用GirlWaiter的serve()方法
  > 在服务之后说：再见！


try {
  开启事务
  dao.xx();
  dao.xx();
  提交事务
} catch() {
  回滚事务
}
-----------

dao.xx();
dao.xx();

