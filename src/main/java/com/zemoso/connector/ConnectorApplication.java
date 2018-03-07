package com.zemoso.connector;

import com.zemoso.connector.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class ConnectorApplication {
    private static Logger logger = LoggerFactory.getLogger(ConnectorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ConnectorApplication.class, args);

        Scanner sc = new Scanner(System.in);

        logger.info("Please enter csv file name:");
        String csvFilePath = sc.nextLine();

        Map<String,List< String>> data = new LinkedHashMap<>();
        //load csv
        try {
            data = Util.loadCSV(csvFilePath);
            logger.info("CSV file is loaded successfully");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

}
