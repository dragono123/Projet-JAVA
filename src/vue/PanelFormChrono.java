package vue;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import data.Data;

public class PanelFormChrono extends JPanel{
	private JTextArea chIntitule = new JTextArea(1, 18);
	private JTextArea chAnDebut = new JTextArea(1, 4);
	private JTextArea chAnFin = new JTextArea(1, 4);
	private JTextArea chDossier = new JTextArea();
	private JButton chAjout = new JButton();
	public PanelFormChrono()
	{
		JLabel titrePanel = new JLabel("Formulaire de Chronologies");
		setLayout(new GridBagLayout());
		GridBagConstraints contrainte = new GridBagConstraints();
		JLabel[] label = new JLabel[Data.titreElementsChrono.length];
		
		
		add(titrePanel, contrainte);
		contrainte.gridx = 0;
		contrainte.gridy = 2;

		contrainte.gridheight = 1;
		contrainte.gridwidth = 2;

		contrainte.insets = new Insets(10, 10, 10, 10);
		contrainte.fill = GridBagConstraints.BOTH;
		
		contrainte.gridy = 0;
		add(titrePanel, contrainte);
		
		for(int i = 0; i < Data.titreElementsChrono.length; i++)
		{
			contrainte.gridy++;
			contrainte.gridwidth = 2;
			label[i] = new JLabel(Data.titreElementsChrono[i]);
			add(label[i], contrainte);
			contrainte.gridx = 3;
			contrainte.gridwidth = 8;
			if(Data.titreElementsChrono[i].equals("Intitulé"))
				add(new JScrollPane(chIntitule, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), contrainte);
			else if(Data.titreElementsChrono[i].equals("Nom de l'image"))
				add(new JScrollPane(chDossier, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), contrainte);
			else if(Data.titreElementsChrono[i].equals("Période"))
			{
				contrainte.gridwidth = 2;
				add(new JScrollPane(chAnDebut, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), contrainte);
				contrainte.gridwidth = 1;
				contrainte.gridx += 2;
				JLabel labelTire = new JLabel("-");
				add(labelTire, contrainte);
				contrainte.gridx++;
				contrainte.gridwidth = 2;
				add(new JScrollPane(chAnFin, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), contrainte);
				contrainte.gridwidth = 8;
			}
			contrainte.gridx = 0;
		}

		chAjout = new JButton("Ajouter une chronologie");
		chAjout.setFont(new Font("Carrier", Font.BOLD, 13));
		contrainte.gridwidth = 2;
		contrainte.gridy++;
		add(chAjout, contrainte);
	}
}
