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

import vue.PanelFormChrono;
import vue.PanelFormEvt;
import vue.PanelFormulaire;

public class Controleur implements ActionListener{
	PanelFormChrono chFormChrono;
	PanelFormEvt chFormEvt;
	Historique chHistorique;
	public Controleur(PanelFormulaire parFormulaire, Historique parHistorique)
	{
		chFormChrono = parFormulaire.getPanelChrono();
		chFormEvt = parFormulaire.getPanelEvt();
		chFormChrono.enregistreEcouteur(this);
		chFormEvt.enregistreEcouteur(this);
		chHistorique = parHistorique;
	}

	public void actionPerformed(ActionEvent parEvt) 
	{
		if(parEvt.getSource().equals(Data.commandAjoutChrono))
		{
			chHistorique.ajout(new Chronologie(chFormChrono.getAnDebut(), chFormChrono.getAnFin(), chFormChrono.getIntitule(), chFormChrono.getDossier()));
		}
		if(parEvt.getSource().equals(Data.commandAjoutEvt))
		{
			if(chHistorique.contientCle(chFormEvt.getChronologie()))
			{
				Chronologie chrono = chHistorique.getChronologie(chFormEvt.getChronologie());
				try {
					Date date = new Date(chFormEvt.getDate());
					Evt evt = new Evt(date, chFormEvt.getName(), chFormEvt.getDescription(), chFormEvt.getPoids(), chFormEvt.getFichier());
					chrono.ajout(evt);
				}
				catch (ExceptionDate e) {
				}
				catch (ExceptionEvt e) {
				} 
				catch (ExceptionChronologie e) {
				}
			}
		}
		
	}
	
	
	
}
