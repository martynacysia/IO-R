package App;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import org.example.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class MainJPA {

    private final static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("lab");
    public static void main(String[] args) {
        final DataLoadClass dataLoad = new DataLoadClass();
        dataLoad.createData();
        testJPQL();
        showMatches();
        FACTORY.close();
    }

    public static void testJPQL() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("select m from Match m where STADIUM_ID is 1");
            List<Match> matches = query.list();
            System.out.println("\n" + "Query Results:");
            matches.stream().forEach((match) -> {
                System.out.println("\n"+
                        "Team 1: " + match.getTeam1().getCountry() + " " +
                        "  Team 2: " + match.getTeam2().getCountry() + " " +
                        "  Data: " + match.getDate() + " " +
                        "  Stadion: " + match.getStadium().getName() + " " +
                        "  Wynik: " + match.getResult());
            });
        }
    }

    public static void showMatches() {

        // projekcja JPQL

        EntityManager em = FACTORY.createEntityManager();
        List<Match> result = em.createQuery("select m from Match m").getResultList();
        result.stream().forEach((match) -> {
            System.out.println("\n"+
                    "Team 1: " + match.getTeam1().getCountry() + " " +
                    "  Team 2: " + match.getTeam2().getCountry() + " " +
                    "  Data: " + match.getDate() + " " +
                    "  Stadion: " + match.getStadium().getName() + " " +
                    "  Wynik: " + match.getResult());
        });
        System.out.println("=======================");

        // CRITERIA API
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery(Match.class);
        Root<Match> root = criteria.from(Match.class);
        criteria.select(root);
        result = em.createQuery(criteria).getResultList();
        result.stream().forEach((match) -> {
            System.out.println("\n"+
                    "Team 1: " + match.getTeam1().getCountry() + " " +
                    "  Team 2: " + match.getTeam2().getCountry() + " " +
                    "  Data: " + match.getDate() + " " +
                    "  Stadion: " + match.getStadium().getName() + " " +
                    "  Wynik: " + match.getResult());
        });

        // z warunkiem JPQL

        List<Match> result2 = em.createQuery("select m from Match m where m.team1 is 1").getResultList();
        result2.stream().forEach((match) -> {
            System.out.println("\n"+
                    "Team 1: " + match.getTeam1().getCountry() + " " +
                    "  Team 2: " + match.getTeam2().getCountry() + " " +
                    "  Data: " + match.getDate() + " " +
                    "  Stadion: " + match.getStadium().getName() + " " +
                    "  Wynik: " + match.getResult());
        });
        System.out.println("=======================");

        // CRITERIA API
        CriteriaBuilder builder2 = em.getCriteriaBuilder();
        CriteriaQuery<Match> q = builder2.createQuery(Match.class);
        Root<Match> c = q.from(Match.class);
        q.select(c).where(builder2.equal(c.get("team1"), 1));

        TypedQuery<Match> query = em.createQuery(q);
        List<Match> results = query.getResultList();

        results.stream().forEach((match) -> {
            System.out.println("\n"+
                    "Team 1: " + match.getTeam1().getCountry() + " " +
                    "  Team 2: " + match.getTeam2().getCountry() + " " +
                    "  Data: " + match.getDate() + " " +
                    "  Stadion: " + match.getStadium().getName() + " " +
                    "  Wynik: " + match.getResult());
        });

        System.out.println("=======================");

        // złączenie + operacja na kolekcji JPQL

        List<Stadium> result3 = em.createQuery("SELECT DISTINCT d FROM Match m where m.stadium.location='Madryt'", Stadium.class).getResultList();

        System.out.println(result3);
        System.out.println("=======================");

        // CRITERIA API

        CriteriaBuilder builder3 = em.getCriteriaBuilder();
        CriteriaQuery<Stadium> q3 = builder3.createQuery(Stadium.class);
        Root<Match> matches = q3.from(Match.class);

        Join<Match, Stadium> stad = matches.join("stadium");
        q3.select(stad).where(builder3.equal(stad.get("location"), "Madryt")).distinct(true);

        List<Stadium> stadiony = em.createQuery(q3).getResultList();
        System.out.println("\n"+"Mecze na stadionie w Madrycie: "+"\n"+ stadiony);


        // agregacja z frazą 'having' JPQL

        List result7 = em.createQuery("SELECT count(m) FROM Match m  LEFT OUTER JOIN Stadium s on m.stadium = s.id " +
                "GROUP BY m.stadium HAVING count(m)>1"
        ).getResultList();

        System.out.println("=======================");
        System.out.println("Liczba meczy na jednym stadionie > 1: "+ result7.get(0));
        System.out.println("=======================");


        // CRITERIA API

        CriteriaBuilder builder4 = em.getCriteriaBuilder();
        CriteriaQuery<Long> q4 = builder4.createQuery(Long.class);
        Root<Match> matches_count = q4.from(Match.class);

        Join<Match, Stadium> stad_match = matches_count.join("stadium");
        q4.select(builder4.count(stad_match)).groupBy(stad_match.get("id")).having(builder4.gt(builder4.count(stad_match), 1));

        List<Long> liczba = em.createQuery(q4).getResultList();

        System.out.println("=======================");
        System.out.println("Liczba meczy na jednym stadionie > 1: "+ liczba.get(0));
        System.out.println("=======================");

    }
}
