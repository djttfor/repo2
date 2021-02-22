### Servlet

#### 1.在tomcat安装目录部署项目的三种方式

#### 1.1 在conf/catalina/localhost配置

创建一个xml文件,在里面写war包路径或解压的war包资源如下

<Context docBase = "F://xxx/xxx/ssm.war"/>

虚拟路径为xml文件的名字

#### 1.2 在webapps下放war包即可,tomcat自动解压

#### 1.3在conf/server.xml中配置

找到Host节点在下面创建一个Context节点填写项目路径

<Context docBase = "F://xxx/xxx/ssm"/>

这种部署方式路径不能写war包路径,要先把war包解压,填文件夹路径

## 2.文件下载

只需注意设置响应头即可,不设置响应头就会直接展示图片

```java
String realPath = req.getServletContext().getRealPath("images\\周数.png");
String fileName = realPath.substring(realPath.lastIndexOf("\\")+1);
resp.setHeader("content-disposition",
        "attachment;filename="+ URLEncoder.encode(fileName,"utf-8"));
InputStream in = new FileInputStream(realPath);
ServletOutputStream out = resp.getOutputStream();
int len;
byte[] buff =new byte[1024*2];
while((len=in.read(buff))!=-1){
    out.write(buff,0,len);
}
```

### 3.文件上传

所需jar包

```xml
<!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.4</version>
</dependency>
<!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.4</version>
</dependency>
```

原生上传代码

```java
//表单提交方法一定要为post, 同时要设置属性enctype=multipart/form-data
private static final long serialVersionUID = 1L;

// 上传文件存储目录
private static final String UPLOAD_DIRECTORY = "upload";

// 上传配置
private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

/**
 * 上传数据及保存文件
 */
protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
    // 检测是否为多媒体上传
    if (!ServletFileUpload.isMultipartContent(request)) {
        // 如果不是则停止
        PrintWriter writer = response.getWriter();
        writer.println("Error: 表单必须包含 enctype=multipart/form-data");
        writer.flush();
        return;
    }

    // 配置上传参数
    DiskFileItemFactory factory = new DiskFileItemFactory();
    // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中C:\Users\DajieWei\AppData\Local\Temp\
    factory.setSizeThreshold(MEMORY_THRESHOLD);
    // 设置临时存储目录
    factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

    ServletFileUpload upload = new ServletFileUpload(factory);

    // 设置最大文件上传值
    upload.setFileSizeMax(MAX_FILE_SIZE);

    // 设置最大请求值 (包含文件和表单数据)
    upload.setSizeMax(MAX_REQUEST_SIZE);

    // 中文处理
    upload.setHeaderEncoding("UTF-8");

    // 构造临时路径来存储上传的文件
    // 这个路径相对当前应用的目录
    String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;

    // 如果目录不存在则创建
    File uploadDir = new File(uploadPath);
    if (!uploadDir.exists()) {
        uploadDir.mkdir();
    }

    try {
        // 解析请求的内容提取文件数据
        @SuppressWarnings("unchecked")
        List<FileItem> formItems = upload.parseRequest(request);

        if (formItems != null && formItems.size() > 0) {
            // 迭代表单数据
            for (FileItem item : formItems) {
                // 处理不在表单中的字段
                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadPath + File.separator + fileName;
                    File storeFile = new File(filePath);
                    // 在控制台输出文件的上传路径
                    System.out.println(filePath);
                    // 保存文件到硬盘
                    item.write(storeFile);
                    request.setAttribute("message",
                            "文件上传成功!");
                }
            }
        }
    } catch (Exception ex) {
        request.setAttribute("message",
                "错误信息: " + ex.getMessage());
    }
    // 跳转到 message.jsp
    request.getServletContext().getRequestDispatcher("/").forward(
            request, response);
}
```

