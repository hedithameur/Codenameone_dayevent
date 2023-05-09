/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

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
import edu.esprit.entities.Music;
import edu.esprit.services.ServiceMusic;

/**
 *
 * @author Lenovo
 */
public class ModifierMusicForm extends BaseForm {
    
    Form current;
    public ModifierMusicForm(Resources res , Music r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Music");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField nomMorceaux = new TextField(r.getNom_morceaux() , "nom du morceaux" , 20 , TextField.ANY);
        TextField nomArtiste = new TextField(r.getNom_artiste() , "nom de l artiste" , 20 , TextField.ANY);
               TextField fichier = new TextField(r.getFichier() , "fichier" , 20 , TextField.ANY);
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
       
        
        
        
        
        
        nomMorceaux.setUIID("NewsTopLine");
        nomArtiste.setUIID("NewsTopLine");
        fichier.setUIID("NewsTopLine");
        
     nomMorceaux.setSingleLineTextArea(true);
       nomArtiste.setSingleLineTextArea(true);
        fichier.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           r.setNom_morceaux(nomMorceaux.getText());
           r.setNom_artiste(nomArtiste.getText());
           r.setFichier(fichier.getText());
      
       
       //appel fonction modfier reclamation men service
       
       if(ServiceMusic.getInstance().modifierMusic(r)) { // if true
           new ListMusicForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListMusicForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2,l3 ,
                new FloatingHint(nomMorceaux),
                createLineSeparator(),
                new FloatingHint(nomArtiste),
                createLineSeparator(),
             
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}
