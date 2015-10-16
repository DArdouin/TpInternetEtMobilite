/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Match;

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
public class ListeDesMatchs {
    
    private List<Match> matchs ; 
    
    public ListeDesMatchs(){
        matchs = new ArrayList<>(); 
        Match match1 = new Match(new Equipe("Sherbrooke"),new Equipe("Montréal"), new Date(2015,10,18,20,00,00));
        Match match2 = new Match(new Equipe("Sherbrooke"),new Equipe("Ottawa"), new Date(2015,10,18,20,00,00));
        Match match3 = new Match(new Equipe("Sherbrooke"),new Equipe("Québec"), new Date(2015,10,18,20,00,00));
        Match match4 = new Match(new Equipe("Sherbrooke"),new Equipe("Vancouver"), new Date(2015,10,18,20,00,00));
        matchs.add(match1);
        matchs.add(match2);
        matchs.add(match3);
        matchs.add(match4);
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

    @Override
    public String toString() {
        String s = "Liste des matchs :"; 
        for(Match match : matchs) {
            s += "\n\t- " + match ;
        }
        return s; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
