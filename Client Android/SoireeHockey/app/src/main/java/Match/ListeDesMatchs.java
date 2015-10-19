package Match;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimitri on 16/10/2015.
 */
public class ListeDesMatchs implements Serializable{
    private List<Match> matchs ;

    public ListeDesMatchs() throws ParseException{
        matchs = new ArrayList<>();
        Match match1 = new Match(new Equipe("Sherbrooke"),new Equipe("Montréal"), "2015/10/16 16:55:00");
        Match match2 = new Match(new Equipe("Sherbrooke"),new Equipe("Ottawa"), "2015/10/16 20:00:00");
        Match match3 = new Match(new Equipe("Sherbrooke"),new Equipe("Québec"), "2015/10/16 20:00:00");
        Match match4 = new Match(new Equipe("Sherbrooke"),new Equipe("Vancouver"), "2015/10/16 20:00:00");
        matchs.add(match1);
        matchs.add(match2);
        matchs.add(match3);
        matchs.add(match4);

        match1.getTemps() ;
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
