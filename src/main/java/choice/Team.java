package choice;

//we are a team
public class Team extends StoppableThread {

	protected Oracle oracle;
	
	public Team(String name) {
		super(name);
	}

	@Override
	public void doOnce() {
		if (oracle.isChoosed()) {
			if (getName().equals(oracle.getChoice())) {
				System.out.println("for " + oracle.getChoice() + "'s future");
			}
		}
	}

	public Oracle getTeammate() {
		return oracle;
	}

	public void setTeammate(Oracle oracle) {
		this.oracle = oracle;
	}

}
