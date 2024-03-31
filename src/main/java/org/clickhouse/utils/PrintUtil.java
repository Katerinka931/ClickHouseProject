package org.clickhouse.utils;

import java.util.List;
import java.util.Map;

public class PrintUtil {
    public static void printTeamToScreen(List<Map<String, Object>> result) {
        for (Map<String, Object> row : result){
            String name = (String) row.get("name");
            int count = (int) row.get("count");
            String sport_name = (String) row.get("sport_name");
            System.out.printf("%s\t\t%d\t\t%s\n", name, count, sport_name);
        }
    }
    public static void printSportToScreen(List<Map<String, Object>> result) {
        for (Map<String, Object> row : result){
            String name = (String) row.get("name");
            System.out.println(name);
        }
    }
    public static void printCollapsingSportToScreen(List<Map<String, Object>> result) {
        System.out.printf("%s\t\t%s\n", "ID", "Количество");
        for (Map<String, Object> row : result){
            int id = (int) row.get("id");
            long count = (long) row.get("sportsmen");
            System.out.printf("%d\t\t%d\n", id, count);
        }
    }
}
