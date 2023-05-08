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
import com.mycompany.entities.Ticket;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hedit
 */
public class ServiceTicket {
    public static ServiceTicket instance = null;
    public static boolean resultOk = true;
    private ConnectionRequest req;

    public static ServiceTicket getInstance() {
        if (instance == null) {
            instance = new ServiceTicket();
        }
        return instance;
    }

    public ServiceTicket() {
        req = new ConnectionRequest();
    }
    public void ajoutTicket(Ticket ticket) {
    String url = Statics.BASE_URL + "/add?prix=" + ticket.getPrix()
            + "&nbTickets=" + ticket.getNb_tickets()
            + "&type=" + ticket.getType()
            + "&idEvenement=" + ticket.getIdEvenement();

    req.setUrl(url);
    req.addResponseListener((e) -> {
        String str = new String(req.getResponseData());
        System.out.println("data == " + str);
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
}
    
    public ArrayList<Ticket> affichageTickets() {
        ArrayList<Ticket> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapTickets = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapTickets.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Ticket ticket = new Ticket();

                        // Parse the ticket properties
                        float id = Float.parseFloat(obj.get("id").toString());
                        float prix = Float.parseFloat(obj.get("prix").toString());
                        float nb_tickets = Float.parseFloat(obj.get("nbTickets").toString());
                        String type = obj.get("type").toString();
                        float IdEvenement = Float.parseFloat(obj.get("idEvenement").toString());
                        

                        ticket.setId((int) id);
                        ticket.setPrix(prix);
                        ticket.setNb_tickets((int) nb_tickets);
                        ticket.setType(type);
                        ticket.setIdEvenement((int)IdEvenement);

                        // Add the ticket to the result list
                        result.add(ticket);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    }
    
    public Ticket detailTicket(int id) {
    String url = Statics.BASE_URL + "/detail/" + id;
    req.setUrl(url);

    final Ticket ticket = new Ticket(); // Create a new Ticket object
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            String str = new String(req.getResponseData());

            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(str.toCharArray()));

                ticket.setId(Integer.parseInt(obj.get("id").toString()));
                ticket.setPrix(Double.parseDouble(obj.get("prix").toString()));
                ticket.setNb_tickets(Integer.parseInt(obj.get("nbTickets").toString()));
                ticket.setType(obj.get("type").toString());
                ticket.setIdEvenement(Integer.parseInt(obj.get("id_evenement").toString()));
                
            } catch (IOException ex) {
                System.out.println("Error related to SQL: " + ex.getMessage());
            }

            System.out.println("Data: " + str);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return ticket;
}




    public boolean deleteTicket(int id) {
    String url = Statics.BASE_URL + "/delete/" + id;
    req.setUrl(url);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            req.removeResponseCodeListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk;
    }
    
    public boolean modifyTicket(Ticket ticket) {
    String url = Statics.BASE_URL + "/update/" + ticket.getId() + "?prix=" + ticket.getPrix() 
            + "&nbTickets=" + ticket.getNb_tickets() 
            + "&type=" + ticket.getType() 
            + "&idEvenement=" + ticket.getIdEvenement();
    req.setUrl(url);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOk = req.getResponseCode() == 200; // HTTP response code 200 indicates success
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk;
}
    
}
