
/**
 * Keeps track of score
 * and streak
 * @author danbro
 *
 */
public class Score {
	
	private int score = 0;
	private int streak = 0;
	private int streakGauge = 0;
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore() {
		this.score++;
	}/**
	 * @param score the score to set
	 */
	public void setScoreZero(int score ) {
		this.score = score;
	}
	/**
	 * @return the streak
	 */
	public int getStreak() {
		return streak;
	}
	/**
	 * @param streak the streak to set
	 */
	public void setStreak(int streak) {
		this.streak = streak;
	}
	/**
	 * @return the streakGauge
	 */
	public int getStreakGauge() {
		return streakGauge;
	}
	/**
	 * @param streakGauge the streakGauge to set
	 */
	public void setStreakGauge(int streakGauge) {
		this.streakGauge = streakGauge;
	}
	

}
