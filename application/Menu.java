package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.NumberDao;
import entity.Number;

public class Menu {

  private NumberDao numberDao = new NumberDao();
  private Scanner scanner = new Scanner(System.in);
  private List<String> options = Arrays.asList(
		  "Display Numbers",
		  "Add Number",
		  "Update Number",
		  "Delete Number");

  public void start() {
    String selection = "";

    do {
      printMenu();
      selection = scanner.nextLine();

      try {
        if (selection.equals("1")) {
          displayNumbers();
        } else if (selection.equals("2")) {
          createNumber();
        } else if (selection.equals("3")) {
          updateNumber();
        } else if (selection.equals("4")) {
          deleteNumber();
        } else if (selection.equals("-1")) {
          System.out.println("Exiting...");
        } else {
          System.out.println("Invalid input");
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }

      if (!selection.equals("-1")) {
        System.out.println("Press enter to continue..");
        scanner.nextLine();
      }

    } while (!selection.equals("-1"));
  }
  
  private void printMenu() {
    System.out.println("Select an Option: -1 to exit program\n--------------");
    for (int i = 0; i < options.size(); i++) {
      System.out.println(i + 1 + ") " + options.get(i));
    }
  }

  private void displayNumbers() throws SQLException {
    List<Number> numbers = numberDao.getNumbers();
    for (Number number : numbers) {
      System.out.println(number.getNumberId() + ": " + number.getName());
    }
  }

  private void createNumber() throws SQLException {
    System.out.print("Enter new number to add!: ");
    String name = scanner.nextLine();
    numberDao.addNewNumber(name);
  }

  private void updateNumber() throws SQLException {
    System.out.println("Enter number to update: ");
    System.out.println("Example if you want to change 1: 20 to 1: 21 enter 20");
    String nameOld = scanner.nextLine();
    System.out.println("Enter new number name: ");
    String nameNew = scanner.nextLine();
    numberDao.updateNumberByName(nameNew, nameOld);
  }

  private void deleteNumber() throws SQLException {
    System.out.println("Enter number to delete: ");
    System.out.println("Example if you want to delete 1: 20 enter 20");
    String name = scanner.nextLine();
    numberDao.deleteNumberByName(name);
  }
}