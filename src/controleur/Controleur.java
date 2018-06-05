package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Chronologie;
import modele.Date;
import modele.Evt;
import modele.ExceptionChronologie;
import modele.ExceptionDate;
import modele.ExceptionEvt;
import modele.Historique;

import data.Data;

import vue.PanelAffichage;
import vue.PanelFormChrono;
import vue.PanelFormEvt;
import vue.PanelFormulaire;
import vue.PanelListeDescription;
import vue.PanelListeEvts;

public class Controleur implements ActionListener{
	PanelFormChrono chFormChrono;
	PanelFormEvt chFormEvt;
	PanelListeEvts chListeEvts;
	Historique chHistorique;
	PanelListeDescription chPanelDescription;
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
	}

	public void actionPerformed(ActionEvent parEvt) 
	{
		if(parEvt.getActionCommand().equals(Data.commandAjoutChrono))
		{
			chHistorique.ajout(new Chronologie(chFormChrono.getAnDebut(), chFormChrono.getAnFin(), chFormChrono.getIntitule(), chFormChrono.getDossier()));
			chListeEvts.resetModele();
		}
		if(parEvt.getActionCommand().equals(Data.commandAjoutEvt))
		{
			if(chHistorique.contientCle(chFormEvt.getChronologie()))
			{
				Chronologie chrono = chHistorique.getChronologie(chFormEvt.getChronologie());
				try {
					Date date = new Date(chFormEvt.getDate());
					Evt evt = new Evt(date, chFormEvt.getName(), chFormEvt.getDescription(), chFormEvt.getPoids(), chFormEvt.getFichier());
					chrono.ajout(evt);
					chPanelDescription.ajoutDiapo(evt);
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
