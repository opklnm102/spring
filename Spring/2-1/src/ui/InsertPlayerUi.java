package ui;

import model.Player;
import model.Team;

import org.apache.commons.lang.StringUtils;

import dao.PlayerDao;
import dao.TeamDao;

public class InsertPlayerUi extends AbstractUi {

	private TeamDao teamDao;

	private PlayerDao playerDao;

	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}

	public void setPlayerDao(PlayerDao service) {
		this.playerDao = service;
	}
	
	

	@Override
	public void show() {
		  final String playerName = "������";
	        // �޴� ǥ��
	        showMenu(playerName);
	        // �ֿܼ� �Էµ� ���� ���
	        String name = getInputedString();
	        // ���ڿ��� �ԷµǾ�����?
	        if (StringUtils.isEmpty(name)) {
	            // �޴��� ���ư���
	            return;
	          //128���� �̳�����?
	        } else if (UiUtils.isSmallLength(name, playerName, 128)) {
	        	//���ο� ������ ����
	            Player player = new Player();
	            player.setName(name);
	          //���� ����
	            showTeamField(player);
	        } else {
	            show();
	        }

	}
	
	 protected void showTeamField(Player player) {
	        final String teamId = "��ID";
	        // �޴��� ǥ��
	        showMenu(teamId);
	        // �ֿܼ� �Էµ� ���� ���
	        String id = getInputedString();
	        // ���ڿ��� �ԷµǾ�����?
	        if (StringUtils.isEmpty(id)) {
	            return;
	        // ��������?
	        } else if (UiUtils.isNumeric(id, teamId)) {
	        	//ID�� ���� �˻�
	            Team team = this.teamDao.getTeam(Integer.valueOf(id));
	            if (team == null) {
	            	//�ش��ϴ� ���� �����ϴ���?
	                System.out.printf("�Է��Ͻ� ������ '%s'�� ���� �������� �ʽ��ϴ�.%n", id);
	                showTeamField(player);
	            } else {
	                // ���� ������ ����
	                player.setTeam(team);
	                // �����ͺ��̽��� ������ ���
	                playerDao.insertPlayer(player);
	                System.out.printf("����%s������%s�������� �߰��߽��ϴ�.%n", team.getName(), player.getName());
	            }
	        }
	    }

	    protected void showMenu(String wanted) {
	        System.out.println("--------------------");
	        System.out.println("������ ��ܡ������� �߰���");
	        System.out.println("");
	        System.out.printf("%s�� �Է��ϰ� Enter Ű�� �����ּ���.%n", wanted);
	        System.out.println("�ƹ��͵� �Է����� �ʰ� Enter Ű�� ������ �޴��� ���ư��ϴ�.");
	    }

}
