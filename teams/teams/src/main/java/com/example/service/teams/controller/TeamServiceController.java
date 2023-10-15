package com.example.service.teams.controller;


import com.example.service.teams.bean.Player;
import com.example.service.teams.bean.Team;

import com.example.service.teams.delegate.PlayerServiceDelegate;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/teams")
@Api(value = "Team Service API", tags = "Operations related to team management")
public class TeamServiceController {

    @Autowired
    PlayerServiceDelegate playerServiceDelegate;
    private static final Map<Long, Team> teams = new HashMap<Long, Team>() {

        {
            List<Player> playersTeam1 = Arrays.asList(new Player(1L, "messi"), new Player(2L, "ronaldo"));
            put(1L, new Team(1L, "Marseille", "France", 11, playersTeam1));
            List<Player> playersTeam2 = Arrays.asList(new Player(3L, "edy"), new Player(4L, "jean"));
            put(2L, new Team(2L, "PSG", "France", 15, playersTeam2));
            List<Player> playersTeam3 = Arrays.asList(new Player(5L, "paul"), new Player(6L, "gaby"), new Player(7L, "dony"));
            put(3L, new Team(3L, "totnham", "bretagne", 14, playersTeam3));
        }

    };
    private long nextId = 1;

    @ApiOperation(value = "Get a team by ID", response = Team.class)
    @ApiParam(value = "ID of the team", required = true)
    @GetMapping("/{id}")
    public Team getTeam(@PathVariable Long id) {
        Team matchingTeam = teams.get(id);
        if(matchingTeam == null){
            matchingTeam = new Team(0L, "Not Found", "N/A", 0, new ArrayList<>());
        }
        return matchingTeam;
    }

    @PostMapping
    @ApiOperation(value = "Create a new team", response = String.class)
    public String createTeam(@RequestBody Team team) throws IOException {
        if(team.getId() == null || team.getName() == null || team.getCountry() == null || team.getNumberOfPlayers() == 0 || team.getPlayers() == null){
            return "id, name, country, number of players and players list are all required";
        }
        //get the list of players
        List<Player> players = team.getPlayers();
        // check if the players list is correct and contains actually existing players
        boolean allPlayersExist = true;

        for (Player p : players) {
            // call the PlayerServiceDelegate
            String response = playerServiceDelegate.callPlayerServiceAndCheckPlayerExists(p.getId());
            // check if the response corresponds to the one in the fallback, when service Player is down
            if(Objects.equals(response, "Circuit Breaker, No response from Player Service at the moment !")){
                return response;
            }
            // since if the id of player is not found i return a player with id 0, i check the equality here
            if(Objects.equals(response, "<Player><id>0</id><name>NOT FOUND</name></Player>")){
                allPlayersExist = false;
                System.out.println("not existing");
                break;
            }
        }
        // check allPlayersExist boolean value
        if(allPlayersExist){
            System.out.println("a new team is being crated");
            long id = nextId++;
            team.setId(id);
            teams.put(id, team);
            String response = "Team " + team.getName()  + " created successfully !";
            return response;

        }else{
            return "players list is not compatible with existing players ";
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a team by ID", response = String.class)
    public String updateTeam(@PathVariable Long id, @RequestBody Team updatedTeam) {
        if(!teams.containsKey(id)){
            return "team with id " + id + " not found !";
        }
        Team existingTeam = teams.get(id);
        // verify and update the name
        if(updatedTeam.getName() != null && !updatedTeam.getName().isEmpty()){
            existingTeam.setName(updatedTeam.getName());
        }
        // check for the numberOfPlayers, must be greater than 0
        if(updatedTeam.getNumberOfPlayers() > 0){
            existingTeam.setNumberOfPlayers(updatedTeam.getNumberOfPlayers());
        }
        // verify and update the country
        if(updatedTeam.getCountry() != null){
            existingTeam.setCountry(updatedTeam.getCountry());
        }

        // verify and update the list of players
        List<Player> updatedPlayers = updatedTeam.getPlayers();
        boolean allPlayersExist = true;
        if(updatedPlayers != null){
            for(Player p : updatedPlayers){
                // we only check for the PlayerServiceDelegate if the user needs to update playersList
                // if not we can update the remaining team attributes without the need for PlayerService to be UP
                System.out.println("checking existence of player with id " + p.getId() + " and name " + p.getName());
                // call the PlayerServiceDelegate
                String response = playerServiceDelegate.callPlayerServiceAndCheckPlayerExists(p.getId());
                // check if the response corresponds to the one from fallBack
                if(Objects.equals(response, "Circuit Breaker, No response from Player Service at the moment !")){
                    return response;
                }
                // the Player service is up, check to see if the returned response corresponds to NOT FOUND player
                if(Objects.equals(response, "<Player><id>0</id><name>NOT FOUND</name></Player>")){
                    allPlayersExist = false;
                    break;
                }
            }
            if(!allPlayersExist){
                return "Failed updating Team. One or more players in the updated list do not exist.";
            }
            existingTeam.setPlayers(updatedPlayers);
        }

        teams.put(id, existingTeam);
        return  "Team " + existingTeam.getName() + " updated successfully!";
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a team by ID", response = String.class)
    @ApiParam(value = "ID of the team to delete", required = true)
    public String deleteTeam(@PathVariable Long id) {
        if(teams.containsKey(id)){
            teams.remove(id);
            return "Deleted with success!";
        }else{
            return "Failed deleting team with id :" +id;
        }
    }
}
