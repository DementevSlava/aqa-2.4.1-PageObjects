package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountField = $("[data-test-id=\"amount\"] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public void transferMoney(DataHelper.CardInfo CardInfo, String amount) {
        amountField.setValue(amount);
        fromField.setValue(CardInfo.getCardNumber());
        transferButton.click();
    }
}
