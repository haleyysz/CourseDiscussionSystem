package com.haleyysz.discussion;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;

@Configuration
public class MongoConf {
    public @Bean
    Mongo mongo() throws UnknownHostException {
        return new Mongo("localhost");
    }

    public @Bean
    MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "discussion");
    }
}
