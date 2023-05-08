/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author Hedi
 */
public class Utilisateur {
    private int id;
    private String nom,prenom,email,roles,num_tel,motdepasse,date;

    public Utilisateur(){
    }

    public Utilisateur(int id, String nom, String prenom, String email, String roles, String num_tel, String motdepasse, String date) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.roles = roles;
        this.num_tel = num_tel;
        this.motdepasse = motdepasse;
        this.date = date;
    }

    public Utilisateur(String nom, String prenom, String email, String roles, String num_tel, String motdepasse, String date) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.roles = roles;
        this.num_tel = num_tel;
        this.motdepasse = motdepasse;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", roles=" + roles + ", num_tel=" + num_tel + ", motdepasse=" + motdepasse + ", date=" + date + '}';
    }
    
    
    
}
