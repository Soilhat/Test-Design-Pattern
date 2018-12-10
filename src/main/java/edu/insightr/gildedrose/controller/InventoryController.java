package edu.insightr.gildedrose.controller;

import edu.insightr.gildedrose.Model.Inventory;
import edu.insightr.gildedrose.Model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {
    Inventory inv;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inv = new Inventory();
        inv.setItems(inv.ReaderFileJson("gildedRosebis.json"));
        listInv.setItems(FXCollections.observableArrayList(inv.getItems()));
        ObservableList<Item> items = FXCollections.observableArrayList();
        listInv.setItems(items);
    }


    @FXML
    ListView<Item> listInv;

}
