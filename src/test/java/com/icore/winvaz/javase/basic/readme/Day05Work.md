今天重点复习前面的内容，明天开始就是面向对象了。
前面的知识点比较多和杂，希望同学们能静下心来，把下面的步骤做一遍。
没什么基础的同学更应该比别人花更多的时间去写案例。

1：至少把前五天的总结看一遍。如果遇到有知识点不会的，
   一定要和同学讨论，交流。还不会一定要问我。
2：把前五天的案例不会的至少写一遍。
3：如果都不会，那么你就照着写一遍。写的时候，自己看能不能有些印象，这样跟下来也没有问题。
   如果哪个案例一点都看不懂，先问同学这个案例是干什么的，然后再写一遍。

作业：
	画内存图：
		int[][] arr = new int[3][];

		arr[0] = new int[3];
		arr[1] = new int[1];
		arr[2] = new int[2];

	将课上作业test部分至少看一遍。最好推荐，是因为强制也不听所以妥协成这样。最好能手敲一遍

思考题：请写出下列程序的结果,并总结基本类型和引用类型的不同。如果图画出来了，那么就
直接理解了。如果图没画明白，很难理解！
	class Demo
	{
		public static void main(String[] args)
		{
			int a = 10;
			int b = 20;
			System.out.println("a:"+a+",b:"+b);
			change(a,b);
			System.out.println("a:"+a+",b:"+b);

			int[] arr = {1,2,3,4,5};
			change(arr);
			System.out.println(arr[1]);
		}

		public static void change(int a,int b)
		{
			System.out.println("a:"+a+",b:"+b);
			a = b;
			b = a + b;
			System.out.println("a:"+a+",b:"+b);
		}

		public static void change(int[] arr)
		{
			for(int x=0; x<arr.length; x++)
			{
				if(arr[x]%2==0)
				{
					arr[x]*=2;
				}
			}
		}
	}
