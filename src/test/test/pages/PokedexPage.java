package test.pages;

import framework.base.BasePage;
import framework.base.DriverContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PokedexPage extends BasePage {

    public PokedexPage(String pokemon) {
        this.pokemon = pokemon;
    }

    @FindBy(css = "//h5[.='%s']")

    private String pokemon;
    private WebElement txtSearchInput = DriverContext.Driver.findElement(By.id("searchInput"));
    private List<WebElement> pokemonInfo =
            DriverContext.Driver.findElements(By.xpath(String.format("//h5[.='%s']", pokemon)));

    public boolean IsOpen(){
        return txtSearchInput.isDisplayed();
    }

    public void EnterText(String text){
        txtSearchInput.clear();
        txtSearchInput.sendKeys(text);
    }

    public boolean GetPokemonDisplayedInList(String pokemon){
        pokemon = this.pokemon;
        return DriverContext.GetElementDisplayedInArray(pokemonInfo).isDisplayed();
    }
}