package modele;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import data.Data;

public class Date implements Comparable<Date>, Serializable
{
	private int chJour;
	private int chMois;
	private int chAn;
	private int chJourSemaine;

    /**
    * 
    * @param parJour, parMois, parAn 
    *
    */
	public Date(int parJour, int parMois, int parAn) throws ExceptionDate
	{
		if(parAn < 1583)
			throw new ExceptionDate("Année invalide !");
		if(parMois < 1 || parMois > 12)
			throw new ExceptionDate("Mois invalide !");
		if(parJour < 1 || parJour > dernierJour(parMois, parAn))
			throw new ExceptionDate("Jour invalide !");
		
		GregorianCalendar nouveau = new GregorianCalendar(parAn, parMois - 1, parJour);
		
		this.chJour = parJour;
		this.chMois = parMois;
		this.chAn = parAn;
		this.chJourSemaine = nouveau.get(Calendar.DAY_OF_WEEK);
	}
	public Date()
	{
		GregorianCalendar aujourdhui = new GregorianCalendar();
		
        chJour = aujourdhui.get(Calendar.DAY_OF_MONTH);
        chMois = aujourdhui.get(Calendar.MONTH) + 1;
        chAn = aujourdhui.get(Calendar.YEAR);
        chJourSemaine = aujourdhui.get(Calendar.DAY_OF_WEEK);
	}
    public Date(Date parDate)
    {
		this.chJour = parDate.chJour;
		this.chMois = parDate.chMois;
		this.chAn = parDate.chAn;
		this.chJourSemaine = parDate.chJourSemaine;
    }
    public String toString()
    {
        return Data.jour[chJourSemaine] + " " + chJour + " " + Data.mois[chMois] + " " + chAn;
    }
    
    public int dernierJour(int parMois, int parAn)
    {
        int nombreJour;
        switch(parMois)
        {
            case 4:
            case 6:
            case 9:
            case 11:
                nombreJour = 30;break;
            case 2:
                if(((parAn%4 == 0) && (parAn%100 != 0)) || (parAn%400 == 0))
                    nombreJour = 29;
                else
                    nombreJour = 28;
                break;
            default:
                nombreJour = 31;
        }
        return nombreJour;
    }
    
    public int getJour()
    {
    	return chJour;
    }
    public int getMois()
    {
    	return chMois;
    }
    public int getAn()
    {
    	return chAn;
    }
    
    
    public int compareTo(Date parDate)
    {
        int valeur = 0;
        if(parDate.chAn > this.chAn)
            valeur = -1;
        else if(parDate.chAn < this.chAn)
            valeur = 1;
        else if((parDate.chMois > this.chMois) || ((parDate.chMois == this.chMois) && (this.chJour < parDate.chJour)))
            valeur = -1;
        else if((parDate.chMois < this.chMois) || ((parDate.chMois == this.chMois) && (this.chJour > parDate.chJour)))
            valeur = 1;
        
        return valeur;
    }
}
