package controller.commands;

import java.util.Optional;

import model.ImageModelInterface;
import view.GUIInterface;

/**
 * The CommandFactory class creates Command objects based on
 * the user's action, such as loading, saving, and applying
 * various image processing operations.
 */
public class CommandFactory {

  private final ImageModelInterface model;
  private final GUIInterface view;
  private AppState state;

  /**
   * Constructs a CommandFactory with the specified ImageModelInterface and GUIView.
   *
   * @param model The image model interface.
   * @param view  The graphical user interface view.
   */
  public CommandFactory(ImageModelInterface model, GUIInterface view) {
    this.model = model;
    this.view = view;
    this.state = AppState.NO_IMAGE_LOADED;
  }

  /**
   * Checks if the current status allows certain operations to be performed.
   *
   * @return True if the current status allows operations, false otherwise.
   */
  private boolean checkStatus() {
    return (state == AppState.IMAGE_LOADED || state == AppState.IMAGE_SAVED);
  }

  /**
   * Handles the logic for processing the "load" button action.
   *
   * @return A CommandGroup containing the preview and complete Command objects, or
   *         null if the command cannot be executed.
   */
  private CommandGroup handleLoadButton() {
    if (state == AppState.IMAGE_LOADED) {
      boolean shouldProceed = view.confirmLoadButton();
      if (!shouldProceed) {
        return null;
      }
      state = AppState.NO_IMAGE_LOADED;
    }
    if (state == AppState.NO_IMAGE_LOADED || state == AppState.IMAGE_SAVED) {
      String path = view.loadImagePath();
      if (path != null) {
        CommandInterface loadCommand = new LoadCommand(model, path, "testImage");
        state = AppState.IMAGE_LOADED;
        return new CommandGroup(null, loadCommand);
      }
    } else {
      view.display("Cannot load another image without saving the image first!");
    }
    return null;
  }

  /**
   * Handles the logic for processing the "save" button action.
   *
   * @return A CommandGroup containing the preview and complete Command objects, or
   *         null if the command cannot be executed.
   */
  private CommandGroup handleSaveButton() {
    if (checkStatus()) {
      String path = view.saveImagePath();
      if (path != null) {
        CommandInterface saveCommand = new SaveCommand(model, path, "testImage");
        state = AppState.IMAGE_SAVED;
        return new CommandGroup(null, saveCommand);
      }
    } else {
      view.display("Cannot save the image without loading the image first!");
    }
    return null;
  }

  /**
   * Handles the logic for processing the "compress" button action.
   *
   * @return A CommandGroup containing the preview and complete Command objects, or
   *         null if the command cannot be executed.
   */
  private CommandGroup handleCompressButton() {
    if (state == AppState.IMAGE_LOADED || state == AppState.IMAGE_SAVED) {
      Optional<Double> splitPercentage = view.promptCompressPercentage();
      if (splitPercentage.isPresent()) {
        CommandInterface verticalFlipPrevCommand = new CompressCommand(model, splitPercentage.get(),
                "testImage", "previewImage");
        CommandInterface verticalFlipCompleteCommand =
                new CompressCommand(model, splitPercentage.get(),
                "testImage", "testImage");
        state = AppState.IMAGE_LOADED;
        return new CommandGroup(verticalFlipPrevCommand, verticalFlipCompleteCommand);
      }
    } else {
      view.display("Cannot apply compress without loading an image first!");
    }
    return null;
  }

  /**
   * Handles the logic for processing the "adjust-levels" button action.
   *
   * @return A CommandGroup containing the preview and complete Command objects, or
   *         null if the command cannot be executed.
   */
  private CommandGroup handleAdjustLevels() {
    if (state == AppState.IMAGE_LOADED || state == AppState.IMAGE_SAVED) {
      Optional<int[]> adjustLevels = view.promptForAdjustLevels();
      if (adjustLevels.isPresent()) {
        Optional<Double> splitPercentage = view.promptPercentage();
        if (splitPercentage.isPresent()) {
          CommandInterface colorCorrectedPrevCommand = new LevelsAdjustmentCommand(model,
                  adjustLevels.get()[0], adjustLevels.get()[1], adjustLevels.get()[2],
                  "testImage", "previewImage", splitPercentage);
          CommandInterface colorCorrectedCompleteCommand = new LevelsAdjustmentCommand(model,
                  adjustLevels.get()[0], adjustLevels.get()[1], adjustLevels.get()[2],
                  "testImage", "testImage", Optional.of(100.0));
          state = AppState.IMAGE_LOADED;
          return new CommandGroup(colorCorrectedPrevCommand, colorCorrectedCompleteCommand);
        }
      }
    } else {
      view.display("Cannot apply adjust-levels without loading an image first!");
    }
    return null;
  }

  /**
   * Creates a CommandInterface object for split percentage
   * commands based on the specified parameters.
   *
   * @param commandType     The type of image processing command.
   * @param targetImageName The name of the target image.
   * @param splitPercentage The split percentage for the command.
   * @return A CommandInterface object representing the specified command.
   * @throws IllegalArgumentException If the command type is unknown or invalid.
   */
  private CommandInterface createSplitPercentageCommand(String commandType, String targetImageName,
                                                        Optional<Double> splitPercentage) {
    switch (commandType) {
      case "blur":
        return new BlurCommand(model, "testImage", targetImageName, splitPercentage);
      case "sepia":
        return new SepiaCommand(model, "testImage", targetImageName, splitPercentage);
      case "luma":
        return new LumaComponentCommand(model, "testImage", targetImageName, splitPercentage);
      case "sharpen":
        return new SharpenCommand(model, "testImage", targetImageName, splitPercentage);
      case "color-corrected":
        return new ColorCorrectionCommand(model, "testImage", targetImageName, splitPercentage);
      default:
        throw new IllegalArgumentException("Unsupported command type: " + commandType);
    }
  }

  /**
   * Creates a CommandInterface object based on the
   * specified command type and target image name.
   *
   * @param commandType     The type of image processing command.
   * @param targetImageName The name of the target image.
   * @return A CommandInterface object representing the specified command.
   * @throws IllegalArgumentException If the command type is unknown or invalid.
   */
  private CommandInterface createCommand(String commandType, String targetImageName) {
    switch (commandType) {
      case "red":
        return new RedComponentCommand(model, "testImage", targetImageName);
      case "green":
        return new GreenComponentCommand(model, "testImage", targetImageName);
      case "blue":
        return new BlueComponentCommand(model, "testImage", targetImageName);
      case "horizontal-flip":
        return new HorizontalFlipCommand(model, "testImage", targetImageName);
      case "vertical-flip":
        return new VerticalFlipCommand(model, "testImage", targetImageName);
      default:
        throw new IllegalArgumentException("Unsupported command type: " + commandType);
    }
  }

  /**
   * Creates a CommandGroup containing preview and complete
   * Command objects for split percentage commands.
   *
   * @param commandType The type of image processing command.
   * @return A CommandGroup containing the preview and complete Command objects,
   *         or null if the command cannot be executed.
   */
  private CommandGroup createSplitFilterCommand(String commandType) {
    if (!checkStatus()) {
      view.display("Cannot apply " + commandType + " without loading an image first!");
      return null;
    }

    Optional<Double> splitPercentage = view.promptPercentage();
    if (!splitPercentage.isPresent()) {
      return null;
    }

    CommandInterface previewCommand = createSplitPercentageCommand(commandType, "previewImage",
            splitPercentage);
    CommandInterface completeCommand = createSplitPercentageCommand(commandType, "testImage",
            Optional.of(100.0));

    return new CommandGroup(previewCommand, completeCommand);
  }

  /**
   * Creates a CommandGroup containing preview
   * and complete Command objects for non-split percentage commands.
   *
   * @param commandType The type of image processing command.
   * @return A CommandGroup containing the preview and complete Command objects,
   *         or null if the command cannot be executed.
   */
  private CommandGroup createFilterCommand(String commandType) {
    if (!checkStatus()) {
      view.display("Cannot apply " + commandType + " without loading an image first!");
      return null;
    }

    CommandInterface previewCommand = createCommand(commandType, "previewImage");
    CommandInterface completeCommand = createCommand(commandType, "testImage");
    return new CommandGroup(previewCommand, completeCommand);
  }

  /**
   * Invokes a command based on the provided action command, creating
   * and returning the appropriate Command objects.
   *
   * @param actionCommand The action command representing the user's request.
   * @return A CommandPair containing the preview and complete Command objects, or
   *         null if the command cannot be executed.
   * @throws IllegalArgumentException If the action command is unknown or invalid.
   */
  public CommandGroup invokeCommand(String actionCommand) throws IllegalArgumentException {
    switch (actionCommand) {
      case "load":
        return handleLoadButton();
      case "save":
        return handleSaveButton();
      case "blur":
      case "sepia":
      case "luma":
      case "sharpen":
      case "color-corrected":
        return createSplitFilterCommand(actionCommand);
      case "compress":
        return handleCompressButton();
      case "red":
      case "green":
      case "blue":
      case "horizontal-flip":
      case "vertical-flip":
        return createFilterCommand(actionCommand);
      case "adjust-levels":
        return handleAdjustLevels();
      default:
        throw new IllegalArgumentException("Unknown command " + actionCommand);
    }
  }
}