package org.example;

import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@PrimaryKeyJoinColumn(name="COACH_ID",foreignKey = @javax.persistence.ForeignKey(name="FK_COACH_PER"))
@Table(name="COACH")
@DiscriminatorValue("Coach")
public class Coach extends Person implements Serializable{

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="TEAM_ID",foreignKey = @javax.persistence.ForeignKey(name = "FK_COACH_TEAM"))
    private Team team;
    public Coach() {}

    public Coach(String firstname, String surname) {
        super(firstname, surname);
    }

    public Team getTeam() {return team;}

    public void setTeam(Team team) {
        this.team = team;
        team.setCoach(this);
    }
    @Override
    public String toString() {
        return (super.getFirstname() + " "
                +super.getSurname() + "  "
                + " team: " + team.getCountry());
    }
}
