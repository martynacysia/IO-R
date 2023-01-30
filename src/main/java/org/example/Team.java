package org.example;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="TEAM")
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private int id;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private String country;

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    @OneToMany(mappedBy = "team")
    private Set<Player> players = new HashSet<>();
    public Team() {
    }
    public Team(String country) {
        this.country = country;
    }

    @OneToOne
    @JoinColumn(name="COACH_ID", foreignKey =  @javax.persistence.ForeignKey(name = "FK_TEAM_COACH"))
    private Coach coach;

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ("players: " + players.size() + " "
                +"coach: " + coach.getSurname() + "  "
                + " country: " + country);
    }


}

