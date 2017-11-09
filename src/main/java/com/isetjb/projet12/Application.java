/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isetjb.projet12;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
/**
 *
 * @author MALEK
 */
public class Application {
     /**
     * Attribute declaration for factory to share between methods.
     */
    private static SessionFactory factory;

    public static void main(String[] args)
    {
        System.out.println("JavaSE + Maven + Hibernate + MySQL : Many to One Association");

        // Open connection  pool
        factory = HibernateUtil.getSessionFactory();

        Session session = factory.openSession();
        Transaction transaction = null;

        try
        {
            transaction = session.beginTransaction();

            // new Marque
            Marque marque_a = new Marque();
            marque_a.setNom("marque a");
            marque_a.setConstr("constructor a");
            session.save(marque_a);

            // new Marque
            Marque marque_b = new Marque();
            marque_b.setNom("marque b");
            marque_b.setConstr("constructor b");
            session.save(marque_b);

            // new Voiture
            Voiture voiture_a = new Voiture();
            voiture_a.setMat("Matricule a");
            voiture_a.setCol("Couleure a");
            voiture_a.setMarque(marque_a);
            session.save(voiture_a);

            // new Voiture
            Voiture voiture_b = new Voiture();
            voiture_b.setMat("Matricule b");
            voiture_b.setCol("Couleure b");
            voiture_b.setMarque(marque_b);
            session.save(voiture_b);

            // new Voiture
            Voiture voiture_c = new Voiture();
            voiture_c.setMat("Matricule c");
            voiture_c.setCol("Couleure c");
            voiture_c.setMarque(marque_b);
            session.save(voiture_c);
           
            //new Client
            Client client_a = new Client();
            client_a.setNom("client a");
            client_a.setNum(1);
            session.save(client_a);
            
            //new Client
            Client client_b = new Client();
            client_b.setNom("client b");
            client_b.setNum(2);
            session.save(client_b);
            
            // new Contrat
            Contrat contrat_a = new Contrat();
            contrat_a.setPrixLocationJour(10);
             Date date1 = new Date();
            contrat_a.setDate_debut(date1);
             Date date2 = new Date();
            contrat_a.setDate_fin(date2);
            contrat_a.setClient(client_a);
            contrat_a.setVoiture(voiture_a);
            session.save(contrat_a);
            
            // new Contrat
            Contrat contrat_b = new Contrat();
            contrat_b.setPrixLocationJour(20);
             Date date3 = new Date();
            contrat_b.setDate_debut(date3);
             Date date4 = new Date();
            contrat_b.setDate_fin(date4);
            contrat_b.setClient(client_b);
            contrat_b.setVoiture(voiture_b);
            session.save(contrat_b);
            
            //new Etiquette
            Etiquette etiq_a = new Etiquette();
            etiq_a.setNom("Tiquette a");
            etiq_a.setCouleur("couleur a");
            etiq_a.getVoitures().add(voiture_a);
            session.save(etiq_a);

            // Voiture list by executing HQL Query
            List voitures = session.createQuery("FROM Voiture").list();

            for (Iterator iterator = voitures.iterator(); iterator.hasNext();)
            {
                Voiture voit = (Voiture) iterator.next();
                System.out.print("ID: " + voit.getId());
                System.out.print(" ===> Matricule: " + voit.getMat());
                System.out.print(" ===> Couleure: " + voit.getCol());
                System.out.println(" ===> Marque: " + voit.getMarque().getNom());
                System.out.println(" ===> Etiquette: " + voit.getEtiquettes());
            }
            
            List contrats = session.createQuery("FROM Contrat").list();

            for (Iterator iterator = contrats.iterator(); iterator.hasNext();)
            {
                Contrat voit = (Contrat) iterator.next();
                System.out.print("ID: " + voit.getId());
                System.out.print(" ===> Prix: " + voit.getPrixLocationJour());
                System.out.print(" ===> Date de début: " + voit.getDate_debut());
                System.out.println(" ===> Date de fin: " + voit.getDate_fin());
                System.out.println(" ===> Client: " + voit.getClient().getNom());
                System.out.println(" ===> Voiture: " + voit.getVoiture().getMat());
            }
            
          


            transaction.commit();
        } catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            //e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
        } finally
        {
            session.close();
        }

        // Cleaning up connection pool
        factory.close();
    }
}
