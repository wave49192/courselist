package courselist.persistence;

import courselist.Course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CourseDao {
	private Connection connection;

	public CourseDao(Connection connection) {
		this.connection = connection;
	}

	public Course get(int id) {
		Course course = null;
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM courses WHERE id = " + id;
			ResultSet rs = statement.executeQuery(query);
			if (rs.next()) {
				course = makeCourse(id, rs);
			}
		}
		catch (SQLException ex) {
			throw new RuntimeException("Problem getting course from database", ex);
		}

		return course;
	}

	private Course makeCourse(int id, ResultSet rs) throws SQLException {
		Course course;
		String courseNumber = rs.getString("course_number");
		String title = rs.getString("title");
		int credits = rs.getInt("credits");
		double difficulty = rs.getDouble("difficulty");
		course = new Course(courseNumber, title, credits);
		course.setDifficulty(difficulty);
		course.setId(id);
		return course;
	}
}