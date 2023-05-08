/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


//CODE TEMPLATE :


import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.mycomany.utils.Statics;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class StatistiquePieForm extends BaseForm {

    public StatistiquePieForm(Resources res) {
        super("Music Stats", new BorderLayout());
         Button btnback = new Button("Back");
       
               btnback.addActionListener((e) -> {
               
             new  AjoutRMusicForm(res).show();
               });
        affichageStats();
      addComponent(BorderLayout.SOUTH, btnback);
         
    }
 

    public void affichageStats() {
        String url = Statics.BASE_URL + "/stats";
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setHttpMethod("GET");

        req.addResponseListener(e -> {
            JSONParser jsonParser = new JSONParser();
            try {
                // Analyser la réponse JSON
                Map<String, Object> response = jsonParser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));

                // Récupérer les données de statistiques
                List<Map<String, Object>> stats = (List<Map<String, Object>>) response.get("root");
    List<String> artistNames = new ArrayList<>();
                // Créer le modèle de données pour le graphique pie chart
                CategorySeries dataset = new CategorySeries("");
                for (Map<String, Object> stat : stats) {
                    String label = (String) stat.get("nomArtiste");
  int count = ((Number) stat.get("nombreMusiques")).intValue();
    dataset.add(label, count);
             String artistName = (String) stat.get("nomArtiste");
        artistNames.add(artistName);
                }

                // Créer le graphique pie chart
                  DefaultRenderer renderer = new DefaultRenderer();
    Random rand = new Random();
    for (int i = 0; i < dataset.getItemCount(); i++) {
        SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
        seriesRenderer.setColor(rand.nextInt());
        renderer.addSeriesRenderer(seriesRenderer);
    }


                PieChart chart = new PieChart(dataset, renderer);
        
                ChartComponent c  = new ChartComponent(chart);
        
     String[] messages = {
    "Statistique les artistes qui ont plus musiques "
};

SpanLabel message = new SpanLabel(messages[0], "WelcomeMessage");

Container cnt = BorderLayout.center(message);
cnt.setUIID("Container");
addComponent(BorderLayout.NORTH, cnt);
addComponent(BorderLayout.CENTER, c);

               

                // Afficher le graphique dans un composant ChartComponent
                ChartComponent chartComponent = new ChartComponent(chart);
                addComponent(BorderLayout.CENTER, chartComponent);
                revalidate();
                
           //colors set:
        

     //   PieChart chart = new PieChart(dataset, renderer);



            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        NetworkManager.getInstance().addToQueue(req);
    }
      public DefaultRenderer buildCatRendrer(int []colors) {
        
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[] {20, 30, 15, 0});
        
        for(int color : colors) {
            SimpleSeriesRenderer simpleSeriesRenderer = new SimpleSeriesRenderer();
            
            simpleSeriesRenderer.setColor(color);
            renderer.addSeriesRenderer(simpleSeriesRenderer);
        }
        return renderer;
     }  
    
}