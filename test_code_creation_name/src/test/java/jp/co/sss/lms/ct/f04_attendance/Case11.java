package jp.co.sss.lms.ct.f04_attendance;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト 勤怠管理機能
 * ケース11
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース11 受講生 勤怠直接編集 正常系")
public class Case11 {

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
		Alert ok = WebDriverUtils.webDriver.switchTo().alert();
		ok.accept();
		Thread.sleep(1000);
		WebDriverUtils.resultTitle("勤怠情報変更｜LMS");
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「勤怠情報を直接編集する」リンクから勤怠情報直接変更画面に遷移")
	void test04() throws InterruptedException {
		WebDriverUtils.webDriver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/p/a")).click();
		Thread.sleep(1000);
		WebDriverUtils.resultTitle("勤怠情報変更｜LMS");
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 すべての研修日程の勤怠情報を正しく更新し勤怠管理画面に遷移")
	void test05() throws InterruptedException {
		WebDriverUtils.inputIdPull("startHour0", "09");
		WebDriverUtils.inputIdPull("startMinute0", "00");
		WebDriverUtils.inputIdPull("endHour0", "18");
		WebDriverUtils.inputIdPull("endMinute0", "00");
		WebDriverUtils.inputNamePull("attendanceList[0].blankTime", "15分");
		WebDriverUtils.nameInput("attendanceList[0].note", "研修最高でした！本当にありがとうございました！");
		Thread.sleep(5000);
		WebDriverUtils.resultValue("//*[@id=\"main\"]/div/div/form/table/tbody/tr[1]/td[12]/input",
				"研修最高でした！本当にありがとうございました！");
		getEvidence(new Object() {
		});
		WebDriverUtils.enterKey("update-button");
		Alert ok = WebDriverUtils.webDriver.switchTo().alert();
		ok.accept();

		Thread.sleep(1000);

		WebDriverUtils.resultTitle("勤怠情報変更｜LMS");
		getEvidence(new Object() {
		});
	}

}
