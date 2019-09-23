Feature: Pokédex
  As a pokedex user
  I want to search a pokemon
  So I can see it's details and catch em' all!

  Background: Access pokedex page
    Given user access the pokedex page
    Then pokedex page is open

  Scenario Outline: Search pokemon by given values
    When user searches a pokemon by "<search>"
    Then the result "<result>" is displayed
    Examples:
      | search             | result             |
      | 0101               | Electrode          |
      | Pikachu            | Pikachu            |
      | p                  | List of pokemons   |
      |                    | List of pokemons   |
      | Fire               | No pokemon matched |
      | ///)(+             | No pokemon matched |
      | 987897898978979489 | No pokemon matched |
      | zzxcxzjkhasjk      | No pokemon matched |

  Scenario: Change number range
    Given user opens the advanced search
    When set number range to "-1" and "0"
    And click on search button in advanced search
    Then the result "No pokemon matched" is displayed

  Scenario: Click on surprise me button
    When user clicks on surprise me button
    Then a list with 12 pokemons is shown

  Scenario: Change the sort filter
    When change the sort filter to "Z-A"
    Then a list of pokemons starting with word "Z" is shown
