package modele;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeMap;

/**
 * Cette classe représente la chronologie contenant la liste des évènements
 * @author Vincent LIM Simon Ledoit
 * @version 1.0
 *
 */
public class Chronologie implements Serializable{

	private TreeMap <Integer, TreeMap<Integer, Evt>>chTreeMap;
	private int chAnDebut;
	private int chAnFin;
	private String chNom;
	private String chDossier;
	private String chSauvegarde;
	/**
	 * Constructeur de la chronologie
	 * @param parAnDebut représente l'année de début de la chronologie
	 * @param parAnFin représente l'année de fin
	 * @param parNom représente le nom de la chronologie
	 * @param parDossier représente le dossier où se trouve lees images
	 * @param parSauvegarde représente le nom du fichier de sauvegarde
	 */
	public Chronologie(int parAnDebut, int parAnFin, String parNom, String parDossier, String parSauvegarde)
	{
		chTreeMap = new TreeMap<Integer, TreeMap<Integer, Evt>>();
		chAnDebut = parAnDebut;
		chAnFin = parAnFin;
		chNom = parNom;
		chDossier = parDossier;
		chSauvegarde = parSauvegarde;
	}
	/**
	 * Permet l'ajout d'un Evt dans la chronologie
	 * @param parEvt
	 * @throws ExceptionChronologie
	 */
	public void ajout(Evt parEvt) throws ExceptionChronologie
	{
		int an = parEvt.getDate().getAn();
		
		if(an < chAnDebut)
			throw new ExceptionChronologie("Année de l'Evt inférieur à l'année de début de la chronologie");
		if(an > chAnFin)
			throw new ExceptionChronologie("Année de l'Evt supérieur à l'année de fin de la chronologie");
		
		if(chTreeMap.containsKey(an))
		{
			TreeMap<Integer, Evt> listeEvtAn = chTreeMap.get(an);
			if(listeEvtAn.containsKey(parEvt.getPoids()))
				throw new ExceptionChronologie("Evt de poids équivalent déjà existant !");
			else
				listeEvtAn.put(parEvt.getPoids(), parEvt);
		}
		else
		{
			TreeMap<Integer, Evt> nouveauTreeMap = new TreeMap<Integer, Evt>();
			nouveauTreeMap.put(parEvt.getPoids(), parEvt);
			chTreeMap.put(an, nouveauTreeMap);
		}
	}
	/**
	 * Getter permettant de récupérer le nom de la chronologie
	 * @return chNom qui représente le nom de la chronologie
	 */
	public String getNom()
	{
		return chNom;
	}

	/**
	 * Getter permettant de récupérer l'année de début de la chronologie
	 * @return chAnDébut qui représente l'année de début de la chronologie
	 */
	public int getAnDebut()
	{
		return chAnDebut;
	}

	/**
	 * Getter permettant de récupérer l'année de fin de de la chronologie
	 * @return chAnFin qui représente l'année de fin de la chronologie
	 */
	public int getAnFin()
	{
		return chAnFin;
	}

	/**
	 * Getter permettant de récupérer le dossier d'image de la chronologie
	 * @return chDossier qui représente le dossier d'image de la chronologie
	 */
	public String getDossier()
	{
		return chDossier;
	}

	/**
	 * Getter permettant de récupérer la liste d'Evt de la chronologie
	 * @return chTreeMap qui représente la liste d'Evt de la chronologie (sous la forme : TreeMap<Integer, TreeMap<Integer, Evt>>)
	 */
	public TreeMap<Integer, TreeMap<Integer, Evt>> getEvtListe()
	{
		return chTreeMap;
	}
	/**
	 * Permet l'affichage de la chronologie
	 */
	public String toString(){
		String chaine = new String();
		Set<Integer> keysAn = chTreeMap.keySet();
		
		for (Integer keyAn : keysAn)
		{
			TreeMap<Integer, Evt> listeEvtAn = chTreeMap.get(keyAn);
			Set<Integer> keysPoids = listeEvtAn.keySet();
			chaine += keyAn + " :\n";
			for(Integer keyPoids : keysPoids)
				chaine += listeEvtAn.get(keyPoids);
			chaine += "\n";
		}
		return chaine;
	}

	/**
	 * Getter permettant de récupérer le fichier de sauvegarde de la chronologie
	 * @return chSauvegarde qui représente le fichier de sauvegarde de la chronologie
	 */
	public String getSave()
	{
		return chSauvegarde;
	}
}