1：
练习：
    类比Person类定义一个Transformers类(变形金刚)
	要求至少三个属性
	要求至少三个方法（参数，返回值不限）

/*
	类比Person类定义一个Transformers类(变形金刚)
	要求至少三个属性
	要求至少三个方法（参数，返回值不限）
*/
class Test
{
	public static void main(String[] args)
	{
		//创建对象
		Transformers tr = new Transformers();
		tr.name = "擎天柱";
		tr.color = "红色";
		tr.isGoodMan = true;

		//调用擎天柱这个对象的方法
		//调用变形方法
		tr.trans();
		//调用返回值为String类型的胜利方法
		System.out.println(tr.vic());
		//调用参数为Transformers的结盟方法
			//需要创建一个Transformers的对象作为参数传递给该方法
					Transformers tr2 = new Transformers();
					tr2.name = "大黄蜂";
					tr2.color = "黄色";
					tr2.isGoodMan = true;
		tr.method(tr2);
	}
}

class Transformers
{
	String name;
	String color;
	boolean isGoodMan;
	//变形的方法
	public void trans()
	{
		System.out.println("我变了");
	}
	//胜利的方法
	public String vic()
	{
		return "我再次拯救了地球";
	}
	//结盟
	public void method(Transformers trans){
		System.out.println(name+"与"+trans.name+"结盟了！");
	}
}

2:
请设计一个类MyMathTool，这个类包含如下操作：
	A:求两个数的和。
	B:判断两个数是否相等。
	C:输出九九乘法表。

class Test2 
{
	public static void main(String[] args) 
	{
		MyMathTool mmt = new MyMathTool();
		mmt.x = 10;
		mmt.y = 20;

		System.out.println(mmt.sum());
		System.out.println(mmt.sum(100,20));
		System.out.println(mmt.isEq(100,20));
		System.out.println(mmt.out99());如果返回值类型为void则不能打印
	}
}

class MyMathTool
{
	//求两数和：在这个需求当中包含两个数字，请问将这两个数字定义在成员变量位置，还是定义在局部变量位置？
	//定义在成员变量位置
	int x;
	int y;

	public int sum(){
		return x+y;
	}
	//定义在局部变量位置,建议使用这种方法
	public int sum(int x,int y){
		return x+y;
	}
	//判断两数是否相等：
	public boolean isEq(int x,int y){
		return x==y;
	}
	//输出九九乘法表
	public void out99(){
		//九九乘法表代码
		for (int i = 1; i <= 9; i++) {
		  for(int j = 1; j < i; j++){
		  System.out.println( j  + "*" + i + "=" + ( j * i) + '\t');
		  }
		  System.out.println();
		}
	}
		//System.out.println("我是99乘法表");
}

最后在测试类Test中进行测试。
	A:必须能实现，给两个数，返回和
	B:返回类型不限，但是必须有判断两数是否相等的标志
	C:一定是void返回值



3:
思考：
成员变量和局部变量的区别?
习题1当中，变量应该放到成员变量还是放到方法当中的局部变量的地方？

一般数据我没有说什么类型，默认int类型。适用于我讲课。