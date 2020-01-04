import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Commandline_Tagger {

    String Sentence;
    List<Wortklasse> Wortklassen;
    public Commandline_Tagger(List<Wortklasse> wortklassen)
    {
        Wortklassen = wortklassen;
        Sentence = getSentence();
        String taggedSentence = tagSentence();
        System.out.println(taggedSentence);
    }

    public String getSentence()
    {
        System.out.println("Bitte geben sie einen Satz ein");
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    public String tagSentence()
    {
        String tSentence = new String();
        boolean added;
        Wortklasse letzteWortklasse = Wortklassen.get(0);
        StringTokenizer tokenizer = new StringTokenizer(Sentence);
        if(tokenizer.hasMoreTokens())
        {
            String token = tokenizer.nextToken();
            for(Wortklasse w : Wortklassen)
            {
                for(Object o : w.Worte.keySet())
                {
                    if(o.equals(token))
                    {
                        letzteWortklasse = w;
                        tSentence = tSentence + token + "\\" + w.getAnzeige();
                        break;
                    }
                }
            }
        }
        while (tokenizer.hasMoreTokens())
        {
            String token = tokenizer.nextToken();
            added = false;
            for(Wortklasse w : Wortklassen)
            {
                for(Object o : w.Worte.keySet())
                {
                    if(o.equals(token))
                    {
                        letzteWortklasse = w;
                        tSentence = tSentence + token + "\\" + w.getAnzeige();
                        added = true;
                        break;
                    }
                }
                break;
            }
            if(!added)
            {
                Wortklasse tmpo = null;
                double tmpv = 0.0;
                for(Object o : letzteWortklasse.Nachfolger.keySet())
                {
                    if((double) letzteWortklasse.Nachfolger.get(o) > tmpv)
                    {
                        tmpo = (Wortklasse) o;
                        tmpv = (double)letzteWortklasse.Nachfolger.get(o);
                    }
                }
                tSentence = tSentence + token + "\\" + tmpo.getAnzeige();
            }
        }
        return tSentence;
    }

    
}
