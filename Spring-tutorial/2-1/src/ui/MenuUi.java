package ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MenuUi extends AbstractUiTemplate {

	private SelectTeamUi selectTeamUi;

	public void setSelectTeamUi(SelectTeamUi selectTeamUi) {
		this.selectTeamUi = selectTeamUi;
	}

	@Override
	protected void showMenu() {
		System.out.println("--------------------");
		System.out.println("������ ��ܡ����޴���");
		System.out.println("");
		System.out.println("1.����");
		System.out.println("2.�� ���");
		System.out.println("");
		System.out.println("��ȣ�� �Է��� �� Enter Ű�� ���� �ּ���.");
	}

	@Override
	protected int getMaxMenuNumber() {
		return 2;
	}

	@Override
	protected int getMinMenuNumber() {
		return 1;
	}

	@Override
	protected void execute(int number) {
		switch (number) {
		case 1:
			// 1.����
			System.out.println("����Ǿ����ϴ�");
			System.exit(0);

		case 2:

			// 2.�� ���
			this.selectTeamUi.show();
			break;

		}

	}
	
	public static void main(String[] args) {
		//������ ���� ���� �б�
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MenuUi menuUi = context.getBean(MenuUi.class);
		while(true){
			menuUi.show();
		}
	}

}
