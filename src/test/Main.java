package test;

import java.util.ArrayList;
import java.util.Calendar;

import jpa.Event;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
	private static Session s = null;

	public static void main(String[] args) {
		// Ouverture d'une session Hibernate
		s = HibernateUtils.getSession();

		// Lancement des tests successifs
		print();
		clean();

		// Fermeture de la session Hibernate
		s.close();
	}

	// Création d'un event
	private static Event testCreate(String title, String description,
			Calendar beginDate, boolean allDay) {
		Event e = new Event();
		e.setTitle(title);
		e.setDescription(description);
		e.setBeginDate(beginDate);
		e.setAllDay(allDay);
		return e;

	}

	// Lecture d'un event
	private static void testRead(String title) {

	}

	// Modification d'un event
	private static void testUpdate() {
	}

	// Suppression d'un event
	private static void testDelete() {
	}

	// Vide la table EVENTS de toutes ses entrées
	private static void clean() {
		System.out
				.println("Supression de la table associé à Event ...........");
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("delete Event");
		q.executeUpdate();
		t.commit();
		System.out.println("Supression de la table associé à Event : [OK]");

	}

	// Affiche le contenu de la table EVENTS
	private static void print() {
		System.out.println("Lecture de la table associé à Event ...........");
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("from Event");
		ArrayList<Event> list = (ArrayList<Event>) q.list();
		for (Event e : list) {
			System.out.println("Event : [id] = " + e.getId() + "\t"
					+ "[title] = " + e.getTitle() + "\t" + "[desc] = "
					+ e.getDescription() + "\n" + "[date] = "
					+ e.getBeginDate().getTime().toLocaleString() + "\t"
					+ "[allDay] = " + e.isAllDay());
		}
		t.commit();
		System.out.println("Lecture de la table associé à Event : [OK]");
	}
}