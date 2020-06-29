package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    SelenideElement login = $("[data-test-id=login] input");
    SelenideElement pass = $("[data-test-id=password] input");
    SelenideElement button = $("[data-test-id=action-login]");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        login.setValue(info.getLogin());
        pass.setValue(info.getPassword());
        button.click();
        return new VerificationPage();
    }
}