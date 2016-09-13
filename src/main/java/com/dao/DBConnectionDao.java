package com.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class DBConnectionDao {

	public static final String FILE_NAME = "db_connection.properties";
	private static DBConnectionDao instance;
	private Properties properties;

	private DBConnectionDao() throws IOException {
		
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream(FILE_NAME);
		properties = new Properties();
		properties.load(inputStream);
	}

	public String getProperty(String nimi) {
		return this.properties.getProperty(nimi);
	}

	public static DBConnectionDao getInstance() throws IOException {
		if (instance == null) {
			instance = new DBConnectionDao();
		}
		return instance;
	}

}