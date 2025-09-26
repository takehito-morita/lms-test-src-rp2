package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.Assert.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト よくある質問機能
 * ケース04
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース04 よくある質問画面への遷移")
public class Case04 {

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
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	public void test02() throws InterruptedException {
		WebDriverUtils.nameInput("loginId", "StudentAA03");
		WebDriverUtils.nameInput("password", "StudentAA033");
		WebDriverUtils.enterKey("btn");

		Thread.sleep(1000);

		String pegeTitle = WebDriverUtils.getTitle();
		assertEquals("コース詳細 | LMS", pegeTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	public void test03() {
		WebDriverUtils.webDriver.findElement(By.className("dropdown-toggle")).click();
		WebDriverUtils.webDriver.findElement(By.xpath("//*[text()=\"ヘルプ\"]")).click();

		String pegeTitle = WebDriverUtils.getTitle();
		assertEquals("ヘルプ | LMS", pegeTitle);

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	public void test04() {

		WebDriverUtils.webDriver.findElement(By.linkText("よくある質問")).click();

		/*「よくある質問」リンク(aタグ)は別タブを開くため、
		 * 新しいウィンドウで作業するには、それに切り替える必要があります。
		 * 下記コードではWebDriverが表示できる両方のウィンドウまたはタブをループし、
		 * 元のウィンドウまたはタブに切り替えることができます。
		*/
		Object[] windowHandles = WebDriverUtils.webDriver.getWindowHandles().toArray();
		WebDriverUtils.webDriver.switchTo().window((String) windowHandles[1]);

		System.out.println(WebDriverUtils.webDriver.getTitle());
		String title = WebDriverUtils.webDriver.getTitle();
		assertEquals("よくある質問 | LMS", title);

		getEvidence(new Object() {
		});

	}

}
