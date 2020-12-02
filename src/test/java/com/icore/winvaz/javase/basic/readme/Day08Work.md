1：了解制作API的过程。然后按照过程把Math类和Random类在回顾一次。猜数字
import java.util.Scanner;
public class {
  public static void main(String[] args) {
     // Generate a radom number to be guessed
     int number = ((int)(Math.random() * 100));
     //Convert a Scanner
     Scanner input = new Scanner(System.in);
     
     //Enter a guess
     System.out.println("Enter a guess number for 1 between 100: ");
    
     int guess = input.nextInt();
    
    while (guess != number){
      if (guess == number){
        System.out.println("恭喜你，猜对了！正是这个数" + number);
      }
      else if (guess > number) {
        System.out.println("你猜的数字大了，请往小数字猜。");
      }
      else
        System.out.println("你猜的数字小了，请往大数字猜。");
    } // End of loop
  }
}

2：继承是什么?继承的好处是什么?
继承：多个类中存在相同属性和行为时，将这些内容抽取到单独一个类中，
      那么多个类无需再定义这些属性和行为，只要继承那个类即可。
好处：继承的出现提高了代码的复用性
      继承的出现让类与类之间产生了关系，提供了多态的前提
      子类可以直接访问父类中的非私有的属性和行为。

3：Java中的继承特点是什么?
特点：继承的出现提高了代码的复用性
      继承的出现让类与类之间产生了关系，提供了多态的前提
//慎做：相对好一些
4：看程序写结论。最好自己分析,先不要运行看结果。
	class Fu
	{
		public int num = 10;

		public Fu()
		{
			System.out.println("fu");
		}
	}

	class Zi extends Fu
	{
		public int num = 20;

		public Zi()
		{
			System.out.println("zi");
		}

		public void show()
		{
			int num = 30;
			System.out.println(num);
			System.out.println(this.num);
			System.out.println(super.num);
		}
	}

	class Test
	{
		public static void main(String[] args)
		{
			Zi z = new Zi();
			z.show();
		}
	}
//暂留
5：this和super分别是什么，他们各自的应用场景是什么?
this:代表当前类的一个引用对象，谁调用，它代表谁
	
//暂留
6：什么是方法重写?需要注意哪些问题?
//暂留
7：方法重写和重载有什么区别?
//暂留
8：子父类中构造方法的执行有什么特点?为什么要这样?
子父类中构造方法必须先调用父类的构造方法

9：静态代码块，构造代码块，构造方法的执行顺序是什么?
执行顺序：静态代码块》构造代码块》构造方法：创建一个实例对象，初始化属性。
//暂留
10：final关键字是什么，可以干什么?分别是哪些特点?
final:修饰类 不能再被继承
      修饰方法 方法调用
      修饰变量 变量之后不能再赋值

11：分析如下需求，写出你分析的类有哪些，以及功能。
    最后写一个测试类，针对每个类的功能进行测试。

    动物园里有很多种动物：
	比如说，狗，猫等。
	狗有姓名和年龄，猫也有姓名和年龄。
	狗有跑步的方法，猫也有跑步的方法。而且都仅仅是跑步。
	狗有吃饭的方法，猫也有吃饭的方法。只不过，狗吃骨头，猫吃鱼。
    请用所学知识，对这个问题进行解决。
class Zoo {


}




//慎做：无聊的人出的题
12:分析如下代码的结果
	class X{
		Y b = new Y();
		X() {
			super();
			System.out.print("X");
		}
	}
	class Y {
		int a = 19;
		Y() {
			System.out.print("Y");
		}
	}
	public class Z extends X {
		Y y = new Y();
		Z() {
			super(); //代表先构建父类，不是从这里开始
			System.out.print("Z");
		}
		public static void main(String[] args) {
			new Z(); Y X Y Z
		}
	}

	提示：
		一个类的初始化
			A:把class文件加载到内存
			B:在栈内存开辟p变量空间
			C:在堆内存开辟new Person()空间
			D:对成员变量默认初始化
			E:对成员变量显示初始化
			F:走构造方法对成员变量初始化(如果有构造代码块，走构造代码块)
			G:构造完毕，把地址赋值给p变量。

		如果有继承关系，则无论父类还是子类，构造顺序如上


第5题答案：
this：代表当前类的一个引用对象。谁调用，它代表谁。
	super：代表的是父类的存储空间标识。可以理解为父类的引用对象。

	应用场景：
		成员变量：
			this.变量 使用当前类的成员变量
			super.变量 使用父类的成员变量
		构造方法：
			this(...) 使用当前类的构造方法
			super(...) 使用父类的构造方法
		成员方法：
			this.方法() 使用当前类的成员方法
			super.方法() 使用父类的成员方法

	构造方法问题：
		子类在实例化的时候，会首先调用父类的构造方法。
		在子类的构造方法中，默认有一个调用父类的无参构造方法。
		假如父类并没有提供无参构造方法。
		这个时候，代码就会报错，请问怎么解决?
			使用super(...)调用父类带参构造方法
			使用this(...)调用本类的其他构造方法

第6题答案：
方法重写：在子类中，方法声明（修饰符，返回值，方法名，参数列表）相同的情况。
覆盖时，子类方法权限一定要大于等于父类方法权限
静态只能覆盖静态。
参数不同，函数名相同同样符合重载规律
覆盖时，返回值类型为基本数据类型时，必须相同，如果是引用数据类型，子类返回值类型必须为父类返回值类型的子类


第７题答案：
	方法重写：在不同类中，方法声明相同。(方法名和参数列表)
		扩充：返回值类型可以小于等于父类返回值类型
		      权限修饰符要大于等于父类的权限修饰符

	方法重载：在同一个类，方法名相同，参数列表不同。

	重载可以改变返回值类型，因为它和返回值类型无关。

	重载：overload
	重写：override