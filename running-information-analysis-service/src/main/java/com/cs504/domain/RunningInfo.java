package com.cs504.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "RUNNING_ANALYSIS")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RunningInfo {

    public enum HealthWarningLevel {
        LOW, NORMAL, HIGH
    }

    @Id
    @GeneratedValue
    private Long id;

    private String runningId;
    private double latitude;
    private double longitude;
    private double runningDistance;
    private double totalRunningTime;
    private int heartRate;
    private String timestamp;
    private HealthWarningLevel healthWarningLevel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userName")
    private UserInfo userInfo;

    public RunningInfo() {
    }

    @JsonCreator
    public RunningInfo(@JsonProperty("username") String userName, @JsonProperty("address") String address) {
        this.userInfo = new UserInfo(userName, address);
    }
}
