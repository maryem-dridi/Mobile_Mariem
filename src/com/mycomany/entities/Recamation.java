/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author youss
 */
public class Recamation {
    
    
    private int idReclamation;
    private String message;   
    private String type;   
    private String statut;   

    public int getIdreclamation() {
        return idReclamation;
    }

    public void setIdreclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }



    public Recamation(int idReclamation, String message, String type, String statut) {
        this.idReclamation = idReclamation;
        this.message = message;
        this.type = type;
        this.statut = statut;
    }

    public Recamation() {
    }
    
    
    
    
   
    
}
