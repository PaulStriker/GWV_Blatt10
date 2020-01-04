import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Einlesetool {
    List<Wortklasse> Wortklassen;
    public Einlesetool() throws IOException
    {
        Wortklassen = new LinkedList<>();
        lese_ein();
        for(Wortklasse w : Wortklassen)
        {
            w.berechneWarscheinlichkeiten();
        }
    }

    public void lese_ein() throws IOException {
        BufferedReader b1 = new BufferedReader(new FileReader("hdt-tagged.txt"));
        boolean added;
        boolean added2;
        String zeile1 = b1.readLine(); // musst schon einmal ausgeführt sein um die erste Zeile zu überspringen
        zeile1 = b1.readLine();
        String zeile2;
        int i = 0;  // i zählt die leeren zeilen und wenn es mehrere hinter einader sind, ist das Textdokument zu Ende.
        int z = 0;
        while(zeile1 != null&& z < 200000)
        {
            z++;
            zeile2 = zeile1;
            zeile1 = b1.readLine();
            while(zeile1.equals(""))
            {
                zeile1 = b1.readLine();
            }
            if(!zeile2.equals("")&& zeile1 != null) {
                i = 0;
                StringTokenizer Tokenz1 = new StringTokenizer(zeile2);
                String Token1 = Tokenz1.nextToken();
                String Token2 = Tokenz1.nextToken();
                StringTokenizer Tokenz2 = new StringTokenizer(zeile1);
                String Token3 = Tokenz2.nextToken();
                String Token4 = Tokenz2.nextToken();
                added = false;
                added2 = false;
                for (Wortklasse w : Wortklassen) {
                    if (w.getAnzeige().equals(Token2))
                    {
                        w.addWort(Token1);
                        added2 = true;
                        for(Wortklasse w2: Wortklassen)
                        {
                            if(w2.getAnzeige().equals(Token4))
                            {
                                w.addNachfolger(w2);
                                added = true;
                            }
                        }

                        if(!added)
                        {
                            w.addNachfolger(new Wortklasse(Token4));
                        }

                    }
                }
                if (!added2)
                {
                    Wortklasse tmp = new Wortklasse(Token2);
                    tmp.addNachfolger(new Wortklasse(Token4));
                    Wortklassen.add(tmp);

                }

            }
            else{i++;}
        }
    }

    public List<Wortklasse> getWortklassen(){return Wortklassen;}

}
