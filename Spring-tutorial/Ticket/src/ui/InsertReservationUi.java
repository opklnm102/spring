package ui;

import java.util.List;

import model.Reservation;
import model.Ticket;
import model.User;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import dao.ReservationDao;
import dao.TicketDao;
import dao.UserDao;

public class InsertReservationUi extends AbstractUi {

	private TicketDao ticketDao;
	
	private UserDao userDao;
	
	private ReservationDao reservationDao;
	

	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setReservationDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}




	@Override
	@Transactional
	public void show() {
		showHeader();
		// 콘솔에 입력된 값을 취득
		Integer rankId = getNumber();
		if (rankId == null) {
			return;
		}
		// 메뉴를 표시
		showMenu("티켓 매수");
		System.out.println("아무것도 입력하지 않고 Enter를 누르면 메뉴로 돌아갑니다.");
		// 콘솔에 입력된 값을 취득
		Integer quantity = getNumber();
		if (quantity == null) {
			return;
		}
		
		List<Ticket> ticketList = this.ticketDao.getNotBookedTicketList(rankId, quantity);
		if(ticketList == null){
			System.out.println("입력된 ID인 랭크의 티켓은 없습니다. 다시 한번 입력해주세요.");
			show();
			return;
		}
		
		if(ticketList.size() < quantity.intValue()){
			System.out.println("티켓이 입력된 매수만큼 남아있지 않습니다. 다시 한번 입력해 주세요.");
			show();
			return;
		}
		
		showMenu("이름");
		// 콘솔에 입력된 값을 취득
		String name = getInputedString();
		// 문자열이 입력되었는지?
		if (StringUtils.isBlank(name)) {
			return;
		}
		// 유저명으로 유저를 검색
		User user = this.userDao.getUser(name);
		if (user == null) {
			// 신규 유저를 생성
			user = new User();
			user.setName(name);
			this.userDao.addUser(user);
		}
		// 예약 처리를 실행
		reserve(ticketList, user);
		
	}
	
	protected void reserve(List<Ticket> ticketList, User user){
		for(Ticket t : ticketList){
			Reservation reservation = new Reservation();
			reservation.setReservationId(t.getTicketId());
			reservation.setTicket(t);
			reservation.setUser(user);
			
			this.reservationDao.addReservation(reservation);
		}
	}
	
	protected Integer getNumber() {
		// 콘솔에 입력된 값을 취득
		String number = getInputedString();
		// 문자열이 입력되었는지?
		if (StringUtils.isBlank(number)) {
			return null;
		}
		// 숫자인지?
		if (StringUtils.isNumeric(number)) {
			return Integer.valueOf(number);
		}
		System.out.println("숫자로 입력해주세요.");

		return getNumber();
	}
	
	protected void showHeader() {
		System.out.println("--------------------");
		System.out.println("『티켓 예약』「티켓」");
		System.out.println("");
		showMenu("예약하고 싶은 티켓의 ID");
		System.out.println("아무것도 입력하지 않고 Enter를 누르면 메뉴로 돌아갑니다.");
	}

	protected void showMenu(String wanted) {
		System.out.printf("%s를 입력한 후 Enter를 눌러주세요.%n", wanted);
	}
}
