package controller.commands;

import model.ImageModelInterface;

/**
 * The VerticalFlipCommand class is a concrete implementation of AbstractBaseCommand.
 * It represents a command that processes an image by performing a vertical flip operation
 * and saving the result as a new image using an ImageModel.
 * Vertical flip reflects the image along its horizontal axis, creating a mirror image.
 */
public class VerticalFlipCommand extends AbstractBaseCommand {

  /**
   * Constructs a new VerticalFlipCommand with the given ImageModel, source image name,
   * and destination image name.
   *
   * @param model                The ImageModel to be used for image manipulation.
   * @param imageName            The name of the source image to which
   *                             the vertical flip operation will be applied.
   * @param destinationImageName The name of the destination image to save the
   *                             vertically flipped image.
   */
  public VerticalFlipCommand(ImageModelInterface model, String imageName,
                             String destinationImageName) {
    super(model, imageName, destinationImageName);
  }

  /**
   * Processes the image by performing a vertical flip operation
   * and saving the result as a new image using the ImageModel.
   *
   * @throws Exception if there are issues during the vertical flip operation.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.verticalFlipCommand(this.imageName, this.destinationImageName);
  }
}
