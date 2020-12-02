day18
1.	TreeSet集合
 底层是一个二叉树，存储对象的时候，依据对象的自然顺序，自定义对象具备自然顺序，实现Comparable接口，重写compareTo方法。Person写的，姓名为主要排序条件，年龄次要的条件。按照人的年龄为主要的条件，排序。
 JDK中，提供了另外的一种排序方式，比较器排序，比较器接口java..util.Comparator，
 TreeSet集合的构造方法中，传递一个比较器对象，按照比较器排序了。
 自定义比较器，写一个类，实现Comparator接口，重写compare方法
2.	泛型
JDK1.5版本出现的一种安全机制
格式:
  集合类<数据类型> 变量 = new 集合类<数据类型>();
  数据类型，指定了这个集合，只能存储这个类型的对象

泛型的出现，将安全问题，由运行时期，提前到了编译时期
ArrayList<E>  boolean add(E e)  E:看成是一个变量
ArrayList<String>  所有的E都变成了String

好处，减少了代码，使用泛型避免类型的强制转换
泛型什么时候该写泛型：当你用一个类的时候，发现类的右边<>使用泛型了
注意：存储基本数据类型，泛型必须写8个对象包装类

泛型擦除(擦除法实现的伪泛型)
无论何时定义泛型类型，都会自动提供一个相应的原始类型，这个原始类型就是去掉类型参数的泛型类型名。
Java语言的泛型采用的是擦除法实现的伪泛型，泛型信息(类型变量，参数化类型)编译之后通通被擦除了。
擦除法的好处:实现简单，非常容易Backport，运行期也能够节省一些类型所占的内存空间。
擦除法的坏处:这种机制实现的泛型远不如真泛型灵活和强大。
Java选取这种方法是一种折中，Java最开始的版本是不支持泛型的，为了兼容以前的库而不得不使用擦除法。

3.	自定义的泛型，保证数据安全
  定义一个工厂，生产对象的，制造对象，下订单
  泛型类，声明类的时候，加上泛型
  泛型方法, 在方法上定义泛型，和类无关
  泛型接口

4.	泛型的通配符和限定
 通配，所有的都匹配 *  *.java   XXX.*  ?
 员工和经理的案例
   员工：姓名，年龄，工资，工作
   经理：姓名，年龄，工资，奖金，工作
员工和经理的共性内容，抽取一个父类，抽象类，抽象类的构造方法中，给成员变量赋值。? extends Company 限定的是父类，子类? 通配符，传递Company的任意子类，泛型的上限限定。
   上限限定  ? extends E  传递E类型，E的子类类型
   下限限定  ? super E    传递E类型，E的父类类型
5.	Map集合
  映射，键映射到值的集合，Map体系的集合，存储对象的时候，一次存储两个对象，一个称作键，一个称作值，双列集合
	一个键，最多只能映射一个值
	不允许出现重复键
6.	Map接口中的方法
	V put(K,V) 将键值对存储到集合。返回值，存储了重复的键，返回被覆盖之间的值
	V get(K) 根据键，获取值，传递一个键，返回键映射的值，没有这个键，返回null
	V remove(K)移除指定的键，对应的值，返回被移除前的值，没有移除成功，返回null
	boolean containsKey(K)判断集合中，有没有这个键，有返回true
	boolean containsValue(V)判断集合中个，有没有这个值，有返回true
	Collection values()将集合中的所有值，保存到Collection集合
	Set<K> keySet()键存储到Set集合
	Set<Map.Entry<K,V>>映射关系对象保存到Set集合

7.	获取Map集合中键值对的方式
	第一种，利用Map中的一个方法keySet()，Map集合中的键，存储到Set集合
	迭代Set集合，获取出来的是所有的键
	通过键获取值，Map接口中的get方法

	第二种利用Map集合中的键值对映射关系获取
	Map接口中有一个方法entrySet(),获取键值对的映射关系对象Entry,将这个对象Entry存储到了Set集合
	迭代Set集合，获取出来的Set集合中存储的是映射关系对象Entry
	通过关系对象的方法 getKey  getValue

8.	Map中接口中，有一个子接口
 interface Map{
    interface Entry{}描述键值对映射关系的
    Entry接口中的两个方法 getKey()  getValue()
    Entry -- 结婚证  getKey()获取键  getValue()获取值
 }
9.	HashMap类
  底层也是哈希表，允许存储null值，null键
  不同步，线程不安全，执行效率高
  保证：存储到HashMap集合中的键，唯一性
  自定义对象，保证唯一性，hashCode equals方法
  HashMap存储自定义对象，当作键，两种方式获取

10.	作业，保持清醒的头脑，技术简单
  集合的嵌套 Map
数据结构，百度容器，存javase容器，javaee容器
javase存储学生 001 张三  002 李四
javaee存储学生 001 王武  002 赵柳
HashMap<String,HashMap<String,String >>  百度 = new HashMap<>();
HashMap<String,String> javase = new HashMap<String,String>();
HashMap<String,Strng> javaee = new HashMap<String,String>()
百度.put("javase",javaseMap);
百度.put("javaee",javaeeMap);
javaseMap.put("001","张三")
javaeeMap.put("001","王武")
