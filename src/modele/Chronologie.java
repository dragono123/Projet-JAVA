package modele;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Chronologie {

	private TreeMap <Integer, TreeSet<Evt>>chTreeMap;

	public Chronologie()
	{
		chTreeMap = new TreeMap<Integer, TreeSet<Evt>>();
	}
	
	public void ajout(Evt parEvt)
	{
		int key = parEvt.getDate().getAn();
		if(chTreeMap.containsKey(key))
			chTreeMap.get(key).add(parEvt);
		else
		{
			TreeSet <Evt>newSet = new TreeSet<Evt>();
			chTreeMap.put(parEvt.getDate().getNoSemaine(), newSet);
		}
	}
	public boolean containsKey(Integer key)
	{
		return chTreeMap.containsKey(key);
	}
	public TreeSet<Evt> getEvtList(Integer key)
	{
		TreeSet<Evt> evt = chTreeMap.get(key);
		return evt;
	}
	public String toString(){
		String chaine = new String();
		Set<Integer> keys = chTreeMap.keySet();
		
		for (Integer key : keys)
		{
			chaine += "Semaine " + key + ":\n";
			Iterator <Evt>ite = chTreeMap.get(key).iterator();
			if(!ite.hasNext())
			{
				chaine += "Pas d'éléments \n";
			}
			while(ite.hasNext())
			{
				chaine += "-" + ite.next().toString() + "\n";
			}
		}
		return chaine;
	}
}