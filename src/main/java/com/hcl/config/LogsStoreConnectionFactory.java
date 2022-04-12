package com.hcl.config;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;


public class LogsStoreConnectionFactory {

	private static BasicDataSource dataSource;

	private LogsStoreConnectionFactory() {
	}

	public static Connection getConnection() throws SQLException {
		if (dataSource == null) {
			dataSource = new BasicDataSource();
			dataSource.setUrl("jdbc:mysql://azureecommercesrv.mysql.database.azure.com:3306/full-stack-ecommerce?serverTimezone=UTC");
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setUsername("azureadmin@azureecommercesrv");
			dataSource.setPassword("admin1234!");
		}
		return dataSource.getConnection();
	}
}
