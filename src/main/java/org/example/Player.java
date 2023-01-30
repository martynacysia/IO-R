package org.example;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name="PLAYER_ID", foreignKey = @javax.persistence.ForeignKey(name = "FK_PLAYER_PER"))
@Table(name="PLAYER")
//@DiscriminatorValue("Player")
public class Player extends Person implements Serializable{

    public Team getTeam() {return team;}

    public void setTeam(Team team) {
        this.team = team;
    }

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="TEAM_ID",foreignKey = @javax.persistence.ForeignKey(name = "FK_PLAYER_TEAM"))
    private Team team;


    @OneToMany(mappedBy="player")
    private Set<Position> position = new HashSet<>();

    public Player() {}

    public Player(String firstname, String surname) {
        super(firstname, surname);
    }

    @Override
    public String toString() {
        return (super.getFirstname() + " "
                +super.getSurname()
                //+ " team: " + team.getCountry()
        );
    }

}

