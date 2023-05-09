/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Evenement;

import com.mycompany.services.ServiceEvent;
import java.text.SimpleDateFormat;

import java.util.Date;


/**
 *
 * @author 21650
 */
public class AjouterEvent extends BaseForm{
     Form current;
     String dateString;
        Date date;
    public AjouterEvent(Resources res ) {
        super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Evenement");
        getContentPane().setScrollVisible(false);
         super.addSideMenu(res);
        
        tb.addSearchCommand(e ->  {
            
        });
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("back-logo.jpeg"),"","",res);
        
        //
        
         swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

       

        
        //
        
      
        TextField nom = new TextField("", "entrer Nom!!");
        nom.setUIID("TextFieldBlack");
        addStringValue("nom",nom);
        
        TextField lieu = new TextField("", "entrer lieu!!");
        lieu.setUIID("TextFieldBlack");
        addStringValue("lieu",lieu);
      /*  
        TextField date = new TextField("", "entrer date!!");
        date.setUIID("TextFieldBlack");
        addStringValue("date",date);*/
      Picker datePicker = new Picker();
datePicker.setType(Display.PICKER_TYPE_DATE);
datePicker.setUIID("TextFieldBlack");
addStringValue("datePicker", datePicker);
System.out.println(datePicker);

datePicker.addActionListener((e) -> {
     date = datePicker.getDate();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    dateString = formatter.format(date);
    System.out.println(dateString);
});
 



     

        
        
        
        TextField nb_ticket = new TextField("", "entrer nb_ticket!!");
        nb_ticket.setUIID("TextFieldBlack");
        addStringValue("nb_ticket",nb_ticket);
        
        TextField prix = new TextField("", "entrer prix!!");
        prix.setUIID("TextFieldBlack");
        addStringValue("prix",prix);
        
        TextField affiche = new TextField("", "entrer affiche!!");
        affiche.setUIID("TextFieldBlack");
        addStringValue("affiche",affiche);
        
        
        
     
        
        
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        



// Compare the LocalDate to today's date

        //onclick button event 

        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    
                    if(nom.getText().equals("") ||nb_ticket.getText().equals("") || prix.getText().equals("") || lieu.getText().equals("")|| affiche.getText().equals("")) {
                        Dialog.show("Veuillez vérifier un ou plusieur champs sont vides","","Annuler", "OK");
                    }
                    
                    else if (nom.getText().length() < 5)
                    {
                         Dialog.show("Le nom d'event doit contenir au moins 5 caractères","","Annuler", "OK");
                    }
                    
                   
                    else {
                        InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                        
                        final Dialog iDialog = ip.showInfiniteBlocking();
                        
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        
                        int nbTicketInt = Integer.parseInt(nb_ticket.getText());
                        int prixInt = Integer.parseInt(prix.getText());

                        //njibo iduser men session (current user)
                        Evenement r = new Evenement(nom.getText(),
                                lieu.getText(),
                                dateString,
                               nbTicketInt,
                               prixInt,
                                affiche.getText());
                        
                        
                        System.out.println("data  event == "+r);
                        
                        
                        //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base
                        ServiceEvent.getInstance().ajoutEvenement(r);
                        
                        iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                        
                        //ba3d ajout net3adaw lel ListREclamationForm
                        new ListeEvent(res).show();
                        
                        
                        refreshTheme();//Actualisation
                        
                    }
                    
                }catch(Exception ex ) {
                    ex.printStackTrace();                            
                }
            }
        });
        
        
    }

    private void addStringValue(String s, Component v) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }

    private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if(image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        
        
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay = new Label("","ImageOverlay");
        
        
        Container page1 = 
                LayeredLayout.encloseIn(
                imageScale,
                        overLay,
                        BorderLayout.south(
                        BoxLayout.encloseY(
                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                        )
                    )
                );
        
        swipe.addTab("",res.getImage("back-logo.jpeg"), page1);
        
        
        
        
    }
    
    
    
    public void bindButtonSelection(Button btn , Label l ) {
        
        btn.addActionListener(e-> {
        if(btn.isSelected()) {
            updateArrowPosition(btn,l);
        }
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
        
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth()  / 2  - l.getWidth() / 2 );
        l.getParent().repaint();
    }
    
   
   
    
}
