Feature: Tests Inventory

  #JSON File Reading into list of Item in the Inventory
  Scenario: Json File
    Given I read a Json File
    Then my inventory is correctly filled

  #Once the sell by date has passed, Quality degrades twice as fast
  Scenario: sell passed
    Given I have a new inventory
    Then the quality of the sold item is 20
    When I update the inventory
    Then the quality of the sold item is 18

  #The Quality of an item is never negative
  Scenario: positive quality
    Given I have a new inventory
    Then the quality of the item is positive
    When I update the inventory
    Then the quality of the item is positive

  #"Aged Brie" actually increases in Quality the older it gets
  Scenario: aged brie item update
    Given I have a new inventory
    Then the quality of the Aged Brie item is 0
    When I update the inventory
    Then the quality of the Aged Brie item is 1

  #The Quality of an item is never more than 50
  Scenario: Quality max 50
    Given I have a new inventory
    Then the quality of the item is less than 50
    When I update the inventory
    Then the quality of the item is less than 50

  #"Sulfuras", being a legendary item, never has to be sold or decreases in Quality
  Scenario: legendary item
    Given I have a new inventory
    Then the quality of the sulfuras item is 80
    When I update the inventory
    Then the quality of the sulfuras item is 80

  #"Backstage passes", like aged brie, increases in Quality as it's SellIn value approaches; Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but Quality drops to 0 after the concert
  Scenario: backstage passes
    Given I have a new inventory
    Then the quality of the backstage item is 20
    When I update the inventory
    Then the quality of the backstage item is 22

  #"Conjured" items degrade in Quality twice as fast as normal items
  Scenario: conjured item update
    Given I have a new inventory
    Then the quality of the conjured item is 6
    When I update the inventory
    Then the quality of the conjured item is 4