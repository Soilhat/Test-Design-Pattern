package edu.insightr.gildedrose;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.insightr.gildedrose.Model.*;
import edu.insightr.gildedrose.controller.InventoryController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class Stepdefs {
    private Inventory inventory;
    private InventoryController GUI;
    private ObservableList<Item> items;

    @Given("I fetch my items")
    public void Fetching(){
        GUI = new InventoryController();
        GUI.inv = new Inventory("gildedRose.json");
        inventory = GUI.inv;
        GUI.pie = new PieChart();
    }

    @Then("The number of items corespond to the Piechart")
    public void CountPiechart(){
        GUI.piechartFunction();
        ObservableList<PieChart.Data> datas = GUI.pie.getData();
        for(PieChart.Data i : datas)
        {
            switch(i.getName())
            {
                case "Aged_brie":
                    assertThat(inventory.countItem()[0], is(i.getPieValue()));
                    break;
                case "Backstage_Passes":
                    assertThat(inventory.countItem()[2], is(i.getPieValue()));
                    break;
                case "Conjured_Mana_Cake":
                    assertThat(inventory.countItem()[3], is(i.getPieValue()));
                    break;
                case "Dexterity_Vest":
                    assertThat(inventory.countItem()[4], is(i.getPieValue()));
                    break;
                case "Elixir_of_the_Mongoose":
                    assertThat(inventory.countItem()[5], is(i.getPieValue()));
                    break;
                case "Sulfuras":
                    assertThat(inventory.countItem()[1], is(i.getPieValue()));
                    break;
            }
        }
    }

    @Given("^I have a new inventory$")
    public void iHaveANewInventory(){
        items = FXCollections.observableArrayList();
        items =  FXCollections.observableArrayList(
                new Dexterity_Vest(),
                new Aged_Brie(),
                new Elixir_of_the_Mongoose(),
                new Sulfuras(),
                new Backstage_Passes(),
                new Conjured_Mana_Cake())
        ;
        inventory = new Inventory(items);
    }

    @Given("I read a Json File")
    public void JsonInventory()
    {
        inventory = new Inventory();
        inventory.setItems(inventory.ReaderFileJson("gildedRosebis.json"));
    }

    @Then("my inventory is filled")
    public void TestInventoryFilled(){
        for(Item i : inventory.getItems()){
            System.out.println(i);
        }
    }

    @Then("^the quality of the conjured item is (\\d+)$")
    public void theQualityOfTheConjuredIs(int conjuredQuality){
        for(int i = 0 ; i < items.size(); i++)
            if(inventory.getItems().get(i).getName().contains("Conjured"))
                assertThat(items.get(i).getQuality(), is(conjuredQuality));
    }

    @Then("^the quality of the sold item is (\\d+)$")
    public void QualitySellPassedItem(int soldItemQuality){
        for(Item item : inventory.getItems())
            if(item.getSellIn() == 0 && !item.getName().contains("Sulfuras"))
                assertThat(item.getQuality(), is(soldItemQuality));
    }

    @Then("^the quality of the item is positive$")
    public void PositiveQuality()
    {
        for (Item item : inventory.getItems()) {
            assertTrue(item.getQuality() >= 0);
        }
    }

    @Then("^the quality of the Aged Brie item is (\\d+)$")
    public void AgedBrieQuality(int quality){
        for(Item item : inventory.getItems()){
            if(item.getName().contains("Aged Brie"))
            {
                assertThat(item.getQuality(), is(quality));
            }
        }
    }

    @Then("^the quality of the item is less than (\\d+)$")
    public void QualityMax(int maxquality)
    {
        for(Item item : inventory.getItems())
        {
            if(!item.getName().contains("Sulfuras"))
            {
                assertTrue(item.getQuality() <= maxquality);
            }
        }
    }

    @Then("^the quality of the sulfuras item is (\\d+)$")
    public void SulfurasQuality(int quality)
    {
        for(Item item : inventory.getItems())
        {
            if(item.getName().contains("Sulfuras")){
                assertThat(item.getQuality(), is(quality));
            }
        }
    }

    @Then("^the quality of the backstage item is (\\d+)$")
    public void BackstageQuality(int quality)
    {
        for(Item item : inventory.getItems())
        {
            if(item.getName().contains("Backstage")){
                assertThat(item.getQuality(), is(quality));
            }
        }
    }
    @When("^I update the inventory$")
    public void iUpdateTheInventory(){
        inventory.updateQuality();
        inventory.printInventory();

    }
}