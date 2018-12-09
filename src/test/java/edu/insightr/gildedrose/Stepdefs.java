package edu.insightr.gildedrose;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import javafx.collections.ObservableList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/*public class Stepdefs {
    private Inventory inventory;
    private ObservableList<Item> items;

    @Given("^I have a new inventory$")
    public void iHaveANewInventory() throws Throwable {
        inventory = new Inventory();
        items = new Item[inventory.getItems().size()];
        for(int i = 0; i < items.length; i++)
            items[i] = inventory.getItems()[i];
    }

    @Then("^the quality of the conjured item is (\\d+)$")
    public void theQualityOfTheConjuredIs(int conjuredQuality) throws Throwable {
        for(int i = 0 ; i < items.size(); i++)
            if(inventory.getItems()[i].getName().contains("Conjured"))
                assertThat(items[i].getQuality(), is(conjuredQuality));
    }

    @Then("^the quality of the sold item is (\\d+)$")
    public void QualitySellPassedItem(int soldItemQuality) throws Throwable {
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
    public void iUpdateTheInventory() throws Throwable {
        inventory.updateQuality();
        inventory.printInventory();
    }
}*/