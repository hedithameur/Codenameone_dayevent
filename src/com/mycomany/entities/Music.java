/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class Music {
    private int id;
    private String nom_morceaux;
    private String nom_artiste;
   private String  fichier;

  public Music()
{
    
}

    public Music(String nom_morceaux, String nom_artiste, String fichier) {
        this.nom_morceaux = nom_morceaux;
        this.nom_artiste = nom_artiste;
        this.fichier = fichier;
    }



    public Music(int id, String nom_artiste, String nom_morceaux,  String fichier) {
        this.id = id;
        this.nom_artiste = nom_artiste;
        this.nom_morceaux = nom_morceaux;
    
        this.fichier = fichier;
       
    }


   

   

    public int getId() {
        return id;
    }

    public String getNom_artiste() {
        return nom_artiste;
    }

    public String getNom_morceaux() {
        return nom_morceaux;
    }

    public String getFichier() {
        return fichier;
    }

   

    
   

    

    public void setId(int id) {
        this.id = id;
    }

    public void setNom_artiste(String nom_artiste) {
        this.nom_artiste = nom_artiste;
    }

    public void setNom_morceaux(String nom_morceaux) {
        this.nom_morceaux = nom_morceaux;
    }

    

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

 

    

   
    

    
    



    
    
    
    
}

