package com.example.service.stats.bean;

import io.swagger.annotations.ApiModelProperty;

public class Player {
    @ApiModelProperty(notes = "Id of the player", name = "id", required = true, value = "test id")
    private Long id;

    @ApiModelProperty(notes = "name of the player", name = "name", required = true, value = "test name")
    private String name;

    public Player() {
        // Constructeur par défaut
    }

    public Player(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
