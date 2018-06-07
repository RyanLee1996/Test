package ryan.databaseconnect;

import ryan.httpio.UsersPO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class JDBCDemo {
	private static final String url = "jdbc:mysql://localhost/girlcloset?useUnicode=true&characterEncoding=utf-8";
	private static final String userName = "root";
	private static final String passWord = "123456";

	public static void main(String[] args) {
//		new JDBCDemo().InsertDemo();
//		new JDBCDemo().preparedDemo();
		new JDBCDemo().callableDemo();
	}
	public void callableDemo(){
		//获取连接
		Connection con = JDBCDemo.getCon();
		//准备SQL
		String sql="{call proc_test(?,?)}";
		//创建状态
		try {
			CallableStatement state = con.prepareCall(sql);
			//设置参数
			state.setString(1, "admin");
			state.registerOutParameter(2, Types.VARCHAR);
			//执行SQL
			state.execute();
			//获取输出参数
			System.out.println(state.getString(2));
			//释放资源
			state.close();
			JDBCDemo.closeCon(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void InsertDemo() {
		// 获取连接
		Connection con = JDBCDemo.getCon();
		// 准备SQL语句
		// String sql =
		// "insert into users(account,password,roleid) values('张三','zs123',1)";
		try {
			con.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String par = "'admin' or 1=1";
		String sql = "delete from users where account=" + par;
		System.out.println(sql);
		// 创建状态
		try {
			Statement state = con.createStatement();
			// 执行SQL语句
			int row = state.executeUpdate(sql);
			System.out.println(row);
			con.commit();
			// 释放资源
			state.close();
			JDBCDemo.closeCon(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void preparedDemo() {
		// 获取连接
		Connection con = JDBCDemo.getCon();
		// 准备SQL语句
		// String sql =
		// "insert into users(account,password,roleid) values('张三','zs123',1)";
		try {
			con.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String par = "'admin' or 1=1";
//		String sql = "delete from users where id=" + par;
		String sql = "delete from users where account=?";
		System.out.println(sql);
		// 创建状态
		try {
			PreparedStatement state = con.prepareStatement(sql);
			//设置参数
			state.setString(1, par);
			
			// 执行SQL语句
			int row = state.executeUpdate();
			System.out.println(row);
			// 释放资源
			state.close();
			JDBCDemo.closeCon(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void SelectDemo() {
		// 1、获取数据库连接
		Connection con = JDBCDemo.getCon();
		// 操作数据库
		// 2、准备SQL语句
		// String sql="SELECT * FROM users";
		String sql = "insert ";
		// 3、创建状态
		try {
			Statement state = con.createStatement();
			// 4、执行SQL语句，并操作结果
			ResultSet set = state.executeQuery(sql);
			// 5、操作查询结果
			while (set.next()) {
				// 操作单行查询结果
				// System.out.println("id:"+set.getInt("id")+" account:"+set.getString("account"));
				UsersPO po = new UsersPO();
				po.setId(set.getInt("id"));
				po.setAccount(set.getString("account"));
				po.setPassword(set.getString("password"));
				po.setRoleid(set.getInt("roleid"));
				po.setFlag(set.getInt("flag"));
			}
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 6、关闭数据库
		JDBCDemo.closeCon(con);
	}

	private static Connection getCon() {
		Connection con = null;
		try {
			// 1、加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2、获取连接对象
			// URL：jdbc:mysql://数据库主机/数据库名字
			// String
			// url="jdbc:mysql://localhost/girlcloset?user=root&password=123456";

			try {
				// 3、获取数据库连接
				con = DriverManager.getConnection(url, userName, passWord);
				// con = DriverManager.getConnection(url);
				System.out.println(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void closeCon(Connection con) {
		// 4、关闭连接
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
