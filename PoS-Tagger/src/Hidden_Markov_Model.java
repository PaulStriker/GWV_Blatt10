import java.util.List;

public class Hidden_Markov_Model {

    String Satz;
    Wortklasse letzteWortklasse;
    List<Wortklasse> Wortklassen;
    public Hidden_Markov_Model(Wortklasse w, int Laenge, List<Wortklasse> wortklassen)
    {
        letzteWortklasse = w;
        Satz = getNextWort(w);
        Wortklassen = wortklassen;
        for(int i = 0; i< Laenge; i++)
        {
            erweitereSatz();
        }
        System.out.println(Satz);

    }

    public void erweitereSatz()
    {
        double r = Math.random();
        double sum_of_probs = 0.0;
        for(Object o : letzteWortklasse.Nachfolger.keySet())
        {
            if(o != null && letzteWortklasse.Nachfolger.get(o) != null) {
                double dtmp = (double) letzteWortklasse.Nachfolger.get(o);
                sum_of_probs = sum_of_probs + dtmp;
                if (sum_of_probs > r) {
                    letzteWortklasse = (Wortklasse) o;
                    Satz = Satz + " " + getNextWort(letzteWortklasse);
                    break;
                }
            }
        }
    }

    public String getNextWort(Wortklasse w)
    {
        double r = Math.random();
        double sum_of_probs = 0.0;
        for(Object o : w.Worte.keySet())
        {
            double dtmp = (double) w.Worte.get(o);
            sum_of_probs = sum_of_probs + dtmp;
            if(sum_of_probs > r)
            {
                return (String) o;
            }
        }
        return "";
    }
}
