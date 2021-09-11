package bitw.controllers;

import bitw.Main;
import bitw.models.AppModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button btnAllItems;

    @FXML
    private Button btnFavorites;

    @FXML
    private Button btnTrash;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnCard;

    @FXML
    private Button btnIdentity;

    @FXML
    private Button btnNote;

    @FXML
    private Button btnWork;

    @FXML
    private Button btnSocial;

    @FXML
    private Button btnPersonal;

    @FXML
    private ImageView ivLogo;

    @FXML
    private Label lblCompanyName;

    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private Label lblWebsite;

    @FXML
    private Label lblNotes;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField tfSearch;

    @FXML
    private ImageView btnAdd;

    @FXML
    private VBox vItems;

    private boolean[] isSelected;

    @FXML
    void handleButtonClicks(ActionEvent event) {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //

        try {
            List<AppModel> apps = new ArrayList<>();
            apps.add(new AppModel("Apple", "mail@apple.com", "icons/apple.png", "apple.com", "Intuitive User"));
            apps.add(new AppModel("Telegram", "mail@telegram.com", "icons/telegram.png", "telegram.com", "Secret Chats"));
            apps.add(new AppModel("Facebook", "mail@facebook.com", "icons/facebook.png", "facebook.com", "Connect with friends"));
            apps.add(new AppModel("Adobe", "mail@adobe.com", "icons/adobecc.png", "adobe.com", "Create, Design and Sell"));
            apps.add(new AppModel("Dribbble", "mail@dribbble.com", "icons/dribble.png", "dribble.com", "Flex your skill"));
            apps.add(new AppModel("Google", "mail@google.com", "icons/google.png", "google.com", "Search everything"));
            apps.add(new AppModel("Etsy", "mail@etsy.com", "icons/etsy.png", "etsy.com", "Some notes here"));

            Node[] nodes = new Node[apps.size()];

            for (int i = 0; i < nodes.length; i++) {
                //stays in the loop
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("fxml/mainitem.fxml"));
                nodes[i] = loader.load();

                //add selected clicks

                isSelected = new boolean[apps.size()];
                final int h = i;
                MainItemController controller = loader.getController();
                //customize items.
                controller.setItemInfo(apps.get(i).getAppName(), apps.get(i).getAppEmail(), apps.get(i).getAppIcon());

                nodes[i].setOnMouseEntered(evt -> {
                    //add effect
                    if (!isSelected[h]) {
                        nodes[h].setStyle("-fx-background-color: #165DDB");
                    }
                });
                nodes[i].setOnMouseExited(evt -> {
                    if (isSelected[h]) {
                        nodes[h].setStyle("-fx-background-color: #165DDB");
                    } else {
                        nodes[h].setStyle("-fx-background-color: #1E1E1E");
                    }
                });

                nodes[i].setOnMousePressed(evt -> {
                    //reset the array
                    Arrays.fill(isSelected, Boolean.FALSE);
                    isSelected[h] = true;
                    //reset the gui
                    for (Node n : nodes) {
                        n.setStyle("-fx-background-color: #1E1E1E");
                    }
                    if (isSelected[h])
                    { nodes[h].setStyle("-fx-background-color: #165DDB");}
                    lblCompanyName.setText(apps.get(h).getAppName());
                    //do something
                    lblWebsite.setText(apps.get(h).getWebsite());
                    lblNotes.setText(apps.get(h).getNotes());
                    ivLogo.setImage(new Image(String.valueOf(Main.class.getResource(apps.get(h).getAppIcon()))));
                    tfUsername.setText(apps.get(h).getAppEmail());
                });
                vItems.getChildren().add(nodes[i]);

                //some other func

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
