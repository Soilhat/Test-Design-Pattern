package edu.insightr.gildedrose.controller;

import edu.insightr.gildedrose.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class InventoryController implements Initializable {

    private Inventory inv;
    @FXML
    TableView<Item> table;
    @FXML
    PieChart pie;
    @FXML
    BarChart BarChartItems;
    @FXML
    BarChart barChartSellIn;
    @FXML
    ChoiceBox<String> newType;
    @FXML
    TextField newName;
    @FXML
    TextField newSellIn;
    @FXML
    TextField newQuality;
    @FXML
    CategoryAxis xAxisC;
    @FXML
    CategoryAxis xAxisS;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inv = new Inventory();
        fetchItem();
        ObservableList<String> types = FXCollections.observableArrayList(
                "Aged_Brie", "Backstage_Passes", "Conjured_Mana_Cake", "Dexterity_Vest", "Elixir_of_the_Mongoose", "Sulfuras"
        );
        newType.setItems(types);

    }

    private void fetchItem(){
        TableColumn<Item, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<Item, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Item, String> sellInCol = new TableColumn<>("SellIn");
        sellInCol.setCellValueFactory(new PropertyValueFactory<>("sellIn"));
        TableColumn<Item, String> qualityCol = new TableColumn<>("Quality");
        qualityCol.setCellValueFactory(new PropertyValueFactory<>("quality"));
        TableColumn<Item, String> creationCol = new TableColumn<>("Creation date");
        creationCol.setCellValueFactory(new PropertyValueFactory<>("creation_date"));
        table.setItems(inv.getItems());
        //noinspection unchecked
        table.getColumns().setAll(typeCol, nameCol, sellInCol, qualityCol, creationCol);
    }

    private void piechartFunction(){
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Aged Brie", inv.countItem()[0]),
                        new PieChart.Data("Sulfuras", inv.countItem()[1]),
                        new PieChart.Data("Pass", inv.countItem()[2]),
                        new PieChart.Data("Conjured", inv.countItem()[3]),
                        new PieChart.Data("Vest", inv.countItem()[4]),
                        new PieChart.Data("Elixir", inv.countItem()[5]));
        pie.setData(pieChartData);
        pie.setTitle("Inventory");
    }

    @SuppressWarnings({"unchecked"})
    private void barchartFunction(){
        XYChart.Series set1 = new XYChart.Series<>();
        Map<String, Integer> dico = inv.itemCountPerDate();
        Set<Map.Entry<String, Integer>> setHm = dico.entrySet();
        Iterator<Map.Entry<String, Integer>> it = setHm.iterator();
        ObservableList<XYChart.Data> data = FXCollections.observableArrayList();
        ObservableList<String> xAxis = FXCollections.observableArrayList();
        while(it.hasNext()){
            Map.Entry<String, Integer> e = it.next();
            data.add(new XYChart.Data(e.getKey(), e.getValue()));
            xAxis.add(e.getKey());
        }
        set1.setData(data);
        xAxisC.setCategories(xAxis);
        BarChartItems.setData(FXCollections.observableArrayList(set1));
    }

    @SuppressWarnings({"unchecked"})
    private void barchartSellIn(){
        XYChart.Series set1 = new XYChart.Series<>();
        Map<String, Integer> dico = inv.countSellIn();
        Set<Map.Entry<String, Integer>> setHm = dico.entrySet();
        Iterator<Map.Entry<String, Integer>> it = setHm.iterator();
        ObservableList<XYChart.Data> data = FXCollections.observableArrayList();
        ObservableList<String> xAxis = FXCollections.observableArrayList();
        while(it.hasNext()){
            Map.Entry<String, Integer> e = it.next();
            data.add(new XYChart.Data(e.getKey(), e.getValue()));
            xAxis.add(e.getKey());
        }
        set1.setData(data);
        xAxisS.setCategories(xAxis);
        barChartSellIn.setData(FXCollections.observableArrayList(set1));
    }


    public void UpdateButton(){
        inv.updateQuality();
        fetchItem();
        barchartSellIn();

    }

    public void loadFileButton(){
        inv.ChargeItems("gildedRose.json");
        barchartFunction();
        barchartSellIn();
        piechartFunction();
    }
    public void loadFileButtonbis(){
        inv.ChargeItems("gildedRosebis.json");
        barchartFunction();
        barchartSellIn();
        piechartFunction();
    }

    public void sellButtonCliked(){
        Item toSell = table.getSelectionModel().getSelectedItem();
        if(toSell != null) inv.SellItem(toSell);
        barchartFunction();
        barchartSellIn();
        piechartFunction();
    }
    public void addButton(){
        Item item = null;
        if ((newQuality.getText())!= null && newSellIn.getText()!= null && newName.getText()!= null && newType.getValue() != null ) {

            try {


                switch (newType.getValue()) {
                    case "Aged_Brie":
                        item = new Aged_Brie(newName.getText(), Integer.parseInt(newSellIn.getText()), Integer.parseInt(newQuality.getText()), LocalDate.now().toString());
                        break;
                    case "Backstage_Passes":
                        item = new Backstage_Passes(newName.getText(), Integer.parseInt(newSellIn.getText()), Integer.parseInt(newQuality.getText()),  LocalDate.now().toString());
                        break;
                    case "Conjured_Mana_Cake":
                        item = new Conjured_Mana_Cake(newName.getText(), Integer.parseInt(newSellIn.getText()), Integer.parseInt(newQuality.getText()),  LocalDate.now().toString());
                        break;
                    case "Dexterity_Vest":
                        item = new Dexterity_Vest(newName.getText(), Integer.parseInt(newSellIn.getText()), Integer.parseInt(newQuality.getText()), LocalDate.now().toString());
                        break;
                    case "Elixir_of_the_Mongoose":
                        item = new Elixir_of_the_Mongoose(newName.getText(), Integer.parseInt(newSellIn.getText()), Integer.parseInt(newQuality.getText()), LocalDate.now().toString());
                        break;
                    case "Sulfuras":
                        item = new Sulfuras(newName.getText(), Integer.parseInt(newSellIn.getText()), Integer.parseInt(newQuality.getText()), LocalDate.now().toString());
                        break;

                }
                if(!inv.getItems().contains(item)){
                  inv.getItems().add(item);
                  inv.getBoughtItems().add(item);
                  newName.clear();
                  newSellIn.clear();
                  newQuality.clear();
                  piechartFunction();
                  barchartSellIn();
                  barchartFunction();
                }
            }
            catch( NumberFormatException e){
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Number Format Exception");
                errorAlert.setContentText("You should enter a number for Sellin and Quality");
                errorAlert.showAndWait();
            }
        }
        else {

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Null Exception");
            errorAlert.setContentText("You should fill all the input");
            errorAlert.showAndWait();
        }
    }
}