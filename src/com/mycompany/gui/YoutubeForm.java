package com.mycompany.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class YoutubeForm extends Form {
    private static final String API_KEY = "AIzaSyC4kTU9ydpPFxi2hvnmew72esl4N0wYdQM";
    private static final int MAX_RESULTS = 10;

    private TextField searchField;
    private Button searchButton;
    private Container videosContainer;

    public YoutubeForm(Resources res ) {
        super("Youtube Video Search Using Data API V3");
        setLayout(new BorderLayout());

        Container content = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        addComponent(BorderLayout.CENTER, content);

        Label title = new Label("Youtube Video Search Using Data API V3");
        title.getAllStyles().setAlignment(CENTER);
        content.addComponent(title);

        searchField = new TextField();
        searchButton = new Button("Search Videos");
        searchButton.addActionListener(e -> searchVideos());
        Container searchContainer = new Container(new BorderLayout());
        searchContainer.addComponent(BorderLayout.CENTER, searchField);
        searchContainer.addComponent(BorderLayout.EAST, searchButton);
        content.addComponent(searchContainer);

        videosContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        content.addComponent(videosContainer);

        getToolbar().addCommandToSideMenu("Exit", null, e -> Display.getInstance().exitApplication());
    }

    private void searchVideos() {
        String search = searchField.getText();
        if (search.isEmpty()) {
            return;
        }
 ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
        r.setUrl("http://www.googleapis.com/youtube/v3/search?key=" + API_KEY +
                "&type=video&part=snippet&maxResults=" + MAX_RESULTS + "&q=" + search);
NetworkManager.getInstance().addToQueueAndWait(r);
    JSONParser jsonp = new JSONParser();
        ConnectionRequest request = new ConnectionRequest() {
            protected void readResponse(InputStream input) throws IOException {
                JSONParser jsonParser = new JSONParser();
                Map<String, Object> response = jsonParser.parseJSON(new InputStreamReader(input, "UTF-8"));

                List<Map<String, Object>> items = (List<Map<String, Object>>) response.get("items");
                for (Map<String, Object> item : items) {
                    Map<String, Object> id = (Map<String, Object>) item.get("id");
                    String videoId = (String) id.get("videoId");
                    String videoUrl = "http://www.youtube.com/embed/" + videoId;
                    addVideoComponent(videoUrl);
                }
            }
        };

      
    }


    private void addVideoComponent(String videoUrl) {
        Label video = new Label(videoUrl);
        videosContainer.addComponent(video);
    }

  
}