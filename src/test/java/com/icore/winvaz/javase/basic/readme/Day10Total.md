1:内部类(理解)
	(1)把类定义在一个类的内部。
	(2)特点：
		A:内部类可以直接使用外部类的成员，包括私有。
		B:外部类要使用内部类成员，必须创建对象使用。
	(3)内部类的分类：
		局部内部类：定义在方法中的内部类
		成员内部类：定义在类中方法外的内部类
	(4)成员内部类
		class Outer {
			class Inner {
			
			}
		}

		普通的修饰的时候：
			外部类名.内部类名 变量 = 外部类对象.内部类对象;
			Outer.Inner oi = new Outer().new Inner();

		 class Outer {
			static class Inner {
			
			}
		}
		用静态修饰后：
			外部类名.内部类名 变量 = 外部类名.内部类对象
			Outer.Inner oi = new Outer.Inner();

		成员内部类：
			private：为了数据的安全。
			static：为了方便调用。
	(5)局部内部类
		A:带名字的局部内部类
			class Outer {
				public void method() {
					final int a = 10;
					class Inner {
						public void show() {
							System.out.println("show");
							System.out.println(a);
						}

						Inner i = new Inner();
						i.show();
					}
				}
			}
		B:匿名内部类(掌握)
			a:没有名字的内部类。其本质是一个对象。
			b:前提：存在一个抽象类或者接口。
			c:格式：
				new 抽象类或者接口() {
					重写父类方法;
				};

				本质：是一个继承了类或者实现了接口的子类匿名对象。
	(6)面试题：
		interface Inter {
			public abstract void show();
		}

		class Outer {
			//补齐代码
		}

		class Test {
			public static void main(String[] args) {
				Outer.method().show(); //输出"林青霞"
			}
		}

2:包(掌握)
	(1)包就是永远区分相同类在不同文件夹下。其本质就是一个文件夹。
	(2)包的作用：让相同类名的类可以存在。
	(3)定义包：
		package 包名.包名...;
	(4)带包的编译和运行(理解)
		A:方式一
			手动式
			a:javac编译代码
			b:手动建立包，把class扔进去
			c:java执行，带全路径
		B:方式二
			自动式
			a:通过javac编译自动生成包
				javac -d . 文件名
			b:通过java运行class文件
				java 包名.包名.class文件名不带扩展名
	(5)不同包下的类之间的访问。
		权限够大才行。

		以后，我们所有定义的类都用public修饰。
	(6)面试题：
		在同一个java文件中，可不可以有多个类?可以。
		而我们知道类是可以用public修饰的,那么，可不可以多个类都用public修饰?不可以。
		并且，一般的时候，如果有main方法也存在的时候，都是public修饰带main那个类。
		文件名必须和该public修饰的类名一致。

3:导包(掌握)
	(1)如果多次使用一个多级包的类，每次带全路径太麻烦，我们就考虑使用导包。
	(2)导包的格式：
		import 包名.包名...;

		注意：
			在同一个包下如果有多个类被使用，可以使用通配符*来导入，但是不建议。
			推荐：使用谁导入谁。
	(3)面试题：
		package，import，class出现的位置?
			package > import > class

4:权限修饰符(掌握)
	(1)用于保证在不同的情况下可以使用
	(2)使用如下
				本类	同一个包下	不同包下的子类	不同包下的无关类
		private		Y
		默认		Y	Y
		protected	Y	Y		Y
		public		Y	Y		Y		Y


常见的组合规则：
	权限修饰符+abstract/final/static+...

我们到底怎么用：
	类：
		public class Demo {
		
		}

	成员变量：
		变量：private String name;
		常量：public static final int X = 10;

	构造方法：
		不让外界创建：private Demo(){}
		大部分：public Demo(){}

	成员方法：
		不让外界用：private void show(){}
		大部分：public void method(){}
		抽象方法：public abstract void function();
