package modele;

import java.io.Serializable;


/**
 * Cette classe représente un évènement
 * @author Vincent LIM Simon Ledoit
 * @version 1.0
 *
 */
public class Evt implements Comparable<Evt>, Serializable
{
    private Date chDate = new Date();
    private String chNom;
    private String chDescription;
    private int chPoids;
    private String chFichier;
    /**
     * Constructeur de l'évènement
     * @param parDate correspond à la date de l'évènement
     * @param parNom correspond au nom de l'évènement
     * @param parDescription correspond à la description de l'évènement
     * @param parPoids correspond au poids de l'évènement
     * @param parFichier correspond au fichier d'image de l'évènement
     * @throws ExceptionEvt se lance quand le poids n'est pas compris entre 1 et 4
     */
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
    /**
     * Permet l'affichage en texte
     */
    public String toString()
    {
        return chDate + "\n" + chNom + "\n" + chDescription + "\nPoids :" + chPoids + "\n" + chFichier;
    }
    /**
     * Getter permettant de récupérer le nom de l'évènement
     * @return chNom qui représente le nom de l'évènement
     */
    public String getNom()
    {
        return chNom;
    }
    /**
     * Getter permettant de récupérer le nom de l'évènement
     * @return chNom qui représente le nom de l'évènement
     */
    public int getPoids()
    {
    	return chPoids;
    }
    /**
     * Getter permettant de récupérer le fichier image de l'évènement
     * @return chFichier qui représente le fichier image de l'évènement
     */
    public String getFichier()
    {
    	return chFichier;
    }
    /**
     * Getter permettant de récupérer la description de l'évènement
     * @return chDescription qui représente la description de l'évènement
     */
    public String getDescription()
    {
        return chDescription;
    }
    /**
     * Getter permettant de récupérer la date de l'évènement
     * @return chDate qui représente la date de l'évènement
     */
    public Date getDate()
    {
        return chDate;
    }
    /**
     * Permet la comparaison entre deux évènements et le tri dans les collections
     */
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
