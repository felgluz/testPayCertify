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
    @FindBy(css = ".filter-toggle-span .text")
    WebElement btnShowAdvancedSearch;

    public boolean IsOpen() {
        return txtSearchInput.isDisplayed();
    }

    public void EnterText(String text) {
        txtSearchInput.clear();
        txtSearchInput.sendKeys(text);
    }

    public boolean GetPokemon(String pokemonName) {
        String locatePokemon = String.format("//div[@class='pokemon-info']//h5[contains(text(),'%s')]", pokemonName);
        DriverContext.WaitForAnimation(By.xpath("//div[@class='pokemon-info']//h5"));
        WebElement pokemon = DriverContext.Driver.findElement(By.xpath(locatePokemon));
        //todo make scroll down to pokemon located
        return pokemon.isDisplayed();
    }

    public boolean GetPokemonList(String pokemonName) {
        String locatePokemon = String.format("//div[@class='pokemon-info']//h5[contains(text(),'%s') or contains(text(),'%s')]",
                pokemonName.toUpperCase(), pokemonName.toLowerCase());
        DriverContext.WaitForAnimation(By.xpath("//div[@class='pokemon-info']//h5"));

        List<WebElement> pokemons = DriverContext.Driver.findElements(By.xpath(locatePokemon));
        return DriverContext.GetStatusOfEachElementVisibleInArray(pokemons);
    }

    public AdvancedSearchPage OpenAdvancedSearch() {
        DriverContext.WaitForElementVisible(btnShowAdvancedSearch);
        btnShowAdvancedSearch.click();
        return GetInstance(AdvancedSearchPage.class);
    }

    public void ClickBtnSearch() {
        btnSearch.click();
    }


}