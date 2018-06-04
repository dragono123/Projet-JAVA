package modele;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date implements Comparable<Date>, Serializable
{
	private int chJour;
	private int chMois;
	private int chAn;
	private int chJourSemaine;
	private boolean chHeureActive = false;
	private int chHeure = 0;
	private int chMinute = 0;

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
	public Date(int parJour, int parMois, int parAn, int parHeure, int parMinute) throws ExceptionDate
	{
		if(parAn < 1583)
			throw new ExceptionDate("Année invalide !");
		if(parMois < 1 || parMois > 12)
			throw new ExceptionDate("Mois invalide !");
		if(parJour < 1 || parJour > dernierJour(parMois, parAn))
			throw new ExceptionDate("Jour invalide !");
		if(parHeure > 23 || parHeure < 0)
			throw new ExceptionDate("Heure invalide !");
		if(parMinute > 23 || parMinute < 0)
			throw new ExceptionDate("Minute invalide !");
		GregorianCalendar nouveau = new GregorianCalendar(parAn, parMois - 1, parJour);
		
		this.chJour = parJour;
		this.chMois = parMois;
		this.chAn = parAn;
		this.chJourSemaine = nouveau.get(Calendar.DAY_OF_WEEK);
		this.chMinute = parMinute;
		this.chHeure = parHeure;
		this.chHeureActive = true;
		 
	}
	public Date()
	{
		GregorianCalendar aujourdhui = new GregorianCalendar();
		
        chJour = aujourdhui.get(Calendar.DAY_OF_MONTH);
        chMois = aujourdhui.get(Calendar.MONTH) + 1;
        chAn = aujourdhui.get(Calendar.YEAR);
        chJourSemaine = aujourdhui.get(Calendar.DAY_OF_WEEK);
	}
	public Date(int parHeure, int parMinute) throws ExceptionDate
	{
		if(parHeure > 23 || parHeure < 0)
			throw new ExceptionDate("Heure invalide !");
		if(parMinute > 23 || parMinute < 0)
			throw new ExceptionDate("Minute invalide !");
		
		GregorianCalendar aujourdhui = new GregorianCalendar();
		
        chJour = aujourdhui.get(Calendar.DAY_OF_MONTH);
        chMois = aujourdhui.get(Calendar.MONTH) + 1;
        chAn = aujourdhui.get(Calendar.YEAR);
        chJourSemaine = aujourdhui.get(Calendar.DAY_OF_WEEK);
		chMinute = parMinute;
		chHeure = parHeure;
		chHeureActive = true;
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
    	String mois[] = {"", "janvier", "fevrier", "mars", "avril", "mai", "juin", "juillet", "août", "septembre",
    			"octobre", "novembre", "décembre"};
    	String jour[] = {"", "Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
    	
    	String horaire = "";
    	
    	if(chHeureActive)
    	{
    		String heure;
    		String minute;
    		if(chHeure < 10)
    			heure = "0" + chHeure;
    		else
    			heure = Integer.toString(chHeure);
    		if(chMinute < 10)
    			minute = "0" + chMinute;
    		else
    			minute = Integer.toString(chMinute);
    		horaire = " " + heure + ":" + minute;
    	}
    	
        return jour[chJourSemaine] + " " + chJour + " " + mois[chMois] + " " + chAn + horaire;
    }
    public int getNoSemaine()
    {
    	GregorianCalendar cal = new GregorianCalendar(chAn, chMois - 1, chJour);
    	return cal.get(Calendar.WEEK_OF_YEAR);
    }
    
    public int getJourSemaine()
    {
    	return chJourSemaine;
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
    
    public Date anSuiv()
    {
    	Date nouvelleDate = new Date(this);
        nouvelleDate.chAn++;
        return nouvelleDate;
    }
    
    public Date jourLundi()
    {
    	Date nouvelleDate = new Date(this);
    	while(nouvelleDate.getJourSemaine() != 2)
    	{
        	System.out.println(nouvelleDate.getJourSemaine());
    		nouvelleDate = nouvelleDate.dateDeLaVeille();
    	}
    	return nouvelleDate;
    }
    
    public Date moisSuiv()
    {
    	Date nouvelleDate = new Date(this);
        if(nouvelleDate.chMois == 12)
        {
            nouvelleDate.chMois = 1;
        	nouvelleDate.chAn++;
        }
        else
            nouvelleDate.chMois += 1;
        return nouvelleDate;
    }
    public Date anPrec()
    {
    	Date nouvelleDate = new Date(this);
        if(nouvelleDate.chAn >= 1583)
        	nouvelleDate.chAn--;
        return nouvelleDate;
    }
    public Date moisPrec()
    {
    	Date nouvelleDate = new Date(this);
        if(nouvelleDate.chMois == 1)
        {
            nouvelleDate.chMois = 12;
        	nouvelleDate.chAn--;
        }
        else
            nouvelleDate.chMois -= 1;
        return nouvelleDate;
    }
    
    public Date dateDuLendemain()
    {
        int jour = chJour, mois = chMois, an = chAn;
        if(jour == dernierJour(mois, an))
        {
            jour = 1;
            if(mois == 12)
            {
                mois = 1;
                an += 1;
            }
            else
                mois += 1;
        }
        else
            jour += 1;
        Date dateDuLendemain = null;
        try {
			dateDuLendemain = new Date(jour, mois, an);
		} catch (ExceptionDate e) {
			e.printStackTrace();
		}
        return dateDuLendemain;
    }
    public Date dateDeLaVeille()
    {
        Date nouvelleDate = null;
        int jour = chJour, mois = chMois, an = chAn;
        if(jour == 1)
        {
            if(mois == 1)
            {
            	mois = 12;
                an -= 1;
            }
            else
            	mois -= 1;
            jour = dernierJour(mois, an);
        }
        else
        {

        	jour -= 1;
        }
        try {
			nouvelleDate = new Date(jour, mois, an);
		} catch (ExceptionDate e) {
			e.printStackTrace();
		}
        return nouvelleDate;
    }
    
    public int compareTo(Date parDate)
    {
        int valeur = 0;
        if(parDate.chAn > this.chAn)
            valeur = -1;
        else if(parDate.chAn < this.chAn)
            valeur = 1;
        else if((parDate.chMois > this.chMois) || ((parDate.chMois == this.chMois) && (this.chJour < parDate.chJour)) || 
        		((parDate.chHeure > this.chHeure) && (parDate.chMois == this.chMois) && (this.chJour == parDate.chJour)) ||
        		((parDate.chHeure == this.chHeure) && (parDate.chMois == this.chMois) && (this.chJour == parDate.chJour)) && (parDate.chMinute > this.chMinute))
            valeur = -1;
        else if((parDate.chMois < this.chMois) || ((parDate.chMois == this.chMois) && (this.chJour > parDate.chJour)) || 
        		((parDate.chHeure < this.chHeure) && (parDate.chMois == this.chMois) && (this.chJour == parDate.chJour)) ||
        		((parDate.chHeure == this.chHeure) && (parDate.chMois == this.chMois) && (this.chJour == parDate.chJour)) && (parDate.chMinute < this.chMinute))
            valeur = 1;
        
        return valeur;
    }
}
