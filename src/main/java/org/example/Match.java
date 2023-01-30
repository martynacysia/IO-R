package org.example;

import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="MATCH")
public class Match implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATCH_ID", nullable = false)
    private int id;
    private LocalDate date;
    private String result;

    public Team getTeam1() {return team1;}
    public void setTeam1(Team team1) {
        this.team1 = team1;
    }
    public Team getTeam2() {return team2;}

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Match(){}

    public Match(LocalDate date, String result){
        this.date = date;
        this.result = result;
    }

    @OneToMany(mappedBy="match")
    private Set<Position> position = new HashSet<>();
    public Stadium getStadium() {return stadium;}

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="STADIUM_ID",foreignKey = @javax.persistence.ForeignKey(name = "FK_MATCH_STADIUM"))
    private Stadium stadium;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team1")
    private Team team1;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team2")
    private Team team2;

    @Override
    public String toString() {
        return ("date: " + date + " "
                +"result: " + result + "  "
                + " team1: "  + team1.getCountry() + "  "
                + " team2: " + team2.getCountry() + "  "
                + " stadium: " + stadium.getName());
    }
}
