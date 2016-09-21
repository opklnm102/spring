package final1;

public class DamselRescuingKnight implements Knight {
	
	private Quest quest;

	public void setQuest(Quest quest) {
		this.quest = quest;
	}

	public void embarkOnQuest(){
		quest.embark();
	}
}
