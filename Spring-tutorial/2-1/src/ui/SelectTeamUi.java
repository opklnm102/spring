package ui;

import java.util.List;

import model.Team;
import dao.TeamDao;

public class SelectTeamUi extends AbstractUi {

	private TeamDao teamDao;
	
	public void setTeamDao(TeamDao teamDao){
		this.teamDao = teamDao;
	}
	
	@Override
	public void show() {
		//�� ��� ǥ��
		showTeamList(this.teamDao.getTeamList());
		System.out.println("Enter�� �����ּ���.");
		getInputedString();
	}
	
	protected void showTeamList(List<Team> teamList){
		System.out.println("---------------");
        System.out.println("������ ��ܡ����� ��ϡ�");
        System.out.println("ID    �̸�");
        
        for(Team team : teamList){
        	//�� id�� ���� ǥ��
        	System.out.printf("%d %s\n", team.getId(), team.getName());
        }
	}
}
