package com.example.gdp;

import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            GDPService service = new GDPService();

            System.out.println("=== Sorted by Year (Asc) ===");
            List<GDPData> sorted = service.sortByYear();
            sorted.forEach(System.out::println);

            System.out.println("\n=== Search by Year Prefix '202' ===");
            List<GDPData> searchResults = service.searchByYearPrefix("202");
            searchResults.forEach(System.out::println);

            System.out.println("\n=== Compare 2024 with Previous Year ===");
            System.out.println(service.compareWithPreviousYear("2024"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
