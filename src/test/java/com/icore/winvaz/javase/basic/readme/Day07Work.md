简答题目：
1：请解释面向对象是什么?并举例说明?
2：类与对象的关系?
3：类有哪些内容组成?
4：局部变量和成员变量的区别?
   栈内存    堆内存
   没有初始值   有初始值
编程题：
	1：完成一个学生类的封装，要求学生有姓名，年龄，学号。
		并在测试类中对其学号进行赋值和获取

	//先不要做，在多态(整个面向对象)完成之后，有兴趣的同学做一下
        2：使用面向对象完成下列业务
	环保局(一个类，有派发任务的功能，如果这个监察队中大于10个人，就返回"大规模监察"，否则就返回"局部巡查")
	监察任务(一个类，有监测企业名称，监测队员名称（用数组存储）)

	强制要求：环保局派发任务的功能需要接收一个监察任务类型的参数
		创建三个任务，调用派发任务得到返回结果

	3：在第二题的基础上，将监察任务进行封装。

5：构造方法的特点及注意事项?
6：this关键字是什么?在什么时候使用?
7：给成员变量赋值的方式有几种?分别怎么实现?
8：static关键字是什么?有什么特点?在什么时候使用?

编程题目：
1：请写出一个标准的学生类(Student)
	Student:
		成员变量：name,age,sex
		构造方法：无参构造方法，带三个参数的构造方法
		成员方法：
			A:get/set方法
			B:把所有成员变量的值输出的方法(show)
			
2：在测试类StudentTest中测试第一题的内容。
class Day07 
{
	public static void main(String[] args) 
	{
   Student s = new Student();
   Student s2 = new Student("张三");
   Student s3 = new Student("李四","男");
   Student s4 = new Student("王老五","男",20);
   Student s5 = new Student("王老六","男",20,"2014071508");
   s.show();
   s2.show();
   s3.show();
   s4.show();
   s5.show();

	}
}

 class Student
{
	private String name;
	private String sex;
	private int age;
	private String xueHao;


	// 创建构造方法(函数)
	public Student(){
	
	}

	public Student(String name) {
		this.name = name;
	}

	public Student(String name, String sex) {
		this(name); // this.name = name;
		this.sex = sex;
	}

	public Student(String name, String sex, int age) {
		this(name, sex);
		this.age = age;
	}

	public Student(String name, String sex, int age, String xueHao) {
		this(name, sex, age);
		this.xueHao = xueHao;
	}

	public void show() {
		System.out.println();
		System.out.println("姓名: " + name);
		System.out.println("姓名: " + name + "性别: " + sex);
		System.out.println("姓名: " + name + "性别: " + sex + "年龄: " + age);
		System.out.println("姓名: " + name + "性别: " + sex + "年龄: " + age + "学号:" + xueHao);
	}

//--------------gs---------------

    // 对学生类的姓名封装
	public void setName(String name) {
		this.name = name;
	}
	public String gstName() {
		return name;
	}

	// 对学生的性别封装
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSex() {
		return sex;
	}

	// 对学生的年龄封装
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}

	// 对学生的学号封装
	public void setXueHao(String xueHao) {
		this.xueHao = xueHao;
	}
	public String getXueHao() {
		return xueHao;
	}
}
/*暂留
3：编写代码测试下面这句话：
	静态方法只能访问静态成员(成员变量和成员方法)。

4：猜数字小程序。
*/