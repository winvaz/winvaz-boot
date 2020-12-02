1:Scanner的概述(理解)
	(1)Scanner是JDK5以后出现的方便我们从键盘接受数据的类。
	(2)Scanner的构造格式：
		Scanner sc = new Scanner(System.in);

		System.in 是System类下面有一个静态的成员变量in。它的类型是InputStream。	
			  代表的是标准键盘输入流。也就是键盘录入数据。
		Scanner是对其进行了封装，提供了各种转换功能。方便我们获取到想要的数据类型的数据。
	(3)要掌握的两个功能：
		A:返回int类型
			public int nextInt()
		B:返回String类型
			public String nextLine()
			public String next()
		
		注意事项：
			先int,在String会有问题。
			解决方案：
				重新建立Scanner对象。//一般不会这样做。因为消耗资源
				把所有的数据都看成是String类型，将来转换为int类型。
				通常使用next()方法而不用nextLine()方法

2:String类的概述和使用(掌握)
	(1)由多个字符组成的一串数据。
	(2)构造方法：
		A:String s = new String();
		B:String s = new String(byte[] bys);
		//C:String s = new String(byte[] bys,int startIndex,int count);
		D:String s = new String(char[] chs);
		//E:String s = new String(char[] chs,int startIndex,int count);
		F:String s = new String(String s2);
		G:String s = "hello";

		长使用：
			BCDEG
	(3)面试题：
		A:字符串一旦被赋值就不能被改动。
			注意：这里的改动指的是字符串的内容，而不是字符串对象的引用。
		B:String s = new String("hello");和String s = "hello";有区别吗?是什么呢?
			有。
			前者创建了两个对象。
			后者创建了一个对象。
		C:看程序，写结果
			String s1 = new String("hello");
			String s2 = new String("hello");
			System.out.println(s1==s2);// false 两个不相同的对象，地址值也不相同
			System.out.println(s1.equals(s2)); // true 方法重写

			String s3 = new String("hello");
			String s4 = "hello";
			System.out.println(s3==s4); // false 两个不相同的对象，地址值也不相同
			System.out.println(s3.equals(s4)); // true 方法重写

			String s5 = "hello";
			String s6 = "hello";
			System.out.println(s5==s6); // true 
			System.out.println(s5.equals(s6)); // true
		D:看程序，写结果
			String s7 = "hello";
			String s8 = "world";
			String s9 = "helloworld";
			System.out.println(s9==s7+s8); // false 编译出错，
			System.out.println(s9=="hello"+"world"); // true

			变量就直接造，常量先找，如果有就使用，否则就造。
	//(4)字符串的常见功能：(补齐中文)
		A:判断功能
			boolean equals(Object obj)
			boolean equalsIgnoreCase(String str)
			boolean contains(String str)
			boolean startsWith(String str)
			boolean endsWith(String str)
			boolean isEmpty()

		B:获取功能
			int length()
			char charAt(int index)
			int indexOf(int ch) 
			int indexOf(String str);
			int indexOf(int ch,int fromIndex)
			int indexOf(String str,int fromIndex)
			String substring(int start)
			String substring(int start,int end)

		C:转换功能
			byte[] getBytes()
			char[] toCharArray()
			static String copyValueOf(char[] chs)
			static String valueOf(char[] chs)
			static String valueOf(int i)
			String toLowerCase()
			String toUpperCase()
			String concat(String str)

		D:其他功能
			String replace(char old,char new)
			String replace(String old,String new)

			String[] split(String regex)

			String trim()
			
			int compareTo(String str)
			int compareToIgnoreCase(String str) 
	(5)案例：(理解)
		A:模拟用户登录
		B:遍历字符串
			String s = "hello";
			
			for(int x=0; x<s.length(); x++) {
				System.out.println(s.charAt(x));
			}
		C:统计字符串中大写字母，小写字母以及数字字符出现的次数
		D:把一个字符串的首字母变成大写，其他的全部小写
		E:统计大串中小串出现的次数