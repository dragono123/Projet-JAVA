package vue;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import data.Data;

public class PanelFormChrono extends JPanel{
	private JTextArea chIntitule = new JTextArea();
	private JTextArea chAnDebut = new JTextArea();
	private JTextArea chAnFin = new JTextArea();
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
			contrainte.gridwidth = 3;
			label[i] = new JLabel(Data.titreElementsChrono[i]);
			add(label[i], contrainte);
			contrainte.gridx = 3;
			contrainte.gridwidth = 8;
			if(Data.titreElementsChrono.equals("Intitulé"))
				add(chIntitule, contrainte);
			else if(Data.titreElementsChrono.equals("Nom de l'image"))
				add(chDossier, contrainte);
			
			contrainte.gridx = 0;
		}

		chAjout = new JButton("Ajouter une chronologie");
		chAjout.setFont(new Font("Carrier", Font.BOLD, 13));
		contrainte.gridwidth = 2;
		contrainte.gridy++;
		add(chAjout, contrainte);
	}
}
