package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Classe permettant la lecture/écriture d'un fichier
 * @author Vincent LIM Simon Ledoit
 * @version 1.0
 *
 */
public class LectureEcriture {
	/**
	 * Cette fonction permet la lecture d'un fichier
	 * @param parFichier qu'on lit
	 * @return Objet récupérer à partir du fichier111
	 * @throws IOException
	 */
	public static Object lecture (File parFichier) throws  IOException {
		ObjectInputStream flux ;
		Object objetLu = null;
		// Ouverture du fichier en mode lecture
		try 
		{
			flux = new ObjectInputStream(new FileInputStream (parFichier));
			objetLu = (Object)flux.readObject ();
			flux.close ();
		}catch (ClassNotFoundException parException) {
			System.err.println (parException.toString ());
			System.exit (1);
		}
	return objetLu ;
	}  // lecture ()

	/**
	 * Cette fonction permet l'écriture d'un fichier
	 * @param parFichier représente le fichier qui va être réécrit
	 * @param parObjet représente l'objet que l'on souhaite "insérer" dans le fichier
	 */
	public static void ecriture (File parFichier, Object parObjet)  {
		ObjectOutputStream flux = null;
		// Ouverture du fichier en mode écriture
		try {
			flux = new ObjectOutputStream (new FileOutputStream (parFichier));
			flux.writeObject (parObjet);
			flux.flush ();
			flux.close ();
		}
			catch (IOException parException)  {
			System.err.println ("Probleme a l’ecriture\n" + parException.toString ());
			System.exit (1);
		}
	} //  ecriture ()
}