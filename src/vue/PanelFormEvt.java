package vue;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import data.Data;

public class PanelFormEvt extends JPanel{
	private JTextArea[] chDate = new JTextArea[3];
	private JTextArea chAdresse = new JTextArea();
	private JTextArea chDescription = new JTextArea(8, 18);
	private JTextArea chTitre = new JTextArea();
	private JTextArea chChronologie = new JTextArea();
	private JComboBox chPoids;
	private JButton chAjout;
	public PanelFormEvt()
	{
		JLabel titrePanel = new JLabel("Formulaire d'Evènements");
		setLayout(new GridBagLayout());
		GridBagConstraints contrainte = new GridBagConstraints();
		JLabel[] dateSlash = new JLabel[2];
		JLabel[] label = new JLabel[Data.titreElementsEvt.length];
		chPoids = new JComboBox(Data.poids);
		
		add(titrePanel, contrainte);
		contrainte.gridx = 0;
		contrainte.gridy = 2;

		contrainte.gridheight = 1;
		contrainte.gridwidth = 2;

		contrainte.insets = new Insets(10, 10, 10, 10);
		contrainte.fill = GridBagConstraints.BOTH;
		
		contrainte.gridy = 0;
		add(titrePanel, contrainte);
		
		for(int i = 0; i < Data.titreElementsEvt.length; i++)
		{
			contrainte.gridy++;
			contrainte.gridwidth = 3;
			label[i] = new JLabel(Data.titreElementsEvt[i]);
			add(label[i], contrainte);
			contrainte.gridx = 3;
			contrainte.gridwidth = 8;
			if(Data.titreElementsEvt[i].equals("Description"))
			{
				contrainte.gridheight = 3;
				add(new JScrollPane(chDescription, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), contrainte);
				contrainte.gridy += 2;
				contrainte.gridheight = 1;
			}
			else if(Data.titreElementsEvt[i].equals("Titre"))
				add(new JScrollPane(chTitre, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), contrainte);
			else if(Data.titreElementsEvt[i].equals("Nom de l'image"))
				add(new JScrollPane(chAdresse, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), contrainte);
			else if(Data.titreElementsEvt[i].equals("Poids"))
				add(new JScrollPane(chPoids, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), contrainte);
			else if(Data.titreElementsEvt[i].equals("Date"))
			{
				contrainte.gridwidth = 1;
				for(int j = 0; j < 3; j++)
				{
					if(j == 2)
						chDate[j] = new JTextArea(1, 4);
					else
						chDate[j] = new JTextArea(1, 2);

					add(new JScrollPane(chDate[j], ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), contrainte);
					contrainte.gridx++;
					if(j != 2)
					{
						dateSlash[j] = new JLabel("\\");
						add(dateSlash[j], contrainte);
						contrainte.gridx++;
					}
				}
			}
			else
				add(new JScrollPane(chChronologie, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), contrainte);
			contrainte.gridx = 0;
		}
		
		chAjout = new JButton("Ajouter un évènement");
		chAjout.setFont(new Font("Carrier", Font.BOLD, 13));
		contrainte.gridwidth = 2;
		contrainte.gridy++;
		add(chAjout, contrainte);
	}
}
