XML技术:
 * 定义:可扩展标记语言.
 * 版本:
   * 1.0版本:我们使用该版本.
   * 1.1版本:与1.0版本不兼容.
 * 用途:
   * 用于存储数据内容.
   * 用于软件工程的配置信息.
 * 独立文件:扩展名为".xml".
 * 特点:
   * XML文件的标签,用户可以自定义.
 * 第一个XML文件:
   * MyEclipse安装Aptana插件:
     * 还提供了xml文件的编辑器.
     * Aptana提供的xml编辑器有Bug(不能用).
   * <?xml version="1.0" encoding="UTF-8"?>
     * 叫做XML文件的声明(约束).
     * 格式:<?xml ?>
     * 属性:
       * version属性:设置使用的XML版本.
       * encoding属性:设置当前XML文件的编码格式.
     * 注意:
       * XML声明必须出现在XML文件的0行0列处.
   * <xml-body></xml-body>
     * MyEclipse提供的XML文件模版自动生成的根标签.
   * 标签(元素)
     * 分类:
       * 根标签(元素)
       * 起始标签:有开始标签,有结束标签.例如<title></title>
       * 空标签(元素):一个标签有开始又有结束.例如<br />
     * 标签名:
       * 可以包含字母、数字、下划线（_）、中划线（-）、冒号（:）和点号（.）.
       * 不能以数字、中划线和点号开始.
       * 不能包含空格.
       * 尽量不要出现英文冒号“:”.
       * 尽量不要以字符“xml”开始.
       * XML命名区分大小写.
   * 属性:
     * 注意:
       * 必须出现在开始标签内.
       * 一个标签内可以包含0 - N个属性.
       * 一个标签内不能出现两个同名的属性.
     * 格式:属性名=属性值
   * 注释:
     * 格式:<!--  -->
     * 注意:
       * 不能以"--->"结尾.
       * 注释中不能包含"--".
       * 不能以“--->”结尾.
 * XML高级:
   * 转义字符:用于出现次数低.
     * < - &lt;
     * > - &gt;
     * ' - &apos;
     * " - &quot;
     * & - &amp;
   * CDATA段:用于出现次数多.
     * 格式:<![CDATA[编辑内容]]>
   * 处理指令(了解)
     * <?xml-stylesheet type="text/css" href="a.css" ?>
     * 格式:<?xml-stylesheet ?>
     * 属性:
       * type属性:指定当前使用的是css.
       * href属性:设置引入css文件的路径.
 * DOM解析XML:
   * XML文件的解析器.
   * DOM解析XML时:只有getElementsByTagName()方法有效.
   * 提供了保存方法save().

   HTML、CSS和JavaScript(前端技术)的关系:HTML当作一幅画(素描)、CSS是彩色、javascript是动画.

DTD技术:
 * 介绍:
   * 作用:用来约束XML文档定义的.
   * 文档类型定义.
 * 如何来使用DTD:
   * 内部DTD:
     <!DOCTYPE 根元素名[
	元素描述
     ]>
   * 外部DTD
     * 定义一个独立的DTD文件:扩展名为".dtd".
     * 在XML文件中如何引入:
       <!DOCTYPE 根元素名 SYSTEM "外部DTD的URI">
   * 公用DTD
     * 含义:其实就是外部DTD的一种,只是由权威机构定制.
     * 格式:
       <!DOCTYPE 根元素 PUBLIC "DTD的标识名" "公用的DTD的URI">
 * 如何定义DTD内容:
   * 定义元素(标签)
     * 格式:
       <!ELEMENT 元素名 元素描述>
     * 元素名:是指XML文件中所使用的标签名称.
     * 元素描述:用于描述当前标签是如何的.
       * (#PCDATA):表示当前标签包含的内容为文本内容.
       * ANY:表示当前标签包含是任意内容.
       * EMPTY:表示当前标签包含的内容为空.
       * (name,age,sex):表示当前标签包含name、age和sex标签,并且每个标签只能出现一次,必须按照此顺序.
     * 元素常用:
       * 有序的子元素:子元素之间使用","分割即可.
       * 互斥的子元素:name age sex
         * 所谓互斥的子元素,就是几选一的问题.
	 * 子元素之间使用"|"分割.
       * 子元素出现的次数:
         * 默认情况下,子元素只能出现一次.
	 * "+" - 表示子元素可以出现1次或多次.
	 * "*" - 表示子元素可以出现0次或多次.
	 * "?" - 表示子元素可以出现0次或1次.
       * 无序的子元素:(name|age|sex)+
   * 定义属性
     * 格式:
       <!ATTLIST 元素名 属性名 属性类型 属性规则>
     * 元素名:指定当前属性所在的标签名称.
     * 属性名:指定XML文件标签所使用的属性名称.
     * 属性类型:
       * CDATA:表示属性值为文本内容.
       * en1|en2|en3:表示枚举(就把枚举当作选择题).
       * NMTOKEN:表示合法的XML名称.
       * ENTITY:表示引入外部实体.
     * 属性规则:
       * #REQUIRED:表示必需的属性,没有的话是不行的.
       * #IMPLIED:表示可有可无.
       * #FIXED:表示固定值,定义时必须指定固定值.
   * 定义实体(不是重点):
     * 实体的定义:其实就是简称.例如"北京市百度教育科技有限公司",定义一个简称叫"传智".
     * 一般实体:
       * 位置:在XML文件中使用.
       * 定义:
         <!ENTITY 实体名 "实体值">
	 * 实体值:就是全称.
	 * 实体名:就是简称.
       * 调用:&实体名;
     * 参数实体(了解):
       * 位置:在DTD文件中使用.
       * 定义:
         <!ENTITY % 实体名 "实体值">
       * 调用:%实体名;