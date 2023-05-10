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
import com.mycomany.entities.Recamation;
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author youss
 */
public class Service_Reclamation {
        
    public ArrayList<Recamation> Coachs;
    public static Service_Reclamation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Service_Reclamation() {
        req = new ConnectionRequest();
    }

     
    public static Service_Reclamation getInstance() {
        if (instance == null) {
            instance = new Service_Reclamation();
        }
        return instance;
    }
    
    
    
    
    
     public ArrayList<Recamation> parseCoach(String jsonText) {
        try {
            
            Coachs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                
                Recamation coach = new Recamation();
                
                
                

               float idReclamation = Float.parseFloat(obj.get("idReclamation").toString());
               coach.setIdreclamation((int) idReclamation);
               
             
                
                    
               coach.setMessage(obj.get("message").toString());
                           
               coach.setType(obj.get("type").toString());
               
                         
               coach.setStatut(obj.get("statut").toString());
               
              
     


                Coachs.add(coach);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Coachs;
    }

    
        
    public ArrayList<Recamation> findAll() {
        String url = Statics.BASE_URL + "reclamation_mobile";
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
    
    
    
    
    
    
    
    
    
    
    
      
          public void AddReclamation(Recamation c) {
        String url = Statics.BASE_URL + "newreclamation_mobile/"+ c.getMessage()+ "/" + c.getType()+ "/" +c.getStatut(); //création de l'URL

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
    
    
    
    
    
    
    
      public boolean deleteReclamation(int idReclamation) {
        String url = Statics.BASE_URL + "SupprimerReclamation?idReclamation=" + idReclamation;
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
    
      
      
      
      
      
      
      
      
          
    public boolean ModifierReclamation( Recamation c) {
        
       String url = Statics.BASE_URL + "updateReclamation?idReclamation=" + c.getIdreclamation()+"&message=" + c.getMessage()+"&type=" + c.getType()+"&statut=" + c.getStatut();

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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
      
    public ArrayList<Recamation> getStat() {
        String url = Statics.BASE_URL + "reclamation_mobile_stat";

 
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               Coachs = parseStat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return Coachs;
    }

    
    
                  
    
    
    
    public ArrayList<Recamation> parseStat(String jsonText) {
        
        try {
            Coachs = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) 
            {           
          
             
            String LibC = obj.get("message").toString();         
            int  quantite =  (int) Float.parseFloat(obj.get("idReclamation").toString());
           
                
            
     
            
            Recamation ab = new Recamation();
            ab.setIdreclamation((int)quantite);
            ab.setMessage(LibC);
            Coachs.add(ab);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Coachs;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
      
}
