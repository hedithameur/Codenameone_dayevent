/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import edu.esprit.gui.NewsfeedForm;
import edu.esprit.utils.Statics;
import edu.esprit.gui.SessionManager;
import edu.esprit.gui.WalkthruForm;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Ghalya
 */
public class ServiceUtilisateur {

    public static ServiceUtilisateur instance = null;

    public static boolean resultOk = true;
    String json;
    public static float verified;
    public static float active;

    private ConnectionRequest req;

    public static ServiceUtilisateur getInstance() {
        if (instance == null) {
            instance = new ServiceUtilisateur();
        }
        return instance;
    }

    public ServiceUtilisateur() {
        req = new ConnectionRequest();
    }

    public void signup(TextField password, TextField email, TextField confirmPassword, TextField nom, TextField prenom, TextField num_tel, Resources res) {
        String url = Statics.BASE_URL + "/register?email=" + email.getText().toString()+ "&password=" + password.getText().toString()
                + "&nom=" + nom.getText().toString() + "&prenom=" + prenom.getText().toString() + "&num_tel=" + num_tel.getText().toString();
        req.setUrl(url);

        if (nom.getText().equals("") && password.getText().equals("") && email.getText().equals("")) {
            Dialog.show("Erreur", "Veuillez remplir les champs", "OK", null);
        }
        if(!password.getText().equals(confirmPassword.getText())){
            Dialog.show("Erreur", "Mots de passse ne sont pas correspondantes.", "OK",null);
        }

        req.addResponseListener((e) -> {
            byte[] data = (byte[]) e.getMetaData();
            String responseData = new String(data);
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void signin(TextField username, TextField password, Resources rs) {
        String url = Statics.BASE_URL + "/signin?email=" + username.getText().toString() + "&password=" + password.getText().toString();
        req = new ConnectionRequest(url, false);
        req.setUrl(url);
        req.addResponseListener((e) -> {
            JSONParser j = new JSONParser();
            String json = new String(req.getResponseData()) + "";
            try {
                if (json.equals("failed")) {
                    Dialog.show("Echec d'authentification", "Username ou mot de passe éronné", "OK", null);
                } else {
                    Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    System.out.println(user);
                    float id = Float.parseFloat(user.get("id").toString());
                    SessionManager.setId((int) id);
                    SessionManager.setPassowrd(user.get("password").toString());
                    SessionManager.setNom(user.get("nom").toString());
                    SessionManager.setPrenom(user.get("prenom").toString());
                    SessionManager.setEmail(user.get("email").toString());
                    Dialog.show("Welcome", "Logged in successfully", "OK", null);
                    new NewsfeedForm(rs).show();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void modify(TextField nom, TextField prenom, TextField email, Resources rs) {
        String url = Statics.BASE_URL + "/update?id=" + SessionManager.getId() + "&email=" + email.getText().toString()
                + "&nom=" + nom.getText().toString() + "&prenom=" + prenom.getText().toString();
        req = new ConnectionRequest(url, false);
        req.setUrl(url);
        req.addResponseListener((e) -> {
            JSONParser j = new JSONParser();
            String json = new String(req.getResponseData()) + "";
            try {
                if (json.equals("failed")) {
                    Dialog.show("Echec de mise à jour", null, "OK", null);
                } else {
                    Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    System.out.println(user);
                    float id = Float.parseFloat(user.get("id").toString());
                    SessionManager.setId((int) id);
                    SessionManager.setPassowrd(user.get("password").toString());
                    SessionManager.setNom(user.get("nom").toString());
                    SessionManager.setPrenom(user.get("prenom").toString());
                    SessionManager.setEmail(user.get("email").toString());
                    Dialog.show("Done", "Updated successfully", "OK", null);
                    new WalkthruForm(rs).show();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public String getPasswordByEmail(String email, Resources rs) {
        String url = Statics.BASE_URL + "/reset?email=" + email;
        req = new ConnectionRequest(url, false);
        req.setUrl(url);
        req.addResponseListener((e) -> {
            JSONParser j = new JSONParser();
            json = new String(req.getResponseData()) + "";
            try {
                System.out.println("data ==" + json);
                Map<String, Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return json;
    }

    public void getActive() {
        String url = Statics.BASE_URL + "/all";
        req = new ConnectionRequest(url, false);
        req.setUrl(url);
        req.addResponseListener((e) -> {
            JSONParser j = new JSONParser();
            json = new String(req.getResponseData()) + "";
            try {
                Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));
                verified = Float.parseFloat(obj.get("verified").toString());
                active = Float.parseFloat(obj.get("verified").toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
}
