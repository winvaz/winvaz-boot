day16
1.	对象数组
需求：有学生的姓名和成绩，要求把所有的学生姓名和成绩，存储起来
容器只有数组一种，如何存储。将学生的信息，进行对象的封装，出现一个描述学生对象的类 class Student{姓名，成绩}。利用数组存储学生对象，存储对象的这个数组，对象数组。由于对象数组，始终还是改变不了数组定长的问题，Java工程师们，从JDK1.2版本开始，出现了集合框架。
2.	数组和集合的区别
  数组还是集合都是容器，容器就是用来存储的！
	数组的长度是固定的，集合的长度是可变的 (数组是定长的，集合是变长的)
	数组可以存储对象，也可以存储基本数据类型，但是集合只存对象，不存储基本数据类型，集合存储的都是引用类型
3.	学习集合框架，到底需要学的是什么
	掌握，如何把对象存储到集合这个容器中
	掌握，如何把对象中集合容器中获取出来
	掌握，集合中每一个类的自身特点
4.	集合的框架图
  集合是一个继承体系，学习的是这个体系中的最顶层，建立最底层类的对象
  集合有一个顶层的接口 java.util.Collection,定义了这个体系的最共性的内容
5.	集合派系的顶层接口Collection   代码实现在day16的CollectionDemo
 public interface Collection<E>   <E>泛型  没学泛型之前都看做是<E> == Object
 Collection接口中的方法
 Collection集合存储对象，存储对象的方法
	add(E e) 将元素存储到集合的方法  add(Object e)参数，任何对象都可以传递的
	addAll(Collection c)将一个集合添加到另一个集合中
 
 Collection集合中，移除对象的方法
	boolean remove(Object o)传递一个对象，移除这个对象，移除成功返回true
	boolean removeAll(Collection c)移除，两个集合的共性元素

 Collection集合中的其他方法
	 void clear()移除集合中的所有元素
	 int size()返回集合中存储的元素的个数
	 boolean contains(Object o)判断参数，在不在集合中，如果在返回true
	 boolean containsAll(Collection c)判断一个集合是否包含另一个集合，包含返回true
	 boolean retainAll(Collection c)获取两个集合的交集

6.	迭代器
什么是迭代器：取出集合中存储的对象的方式，接口Collection中，定义了一个方法，是集合中所有的子类都具备的， Iterator iterator(),返回一个Iterator类型的对象
由于每个集合存储对象的方式不同，取出方法也不同
iterator方法返回的是一个接口的类型，接口本身不能创建对象的，返回的肯定是接口的实现类对象,迭代器的原理，其实就是每个集合中的内部类的事情，集合中的内部类，实现了Iterator接口，使用iterator方法，获取的就是内部类的对象
Iterator接口中的三个方法
	boolean hasNext() 判断集合中还没有没有下一个被取出的元素，有返回true
	Object next() 获取集合中的元素 ，看成是arr[i]
	void remove() 移除遍历到的集合中的元素
实现迭代器取出集合对象的三个步骤
	通过集合对象的iterator方法，获取迭代器对象，返回的是一个接口的实现类
	使用迭代器对象的hasNext()方法，判断集合中还有没有对象可以被取出
	使用迭代器对象的next()方法直接获取存储到集合中的对象
    迭代器是获取存储到集合的对象的通用方式
注意，如果迭代器已经获取完了，再次获取，出现没有元素被取出异常
java.util.NoSuchElementException
    注意，迭代过程中，出现了并发修改异常ConcurrentModificationException。出现异常的原因：使用集合的方法修改了集合的长度，而迭代器不知道，因此出现并发修改异常。在迭代的过程中，不要使用集合的方法，改变集合长度
    一次迭代中，不能出现多次next方法，否则可能出现异常，出现数据错乱
7.	List 接口派系
List接口派系的特点：
	有序的:存储的时候，和取出的时候顺序一致
	List派系是一个有索引的，具有下标
	允许存储重复元素
   List接口中的特有方法
	add(int index, Object obj)在指定的索引上，插入元素
	Object get(int index)指定下标，获取这个下标上的元素
	Object remove(int index)指定下标，移除这个下标上的元素，返回删除之前的
	Object set(int index ,Object obj)执行下标，修改这个下标上的元素，返回修改之前的
	List subList(int start,int end)获取集合中的一部分，包含头，不包含尾

8.	List接口派系的特有迭代器ListIterator
List接口中定义了一个方法  listIterator,所有的子类都具备的，返回一个接口的实现类的对象，接口是就ListIterator，List的特有迭代器
使用List特有迭代器，可以实现在迭代的过程中，对集合中的元素，进行添加，修改，和删除。
ListIterator接口方法
add(Object obj)使用迭代器，遍历集合的过程中，添加
set( Object obj)使用迭代器，遍历集合的过程中，修改
boolean hasPrevious()  看成是hasNext()
Object previous() 看成是next()

9.	ArrayList类
属于List派系中的一个子类，具备List派系的所有特点： 有序，下标，重复
ArrayList自己的特点：
	ArrayList底层实现的数据结构是数组结构
	ArrayList实现是不同步，线程不安全，但是运行效率高  数据(增，删，查，改)
	ArrayList底层是一个可变数组，导致了，这个集合查询(遍历)快，增删慢
	ArrayList 数组大小默认10个位置，每次增长50%
   ArrayList存储自定义对象，并取出，必须要练熟

10.	作业题
用户通过键盘输入，输入姓名和成绩，姓名成绩封装成学生对象
学生对象存到ArrayList中

如果用户输入了over,结束键盘输入，迭代集合，迭代出已经存储的学生对象，姓名和成绩，不使用toString()

有一个学生类，姓名，成绩，构造 get方法
建立集合
哈哈  34656 String[] split(" +")
嘻嘻  123
over


制作一个6位的不同字符的验证码
输出到控制台，用户输入，判断对还是错
6位不同的，数字，字母，汉字	

