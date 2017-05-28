package com.greencode.hooconservice.Database;

import com.greencode.hooconservice.Constants.DBConstant;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MLabDatabase {
    private static MongoOperations mongoOperation;
    private final static Properties properties = new Properties();
    private final static Logger logger = LoggerFactory.getLogger(MLabDatabase.class);

    public static MongoOperations getMongoOperations() throws Exception {
        if (mongoOperation == null) {
            logger.info("Connecting to db ... ");
            MongoClientURI uri = new MongoClientURI(getDatabaseURI() + getDatabaseName());
            MongoClient client = new MongoClient(uri);

            mongoOperation = new MongoTemplate(client, getDatabaseName());
            logger.info("Connected to db : " + getDatabaseName());
        }

        return mongoOperation;
    }

    private static String getDatabaseName() {
        try {
            InputStream inputStream = MLabDatabase.class.getClassLoader()
                    .getResourceAsStream(DBConstant.PROPERTIES_FILE);
            properties.load(inputStream);

        } catch (IOException e) {

            logger.error("Error:"+e.getMessage());
        }

        return properties.getProperty(DBConstant.PROPERTIES_DB_NAME);
    }

    private static  String getDatabaseURI() {
        try {
            InputStream inputStream = MLabDatabase.class.getClassLoader().getResourceAsStream(DBConstant.PROPERTIES_FILE);
            properties.load(inputStream);

        } catch (IOException e) {
            logger.error("Error:"+e.getMessage());
        }

        String dbURI = "mongodb://" + properties.getProperty(DBConstant.PROPERTIES_DB_USER) +
                ":" + properties.getProperty(DBConstant.PROPERTIES_DB_PASSWORD)   +
                "@" + properties.getProperty(DBConstant.PROPERTIES_DB_IP)      +
                ":" + properties.getProperty(DBConstant.PROPERTIES_DB_PORT)      + "/";

        logger.info(dbURI);

        return dbURI;
    }

    public static Properties setProperties(){
        try {
            InputStream inputStream = MLabDatabase.class.getClassLoader()
                    .getResourceAsStream(DBConstant.PROPERTIES_FILE);
            properties.load(inputStream);

        } catch (IOException e) {

            logger.error("Error:"+e.getMessage());
        }
        return properties;
    }
}
