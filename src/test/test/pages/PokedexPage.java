package test.pages;

import framework.base.BasePage;
import framework.base.DriverContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PokedexPage extends BasePage {

    public PokedexPage() {

    }

    @FindBy(id = "searchInput")
    WebElement txtSearchInput;
    @FindBy(id = "search")
    WebElement btnSearch;
    @FindBy(css = ".filterOptions-toggle-span .text")
    WebElement btnShowAdvancedSearch;
    @FindBy(css = "[class='alert alert-error']")
    WebElement alertNoPokemonMatched;
    @FindBy(css = "[class='results'] [class='animating']")
    List<WebElement> resultOfPokemons;
    @FindBy(id = "shuffle")
    WebElement btnSurpriseMe;
    @FindBy(xpath = "//div[@class='pokemon-info']//h5")
    By animation;
    @FindBy(xpath = "//*[@class='custom-select-menu']/ul")
    WebElement filterOptions;

    public boolean IsOpen() {
        return txtSearchInput.isDisplayed();
    }

    public void EnterText(String text) {
        txtSearchInput.clear();
        txtSearchInput.sendKeys(text);
    }

    public boolean GetPokemon(String pokemonName) {
        String locatePokemon = String.format("//div[@class='pokemon-info']//h5[contains(text(),'%s') or contains(text(),'%s')]",
                pokemonName.toUpperCase(), pokemonName.toLowerCase());
        DriverContext.WaitForAnimation(animation);

        List<WebElement> pokemons = DriverContext.Driver.findElements(By.xpath(locatePokemon));
        return DriverContext.GetAllElementsVisibleInArray(pokemons);
    }

    public AdvancedSearchPage OpenAdvancedSearch() {
        DriverContext.WaitForElementVisible(btnShowAdvancedSearch);
        btnShowAdvancedSearch.click();
        return GetInstance(AdvancedSearchPage.class);
    }

    public void ClickBtnSearch() {
        DriverContext.ScrollDownToElementVisibled(btnSearch);
        DriverContext.WaitForElementVisible(btnSearch);
        btnSearch.click();
    }

    public boolean AlertNoPokemonMatchedYourSearch() {
        return alertNoPokemonMatched.isDisplayed();
    }

    public int GetNumberOfPokemons() {
        DriverContext.WaitForAnimation(animation);
        return resultOfPokemons.size();
    }

    public void ClickBtnSurpriseMe() {
        DriverContext.WaitForElementVisible(btnSurpriseMe);
        btnSurpriseMe.click();
    }

    public void SetResultFilter(String resultFilter) {
        if (resultFilter.contains("Z-A")) {
            DriverContext.GetElementWithinOptions(filterOptions, By.tagName("li"), "Z-A");
        }
    }
}