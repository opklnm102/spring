package dao;

import java.util.List;

import model.Team;

import org.springframework.dao.DataAccessException;

public interface TeamDao {
	
	List<Team> getTeamList() throws DataAccessException;

	 Team getTeam(Integer teamId) throws DataAccessException;
}
