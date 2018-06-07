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

import modele.Date;
import modele.ExceptionDate;

import controleur.Controleur;

import data.Data;

/**
 * 
 * Ce panel est le panel représentant le formulaire d'évènement
 * @author Vincent LIM Simon Ledoit
 * @version 1.0
 */
public class PanelFormEvt extends JPanel{
	private JTextArea[] chDate = new JTextArea[3];
	private JTextArea chFichier = new JTextArea();
	private JTextArea chDescription = new JTextArea(8, 18);
	private JTextArea chTitre = new JTextArea();
	private JTextArea chChronologie = new JTextArea();
	private JComboBox chPoids;
	private JButton chAjout;

	/**
	 * 
	 * Constructeur permettant d'instancier les différents élements du formulaire
	 */
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
			label[i].setDisplayedMnemonic(Data.titreElementsEvt[i].charAt(0));
			
			if(Data.titreElementsEvt[i].equals("Description"))
			{
				contrainte.gridheight = 3;
				chDescription.setLineWrap(true);
				add(new JScrollPane(chDescription, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), contrainte);
				label[i].setLabelFor(chDescription);
				contrainte.gridy += 2;
				contrainte.gridheight = 1;
			}
			else if(Data.titreElementsEvt[i].equals("Jour\\Mois\\An"))
			{
				contrainte.gridwidth = 1;
				for(int j = 0; j < 3; j++)
				{
					if(j == 2)
						chDate[j] = new JTextArea(1, 4);
					else
						chDate[j] = new JTextArea(1, 2);
					chDate[j].setLineWrap(true);
					add(new JScrollPane(chDate[j], ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), contrainte);
					contrainte.gridx++;
					if(j != 2)
					{
						dateSlash[j] = new JLabel("\\");
						add(dateSlash[j], contrainte);
						contrainte.gridx++;
					}
				}
				
				label[i].setLabelFor(chDate[0]);
			}
			else if(Data.titreElementsEvt[i].equals("Poids"))
			{
				add(new JScrollPane(chPoids, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), contrainte);
				label[i].setLabelFor(chPoids);
			}
			else
			{
				JTextArea textArea;
				if(Data.titreElementsEvt[i].equals("Titre"))
					textArea = chTitre;
				else if(Data.titreElementsEvt[i].equals("Nom de l'image"))
					textArea = chFichier;
				else
					textArea = chChronologie;
				
				textArea.setLineWrap(true);
				add(new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), contrainte);
				label[i].setLabelFor(textArea);
			}
			contrainte.gridx = 0;
		}
		
		chAjout = new JButton("Ajouter un évènement");
		chAjout.setFont(new Font("Carrier", Font.BOLD, 13));
		contrainte.gridwidth = 2;
		contrainte.gridy++;
		add(chAjout, contrainte);
	}

	/**
	 * Permet au controleur de se mettre à l'écoute du JButton
	 * @param parControleur correspond au controleur que l'on souhaite enregistrer
	 */
	public void enregistreEcouteur(Controleur parControleur)
	{
		chAjout.addActionListener(parControleur);
		chAjout.setActionCommand(Data.commandAjoutEvt);
	}
	/**
	 * Permet de récupérer le texte inséré dans le champs "Nom de l'image"
	 * @return le texte ajouté par l'utilisateur dans le champs "Nom de l'image"
	 */
	public String getFichier()
	{
		return chFichier.getText();
	}
	/**
	 * Permet de récupérer le texte inséré dans le champs "Description"
	 * @return le texte ajouté par l'utilisateur dans le champs "Description"
	 */
	public String getDescription()
	{
		return chDescription.getText();
	}

	/**
	 * Permet de récupérer la valeur du poids choisi dans le JComboBox désigné par le label "Poids"
	 * @return la valeur du poids choisi dans le JComboBox désigné par le label "Poids"
	 */
	public Integer getPoids()
	{
		return (Integer) chPoids.getSelectedItem();
	}
	/**
	 * Permet de récupérer le texte inséré dans le champs "Titre"
	 * @return le texte ajouté par l'utilisateur dans le champs "Titre"
	 */
	public String getTitre()
	{
		return chTitre.getText();
	}

	/**
	 * Permet de récupérer la date insérée dans le champs "Jour\Mois\An"
	 * @return la date insérée par l'utilisateur dans le champs "Jour\Mois\An"
	 * @throws ExceptionDate Gère les erreurs au moment de la récupération de la date
	 */
	public Date getDate() throws ExceptionDate
	{
		Date date = new Date(Integer.parseInt(chDate[0].getText()), 
				Integer.parseInt(chDate[1].getText()),
				Integer.parseInt(chDate[2].getText()));
		return date;
	}
	/**
	 * Permet de récupérer le texte inséré dans le champs "Chronologie"
	 * @return le texte ajouté par l'utilisateur dans le champs "Chronologie"
	 */
	public String getChronologie()
	{
		return chChronologie.getText();
	}
}
