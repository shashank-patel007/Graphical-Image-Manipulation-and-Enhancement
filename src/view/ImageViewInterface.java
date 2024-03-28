package view;

/**
 * The ImageViewInterface defines the contract for an interface that represents an image view
 * within a graphical user interface (GUI) application.
 */
public interface ImageViewInterface {

  /**
   * Retrieves the command associated with this image view.
   *
   * @return A String representing the command associated with the image view.
   */
  String getCommand();

  /**
   * Displays a message in the image view.
   *
   * @param message The message to be displayed in the image view.
   */
  void display(String message);
}
