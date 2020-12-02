1：对两个整数变量的值进行互换。
提示：
第一种：使用第三方变量
public class Test {
  public static void main(String[] args) {
     int x , y , z;
     x = 10;
     
第二种：不需要第三方变量，思考下异或运输符的特点。

2：请写出下列各题的结果：分析得出结果，先不要编译运行
第一题
int x = 1,y = 1;
if(x++==2 & ++y==2)
{
	x =7;
}
System.out.println("x="+x+",y="+y);  x=2,y=2

---------------------------------------------------
第二题
int x = 1,y = 1;

if(x++==2 && ++y==2)
{
	x =7;
}
System.out.println("x="+x+",y="+y);x=2,y=1

---------------------------------------------------
第三题
int x = 1,y = 1;

if(x++==1 | ++y==1) // |有真则真
{
	x =7;
}
System.out.println("x="+x+",y="+y);x=7,y=2

---------------------------------------------------
第四题
int x = 1,y = 1;

if(x++==1 || ++y==1)
{
	x =7;
}
System.out.println("x="+x+",y="+y);x=7,y1=2

---------------------------------------------------
第五题
boolean b = true;

if(b==false) 
	System.out.println("a");
else if(b)
	System.out.println("b");b
else if(!b)
	System.out.println("c");
else
	System.out.println("d");

---------------------------------------------------
第六题
int x = 2,y=3;

switch(x)
{
	default:
		y++;// y=4
	case 3:
		y++;// y=5
		break;
	case 4:
		y++;
}

System.out.println("y="+y);y=5

---------------------------------------------------

2：编写代码实现如下内容：

第一题（1）：
考试成绩分等级。
	90~100	A等。
	80-89	B等。
	70-79	C等。
	60-69	D等。
	60以下	E等。
请根据给定成绩，输出对应的等级。
//import java.util.Scanner;
public static void main(String[] args){

  /* Create a Scanner
  Scanner input = new Scanner(System.in);

  System.out.print("Enter a number of score: ");
  double score = input.nextDouble();
 */
  
  int score = 59;
  if (90 <= score && score <= 100) {
      System.out.println("A等");
  }
  else if (80 <= score && score <=89) {
      System.out.println("B等");
  }
  else if (70 <= score && score <= 79) {
      System.out.println("C等");
  }
  else if (60 <= score && score <=69) {
      System.out.println("D等");
  }
  else if (score < 60) {
      System.out.println("E等");
  }
  else
      System.out.println("您输入的成绩有误，请重新输入_____);
}
第一题（2）：
一年有12个月，每个月分别对应于不同的季节。
  请根据给定的月份，输出对应的季节。

/* Create a Scanner
  Scanner input = new Scanner(System.in);

  System.out.print("Enter a number of month: ");
  int  month = input.nextInt();
 */
//冬天：12，1，2  春天：3-5  夏天：6-8  秋天：9-11
		//给定一个月份
		int month = 12;
		//使用switch来判断该月份对应的季节
		switch(month){
		case 1:
		case 2:
		case 12:
			System.out.println("冬天");
			break;
		case 3:
		case 4:
		case 5:
			System.out.println("春天");
			break;
		case 6:
		case 7:
		case 8:
			System.out.println("夏天");
			break;
		case 9:
		case 10:
		case 11:
			System.out.println("秋天");
			break;

		default:
			System.out.println("您输入的月份有误");
			break;

第三题：
我国最高山峰是珠穆朗玛峰，8848米。现在我有一张足够大的纸，它的厚度是0.01厘米。
请问，我折叠多少次，可以折成珠穆朗玛峰的高度。(思考题)0.01厘米=0.0001米
/* 第一种do while循环
int houDu = 0.0001
int count = 0;
do {
    houDu = houDu * 2;
    count++;
}while(houDu < 8848);
*/
/* 第二种while循环
int houDu = 1;
int count = 0;
while (houDu < 88480000) {
    houDu = houDu * 2;
    count++;
}
System.out.println(count);
*/
/*
	珠峰问题：
	我国最高山峰是珠穆朗玛峰，8848米。现在我有一张足够大的纸，它的厚度是0.01厘米。
	请问，我折叠多少次，可以折成珠穆朗玛峰的高度。

	分析：
		1、需要一个目标高度，需要记录纸的当前厚度，折叠的次数
		2、统一变量的单位目标高度为88480000，纸的厚度为1
		3、每回纸的厚度都会变成之前厚度的2被
		4、折纸的厚度超过或者等于珠峰高度时，不再折叠
*/
class Test3
{
	public static void main(String[] args) 
	{
		//目标高度
		int fHeight = 88480000;
		//当前纸张的高度
		int nHeight = 1;
		//折叠次数
		int times = 0;
		//只要纸张的厚度小于珠峰的高度，就一直循环
		while(nHeight < fHeight){
			//每次对折，纸张厚度变为原来的二倍
			nHeight = nHeight*2;
			//每折叠一次，则折叠次数加1
			times++;
		}
		System.out.println(times);
		System.out.println("纸张最后一次对折前的高度："+nHeight/2);
		System.out.println("珠峰的高度："+fHeight);	
		System.out.println("纸张当前的高度："+nHeight);
	}
}