package com.zemoso.connector.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/***********************************************************************************************************************
 * @author spolley
 * @since 06/03/2016
 * This is a utility class.
 **********************************************************************************************************************/
public class Util {
    private static Logger logger = LoggerFactory.getLogger(Util.class);

    /**
     * This function will load data from a .CSV file and will store to
     * a LinkedHashMap.
     *
     * @param filename as String
     * @return LikedHashMap with data.
     */
    public static Map<String, List<String>> loadCSV(String filename) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(filename));

        String[] colNames = br.readLine().split(",");
        String line = "";

        //Get the column names and use them as key in Map.
        Map<String, List<String>> map = new LinkedHashMap<>();
        for (String col : colNames) {
            List<String> list = new ArrayList<>();
            map.put(col.replace("\"", ""), list);
        }

        //Read the csv and store the data in arraylist
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            int i = 0;

            for (Map.Entry<String, List<String>> entry : map.entrySet()) {

                if (i < values.length) {

                    ArrayList<String> arrayList = (ArrayList<String>) entry.getValue();

                    arrayList.add(values[i].replace("\"", ""));
                    map.put(entry.getKey(), arrayList);

                    i++;

                } else {        //if some column has no entry

                    ArrayList<String> arrayList = (ArrayList<String>) entry.getValue();

                    arrayList.add("");
                    map.put(entry.getKey(), arrayList);
                }

            }


        }
        return map;
    }
}
