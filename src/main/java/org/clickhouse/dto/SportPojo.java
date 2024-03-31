package org.clickhouse.dto;

import lombok.Getter;
import lombok.Setter;
import org.clickhouse.entities.Sport;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SportPojo {
    private long id;
    private String name;
    private List<TeamPojo> teams;

    public static SportPojo fromEntity(Sport sport) {
        SportPojo pojo = new SportPojo();
        pojo.setName(sport.getName());
        return pojo;
    }

    public static Sport toEntity(SportPojo pojo) {
        Sport sport = new Sport();
        sport.setName(pojo.getName());
        return sport;
    }

    public static List<SportPojo> convertSportsToPojo(List<Sport> sports) {
        List<SportPojo> pojoList = new ArrayList<>();
        for (Sport sport : sports) {
            pojoList.add(SportPojo.fromEntity(sport));
        }
        return pojoList;
    }
}
