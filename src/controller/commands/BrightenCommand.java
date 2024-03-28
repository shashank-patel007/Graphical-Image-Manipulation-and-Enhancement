package controller.commands;

import model.ImageModelInterface;

/**
 * The BrightenCommand class is a concrete implementation of AbstractBaseCommand.
 * It represents a command that processes an image by brightening it and saving the
 * result as a new image using an ImageModel. Brightening is achieved by increasing
 * the pixel values in the image.
 */
public class BrightenCommand extends AbstractBaseCommand {
  private final int increment;

  /**
   * Constructs a new BrightenCommand with the given
   * ImageModel, increment value, source image name,
   * and destination image name.
   *
   * @param model                The ImageModel to be used for image manipulation.
   * @param increment            The amount by which to brighten the image
   *                             (positive for brightening, negative for darkening).
   * @param imageName            The name of the source image to be brightened.
   * @param destinationImageName The name of the destination image to save the brightened result.
   */
  public BrightenCommand(ImageModelInterface model, int increment,
                         String imageName, String destinationImageName) {
    super(model, imageName, destinationImageName);
    this.increment = increment;
  }

  /**
   * Processes the image by brightening it based
   * on the specified increment value and saves the result
   * as a new image using the ImageModel.
   *
   * @throws Exception if there are issues during the brightened operation.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.brightenCommand(imageName, destinationImageName, this.increment);
  }
}
