package edu.insightr.gildedrose;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.insightr.gildedrose.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class Stepdefs {
    private Inventory inventory;
    private ObservableList<Item> items;
    private Item item;

    @Given("^I fetch my items$")
    public void Fetching(){
        inventory = new Inventory("gildedRosebis.json");
    }

    @And("^I sell an item$")
    public void selling(){
        item = items.get(0);
        inventory.SellItem(item);
    }

    @Then("^my item is no longer in my inventory$")
    public void Deleted(){
        assertFalse(inventory.getItems().contains(item));
    }

    @And("^the item is in the sold list$")
    public void SoldList(){
        assertTrue(inventory.getSoldItems().contains(item));
    }

    @Then("^the number of item with SellIn date 10 is (\\d+)$")
    public void CountSellIn(int number){assertThat(inventory.countSellIn().get("10"), is(number));}

    @Then("^The number of items correspond to the PieChart$")
    public void CountPiechart(){
        assertThat(inventory.countItem()[0], is(2));
    }

    @Then("^The number of item created the 2018-12-12 is (\\d+)$")
    public void  CountCreation(int number) { assertThat(inventory.itemCountPerDate().get("2018-12-12"), is(number));}

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
        assertThat(inventory.getItems().get(0), is(new Sulfuras("sulfuras1", 10, 56, "2018-12-12")));
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