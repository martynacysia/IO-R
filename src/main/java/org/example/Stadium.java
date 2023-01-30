package org.example;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="STADIUM")
public class Stadium implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STADIUM_ID", nullable = false)
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String name;
    private String location;

    public Set<Match> getMatches() {return matches;}

    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }

    @OneToMany(mappedBy = "stadium")
    //@JoinColumn(name="MATCH_ID", foreignKey = @javax.persistence.ForeignKey(name="FK_STADIUM_MATCH"))
    private Set<Match> matches = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Stadium(){}

    public Stadium(String name, String location){
        this.name = name;
        this.location = location;
    }

    @Override
    public String toString() {
        return ("name: " + name + " "
                +"location: " + location + "  "
                +" match: " + matches);
    }
}
