package test;

import java.util.GregorianCalendar;

import jpa.Event;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class InitDB {
    public static void main(String[] args){
    	//Récupération d'une session hibernate
    	Session session = HibernateUtils.getSession();
    	//Début de la transaction
    	Transaction transaction = session.beginTransaction();
    	//Création d'un objet Event 	
    	Event e = new Event();
    	e.setTitle("Titre de l'event 1");
    	e.setDescription("Pour le lancement du nouveau single");
    	e.setBeginDate(new GregorianCalendar());
    	e.setAllDay(false);
    	//Enregistrement de l'event
    	session.save(e);
    	//Fin de la transaction
    	transaction.commit();
    	//fermerture de la session hibernate 
    	
    }
}
