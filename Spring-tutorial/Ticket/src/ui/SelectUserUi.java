package ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import model.Ticket;
import model.User;

import org.apache.commons.lang.StringUtils;

import dao.TicketDao;
import dao.UserDao;

public class SelectUserUi extends AbstractUi {

	private UserDao userDao;

	private TicketDao ticketDao;

	private DeleteReservationUi deleteReservationUi;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}

	public void setDeleteReservationUi(DeleteReservationUi deleteReservationUi) {
		this.deleteReservationUi = deleteReservationUi;
	}

	public void show() {
		// 헤더를 표시
		showHeader();
		// 메뉴를 표시
		showMenu("유저명");
		// 콘솔에 입력된 값을 취득
		String userName = getInputedString();
		// 문자열이 입력되었는지?
		if (StringUtils.isEmpty(userName)) {
			return;
		}
		// 이름으로 유저를 검색
		User user = this.userDao.getUser(userName);
		if (user == null) {
			// 해당하는 유저가 존재하지 않는다
			System.out.printf("입력한 유저명「%s」은 존재하지 않습니다.%n", userName);
			show();
			return;
		}
		// 유저 정보를 표시
		showUser(user);
		// 예약되어 있는 티켓을 취득
		List<Ticket> ticketList = this.ticketDao.getBookedTicketList(user.getUserId());
		// 예약되어 있는 티켓 목록을 표시
		showBookedTikectList(ticketList);
		
        this.deleteReservationUi.show();

	}

	protected void showBookedTikectList(List<Ticket> ticketList) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		System.out.println(" ID  이벤트명  날짜  가격  예약한날짜 ");
		for (Ticket ticket : ticketList) {
			// 티켓과 예약 정보를 표시
			System.out.printf("%s  %s  %s  %s  %s%n", ticket.getTicketId(), ticket.getEvent().getName(), dateFormat
					.format(ticket.getEvent().getDate()), ticket.getRank().getPrice(), dateFormat.format(ticket
					.getReservation().getTimestamp()));
		}
	}

	protected void showUser(User user) {
		System.out.println("--------------------");
		System.out.println("『티켓 예약』「예약 완료 티켓 목록」");
		System.out.println("ID    이름");
		System.out.printf("%s  %s%n", user.getUserId(), user.getName());
	}

	protected void showHeader() {
		System.out.println("--------------------");
		System.out.println("『티켓 예약』「예약 완료 티켓 목록」");
		System.out.println("");
	}

	protected void showMenu(String wanted) {
		System.out.printf("%s를 입력한 후, Enter를 눌러주세요.%n", wanted);
		System.out.println("아무것도 입력하지 않고 Enter를 누르면 메뉴로 돌아갑니다.");
	}
}
