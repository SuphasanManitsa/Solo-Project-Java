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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainSceneController implements Initializable {
    private static String nowFile = "";
    private static int cp = 0;
    private static TableView<Customer> Teble;
    private static int no = 1;
    private static int sum = 0;

    @FXML
    private Button BottonScene1, BottonScene2;

    @FXML
    void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("page1.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToScene2(ActionEvent event) throws IOException {
        MainSceneController.Teble = tableView;

        Parent root = FXMLLoader.load(getClass().getResource("page2.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void switchToScene3(ActionEvent event) throws IOException {
        MainSceneController.Teble = tableView;

        if (tableView.getItems().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No data found");
            alert.setContentText("Please add some data");
            alert.showAndWait();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("page3.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }


    @FXML
    void switchToScene4(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Notify");
        alert.setHeaderText("Sending order to cashier");
        alert.setContentText(
                "please wait We're making smoothies for you.");
        alert.showAndWait();
        
        MainSceneController.sum = 0;
        MainSceneController.no = 1;
        this.cp = 0;
        MainSceneController.Teble = null;
        Parent root = FXMLLoader.load(getClass().getResource("page4.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    // ---------------------------------------------------------------------------------------

    @FXML
    private TextField TotalShow;

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

    private String[] smoothie = { "Sakura Milk Tea Smoothie", "Caramel Cream Milk Frappe", "Strawberry Yogurt Smoothie",
            "Green Tea Milk Frappe", "Cocoa Coffee Caramel Frappe", "Pearl milk tea smoothie" };
    private String[] price = { "89", "89", "79", "99", "69", "89" };

    private String[] sweetnessLv = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
    private String[] amount = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

    // add botton
    @FXML
    void add_to_teble(ActionEvent event) {
        int p = -1;
        for (int i = 0; i < smoothie.length; i++) {
            if (smoothie[i].equals(ComboBox2.getValue())) {
                p = i;
            }
        }
        int c = 1;
        try {
            // Check combobox4
            if (Integer.parseInt(ComboBox4.getValue()) <= 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Unable to add item to table");
                alert.setContentText(
                        "An error occurred while trying to add the item to the table. (The order entered does not match the information in the combo box. please select again)");
                alert.showAndWait();
                c = 0;
            }
            // Check combobox3
            for (int i = 0; i < sweetnessLv.length; i++) {
                if (Integer.parseInt(ComboBox3.getValue()) <= 0 || Integer.parseInt(ComboBox3.getValue()) > Integer
                        .parseInt(sweetnessLv[sweetnessLv.length - 1])) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Unable to add item to table");
                    alert.setContentText(
                            "An error occurred while trying to add the item to the table. (The order entered does not match the information in the combo box. please select again)");
                    alert.showAndWait();
                    c = 0;
                    break;
                }
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unable to add item to table");
            alert.setContentText(
                    "An error occurred while trying to add the item to the table. (The order entered does not match the information in the combo box. please select again)");
            alert.showAndWait();
            c = 0;
        }
        if (c == 1) {
            if (p == -1 || ComboBox3.getValue() == null || ComboBox4.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Unable to add item to table");
                alert.setContentText(
                        "An error occurred while trying to add the item to the table. (The order entered does not match the information in the combo box. please select again)");
                alert.showAndWait();
            } else {
                try {
                    Customer customer = new Customer(
                            MainSceneController.no + (Integer.parseInt(ComboBox4.getValue()) * 0),
                            ComboBox2.getValue(), ComboBox3.getValue(), ComboBox4.getValue(),
                            Integer.parseInt(ComboBox4.getValue()) * Integer.parseInt(price[p]));
                    MainSceneController.sum += Integer.parseInt(ComboBox4.getValue()) * Integer.parseInt(price[p]);
                    // TotalShow.setText(String.valueOf(MainSceneController.sum));

                    ObservableList<Customer> customers = tableView.getItems();
                    customers.add(customer);
                    tableView.setItems(customers);

                    MainSceneController.no += 1;

                    MainSceneController.Teble = tableView;
                    System.out.println(tableView.getItems());
                    System.out.println(MainSceneController.Teble.getItems());
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Unable to add item to table");
                    alert.setContentText(
                            "An error occurred while trying to add the item to the table. (The order entered does not match the information in the combo box. please select again)");
                    alert.showAndWait();
                }
            }
        }
    }

    @FXML
    void removeTebleView(ActionEvent event) {
        try {
            MainSceneController.sum -= tableView.getItems().get(tableView.getItems().size() - 1).getPrice();
            // TotalShow.setText(String.valueOf(MainSceneController.sum));
            tableView.getItems().remove(tableView.getItems().get(tableView.getItems().size() - 1));
            MainSceneController.no -= 1;

            MainSceneController.Teble = tableView;
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
        try {
            MainSceneController.nowFile = location.getFile().split("/")[location.getFile().split("/").length - 1];
            if (MainSceneController.nowFile.equals("page1.fxml")) {
                this.cp += 1;
                System.out.println("now page 1");
                if(this.cp == 1){
                    MainSceneController.Teble = tableView;
                }
                else{
                    tableView.setItems(MainSceneController.Teble.getItems());
                }

                System.out.println(MainSceneController.Teble);
                System.out.println(tableView);
                

                ComboBox2.setItems(FXCollections.observableArrayList(smoothie));
                ComboBox3.setItems(FXCollections.observableArrayList(sweetnessLv));
                ComboBox4.setItems(FXCollections.observableArrayList(amount));

                order_no_Column.setCellValueFactory(new PropertyValueFactory<Customer, String>("OrderNumber"));
                order_name_Column.setCellValueFactory(new PropertyValueFactory<Customer, String>("Name"));
                sweetnessLv_Column.setCellValueFactory(new PropertyValueFactory<Customer, String>("Sweetness"));
                amount_Column.setCellValueFactory(new PropertyValueFactory<Customer, String>("Amount"));
                price_Column.setCellValueFactory(new PropertyValueFactory<Customer, String>("Price"));
            } else if (MainSceneController.nowFile.equals("page2.fxml")) {
                System.out.println("now page 2");

            } else if (MainSceneController.nowFile.equals("page3.fxml")) {
                System.out.println("now page 3");

                TotalShow.setText(String.valueOf(MainSceneController.sum));

            } else if (MainSceneController.nowFile.equals("page4.fxml")) {
                System.out.println("now page 4");

            }
        } catch (Exception e) {
            // System.out.println("switch page");
        }
    }
}