package controller;


/**
 * The ImageControllerInterface defines the contract for an image controller in the
 * context of an image processing application. It specifies two essential methods
 * for processing user commands and controlling the application's behavior.
 * Implementing classes are responsible for interpreting user commands, executing
 * image processing operations, and managing interactions between the user interface
 * and the image processing model.
 */
public interface ImageControllerInterface {

  /**
   * Starts the process of handling user commands and controlling
   * the application's behavior.
   * This method typically runs in a loop, continuously reading and
   * executing user commands.
   */
  void process();

}
