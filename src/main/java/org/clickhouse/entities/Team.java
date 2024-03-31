package org.clickhouse.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Team {
    private String name;

    private int count;

    private Sport teamsSport;
}
