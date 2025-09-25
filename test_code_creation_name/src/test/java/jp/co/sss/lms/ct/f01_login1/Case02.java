package jp.co.sss.lms.ct.f01_login1;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.Assert.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト ログイン機能①
 * ケース02
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース02 受講生 ログイン 認証失敗")
public class Case02 {

	public static WebDriver webDriver;

	/** 前処理 */
	@BeforeAll
	public static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	public static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	public void test01() {
		goTo("http://localhost:8080/lms");

		String pegeTitle = WebDriverUtils.getTitle();
		assertEquals("ログイン | LMS", pegeTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 DBに登録されていないユーザーでログイン")
	public void test02() throws InterruptedException {
		WebDriverUtils.nameInput("loginId", "login_er");
		WebDriverUtils.nameInput("password", "pass_er");
		WebDriverUtils.enterKey("btn");

		//  59行目のコードでもできるが、56行目～58行目のコードではうまくいかない。
		//		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//		final WebDriverWait wait = new WebDriverWait(webDriver, 60); 
		//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); 
		Thread.sleep(1000);

		WebDriverUtils.resultClassName("help-inline", "* ログインに失敗しました。");

		getEvidence(new Object() {
		});
	}

}
