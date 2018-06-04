package modele;

import java.io.Serializable;


public class Evt implements Comparable<Evt>, Serializable
{
    private Date chDate = new Date();
    private String chNom;
    private String chDescription;
    public Evt(Date parDate, String parNom, String parDescription)
    {
        chDate = parDate;
        chNom = parNom;
        chDescription = parDescription;
    }
    public String toString()
    {
        return chDate + "|" + chNom + "|" + chDescription;
    }
    public String getNom()
    {
        return chNom;
    }
    public String getLieu()
    {
        return chDescription;
    }
    public Date getDate()
    {
        return chDate;
    }
    public void setNom(String parNom)
    {
       chNom = parNom; 
    }
    public void setDate(Date parDate)
    {
       chDate = parDate; 
    }
    public void setLieu(String parDescription)
    {
    	chDescription = parDescription; 
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
