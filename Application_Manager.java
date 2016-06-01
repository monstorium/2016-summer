import java.net.InetAddress;
import java.net.UnknownHostException;
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
import javafx.scene.text.Text;

/**
 * Varun's Application Manager for Desktop
 * Version 1.0
 * 6/1/2016
 */
public class Application_Manager extends Application {
	private static String ip_address_string = "";
	private static String hostName = "";

	@Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        pane.setTop(userInfo());

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

	public static void main(String[] args) {
		Application.launch(args);
	}

}
