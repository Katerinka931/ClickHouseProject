package org.clickhouse.services;

import org.clickhouse.repositories.ClickHouseJDBC;
import org.clickhouse.utils.PrintUtil;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public class ClickHouseService {
    private final ClickHouseJDBC jdbc;

    public ClickHouseService(DataSource dataSource) {
        this.jdbc = new ClickHouseJDBC(dataSource);
    }

    public int createSport(String name) {
        return jdbc.createSport(name);
    }

    public boolean deleteSport(String name) {
        if (jdbc.isSportExists(name)) {
            jdbc.deleteSport(name);
            return true;
        }
        return false;
    }

    public int editTeam(String name, String newName, int count) {
        return jdbc.editTeam(name, newName, count);
    }


    public void selectByCount(int count) {
        List<Map<String, Object>> result = jdbc.selectTeamByCount(count);
        PrintUtil.printTeamToScreen(result);
    }

    public void selectWithReplacing() {
        List<Map<String, Object>> result = jdbc.selectWithReplacing();
        PrintUtil.printSportToScreen(result);
    }

    public void selectWithCollapsing() {
        List<Map<String, Object>> result = jdbc.selectWithCollapsing();
        PrintUtil.printCollapsingSportToScreen(result);
    }
}
