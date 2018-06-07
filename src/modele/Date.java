package modele;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import data.Data;
/**
 * Cette classe représente une date
 * @author Vincent LIM Simon Ledoit
 * @version 1.0
 *
 */
public class Date implements Comparable<Date>, Serializable
{
	private int chJour;
	private int chMois;
	private int chAn;
	private int chJourSemaine;

	/**
	 * Constructeur de la date à partir du jour du mois et de l'année
	 * @param parJour correspond au jour de la date
	 * @param parMois correspond au mois de la date
	 * @param parAn correspond à l'année de la date
	 * @throws ExceptionDate pour vérifier si la date construite correspond aux normes
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
	/**
	 * Créer une date automatiquement à partir du jour actuel
	 */
	public Date()
	{
		GregorianCalendar aujourdhui = new GregorianCalendar();
		
        chJour = aujourdhui.get(Calendar.DAY_OF_MONTH);
        chMois = aujourdhui.get(Calendar.MONTH) + 1;
        chAn = aujourdhui.get(Calendar.YEAR);
        chJourSemaine = aujourdhui.get(Calendar.DAY_OF_WEEK);
	}
	/**
	 * Construit une date à partir d'une autre date
	 * @param parDate la date à partir de laquelle sera construite la nouvelle
	 */
    public Date(Date parDate)
    {
		this.chJour = parDate.chJour;
		this.chMois = parDate.chMois;
		this.chAn = parDate.chAn;
		this.chJourSemaine = parDate.chJourSemaine;
    }
    /**
     * Permet l'affichage de la date
     */
    public String toString()
    {
        return Data.jour[chJourSemaine] + " " + chJour + " " + Data.mois[chMois] + " " + chAn;
    }
    /**
     * Permet de récupérer le dernier jour du mois à partir d'un mois et d'une année
     * @param parMois représente le mois
     * @param parAn représente le jour
     * @return le denier jour du mois
     */
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
    /**
     * Getter permettant de récupérer le jour de la date
     * @return chJour qui correspond au jour de la date
     */
    public int getJour()
    {
    	return chJour;
    }
    /**
     * Getter permettant de récupérer le date de la date
     * @return chMois qui correspond au mois de la date
     */
    public int getMois()
    {
    	return chMois;
    }

    /**
     * Getter permettant de récupérer l'année de la date
     * @return chAn qui correspond à l'année de la date
     */
    public int getAn()
    {
    	return chAn;
    }
    
    /**
     * Permet de comparer deux dates(permet le tri dans les collections)
     */
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
