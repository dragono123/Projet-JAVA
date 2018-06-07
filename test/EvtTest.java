import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;


import modele.Evt;

import org.junit.Test;


public class DateTest {

	@Test
	public void testEvtDateStringStringIntString() throws ExceptionEvt {
		Date d1 = new Date(20, 10, 2005);
		Evt e1 = new Evt(d1, "nom", "description", 1, "fichier");

		assertEquals("accès date", 0, d1.compareTo(e1.getDate()));
		assertEquals("accès nom", 0, e1.getNom().compareTo("nom"));
		assertEquals("accès description", 0, e1.getDescription().compareTo("description"));
		assertEquals("accès fichier", 0, e1.getFichier().compareTo("fichier"));
		assertEquals("accès poids", 1, e1.getPoids());
	}
}
