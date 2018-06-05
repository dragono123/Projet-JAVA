package modele;

import java.util.Set;
import java.util.TreeMap;

import javax.swing.table.DefaultTableModel;

public class ModeleTable extends DefaultTableModel{
	Chronologie chChronologie;
	public ModeleTable(Chronologie parChronologie)
	{
		chChronologie = parChronologie;
		afficherFrise();
	}
	public void afficherFrise()
	{
		setRowCount(0);
		setRowCount(4);
		if(chChronologie != null)
		{
			int anDebut = chChronologie.getAnDebut();
			int anFin = chChronologie.getAnFin();
			int nbColonne = anFin - anDebut + 1;
			setColumnCount(nbColonne);
			String entete[] = new String[nbColonne];
			for(int i = 0; i < nbColonne; i++)
			{
				if(i%5 == 0)
					entete[i] = Integer.toString(i+anDebut);
				else
					entete[i] = "";
			}
			setColumnIdentifiers(entete);
			TreeMap<Integer, TreeMap<Integer, Evt>> liste = chChronologie.getEvtListe();
			Set<Integer> keysAn = liste.keySet();
			for(Integer keyAn : keysAn)
			{
				TreeMap<Integer, Evt> listeEvtAn = liste.get(keyAn);
				Set<Integer> keysPoids = listeEvtAn.keySet();
				for(Integer keyPoids : keysPoids)
				{
					this.setValueAt(listeEvtAn.get(keyPoids), keyPoids - 1, keyAn - anDebut);
				}
			}
		}
		else
		{
			int nbColonne = 20;
			setColumnCount(nbColonne);
			String entete[] = new String[nbColonne];
			for(int i = 0; i < nbColonne; i++)
			{
				if(i%5 == 0)
					entete[i] = Integer.toString(i);
				else
					entete[i] = "";
			}
			setColumnIdentifiers(entete);
		}
	}
	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
}