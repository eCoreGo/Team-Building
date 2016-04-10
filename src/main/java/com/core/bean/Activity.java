package com.core.bean;

import com.core.json.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Created by stereomatrix on 2016/3/20.
 */
public class Activity {
    public static final Integer TODO = 1;
    public static final Integer DOING = 2;
    public static final Integer DONE = 3;
    public static final Integer OVERTIME = 4;
    private Integer id;
    private String name;
    private Double totalCost;
    private Double totalFoundationCost;
    private String description;
    private Integer status;
    private String startTime;
    private String endTime;
    private Team team;
    private Boolean openCarSchedule;
    private Boolean openExchangeModule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getTotalFoundationCost() {
        return totalFoundationCost;
    }

    public void setTotalFoundationCost(Double totalFoundationCost) {
        this.totalFoundationCost = totalFoundationCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

	public Boolean getOpenCarSchedule() {
		return openCarSchedule;
	}

	public void setOpenCarSchedule(Boolean openCarSchedule) {
		this.openCarSchedule = openCarSchedule;
	}

	public Boolean getOpenExchangeModule() {
		return openExchangeModule;
	}

	public void setOpenExchangeModule(Boolean openExchangeModule) {
		this.openExchangeModule = openExchangeModule;
	}
    
    
}


