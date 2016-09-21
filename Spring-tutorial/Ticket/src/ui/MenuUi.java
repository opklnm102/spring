package ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MenuUi extends AbstractUiTemplate {
    
    private SelectEventUi selectEventUi;
    
    private SelectUserUi selectUserUi;
    
    private UpdateUserUi updateUserUi;
    
    public void setSelectEventUi(SelectEventUi selectEventUi) {
        this.selectEventUi = selectEventUi;
    }
    
    public void setSelectUserUi(SelectUserUi selectUserUi) {
        this.selectUserUi = selectUserUi;
    }

    public void setUpdateUserUi(UpdateUserUi updateUserUi) {
        this.updateUserUi = updateUserUi;
    }

    protected void execute(int number) {
        switch (number) {
        case 1:
            // 1.����
            System.out.println("����Ǿ����ϴ�.");
            System.exit(0);
        case 2:
            // 2.�̺�Ʈ �˻�
            this.selectEventUi.show();
            break;
        case 3:
            // 3.���� �Ϸ� Ƽ�� ���
            this.selectUserUi.show();
            break;
        case 4:
            // 4.������ ����
            this.updateUserUi.show();
            break;           
        }
    }

    protected int getMaxMenuNumber() {
        return 4;
    }

    protected int getMinMenuNumber() {
        return 1;
    }

    protected void showMenu() {
        System.out.println("--------------------");
        System.out.println("��Ƽ�� ���ࡻ���޴���");
        System.out.println("");
        System.out.println("1.����");
        System.out.println("2.�̺�Ʈ �˻�");
        System.out.println("3.���� �Ϸ� Ƽ�� ���");
        System.out.println("4.������ ����");
        System.out.println("");
        System.out.println("��ȣ�� �Է��� ��, Enter�� �Է��� �ּ���.");
    }

    public static void main(String[] args) {
        // ������ ���� ���� �б�
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MenuUi menuUi = (MenuUi) context.getBean("menuUi");
        while (true) {
            menuUi.show();
        }
    }

}
