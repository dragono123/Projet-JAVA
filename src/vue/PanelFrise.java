package vue;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modele.Historique;

import data.Data;

public class PanelFrise extends JPanel implements ActionListener{

	private CardLayout layout = new CardLayout(5,5);
	public PanelFrise(JMenuItem[] parItems, Historique historique)
	{
		setLayout(layout);
		PanelAffichage affiche = new PanelAffichage(historique);
		PanelFormulaire formulaire = new PanelFormulaire();
		
		add(Data.items[1], affiche);
		add(Data.items[0], formulaire);
		layout.show(this, Data.items[1]);
		for(int i = 0; i < parItems.length; i++)
		{
			parItems[i].addActionListener(this);
			parItems[i].setActionCommand(Data.items[i]);
		}
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getActionCommand().equals(Data.items[0]))
			layout.show(this, evt.getActionCommand());
		if(evt.getActionCommand().equals(Data.items[1]))
			layout.show(this, evt.getActionCommand());

    	if(evt.getActionCommand() == Data.items[2])
    	{
    		int choix = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment continuer ?", "Fermer la fenêtre", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
    		if(choix == JOptionPane.OK_OPTION)
    			System.exit(0);
    	}
	}
	
	
}
