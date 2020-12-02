上传

1. 什么是上传？
  客户端给服务器发送数据（包含非文本）！

2. 上传对表单的要求！
  * method="post"
  * enctype="multipart/form-data"
    > 每个表单项都自己是一个独立的请求格式：
      * 有自己的请求头：Content-Disposition，包含表单项的名称
      * 空行
      * 请求体
    > 如果表单项是文件表单项
      * 有两个请求头
        > Content-Disposition：
	  * 表单项的名称
	  * 上传文件的名称
	> Content-Type：上传文件的mime类型
      * 请求体内容为文件内容！
  * <input type="file" .../>



-----------------------------7de3042c309e2
Content-Disposition: form-data; name="username"

zhangsan
-----------------------------7de3042c309e2
Content-Disposition: form-data; name="zhaopian"; filename="big.jpg"
Content-Type: image/jpeg

<二进制文件数据未显示>
---------------------------7de3042c309e2--


============================================================

3. 上传对Servlet的要求
  * 当使用了enctype="multipart/form-data"
    > String getParameter()：所有获取请求参数的方法不能再使用！
      * BaseServlet：它依赖request.getParameter()，所以上传时也不能使用BaseServlet
    > ServletInputStream getInputStream()：返回完整的请求体内容（包含每个表单项的部分）
      * 自己来对getInputStream()方法返回的流进行分割，太麻烦！可以使用工具来对整个表单数据进行分割处理！

===================================
===================================
===================================

上传组件：commons-fileupload

1. 导jar包
  * commons-fileupload.jar
    > commons-io.jar

2. 核心类
  * 工厂：DiskFileItemFactory，用来创建解析器！
  * 解析器：ServletFileUpload，用来解析request（它内部的InputStream），解析后会得到N个表单项
  * 表单项：FileItem，一个FileItem对象对应一个表单项，它可能是普通表单项，也可能是文件表单项！使用该类的API可以方便的操作表单数据。

3. 核心代码
  * 创建工厂：DiskFileItemFactory factory = new DiskFileItemFactory();
  * 创建解析器：ServletFileUpload sfu = new ServletFileUpload(factory);
  * 解析request，得到N个FileItem：List<FileItem> fileItemList = sfu.parseRequest(request);

4. FileItem的API
  * 普通表单项
    > String getFieldName()：获取表单项名称
    > String getString(String encoding)：获取表单项的值，可以传递字符编码！
  * 文件表单项
    > String getFieldName()：获取表单项名称
    > String getName()：获取上传文件的名称（有的浏览器会包含客户端路径）
    > String getContentType()：获取上传文件的mime类型
    > byte[] get()：获得文件内容（字符数组形式）
    > InputStream getInputStream()；获取文件内容（流形式）
    > long getSize()：获取上传文件的字节数
    > boolean isFormField()：判断当前表单项是否为普通表单项，返回true表示是普通表单项，返回false表示为文件表单项
    > void write(File file)：把上传文件保存到指定路径
    > void delete()：删除临时文件！

====================================================== 
======================================================

上传第一例

1. 上传三步：
  * 创建工厂
  * 创建解析器
  * 得到N个FileItem

  * 调用FileItem的API，来完成上传操作！

======================================================

细节

1. 上传文件必须保存到WEB-INF目录下！
  * 用户上传的是jsp，然后自己再去访问，就会执行用户的jsp

2. 文件名称相关细节
  * 文件路径问题：fileItem.getName()可以获取上传文件的名称
    > 不同浏览器效果不同：
      * 有的浏览器文件名称只是名称，没有路径！
      * 有的浏览器文件名称中包含路径！
    > 需要把包含路径的名称进行切割！
  * 文件名称中文编码
    > request.setCharacterEncoding("utf-8")
    > servletFileUpload.setHeaderEncoding("utf-8")：它的优先级高！
  * 文件同名问题
    > 多人上传文件名称相同会出现覆盖问题
    > 给文件名称添加uuid前缀，即可！！！

			
			String filename = fi2.getName();
			/*
			 * 对文件名称进行切割
			 */
			int index = filename.lastIndexOf("\\");
			if(index != -1) {
				filename = filename.substring(index+1);
			}


			// 处理文件同名问题
			filename = CommonUtils.uuid() + "_" + filename;


3. 目录打散问题
  * 不让一个目录下存放过多的文件
  * 打散方式：
    > 时间打散：例如一天生成一个目录！
    > 首字母打散：
    > 哈希打散：
      * 文件名称 --> 哈希码（int hCode = filename.hashCode()）
      * 哈希码 --> 16进行哈希码（String hStr = Integer.toHexString(hCode)） 
      * 16进行哈希码 --> 获取第一个字母，使用它生成一个目录，生成一个a目录
      * 16进行哈希码 --> 获取第二个字母，使用它生成一个目录（在第一个目录下生成），在a目录下生成一个b目录
      * 把文件保存到/第一字母/第二字母/文件


4. 大小限制问题
  * 单个文件大小限制
    > servletFileUpload.setFileSizeMax(long)
    > 这个方法必须在解析之前调用！
    > 当上传的文件超出限制时，parseRequest()方法会抛出异常！
  * 总大小限制（整个请求）
    > servletFileUpload.setSizeMax(long)
    > 这个方法必须在解析之前调用！
    > 当上传的文件超出限制时，parseRequest()方法会抛出异常！


5. 缓存大小与临时目录
  * 客户端上传的文件是否保存到内存中，还是保存到硬盘上。
  * 设置缓存大小：例如缓存大小为10KB，那么上传的文件如果大小在10KB之内，就不用保存到硬盘上了。如果超出10KB，先保存到硬盘上。
  * 临时目录：当需要把文件保存到硬盘上时，需要一个保存临时文件的目录！

  * 在创建工厂时，可以指定缓存大小和临时目录
    > DiskFileItemFactory(int sizeThreshold, File repository) 

  * 缓存大小默认值：Size threshold is 10KB.
  * 临时目录默认值：Repository is the system default temp directory, as returned by System.getProperty("java.io.tmpdir").

==========================================
==========================================
==========================================
下载

1. 什么是下载
  服务器端数据发送给客户端

2. 下载的口诀
  * 一流两个头
    > 流：要下载的数据
    > 两个头（响应头）
      * Content-Type：文件的mime类型
      * Content-Disposition：它用来指定是否弹出下载框，以及在下载框中显示的文件名称！
      response.setHeader("Content-Disposition", "attachment:fileName=" + fileName);

3. 下载框中中文问题
  * filename = new String(filename.getBytes("GBK"), "ISO-8859-1);


==========================================
==========================================
==========================================
发邮件

1. javamail
  * jar包：mail.jar、activtion.jar
  * 核心类：Session

2. 自写小工具（封装了javamail发邮件）
  * icore-tools-1.4.3.jar

3. 核心类
  * MailUtils（邮件工具类）
  * Mail（邮件类）
  * AttachBean（附件类）

4. 步骤
  1). 得到session
    > Session session = MailUtils.createSession("smtp.163.com", "icore_cxf", "icore");
  2). 创建mail对象
    > Mail mail = new Mail(发件人, 收件人们, 标题, 内容);
      * mail.addAttach(new AttachBean(文件对象, 文件名));
  3). 发
    > MailUtils.send(session, mail);

============================================
============================================
============================================
网盘

1. 功能分析
  * 上传文件
  * 显示所有文件
  * 下载文件

2. 表
  * myfile表
    > fid, 主键
    > fsize, 字节数
    > fname, 文件名(显示名称)
    > fpath, 文件路径(真实的路径)
    > cnt, 下载次数
    > uploadtime, 上传时间
CREATE TABLE tb_myfile(
    fid CHAR(32) PRIMARY KEY,
    fsize LONG,
    fname VARCHAR(100),
    fpath VARCHAR(500),
    cnt INT,
    uploadtime CHAR(19)
);
SELECT * FROM tb_myfile;

3. 页面
  * upload.jsp
  * filelist.jsp

4. Servlet
  * UploadServlet
  * MyFileServlet
    > 显示所有文件
    > 下载文件

5. Service
  * upload()
  * download()
  * findAll()

6. dao
  * 略

－－－－－－－－－－－－－－－－

jar包

－－－－－－－－－－－－－－－－




