package org.clickhouse.dto;

import lombok.Getter;
import lombok.Setter;
import org.clickhouse.entities.Team;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TeamPojo {
    private String name;
    private int count;
    private String sport_name;

    public static TeamPojo fromEntity(Team team) {
        TeamPojo pojo = new TeamPojo();
        setDataToPojo(pojo, team);
        return pojo;
    }

    public static TeamPojo fromEntity(Team team, String sport_id) {
        TeamPojo pojo = new TeamPojo();
        pojo.setSport_name(sport_id);
        setDataToPojo(pojo, team);
        return pojo;
    }

    public static Team toEntity(TeamPojo pojo) {
        Team team = new Team();
        team.setName(pojo.getName());
        team.setCount(pojo.getCount());

        return team;
    }

    public static List<TeamPojo> convertTeamsToPojo(List<Team> teams) {
        List<TeamPojo> pojos = new ArrayList<>();
        for (Team team : teams) {
            pojos.add(TeamPojo.fromEntity(team));
        }
        return pojos;
    }

    private static void setDataToPojo(TeamPojo pojo, Team team) {
        pojo.setName(team.getName());
        pojo.setCount(team.getCount());
    }
}
