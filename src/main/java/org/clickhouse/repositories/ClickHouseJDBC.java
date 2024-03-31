package org.clickhouse.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ClickHouseJDBC {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ClickHouseJDBC(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public int createSport(String name) {
        var params = new MapSqlParameterSource();
        params.addValue("name", name);
        return namedParameterJdbcTemplate.update("INSERT INTO sport.sport (*) VALUES (:name)", params);
    }

    public void deleteSport(String name) {
        var params = new MapSqlParameterSource();
        params.addValue("name", name);
        namedParameterJdbcTemplate.update("ALTER TABLE sport.sport DELETE WHERE name = :name", params);
    }

    public int editTeam(String name, String newName, int count) {
        var params = new MapSqlParameterSource();
        params.addValue("name", name);
        params.addValue("newName", newName);
        params.addValue("count", count);

        return namedParameterJdbcTemplate.update("ALTER TABLE sport.team UPDATE sport_name = :newName, count = :count WHERE name = :name;", params);
    }

    public List<Map<String, Object>> selectTeamByCount(int count) {
        var params = new MapSqlParameterSource();
        params.addValue("count", count);

        return namedParameterJdbcTemplate.queryForList("SELECT * FROM sport.team WHERE count > :count", params);
    }

    public boolean isSportExists(String name) {
        return namedParameterJdbcTemplate.queryForObject("SELECT count(*) FROM sport.sport WHERE name = :name",
                Collections.singletonMap("name", name), Integer.class) > 0;
    }

    public List<Map<String, Object>> selectWithReplacing() {
        return jdbcTemplate.queryForList("SELECT * FROM sport.replacingSport ORDER BY id ASC, version DESC LIMIT 1 BY id");
    }

    public List<Map<String, Object>> selectWithCollapsing() {
        return jdbcTemplate.queryForList("SELECT id, sum(sportsmen * sign) as sportsmen FROM sport.collapsingSport" +
                " GROUP BY id HAVING SUM(sign) > 0");
    }
}