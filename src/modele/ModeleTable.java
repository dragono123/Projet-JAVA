package modele;

import java.util.Set;
import java.util.TreeMap;

import javax.swing.table.DefaultTableModel;

/**
 * Classe représentant le modèle de la frise
 * @author Vincent LIM Simon Ledoit
 * @version 1.0
 *
 */
public class ModeleTable extends DefaultTableModel{
	Chronologie chChronologie;
	/**
	 * Constructeur du modele
	 * @param parChronologie correspond à la chronologie courante
	 */
	public ModeleTable(Chronologie parChronologie)
	{
		updateChronologie(parChronologie);
	}
	/**
	 * Permet l'affichage de la frise à partir de la chronologie courante
	 */
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
	/**
	 * 
	 * Permet de changer la chronologie courante
	 * @param parChronologie correspond à la nouvelle chronologie courante
	 */
	public void updateChronologie(Chronologie parChronologie)
	{
		chChronologie = parChronologie;
		afficherFrise();
	}
	/**
	 * Permet d'interdir l'écriture dans les cellules
	 */
	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
}