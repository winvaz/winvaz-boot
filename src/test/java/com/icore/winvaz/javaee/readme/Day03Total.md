javascript技术:
 * 函数:
   * 什么是函数:了解一下.
   * 参数:
     * 形参:定义函数时,使用的参数.
     * 实参:调用函数时,传入的参数.
   * 函数包含:
     * function关键字
     * 函数名
     * 可能包含参数.
     * 函数体
   * 如何定义函数:
     * (最多)function 函数名(参数){函数体}
     * var 函数名 = function(参数){函数体}
     * (特殊)var 函数名 = new Function(参数n,函数体);
   * 变量的作用域:
     * 变量
       * 全局变量
       * 局部变量
     * 作用域
       * 全局域
       * 函数域
     * 特殊情况:
       * 定义局部变量,不使用var时:自动会定义为全局变量.
       * 全局变量与局部变量同名时:在函数域中只能访问到局部变量.
 * BOM:浏览器对象模型
   * 介绍:
     * window对象:窗口对象,一个页面就具有一个window对象.
       * Navigator对象:浏览器对象,用于存储当前浏览器的基本信息.
       * Screen对象:屏幕对象,用于存储当前浏览器显示的一些信息,例如:高度、宽度等.
       * Location对象:本地对象,用于存储当前浏览器所访问网站的地址信息.
       * History对象:历史对象.
         * 用于存储当前浏览器所访问过的网站地址信息.
	 * 浏览器中的历史列表功能,就是历史对象提供的.
       * (DOM)Document对象:文档对象.
         * javascript解析HTML页面时,将HTML页面解析为文档.
   * 研究BOM对象:
     * window对象:
       * 介绍:
         * window对象是浏览器中最顶层的对象.
	 * window对象会在页面初始化完毕之后,自动创建.
       * 属性:
         * window对象下的其他对象,都是以window对象的属性形式存在.
	   * Document对象
	   * Screen对象
	   * Location对象
	   * History对象
	   * Navigator对象
	 * name属性:窗口名称并不是title标签的文本内容.

	 * (面试)target:
	   * _blank:在新的窗口打开.
	   * _self:在当前窗口打开.
	   * _parent:在父窗口打开.
	   * _top:在顶层窗口打开.
      * 方法:
        * alert()
	* confirm()
	* prompt()
	* open()和close()
	* setTimeout()和clearTimeout()
	* setInterval()和clearInterval()
      * window对象的属性和方法在调用时,可以省略"window.".
   * Navigator对象
     * 方法在实际开发中,基本不用.
     * 属性:
       * appName属性:浏览器的名称.
       * appVersion属性:返回的4.0内容,表示浏览器内核.
       * platform属性:返回的是浏览器所在操作系统平台信息.

       * 不同浏览器支持javascript就不同?使用不同的浏览器内核.
         * IE:IE6.0\7.0		IE8.0\9.0	IE10\11
	 * 其他浏览器:webkit(苹果)
	   * 苹果浏览器
	   * 谷歌浏览器:即将推出自主内核.
	   * 火狐浏览器:即将推出自主内核.
	 * 众多国内浏览器:
	   * QQ浏览器:号称自主内核.
	   * 百度浏览器:号称自主内核.
	   * 遨游浏览器:号称自主内核.
	   * 其他浏览器:360\搜狗等等.

   * Screen对象:
   * Location对象
     * 属性:
       * host属性:主机IP+端口号.
       * hostname属性:主机IP.
       * port属性:端口号.
       * href属性:URL的完整路径.
     * 方法:
       * reload()方法:重新加载页面.
       * replace()方法:利用新的页面替换当前页面.
   * History对象
     * 属性:
       * length属性:返回历史列表中页面的个数.
     * 方法:
       * back():浏览器的后退功能.
       * forward():浏览器的前进功能.
       * go():跳转到指定页面的(该页面一定是在历史列表中).
         * 浏览器的历史列表中:http://www.baidu.com
	 * http://www.sina.com.cn ,不在浏览器的历史列表中.
 * DOM
   * 介绍:
     * 定义:尽量使用W3C的定义.
     * 特点:
       * DOM是独立于任何平台和语言的.
       * DOM可以访问和修改一个文档(HTML页面).
       * DOM将文档(HTML页面)分成内容和结构.
         * 内容(数据):页面中文本、图片、音频和视频等.
	 * 结构:其实就是指页面的标签.
       * DOM可以将HTML页面,实现动态变化.
       * DOM将HTML页面利用树形方式解析.
     * 组成部分:
       * DOM 核心
       * DOM HTML
       * DOM XML
       * DOM CSS
     * DOM分级:
       * 1级:DOM 核心和DOM HTML
       * 2级:DOM的内容已经完整
       * 3级:载入、保存和验证的方法.
       * 0级:
         * 不是W3C提供的标准.
	 * 0级包含只是一些有关Document对象的内容.
   * DOM树结构
   * DOM的具体内容:
     * document对象:文档对象
       * documentElement属性:直接指向HTML页面中的根标签.
         * 作用:当无法准确定位时,利用该属性获取到HTML页面的根标签,再根据DOM节点树进行解析.
       * 查找HTML页面的标签:
         * getElementById(id属性值):返回指定id属性值的标签.
	 * getElementsByName(name属性值):返回指定name属性值的标签集合.
	 * getElementsByTagName(标签名):返回指定标签名的标签集合.

	 * 如果通过name属性值或标签名获取到的标签就一个:返回的永远都是集合.
       * 创建HTML页面的标签:
         * 元素节点:createElement(标签名)
	 * 文本节点:createTextNode(文本内容)
	 * 属性节点:
	   * 并没有使用创建属性节点的方法(createAttribute()).
	   * 利用Element对象的设置属性:setAttribute(name, value)
     * element对象:元素对象
       * 注意:利用节点解析HTML页面时,有些内容过于复杂.而利用元素方式解析,相对比较简单.
       * 操作标签的属性:
         * 获取属性:getAttribute(属性名)
	 * 设置属性:setAttribute(属性名, 属性值)
	 * 删除属性:removeAttribute(属性名)
       * 在某个标签中,查找具体标签:只有getElementsByTagName()方法有效.
         * 面试题:下列哪些方法是元素的方法?CD
	   A getElementById()
	   B getElementsByName()
	   C getElementsByTagName()
	   D getAttribute()
     * node对象:节点对象
       * 节点名称、类型和值
         * nodeName:其内容是给定节点的名字.
	   * 元素节点 - 就是标签名.
	   * 属性节点 - 就是属性名.
	   * 文本节点 - 是固定写法"#text".
	 * nodeType:返回一个整数，这个数值代表着给定节点的类型.
	   * 元素节点 - 1
	   * 属性节点 - 2
	   * 文本节点 - 3
	 * nodeValue:返回给定节点的当前值.
	   * 元素节点 - 是Null值.
	   * 属性节点 - 是属性的值.
	   * 文本节点 - 是文本内容.
       * 遍历节点:
         * 父节点:parentNode
	 * 子节点:
	   * childNodes:返回所有子节点的集合.
	   * firstChild:返回第一个子节点.
	   * lastChild:返回最后一个子节点.
	 * 同辈节点
	   * 上一个兄弟节点:previousSibling
	   * 下一个兄弟节点:nextSibling
       * 节点属性(了解)
         * 节点属性:attributes,返回当前标签的所有属性集合.
	 * 是Node对象提供的一个接口.
	 * 利用Node对象获取到元素节点的属性内容.
	 * 相当于Element对象的getAttribute()方法.
	 * 标签.attributes.getNameItem():获取到我们想要的属性.
	   * 等价于Element对象的getAttribute()方法.
	   * attributes属性:不是所有浏览器都支持.
       * 检测子节点和属性:
         * 检测子节点:hasChildNodes(),返回Boolean类型值,true表示具有子节点.
	 * 检测属性:hasAttributes(),返回Boolean类型值,true表示具有属性.
	 * 通过以上测试,属性节点并不是子节点!
       * 操作DOM节点树:
         * 插入节点:
	   * parent.appendChild(newChild):插入到父节点下的子节点列表的最后.
	   * parent.insertBefore(newChild,oldChild):插入到目标节点的前面.
	   * 没有insertAfter()方法.
	 * 删除节点:
	   * parent.removeChild(child)
	 * 替换节点:
	   * parent.repalceChild(newChild,oldChild)
       * this的用法:明确指代HTML页面中的标签.

javascript在JavaEE开发的作用:
 * 问题:
   * 很多人不重要javascript技术.
 * 原因:
   * javascript很简单.
   * 一般情况,入职后第一个工作:前端开发.
 * javascript技术的发展:
   * 每年都有全球开发语言排行.
   * 公司需求:专职的javascript开发人员.
   * javascript只是脚本语言.
     * 只能开发客户端.
     * 还能开发服务器端 - Node.js.
     * 还能开发移动端 - phoneGap.
   * javascript有可能成为下一个企业级开发语言.