import java.sql.*;

public class SQLite {
	
	protected Statement statement = null;
	
	private static SQLite _dbInstace = null;
	
	private SQLite()
	{
		String DRIVER_CLASS = "org.sqlite.JDBC";
		String url = "jdbc:sqlite:players.db";
		try {
			Class.forName(DRIVER_CLASS);
			Connection connection = DriverManager.getConnection(url);
			statement = connection.createStatement();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static SQLite getInstace()
	{

		if (_dbInstace == null) {
			
			_dbInstace = new SQLite();
		}
		return _dbInstace;
	}
	
	public void insertScore(SQLite sqLite, String name, int score, int streak )
	{
		try {
		String sql = "INSERT INTO scores (NAME, SCORE, STREAK) " +
				"VALUES('" + name + "'," + score + "," + streak + ")";
			sqLite.statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
