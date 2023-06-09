/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Chadi
 */
public class MapForm {
Form f = new Form();
  MapContainer cnt = null;
    private Resources theme;
public MapForm() {
       theme = UIManager.initFirstTheme("/theme");  
    try{
        cnt = new MapContainer("AIzaSyCy-fMWerzvXcPCV0FDI07hW2DAzs_mnpY");
    }catch(Exception ex) {
        ex.printStackTrace();
    }
 Button btnRetour = new Button();
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "Button", 3.5f);
        btnRetour.setIcon(icon);
        btnRetour.addActionListener(e -> {
             new AjoutReclamationForm(theme).show();
    
            
        });
        Button btnMoveCamera = new Button("Tunis , Tunisie");
        btnMoveCamera.addActionListener(e->{
            cnt.setCameraPosition(new Coord(36.8189700, 10.1657900));
            
        });
        
        
        
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, Display.getInstance().convertToPixels(3));

        
    


        cnt.addTapListener(e->{
    
            
                cnt.clearMapLayers();
                cnt.addMarker(
                        EncodedImage.createFromImage(markerImg, false),
                        cnt.getCoordAtPosition(e.getX(), e.getY()),
                        ""+cnt.getCameraPosition().toString(),
                        "",
                        e3->{
                                ToastBar.showMessage("You clicked "+cnt.getName(), FontImage.MATERIAL_PLACE);
                        }
                );
             ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl("http://maps.google.com/maps/api/geocode/json?latlng="+cnt.getCameraPosition().getLatitude()+","+cnt.getCameraPosition().getLongitude()+"&oe=utf8&sensor=false");
                     NetworkManager.getInstance().addToQueueAndWait(r);

            JSONParser jsonp = new JSONParser();
         try {
               java.util.Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(r.getResponseData()).toCharArray()));
                              System.out.println("roooooot:" +tasks.get("results"));
                              List<java.util.Map<String, Object>> list1 = (List<java.util.Map<String, Object>>)tasks.get("results");
//                              java.util.Map<String, Object> list = (java.util.Map<String, Object>) list1.get(0);

  //                             List<java.util.Map<String, Object>> listf = (List<java.util.Map<String, Object>>) list.get("address_components");
//String ch="";
  //                       for (java.util.Map<String, Object> obj : listf) {
    //             ch=ch+obj.get("long_name").toString();
      //                   }
                       //
                         // b.setAdresse(ch);

                        

           } catch (IOException ex) {
           }

            
            
        });
 Container buttonsContainer = new Container(new FlowLayout());
        buttonsContainer.add(btnRetour);
        buttonsContainer.add(btnMoveCamera);

        f.setLayout(new BorderLayout());
        f.addComponent(BorderLayout.CENTER, cnt);
        f.addComponent(BorderLayout.SOUTH, buttonsContainer);
        f.show();
}
    MapForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    

    
}