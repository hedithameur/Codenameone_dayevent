/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author user
 */
public class GlobalForm extends BaseForm {
    public GlobalForm(Resources res) {
        super("Buttons Example", new BorderLayout());

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        // Création du bouton 1
        Button button1 = new Button("Evenement");
        button1.addActionListener(e -> {
            System.out.println("Button 1 a été cliqué.");
            // Ajoutez ici le code que vous souhaitez exécuter lorsqu'on clique sur le bouton 1
        });
        container.addComponent(button1);

        // Création du bouton 2
        Button button2 = new Button("Musiques");
        button2.addActionListener(e -> {
           new AjoutRMusicForm(res).show();
            // Ajoutez ici le code que vous souhaitez exécuter lorsqu'on clique sur le bouton 2
        });
        container.addComponent(button2);
  Button button3 = new Button("Ticket");
        button3.addActionListener(e -> {
           new AjoutTicketForm(res).show();
            // Ajoutez ici le code que vous souhaitez exécuter lorsqu'on clique sur le bouton 2
        });
        container.addComponent(button3);
          Button button4 = new Button("Categories Instrument");
        button4.addActionListener(e -> {
         new AjoutCategorie(res).show();
            // Ajoutez ici le code que vous souhaitez exécuter lorsqu'on clique sur le bouton 2
        });
        container.addComponent(button4);
          Button button5 = new Button("Reclamation");
        button5.addActionListener(e -> {
            new AjoutReclamationForm(res).show();
            // Ajoutez ici le code que vous souhaitez exécuter lorsqu'on clique sur le bouton 2
        });
        container.addComponent(button5);
        Button button6 = new Button("Votre Profil");
        button6.addActionListener(e -> {
            new ProfileForm(res).show();
            // Ajoutez ici le code que vous souhaitez exécuter lorsqu'on clique sur le bouton 2
        });
         container.addComponent(button6);
        // Répétez les étapes ci-dessus pour créer et ajouter des écouteurs d'événements à d'autres boutons
        // Créez et ajoutez des boutons supplémentaires selon vos besoins
        
        add(BorderLayout.CENTER, container);
    }
}

