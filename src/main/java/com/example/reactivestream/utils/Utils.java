package com.example.reactivestream.utils;

import org.apache.commons.lang3.StringUtils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

public class Utils {

	private Utils() {
	}

	public static String getEnvironmentConfig(String propName) {
		if (StringUtils.isNotBlank(propName)) {
			String propValue = System.getenv(propName);
			if (StringUtils.isBlank(propValue)) {
				propValue = System.getProperty(propName);
			}
			return propValue;
		} else {
			return propName;
		}
	}

	
	
	public static MongoClient mongoClient() {
		StringBuilder connectionStringSb = new StringBuilder("mongodb+srv://");
		connectionStringSb.append(Utils.getEnvironmentConfig("username"));
		connectionStringSb.append(":");
		connectionStringSb.append(Utils.getEnvironmentConfig("password"));
		connectionStringSb.append("@");
		connectionStringSb.append(Utils.getEnvironmentConfig("clustername"));
		connectionStringSb.append("/");
		connectionStringSb.append(Utils.getEnvironmentConfig("dbname"));
		connectionStringSb.append("?");
		connectionStringSb.append(Utils.getEnvironmentConfig("otheroption"));
		ConnectionString connectionString = new ConnectionString(connectionStringSb.toString());
		MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.build();

		return MongoClients.create(mongoClientSettings);
	}
}
