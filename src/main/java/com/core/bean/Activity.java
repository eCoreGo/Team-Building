package com.core.bean;

import com.core.json.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Created by stereomatrix on 2016/3/20.
 */
public class Activity {
    public static final Integer DRAFT = 0;
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
    private Date startTime;
    private Date endTime;
    private Team team;
    private int openCarSchedule;
    private int openExchangeModule;

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
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

	public int getOpenCarSchedule() {
		return openCarSchedule;
	}

	public void setOpenCarSchedule(int openCarSchedule) {
		this.openCarSchedule = openCarSchedule;
	}

	public int getOpenExchangeModule() {
		return openExchangeModule;
	}

	public void setOpenExchangeModule(int openExchangeModule) {
		this.openExchangeModule = openExchangeModule;
	}
    
    
}


