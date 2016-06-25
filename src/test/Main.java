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
		clean();
		testCreate();
		testRead();
		testUpdate();
		testDelete();

		// Fermeture de la session Hibernate
		s.close();
	}

	// Création d'un event
	private static void testCreate() {

		System.out.println("--------- testCreate ---------");

		// Création des objets à sauvegarder
		Event e1 = new Event();
		e1.setTitle("Titre du premier évènement");
		e1.setDescription("Description 1");
		e1.setBeginDate(Calendar.getInstance());
		e1.setAllDay(true);

		Event e2 = new Event();
		e2.setTitle("Deuxième évènement");
		e2.setDescription("Description du deuxième évènement");
		e2.setBeginDate(Calendar.getInstance());
		e2.setAllDay(false);

		// Début de la transaction
		Transaction tx = s.beginTransaction();
		// Sauvegarde des objets
		s.save(e1);
		s.save(e2);
		// Fermeture de la transaction
		tx.commit();
		// Affiche le contenu de la table EVENTS
		print();
	}

	// Lecture d'un event
	private static void testRead() {
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("from Event where title LIKE ?");
		q.setParameter(0, "Titre du premier évènement");
		Event e = (Event) q.uniqueResult();
		printEvent(e);
		t.commit();
	}

	// Modification d'un event
	private static void testUpdate() {
		 System.out.println("--------- testUpdate ---------");
	      System.out.println("Avant modification");
	      print();
	 
	      // Récupération de l'Event d'après son titre
	      Query q = s.createQuery("from Event where title= :myTitle");
	      q.setString("myTitle", "Titre du premier évènement");
	      Event e = (Event) q.uniqueResult();
	 
	      // Modifications des attributs de l'objet
	      e.setDescription("Description modifiée");
	      e.setAllDay(false);
	 
	      // Prise en compte de la modification
	      Transaction tx = s.beginTransaction();
	      s.update(e);
	      tx.commit();
	 
	      System.out.println("Après modification");
	      print();
	}

	// Suppression d'un event
	private static void testDelete() {
		System.out.println("--------- testDelete ---------");
	      System.out.println("Avant suppression");
	      print();
	 
	      // Récupération de l'Event d'après son titre
	      Query q = s.createQuery("from Event where title= :myTitle");
	      q.setString("myTitle", "Titre du premier évènement");
	      Event e = (Event) q.uniqueResult();
	 
	      // Enregistrement de la suppression
	      Transaction tx = s.beginTransaction();
	      s.delete(e);
	      tx.commit();
	 
	      System.out.println("Après suppression");
	      print();
	}

	// Vide la table EVENTS de toutes ses entrées
	private static void clean() {
		System.out.println("Supression de la table associé à Event ...........");
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
			printEvent(e);
		}
		t.commit();
		System.out.println("Lecture de la table associé à Event : [OK] ");
	}

	private static void printEvent(Event e) {

		System.out.println("Event : [id] = " + e.getId() + "\t" + "[title] = "
				+ e.getTitle() + "\t" + "[desc] = " + e.getDescription() + "\n"
				+ "[date] = " + e.getBeginDate().getTime().toLocaleString()
				+ "\t" + "[allDay] = " + e.isAllDay());
	}
}