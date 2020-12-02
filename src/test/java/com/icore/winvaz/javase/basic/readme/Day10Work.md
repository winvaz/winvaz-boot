把面向对象总结回顾一遍。把不太理解问题记录下来，然后提问。
后天我们就开始对着API学习了。
/*
1:请补齐代码，让程序可以正常执行，并输出"HelloWorld"

		interface Inter
		{
			void show();
		}

		class Outer
		{
			//补齐代码，完成主方法中定义的功能
		}

		class Test
		{
			public static void main(String[] args)
			{
				Outer.method().show(); 
			}
		}
*/

//类型.方法名()：一定是在该类里有一个静态方法
//XX.show()XX一定是一个类名或者一个对象
//通过阅读Inter我们认为Outer.method()即上步的XX部分一定是一个Inter类型或者其对象实例
//Outer.method()中method方法返回的类型为XX部分所描述的内容
