/*package controller;

import edu.insightr.gildedrose.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {
    Inventory inv;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //listInv = new ListView<Item>();
        inv = new Inventory();
        listInv.setItems(FXCollections.observableArrayList(inv.getItems()));
        ObservableList<Item> items = FXCollections.observableArrayList();
        listInv.setItems(items);
    }


    @FXML
    ListView<Item> listInv;
    @FXML
    TableView<Item> table;

}
*/