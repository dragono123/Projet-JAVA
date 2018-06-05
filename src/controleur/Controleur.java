package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import modele.Chronologie;
import modele.Date;
import modele.Evt;
import modele.ExceptionChronologie;
import modele.ExceptionDate;
import modele.ExceptionEvt;
import modele.Historique;

import data.Data;
import data.LectureEcriture;
import vue.PanelAffichage;
import vue.PanelEvt;
import vue.PanelFormChrono;
import vue.PanelFormEvt;
import vue.PanelFormulaire;
import vue.PanelListeDescription;
import vue.PanelListeEvts;

public class Controleur implements ActionListener{
	private PanelFormChrono chFormChrono;
	private PanelFormEvt chFormEvt;
	private PanelListeEvts chListeEvts;
	private Historique chHistorique;
	private PanelListeDescription chPanelDescription;
	private PanelEvt chPanelEvt;
	public Controleur(PanelFormulaire parFormulaire, PanelAffichage parAffiche, Historique parHistorique)
	{
		chFormChrono = parFormulaire.getPanelChrono();
		chFormEvt = parFormulaire.getPanelEvt();
		chFormChrono.enregistreEcouteur(this);
		chFormEvt.enregistreEcouteur(this);
		chHistorique = parHistorique;
		chListeEvts = parAffiche.getListeEvts();
		chPanelDescription = parAffiche.getPanelEvt().getPanelListeDesc();
		chPanelDescription.enregistreEcouteur(this);
		chPanelEvt = parAffiche.getPanelEvt();
	}
	
	public void actionPerformed(ActionEvent parEvt) 
	{
		if(parEvt.getActionCommand().equals(Data.commandAjoutChrono))
		{
			Chronologie chrono = new Chronologie(chFormChrono.getAnDebut(), 
					chFormChrono.getAnFin(), chFormChrono.getIntitule(), 
					chFormChrono.getDossier(), chFormChrono.getSaveFile());
			if(!chHistorique.contientValeur(chrono))
			{
				chHistorique.ajout(chrono);
				File fichier = new File(Data.saveFile + File.separator + chrono.getSave());
				if(fichier.exists())
					fichier.delete();
				try {
					fichier.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				LectureEcriture.ecriture(fichier, chrono);
				chListeEvts.resetModele();
				chPanelEvt.updateChronologie(chrono);
			}
		}
		if(parEvt.getActionCommand().equals(Data.commandAjoutEvt))
		{
			if(chHistorique.contientCle(chFormEvt.getChronologie()))
			{
				Chronologie chrono = chHistorique.getChronologie(chFormEvt.getChronologie());
				try {
					Date date = new Date(chFormEvt.getDate());
					Evt evt = new Evt(date, chFormEvt.getTitre(), chFormEvt.getDescription(), chFormEvt.getPoids(), chFormEvt.getFichier());
					chrono.ajout(evt);
					File file = new File(Data.saveFile + File.separator + chrono.getDossier());
					LectureEcriture.ecriture(file, chrono);
				}
				catch (ExceptionDate e) {
				}
				catch (ExceptionEvt e) {
				} 
				catch (ExceptionChronologie e) {
				}
			}
			chListeEvts.resetModele();
		}
		if(parEvt.getActionCommand().equals(Data.commandDiapoDroite))
		{
			chPanelDescription.afficherSuivant();
		}
		if(parEvt.getActionCommand().equals(Data.commandDiapoGauche))
		{
			chPanelDescription.afficherPrec();
		}
	}
	
}
