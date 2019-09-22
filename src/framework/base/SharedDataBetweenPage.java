package framework.base;

public class SharedDataBetweenPage extends BasePage {
    private String stringData;

    public SharedDataBetweenPage(String stringData) {
        this.stringData = stringData;
    }

    public String getStringData() {
        return stringData;
    }

    public void setStringData(String stringData) {
        this.stringData = stringData;
    }
}
