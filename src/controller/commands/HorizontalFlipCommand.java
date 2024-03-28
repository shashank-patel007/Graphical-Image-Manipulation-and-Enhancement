package controller.commands;

import model.ImageModelInterface;

/**
 * The HorizontalFlipCommand class is a concrete implementation of AbstractBaseCommand.
 * It represents a command that processes an image by horizontally flipping it and
 * saving the result as a new image using an ImageModel.
 */
public class HorizontalFlipCommand extends AbstractBaseCommand {


  /**
   * Constructs a new HorizontalFlipCommand with the given
   * ImageModel, source image name, and destination image name.
   *
   * @param model                The ImageModel to be used for image manipulation.
   * @param imageName            The name of the source image to be horizontally flipped.
   * @param destinationImageName The name of the destination image to save
   *                             the horizontally flipped result.
   */
  public HorizontalFlipCommand(ImageModelInterface model, String imageName,
                               String destinationImageName) {
    super(model, imageName, destinationImageName);
  }

  /**
   * Processes the image by horizontally flipping it and saving
   * the result as a new image using the ImageModel.
   *
   * @throws Exception if an error occurs during
   *                   the horizontal flip operation.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.horizontalFlipCommand(this.imageName, this.destinationImageName);
  }
}
