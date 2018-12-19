package com.example.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Area {

    @Getter @Setter
    private Integer id;

    private Integer postalcode;

    private String address;

    private Integer type;
}