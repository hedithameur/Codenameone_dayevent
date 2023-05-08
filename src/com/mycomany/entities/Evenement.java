/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

import java.util.Date;

/**
 *
 * @author 21650
 */
public class Evenement {
     private int id;
    private String nom;
    private String lieu;
    private String affiche;
    private String date;
    private int nb_ticket;
    private int prix;

    public Evenement(String nom, String lieu, String date, int nb_ticket, int prix, String affiche) {
        this.nom = nom;
        this.lieu = lieu;
        this.date= date;
        this.nb_ticket = nb_ticket;
        this.prix = prix;
        this.affiche = affiche;
    }

    public Evenement() {
     
    }

   

   

    

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getLieu() {
        return lieu;
    }

    public String getAffiche() {
        return affiche;
    }

    public String getDate() {
        return date;
    }

    public int getNb_ticket() {
        return nb_ticket;
    }

    public int getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNb_ticket(int nb_ticket) {
        this.nb_ticket = nb_ticket;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Evenement(String nom, String lieu, String affiche, String date, int nb_ticket, int prix) {
        this.nom = nom;
        this.lieu = lieu;
        this.affiche = affiche;
        this.date = date;
        this.nb_ticket = nb_ticket;
        this.prix = prix;
    }
    


    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", lieu=" + lieu + ", affiche=" + affiche + ", date=" + date + ", nb_ticket=" + nb_ticket + ", prix=" + prix + '}';
    }

   

    

   

   
}
