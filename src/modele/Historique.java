package modele;

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
}