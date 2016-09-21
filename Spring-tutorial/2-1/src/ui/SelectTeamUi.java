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
		//팀 목록 표시
		showTeamList(this.teamDao.getTeamList());
		System.out.println("Enter를 눌러주세요.");
		getInputedString();
	}
	
	protected void showTeamList(List<Team> teamList){
		System.out.println("---------------");
        System.out.println("『선수 명단』「팀 목록」");
        System.out.println("ID    이름");
        
        for(Team team : teamList){
        	//팀 id와 팀명 표시
        	System.out.printf("%d %s\n", team.getId(), team.getName());
        }
	}
}
