package controller;

import java.awt.image.BufferedImage;

import controller.commands.CommandFactory;
import controller.commands.CommandInterface;
import controller.commands.CommandGroup;
import controller.commands.HistogramCommand;
import model.Image;
import model.ImageModelInterface;
import model.Pixel;
import view.GUIInterface;

/**
 * The GUIController class implements the ImageControllerInterface
 * and Features interface to control the image processing application's
 * graphical user interface.
 */
public class GUIController implements ImageControllerInterface, Features {
  private final GUIInterface view;
  private final ImageModelInterface model;
  private String previewImageName;
  private String displayImageName;
  private final CommandFactory commandFactory;
  private CommandGroup commandGroup;

  /**
   * Constructs a GUIController with the specified view and model.
   *
   * @param view  The graphical user interface view.
   * @param model The image model interface.
   * @throws IllegalArgumentException If the view or model object is missing.
   */
  public GUIController(GUIInterface view,
                       ImageModelInterface model) throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("View Object is missing!");
    }
    if (model == null) {
      throw new IllegalArgumentException("Model Object is missing!");
    }
    this.view = view;
    this.model = model;
    commandFactory = new CommandFactory(model, view);
  }

  /**
   * Updates the view with the specified image.
   *
   * @param imageName The name of the image to update the view with.
   * @throws Exception If an error occurs during the update process.
   */
  private void updateViewWithImage(String imageName) throws Exception {
    BufferedImage image = convertToBufferedImage(model.getImage(imageName));
    view.setImage(image);
    createHistogram("testImage");
    view.setHistogram(convertToBufferedImage(
            model.getImage("testImageHist")));
  }

  /**
   * Creates a histogram for the specified image name.
   *
   * @param imageName The name of the image to create a histogram for.
   * @throws Exception If an error occurs during histogram creation.
   */
  private void createHistogram(String imageName) throws Exception {
    CommandInterface histogram = new HistogramCommand(model, imageName, "testImageHist");
    try {
      histogram.execute();
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

  /**
   * Converts an Image to a BufferedImage.
   *
   * @param image The Image to convert.
   * @return The converted BufferedImage.
   */
  private BufferedImage convertToBufferedImage(Image image) {
    Pixel[][] pixels = image.getPixels();
    int width = pixels.length;
    int height = pixels[0].length;
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Pixel pixel = pixels[x][y];
        int rgb = (pixel.getRed() << 16) | (pixel.getGreen() << 8) | pixel.getBlue();
        bufferedImage.setRGB(x, y, rgb);
      }
    }
    return bufferedImage;
  }

  /**
   * Initializes the image names for preview and display,
   * and adds this controller as a feature to the associated
   * graphical user interface view.
   */
  @Override
  public void process() {
    previewImageName = "previewImage";
    displayImageName = "testImage";
    view.addFeatures(this);
  }

  /**
   * Processes the specified action by invoking the corresponding
   * command and updating the view.
   *
   * @param action The action to process.
   * @return True if the action was processed successfully, false otherwise.
   */
  private boolean processor(String action) {
    try {
      commandGroup = commandFactory.invokeCommand(action);
      if (commandGroup != null)
        if (commandGroup.hasPreview()) {
          boolean previewSuccess = commandGroup.getPreviewCommand().execute();
          if (previewSuccess) {
            updateViewWithImage(previewImageName);
            view.showOperationControls(true);
          }
        } else if (commandGroup.hasApply()) {
          commandGroup.getApplyCommand().execute();
          updateViewWithImage(displayImageName);
        }
    } catch (Exception err) {
      return false;
    }
    return true;
  }

  /**
   * Initiates the process of loading an image,
   * handling user input from the GUI.
   * Displays an error message if the execution fails.
   */
  @Override
  public void loadButton() {
    boolean status = processor("load");
    if (!status) {
      view.display("Error executing Load!");
    }
  }

  /**
   * Initiates the process of applying a blur effect to the image,
   * handling user input from the GUI.
   * Displays an error message if the execution fails.
   */
  @Override
  public void blurButton() {
    boolean status = processor("blur");
    if (!status) {
      view.display("Error executing Blur!");
    }
  }

  /**
   * Initiates the process of applying a sepia effect to the
   * image, handling user input from the GUI.
   * Displays an error message if the execution fails.
   */
  @Override
  public void sepiaButton() {
    boolean status = processor("sepia");
    if (!status) {
      view.display("Error executing Sepia!");
    }
  }

  /**
   * Initiates the process of applying a luma effect to the image,
   * handling user input from the GUI.
   * Displays an error message if the execution fails.
   */
  @Override
  public void lumaButton() {
    boolean status = processor("luma");
    if (!status) {
      view.display("Error executing Luma!");
    }
  }

  /**
   * Initiates the process of applying a red component effect to
   * the image, handling user input from the GUI.
   * Displays an error message if the execution fails.
   */
  @Override
  public void redButton() {
    boolean status = processor("red");
    if (!status) {
      view.display("Error executing Red-Component!");
    }
  }

  /**
   * Initiates the process of applying a green component
   * effect to the image, handling user input from the GUI.
   * Displays an error message if the execution fails.
   */
  @Override
  public void greenButton() {
    boolean status = processor("green");
    if (!status) {
      view.display("Error executing Green-Component!");
    }
  }

  /**
   * Initiates the process of applying a blue component
   * effect to the image, handling user input from the GUI.
   * Displays an error message if the execution fails.
   */
  @Override
  public void blueButton() {
    boolean status = processor("blue");
    if (!status) {
      view.display("Error executing Blue-Component!");
    }
  }

  /**
   * Initiates the process of applying a compression
   * effect to the image, handling user input from the GUI.
   * Displays an error message if the execution fails.
   */
  @Override
  public void compressButton() {
    boolean status = processor("compress");
    if (!status) {
      view.display("Error executing Compress!");
    }
  }

  /**
   * Initiates the process of adjusting levels in the
   * image, handling user input from the GUI.
   * Displays an error message if the execution fails.
   */
  @Override
  public void adjustLevelsButton() {
    boolean status = processor("adjust-levels");
    if (!status) {
      view.display("Error executing Adjust-Levels!");
    }
  }

  /**
   * Initiates the process of applying a color correction
   * effect to the image, handling user input from the GUI.
   * Displays an error message if the execution fails.
   */
  @Override
  public void colorCorrectedButton() {
    boolean status = processor("color-corrected");
    if (!status) {
      view.display("Error executing Color-Corrected!");
    }
  }

  /**
   * Initiates the process of applying a sharpening
   * effect to the image, handling user input from the GUI.
   * Displays an error message if the execution fails.
   */
  @Override
  public void sharpenButton() {
    boolean status = processor("sharpen");
    if (!status) {
      view.display("Error executing Sharpen!");
    }
  }

  /**
   * Initiates the process of flipping the image
   * horizontally, handling user input from the GUI.
   * Displays an error message if the execution fails.
   */
  @Override
  public void horizontalFlipButton() {
    boolean status = processor("horizontal-flip");
    if (!status) {
      view.display("Error executing Horizontal-Flip!");
    }
  }

  /**
   * Initiates the process of flipping the image
   * vertically, handling user input from the GUI.
   * Displays an error message if the execution fails.
   */
  @Override
  public void verticalFlipButton() {
    boolean status = processor("vertical-flip");
    if (!status) {
      view.display("Error executing Vertical-Flip!");
    }
  }

  /**
   * Initiates the process of saving the image,
   * handling user input from the GUI.
   * Displays an error message if the execution fails.
   */
  @Override
  public void saveButton() {
    boolean status = processor("save");
    if (!status) {
      view.display("Error executing Save!");
    }
  }

  /**
   * Initiates the process of canceling the current
   * operation, restoring the display image.
   * Displays an error message if the execution fails.
   */
  @Override
  public void cancelButton() {
    try {
      updateViewWithImage(displayImageName);
    } catch (Exception e) {
      view.display("Error Executing Cancel!");
    }
    view.showOperationControls(false);
  }

  /**
   * Initiates the process of confirming the current
   * operation, applying the changes to the display image.
   * Displays an error message if the execution fails.
   */
  @Override
  public void confirmButton() {
    try {
      if (commandGroup != null && commandGroup.getApplyCommand() != null) {
        commandGroup.getApplyCommand().execute();
        updateViewWithImage(displayImageName);
        view.showOperationControls(false);
      }
    } catch (Exception e) {
      view.display("Error Executing Confirm!");
    }
  }
}
