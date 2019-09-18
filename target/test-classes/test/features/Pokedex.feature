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
      #| Pikachu            | Pikachu            |
      #| p                  | list of pokemons   |
      #|                    | list of pokemons   |
      #| Fire               | no pokemon matched |
      #| /                  | no pokemon matched |
      #| 987897898978979489 | no pokemon matched |
      #| zzxcxzjkhasjk      | no pokemon matched |
