1:Eclipse的使用(掌握)
	请参照：Java开发工具

2:Object类
	(1)是定义在java.lang包下的，是所有类的超类。所有类都直接或者间接的继承自Object类。
		父类：超类，根类，基类。
		子类：派生类。
	(2)要掌握的方法：
		public String toString():返回对象的字符串表示形式。
			默认情况下的组合：类的全路径名称+@+对象的哈希值的十六进制表示。
			这种做法对我们来说，一般没有意义，所以，建议重写。
			重写做法：一般是把所有的成员变量组合成一个字符串返回。
			实在不愿意自己写，自动生成。
		public boolean equals(Object obj):比较对象的地址值是否相同。
			默认情况下，比较的是对象的地址值是否相同。
			如果有自己的特殊需求，请重写Object类的该方法。
			怎么重写呢?
				public boolean equals(Object obj) {
					if(this == obj) {
						return true;
					}

					if(!(obj instraceof Student)) {
						return false;
					}

					Student s = (Student) obj;
					return this.name.equals(s.name) && this.age == s.age;
				}

				如果有多个成员变量，把多个比较用&&连接即可。
					引用类型用equals()方法。
					基本类型用==号即可。
			实在不愿意自己写，自动生成。
	(3)面试题：
		==和equals()的区别?
		==:
			可以比较基本类型，也可以比较引用类型。
			比较基本类型，比较的是值是否相同。
			比较引用类型，比较的是地址值是否相同。
		equals:
			只能比较引用类型。
			默认情况下，比较的是地址值是否相同。
			如果想比较内容，请自己重写Object类的equals()方法。