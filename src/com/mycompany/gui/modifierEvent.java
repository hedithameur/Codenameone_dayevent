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
        setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField objet = new TextField(r.getNom() , "Objet" , 20 , TextField.ANY);
        TextField description = new TextField(r.getLieu() , "Description" , 20 , TextField.ANY);
           //    TextField etat = new TextField(String.valueOf(r.getEtat()) , "Etat" , 20 , TextField.ANY);
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
       
        
        
        
        objet.setUIID("NewsTopLine");
        description.setUIID("NewsTopLine");
       // etat.setUIID("NewsTopLine");
        
        objet.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
       // etat.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setNom(objet.getText());
           r.setLieu(description.getText());
           
          
       
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
                new FloatingHint(objet),
                createLineSeparator(),
                new FloatingHint(description),
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

