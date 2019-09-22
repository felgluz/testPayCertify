package test.seleniumTest;

import framework.base.DriverContext;
import org.junit.Test;
import org.openqa.selenium.By;
import test.steps.TestInitialize;

public class PokedexTest extends TestInitialize {

    //todo make the selenium test
    @Test
    public void Pokedex(){
        DriverContext.Driver.findElement(By.cssSelector(""));
    }

}
