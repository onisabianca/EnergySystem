package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageProcess {

    public List<String> readCSV() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("sensor.csv"));
        List<String> lines = new ArrayList<>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }

    public String stringToJson (float measurement, long timestamp, String device_id)
    {
        String rez= "{\n" + "\"timestamp\": " + timestamp + ",\n" + "\"device_id\": \"" + device_id + "\",\n" + "\"measurement_value\": " + measurement+",\n"+"}";
        return rez;
    }

}
