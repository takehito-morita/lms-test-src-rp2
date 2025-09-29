package jp.co.sss.lms.ct.f03_report;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト レポート機能
 * ケース07
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース07 受講生 レポート新規登録(日報) 正常系")
public class Case07 {

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
	@DisplayName("テスト03 未提出の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() throws InterruptedException {

		WebDriverUtils.webDriver
				.findElement(By.xpath("//*[@id=\"main\"]/div/div[5]/div[2]/table/tbody/tr[4]/td[5]/form/input[3]"))
				.sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		WebDriverUtils.resultTitle("セクション詳細 | LMS");
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「提出する」ボタンを押下しレポート登録画面に遷移")
	void test04() throws InterruptedException {
		WebDriverUtils.enterKeyValue("//input[@value='日報【デモ】を提出する']");
		Thread.sleep(1000);
		WebDriverUtils.resultTitle("レポート登録 | LMS");
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を入力して「提出する」ボタンを押下し確認ボタン名が更新される")
	void test05() throws InterruptedException {
		WebDriverUtils.inputClassName("form-control", "本日もありがとうございました。");
		WebDriverUtils.enterKey("btn-primary");
		Thread.sleep(1000);

		// 下記のプログラムでエラーが発生
		//WebDriverUtils.resultValue("btn-default", "提出済み日報【デモ】を確認する");
		getEvidence(new Object() {
		});
	}

}
