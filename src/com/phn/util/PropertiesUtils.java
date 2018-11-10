package com.phn.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
public class PropertiesUtils {
	
	public static String dbname() {
		Properties prop=new Properties();
		PropertiesUtils proputils =new PropertiesUtils();
		File file =new File(proputils.getClass().getClassLoader().getResource("database.properties").getPath());
		try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			prop.load(br);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String jdbcname = prop.getProperty("jdbc_url");
		String dbname=jdbcname.split("///")[1];
		if (dbname==null) {
			dbname="defaultdb";
		}
		return dbname;
	}
	public static String username() {
		Properties prop=new Properties();
		PropertiesUtils proputils =new PropertiesUtils();
		File file =new File(proputils.getClass().getClassLoader().getResource("database.properties").getPath());
		try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			prop.load(br);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty("jdbc_username");
	}
	public static String password() {
		Properties prop=new Properties();
		PropertiesUtils proputils =new PropertiesUtils();
		File file =new File(proputils.getClass().getClassLoader().getResource("database.properties").getPath());
		try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			prop.load(br);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty("jdbc_password");
	}
	
	public static void main(String[] args) {
		System.out.println(dbname());
		System.out.println(username());
		System.out.println(password());
	}
}
