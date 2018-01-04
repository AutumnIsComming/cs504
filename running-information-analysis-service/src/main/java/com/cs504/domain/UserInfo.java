package com.cs504.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "USER")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfo {
    @Id
    @GeneratedValue
    private Long id;

    private String userName;
    private String address;

    public UserInfo() {
    }

    public UserInfo(String userName, String address) {
        this.userName = userName;
        this.address = address;
    }
}
