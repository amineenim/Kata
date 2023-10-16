package com.example.service.player.controller;

import com.example.service.player.bean.Player;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/players")
@Api(value = "Players Service API", tags = "Operations related to Players management")
public class PlayerServiceController {


    private long nextId = 1;
    private static final Map<Long, Player> players = new HashMap<Long, Player>() {

        {
            put(1L,new Player(1L,"Messi"));
            put(2L,new Player(2L, "Ronaldo"));
        }
    };

    @ApiOperation(value = "Get a player by Id", response = Player.class)
    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable Long id) {
        Player matchingPlayer = players.get(id);
        if(matchingPlayer == null){
            matchingPlayer = new Player(0L, "NOT FOUND");
        }
        return matchingPlayer;
    }

    @PostMapping
    @ApiOperation(value = "Create a new Player", response = Player.class)
    public Player createPlayer(@RequestBody Player player) {
        if(player.getName() == null){
            return new Player(0L, "Name is required");
        }
        long id = nextId++;
        player.setId(id);
        players.put(id, player);
        return player;
    }

    @ApiOperation(value = "Update a Player By Id", response = String.class)
    @PutMapping("/{id}")
    public String updatePlayer(@PathVariable Long id, @RequestBody Player updatedPlayer) {
        if (players.containsKey(id)) {
            updatedPlayer.setId(id);
            players.put(id, updatedPlayer);
            return "updated player with name " + updatedPlayer.getName();
        }else{
            // Gérer le cas où le joueur n'existe pas
            return "updating player with id " + id + " Not found!";
        }
    }

    @ApiOperation(value = "Delete a Player by ID", response = String.class)
    @ApiParam(value = "ID of the Player to delete", required = true)
    @DeleteMapping("/{id}")
    public String deletePlayer(@PathVariable Long id) {
        if(players.containsKey(id)){
            players.remove(id);
            return "Player with id " + id + " deleted!";
        }else{
            return "Failed deleting, not found Player with id " + id;
        }
    }
}
