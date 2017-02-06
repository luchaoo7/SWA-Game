import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Label;
/**
 * Keeps track of score
 * and streak
 * @author danbro
 */
public class Score {
	
	private int score = 0;
	private int streak = 0;
	private int streakGauge = 0;
	SQLite _dbHandle = SQLite.getInstace();
	
		
	/**
	 * return top ten scores
	 * @return
	 */
	public String getTop10Scores()
	{
		String sql = "SELECT * FROM scores order by score desc, "
				+ "streak desc limit 10";
		String allScore = "Pos   Name   Score    Streak\n";
		try {
			ResultSet resultSet =_dbHandle.statement.executeQuery(sql);
			int count = 0;
			while (resultSet.next()) {
				count++;
//						int id = resultSet.getInt("ID");
				String name = resultSet.getString("NAME");
				int score = resultSet.getInt("score");
				int streak = resultSet.getInt("streak");
		
				allScore += count + "      " + name + "       " + score + "      " + streak + "\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allScore;
	}
	/**
	 * insert into database
	 */
	public void insertScore(String name, int score, int streak )
	{
		try {
		String sql = "INSERT INTO scores (NAME, SCORE, STREAK) " +
				"VALUES('" + name + "'," + score + "," + streak + ")";
			_dbHandle.statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
	public void setScoreZero() {
		this.score = 0;
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
	public void setStreakZero() {
		this.streak = 0;
	}
	/**
	 * @param streak the streak to set
	 */
	public void setStreak() {
		this.streak++;
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

	public void reset(Score score, Label labelScore, Label labelStreak) 
	{
		score.setStreakZero();
		score.setScoreZero();;
		labelScore.setText("Your Score: " + score.getScore());
		score.setStreakGauge(0);
		labelStreak.setText("Your Streak: " + score.getStreakGauge());
	}
}
