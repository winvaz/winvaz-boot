Linux操作系统基础

CentOS
主流：目前的Linux操作系统主要应用于生产环境，主流企业级Linux系统仍旧是RedHat或者CentOS
免费：RedHat 和CentOS差别不大，基于Red Hat Linux 提供的可自由使用源代码的企业CentOS是一个级Linux发行版本
更新方便：CentOS独有的yum命令支持在线升级，可以即时更新系统，不像RedHat 那样需要花钱购买支持服务！

========================================
Linux目录结构(/)
                 /
bin boot dev etc home lib sbin usr var tmp

bin  (binaries)存放二进制可执行文件
sbin  (super user binaries)存放二进制可执行文件，只有root才能访问
etc (etcetera)存放系统配置文件
usr  (unix shared resources)用于存放共享的系统资源
home 存放用户文件的根目录
root  超级用户目录
dev (devices)用于存放设备文件
lib  (library)存放跟文件系统中的程序运行所需要的共享库及内核模块
mnt  (mount)系统管理员安装临时文件系统的安装点
boot 存放用于系统引导时使用的各种文件
tmp  (temporary)用于存放各种临时文件
var  (variable)用于存放运行时需要改变数据的文件

========================================
========================================
一、文件系统的管理
tips:输入命令的时候要常用tab键来补全

ls   查看目录信息  （ ls /  ）  
	ls -l 等价于  ll
pwd	  查看当前所处的路径   
cd     切换目录 (cd /) ,如果不带参数则切换到用户主目录 ~

mkdir   创建文件夹
rmdir   删除文件夹  (只能删除空文件夹)
rm -r(递归删除) /a/b(删除非空文件夹/文件) /f(强制删除)

创建文件
touch a.avi 创建一个空文件
vi blabla.txt 用文本编辑器编辑一个文件并且保存(按i键进入编辑，然后按ESC退出编辑，再:wq保存)
echo "angelababy,zhen de hen xihuan ni" > qingshu.txt  把左边的输出放到右边的文件里去 


删除文件
rm filename  (rm -r  删除文件夹     rm -rf 强制删除文件或文件夹)
移动文件  修改文件名
mv a/wenjian1.txt b/file1.txt   (移动文件的同时还修改了文件名)

拷贝文件
cp srcFile destFile

查看文件内容
cat wodeqingshu.txt
more wodeqingshu.txt 可以分页看
less wodeqingshu.txt 不仅可以分页，还可以方便地搜索，回翻等操作(shift+G文件末尾，gg文件首部)

tail -10 wodeqingshu.txt   查看文件的尾部的10行
程序打出的日志在生产实践中具有非凡的debug的意义
tail -f user.log


二、权限管理
drwxrwxr-x

d rwx(文件的所有者(owner)的权限) rwx(所属用户组(group)的权限) r-x(其他人的权限)

d：directory(文件夹)
-：(文件)
r：read(读)
w：write(写)
x：可执行，可操作

修改权限
chmod  u+rwx file   为file添加或取消所属用户的权限
(u代表所属用户  o代表其他用户  g代表所属组的成员用户)
chmod 567 file 用数字也可以表示权限  
5 --->  101 ---> r-x

rwx用二进制表示，有表示1，无表示0，转成十进制
111 -- 7
100 -- 4
101 -- 5
000


修改文件的所有者owner
chown hadoop:hadoop  file 将file的所有者改成hadoop用户，所属组改成hadoop组

上级目录的权限对本级文件或者文件夹的操作也有约束


三、用户管理
su  切换用户
exit    退出当前用户返回上回切换用户

添加用户
useradd  icore0830
修改密码
passwd icore0830
删除用户
userdel icore
userdel -r icore -----删除用户的同时删除用户的主目录

修改用户属性
usermod -l b a 将a的登陆名改为b
usermod -g hadoop icore0830 将icore0830的组改为hadoop组
usermod -G hadoop,root icore0830 给icore0830添加两个组hadoop,root
usermod -d /home/icore icore0830 将icore0830的主目录改成/home/icore
（要事先创建icore目录，并且拷入环境变量文件(.bash_profile和.bashrc )）

添加用户组
groupadd beauties

查看用户所属组
groups

删除用户组
groupdel beauties

修改用户组属性
groupmod 



四、常见系统管理(凡是涉及到修改，就一定要用root权限)
普通用户使用sudo来执行root权限的命令
将该用户添加到sudoers文件中去(切换到root用户到/etc/sudoers文件中去添加用户)


磁盘空间信息查看
df -h  查看磁盘空间状态信息
du -sh * 查看当前目录下所有子目录和文件的汇总大小    


进程信息查看 
free  查看内存使用状况
top   查看实时刷新的系统进程信息

ps -ef  查看系统中当前瞬间的进程信息快照
ps -ef | grep myshell.sh  搜索myshell进程的信息
kill -9(强制) pid  杀掉进程


文件归档压缩
tar -czf jdk-7u65-linux-i586 jdk-7u65-linux-i586.tar.gz
tar -xzvf jdk-7u65-linux-i586.tar.gz
gzip  file
bzip  file 


网络管理
ip地址的配置
vi /etc/sysconfig/network-scripts/ifcfg-eth0 修改该配置文件即可改ip地址
或者用setup指令通过一个带提示的伪图形界面来修改

查看ip地址
ifconfig

重启网络服务
root权限下   service network restart 

查看当前的进程连接网络的信息
netstat -nltp   

修改主机名
vi  /etc/sysconfig/network  修改其中的hostname配置项
要想立即生效  可以执行指令  hostname nidezhujiming 



五、常用工具指令
wc   统计文本信息（行数，词数，字符数）
date  查看或者修改系统的日期和时间
echo  输出字符串或者变量的值
vi   linux系统中最通用的文本编辑器

六、linux中的软件安装 
jdk
将安装包解压到你的安装路径下
然后修改环境变量  sudo vi /etc/profile
然后  source /etc/profile  来生效
tomcat
Eclipse


mysql
redhat 公司的RPM方式的包管理 也是很常用的软件包管理器

rpm -qa | grep mysql
sudo rpm -e mysql-libs-5.1.66-2.el6_3.i686 --nodeps
sudo rpm -ivh MySQL-server-5.1.73-1.glibc23.i386.rpm 

========================================
========================================
Linux常用命令
命令格式：命令  -选项  参数
如：ls  -la  /usr
ls：显示文件和目录列表(list)
常用参数：
-l  (long)
-a	(all)  注意隐藏文件、特殊目录.和..   
-t	(time)

------------------------------------
Linux命令的分类
内部命令：属于Shell解析器的一部分
cd 切换目录（change directory）
pwd 显示当前工作目录（print working directory）
help 帮助

外部命令：独立于Shell解析器之外的文件程序
ls 显示文件和目录列表（list）
mkdir 创建目录（make directoriy）
cp 复制文件或目录（copy）

查看帮助文档
内部命令：help + 命令（help cd）
外部命令：man + 命令（man ls）

==============================================
操作文件或目录常用命令
pwd 显示当前工作目录（print working directory）
touch 创建空文件				                    
mkdir 创建目录（make directoriy）
-p 父目录不存在情况下先生成父目录 （parents）            
cp 复制文件或目录（copy）
-r 递归处理，将指定目录下的文件与子目录一并拷贝（recursive）     
mv 移动文件或目录、文件或目录改名（move）

================================================
常用命令
wc 统计文本的行数、字数、字符数（word count）
-m 统计文本字符数
-w 统计文本字数
-l 统计文本行数
find 在文件系统中查找指定的文件
find /etc/ -name "aaa"
grep 在指定的文本文件中查找指定的字符串
ln 建立链接文件（link）
-s 对源文件建立符号连接，而非硬连接（symbolic）

----------------------------------------
top 显示当前系统中耗费资源最多的进程 
ps 显示瞬间的进程状态
-e /-A 显示所有进程，环境变量
-f 全格式
-a 显示所有用户的所有进程（包括其它用户）
-u 按用户名和启动时间的顺序来显示进程
-x 显示无控制终端的进程
kill 杀死一个进程
kill -9 pid
df 显示文件系统磁盘空间的使用情况

-------------------------------------
du 显示指定的文件（目录）已使用的磁盘空间的总
-h文件大小以K，M，G为单位显示（human-readable）
-s只显示各档案大小的总合（summarize）
free 显示当前内存和交换空间的使用情况 
netstat 显示网络状态信息
-a 显示所有连接和监听端口
-t (tcp)仅显示tcp相关选项
-u (udp)仅显示udp相关选项
-n 拒绝显示别名，能显示数字的全部转化成数字。
-p 显示建立相关链接的程序名
ifconfig 网卡网络配置详解 
ping 测试网络的连通性 

=====================================
=====================================
备份压缩命令
gzip 压缩（解压）文件或目录，压缩文件后缀为gz
命令格式：gzip [选项] 压缩（解压缩）的文件名
-d将压缩文件解压（decompress）
-l显示压缩文件的大小，未压缩文件的大小，压缩比（list）
-v显示文件名和压缩比（verbose）
-num用指定的数字num调整压缩的速度，-1或--fast表示最快压缩方法（低压缩比），-9或--best表示最慢压缩方法（高压缩比）。系统缺省值为6

-------------------------------------------
bzip2 压缩（解压）文件或目录，压缩文件后缀为bz2 
命令格式：bzip2 [-cdz] 文档名
-c将压缩的过程产生的数据输出到屏幕上
-d解压缩的参数（decompress）
-z压缩的参数（compress）
-num 用指定的数字num调整压缩的速度，-1或--fast表示最快压缩方法（低压缩比），-9或--best表示最慢压缩方法（高压缩比）。系统缺省值为6

---------------------------------------
tar 文件、目录打（解）包
-c 建立一个压缩文件的参数指令（create）
-x 解开一个压缩文件的参数指令（extract）
-z 是否需要用 gzip 压缩
-j 是否需要用 bzip2 压缩
-v 压缩的过程中显示文件（verbose）
-f 使用档名，在 f 之后要立即接档名（file）

========================================
关机/重启命令
shutdown系统关机 
-r 关机后立即重启
-h 关机后不重新启动
halt 关机后关闭电源 
reboot 重新启动

======================================
学习Linux的好习惯
善于查看man page（manual）等帮助文档
利用好Tab键
掌握好一些快捷键
ctrl + c（停止当前进程）
ctrl + r（查看命令历史）
ctrl + l（清屏，与clear命令作用相同）

====================================
VIM文本编辑器
vi / vim是Unix / Linux上最常用的文本编辑器而且功能非常强大。
只有命令，没有菜单。

VIM工作模式
vim filename 进入
i/a/o 插入模式
Esc键 退出编辑模式
: 底行模式 命令以回车结束
:wq 退出

------------------------
插入命令
i   在光标前插入
I   在光标当前行开始插入
a   在光标后插入
A   在光标当前行末尾插入
o   在光标当前行的下一行插入新行
O   在光标当前行的上一行插入新行

---------------------------------
定位命令
:set nu 显示行号
:set nonu   取消行号
gg  到文本的第一行
G   到文本的最后一行
:n  到文本的第n行

----------------------------------
删除命令
x   删除光标所在处字符
nx  删除光标所在处后的n个字符
dd  删除光标所在行。ndd删除n行
dG  删除光标所在行到末尾行的所以内容
D   删除光标所在处到行尾的内容
:n1,n2d 删除指定范围的行

------------------------------------
替换和取消命令
u   undo，取消上一步操作
Ctrl + r    redo，返回到undo之前
r   替换光标所在处的字符
R   从光标所在处开始替换，按Esc键结束

-------------------------------
常用快捷键
Shift+ zz   保存退出，与“:wq”作用相同
v   进入字符可视模式
V  或  Shift + v 进入行可视模式
Ctrl + v    进入块可视模式

=====================================
=====================================
用户和组账户管理

配置文件
保存用户信息的文件：/etc/passwd
保存密码的文件：/etc/shadow
保存用户组的文件：/etc/group
保存用户组密码的文件：/etc/gshadow
用户配置文件：/etc/default/useradd

------------------------------------
/etc/passwd

使用命令：man 5 passwd
account:password:UID:GID:GECOS:directory:shell

用户名     用户登陆系统的用户名
密码       密码位
UID        用户标识号
GID        默认组标识号
描述信息    存放用户的描述信息
宿主目录    用户登陆系统的默认目录，默认是在/home下
命令解析器   用户使用的shell，默认是bash

------------------------------------------
Linux用户分类
超级用户：（root，UID=0）
普通用户：（UID在500到60000）
伪用户：（UID在1到499）
系统和服务相关的：bin、daemon、shutdown等
进程相关的：mail、news、games等
为用户不能登陆系统，而且没有宿主目录

============================================
/etc/shadow格式
查看命令：man 5 shadow
用户名     登陆系统的用户名
密码      加密密码
最后一次修改时间    用户最后一次修改密码距现在的天数，从1970-1-1起
最小时间间隔  两次修改密码之间的最小天数
最大时间间隔  密码有效天数
警告时间    从系统警告到密码失效的天数
账号闲置时间  账号闲置时间
失效时间    密码失效的天数
标志  标志

-------------------------------------
用户组
每个用户至少属于一个用户组
每个用户组可以包含多个用户
同一个用户组的用户享有该组共有的权限

============================================
/etc/group格式
组名  用的所在的组
组密码 密码位，一般不使用
GID 主标示号
组内用户列表  属于改组的用户列表

---------------------------------------
操作用户命令
添加用户命令：useradd
-u 指定userID（uid）
-g 指定所属的组名（gid）
-G 指定多个组，用逗号“，”分开（Groups）
-c 用户描述（comment）
-e 失效时间（expire date）
例子：
useradd -u 888 -g users -G sys,root -c "hr zhang" zhangsan
passwd zhangsan

-----------------------------------------------
修改用户命令：usermod（user modify）
-l 修改用户名 （login）usermod -l a b（b改为a）
-g 修改组 usermod -g sys tom
-G添加多个组 usermod -G sys,root tom
–L 锁定用户账号密码（Lock）
–U 解锁用户账号（Unlock）
-d 修改用户的主目录
删除用户命令：userdel（user delete）
-r 删除账号时同时删除目录（remove）

-----------------------------------------
操作用户组命令
添加组：groupadd
-g 指定gid
修改组：groupmod
-n 更改组名（new group）
删除组：groupdel
groups 显示用户所属组

=====================================================
=====================================================
权限管理
三种基本权限 
r 读权限（read）
w 写权限（write）
x 执行权限 （execute）

--------------------------------------------
-rwxrw-r--
-(类型) rwx(所属用户权限) rw-(所属组权限) r--(其他用户权限)
第1位：文件类型（d 目录，- 普通文件，l 链接文件）
第2-4位：所属用户权限，用u（user）表示
第5-7位：所属组权限，用g（group）表示
第8-10位：其他用户权限，用o（other）表示
第2-10位：表示所有的权限，用a（all）表示

完整信息：一个文件，所属用户具有读写执行权限；所属组的用户
有读写权限，没有执行权限；其他用户只有读权限

点是ACL类型，（.）点是ACL_T_SELINUX_ONLY， （+）加是ACL_T_YES，空白是没有ACL。
ACL 是访问控制列表Access Control List

-------------------------------------------------
更改操作权限
chmod修改文件权限命令（change mode）
	参数：-R 下面的文件和子目录做相同权限操作（Recursive递归的）
	例如：chmod  u+x  a.txt
用数字来表示权限（r=4，w=2，x=1，-=0）
	例如：chmod  750  b.txt
	rwx用二进制表示是111，十进制4+2+1=7
	r-x用二进制表示是101，十进制4+0+1=5
	
==========================================
==========================================
RPM软件包管理
RPM是RedHat Package Manager（RedHat软件包管理工具）的缩写，这一文件格式名称虽然打上了RedHat的标志，但是其原始设计理念是开放式的，现在包括RedHat、CentOS、SUSE等Linux的分发版本都有采用，可以算是公认的行业标准了。RPM文件在Linux系统中的安装最为简便

---------------------------------------
RPM命令使用
rpm的常用参数
i：安装应用程序（install）
e：卸载应用程序（erase）
vh：显示安装进度；（verbose   hash） 
U：升级软件包；（update） 
qa: 显示所有已安装软件包（query all）
结合grep命令使用
例子：rmp  -ivh  gcc-c++-4.4.7-3.el6.x86_64.rpm

------------------------------------------
YUM命令
Yum（全称为 Yellow dog Updater, Modified）是一个在Fedora和RedHat以及SUSE、CentOS中的Shell前端软件包管理器。基於RPM包管理，能够从指定的服务器自动下载RPM包并且安装，可以自动处理依赖性关系，并且一次安装所有依赖的软件包，无须繁琐地一次次下载、安装。
例子（需要上网，没有网络可以建本地源）：
yum  install  gcc-c++
yum  remove  gcc-c++
yum  update  gcc-c++




























