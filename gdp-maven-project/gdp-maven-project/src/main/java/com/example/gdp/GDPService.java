package com.example.gdp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class GDPService {
    private static final String API_URL =
            "https://api.worldbank.org/v2/countries/IND/indicators/NY.GDP.MKTP.CD?per_page=5000&format=json";

    private List<GDPData> gdpList;

    public GDPService() throws Exception {
        this.gdpList = fetchGDPData();
    }

    /** Fetch data from API */
    private List<GDPData> fetchGDPData() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.toString());

        // The second element in the root is an array of records
        JsonNode records = root.get(1);

        List<GDPData> list = new ArrayList<>();
        for (JsonNode record : records) {
            String year = record.get("date").asText();
            JsonNode valNode = record.get("value");
            Double value = valNode.isNull() ? null : valNode.asDouble();
            list.add(new GDPData(year, value));
        }

        return list;
    }

    /** Sort by year ascending */
    public List<GDPData> sortByYear() {
        return gdpList.stream()
                .sorted(Comparator.comparing(GDPData::getYear))
                .collect(Collectors.toList());
    }

    /** Search year by prefix */
    public List<GDPData> searchByYearPrefix(String prefix) {
        return gdpList.stream()
                .filter(d -> d.getYear().startsWith(prefix))
                .collect(Collectors.toList());
    }

    /** Compare year with previous year and calculate % change */
    public String compareWithPreviousYear(String year) {
        Map<String, Double> map = gdpList.stream()
                .filter(d -> d.getValue() != null)
                .collect(Collectors.toMap(GDPData::getYear, GDPData::getValue));

        if (!map.containsKey(year)) {
            return "Year " + year + " not found.";
        }

        int prevYear = Integer.parseInt(year) - 1;
        String prevYearStr = String.valueOf(prevYear);

        if (!map.containsKey(prevYearStr)) {
            return "Previous year data not found for " + year;
        }

        double current = map.get(year);
        double previous = map.get(prevYearStr);

        double change = ((current - previous) / previous) * 100.0;

        return String.format("GDP %s vs %s: %.2f%% %s",
                year, prevYearStr,
                Math.abs(change),
                change >= 0 ? "increase" : "decrease");
    }
}
