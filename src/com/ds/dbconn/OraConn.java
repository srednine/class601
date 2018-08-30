package com.ds.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;

import com.ds.util.RNCommon;

public class OraConn {

	public static Connection getConn() {
		try {
			Class.forName(OraConfig.OraDriver);
			Connection conn = DriverManager.getConnection(OraConfig.dburl, OraConfig.dbid, OraConfig.dbpw);
			
			if (conn != null) {
				System.out.println("DB CONNECTION OK");
				return conn;
			}
			
			return null;
			
		} catch (Exception e) {			
			return (Connection) RNCommon.exceptionRtn(e, "DB CONNECTION FAIL!","o");
		}	
	}
}
