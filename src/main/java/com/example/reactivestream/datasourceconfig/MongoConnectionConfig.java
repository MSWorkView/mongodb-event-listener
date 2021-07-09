package com.example.reactivestream.datasourceconfig;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.mongodb.reactivestreams.client.MongoClient;



public class MongoConnectionConfig extends ReactiveMongoTemplate  {

	public MongoConnectionConfig(MongoClient mongoClient, String databaseName) {
		super(mongoClient, databaseName);
		
	}

	
	

	
}
