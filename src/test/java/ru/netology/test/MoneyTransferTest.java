package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {


    @Test
    void shouldTransferFromSecondToFirst() {
        String amount = "5000";
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        val verificationCode = DataHelper.getVerificationCodeFor();
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceOfFirstCardBefore = DashboardPage.getCurrentBalanceOfFirstCard();
        int balanceOfSecondCardBefore = DashboardPage.getCurrentBalanceOfSecondCard();
        val transferPage = dashboardPage.firstCard();
        val cardInfo = DataHelper.getSecondCardInfo();
        transferPage.transferMoney(cardInfo, amount);
        int balanceAfterTransferFirstCard = DataHelper.balanceUp(balanceOfFirstCardBefore, Integer.parseInt(amount));
        int balanceAfterTransferSecondCard = DataHelper.balanceDown(balanceOfSecondCardBefore, Integer.parseInt(amount));
        int balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
        int balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();

        assertEquals(balanceAfterTransferFirstCard, balanceOfFirstCardAfter);
        assertEquals(balanceAfterTransferSecondCard, balanceOfSecondCardAfter);
    }

    @Test
    void shouldTransferFromFirstToSecond() {
        String amount = "4000";
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        val verificationCode = DataHelper.getVerificationCodeFor();
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceOfFirstCardBefore = DashboardPage.getCurrentBalanceOfFirstCard();
        int balanceOfSecondCardBefore = DashboardPage.getCurrentBalanceOfSecondCard();
        val transferPageSecond = dashboardPage.secondCard();
        val cardInfo = DataHelper.getFirstCardInfo();
        transferPageSecond.transferMoney(cardInfo, amount);
        int balanceAfterTransferFirstCard = DataHelper.balanceDown(balanceOfFirstCardBefore, Integer.parseInt(amount));
        int balanceAfterTransferSecondCard = DataHelper.balanceUp(balanceOfSecondCardBefore, Integer.parseInt(amount));
        int balanceOfFirstCardAfter = DashboardPage.getCurrentBalanceOfFirstCard();
        int balanceOfSecondCardAfter = DashboardPage.getCurrentBalanceOfSecondCard();

        assertEquals(balanceAfterTransferFirstCard, balanceOfFirstCardAfter);
        assertEquals(balanceAfterTransferSecondCard, balanceOfSecondCardAfter);
    }
}
