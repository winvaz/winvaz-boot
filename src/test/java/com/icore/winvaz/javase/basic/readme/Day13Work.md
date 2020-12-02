核心作业：
	将上课所学到的方法，手敲一遍！

思考如下问题：
	如果我给你任意一个类，你能够通过API来完成学习和使用吗?
		如何调用呢？
		注意哪些问题呢?
		你能想到什么就写什么

/*
Arrays工具类要掌握的三个功能解释?
	public static String toString(int[] a)
	public static void sort(int[] a)
	public static int binarySearch(int[] a,int key)
*/

StringBuffer常见功能有哪些?
插入，删除，添加
StringBuffer和String的区别?
String:每创建一个对象就开辟一个空间，占用内存大
StringBuffer:是可变的默认缓冲区
StringBuffer和StringBuilder的区别?

编程练习题
	A:把一个int[]转换成一个字符串
	B:把一个字符串反转输出"abcde"
        String string = "abcde";
		char[] charArray = string.toCharArray();
		StringBuilder builder = new StringBuilder();
		
		for (int i = charArray.length - 1; i >=0; i--) {
			//System.out.print(charArray[i]);
			builder.append(charArray[i]);
		}
		System.out.println(builder.toString());
	//C:把字符"34 -12 56 93 27"中的数据排序，然后输出排序后的字符串。

扩展题(看看能不能做出来)
	定义一个标准学生类Student(name,age)
	定义一个学生数组。注意：日常开发过程当中不会出现这样的数组。这里只是作为了解。
	存储5个学生，然后遍历。

	思考
	int[]{23,65,76,97,432,21}>>21,23,65,76,97,432
/*
	冒泡排序的原理,选择排序的原理,二分查找的原理概述?并能看懂代码即可。
*/