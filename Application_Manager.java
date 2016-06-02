import java.net.InetAddress;
import java.net.UnknownHostException;
import java.lang.Process;
import java.lang.Runtime;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Varun's Application Manager for Desktop
 * Version 2.0
 * 6/2/2016
 */
public class Application_Manager extends Application {
    private static String ip_address_string = "";
    private static String hostName = "";
    private static List<String> processes = new ArrayList<String>();
    private static List<Text> processesText = new ArrayList<Text>();

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        pane.setTop(userInfo());
        pane.setBottom(processList());

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Varun's Application Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox userInfo() {
        HBox hBox = new HBox(15);
        hBox.setPadding(new Insets(15, 15, 15, 15));
        hBox.setStyle("-fx-background-color: gold");

        try {
            InetAddress ip_address = InetAddress.getLocalHost();
            ip_address_string = ip_address.getHostAddress();

            hostName = ip_address.getHostName();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }

        String one = "IP Address: " + ip_address_string;
        Text ip_text = new Text(one);

        String two = "Host Name: " + hostName;
        Text hostName_text = new Text(two);

        hBox.getChildren().add(ip_text);
        hBox.getChildren().add(hostName_text);

        return hBox;
    }

    private VBox processList() {
        VBox vBox = new VBox(15);
        vBox.setPadding(new Insets(15, 15, 15, 15));

        try {
            String line;
            Process p = Runtime.getRuntime().exec("ps -e");
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
        while ((line = input.readLine()) != null) {
            processes.add(line);
        }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }

        for(String str : processes) {
            Text tempText = new Text(str);
            processesText.add(tempText);
        }

        for(Text txt : processesText) {
            vBox.getChildren().add(txt);
        }

        return vBox;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
