0：
	冒泡排序*****
	选择排序***
	二分法查找***

1:正则表达式(掌握用法)
	(1)符合一定规则的字符串。
	(2)规则：
		A:字符
			x 字符 x
			\\ 反斜线字符
			\r 回车符
			\n 换行符

		B:字符类
			[abc] a、b 或 c
			[^abc] 任何字符，除了 a、b 或 c  
			[a-zA-Z] a 到 z 或 A 到 Z，两头的字母包括在内 
			[0-9] 包括0-9之间的字符

		C:预定义字符类
			. 任何字符 
			\d 数字：[0-9] 
			\w 单词字符：[a-zA-Z_0-9] 

		D:边界匹配器
			^ 行的开头 
			$ 行的结尾 
			\b 单词边界
				就是指这里出现的不能使单词字符。 
					he;wor xixi
				
		E:Greedy 数量词 
			X? X，一次或一次也没有 
			X* X，零次或多次 
			X+ X，一次或多次 
			X{n} X，恰好 n 次 
			X{n,} X，至少 n 次 
			X{n,m} X，至少 n 次，但是不超过 m 次 

		F:组的概念(按照小括号从左开始编号，每一个对应着一个组)
			(a(b(c)d(e)))

			第一组：a(b(c)d(e))
			第二组：b(c)d(e)
			第三组：c
			第四组：e

			将来我们就可以通过编号来获取组中内容。组0表示整个表达式。
	(3)通过String类的功能使用正则表达式
		A:判断功能
			public boolean matches(String regex)
		B:分割功能
			public String[] split(String regex)
		C:替换功能
			public String replaceAll(String regex,String newString)

		记住：
			叠次：
				在同一个字符串中，右边引用左边："(.)\\1+"
				在后一个字符串中引用前面的字符串的组内容："(.)\\1+","$1"
	(4)案例：
		A:校验电话号码
		B:校验邮箱
		C:我要学编程

2:日期(理解)
	(1)Date:
		表示一个日期类。大部分方法过时，被Calendar替代。

		构造方法：
			Date d = new Date();
			Date d = new Date(long time);
		获取毫秒值：
			getTime();
	(2)DateFormat:
		对日期进行格式化和对字符串解析的类

		String -- Date:
			解析：parse(转换的意思)

		Date -- String:
			格式化：format(“要符合的日期格式”)
	(3)Calendar:
		日历类，对日期进行了更细的划分，可以获取日历的每个字段值。

		根据日历字段获取对应的值：
			get(Calendar.相应字段)
		设置年月日：
			一般情况下，都是由getInstance()获取Calendar对象
			在特殊业务需求时，需要进行判断
			set(int year,int month,int date)
		修改指定日历的值：
			add(int field,int value)

3:System(掌握)
	(1)系统类，通过了静态的方法供我们使用。
	(2)要掌握的功能：
		A:exit(int i)
		B:currentTimeMillis()
		C:arraycopy()
	gc():跟finalize()进行辨析
