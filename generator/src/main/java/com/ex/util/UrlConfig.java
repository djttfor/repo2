package com.ex.util;

import com.ex.database.entity.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建所有路径
 */

public class UrlConfig {
    private String rootPath;
    private String packageName;
    private String completePath;
    private String templatePath;

    private final Map<String,String> packagePath = new HashMap<>();

    private final Map<String,String> filePath = new HashMap<>();

    public String getRootPath() {
        return rootPath;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getCompletePath() {
        return completePath;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public Map<String, String> getPackagePath() {
        return packagePath;
    }

    private static final Logger log = LoggerFactory.getLogger(UrlConfig.class);

    public UrlConfig(String rootPath, String packageName, String templatePath, List<Table> tables){
        if(rootPath==null||packageName==null){
            throw new RuntimeException("包名与项目路径不能为空");
        }
        this.rootPath = rootPath;
        this.packageName = packageName;
        this.completePath = rootPath +"\\"+ packageName.replace('.','\\');
        packagePath.put("Entity",completePath+"\\entity");
        packagePath.put("Mapper",completePath+"\\mapper");
        packagePath.put("Service",completePath+"\\service");
        packagePath.put("ServiceImpl",completePath+"\\service\\impl");
        packagePath.put("Controller",completePath+"\\controller");
        this.templatePath = templatePath;
        buildClassFilePath(tables);
    }

    public void execute(){
        createAllPackagePath();
        createAllFile();
    }

    private void buildClassFilePath(List<Table> tables){
        for (Table table : tables) {
            String entityName = NameUtil.buildClassName(table.getTableName());
            filePath.put(entityName,packagePath.get("Entity")+"\\"+entityName+".java");
            filePath.put(tableName+"Mapper",packagePath.get("Mapper")+"\\"+entityName+".java");
            filePath.put(tableName+"Service",packagePath.get("Service")+"\\"+entityName+".java");
            filePath.put(tableName+"ServiceImpl",packagePath.get("ServiceImpl")+"\\"+entityName+".java");
            filePath.put(tableName+"Controller",packagePath.get("Controller")+"\\"+entityName+".java");
        }
    }
    private void createAllPackagePath(){
        createDirectory(completePath);
        packagePath.forEach((k,v)->{
            createDirectory(v);
        });
    }
    private void createAllFile(){
        filePath.forEach((k,v)->{
            try {
                createFile(v);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void createDirectory(String path){
        File rootFile = new File(path);
        if(!rootFile.exists()){
            log.info("创建路径:"+path);
            if(!rootFile.mkdirs()){
                throw new RuntimeException("创建失败");
            }
            log.info("创建成功");
        }else{
            log.info("路径"+path+"已存在");
        }
    }
    private void createFile(String fileName) throws IOException {
        File file = new File(fileName);
        if(!file.exists()){
            log.info("创建文件:"+fileName);
            if(!file.createNewFile()){
                log.info("创建失败");
            }
            log.info("创建成功");
        }else{
            log.info("文件"+fileName+"已存在");
        }
    }

}
