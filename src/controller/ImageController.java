package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import controller.commands.BlueComponentCommand;
import controller.commands.BlurCommand;
import controller.commands.BrightenCommand;
import controller.commands.ColorCorrectionCommand;
import controller.commands.CombineCommand;
import controller.commands.CommandInterface;
import controller.commands.CompressCommand;
import controller.commands.GreenComponentCommand;
import controller.commands.HistogramCommand;
import controller.commands.HorizontalFlipCommand;
import controller.commands.IntensityComponentCommand;
import controller.commands.LevelsAdjustmentCommand;
import controller.commands.LoadCommand;
import controller.commands.LumaComponentCommand;
import controller.commands.RGBSplit;
import controller.commands.RedComponentCommand;
import controller.commands.SaveCommand;
import controller.commands.SepiaCommand;
import controller.commands.SharpenCommand;
import controller.commands.ValueComponentCommand;
import controller.commands.VerticalFlipCommand;
import model.ImageModelInterface;
import view.ImageViewInterface;

/**
 * The ImageController class is responsible for controlling image processing commands
 * and interactions between the user interface (ImageView) and the image processing model
 * (ImageModel). It processes user commands, executes image processing operations, and
 * handles the execution of script files.
 * The controller supports a variety of image processing commands, such as loading, saving,
 * blurring, sharpening, applying filters, and more. It parses user commands and delegates
 * the execution to the appropriate command classes.
 */
public class ImageController implements ImageControllerInterface {

  private final ImageViewInterface view;
  private final ImageModelInterface model;
  private Map<String, Function<String[], CommandInterface>> commandMap;

  /**
   * Constructs a new ImageController with the specified ImageView and ImageModel.
   *
   * @param view  The ImageView for displaying user interactions and results.
   * @param model The ImageModel for image processing operations.
   * @throws IllegalArgumentException when model or view is null.
   */
  public ImageController(ImageViewInterface view,
                         ImageModelInterface model) throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("View Object is missing!");
    }
    if (model == null) {
      throw new IllegalArgumentException("Model Object is missing!");
    }
    this.view = view;
    this.model = model;
    initializeCommandMap();
  }


  /**
   * Initializes the command map with various
   * image processing commands.
   * Commands are mapped to their corresponding action tokens,
   * creating the appropriate Command objects.
   */
  private void initializeCommandMap() {
    commandMap = new HashMap<>();

    commandMap.put("load", tokens -> new LoadCommand(model, tokens[1], tokens[2]));
    commandMap.put("save", tokens -> new SaveCommand(model, tokens[1], tokens[2]));

    commandMap.put("blur", tokens -> {
      Optional<Double> splitPercentage = tokens.length > 3 ?
              Optional.of(Double.parseDouble(tokens[4])) : Optional.empty();
      return new BlurCommand(model, tokens[1], tokens[2], splitPercentage);
    });

    commandMap.put("sharpen", tokens -> {
      Optional<Double> splitPercentage = tokens.length > 3 ?
              Optional.of(Double.parseDouble(tokens[4])) : Optional.empty();
      return new SharpenCommand(model, tokens[1], tokens[2], splitPercentage);
    });

    commandMap.put("sepia", tokens -> {
      Optional<Double> splitPercentage = tokens.length > 3 ?
              Optional.of(Double.parseDouble(tokens[4])) : Optional.empty();
      return new SepiaCommand(model, tokens[1], tokens[2], splitPercentage);
    });

    commandMap.put("red-component", tokens -> new RedComponentCommand(model, tokens[1], tokens[2]));
    commandMap.put("green-component", tokens ->
            new GreenComponentCommand(model, tokens[1], tokens[2]));
    commandMap.put("blue-component", tokens ->
            new BlueComponentCommand(model, tokens[1], tokens[2]));

    commandMap.put("value-component", tokens -> {
      Optional<Double> splitPercentage = tokens.length > 3 ?
              Optional.of(Double.parseDouble(tokens[4])) : Optional.empty();
      return new ValueComponentCommand(model, tokens[1], tokens[2], splitPercentage);
    });

    commandMap.put("intensity-component", tokens -> {
      Optional<Double> splitPercentage = tokens.length > 3 ?
              Optional.of(Double.parseDouble(tokens[4])) : Optional.empty();
      return new IntensityComponentCommand(model, tokens[1], tokens[2], splitPercentage);
    });

    commandMap.put("luma-component", tokens -> {
      Optional<Double> splitPercentage = tokens.length > 3 ?
              Optional.of(Double.parseDouble(tokens[4])) : Optional.empty();
      return new LumaComponentCommand(model, tokens[1], tokens[2], splitPercentage);
    });

    commandMap.put("brighten", tokens -> {
      int increment = Integer.parseInt(tokens[1]);
      return new BrightenCommand(model, increment, tokens[2], tokens[3]);
    });

    commandMap.put("horizontal-flip", tokens ->
            new HorizontalFlipCommand(model, tokens[1], tokens[2]));
    commandMap.put("vertical-flip", tokens ->
            new VerticalFlipCommand(model, tokens[1], tokens[2]));
    commandMap.put("rgb-split", tokens ->
            new RGBSplit(model, tokens[1], tokens[2], tokens[3], tokens[4]));
    commandMap.put("rgb-combine", tokens ->
            new CombineCommand(model, tokens[2], tokens[1], tokens[3], tokens[4]));

    commandMap.put("compress", tokens -> {
      double percentage = Double.parseDouble(tokens[1]);
      return new CompressCommand(model, percentage, tokens[2], tokens[3]);
    });

    commandMap.put("histogram", tokens -> new HistogramCommand(model, tokens[1], tokens[2]));

    commandMap.put("color-correct", tokens -> {
      Optional<Double> splitPercentage = tokens.length > 3 ?
              Optional.of(Double.parseDouble(tokens[4])) : Optional.empty();
      return new ColorCorrectionCommand(model, tokens[1], tokens[2], splitPercentage);
    });

    commandMap.put("levels-adjust", tokens -> {
      Optional<Double> splitPercentage = tokens.length > 6 ?
              Optional.of(Double.parseDouble(tokens[7])) : Optional.empty();
      return new LevelsAdjustmentCommand(model, Integer.parseInt(tokens[1]),
              Integer.parseInt(tokens[2]),
              Integer.parseInt(tokens[3]), tokens[4], tokens[5], splitPercentage);
    });

  }

  /**
   * Starts processing user commands by continuously
   * reading and executing commands from the view.
   * It also handles and displays error messages for commands that fail.
   */
  @Override
  public void process() {
    boolean status;
    while (true) {
      String command = view.getCommand();
      String[] tokens = command.split(" ");

      if (tokens[0].equals("q")) {
        break;
      }
      status = this.processor(command);

      if (!status) {
        view.display("error executing " + tokens[0]);
      }
    }
  }

  /**
   * Processes the given command by delegating the execution
   * to the appropriate command classes.
   *
   * @param command The user command to process.
   * @return True if the command was executed
   *         successfully, false otherwise.
   */
  private boolean processor(String command) {
    boolean status = false;
    try {
      String[] tokens = command.split(" ");
      if (tokens[0].equals("run")) {
        status = runScript(tokens[1]);
        view.display(tokens[0] + " executed successfully");
      } else {
        Function<String[], CommandInterface> commandFunction = commandMap.get(tokens[0]);
        if (commandFunction != null) {
          CommandInterface feature = commandFunction.apply(tokens);
          status = feature.execute();
          if (status) {
            view.display(tokens[0] + " executed successfully");
          }
        } else {
          throw new IllegalArgumentException("Invalid Input: " + tokens[0]);
        }
      }
    } catch (Exception e) {
      view.display(e.getMessage());
    }
    return status;
  }


  /**
   * Executes a script file containing a sequence of
   * image processing commands.
   *
   * @param path The path to the script file.
   * @return True if all commands in the script were executed
   *         successfully, false otherwise.
   * @throws FileNotFoundException when an invalid path is given.
   */
  public boolean runScript(String path) throws FileNotFoundException {
    boolean status = false;
    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.trim().isEmpty()) {
          continue;
        }
        status = this.processor(line);
      }
    } catch (IOException e) {
      throw new FileNotFoundException("File not Found!");
    }
    return status;
  }
}

