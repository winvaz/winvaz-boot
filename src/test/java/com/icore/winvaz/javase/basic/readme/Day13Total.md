1:数组的高级操作(理解)
	(1)数组：存储同一种数据类型的多个元素的容器。
	(2)特点：每个元素都有从0开始的编号，方便我们获取。专业名称：索引。
	(3)数组操作：
		A:遍历
			public static void printArray(int[] arr) {
				for(int x=0; x<arr.length; x++) {
					System.out.println(arr[x]);
				}
			}

		B:获取最值
			public static int getMax(int[] arr) {
				int max = arr[0];

				for(int x=1; x<arr.length; x++) {
					if(arr[x]>max) {
						max = arr[x];
					}
				}

				return max;
			}

		C:排序
			a:冒泡排序
				原理：相邻元素两两比较，大的往后放。第一次完毕，最大值在最大索引处。

				public static void bubbleSort(int[] arr) {
					for(int x=0; x<arr.length-1; x++) {
						for(int y=0; y<arr.length-1-x; y++) {
							if(arr[y] > arr[y+1]) {
								int temp = arr[y];
								arr[y] = arr[y+1];
								arr[y+1] = temp;
							}
						}
					}
				}

			b:选择排序
				原理：从0索引元素开始，依次和后面的所有元素比较，小的往0索引处放。
				      第一次完毕后，最小值在最小索引处。

				public static void selectSort(int[] arr) {
					for(int x=0; x<arr.length-1; x++) {
						for(int y=x+1; y<arr.length; y++) {
							if(arr[y]<arr[x]) {
								int temp = arr[y];
								arr[y] = arr[x];
								arr[x] = temp;
							}
						}
					}
				}

		D:查找
			a:普通查找
				原理：遍历数组，从头找到尾

				public static int getIndex(int[] arr,int value) {
					int index = -1;

					for(int x=0; x<arr.length; x++) {
						if(arr[x]==value) {
							index = x;
							break;
						}
					}

					return index;
				}

			b:二分查找(折半查找)
				前提：数组必须是有序的。

				原理：每次都从中间开始找，如果比中间数据小，就在左边找，
				      如果比中间数据大，就在右边找，如果相等，就返回中间的索引值。

				public static int getIndex(int[] arr,int value) {
					int start = 0;
					int end = arr.length-1;
					int mid = (start+end)/2;

					while(arr[mid]!=value){
						if(value>arr[mid]) {
							start = mid + 1;
						}else if(value<arr[mid]) {
							end = mid - 1;
						}

						if(start > end) {
							return -1;
						}

						mid = (start+end)/2;
					}

					return mid;
				}

2:Arrays工具类的使用(掌握)
	(1)Arrays是针对数据进行操作的工具类。
	(2)要掌握的功能：
		A:把数组转成字符串
			public static String toString(int[] arr)
		B:排序
			public static void sort(int[] arr)
		C:二分查找
			public static int binarySearch(int[] arr,int value)
	(3)Arrays工具类的源码。(理解)

3:StringBuffer类(掌握)
	(1)StringBuffer:是字符串缓冲区类，长度可以改变。
	(2)面试题：
		String和StringBuffer的区别?
			String的长度固定。
			StringBuffer的长度可变。
		StringBuffer和StringBuilder的区别?
			StringBuffer的线程安全,效率低。
			StringBuilder的线程不安去,效率高。
	(3)StringBuffer的构造方法
		A:StringBuffer sb = new StringBuffer();
		B:StringBuffer sb = new StringBuffer(int capacity);
		C:StringBuffer sb = new StringBuffer(String s);

		注意：StringBuilder的功能和StringBuffer一模一样。前者是JDK5以后出现的。
	(4)要掌握的功能：(请自己把对应的方法写出来)
		A:添加功能
		B:删除功能
		C:其他功能
			替换功能
			截取功能
			反转功能
	(5)案例：
		把一个字符串反转。

4:基本数据类型包装类(掌握)
	(1)由于我们对基本类型只能做一些最简单的操作，
	   为了让我们有更多的操作，java就针对每种基本类型提供了保证类。
	(2)八种基本类型对应的包装类是谁?
		byte	Byte
		short	Short
		int	Integer
		long	Long
		float	Float
		double	Double
		char	Character
		boolean	Boolean
	(3)Integer类的构造方法
		A:Integer i = new Integer(int x);
		B:Integer i = new Integer(String s);
			注意：这里的s必须是有数字字符组成的字符串。
	(4)Integer的功能
		A:String --> int	
			// String s = "100";
			int i = Integer.parseInt("100");

		B:int -- String
		    // int i = 100;
			String s = String.valueOf(100);
	(5)JDK5以后的新特性
		A:自动装箱 从int--Integer
		B:自动拆箱 从Integer--int

		请大家解释下面的代码：哪里体现了自动装箱，哪里体现了自动拆箱
			Integer i = 100;
			i += 200;
			System.out.println(i);
		
		注意：
			让我们操作变得简单，但是隐含了一个问题，这个时候，我们在使用对象前，最好做不为空的校验。
	(6)面试题：(看代码 IntegerTest.java)
		byte常量池。
	(7)案例：
		把字符串"-34 29 76 11 27"中的数据排序并输出。

5:Random(理解)
	(1)是产生随机数的类。
	(2)构造方法：
		A:Random r = new Random();
		B:random r = new Random(long seed);

		注意：种子一样，随机数一样。
	(3)掌握的方法：
		public int nextInt(int n):产生在[0,n)之间的随机数。