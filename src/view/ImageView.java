package view;

import java.util.Scanner;

/**
 * The ImageView class implements the ImageViewInterface and represents an image view within a
 * graphical user interface (GUI) application. It provides methods to retrieve user input and
 * display messages.
 */
public class ImageView implements ImageViewInterface {

  private final Scanner scanner;

  /**
   * Constructs a new ImageView object with an associated
   * Scanner for input from the standard
   * input stream (System.in).
   */
  public ImageView() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Retrieves a command entered by the user from the standard input.
   *
   * @return A String representing the user's input command.
   */
  public String getCommand() {
    return scanner.nextLine();
  }

  /**
   * Displays a message in the image view.
   *
   * @param message The message to be displayed in the image view.
   */
  public void display(String message) {
    System.out.println(message);
  }

}
