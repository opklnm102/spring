package ui;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

public abstract class AbstractUiTemplate extends AbstractUi {

	@Override
	public void show() {
		// �޴� ǥ��
		showMenu();

		// �ݼֿ� �Էµ� ���� ���
		String inputedString = getInputedString();

		// �Է� ���ڿ� Ȯ��
		if (isValidNumber(inputedString)) {
			// ó�� ����
			execute(NumberUtils.toInt(inputedString));
		}
	}

	abstract protected void showMenu();

	abstract protected int getMaxMenuNumber();

	abstract protected int getMinMenuNumber();

	abstract protected void execute(int number);

	protected boolean isValidNumber(String str) {
		// ���ڿ��� �Էµǰ� �ִ���?
		if (StringUtils.isBlank(str)) {
			return false;
		} else if (!StringUtils.isNumeric(str)) { // ��������?
			return false;
		}

		// �����̹Ƿ� int�� ��ȯ
		int number = NumberUtils.toInt(str);

		// ���ڰ� �޴��� ǥ�õǰ� �ִ� ��ȣ�� ��������?
		  if (getMinMenuNumber() <= number && number <= getMaxMenuNumber()) {
			return true;
		}
		return false;
	}
}
