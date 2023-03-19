import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainSceneController implements Initializable {
    private static int no = 1;
    private Stage primaryStage;

    @FXML
    private Button BottonScene1,BottonScene2;

    @FXML
    void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/page1.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToScene2(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/page2.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
// }
    // ---------------------------------------------------------------------------------------

    

    @FXML
    private TableView<Customer> tableView;

    @FXML
    private TableColumn<Customer, String> order_no_Column;

    @FXML
    private TableColumn<Customer, String> order_name_Column;

    @FXML
    private TableColumn<Customer, String> sweetnessLv_Column;

    @FXML
    private TableColumn<Customer, String> amount_Column;

    @FXML
    private TableColumn<Customer, String> price_Column;

    @FXML
    private ComboBox<String> ComboBox2;
    @FXML
    private ComboBox<String> ComboBox3;
    @FXML
    private ComboBox<String> ComboBox4;

    private String[] smoothie = { "banana", "carrot", "milk check" };
    private String[] sweetnessLv = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
    private String[] amount = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
    private String[] price = { "20", "25", "30" };

    // add botton
    @FXML
    void add_to_teble(ActionEvent event) {
        int p = -1;
        for (int i = 0; i < smoothie.length; i++) {
            if (smoothie[i].equals(ComboBox2.getValue())) {
                p = i;
            }
        }

        if (p == -1 || ComboBox3.getValue() == null || ComboBox4.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to add item to table");
            alert.setContentText("An error occurred while trying to add the item to the table. (The order entered does not match the information in the combo box. please select again)");
            alert.showAndWait();
        } else {
            try {
                Customer customer = new Customer(MainSceneController.no + (Integer.parseInt(ComboBox4.getValue()) * 0),
                    ComboBox2.getValue(), ComboBox3.getValue(), ComboBox4.getValue(),
                    Integer.parseInt(ComboBox4.getValue()) * Integer.parseInt(price[p]));
                ObservableList<Customer> customers = tableView.getItems();
                customers.add(customer);
                tableView.setItems(customers);
                MainSceneController.no += 1;
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Unable to add item to table");
                alert.setContentText("An error occurred while trying to add the item to the table. (The order entered does not match the information in the combo box. please select again)");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void removeTebleView(ActionEvent event) {
        try {
            tableView.getItems().remove(tableView.getItems().get(tableView.getItems().size() - 1));
            MainSceneController.no -= 1;
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to remove item");
            alert.setContentText("An error occurred while trying to remove the item from the table view.");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ComboBox2.setItems(FXCollections.observableArrayList(smoothie));
        ComboBox3.setItems(FXCollections.observableArrayList(sweetnessLv));
        ComboBox4.setItems(FXCollections.observableArrayList(amount));

        order_no_Column.setCellValueFactory(new PropertyValueFactory<Customer, String>("OrderNumber"));
        order_name_Column.setCellValueFactory(new PropertyValueFactory<Customer, String>("Name"));
        sweetnessLv_Column.setCellValueFactory(new PropertyValueFactory<Customer, String>("Sweetness"));
        amount_Column.setCellValueFactory(new PropertyValueFactory<Customer, String>("Amount"));
        price_Column.setCellValueFactory(new PropertyValueFactory<Customer, String>("Price"));

    }
}