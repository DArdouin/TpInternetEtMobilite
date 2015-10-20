/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Match;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Cette classe contient l'intégralité des matchs de la journée
 * Pas plus de 10 matchs par jours
 * Lors de sa connection à l'application, le client demande au serveur la liste des matchs du jour
 * 
 * @author Damien
 */
public class ListeDesMatchs implements Serializable {
    
    private List<Match> matchs ; 
    
    public ListeDesMatchs() throws ParseException{
        matchs = new ArrayList<>(); 
        
        Date dateMatch = new Date();
        Match match1 = new Match(new Equipe("Sherbrooke"),new Equipe("Montréal"), dateMatch);
        Match match2 = new Match(new Equipe("Sherbrooke"),new Equipe("Ottawa"), dateMatch);
        Match match3 = new Match(new Equipe("Sherbrooke"),new Equipe("Québec"), dateMatch);
        Match match4 = new Match(new Equipe("Sherbrooke"),new Equipe("Vancouver"), dateMatch);
        match1.setNbPenaliteDomicile(2);
        match1.setNbButsDomicile(5);
        match2.setNbPenaliteExterieur(3);
        match2.setNbButsDomicile(3);
        matchs.add(match1);
        matchs.add(match2);
        matchs.add(match3);
        matchs.add(match4);
        
        new Thread(match1).start();
        new Thread(match2).start();
        new Thread(match3).start();
        new Thread(match4).start();
    }

    public List<Match> getMatchs() {
        return matchs;
    }

    public void setMatchs(List<Match> matchs) {
        this.matchs = matchs;
    }
    
    public void addMatch(Match match){
        matchs.add(match) ;
    }
    
    public void deleteMatch(Match match){
        matchs.remove(match);
    }

    public Match chercherMatch(Match match){
        for(Match m : matchs){
            if(m.toString().equals(match.toString()))
                return m;
        }
        return null;
    }
    
    @Override
    public String toString() {
        String s = "Liste des matchs :"; 
        for(Match match : matchs) {
            s += "\n\t- " + match ;
        }
        return s; //To change body of generated methods, choose Tools | Templates.
    }
}
