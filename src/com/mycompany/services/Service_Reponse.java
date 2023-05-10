/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Reponse;
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author youss
 */
public class Service_Reponse {
        
    public ArrayList<Reponse> Coachs;
    public static Service_Reponse instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Service_Reponse() {
        req = new ConnectionRequest();
    }

     
    public static Service_Reponse getInstance() {
        if (instance == null) {
            instance = new Service_Reponse();
        }
        return instance;
    }
    
    
    
    
    
     public ArrayList<Reponse> parseCoach(String jsonText) {
        try {
            
            Coachs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                
                Reponse coach = new Reponse();
                
                
                

               float idReponse = Float.parseFloat(obj.get("idReponse").toString());
               coach.setIdReponse((int) idReponse);
               
             
               
          
               
                    
               coach.setMessageRep(obj.get("messageRep").toString());
                           
               coach.setDateRep(obj.get("dateRep").toString());
               
                         
               
              
     


                Coachs.add(coach);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Coachs;
    }

    
        
    public ArrayList<Reponse> findAll() {
        String url = Statics.BASE_URL + "reponse_mobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Coachs = parseCoach(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Coachs;
    }
    
    
    
    
    
    
    
    
    
    
    
      
          public void AddReponse(Reponse c) {
        String url = Statics.BASE_URL + "newreponse_mobile/"+c.getMessageRep()+ "/" +c.getDateRep(); //création de l'URL

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
   
    }
    
    
    
    
    
    
    
      public boolean deleteReponse(int idReponse) {
        String url = Statics.BASE_URL + "SupprimerReponse?idReponse=" + idReponse;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
                    
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(e -> {
            String str = new String(req.getResponseData());//reponse jason 
            System.out.println("data ==> " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        return resultOK;
    }
    
      
      
      
      
      
      
      
      
          
    public boolean ModifierReponse( Reponse c) {
        
       String url = Statics.BASE_URL + "updateReponse?idReponse=" + c.getIdReponse()+"&messageRep=" + c.getMessageRep();

       req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //code success  http 200 ok
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        //System.out.println("url ==> " + url);
        //System.out.println("data ==> " + req);
        return resultOK;

    }
    
    
    
    
    
      
}
