package org.example;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "POSITION")
public class Position implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    public Position(){}
    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }
    private String name;
    private LocalTime time;

    public String getName() {return name;}

    public void setName(String name) {
        List<String> lista = new ArrayList<>();

        lista.add("goalkeeper");
        lista.add("left wing");
        lista.add("left back");
        lista.add("left midfiedler");
        lista.add("striker");
        lista.add("reserve");

        if (lista.contains(name)){
            this.name = name;
        }
    }

    public LocalTime getTime() {return time;}

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Position(LocalTime time, String name) {
        this.time = time;
        this.name = name;

    }

    public Player getPlayer() {return player;}

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Match getMatch() {return match;}

    public void setMatch(Match match) {
        this.match = match;
    }

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @Override
    public String toString() {
        return (
                 " name: " + name + "  "
                + " time: " + time + "  "
                + " match: " + match.getDate() + "  "
                + " player: " + player.getSurname());
    }
}
