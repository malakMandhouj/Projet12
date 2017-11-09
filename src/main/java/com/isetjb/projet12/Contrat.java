/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isetjb.projet12;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author MALEK
 */
@Entity
@Table(name = "contrat")
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    
     @Column(name = "prixLocationJour", nullable = true)
    private float prixLocationJour;

    @Column(name = "date_debut", nullable = true)
    private Date date_debut;
    
     @Column(name = "date_fin", nullable = true)
    private Date date_fin;

    @ManyToOne
    @JoinColumn(name = "id_client", foreignKey = @ForeignKey(name = "Client_ID_FK"))
    private Client client;
    
    @ManyToOne
    @JoinColumn(name = "id_voiture", foreignKey = @ForeignKey(name = "Voiture_ID_FK"))
    private Voiture voiture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrixLocationJour() {
        return prixLocationJour;
    }

    public void setPrixLocationJour(float prixLocationJour) {
        this.prixLocationJour = prixLocationJour;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    
}
