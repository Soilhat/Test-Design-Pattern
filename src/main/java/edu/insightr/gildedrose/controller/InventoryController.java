package edu.insightr.gildedrose.controller;

import edu.insightr.gildedrose.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class InventoryController implements Initializable {

    Inventory inv;
    @FXML
    TableView<Item> table;
    @FXML
    PieChart pie;
    @FXML
    BarChart barChartSellIn;
    @FXML
    CategoryAxis category;
    @FXML
    NumberAxis number;
    @FXML
    TextField newType;
    @FXML
    TextField newName;
    @FXML
    TextField newSellIn;
    @FXML
    TextField newQuality;
    @FXML
    Button add;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inv = new Inventory();
        fetchItem();
    }

    public void fetchItem(){
        TableColumn typeCol = new TableColumn("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<Item,String>("type"));
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Item,String>("name"));
        TableColumn sellInCol = new TableColumn("SellIn");
        sellInCol.setCellValueFactory(new PropertyValueFactory<Item,String>("sellIn"));
        TableColumn qualityCol = new TableColumn("Quality");
        qualityCol.setCellValueFactory(new PropertyValueFactory<Item,String>("quality"));
        table.setItems(inv.getItems());
        table.getColumns().setAll(typeCol, nameCol, sellInCol, qualityCol);
        piechartFunction();
        barchartSellIn();
    }

    public void piechartFunction(){

        PieChart.Data s0 = new PieChart.Data("Aged Brie", inv.countItem()[0]);
        PieChart.Data s1 =  new PieChart.Data("Sulfuras", inv.countItem()[1]);
        PieChart.Data s2 = new PieChart.Data("Backstage Passes", inv.countItem()[2]);
        PieChart.Data s3 = new PieChart.Data("Conjured Mana Cake", inv.countItem()[3]);
        PieChart.Data s4 =  new PieChart.Data("Dexterity Vest", inv.countItem()[4]);
        PieChart.Data s5 =  new PieChart.Data("Elixir of the Mongose", inv.countItem()[5]);
        pie.setData(FXCollections.observableArrayList(s0, s1, s2, s3, s4, s5));

        pie.setTitle("Inventory");
    }

    public void barchartSellIn(){
        XYChart.Series set1 = new XYChart.Series<>();
        Map<String, Integer> dico = inv.countSellIn();
        Set<Map.Entry<String, Integer>> setHm = dico.entrySet();
        Iterator<Map.Entry<String, Integer>> it = setHm.iterator();
        ObservableList<XYChart.Data> data = FXCollections.observableArrayList();
        while(it.hasNext()){
            Map.Entry<String, Integer> e = it.next();
            data.add(new XYChart.Data(e.getKey(), e.getValue()));
        }
        set1.setData(data);
        if(set1 != null)barChartSellIn.setData(FXCollections.observableArrayList(set1));
    }


    public void UpdateButton(){
        inv.updateQuality();
        fetchItem();

    }

    public void loadFileButton() {
        inv = new Inventory("gildedRosebis.json");
        fetchItem();
    }
    public void addButton(){
        Item item = null;

        if ((newQuality.getText())!= null && newSellIn.getText()!= null && newName.getText()!= null) {

            try {


                switch (newType.getText()) {
                    case "Aged_Brie":
                        item = new Aged_Brie(newName.getText(), Integer.parseInt(newSellIn.getText()), Integer.parseInt(newQuality.getText()));
                        break;
                    case "Backstage_Passes":
                        item = new Backstage_Passes(newName.getText(), Integer.parseInt(newSellIn.getText()), Integer.parseInt(newQuality.getText()));
                        break;
                    case "Conjured_Mana_Cake":
                        item = new Conjured_Mana_Cake(newName.getText(), Integer.parseInt(newSellIn.getText()), Integer.parseInt(newQuality.getText()));
                        break;
                    case "Dexterity_Vest":
                        item = new Dexterity_Vest(newName.getText(), Integer.parseInt(newSellIn.getText()), Integer.parseInt(newQuality.getText()));
                        break;
                    case "Elixir_of_the_Mongoose":
                        item = new Elixir_of_the_Mongoose(newName.getText(), Integer.parseInt(newSellIn.getText()), Integer.parseInt(newQuality.getText()));
                        break;
                    case "Sulfuras":
                        item = new Sulfuras(newName.getText(), Integer.parseInt(newSellIn.getText()), Integer.parseInt(newQuality.getText()));
                        break;

                }
                inv.getItems().add(item);
            }
            catch( NumberFormatException e){}
        }
        else System.out.println("error");




        newType.clear();
        newName.clear();
        newSellIn.clear();
        newQuality.clear();
        piechartFunction();
        barchartSellIn();
    }
}
