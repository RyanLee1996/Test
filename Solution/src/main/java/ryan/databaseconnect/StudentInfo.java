package ryan.databaseconnect;

import java.sql.*;

public class StudentInfo {
    private static final String url = "jdbc:mysql://127.0.0.1/student_info?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String userName = "root";
    private static final String passWord = "123456";

    public static void main(String[] args) {
        /*CREATE TABLE `users` (
  `id` int(255) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8*/
        /*CREATE TABLE `students` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `sex` varchar(255) NOT NULL,
  `age` int(10) DEFAULT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8*/
        /*CREATE DEFINER=`skip-grants user`@`skip-grants host` PROCEDURE `proc_test`(IN `name` varchar(20),OUT `str` varchar(20))
BEGIN
	declare cnt int default 0;
	insert into users VALUES(DEFAULT,name,'123',0);
	SET cnt = ROW_COUNT();
	if cnt = 0 then
	set str = 'Lose';
else
   set str = 'Success';
	 end if;
	insert into students values(DEFAULT,name,null,12,30,21345);
	SET cnt = ROW_COUNT();
		if cnt = 0 then
	set str = 'Lose';
else
   set str = 'Success';
	 end if;
END*/
//        String sql ="insert into users values(1,'ryan','123456',0)";
//        manipulation(sql);
//        String sql = "update users set password='654321' where id=1";
//        manipulation(sql);
//        String sql = "delete from users where id=1";
//        String sql = "truncate table users";
//        manipulation(sql);
//        String sql = "select*from users";
//        select(sql);
        procedure();
    }
    private static void manipulation(String sql){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(url,userName,passWord);
            PreparedStatement state = connect.prepareStatement(sql);
            /*在此 PreparedStatement 对象中执行 SQL 语句，该语句必须是一个 SQL 数据操作语言（Data Manipulation Language，DML）语句，
            比如 INSERT、UPDATE 或 DELETE 语句；或者是无返回内容的 SQL 语句，比如 DDL 语句。 */
            state.executeUpdate();
            state.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void select(String sql){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(url,userName,passWord);
            PreparedStatement state = connect.prepareStatement(sql);
            ResultSet result = state.executeQuery();
            while (result.next()){
                System.out.println(result.getString(1) + result.getString(2) + result.getString(3) + result.getString(4));
            }
            state.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void procedure(){
        //获取连接
        try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connect = DriverManager.getConnection(url,userName,passWord);
        //准备SQL
        String sql="{call proc_test(?,?)}";
        //创建状态
            CallableStatement state = connect.prepareCall(sql);
            //设置参数
            state.setString(1, "admin");
            state.registerOutParameter(2, Types.VARCHAR);
            //执行SQL
            state.execute();
            //获取输出参数
            System.out.println(state.getString(2));
            //释放资源
            state.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
