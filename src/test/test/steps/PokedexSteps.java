package test.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.base.Base;
import framework.base.DriverContext;
import framework.config.Settings;
import framework.utilities.CucumberUtil;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import test.pages.AdvancedSearchPage;
import test.pages.PokedexPage;
import framework.base.SharedDataBetweenPage;

public class PokedexSteps extends Base {

    private SharedDataBetweenPage sharedDataBetweenPage;

    @Given("user access the pokedex page")
    public void userAccessThePokedexPage() {
        DriverContext.Browser.GoToUrl(Settings.AUT + "/pokedex");
        DriverContext.WaitForPageToLoad();
        DriverContext.AcceptCookies();
    }

    @Then("pokedex page is open")
    public void pokedexPageIsOpen() {
        CurrentPage = GetInstance(PokedexPage.class);
        Assert.assertTrue("Pokédex page is not open", CurrentPage.As(PokedexPage.class).IsOpen());
    }

    @When("user searches a pokemon by {string}")
    public void userSearchesAPokemonBy(String search) {
        sharedDataBetweenPage = new SharedDataBetweenPage(search);
        sharedDataBetweenPage.setStringData(search);

        CurrentPage.As(PokedexPage.class).EnterText(search);
        CurrentPage.As(PokedexPage.class).ClickBtnSearch();
    }

    @Then("the result {string} is displayed")
    public void theResultIsDisplayed(String result) {

        switch (result) {
            case "Electrode":
                Assert.assertTrue("Pokemon not displayed",
                        CurrentPage.As(PokedexPage.class).GetPokemon(sharedDataBetweenPage.getStringData()));
                break;
            case "Pikachu":
                Assert.assertTrue("Pokemon not displayed",
                        CurrentPage.As(PokedexPage.class).GetPokemon(sharedDataBetweenPage.getStringData()));
                break;
            case "List of pokemons":
                Assert.assertTrue("Pokemons not displayed",
                        CurrentPage.As(PokedexPage.class).GetPokemon(sharedDataBetweenPage.getStringData()));
                break;
            case "No pokemon matched":
                Assert.assertTrue("Pokemon not displayed",
                        CurrentPage.As(PokedexPage.class).AlertNoPokemonMatchedYourSearch());
                break;
        }
    }

    @Given("user opens the advanced search")
    public void userOpensTheAdvancedSearch() {
        CurrentPage.As(PokedexPage.class).OpenAdvancedSearch();
    }

    @When("enter the following details")
    public void enterTheFollowingDetails(DataTable data) {
        CucumberUtil.ConvertDataTableToDict(data);

        CurrentPage = GetInstance(AdvancedSearchPage.class);
        DriverContext.ScrollDownUntilTextVisibled("Hide Advanced Search");

        CurrentPage.As(AdvancedSearchPage.class).SelectDetails(
                CucumberUtil.GetCellValueWithRowIndex("Type", 1),
                CucumberUtil.GetCellValueWithRowIndex("Weakness", 1),
                CucumberUtil.GetCellValueWithRowIndex("Ability", 1),
                CucumberUtil.GetCellValueWithRowIndex("Height", 1)
        );
    }

    @And("click on search button in advanced search")
    public void clickOnSearchButtonInAdvancedSearch() {
        CurrentPage = GetInstance(PokedexPage.class);
        CurrentPage.As(PokedexPage.class).ClickBtnSearch();
    }

    @Then("a list with {int} pokemons are shown")
    public void aListWithPokemonsAreShown(int numberOfPokemons) {
        Assert.assertEquals(CurrentPage.As(PokedexPage.class).GetNumberOfPokemons(), numberOfPokemons);
    }
}
