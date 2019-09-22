Feature: Pokédex
  As a pokedex user,
  I want to search a pokemon,
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
      | /                  | No pokemon matched |
      | 987897898978979489 | No pokemon matched |
      | zzxcxzjkhasjk      | No pokemon matched |

  @wip
  Scenario: Open advanced search
    Given user opens the advanced search
    When enter the following details
      | Type | Weakness | Ability | Height          |
      | Fire | Water    | Blaze   | Medium and tall |
    And click on search button in advanced search
    Then a list with 6 pokemons are shown

  Scenario: Reset the filter

  Scenario: Change number range

