package vue;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JFrame;

public class FenetreFrise extends JFrame {
	public FenetreFrise()
	{
		super("Frise chronologique");
		PanelFrise contentPane = new PanelFrise();
		setContentPane(contentPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,600);
		setVisible(true);
		setLocation(200,300);
	}
	public Insets getInsets()
	{
		return new Insets(8, 4, 4, 4);
	}
	public static void main(String args[])
	{
		new FenetreFrise();
	}
}
