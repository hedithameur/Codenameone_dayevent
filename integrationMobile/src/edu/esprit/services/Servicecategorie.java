/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.company.entites.Categorie;
import static edu.esprit.services.ServiceTicket.resultOk;
import edu.esprit.utils.Statics;
//import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import java.awt.event.ActionListener;

/**
 *
 * @author bouzi
 */
public class Servicecategorie {
    //appel crud en json dans symfony
    public static Servicecategorie instance= null ;
    private ConnectionRequest req ;
    public static Servicecategorie getinstance(){
        if (instance == null)
            instance = new edu.esprit.services.Servicecategorie();
        return instance ;
        
    }
    
public  Servicecategorie (){
    req = new ConnectionRequest();
    
}    
public void ajoutercategorie (Categorie cate){
    String url =Statics.BASE_URL+"/categorie/instrument/addDemandeJSON?nomcategorie="+cate.getNomCategorie()+"&description="+cate.getDescription();
    req.setUrl(url);
    req.addResponseListener((e) ->{
        String str = new String (req.getResponseData());
        System.out.println("data == "+str);
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
}
    public ArrayList<Categorie> afficher() {
        ArrayList<Categorie> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/categorie/instrument/categories";
        req.setUrl(url);
        req.addResponseListener(new com.codename1.ui.events.ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapCategorie = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapCategorie.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        Categorie re = new Categorie();

                       float id = Float.parseFloat(obj.get("id").toString());
                        String nomCategorie = obj.get("nomCategorie").toString();
                        String description = obj.get("description").toString();
                        re.setId((int) id);
                        re.setNomCategorie(nomCategorie);
                        re.setDescription(description);
                        result.add(re);
                    }
                } catch (Exception ex) {

                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;

    }
        
public Categorie DetailRecalamation( int id , Categorie cat) {
        
        String url = Statics.BASE_URL+"/categorie/instrument/det/id"+id;
        req.setUrl(url);
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                cat.setNomCategorie(obj.get("nomCategorie").toString());
                cat.setDescription(obj.get("description").toString());


                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return cat;
        
        
    }
      public boolean deleteCategorie(int id ) {
        String url = Statics.BASE_URL +"/categorie/instrument/DeleteCategorie/"+id;
        
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
    }

