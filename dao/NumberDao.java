package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Number;

public class NumberDao {

  private Connection connection;
  private final String GET_NUMBERS_QUERY = "SELECT * FROM numbers";
  private final String GET_NUMBER_BY_NAME_QUERY = "SELECT * FROM numbers WHERE name = ?";
  private final String CREATE_NUMBER_BY_NAME_QUERY = "INSERT INTO numbers(name) VALUES (?)";
  private final String UPDATE_NUMBER_BY_NAME_QUERY = "UPDATE numbers SET name = ? WHERE name = ?";
  private final String DELETE_NUMBER_BY_NAME_QUERY = "DELETE FROM numbers WHERE name = ?";


  public NumberDao() {
    connection = DBConnection.getConnection();
  }

  public List<Number> getNumbers() throws SQLException {
    ResultSet rs = connection.prepareStatement(GET_NUMBERS_QUERY).executeQuery();
    List<Number> numbers = new ArrayList<Number>();

    while (rs.next()) {
      numbers.add(populateNumbers(rs.getInt(1), rs.getString(2)));
    }

    return numbers;
  }

  private Number populateNumbers(int id, String name) {
    return new Number(id, name);
  }

  public Number getNumberByName(String name) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(GET_NUMBER_BY_NAME_QUERY);
    ps.setString(1, name);
    ResultSet rs = ps.executeQuery();
    rs.next();
    return populateNumbers(rs.getInt(1), rs.getString(2));
  }

  public void addNewNumber(String name) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(CREATE_NUMBER_BY_NAME_QUERY);
    ps.setString(1, name);
    ps.executeUpdate();
  }

  public void updateNumberByName(String nameNew, String nameOld) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(UPDATE_NUMBER_BY_NAME_QUERY);
    ps.setString(1, nameNew);
    ps.setString(2, nameOld);
    ps.executeUpdate();
  }

  public void deleteNumberByName(String name) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(DELETE_NUMBER_BY_NAME_QUERY);
    ps.setString(1, name);
    ps.executeUpdate();
  }
}