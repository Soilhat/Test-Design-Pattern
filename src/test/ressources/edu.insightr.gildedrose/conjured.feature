Feature: Tests Inventory

  #Buy an item
  Scenario: Buying of an item
    Given I have a new inventory
    And I buy an item
    Then the item is in my inventory
    And the item is in the bought list

  #Sell an item
  Scenario: Selling of an item
    Given I have a new inventory
    And I sell an item
    Then my item is no longer in my inventory
    And the item is in the sold list

  #BarChert SellIn
  Scenario: BarChart Number of items by SellIn
    Given I fetch my items
    Then the number of item with SellIn date 10 is 2

  #BarChart per creation Date
  Scenario: BarChart Creation Date
    Given I fetch my items
    Then The number of item created the 2018-12-12 is 5

  #PieChart Analyse
  Scenario: PieChart
    Given I fetch my items
    Then The number of items correspond to the PieChart

  #JSON File Reading into list of Item in the Inventory
  Scenario: Json File
    Given I read a Json File
    Then my inventory is filled

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