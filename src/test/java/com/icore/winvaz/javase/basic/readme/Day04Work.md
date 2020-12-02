第一题：
	求1-100之和。
class Demo
{
	public static void main(String []args)
	{
		int sum=0;
		for (int i=1;i<=100 ;i++ )
		{
			sum=sum+i;
		}
		System.out.println(sum);
	}
}
	求1-100之间偶数和。
public class Demo {
  public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <=100; i++) {
           if (i % 2 == 0) {
              sum = sum + i;
              //System.out.println("1-100之间的偶数和为: " + sum);
           }
          //System.out.print("1-100之间的偶数和为: " + sum);
        }
        System.out.print("1-100之间的偶数和为: " + sum);
  }
}

	求1-100之间奇数和。
public class Demo {
  public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <=100; i++) {
           if (i % 2 == 1) {
              sum = sum + i;
              //System.out.println("1-100之间的奇数和为: " + sum);
           }
          //System.out.print("1-100之间的奇数和为: " + sum);
        }
        System.out.print("1-100之间的奇数和为: " + sum);
  }
}


循环里面要做的题目：
不做为作业，有兴趣的同学可以试试看
第二题：
打印出所有的"水仙花数"。
所谓"水仙花数"是指一个三位数，其各位数字立方和等于该数本身。例如：
153是一个"水仙花数"，因为153=1的三次方＋5的三次方＋3的三次方。 
提示：
1：采用循环取得所有的三位数。(三位数指的是100-999之间的数)。
2：把每个三位数的个位，十位，百位进行分解。


第三题：
求5的阶乘。
提示：5的阶乘就是：5*4*3*2*1。 
思路1：   
5   *4*3*2*1  对于5来讲重复4次循环，每次循环乘以比上次循环少1的数
4   *3*2*1    对于4来讲重复3次循环
6   *5*4*3*2*1对于6来讲重复5次循环
思路2：
5   1*1*2*3*4*5
4   1*1*2*3*4
6   1*1*2*3*4*5*6
long fac = 1;
for (int i = 1; i < 5; i++) {
   fac = fac * i; 
  }
  System.out.println(fac);	


第二题：
	打印出所有的"水仙花数"。
	所谓"水仙花数"是指一个三位数，其各位数字立方和等于该数本身。例如：
	153是一个"水仙花数"，因为153=1的三次方＋5的三次方＋3的三次方。

  int a3,b2,c1;
  for(int m=101;m<1000;m++){
    a3=m/100;
    b2=m%100/10;
    c1=m%10;
  if(a3*a3*a3+b2*b2*b2+c1*c1*c1==m) 
   System.out.println(m+"是个水仙花数");
   } 
/*
 for (int three =101; three < 1000; three++)
     {
		  int baiWeiNumber = three / 100;
		      //three = three % 100;
		  // Find the three wei number of (十位数)
		  int shiWeiNumber = three % 100 / 10;
		  int geWeiNumber = three % 10;
          // Find the three wei number of (个位数)
		  //int geWeiNumber = three;
          //double sum = baiWeiNumber * baiWeiNumber * baiWeiNumber + 
			 // shiWeiNumber  * shiWeiNumber * shiWeiNumber + 
			  //geWeiNumber * geWeiNumber * geWeiNumber;
		 double sum = Math.pow(baiWeiNumber,3) + Math.pow(shiWeiNumber,3) + Math.pow(geWeiNumber,3);
		  if (three == sum)
			  System.out.println(three + " 是一个水仙花数");
		  //else
			  //System.out.println(three + " 这不是一个水仙花数");
	 }


第三题：
	请输出满足这样条件的五位数。
	10000-99999 
		个位=万位
		十位=千位
		个位+十位+千位+万位=百位
        11411

int geWeiNumber, shiWeiNumber, baiWeiNumber;
int qianWeiNumber, wanWeiNumber;
for (int i = 10000; i <= 99999; i++) {
   wanWeiNumber = i / 10000;
   qianWeiNumber = (i % 10000) / 1000;
   baiWeiNumber = ((i % 10000) % 1000) / 100;
   shiWeiNumber = ((i %10000) % 1000) % 100 / 10;
   geWeiNumber = i % 10;
   if (geWeiNumber == wanWeiNumber)
   {
	   if (shiWeiNumber == qianWeiNumber)
	   {
		   if (geWeiNumber + shiWeiNumber + qianWeiNumber + wanWeiNumber == baiWeiNumber)
		   {
			   System.out.println(i);
		   }
	   }
   } 
}

第四题：请用for循环实现。
我国最高山峰是珠穆朗玛峰，8848米。现在我有一张足够大的纸，它的厚度是0.01米。
请问，我折叠多少次，可以折成珠穆朗玛峰的高度。
double houDu = 0.0001;
int count = 0;
do {
     houDu = houDu * 2;
     count++;
}while(houDu < 8848);
System.out.println("需要折叠 " + count);
第五题：
	九九乘法表。
for (int h = 1; h <= 9; h++) {
  for(int l =1; l <= h; l++) {
    System.out.print(l + "*" + h + "=" + (l * h) + '\t');
  }
   System.out.println(); // System.out.println("");
}
