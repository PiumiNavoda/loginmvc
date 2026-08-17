package com.registerpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.registerpackage.User;

public class UserDao {
	
	public int registerUser(User user) throws ClassNotFoundException{
		
		String INSERT_USERS_SQL="INSERT INTO logusers"+"(Name,Address,Contact,Username,Password) VALUES"+"(?,?,?,?,?);";
	
		int result=0;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSL=false","root","root");
	
		PreparedStatement ps=con.prepareStatement(INSERT_USERS_SQL)){
		ps.setString(1, user.getName());
		ps.setString(2, user.getAddress());
		ps.setString(3, user.getContact());
		ps.setString(4, user.getUname());
		ps.setString(5, user.getPassword());
		
		System.out.println(ps);
		
		result=ps.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
			printSQLException(e);
		}
		return result;
				
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
		
	}
}

	