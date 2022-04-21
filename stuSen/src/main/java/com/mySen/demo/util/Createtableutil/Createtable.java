package com.mySen.demo.util.Createtableutil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Createtable {
    //支持map通过K-v获取值
    private  List getClassInfo(Object o) {

        Field[] fields = o.getClass().getDeclaredFields();

        String[] fieldNames = new String[fields.length];

        List list = new ArrayList();

        Map infoMap = null;

        for (int i = 0; i < fieldNames.length; i++) {
            infoMap = new HashMap();

            infoMap.put("type", fields[i].getType().toString());

            infoMap.put("name", fields[i].getName());

            list.add(infoMap);
        }
        return list;

    }
    //获取类的属性名称
    private  String[] getClassdatatype(Object o){
        Field[] fields = o.getClass().getDeclaredFields();

        String[] fieldtypes = new String[fields.length];


        for (int i = 0; i < fieldtypes.length; i++) {
            fieldtypes[i] = fields[i].getType().toString();
        }
        return fieldtypes;
    }

    //获取类的属性类型
    public   String[] getClassdataName(Object o){
        Field[] fields = o.getClass().getDeclaredFields();

        String[] fieldNames = new String[fields.length];


        for (int i = 0; i < fieldNames.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    //自动生成sql语句
    public String createTablesql(String tablename,Object obs){
        //判断是否符合规范
        if(tablename.isEmpty()||obs==null){
            return "参数不完整";
        }
        //获得类的属性名称
        String[] name = getClassdataName(obs);
        String[] type = getClassdatatype(obs);
        String sql ="create table if not exists "+tablename+"(";
        //转换数据类型
        for(int i = 0;i<type.length;i++){
            if(type[i].equals("class java.lang.String")){
                type[i] = "varchar(255)";
            }
        }
        for(int i = 0;i<name.length-1;i++){
            sql=sql+name[i]+" "+type[i]+",";
        }
        sql=sql+name[name.length-1]+" "+type[name.length-1]+")";

      return sql;
    }
    //创建相关类的数据表
    public static String createTable(Config config, String sql,String tablename) throws ClassNotFoundException, SQLException {
        Class.forName(config.getDriverClassName());
        String url = config.getUrl();
        String user=config.getUsername();
        String password = config.getPassword();
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement st = conn.createStatement();
        //4. 执行sql
        st.executeUpdate(sql);//创建表
        String sql1 = "select * from "+tablename;
        int res =0;
        if(st.executeQuery(sql1)!=null){
            res=1;
        }
        //5. 释放资源
        st.close();
        conn.close();
        return  res>0? "创建成功":"创建失败";
    }
    //创建相关类数据库的对外实现方法
    public String createTableUtils(Object obs,String tablename) throws SQLException, ClassNotFoundException {
        if(obs!=null){
            Config config = new Config();
            Createtable con = new Createtable();
            String sql =  con.createTablesql(tablename,obs);
            System.out.println(sql);
            System.out.println(createTable(config,sql,tablename));
        }
        return null;
    }

    //创建表单格式的类数据格式
    public  void  createFromtype(String fromname,String[] classdata){
        String Fromstring = fromname+":{"+"\n";
        if(classdata.length!=0){
            for(int i = 0;i<classdata.length-1;i++){
                Fromstring +=classdata[i]+": ''"+","+"\n";
            }
            Fromstring+=classdata[classdata.length-1]+": ''"+"\n"+"},";
        }
        System.out.println(Fromstring);
    }

    public void createupdateFrom(String[] classdata){
     String Fromsql = "UPDATE FROM vaccdb SET"+"\n";
        if(classdata.length!=0){
            for(int i = 0;i<classdata.length-1;i++){
                Fromsql +=classdata[i]+"=#{"+classdata[i]+"},";
            }
            Fromsql+=classdata[classdata.length-1]+"=#{"+classdata[classdata.length-1]+"}"+" WHERE id=#{id}";
            System.out.println(Fromsql);
        }
    }

    public void createRequestParam(String[] name){
        String res="";
        if(name.length!=0){
            for(int i = 0;i<name.length-1;i++){
                res+="@RequestParam String "+name[i]+",";
            }
        }
        res+=res+"@RequestParam String "+name[name.length-1];
        System.out.println(res);
    }
}
