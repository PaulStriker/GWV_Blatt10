import java.io.IOException;

public class mainClass {

    public static void main(String[] args)
    {
        try{
            Einlesetool einlesetool = new Einlesetool();
            //Hidden_Markov_Model H1 = new Hidden_Markov_Model(einlesetool.getWortklassen().get(0), 10, einlesetool.getWortklassen());
            Commandline_Tagger c = new Commandline_Tagger(einlesetool.getWortklassen());
        }
        catch (IOException e)
        {
            System.out.println("Es gab eine IOException.");
        }
    }
}
