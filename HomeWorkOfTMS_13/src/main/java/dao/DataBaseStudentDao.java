package dao;

import connections.MySqlConnection;
import entity.Student;
import exception.DuplicateStudentException;
import exception.NotStudentException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseStudentDao implements StudentDao{

    @Override
    public synchronized void add(Student student) throws DuplicateStudentException {
        try (Connection connection = MySqlConnection.getConnection()) {
            connection.setAutoCommit(false);
            // step 1
            String sql = "INSERT INTO students VALUES(?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, student.getId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getEmail());
            statement.executeUpdate();
            // step 2
            setMarks(connection, student.getId(), student.getMarks());
            connection.commit();
        } catch (SQLException e) {
            throw new DuplicateStudentException(student.getId());
        }
    }

    @Override
    public List<Student> getList() {
        String sql = "SELECT s.id, s.name, s.email, avg(sm.mark) average_mark FROM " +
                "students s LEFT JOIN students_marks sm ON s.id = sm.students_id GROUP BY sm.students_id";
        return getStudents(sql);
    }

    private synchronized List<Student> getStudents(String sql) {
        List<Student> students = new ArrayList<>();
        try (Connection connection = MySqlConnection.getConnection()) {
            // step 1
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next())
                students.add(new Student(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("email"),
                        result.getDouble("average_mark")
                ));

            // step 2
            for (Student student : students){
                student.setMarks(getMarks(connection, student.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public List<Student> getImprovedList(String sql) { return getStudents(sql); }

    @Override
    public void remove(Student student) {
        try (Connection connection = MySqlConnection.getConnection()) {
            connection.setAutoCommit(false);
            // step 1
            deleteMarks(connection, student.getId());
            // step 2
            String sql = "DELETE FROM students WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, student.getId());
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Student student) {
        try (Connection connection = MySqlConnection.getConnection()) {
            String sql = "UPDATE students SET name = ? , email = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setInt(3, student.getId());
            statement.execute();
            deleteMarks(connection, student.getId());
            try {
                setMarks(connection, student.getId(), student.getMarks());
            }catch (SQLException e){
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentByName(Student student) throws NotStudentException {
        Student newStudent = null;
            try (Connection connection = MySqlConnection.getConnection()) {
                String sql = "SELECT s.id, s.name, s.email, avg(sm.mark) average_mark FROM " +
                        "students s LEFT JOIN students_marks sm ON s.id = sm.students_id WHERE s.name = ? GROUP BY sm.students_id";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, student.getName());
                ResultSet result = statement.executeQuery();
                while (result.next())
                    newStudent = new Student(
                            result.getInt("id"),
                            result.getString("name"),
                            result.getString("email"),
                            result.getDouble("average_mark"));
                if (newStudent == null)
                    throw new NotStudentException(student.getName());
                newStudent.setMarks(getMarks(connection, newStudent.getId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return newStudent;
    }






    private void setMarks(Connection connection, int id, List<Integer> marks) throws SQLException {
        String sql = "INSERT INTO students_marks VALUES(null, ?, ?)";
        PreparedStatement markStatement = connection.prepareStatement(sql);
        for (int mark : marks){
            markStatement.setInt(1, id);
            markStatement.setInt(2,mark);
            markStatement.execute();
        }
    }

    private void deleteMarks(Connection connection, int id) throws SQLException {
        String sql = "DELETE FROM students_marks WHERE students_id = ?";
        PreparedStatement markStatement = connection.prepareStatement(sql);
        markStatement.setInt(1, id);
        markStatement.executeUpdate();
    }

    private List<Integer> getMarks(Connection connection, int id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet marksSet = statement.executeQuery("SELECT mark FROM students_marks WHERE students_id = " + id);
        List<Integer> marks = new ArrayList<>();
        while (marksSet.next())
            marks.add(marksSet.getInt("mark"));
        return marks;
    }
}
