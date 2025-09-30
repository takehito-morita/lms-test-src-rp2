package jp.co.sss.lms.ct.f04_attendance;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
 * 結合テスト 勤怠管理機能
 * ケース10
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース10 受講生 勤怠登録 正常系")
public class Case10 {

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
	@DisplayName("テスト03 上部メニューの「勤怠」リンクから勤怠管理画面に遷移")
	void test03() throws InterruptedException {
		WebDriverUtils.webDriver.findElement(By.xpath("//*[@id=\"nav-content\"]/ul[1]/li[3]/a")).click();
		WebDriverUtils.alertPutOK();
		Thread.sleep(1000);
		WebDriverUtils.resultTitle("勤怠情報変更｜LMS");
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「出勤」ボタンを押下し出勤時間を登録")
	void test04() throws InterruptedException {
		WebDriverUtils.xpathClick("//*[@id=\"main\"]/div[1]/div[2]/form/input[1]");
		LocalTime now = LocalTime.now();
		WebDriverUtils.alertPutOK();
		DateTimeFormatter date = DateTimeFormatter.ofPattern("HH:mm");
		String time = now.format(date);
		Thread.sleep(1000);
		WebDriverUtils.resultXpathText("//*[@id=\"main\"]/div[3]/div/table/tbody/tr[2]/td[3]", time);
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 「退勤」ボタンを押下し退勤時間を登録")
	void test05() throws InterruptedException {
		WebDriverUtils.xpathClick("//*[@id=\"main\"]/div[2]/div[2]/form/input[2]");
		LocalTime now = LocalTime.now();
		WebDriverUtils.alertPutOK();
		DateTimeFormatter date = DateTimeFormatter.ofPattern("HH:mm");
		String time = now.format(date);
		Thread.sleep(1000);
		WebDriverUtils.resultXpathText("//*[@id=\"main\"]/div[3]/div/table/tbody/tr[2]/td[4]", time);
	}

}
