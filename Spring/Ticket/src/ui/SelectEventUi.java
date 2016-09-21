package ui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import model.Event;
import dao.EventDao;

public class SelectEventUi extends AbstractUi {

	private static final String DATE_FORMAT_MESSAGE = "날짜는 YYYYMMDD 형식으로 입력해주세요(예:20100101)";

	private EventDao eventDao;
	
	private SelectRankUi selectRankUi;

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	public void setSelectRankUi(SelectRankUi selectRankUi) {
		this.selectRankUi = selectRankUi;
	}
	
	public void show() {
		// 헤더를 표시
		showHeader();
		// 메뉴를 표시
		showMenu("검색개시일");
		// 콘솔에 입력된 값을 취득
		Date start = getDate();
		if (start == null) {
			return;
		}
		// 콘솔에 입력된 값을 취득
		showMenu("검색종료일");
		Date end = getDate();
		if (end == null) {
			return;
		}
		// 개시일과 종료일의 전후를 파악
		if (start.compareTo(end) > 0) {
			System.out.println("검색종료일에는 검색개시일 이후의 날짜를 입력해 주세요.");
			show();
			return;
		}
		// 이벤트를 취득
		List<Event> eventList = this.eventDao.getEventList(start, end);
		// 이벤트 목록을 표시
		showEventList(eventList);

		this.selectRankUi.show();
	}

	protected void showEventList(List<Event> eventList) {
		System.out.println("--------------------");
		System.out.println("『티켓 예약』「이벤트 목록」");
		System.out.println("ID    이름    날짜");

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		for (Event event : eventList) {
			// 이벤트ID와 이벤트명, 날짜를 표시
			System.out.printf("%s  %s  %s%n", event.getEventId(), event.getName(), dateFormat.format(event.getDate()));
		}

	}

	protected Date getDate() {

		// 콘솔에 입력된 값을 취득
		String dateString = getInputedString();
		// 문자열이 입력되었는지?
		if (StringUtils.isEmpty(dateString)) {
			return null;
		}

		// 8문자(yyyyMMdd인지?
		if (dateString.length() != 8) {
			System.out.println(DATE_FORMAT_MESSAGE);
			return getDate();
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date;
		try {
			// Date로 변환
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
			// 날짜로 변환시킬 수 없는 형식의 문자열
			System.out.println(DATE_FORMAT_MESSAGE);
			return getDate();
		}

		// 현재 날짜보다 미래인지?
		if (new Date().after(date)) {
			System.out.println("지금보다 과거인 날짜는 입력할 수 없습니다. 다시 날짜를 입력해 주세요.");
			return getDate();
		}

		return date;

	}

	protected void showHeader() {
		System.out.println("--------------------");
		System.out.println("『티켓 예약』「이벤트 검색」");
		System.out.println("");
	}

	protected void showMenu(String wanted) {
		System.out.printf("%s를 입력한 후, Enter를 눌러주세요.%n", wanted);
		System.out.println("아무것도 입력하지 않고 Enter를 누르면 메뉴로 돌아갑니다.");
		System.out.println(DATE_FORMAT_MESSAGE);
	}
	
}

