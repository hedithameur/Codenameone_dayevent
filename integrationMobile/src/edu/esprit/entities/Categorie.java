/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entites;

/**
 *
 * @author bouzi
 */
public class Categorie {
    private int id ;
    private String nomCategorie;
    private String  description ;

    public Categorie() {
    }

    public Categorie(int id, String nomCategorie, String description) {
        this.id = id;
        this.nomCategorie = nomCategorie;
        this.description = description;
    }

    public Categorie(String nomCategorie, String description) {
        this.nomCategorie = nomCategorie;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
