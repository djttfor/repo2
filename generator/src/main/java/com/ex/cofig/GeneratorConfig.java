package com.ex.cofig;

public class GeneratorConfig {
    //项目根路径
    private String rootPath;
    //包名
    private String packageName;
    //数据库配置
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    //entity类是否需要实现Serializable接口
    private boolean serializableFlag = false;

    //作者
    private String author;

    //生成器模式
    private GeneratorMode mode;

    //是否生成Controller
    private boolean requireController ;

    public GeneratorConfig(){
        //默认
        mode = GeneratorMode.MYBATIS_PLUS;
        //默认生成controller
        requireController = true;
    }

    public boolean isRequireController() {
        return requireController;
    }

    public void setRequireController(boolean requireController) {
        this.requireController = requireController;
    }

    public GeneratorMode getMode() {
        return mode;
    }

    public void setMode(GeneratorMode mode) {
        this.mode = mode;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isSerializableFlag() {
        return serializableFlag;
    }
    public void setSerializableFlag(boolean serializableFlag) {
        this.serializableFlag = serializableFlag;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
