/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

//import java.time.LocalDate;
//import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class Reclamation {
    private int id_rec;
    private String date_rec;
    private String titre,tel;
    private String Description ;

    public Reclamation() {
    }

    public Reclamation(int id_rec, String  date_rec, String titre, String Description, String tel) {
        this.id_rec = id_rec;
        this.date_rec = date_rec;
        this.titre = titre;
        this.Description = Description;
        this.tel=tel;
    }
    

    public Reclamation(String  date_rec, String titre, String Description,String tel) {
        this.date_rec = date_rec;
        this.titre = titre ;
        this.Description = Description;
        this.tel=tel;
    }

    public Reclamation(String toString, String toString0, String toString1, String format, int i, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
   

    public String getTel() {
        return tel;
    }
     public void setTel(String tel) {
        this.tel = tel;
    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public String  getDate_rec() {
        return date_rec;
    }

    public void setDate_rec(String  date_rec) {
        this.date_rec = date_rec;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    
    }
