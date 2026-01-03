public class Main {

  public static void main(String[] args) {
    printWelcome();
    runCounter();
    printGoodbye();
  }

  public static void printWelcome() {
    IO.println("Welcome to the ICS4U Java Final Project");
    IO.println("--------------------------------------");
  }

  public static void runCounter() {
    for (int i = 1; i <= 5; i++) {
      IO.println("Count: " + i);
    }
  }

  public static void printGoodbye() {
    IO.println("--------------------------------------");
    IO.println("Program finished successfully.");
  }
}