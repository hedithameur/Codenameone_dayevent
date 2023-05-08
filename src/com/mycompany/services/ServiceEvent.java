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
import com.mycomany.entities.Evenement;
import com.mycomany.entities.Reclamation;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 21650
 */
public class ServiceEvent {
      public static ServiceEvent instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceEvent getInstance() {
        if(instance == null )
            instance = new ServiceEvent();
        return instance ;
    }
    
    
    
    public ServiceEvent() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutEvenement(Evenement evenement) {
        
        String url =Statics.BASE_URL+"/addeventmobile?nom="+evenement.getNom()+"&lieu="+evenement.getLieu()+"&date="+evenement.getDate()+"+&nb_ticket="+evenement.getNb_ticket()+"&Prix="+evenement.getPrix()+"&Affiche="+evenement.getAffiche();
       

        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //affichage
    
   public ArrayList<Evenement> affichageEvent() {
    ArrayList<Evenement> result = new ArrayList<>();

    String url = Statics.BASE_URL + "/showeventmobile";
    req.setUrl(url);

    req.addResponseListener(evt -> {
        JSONParser jsonp = new JSONParser();
        try {
            Map<String, Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
            List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapReclamations.get("root");

            for (Map<String, Object> obj : listOfMaps) {
                Evenement evenement = new Evenement();

                float id = Float.parseFloat(obj.get("id").toString());
                String nom = obj.get("nom").toString();
                String lieu = obj.get("lieu").toString();
                String affiche = obj.get("affiche").toString();
                String dateStr = obj.get("date").toString();
              int nbTicket = Math.round(Float.parseFloat(obj.get("nb_ticket").toString()));

             float prixFloat = Float.parseFloat(obj.get("prix").toString());
                int prix = (int) prixFloat;


                evenement.setId((int) id);
                evenement.setNom(nom);
                evenement.setLieu(lieu);
                evenement.setAffiche(affiche);
                evenement.setNb_ticket(nbTicket);
                evenement.setPrix(prix);

                // Parse the date
    String dateStr1 = obj.get("date").toString();
String dateConverter = "";
if (dateStr1.contains("timestamp")) {
    dateConverter = dateStr1.substring(dateStr1.indexOf("timestamp") + 10, dateStr1.lastIndexOf("}"));
} else {
    dateConverter = "0";
}
Date date = new Date(Double.valueOf(dateConverter).longValue() * 1000);
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String dateString = formatter.format(date);
evenement.setDate(dateString);



                result.add(evenement);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return result;
}

    
    
    
    
    //Delete 
    public boolean deleteEvenement(int id ) {
        String url = Statics.BASE_URL +"/deletecvmobile?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
    
    //Update 
    public boolean modifierEvent(Evenement evenement) {
        String url = Statics.BASE_URL +"/editeventmobile?id="+evenement.getId()+"&nom="+evenement.getNom()+"&lieu="+evenement.getLieu()+"&date="+evenement.getDate()+"&nb_ticket="+evenement.getNb_ticket()+"&Prix="+evenement.getPrix()+"&Affiche="+evenement.getAffiche();
        req=new ConnectionRequest(url);
             req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //Code HTTP 200 OK
               req.removeResponseListener(this);  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
}