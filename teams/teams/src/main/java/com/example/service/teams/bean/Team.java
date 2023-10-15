package com.example.service.teams.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

public class Team {
    @ApiModelProperty(notes = "Id of the Team",name="id",required=true,value="test id")
    private Long id;
    @ApiModelProperty(notes = "name of the Team",name="name",required=true,value="test name")
    private String name;
    @ApiModelProperty(notes = "Country of the Team",name="country",required=true,value="test country")
    private String country;
    @ApiModelProperty(notes = "number of players in the Team", name = "number of players", required = true, value = "test number of players")
    private int numberOfPlayers;
    @ApiModelProperty(notes = "list of players of the Team", name = "players list", required = true, value = "test players list")
    private List<Player> players;

    public Team() {
    }

    public Team(Long id, String name, String country, int numberOfPlayers, List<Player> players) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.numberOfPlayers = numberOfPlayers;
        this.players = players;
    }

    // Getters et setters

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public List<Player> getPlayers(){
        return players;
    }

    public void setPlayers(List<Player> players){
        this.players = players;
    }
}
