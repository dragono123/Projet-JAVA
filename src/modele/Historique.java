package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class Historique {
	TreeMap<String, Chronologie> mapChronologie;
	public Historique()
	{
		mapChronologie = new TreeMap<String, Chronologie>();
	}
	public void ajout(Chronologie parChronologie)
	{
		mapChronologie.put(parChronologie.getNom(), parChronologie);
	}
	public Chronologie getChronologie(String key)
	{
		return mapChronologie.get(key);
	}
	public boolean contientCle(String key)
	{
		return mapChronologie.containsKey(key);
	}
	public boolean contientValeur(Chronologie parChrono)
	{
		return mapChronologie.containsValue(parChrono);
	}
	public boolean estVide()
	{
		return mapChronologie.isEmpty();
	}
	public Chronologie getRandomChronologie()
	{
		Random random = new Random();
		
		List<String> keys = new ArrayList<String>(mapChronologie.keySet());
		String randomKey = keys.get(random.nextInt(keys.size()));
		
		return mapChronologie.get(randomKey);
	}
}