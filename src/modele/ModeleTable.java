package modele;

import javax.swing.table.DefaultTableModel;

public class ModeleTable extends DefaultTableModel{
	public ModeleTable()
	{
		int nbColumn = 40;
		setColumnCount(nbColumn);
		setRowCount(5);
	}
}
