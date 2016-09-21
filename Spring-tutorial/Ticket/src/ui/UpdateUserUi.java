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
		// 헤더를 표시
		showHeader();
		// 콘솔에 입력된 값을 취득
		Integer id = getUserId();
		if (id == null) {
			return;
		}
		// ID로 유저를 검색
		User user = this.userDao.getUser(id);
		if (user == null) {
			// 해당하는 유저가 존재하지 않는다
			System.out.printf("입력된 유저ID '%s'는 존재하지 않습니다.%n", id);
			show();
			return;
		}
		// 유저 정보를 표시
		showUser(user);
		// 콘솔에 입력된 값을 표시
		String name = getName();
		// 문자열이 입력되었는지?
		if (StringUtils.isBlank(name)) {
			return;
		}
		// 유저명을 지정
		user.setName(name);
		// 데이터베이스를 갱신
		// this.userDao.updateUser(user);
	}

	protected Integer getUserId() {

		final String userId = "유저ID";
		// 메뉴 표시
		showMenu(userId);
		// 콘솔에 입력된 값을 취득
		String id = getInputedString();
		// 문자열이 입력되어 있는지?
		if (StringUtils.isBlank(id)) {
			return null;
		}
		// 숫자인지?
		if (UiUtils.isNumeric(id, userId)) {
			return new Integer(id);
		}

		return getUserId();
	}

	protected String getName() {

		showMenu("새로운 유저명");
		// 콘솔에 입력된 값을 취득
		String newName = getInputedString();
		// 128문자 이하인지?
		if (!UiUtils.isSmallLength(newName, "유저명", 128)) {
			return getName();
		}

		return newName;
	}

	protected void showUser(User user) {
		System.out.println("--------------------");
		System.out.println("『티켓 예약』「유저 정보 변경」");
		System.out.println("ID    이름");
		System.out.printf("%s  %s%n", user.getUserId(), user.getName());
	}

	protected void showHeader() {
		System.out.println("--------------------");
		System.out.println("『티켓 예약』「유저 정보 변경」");
		System.out.println("");
	}

	protected void showMenu(String wanted) {
		System.out.printf("%s를 입력한 후 Enter를 눌러주세요.%n", wanted);
		System.out.println("아무것도 입력하지 않고 Enter를 누르면 메뉴로 돌아갑니다.");
	}
}