package choice;

public class Oracle extends StoppableThread {

	private static int COUNTDOWN_DAY = 7;

	private int countdownDay = COUNTDOWN_DAY;

	private StoppableThread[] threads = new StoppableThread[2];

	private boolean choosed = false;

	private String choice;

	private int priority = this.getPriority();

	public Oracle(StoppableThread k2, StoppableThread xx) {
		super("choice");
		threads[0] = k2;
		threads[1] = xx;
	}

	// called 'every day' once
	@Override
	public void doOnce() {
		if (!choosed) {
			if (--countdownDay > 0) {
				// priority from 1(MIN) to 10(MAX)
				if (this.getPriority() == Thread.MAX_PRIORITY - 1) {
					makeChoice("k2");
					System.out.println("something will be changed, let's in-depth talks");
				} else if (priority != this.getPriority()) {
					priority = this.getPriority();
					System.out.println("but nothing maybe happened");
				}
				System.out.println(countdownDay + (countdownDay <= 1 ? " day " : " days ") + "left");
			} else if (countdownDay <= 0) {
				System.out.println("it is time to leave");
				makeChoice("other");
			}
		} else {
			System.out.println(choice + " maybe a good choice");
		}
	}

	private void makeChoice(String name) {
		if ("k2".equals(name)) {
			this.threads[1].stop(); // stop other thread
			this.choice = threads[0].getName(); // choose k2
		} else if ("other".equals(name)) {
			this.threads[0].stop(); // stop k2 thread
			this.choice = threads[1].getName(); // choose other
		}
		this.choosed = true;
	}

	public boolean isChoosed() {
		return choosed;
	}

	public String getChoice() {
		return choice;
	}

}
