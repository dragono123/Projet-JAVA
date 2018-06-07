package modele;

import java.io.Serializable;


public class Evt implements Comparable<Evt>, Serializable
{
    private Date chDate = new Date();
    private String chNom;
    private String chDescription;
    private int chPoids;
    private String chFichier;
    public Evt(Date parDate, String parNom, String parDescription, int parPoids, String parFichier) throws ExceptionEvt
    {
        if(1 > parPoids || parPoids > 4)
        	throw new ExceptionEvt("Poids non compris entre 1 et 4");
        chDate = parDate;
        chNom = parNom;
        chDescription = parDescription;
        chPoids = parPoids;
        chFichier = parFichier;
    }
    public String toString()
    {
        return chDate + "\n" + chNom + "\n" + chDescription + "\nPoids :" + chPoids + "\n" + chFichier;
    }
    public String getNom()
    {
        return chNom;
    }
    public int getPoids()
    {
    	return chPoids;
    }
    public String getFichier()
    {
    	return chFichier;
    }
    public String getDescription()
    {
        return chDescription;
    }
    public Date getDate()
    {
        return chDate;
    }
    public int compareTo(Evt parEvt)
    {
        int test = chDate.compareTo(parEvt.chDate);
        if(test == 0)
        {
            test = chNom.compareTo(parEvt.chNom);
            if(test == 0)
                test = chDescription.compareTo(parEvt.chDescription);
        }
        return test;
    }
    
}
