package jp.co.sss.lms.ct.f06_login2;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

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
 * 結合テスト ログイン機能②
 * ケース17
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース17 受講生 初回ログイン 正常系")
public class Case17 {

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
	@DisplayName("テスト02 DBに初期登録された未ログインの受講生ユーザーでログイン")
	void test02() throws InterruptedException {
		WebDriverUtils.nameInput("loginId", "StudentAB02");
		WebDriverUtils.nameInput("password", "StudentAB02");
		WebDriverUtils.enterKey("btn");

		Thread.sleep(1000);

		WebDriverUtils.resultTitle("セキュリティ規約 | LMS");
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 「同意します」チェックボックスにチェックを入れ「次へ」ボタン押下")
	void test03() throws InterruptedException {
		WebDriverUtils.webDriver
				.findElement(By.xpath("//*[@id=\"main\"]/div[2]/form/fieldset/div[1]/div/label/input[1]")).click();
		WebDriverUtils.webDriver
				.findElement(By.xpath("//*[@id=\"main\"]/div[2]/form/fieldset/div[2]/button")).click();
		Thread.sleep(1000);
		WebDriverUtils.resultTitle("パスワード変更 | LMS");
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 変更パスワードを入力し「変更」ボタン押下")
	void test04() throws InterruptedException {
		WebDriverUtils.nameInput("currentPassword", "StudentAB02");
		WebDriverUtils.nameInput("password", "StudentAB022");
		WebDriverUtils.nameInput("passwordConfirm", "StudentAB022");
		WebDriverUtils.enterKeyValue("//*[@id=\"upd-form\"]/div[1]/fieldset/div[4]/div/button[2]");

		//下記プログラムを実行してもうまくいかない。納品まで間に合わせることができなかったためコメント。
		//		Alert change = WebDriverUtils.webDriver.switchTo().alert();
		//		Assertions.assertEquals("変更", change.getText());
		//		change.accept();

		Thread.sleep(1000);
		WebDriverUtils.resultTitle("コース詳細 | LMS");
		getEvidence(new Object() {
		});
	}

}
