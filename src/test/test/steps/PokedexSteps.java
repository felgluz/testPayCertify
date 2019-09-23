package test.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.base.Base;
import framework.base.DriverContext;
import framework.config.Settings;
import framework.utilities.LogUtil;
import org.junit.Assert;
import test.pages.AdvancedSearchPage;
import test.pages.PokedexPage;
import framework.base.SharedDataBetweenPage;

public class PokedexSteps extends Base {

    private SharedDataBetweenPage sharedData;

    @Given("user access the pokedex page")
    public void userAccessThePokedexPage() {
        DriverContext.Browser.GoToUrl(Settings.AUT + "/pokedex");
        DriverContext.WaitForPageToLoad();
        Settings.Logs.Write("Pokedex page opened");

        DriverContext.AcceptCookies();
    }

    @Then("pokedex page is open")
    public void pokedexPageIsOpen() {
        CurrentPage = GetInstance(PokedexPage.class);
        Assert.assertTrue("Pokédex page is not open", CurrentPage.As(PokedexPage.class).IsOpen());
    }

    @When("user searches a pokemon by {string}")
    public void userSearchesAPokemonBy(String search) {
        sharedData = new SharedDataBetweenPage(search);
        sharedData.setStringData(search);

        CurrentPage.As(PokedexPage.class).EnterText(search);
        CurrentPage.As(PokedexPage.class).ClickBtnSearch();
    }

    @Then("the result {string} is displayed")
    public void theResultIsDisplayed(String result) {

        CurrentPage = GetInstance(PokedexPage.class).As(PokedexPage.class);
        switch (result) {
            case "Electrode":
                Assert.assertTrue("Pokemon not displayed",
                        CurrentPage.As(PokedexPage.class).GetPokemon(sharedData.getStringData()));
                break;
            case "Pikachu":
                Assert.assertTrue("Pokemon not displayed",
                        CurrentPage.As(PokedexPage.class).GetPokemon(sharedData.getStringData()));
                break;
            case "List of pokemons":
                Assert.assertTrue("Pokemons not displayed",
                        CurrentPage.As(PokedexPage.class).GetPokemon(sharedData.getStringData()));
                break;
            case "No pokemon matched":
                Assert.assertTrue("Pokemon not displayed",
                        CurrentPage.As(PokedexPage.class).AlertNoPokemonMatchedYourSearch());
                break;
        }
        Settings.Logs.Write("Result of pokemons");
    }

    @Given("user opens the advanced search")
    public void userOpensTheAdvancedSearch() {
        CurrentPage.As(PokedexPage.class).OpenAdvancedSearch();
        CurrentPage = GetInstance(AdvancedSearchPage.class);
    }

    @When("user clicks on surprise me button")
    public void userClicksOnSurpriseMeButton() {
        CurrentPage.As(PokedexPage.class).ClickBtnSurpriseMe();
    }

    @Then("a list with {int} pokemons is shown")
    public void aListWithPokemonsAreShown(int numberOfPokemons) {
        Assert.assertEquals(numberOfPokemons, CurrentPage.As(PokedexPage.class).GetNumberOfPokemons());
    }

    @And("click on search button in advanced search")
    public void clickOnSearchButtonInAdvancedSearch() {
        CurrentPage.As(AdvancedSearchPage.class).ClickBtnSearch();
    }

    @When("set number range to {string} and {string}")
    public void setNumberRangeToAnd(String minRange, String maxRange) {
        CurrentPage.As(AdvancedSearchPage.class).SetNumberRange(minRange, maxRange);
    }

    @When("change the sort filter to {string}")
    public void changeTheSortFilterTo(String resultFilter) {
        CurrentPage.As(PokedexPage.class).SetResultFilter(resultFilter);
    }

    @Then("a list of pokemons starting with word {string} is shown")
    public void aListOfPokemonsStartingWithLetterIsShown(String word) {
        Assert.assertTrue("Pokemons not displayed",
                CurrentPage.As(PokedexPage.class).GetPokemon(word));
    }
}
