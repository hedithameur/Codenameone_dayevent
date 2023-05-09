/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Evenement;
import com.mycomany.entities.Reclamation;
import com.mycompany.services.ServiceEvent;
import com.mycompany.services.ServiceReclamation;

/**
 *
 * @author 21650
 */
public class modifierEvent extends BaseForm {
     Form current;
    public modifierEvent(Resources res , Evenement r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("DAY EVENT EVENTS");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField Nom = new TextField(r.getNom() , "Nom" , 20 , TextField.ANY);
        TextField Lieu = new TextField(r.getLieu() , "Lieu" , 20 , TextField.ANY);
        TextField Date = new TextField(r.getDate(), "Date" , 20 , TextField.ANY);
        TextField nb = new TextField(String.valueOf(r.getNb_ticket()), "nb", 20, TextField.ANY);
        TextField p = new TextField(String.valueOf(r.getPrix()), "p", 20, TextField.ANY);
        
        
           //    TextField etat = new TextField(String.valueOf(r.getEtat()) , "Etat" , 20 , TextField.ANY);
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
       
        
        
        
        Nom.setUIID("NewsTopLine");
        Lieu.setUIID("NewsTopLine");
        Date.setUIID("NewsTopLine");
        nb.setUIID("NewsTopLine");
        p.setUIID("NewsTopLine");
       // etat.setUIID("NewsTopLine");
        
        Nom.setSingleLineTextArea(true);
        Lieu.setSingleLineTextArea(true);
        Date.setSingleLineTextArea(true);
        nb.setSingleLineTextArea(true);
        p.setSingleLineTextArea(true);
        
       // etat.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setNom(Nom.getText());
           r.setLieu(Lieu.getText());
           r.setDate(Date.getText());
           r.setNb_ticket(Integer.valueOf(nb.getText()));
           r.setPrix(Integer.valueOf(p.getText()));
           
           
          
       
       //appel fonction modfier reclamation men service
       
       if(ServiceEvent.getInstance().modifierEvent(r)) { // if true
           new ListeEvent(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListeEvent(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(Nom),
                createLineSeparator(),
                new FloatingHint(Lieu),
                createLineSeparator(),
                new FloatingHint(Date),
                createLineSeparator(),
                new FloatingHint(nb),
                createLineSeparator(),
                new FloatingHint(p),
                createLineSeparator(),
               // etatCombo,
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }

    
    
}

