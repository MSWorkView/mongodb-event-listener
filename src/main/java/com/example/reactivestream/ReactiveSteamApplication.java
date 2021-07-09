package com.example.reactivestream;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.example.reactivestream.datasourceconfig.MongoConnectionConfig;
import com.example.reactivestream.utils.Utils;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class ReactiveSteamApplication implements CommandLineRunner{

	 
	MongoConnectionConfig mongoConnectionConfig = new MongoConnectionConfig(Utils.mongoClient(),
			(String)	Utils.getEnvironmentConfig("dbname"));
	
	public static void main(String[] args) {
	
		SpringApplication.run(ReactiveSteamApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		 this.mongoConnectionConfig
         .changeStream(org.bson.Document.class)
         .listen()
         .doOnNext(System.out::println)
         .blockLast(java.time.Duration.ofMinutes(10));
		
	}


}
