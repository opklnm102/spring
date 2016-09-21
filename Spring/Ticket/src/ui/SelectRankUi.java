package ui;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import dao.RankDao;

public class SelectRankUi extends AbstractUi {

	private RankDao rankDao;
	
	private InsertReservationUi insertReservationUi;
	
	public void setRankDao(RankDao rankDao) {
		this.rankDao = rankDao;
	}

	public void setInsertReservationUi(InsertReservationUi insertReservationUi) {
		this.insertReservationUi = insertReservationUi;
	}



	@Override
	public void show() {
		showHeader();
		// 콘솔에 입력된 값을 취득
		Integer eventId = getEventId();
		if (eventId == null) {
			return;
		}
		// 랭크 목록을 표시
		showRanks(this.rankDao.getRank(eventId));

		this.insertReservationUi.show();
		
	}
	

	protected void showRanks(List<Object[]> rankList) {

		System.out.println("--------------------");
		System.out.println("『티켓 예약』「티켓 목록」");
		System.out.println("ID   이름   가격   남은매수");

		for (Object[] objects : rankList) {
			// 랭크ID와 랭크명, 가격, 남은 매수를 표시
			System.out.printf("%s  %s  %s  %s%n", objects[0], objects[1], objects[2], objects[3]);
		}
	}

	protected Integer getEventId() {
		// 콘솔에 입력된 값을 취득
		String eventId = getInputedString();
		// 문자열이 입력되었는지?
		if (StringUtils.isBlank(eventId)) {
			return null;
		}
		// 숫자인지?
		if (UiUtils.isNumeric(eventId, "ID")) {
			return Integer.valueOf(eventId);
		}
		return getEventId();
	}

	protected void showHeader() {
		System.out.println("--------------------");
		System.out.println("『티켓 예약』「티켓 검색」");
		System.out.println("");
		System.out.println("예약하고 싶은 이벤트의 ID를 입력한 후 Enter를 눌러주세요.");
		System.out.println("아무것도 입력하지 않고 Enter를 누르면 메뉴로 돌아갑니다.");
	}

}
