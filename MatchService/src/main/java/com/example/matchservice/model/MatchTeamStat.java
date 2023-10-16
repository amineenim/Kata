package com.example.matchservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel(description = "Match team statistics")
@Entity
public class MatchTeamStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Match team statistics ID")
    private Long id;

    @ApiModelProperty(value = "Number of shots taken by the team")
    private int nombreDeTir;

    @ApiModelProperty(value = "Number of shots on target by the team")
    private int tirCadre;

    @ApiModelProperty(value = "Number of goals scored by the team")
    private int but;

    @ApiModelProperty(value = "Possession percentage of the team")
    private int possession;

    @ApiModelProperty(value = "Number of passes made by the team")
    private int passes;

    @ApiModelProperty(value = "Number of fouls committed by the team")
    private int fautes;

    @ApiModelProperty(value = "Number of yellow cards received by the team")
    private int cartonJaune;

    @ApiModelProperty(value = "Number of red cards received by the team")
    private int cartonRouge;

    @ApiModelProperty(value = "Number of offside situations for the team")
    private int horsJeu;

    @ApiModelProperty(value = "Number of corner kicks awarded to the team")
    private int corner;

    @ApiModelProperty(value = "ID of the team associated with the statistics")
    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "match_id")
    @JsonBackReference
    @ApiModelProperty(value = "Match associated with the team statistics")
    private FootballMatch match;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNombreDeTir() {
        return nombreDeTir;
    }

    public void setNombreDeTir(int nombreDeTir) {
        this.nombreDeTir = nombreDeTir;
    }

    public int getTirCadre() {
        return tirCadre;
    }

    public void setTirCadre(int tirCadre) {
        this.tirCadre = tirCadre;
    }

    public int getBut() {
        return but;
    }

    public void setBut(int but) {
        this.but = but;
    }

    public int getPossession() {
        return possession;
    }

    public void setPossession(int possession) {
        this.possession = possession;
    }

    public int getPasses() {
        return passes;
    }

    public void setPasses(int passes) {
        this.passes = passes;
    }

    public int getFautes() {
        return fautes;
    }

    public void setFautes(int fautes) {
        this.fautes = fautes;
    }

    public int getCartonJaune() {
        return cartonJaune;
    }

    public void setCartonJaune(int cartonJaune) {
        this.cartonJaune = cartonJaune;
    }

    public int getCartonRouge() {
        return cartonRouge;
    }

    public void setCartonRouge(int cartonRouge) {
        this.cartonRouge = cartonRouge;
    }

    public int getHorsJeu() {
        return horsJeu;
    }

    public void setHorsJeu(int horsJeu) {
        this.horsJeu = horsJeu;
    }

    public int getCorner() {
        return corner;
    }

    public void setCorner(int corner) {
        this.corner = corner;
    }

    public FootballMatch getMatch() {
        return match;
    }

    public void setMatch(FootballMatch match) {
        this.match = match;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
