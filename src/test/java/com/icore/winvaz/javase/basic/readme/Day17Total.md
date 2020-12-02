day17
1.	键盘录入姓名和成绩存储到ArrayList集合
2.	六位随机验证码
3.	问题：
  集合是干什么的:存储对象，不存储基本类型
  数组和集合区别：长度可变的集合，不可变的数组，集合存对象，数组存基本类型和对象
  List特点：自己迭代器，下标，有序，重复
  ArrayList：底层数组结构，查询快，增删慢，线程不安全，10个位置，每次增长50%
4.	去掉ArrayList中的重复元素
List本身，允许重复的，必须完成去掉重复的工作，但是List没有这个功能，我们自己写出来这样的功能.集合中存储自定义的对象，并去掉重复.
Person对象，如果姓名和年龄，同样，看做是同一对象。contains方法原理，凭什么判断对象是否包含在集合中。contains方法的实现原理，在于对象中的equals方法，自动调用对象中的equals方法，来进行判断
5.	Vector类
  集合框架在JDK1.2版本后出现的，Vector类出现在JDK1.0版本。没有集合框架以前，存储对象只能依赖Vector。
  Vector底层数据结构也是可变数组实现，线程是安全的，运行效率慢，每次数组扩容100%。从JDK1.2版本开始，此类被ArrayList取代。Vector死了，郁郁而终了.

6.	LinkedList类
LinkedList底层数据结构链表实现，记录对象地址的方式存储
查询慢，增删快，线程也是不安全的，执行效率高
LinkedList类的特有方法
addFirst(Object obj)将元素添加到链表开头
addLast(Object obj)将元素添加到链表结尾
Object getFirst()获取链表的开头
Object getLast()获取链表的结尾
Object removeFirst()移除链表的开头，返回被移除前的对象
Object removeLast()移除链表的结尾, 返回被移除前的对象

JDK1.6开始，有n个新的方法，把以前的方法取代,不做掌握
offerFirst()-->addFist() 返回值不同add开头的没有返回值，offer开头返回boolean
offerLast()-->addLast()
peekFirst()-->getFirst()get开头没有元素，出异常，peek开头没有元素返回null，没异常
peekLast()-->getLast()
pollFirst()-->removeFirst()
pollLast()-->removeLast()

7.	LinkedList练习
LinkedList模拟一个数据结构，堆栈，和队列，堆栈数据先进后出，队列先进先出 
8.	Set集合
Set集合派系的特点：
	不允许存储重复的元素
	没有下标
	无序的集合，存储和取出的顺序不一定的
  Set接口中的方法，和Collection接口中的方法一致

9.	HashSet类
HashSet类的特点
	底层数据结构哈希表
	HashSet本身没有功能，功能来自于HashMap
	HashSet调用的是HashMap的功能
	无序的
	允许存储null
	线程不安全，不同步，执行效率高
  将姓名和年龄的人，视作同一个对象，不要存储了

10.	哈希值
每一个对象，在建立的时候存储在内存堆中，JVM对每一个对象，根据底层的一个算法，哈希算法，计算出来一个十进制数,十进制就是哈希值。程序人员开发时候的一个参考，仅此而已
Object类，定义了一个方法  public int hashCode()计算对象的哈希值的，所有的子类都有这个哈希值.出现是为了哈希表而来的，本身，没有地址意义
HashSet，存储的其实是对象的哈希值
HashSet集合的存储过程，存储对象的时候，先调用对象的hashCode方法，获取该对象的哈希值(十进制数)，HashSet看看这个数以前存储过没有，如果没有存储过，就将这个哈希值存储到自己的哈希表中
实现去掉重复的元素，让姓名和年龄相同的对象，具有相同的哈希值。
覆盖hashCode方法
存储到HashSet中的对象，保证对象的唯一性，必须重写hashCode()和equals()方法
11.	hashCode和equals的问题
  如果两个对象具有相同的哈希值，两个对象进行equals比较，一定返回true吗 No
  如果两个对象进行equals比较，返回真，两个对象具有相同的哈希值吗,必须相同
12.	LinkedHashSet类
特点：有序的，基于哈希表的链表，线程不安全的，执行效率高

13.	TreeSet类
底层数据结构二叉树
线程不安全的，执行效率高
对存储到集合的对象，进行排序(对象的自然顺序 0-9 a-z)
将自定义的对象Person存储到TreeSet集合，出现了类型转换异常
ClassCastException :原因，Person不能被转成java.lang.Comparable
TreeSet中，需要对Person进行排序，可是你的Person对象，不具备自然顺序。
可以让Person具备自然顺序，实现Comparable接口.
String类，可以按照字符串的字典顺序，比较两个字符串，原因String类实习了Comparable接口，重写了接口中的方法compareTo()String类就具备了自然顺序

实现Person类，具备自然顺序，实现Comparable接口，重写compareTo方法
姓名，年龄，主要比较姓名，如果姓名一样，比较年龄
