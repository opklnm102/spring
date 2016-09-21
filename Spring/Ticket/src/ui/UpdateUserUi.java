package ui;

import model.User;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import dao.UserDao;

public class UpdateUserUi extends AbstractUi {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Transactional
	public void show() {
		// ����� ǥ��
		showHeader();
		// �ֿܼ� �Էµ� ���� ���
		Integer id = getUserId();
		if (id == null) {
			return;
		}
		// ID�� ������ �˻�
		User user = this.userDao.getUser(id);
		if (user == null) {
			// �ش��ϴ� ������ �������� �ʴ´�
			System.out.printf("�Էµ� ����ID '%s'�� �������� �ʽ��ϴ�.%n", id);
			show();
			return;
		}
		// ���� ������ ǥ��
		showUser(user);
		// �ֿܼ� �Էµ� ���� ǥ��
		String name = getName();
		// ���ڿ��� �ԷµǾ�����?
		if (StringUtils.isBlank(name)) {
			return;
		}
		// �������� ����
		user.setName(name);
		// �����ͺ��̽��� ����
		// this.userDao.updateUser(user);
	}

	protected Integer getUserId() {

		final String userId = "����ID";
		// �޴� ǥ��
		showMenu(userId);
		// �ֿܼ� �Էµ� ���� ���
		String id = getInputedString();
		// ���ڿ��� �ԷµǾ� �ִ���?
		if (StringUtils.isBlank(id)) {
			return null;
		}
		// ��������?
		if (UiUtils.isNumeric(id, userId)) {
			return new Integer(id);
		}

		return getUserId();
	}

	protected String getName() {

		showMenu("���ο� ������");
		// �ֿܼ� �Էµ� ���� ���
		String newName = getInputedString();
		// 128���� ��������?
		if (!UiUtils.isSmallLength(newName, "������", 128)) {
			return getName();
		}

		return newName;
	}

	protected void showUser(User user) {
		System.out.println("--------------------");
		System.out.println("��Ƽ�� ���ࡻ������ ���� ���桹");
		System.out.println("ID    �̸�");
		System.out.printf("%s  %s%n", user.getUserId(), user.getName());
	}

	protected void showHeader() {
		System.out.println("--------------------");
		System.out.println("��Ƽ�� ���ࡻ������ ���� ���桹");
		System.out.println("");
	}

	protected void showMenu(String wanted) {
		System.out.printf("%s�� �Է��� �� Enter�� �����ּ���.%n", wanted);
		System.out.println("�ƹ��͵� �Է����� �ʰ� Enter�� ������ �޴��� ���ư��ϴ�.");
	}
}