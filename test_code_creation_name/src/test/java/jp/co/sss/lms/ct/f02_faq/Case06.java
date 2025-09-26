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
 * ケース06
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース06 カテゴリ検索 正常系")
public class Case06 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		goTo("http://localhost:8080/lms");

		WebDriverUtils.resultTitle("ログイン | LMS");

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() throws InterruptedException {
		WebDriverUtils.nameInput("loginId", "StudentAA03");
		WebDriverUtils.nameInput("password", "StudentAA033");
		WebDriverUtils.enterKey("btn");

		Thread.sleep(1000);

		WebDriverUtils.resultTitle("コース詳細 | LMS");

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		WebDriverUtils.webDriver.findElement(By.className("dropdown-toggle")).click();
		WebDriverUtils.webDriver.findElement(By.xpath("//*[text()=\"ヘルプ\"]")).click();

		WebDriverUtils.resultTitle("ヘルプ | LMS");

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		WebDriverUtils.webDriver.findElement(By.linkText("よくある質問")).click();

		/*「よくある質問」リンク(aタグ)は別タブを開くため、
		 * 新しいウィンドウで作業するには、それに切り替える必要があります。
		 * 下記コードではWebDriverが表示できる両方のウィンドウまたはタブをループし、
		 * 元のウィンドウまたはタブに切り替えることができます。
		*/
		Object[] windowHandles = WebDriverUtils.webDriver.getWindowHandles().toArray();
		WebDriverUtils.webDriver.switchTo().window((String) windowHandles[1]);

		WebDriverUtils.resultTitle("よくある質問 | LMS");

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 カテゴリ検索で該当カテゴリの検索結果だけ表示")
	void test05() throws InterruptedException {
		WebDriverUtils.webDriver.findElement(By.linkText("【人材開発支援助成金】")).click();

		WebDriverUtils.scrollBy("800");
		Thread.sleep(1000);

		System.out.println(WebDriverUtils.getElementsByClassName("text-primary").size());
		assertEquals(3, WebDriverUtils.getElementsByClassName("text-primary").size());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 検索結果の質問をクリックしその回答を表示")
	void test06() {
		WebDriverUtils.webDriver.findElement(By.className("mb10")).click();

		System.out.println(WebDriverUtils.getElementsByClassName("fs18").size());
		assertEquals(1, WebDriverUtils.getElementsByClassName("fs18").size());

		getEvidence(new Object() {
		});
	}

}
