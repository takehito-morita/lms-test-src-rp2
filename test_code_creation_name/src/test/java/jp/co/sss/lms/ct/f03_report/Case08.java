package jp.co.sss.lms.ct.f03_report;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト レポート機能
 * ケース08
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース08 受講生 レポート修正(週報) 正常系")
public class Case08 {

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
	@DisplayName("テスト03 提出済の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() throws InterruptedException {
		WebDriverUtils.enterXpath("//*[@id=\"main\"]/div/div[2]/div[2]/table/tbody/tr[2]/td[5]/form/input[3]");
		Thread.sleep(1000);
		WebDriverUtils.resultTitle("セクション詳細 | LMS");
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「確認する」ボタンを押下しレポート登録画面に遷移")
	void test04() throws InterruptedException {
		WebDriverUtils.enterKeyValue("//input[@value='提出済み週報【デモ】を確認する']");
		Thread.sleep(1000);
		WebDriverUtils.resultTitle("レポート登録 | LMS");
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しセクション詳細画面に遷移")
	void test05() throws InterruptedException {
		WebDriverUtils.nameInput("intFieldNameArray[0]", "ロケータの種類");
		WebDriverUtils.inputIdPull("intFieldValue_0", "3");
		WebDriverUtils.nameInput("contentArray[0]", "1");
		WebDriverUtils.nameInput("contentArray[1]", "難しかった。");
		WebDriverUtils.nameInput("contentArray[2]", "一週間お疲れ様です。");
		WebDriverUtils.enterKey("btn-primary");

		Thread.sleep(1000);
		WebDriverUtils.resultTitle("セクション詳細 | LMS");
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test06() throws InterruptedException {
		WebDriverUtils.enterKeyValue("//*[@id=\"nav-content\"]/ul[2]/li[2]/a");
		Thread.sleep(1000);
		WebDriverUtils.resultTitle("ユーザー詳細");
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 該当レポートの「詳細」ボタンを押下しレポート詳細画面で修正内容が反映される")
	void test07() throws InterruptedException {
		WebDriverUtils.enterKeyValue("//*[@id=\"main\"]/table[2]/tbody/tr[13]/td[5]/form[1]/input[1]");
		Thread.sleep(1000);
		WebDriverUtils.resultXpathText("//*[@id=\"main\"]/div[1]/table/tbody/tr[2]/td[1]/p", "ロケータの種類");
		WebDriverUtils.resultXpathText("//*[@id=\"main\"]/div[1]/table/tbody/tr[2]/td[2]/p", "3");
		WebDriverUtils.resultXpathText("//*[@id=\"main\"]/div[2]/table/tbody/tr[1]/td", "1");
		WebDriverUtils.resultXpathText("//*[@id=\"main\"]/div[2]/table/tbody/tr[2]/td", "難しかった。");
		WebDriverUtils.resultXpathText("//*[@id=\"main\"]/div[2]/table/tbody/tr[3]/td", "一週間お疲れ様です。");
		getEvidence(new Object() {
		});
	}

}
