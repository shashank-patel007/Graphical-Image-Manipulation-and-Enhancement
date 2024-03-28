import java.io.FileNotFoundException;

import controller.GUIController;
import controller.ImageController;
import controller.ImageControllerInterface;
import model.ImageModel;
import model.ImageModelInterface;
import view.GUIView;
import view.ImageView;
import view.ImageViewInterface;

/**
 * The Application class serves as the entry point for the image
 * processing application. It initializes the main components
 * of the application, including the view, model, and controller,
 * and starts the image processing.
 */
public class Application {

  /**
   * The main method of the application, serving as the entry point.
   * It initializes the view, model, and controller components,
   * and starts the image processing based on command-line arguments
   * or a GUI user interface.
   *
   * @param args Command-line arguments, where the first argument can be "-file"
   *             followed by the path to a script file containing image processing commands.
   *             If no command-line arguments are provided, the application processes
   *             images interactively.
   * @throws FileNotFoundException If a specified script file is not found.
   */
  public static void main(String[] args) throws FileNotFoundException {

    ImageModelInterface model = new ImageModel();
    ImageControllerInterface controller;
    ImageViewInterface view;

    if (args.length == 0) {
      view = new GUIView();
      controller = new GUIController((GUIView) view, model);
      controller.process();
    } else {
      view = new ImageView();
      controller = new ImageController(view, model);
      if (args.length == 2 && args[0].equals("-file")) {
        ((ImageController) controller).runScript(args[1]);
      } else if (args.length == 1 && args[0].equals("-text")) {
        controller.process();
      }
    }
  }
}