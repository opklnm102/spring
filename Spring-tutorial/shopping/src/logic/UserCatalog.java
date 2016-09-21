package logic;

public interface UserCatalog {
	
	public User getUserByUserIdAndPassword(String userId, String password);
	
	public void entryUser(User user);

}
