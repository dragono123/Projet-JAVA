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

import controleur.Controleur;

import data.Data;
/**
 * 
 * Ce panel est le panel représentant le formulaire de chronologie
 * @author Vincent LIM Simon Ledoit
 * @version 1.0
 */
public class PanelFormChrono extends JPanel{
	private JTextArea chIntitule = new JTextArea(1, 18);
	private JTextArea chAnDebut = new JTextArea(1, 4);
	private JTextArea chAnFin = new JTextArea(1, 4);
	private JTextArea chDossier = new JTextArea();
	private JTextArea chFichierSave = new JTextArea();
	private JButton chAjout = new JButton();
	/**
	 * 
	 * Constructeur permettant d'instancier les différents élements du formulaire
	 */
	public PanelFormChrono()
	{
		JLabel titrePanel = new JLabel("Formulaire de Chronologies");
		setLayout(new GridBagLayout()); // On instancie le grid bag layout
		GridBagConstraints contrainte = new GridBagConstraints(); // On créer la contrainte
		JLabel[] label = new JLabel[Data.titreElementsChrono.length];
		
		
		add(titrePanel, contrainte);
		contrainte.gridx = 0;
		contrainte.gridy = 2;

		contrainte.gridheight = 1;
		contrainte.gridwidth = 2;

		contrainte.insets = new Insets(10, 10, 10, 10);
		contrainte.fill = GridBagConstraints.BOTH;
		
		contrainte.gridy = 0;
		// Ajout du titre
		add(titrePanel, contrainte);
		// Instanciation et ajout
		for(int i = 0; i < Data.titreElementsChrono.length; i++)
		{
			contrainte.gridy++;
			contrainte.gridwidth = 2;
			label[i] = new JLabel(Data.titreElementsChrono[i]);
			add(label[i], contrainte);
			contrainte.gridx = 3;
			contrainte.gridwidth = 8;
			label[i].setDisplayedMnemonic(Data.titreElementsChrono[i].charAt(0));
			if(Data.titreElementsChrono[i].equals("Ère concernée"))
			{
				contrainte.gridwidth = 2;
				chAnDebut.setLineWrap(true); // Permet le retour à la ligne
				chAnFin.setLineWrap(true);
				add(new JScrollPane(chAnDebut, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), contrainte); // Ajoute un JScrollPane
				label[i].setLabelFor(chAnDebut);
				contrainte.gridwidth = 1;
				contrainte.gridx += 2;
				JLabel labelTire = new JLabel("-");
				add(labelTire, contrainte);
				contrainte.gridx++;
				contrainte.gridwidth = 2;
				add(new JScrollPane(chAnFin, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), contrainte);
				contrainte.gridwidth = 8;
			}
			else
			{
				JTextArea textArea;
				if(Data.titreElementsChrono[i].equals("Intitulé"))
					textArea = chIntitule;
				else if(Data.titreElementsChrono[i].equals("Emplacement images"))
					textArea = chDossier;
				else
					textArea = chFichierSave;
				
				textArea.setLineWrap(true);
				add(new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), contrainte);
				label[i].setLabelFor(textArea);
			}
			contrainte.gridx = 0;
		}
		// Ajout du bouton
		chAjout = new JButton("Ajouter une chronologie");
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
		chAjout.setActionCommand(Data.commandAjoutChrono);
	}
	/**
	 * Permet de récupérer le texte inséré dans le champs Intitulé
	 * @return le texte ajouté par l'utilisateur dans le champs Intitulé
	 */
	public String getIntitule()
	{
		return chIntitule.getText();
	}

	/**
	 * Permet de récupérer le texte inséré dans le champs "Emplacement Images"
	 * @return le texte ajouté par l'utilisateur dans le champs "Emplacement Images"
	 */
	public String getDossier()
	{
		return chDossier.getText();
	}

	/**
	 * Permet de récupérer l'année de début insérée par l'utilisateur dans le champs "Ère concernée"
	 * @return l'année de début ajoutée par l'utilisateur dans le champs "Ère concernée"
	 */
	public Integer getAnDebut()
	{
		return Integer.parseInt(chAnDebut.getText());
	}

	/**
	 * Permet de récupérer l'année de fin insérée par l'utilisateur dans le champs "Ère concernée"
	 * @return l'année de fin ajoutée par l'utilisateur dans le champs "Ère concernée"
	 */
	public Integer getAnFin()
	{
		return Integer.parseInt(chAnFin.getText());
	}

	/**
	 * Permet de récupérer le texte inséré dans le champs "Adresse fichier sauvegarde"
	 * @return le texte ajouté par l'utilisateur dans le champs "Adresse fichier sauvegarde"
	 */
	public String getSaveFile()
	{
		return chFichierSave.getText();
	}
}
