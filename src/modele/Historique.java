package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 * Classe représentant l'historique(contenant tout les chronologies)
 * @author Vincent LIM Simon Ledoit
 * @version 1.0
 *
 */
public class Historique {
	TreeMap<String, Chronologie> mapChronologie;
	/**
	 * Constructeur de l'historique permettant d'initialiser la TreeMap mapChronologie
	 */
	public Historique()
	{
		mapChronologie = new TreeMap<String, Chronologie>();
	}
	/**
	 * Permet l'ajout d'une chronologie
	 * @param parChronologie correspond à la nouvelle chronologie
	 */
	public void ajout(Chronologie parChronologie)
	{
		mapChronologie.put(parChronologie.getNom(), parChronologie);
	}
	/**
	 * Permet de récuperer une chronologie à partir d'une clé(le nom de la chronologie)
	 * @param key est le nom de la chronologie et la clé permettant de récupérer cette chronologie
	 * @return la chronologie associée au nom
	 */
	public Chronologie getChronologie(String key)
	{
		return mapChronologie.get(key);
	}
	/**
	 * Permet de lister les noms des chronologies dans un tableau de String
	 * @return tableau de String contenant les noms des chronologies
	 */
	public String[] listeNomChrono()
	{
		String[] liste = new String[mapChronologie.size()];
		Set<String> keys = mapChronologie.keySet();
		int indice = 0;
		for(String key : keys)
			liste[indice++] = key;
		return liste;
	}
	/**
	 * Permet de vérifier si la collection contient une clé
	 * @param key nom de la chronologie
	 * @return false si elle n'est pas présente, true si elle l'est
	 */
	public boolean contientCle(String key)
	{
		return mapChronologie.containsKey(key);
	}
	/**
	 * Permet de vérifier si la collection contient une valeur
	 * @param valeur chronologie dont on teste la présence
	 * @return false si elle n'est pas présente, true si elle l'est
	 */
	public boolean contientValeur(Chronologie parChrono)
	{
		return mapChronologie.containsValue(parChrono);
	}
	/**
	 * Permet de vérifier si la collection est vide
	 * @return false si elle n'est pas vide, true si elle l'est
	 */
	public boolean estVide()
	{
		return mapChronologie.isEmpty();
	}
	
	/**
	 * Permet de récupérer une chronologie aléatoire
	 * @return chronologie aléatoire
	 */
	public Chronologie getRandomChronologie()
	{
		Random random = new Random();
		
		List<String> keys = new ArrayList<String>(mapChronologie.keySet());
		String randomKey = keys.get(random.nextInt(keys.size()));
		
		return mapChronologie.get(randomKey);
	}
}