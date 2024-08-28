package tool.oracleConnect;

import java.sql.*;

public class ConnectOracle {
	/**
	 * 一个非常标准的连接Oracle数据库的示例代码
	 * @throws SQLException 
	 */
	public static void main(String[] args) {
		PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
		ResultSet result = null;// 创建一个结果集对象
		Connection con = null;// 创建一个数据库连接
		try {
			con = connect(con);
			String sql = "select * from ETL_JOB";// 预编译语句，“？”代表参数
			pre = con.prepareStatement(sql);
			result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
//			while (result.next()){
//				// 当结果集不为空时
//				System.out.println("id" + result.getInt("id") + "姓名:"
//						+ result.getString("username"));
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
				// 注意关闭的顺序，最后使用的最先关闭
				if (result != null)
					result.close();
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
				System.out.println("数据库连接已关闭！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 传入一个数据库连接con,返回一个connection
	 * @param con
	 */
	public static Connection connect(Connection con) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
			System.out.println("***开始尝试连接数据库！***");
			String url = "jdbc:oracle:" + "thin:@localhost:1521:";
			String user = "";//
			String password = "";//
			con = DriverManager.getConnection(url, user, password);
			System.out.println("***连接成功！***");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("***数据库连接失败***");
		}
		return con; 
	}
}
