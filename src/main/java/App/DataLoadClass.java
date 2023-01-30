/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import org.example.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.time.LocalDate;
import java.time.LocalTime;

/*
 @author Martyna Kurbiel & Maksymilian Złotkowski
 */
public final class DataLoadClass {

    /*
     * Creates test data
     */

    protected void createData() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Transaction tx = null;

            // people

            try {
                tx = session.beginTransaction();
                Person person1 = new Person("Ola", "Ronaldihno");
                session.save((person1));
                Person person2 = new Person("Bohater", "Szczęsny");
                session.save((person2));
                Person person3 = new Person("Kylian", "Mekambe");
                session.save((person3));
                tx.commit();

            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
            }

            // players and teams

            tx = session.beginTransaction();
            Player player1 = new Player("Mati", "Migos");

            Team team1 = new Team("Polska");
            player1.setTeam(team1);
            session.save(player1);

            Player player2 = new Player("Leonardo", "Bonucci");

            Team team2 = new Team("Włochy");
            player2.setTeam(team2);
            session.save(player2);

            Player player3 = new Player("Harry", "Kane");

            Team team3 = new Team("Anglia");
            player3.setTeam(team3);
            session.save(player3);

            Player player4 = new Player("Martyna", "Kurbiel");
            player4.setTeam(team1);
            session.save(player4);


            // coaches and teams
            Coach coach1 = new Coach("Czesio", "Michniewicz");
            coach1.setTeam(team1);
            session.save(team1);
            session.save(coach1);

            Coach coach2 = new Coach("Roberto", "Mancini");
            coach2.setTeam(team2);
            session.save(team2);
            session.save(coach2);

            Coach coach3 = new Coach("Gareth", "Southgate");
            coach3.setTeam(team3);
            session.save(team3);
            session.save(coach3);

            // teams

            Team team4 = new Team("Argentyna");
            Coach coach4 = new Coach("Lionel", "Scaloni");
            coach4.setTeam(team4);
            session.save(team4);
            session.save(coach4);

            Team team5 = new Team("Francja");
            Coach coach5 = new Coach("Didier", "Deschamps");
            coach5.setTeam(team5);
            session.save(team5);
            session.save(coach5);

            // matches

            Match match1 = new Match(LocalDate.parse("2022-12-30"), "3:2");
            match1.setTeam1(team1);
            match1.setTeam2(team2);

            Match match2 = new Match(LocalDate.parse("2022-12-31"), "2:1");
            match2.setTeam1(team2);
            match2.setTeam2(team3);

            Match match3 = new Match(LocalDate.parse("2022-11-07"), "0:1");
            match3.setTeam1(team4);
            match3.setTeam2(team5);

            session.save(match1);
            session.save(match2);
            session.save(match3);

            // stadiums

            Stadium stadium1 = new Stadium();
            stadium1.setName("Estadio Santiago Bernabéu");
            stadium1.setLocation("Madryt");

            Stadium stadium2 = new Stadium();
            stadium2.setName("Narodowy");
            stadium2.setLocation("Wawa");

            match1.setStadium(stadium1);
            match2.setStadium(stadium2);
            match3.setStadium(stadium1);

            session.save(stadium1);
            session.save(stadium2);

            session.save(match1);
            session.save(match2);
            session.save(match3);

            // positions

            Position position1 = new Position();
            position1.setTime(LocalTime.parse("10:00:00"));
            position1.setName("goalkeeper");
            position1.setPlayer(player1);
            position1.setMatch(match1);

            Position position2 = new Position();
            position2.setTime(LocalTime.parse("10:15:00"));
            position2.setName("striker");
            position2.setPlayer(player1);
            position2.setMatch(match2);

            Position position3 = new Position();
            position3.setTime(LocalTime.parse("10:25:00"));
            position3.setName("left wing");
            position3.setPlayer(player2);
            position3.setMatch(match2);

            session.save(position1);
            session.save(position2);
            session.save(position3);

            tx.commit();
        }
    }
}

