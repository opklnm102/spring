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

	private static final String DATE_FORMAT_MESSAGE = "��¥�� YYYYMMDD �������� �Է����ּ���(��:20100101)";

	private EventDao eventDao;
	
	private SelectRankUi selectRankUi;

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	public void setSelectRankUi(SelectRankUi selectRankUi) {
		this.selectRankUi = selectRankUi;
	}
	
	public void show() {
		// ����� ǥ��
		showHeader();
		// �޴��� ǥ��
		showMenu("�˻�������");
		// �ֿܼ� �Էµ� ���� ���
		Date start = getDate();
		if (start == null) {
			return;
		}
		// �ֿܼ� �Էµ� ���� ���
		showMenu("�˻�������");
		Date end = getDate();
		if (end == null) {
			return;
		}
		// �����ϰ� �������� ���ĸ� �ľ�
		if (start.compareTo(end) > 0) {
			System.out.println("�˻������Ͽ��� �˻������� ������ ��¥�� �Է��� �ּ���.");
			show();
			return;
		}
		// �̺�Ʈ�� ���
		List<Event> eventList = this.eventDao.getEventList(start, end);
		// �̺�Ʈ ����� ǥ��
		showEventList(eventList);

		this.selectRankUi.show();
	}

	protected void showEventList(List<Event> eventList) {
		System.out.println("--------------------");
		System.out.println("��Ƽ�� ���ࡻ���̺�Ʈ ��ϡ�");
		System.out.println("ID    �̸�    ��¥");

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		for (Event event : eventList) {
			// �̺�ƮID�� �̺�Ʈ��, ��¥�� ǥ��
			System.out.printf("%s  %s  %s%n", event.getEventId(), event.getName(), dateFormat.format(event.getDate()));
		}

	}

	protected Date getDate() {

		// �ֿܼ� �Էµ� ���� ���
		String dateString = getInputedString();
		// ���ڿ��� �ԷµǾ�����?
		if (StringUtils.isEmpty(dateString)) {
			return null;
		}

		// 8����(yyyyMMdd����?
		if (dateString.length() != 8) {
			System.out.println(DATE_FORMAT_MESSAGE);
			return getDate();
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date;
		try {
			// Date�� ��ȯ
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
			// ��¥�� ��ȯ��ų �� ���� ������ ���ڿ�
			System.out.println(DATE_FORMAT_MESSAGE);
			return getDate();
		}

		// ���� ��¥���� �̷�����?
		if (new Date().after(date)) {
			System.out.println("���ݺ��� ������ ��¥�� �Է��� �� �����ϴ�. �ٽ� ��¥�� �Է��� �ּ���.");
			return getDate();
		}

		return date;

	}

	protected void showHeader() {
		System.out.println("--------------------");
		System.out.println("��Ƽ�� ���ࡻ���̺�Ʈ �˻���");
		System.out.println("");
	}

	protected void showMenu(String wanted) {
		System.out.printf("%s�� �Է��� ��, Enter�� �����ּ���.%n", wanted);
		System.out.println("�ƹ��͵� �Է����� �ʰ� Enter�� ������ �޴��� ���ư��ϴ�.");
		System.out.println(DATE_FORMAT_MESSAGE);
	}
	
}

