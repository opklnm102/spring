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
		// ����� ǥ��
		showHeader();
		// �޴��� ǥ��
		showMenu("������");
		// �ֿܼ� �Էµ� ���� ���
		String userName = getInputedString();
		// ���ڿ��� �ԷµǾ�����?
		if (StringUtils.isEmpty(userName)) {
			return;
		}
		// �̸����� ������ �˻�
		User user = this.userDao.getUser(userName);
		if (user == null) {
			// �ش��ϴ� ������ �������� �ʴ´�
			System.out.printf("�Է��� ������%s���� �������� �ʽ��ϴ�.%n", userName);
			show();
			return;
		}
		// ���� ������ ǥ��
		showUser(user);
		// ����Ǿ� �ִ� Ƽ���� ���
		List<Ticket> ticketList = this.ticketDao.getBookedTicketList(user.getUserId());
		// ����Ǿ� �ִ� Ƽ�� ����� ǥ��
		showBookedTikectList(ticketList);
		
        this.deleteReservationUi.show();

	}

	protected void showBookedTikectList(List<Ticket> ticketList) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		System.out.println(" ID  �̺�Ʈ��  ��¥  ����  �����ѳ�¥ ");
		for (Ticket ticket : ticketList) {
			// Ƽ�ϰ� ���� ������ ǥ��
			System.out.printf("%s  %s  %s  %s  %s%n", ticket.getTicketId(), ticket.getEvent().getName(), dateFormat
					.format(ticket.getEvent().getDate()), ticket.getRank().getPrice(), dateFormat.format(ticket
					.getReservation().getTimestamp()));
		}
	}

	protected void showUser(User user) {
		System.out.println("--------------------");
		System.out.println("��Ƽ�� ���ࡻ������ �Ϸ� Ƽ�� ��ϡ�");
		System.out.println("ID    �̸�");
		System.out.printf("%s  %s%n", user.getUserId(), user.getName());
	}

	protected void showHeader() {
		System.out.println("--------------------");
		System.out.println("��Ƽ�� ���ࡻ������ �Ϸ� Ƽ�� ��ϡ�");
		System.out.println("");
	}

	protected void showMenu(String wanted) {
		System.out.printf("%s�� �Է��� ��, Enter�� �����ּ���.%n", wanted);
		System.out.println("�ƹ��͵� �Է����� �ʰ� Enter�� ������ �޴��� ���ư��ϴ�.");
	}
}
