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
import com.codename1.charts.util.ColorUtil;
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

            // Créer le modèle de données pour le graphique pie chart
            CategorySeries dataset = new CategorySeries("");
            for (Map<String, Object> stat : stats) {
                String artistName = (String) stat.get("nomArtiste");
                int count = ((Number) stat.get("nombreMusiques")).intValue();
                dataset.add(artistName, count);
            }

            // Créer le rendu par défaut du graphique pie chart
            DefaultRenderer renderer = new DefaultRenderer();
            renderer.setLabelsTextSize(45);
            renderer.setLegendTextSize(45);
            renderer.setMargins(new int[]{20, 30, 15, 0});
            renderer.setShowLabels(true); // Afficher les valeurs dans le graphique
            renderer.setLabelsColor(ColorUtil.BLACK); // Couleur du texte des valeurs

            // Ajouter les couleurs aléatoires au rendu du graphique
            for (int i = 0; i < dataset.getItemCount(); i++) {
                SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
                seriesRenderer.setColor(generateRandomColor());
                renderer.addSeriesRenderer(seriesRenderer);
            }

            // Créer le graphique pie chart
            PieChart chart = new PieChart(dataset, renderer);
            ChartComponent chartComponent = new ChartComponent(chart);

            // Afficher le graphique dans un composant ChartComponent
            this.setLayout(new BorderLayout());
            this.add(BorderLayout.CENTER, chartComponent);
            this.revalidate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    });

    NetworkManager.getInstance().addToQueue(req);
}

private int generateRandomColor() {
    Random rand = new Random();
    return rand.nextInt();
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