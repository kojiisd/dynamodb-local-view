package jp.gr.java_conf.kojiisd.dynamodb_local;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author kojiisd
 */
@SpringBootApplication
@ComponentScan
public class DynamodbViewApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamodbViewApplication.class, args);
    }

}
