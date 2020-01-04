import java.util.HashMap;

public class Wortklasse {
     HashMap Worte;
     HashMap Nachfolger;
     String Anzeige;
     int Groesse;

     public Wortklasse(String x)
     {
        Anzeige = x;
        Worte = new HashMap(1000);
        Groesse = 1000;
        Nachfolger  = new HashMap(50);

     }

    public String getAnzeige() { return Anzeige;}

    public void addWort(String wort)
    {
        if(Worte.containsKey(wort))
        {
            int Vorkommen = (int) Worte.get(wort);
            Vorkommen = Vorkommen+1;
            Worte.remove(wort);
            Worte.put(wort, new Integer(Vorkommen));
        }
        else {
            if(Worte.size() == Groesse)
            {
                expandHashMap();
            }
            Worte.put(wort, new Integer(1));
        }
    }

    public void expandHashMap()
    {
        HashMap tmp = new HashMap (Groesse +1000);
        for(Object o : Worte.entrySet())
        {
            tmp.put(o,Worte.get(o));
        }
        Worte = tmp;
    }

    public void addNachfolger(Wortklasse wortklasse)
    {
        if(Nachfolger.containsKey(wortklasse))
        {
            int Vorkommen = (int) Nachfolger.get(wortklasse);
            Vorkommen = Vorkommen+1;
            Nachfolger.remove(wortklasse);
            Nachfolger.put(wortklasse, Vorkommen);
        }
        else {
            if(Nachfolger.size() == Groesse)
            {
                expandHashMap();
            }
            Nachfolger.put(wortklasse, 1);
        }
    }

    public void berechneWarscheinlichkeiten()
    {
        int i = 0;
        for(Object o : Worte.values())
        {
            if(o != null) {
                i = i + (int) o;
            }
        }
        HashMap tmp = new HashMap(Worte.size());
        for(Object o : Worte.keySet())
        {
            if(Worte.get(o) != null) {
                int z = (int) Worte.get(o);
                tmp.put(o, ((double) z / i));
            }
        }
        Worte = tmp;

        i = 0;
        for(Object o : Nachfolger.values())
        {
            if( o != null) {
                i = i + (int) o;
            }
        }
        tmp = new HashMap(Nachfolger.size());
        for(Object o : Nachfolger.keySet())
        {
            if(Nachfolger.get(o) != null) {
                int z = (int) Nachfolger.get(o);
                tmp.put(o, ((double) z / i));
            }
        }
        Nachfolger = tmp;
    }

}
