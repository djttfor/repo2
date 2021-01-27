package com.ex.generator;

import com.ex.database.entity.Column;
import com.ex.database.entity.Table;
import com.ex.entity.Field;
import com.ex.util.Types;
import com.ex.util.UrlConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.*;
import java.util.*;

public class CodeGenerator {
    private static final Logger log = LoggerFactory.getLogger(CodeGenerator.class);
    public static void main(String[] args) throws Exception {

    }
    public static List<Table> getDatabaseConfig(String driverClassName,
                                         String url, String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            DatabaseMetaData m = connection.getMetaData();
            String catalog = connection.getCatalog();
            String schema = connection.getSchema();
            ResultSet metaTables = m.getTables(catalog, schema, "%", new String[]{"TABLE"});
            List<Table> tables = new ArrayList<>();
            while(metaTables.next()){
                String tableName = metaTables.getString("TABLE_NAME");
                Table table = new Table();
                table.setTableName(tableName);
                table.setTableComment(metaTables.getString("REMARKS"));
                List<Column> columns = new ArrayList<>();
                ResultSet mIndexInfo = m.getIndexInfo(catalog, schema, tableName, false, false);
                String primaryColumn = null;
                while(mIndexInfo.next()){
                    if ("PRIMARY".equals(mIndexInfo.getString("INDEX_NAME"))) {
                        primaryColumn = mIndexInfo.getString("COLUMN_NAME");
                    }
                }
                ResultSet mColumns = m.getColumns(catalog, schema, tableName, "%");
                while (mColumns.next()){
                    Column column = new Column();
                    column.setName(mColumns.getString("COLUMN_NAME"));
                    column.setJdbcType(mColumns.getString("TYPE_NAME"));
                    int javaType = Integer.parseInt(mColumns.getString("DATA_TYPE"));
                    column.setJavaType(Types.getJavaTypeName(javaType));
                    column.setLength(mColumns.getString("COLUMN_SIZE"));
                    column.setDecimalDigits(mColumns.getString("DECIMAL_DIGITS"));
                    column.setNotNull(!mColumns.getBoolean("NULLABLE"));
                    column.setPrimary(column.getName().equals(primaryColumn));
                    column.setComment(mColumns.getString("REMARKS"));
                    columns.add(column);
                }
                table.setColumnList(columns);
                tables.add(table);
            }
            return tables;
        } catch (SQLException throwables) {
            log.info("获取元数据失败:------------------------------------------------------------------------------------");
            throw new RuntimeException(throwables);
        }finally {
            Objects.requireNonNull(connection);
            connection.close();
        }
    }
    public static void generateEntity() throws IOException, SQLException, ClassNotFoundException {
        String templatePath = "D:\\Java\\study\\ssm_01\\src\\main\\resources";
        String rootPath = "D:\\Java\\study\\ssm_01\\src\\main\\java";
        String packageName = "com.ex.ssm";
        List<Table> tables = getDatabaseConfig("com.mysql.jdbc.Driver",
        "jdbc:mysql://localhost/base?useUnicode=true&amp;characterEncoding=utf-8",
        "root", "1998");
        UrlConfig urlConfig = new UrlConfig(rootPath,packageName,templatePath,tables);

        urlConfig.execute();

    }
    public static void generateEntity1() throws IOException, TemplateException {
        String rootPath = "D:\\Java\\study\\ssm_01\\src\\main\\java";
        String packageName = "com.ex.ssm.entity";
        String templatePath = "D:\\Java\\study\\ssm_01\\src\\main\\resources";
        String templateName = "entity.ftl";

        Configuration configuration = new Configuration(new Version("2.3.30"));
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        Map<String,Object> map = new HashMap<>();
        map.put("classPath",packageName);
        map.put("className","User");

        List<Field> fields = new ArrayList<>();
        fields.add(new Field("String","username"));
        fields.add(new Field("String","password"));
        fields.add(new Field("Integer","age"));

        map.put("fields",fields);

        Template template = configuration.getTemplate("entity.ftl");

        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rootPath+"\\com\\ex\\ssm\\entity\\"+"User.java")));

        template.process(map,writer);
    }


}