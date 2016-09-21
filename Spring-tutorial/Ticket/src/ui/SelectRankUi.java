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
		// �ֿܼ� �Էµ� ���� ���
		Integer eventId = getEventId();
		if (eventId == null) {
			return;
		}
		// ��ũ ����� ǥ��
		showRanks(this.rankDao.getRank(eventId));

		this.insertReservationUi.show();
		
	}
	

	protected void showRanks(List<Object[]> rankList) {

		System.out.println("--------------------");
		System.out.println("��Ƽ�� ���ࡻ��Ƽ�� ��ϡ�");
		System.out.println("ID   �̸�   ����   �����ż�");

		for (Object[] objects : rankList) {
			// ��ũID�� ��ũ��, ����, ���� �ż��� ǥ��
			System.out.printf("%s  %s  %s  %s%n", objects[0], objects[1], objects[2], objects[3]);
		}
	}

	protected Integer getEventId() {
		// �ֿܼ� �Էµ� ���� ���
		String eventId = getInputedString();
		// ���ڿ��� �ԷµǾ�����?
		if (StringUtils.isBlank(eventId)) {
			return null;
		}
		// ��������?
		if (UiUtils.isNumeric(eventId, "ID")) {
			return Integer.valueOf(eventId);
		}
		return getEventId();
	}

	protected void showHeader() {
		System.out.println("--------------------");
		System.out.println("��Ƽ�� ���ࡻ��Ƽ�� �˻���");
		System.out.println("");
		System.out.println("�����ϰ� ���� �̺�Ʈ�� ID�� �Է��� �� Enter�� �����ּ���.");
		System.out.println("�ƹ��͵� �Է����� �ʰ� Enter�� ������ �޴��� ���ư��ϴ�.");
	}

}
