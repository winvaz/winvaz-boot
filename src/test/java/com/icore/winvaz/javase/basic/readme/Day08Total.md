1：方法的多层调用：
	方法中可以调用其他方法。其他方法中可以再调用其他方法。
	可以使用对象调用，也可以直接调用。


1：如何使用API。(掌握)
	A:找到文档，打开文档。
	B:点击左上角的显示变成隐藏，然后点击索引
	C:你应该知道你要找谁?Math
		所以，你就在那个输入框里面输入Math，然后回车，再回车。
	D:看这个类的结构
		java.lang 
			类 Math

		java.util 
			类 Scanner


		如果是java.lang包下的类，是可以直接使用的。(Math)
		否则，就需要导包才能使用。(Scanner)
	E:看看对这个类的说明。
	F:看构造方法
		名称：字段	--	成员变量	-- 属性。

		a:有构造方法，那么，就通过构造方法创建对象使用。
		b:没有构造方法，那么，这个类的成员一般都是静态。
			通过类名调用。
	G:按照正常的操作使用方法。
		左边：
			是否静态：如果静态修饰，那么可以通过类名调用。否则，就必须创建对象调用。
			是否有明确返回值：如果是void类型，就直接调用即可。否则，就可以输出或者赋值。
		右边：
			看方法名：不要写错了。
			看参数：看参数类型及个数。

2：继承(掌握)
	(1)把多个类中的相同的属性和行为进行抽取，封装到一个类中，
	   然后再建立新类的时候，不需要从头做起，继承刚才定义的那个类即可。
	(2)好处：
		A:提高代码的复用性。
		B:让类与类之间产生了一个关系，是多态的前提。
	(3)什么时候使用继承?
		A:如果类之间存在着:
			is a 的关系，就可以考虑使用继承。
		B:不要为了继承部分功能，而去使用继承。
	(4)继承的特点：
		A:Java只支持单继承，不支持多继承。
			为什么?如果支持多继承，就会有调用不明确的问题。
		B:Java支持多层(重)继承。
	(5)super和this的区别?
		A:super是一个关键字，代表父类的存储空间标识。(可以理解为父亲的引用)
		B:它和this的用法相似
			a:成员变量
				this.变量	--	本类的
				super.变量	--	父类的
			b:构造方法
				this(...)	--	本类的
				super(...)	--	父类的
			c:成员方法
				this.方法名()	--	本类的	
				super.方法名()	--	父类的
	(6)子父类中成员变量的用法：
		A:名称不同，这个太简单了。
		B:名称相同，子类对象的在使用的时候：
			
			先找子类局部范围
			再找子类成员范围
			最后找父类成员范围
	(7)子父类中成员方法的用法：
		A:名称不同，这个太简单了。
		B:名称相同，子类对象的在使用的时候：
			先找子类的
			再找父类的
		C:方法重写
			在子类中，方法声明(修饰符,返回值,方法名,参数列表)相同的情况。

			注意事项：
				a:父类中私有方法是不能被重写
				b:子类方法的访问权限一定要大于等于父类的访问权限
				c:静态只能重写静态。(这个不能算，因为静态跟类相关)
	(8)子父类中构造方法的用法：
		A:子类的初始化过程中，首先回去执行父类的初始化动作。
		   因为子类的构造方法中默认有一个super()。
			为什么?子类要使用父类的成员变量，这个初始化，必须在子类初始化之前完成。
			所以，子类的初始化过程中，会先执行父类的初始化。
		B:如果父类没有无参构造方法
			A:使用super调用父类的带参构造。推荐方式。
			B:使用this调用本身的其他构造。
3：代码块(面试题)
	(1)执行顺序：
		静态代码块 --> 构造代码块 --> 构造方法
	(2)注意事项：
		静态代码块只执行一次

4：final(掌握)
	(1)是一个关键字，可以用于修饰类，成员变量，成员方法。
	(2)特点：
		它修饰的类不能被继承。
		它修饰的成员变量是一个常量。
		它修饰的成员方法是不能被子类重写的。