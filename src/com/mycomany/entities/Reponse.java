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
public class Reponse {
    
    
     
    private int idReponse;
    private String messageRep;   
    private String dateRep;   

    public int getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public String getMessageRep() {
        return messageRep;
    }

    public void setMessageRep(String messageRep) {
        this.messageRep = messageRep;
    }

    public String getDateRep() {
        return dateRep;
    }

    public void setDateRep(String dateRep) {
        this.dateRep = dateRep;
    }

 

    public Reponse(int idReponse, String messageRep, String dateRep) {
        this.idReponse = idReponse;
        this.messageRep = messageRep;
        this.dateRep = dateRep;
    }

    public Reponse() {
    }
    
    
    
    
}
