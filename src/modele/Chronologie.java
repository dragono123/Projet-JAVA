package modele;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Chronologie {

	private TreeMap <Integer, TreeMap<Integer, Evt>>chTreeMap;
	private int chAnDebut;
	private int chAnFin;
	private String chNom;
	private String chDossier;
	public Chronologie(int parAnDebut, int parAnFin, String parNom, String parDossier)
	{
		chTreeMap = new TreeMap<Integer, TreeMap<Integer, Evt>>();
		chAnDebut = parAnDebut;
		chAnFin = parAnFin;
		chNom = parNom;
		chDossier = parDossier;
	}
	
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
	public String getNom()
	{
		return chNom;
	}
	public void setNom(String parNom)
	{
		chNom = parNom;
	}
	
	public int getAnDebut()
	{
		return chAnDebut;
	}
	public int getAnFin()
	{
		return chAnFin;
	}
	public String getDossier()
	{
		return chDossier;
	}
	public boolean containsKey(Integer key)
	{
		return chTreeMap.containsKey(key);
	}
	public TreeMap<Integer, Evt> getEvtAn(Integer an)
	{
		TreeMap<Integer, Evt> evt = chTreeMap.get(an);
		return evt;
	}
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
}