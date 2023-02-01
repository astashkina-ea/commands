import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class OtusTest extends BaseTest {

    private String login = "gitapa8240@bymercy.com";
    private String pass = "Test12345_";

    @Test
    public void firstTest() {
//        Открыть https://otus.ru
        driver.get("https://otus.ru");
//        Авторизоваться на сайте
        loginInOtus();
//        Войти в личный кабинет
        enterLK();
//        В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
        clearAndEnter(By.id("id_fname"), "Тестер");
        clearAndEnter(By.id("id_lname"), "Тестер");
        clearAndEnter(By.id("id_fname_latin"), "Тестер");
        clearAndEnter(By.id("id_lname_latin"), "Тестер");
//        Нажать сохранить
        driver
                .findElement(
                        By.cssSelector("body > div.body-wrapper > div > div.js-lk-cv > div.container.container-padding-bottom > div.container__row > div.container__col.container__col_9.container__col_md-8.container__col_sm-12.container__col_border-left.lk-rightbar.print-block.print-wide > div > form > div.container__row.container__row_gutter-24-gt-sm > div > div > button.button.button_md-4.button_blue.lk-cv-action-buttons__button.js-disable-on-submit"))
                .click();
//        Открыть https://otus.ru в “чистом браузере”
        driver.quit();
        driver = new ChromeDriver();
        driver.get("https://otus.ru");
//        Авторизоваться на сайте
        loginInOtus();
//        Войти в личный кабинет
        enterLK();
//        Проверить, что в разделе "О себе" отображаются указанные ранее данные
        Assertions.assertEquals("Тестер", driver.findElement(By.id("id_fname")).getAttribute("value"));
    }

    private void loginInOtus() {
        driver.findElement(By.cssSelector(".header3__button-sign-in")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        clearAndEnter(By.cssSelector("form.new-log-reg__form.js-login .js-email-input"), login);
        clearAndEnter(By.cssSelector("form.new-log-reg__form.js-login .js-psw-input"), pass);
        driver.findElement(By.cssSelector("form.new-log-reg__form.js-login button")).submit();
    }

    private void clearAndEnter(By by, String text) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }

    private void enterLK() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.get("https://otus.ru/lk/biography/personal/");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}