HTML、CSS及JavaScript前端技术:
 * Eclipse或MyEclipse
   * Aptana插件:
     * 介绍:
       * 就是Eclipse或MyEclipse的插件.
       * 作用:提供代码提示功能.
       * 版本:
         * 最新版本:3.x,在线安装.
	 * 使用版本:2.x,离线安装.
     * 制作插件包:
     * 安装插件包:
   * Firbug工具:
     * 介绍:
       * 提供JavaScript代码的调试功能,类似于MyEclipse的debug功能.
       * 就是火狐浏览器的一个插件.
 * HTML
   * 测试以下Aptana插件如何使用.
 * CSS
   * HTML、CSS及JavaScript技术的关系:
     * HTML:就是素描.
     * CSS:就是彩色.
     * JavaScript:就是动画.
   * 介绍:
     * 版本历史:
       * CSS1
       * CSS2:学习这个版本.
       * CSS3:配合HTML5版本.
     * CSS文件的扩展名为".css"
   * 在HTML页面如何使用CSS:
     * 第一种:利用页面标签的style属性.
     * 第二种:利用页面的style标签.
     * (建议)第三种:定义独立的css文件,在页面中利用link标签引入.
   * CSS语法:
     * 选择器 {声明1;声明2}
       * 选择器:用来找到对应页面的元素内容(标签).
       * 声明:用来设置对应页面的元素内容的样式.
         * 属性:使用的是CSS的什么内容.
	 * 值:使用css内容对应的值.
       * 声明中的属性和值,是以":"分开.
       * 多个声明之间,利用";"隔开.
   * CSS注释:/***/
   * CSS选择器:
     * 元素选择器
     * 类选择器
     * ID选择器
     * 属性选择器:属性可以定义表达式
     * 后代选择器
     * 子元素选择器
     * 相邻选择器
   * CSS的伪类和伪元素(了解).
   * CSS的样式:
     * 背景
     * 字体
     * 文本
     * css样式内容对于我们来讲:
       * 不会设计页面的内容.
       * 页面中的逻辑内容,例如登录功能.
   * css的框模型,又叫做盒子模型.
 * JavaScript:
   * 介绍:
     * 发展历史:
       * 95年网景公司推出一款产品:LiveScript.
       * 联合SUN公司共同推出一款产品:JavaScript.
         * 原因:当时有一款发开语言非常火:Java
	 * 问题:java与javascript的关系?雷锋与雷峰塔的关系.
       * 微软公司IE浏览器3.0版本,独立推出一款产品:JScript
         * 问题:javascript与jscript的关系?没有关系.
       * 开发人员来讲有个问题?选择哪个版本?
       * 联合SUN公司、微软公司和欧洲计算机制造商协会:ECMAScript
       * javascript发展至今,有一个问题存在.
         * javascript的版本不统一,至今都没解决.
	 * 开发javascript代码时,一定考虑浏览器的兼容性.
   * javascript组成部分:
     * ECMAScript
       * 之后发展成了一种标准:其实就是开发语言的语法,例如变量的命名、关键字等.
       * 市场上存在除javascript外的其他脚本语言:actionscript.
         * 几乎市面上所有的脚本语言都具有一个标准:ECMAScript.
	 * ECMAScript不是专属于javascript的.
       * ECMA最新版本:ECMA - 262 3rd标准.
     * BOM:浏览器对象模型.
       * 说明浏览器中存在一系列的对象.
       * 举例:window对象.
     * DOM:文档对象模型
       * javascript脚本代码:让HTML页面中的内容,呈现动态变化.
       * javascript脚本代码是如何解析HTML页面的?利用DOM的内容.
       * javascript脚本代码解析HTML页面时,使用DOM内容解析.
         * javascript解析HTML页面为一个文档.
   * 如何在HTML页面中使用javascript:
     * 在HTML页面直接嵌入javascript代码.
     * 在HTML页面引入外部独立的javascript文件.
 * ECMAScript:
   * 介绍:
     * javascript的语法与java的语法基本类似:
       * 将近80%的内容是相同的.
       * 将近20%左右的内容是不同的.
     * 我们主要学习那20%不同的内容即可.
   * 变量:参看我们的代码.
   * 保留字与关键字.
   * 原始值与引用值:
   * 原始类型与引用类型:
     * 类似于java中的int与Integer的区别.
     * 原始类型:
       * undefined
         * 简单理解:未定义的.
	 * 问题:
	   * 一个变量定义不初始化:存在,但没值.(提示)
	   * 一个变量就没定义:根本不存在.(报错)
       * null
         * 在javascript中,null值与undefined值相同.
       * boolean
       * number
         * javascript中的number类型,既可以存储32位,又可以存储64位.
	 * javascript运算时,无论使用八进制还是十六进制,结果都为十进制.
	 * javascript中的number类型中,具有一个值:
	   * NaN,表示当前值不是一个数字值.
	   * isNaN()方法:返回Boolean类型,true表示不是数字值,false表示是数字值.
       * string
       * 关键字:typeof,判断当前变量是什么类型.
     * 引用类型:其实都在javascript中的内建对象.
       * Object
         * 问题:
	   * ECMAScript,其实就是javascript中,是没有类的概念.
	   * javascript其实还是一个面向对象的语言.
	   * ECMAScript具有叫做内建对象的内容,类似于Java中的JDK内容.
	 * 介绍:
	   * new Object():获取一个javascript对象.
	   * 在javascript中Object也是所有类型的父类.
       * Boolean
       * Number
         * 其实也是javascript的内建对象.
       * String
   * 转换:
     * 转换成字符串:toString()方法
       * Boolean值、数字值及字符串具有toString()方法.
       * 如果是数字值时,数字值本身可以利用八进制、十六进制及十进制,转换成字符串时,都是十进制表示.
     * 转换成数字:
       * 分类:parseInt()和parseFloat()
       * 如果转换成数字的字符串:
         * (注意)字符串本身就不是什么数字,进行转换后,得到NaN.
	 * 字符串本身就是一些简单数字,可以转换成功的.
     * 强制类型转换:
       * Boolean()
       * Number():最麻烦的,特殊注意.
       * String()
   * 运算符:
     * 加号运算符和减号运算符:可能出现面试题.
     * 全等号与非全等号:
       * 全等号(===):判断值与类型,只有值和类型都相同的情况下,返回true.
       * 非全等号(!==):判断值与类型,只有值和类型都不同的情况下,返回true.
   * 语句:
     * fornin语句:
       * 就是for循环的加强用法.
       * forin语句中的i依旧是数组中的角标.
     * with语句(不掌握).