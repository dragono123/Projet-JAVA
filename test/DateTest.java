import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;


public class DateTest {

	@Test
	public void testDateIntIntInt() throws ExceptionDate {
		Date d1 = new Date(20, 10, 2005);
		assertEquals("accès jour", 20, d1.getJour());
		assertEquals("accès mois", 10, d1.getMois());
		assertEquals("accès année", 2005, d1.getAn());
	}

	@Test
	public void testDate() {
		GregorianCalendar aujourdhui = new GregorianCalendar();
		
        chJour = aujourdhui.get(Calendar.DAY_OF_MONTH);
        chMois = aujourdhui.get(Calendar.MONTH) + 1;
        chAn = aujourdhui.get(Calendar.YEAR);
		Date d1 = new Date();
		assertEquals("accès jour", chJour, d1.getJour());
		assertEquals("accès mois", chMois, d1.getJour());
		assertEquals("accès année", chAn, d1.getJour());
		
	}

	@Test
	public void testDateDate() {
		Date d1 = new Date(20, 10, 2005);
		Date d2 = new Date(d1);
		assertEquals("accès jour", d2.getJour(), d1.getJour());
		assertEquals("accès mois", d2.getJour(), d1.getJour());
		assertEquals("accès année", d2.getJour(), d1.getJour());
	}
	
	@Test
	public void testToString() throws ExceptionDate {
		Date d = new Date(19, 02, 2018);
		System.out.println(d.toString());
		String resultat = "19:02:2018";
		assertEquals(0, resultat.compareTo(d.toString()));
	}
	
	@Test
	public void testDernierJour() throws ExceptionDate {
		Date d1 = new Date(1,1,2001);
		assertEquals("fin janvier", 31, d1.dernierJour(d1.getMois(), d1.getAn()));
		
		Date d2 = new Date(1,4,2001);
		assertEquals("fin avril", 30, d2.dernierJour(d1.getMois(), d1.getAn()));
		
		Date d3 = new Date(1,2,2001);
		assertEquals("fev annee impaire", 28, d3.dernierJour(d1.getMois(), d1.getAn()));
		
		Date d4 = new Date(1,2,2002);
		assertEquals("fev annee paire non div par 4", 28, d4.dernierJour(d1.getMois(), d1.getAn()));
		
		Date d5 = new Date(1,2,2004);
		assertEquals("fev annee paire div par 4 pas par 100", 29, d5.dernierJour(d1.getMois(), d1.getAn()));
		
		Date d6 = new Date(1,2,2100);
		assertEquals("fev annee paire div par 100 pas par 400", 28, d6.dernierJour(d1.getMois(), d1.getAn()));
		Date d7 = new Date(1,2,2000);
		assertEquals("fev annee paire div par 400", 29, d7.dernierJour(d1.getMois(), d1.getAn()));
	}
	
	@Test
	public void testCompareTo() throws ExceptionDate {
		Date d1 = new Date(5, 5, 2005);
		Date d1bis = new Date(5, 5, 2005);
		
		assertEquals(0, d1.compareTo(d1bis));
		
		Date d2 = new Date(4, 5, 2005);
		assertEquals(true, d2.compareTo(d1bis) < 0);
		
		Date d3 = new Date(6, 5, 2005);
		assertEquals(true, d3.compareTo(d1bis) > 0);
		
		Date d4 = new Date(5, 4, 2005);
		assertEquals(true, d4.compareTo(d1bis) < 0);
		
		Date d5 = new Date(5, 5, 2004);
		assertEquals(true, d5.compareTo(d1bis) < 0);
		
		Date d6 = new Date(5, 6, 2005);
		assertEquals(true, d6.compareTo(d1bis) > 0);
		
		Date d7 = new Date(5, 5, 2006);
		assertEquals(true, d7.compareTo(d1bis) > 0);
	}
}
