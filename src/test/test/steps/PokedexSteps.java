package test.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.base.Base;
import framework.base.DriverContext;
import framework.config.Settings;
import org.junit.Assert;
import test.pages.AdvancedSearchPage;
import test.pages.PokedexPage;

public class PokedexSteps extends Base {

    @Given("user access the pokedex page")
    public void userAccessThePokedexPage()   {
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
        CurrentPage.As(PokedexPage.class).EnterText(search);
        CurrentPage.As(PokedexPage.class).ClickBtnSearch();
    }

    @Then("the result {string} is displayed")
    public void theResultIsDisplayed(String result) {

        switch (result) {
            case "Electrode":
                Assert.assertTrue("Pokemon not displayed",
                        CurrentPage.As(PokedexPage.class).GetPokemon(result));
                break;
            case "Pikachu":
                Assert.assertTrue("Pokemon not displayed",
                        CurrentPage.As(PokedexPage.class).GetPokemon("Pikachu"));
                break;
            case "p":
                CurrentPage.As(PokedexPage.class).GetPokemonList("p");
                break;
        }

    }

    @Given("user opens the advanced search")
    public void userOpensTheAdvancedSearch() {
        CurrentPage.As(PokedexPage.class).OpenAdvancedSearch();
    }

    @When("user clicks on height")
    public void userClicksOnHeight() {
        CurrentPage = GetInstance(AdvancedSearchPage.class);
        CurrentPage.As(AdvancedSearchPage.class).ClickOnHeight();
    }
}
