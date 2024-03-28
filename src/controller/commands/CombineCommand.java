package controller.commands;

import model.ImageModelInterface;

/**
 * The CombineCommand class is a concrete implementation of AbstractBaseCommand.
 * It represents a command that processes an image by combining three separate
 * color components (Red, Green, and Blue) into a single image and saving the
 * combined result as a new image using an ImageModel.
 */
public class CombineCommand extends AbstractBaseCommand {

  /**
   * Constructs a new CombineCommand with the given ImageModel, destination image names
   * for the red, green, and blue color components, and the source image name.
   *
   * @param model                     The ImageModel to be used for image manipulation.
   * @param destinationImageRedName   The name of the destination image for
   *                                  the red color component.
   * @param imageName                 The name of the source image to be combined.
   * @param destinationImageGreenName The name of the destination image for the
   *                                  green color component.
   * @param destinationImageBlueName  The name of the destination image for the
   *                                  blue color component.
   */
  public CombineCommand(ImageModelInterface model,
                        String destinationImageRedName,
                        String imageName,
                        String destinationImageGreenName,
                        String destinationImageBlueName) {

    super(model, imageName, destinationImageRedName,
            destinationImageGreenName, destinationImageBlueName);
  }

  /**
   * Processes the image by combining its red, green, and blue color components
   * into a single image and saves the combined result as three separate images
   * (red, green, and blue) using the ImageModel.
   *
   * @throws Exception if there are issues during the combine operation.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.combineCommand(this.redImageName, this.greenImageName,
            this.blueImageName, this.imageName);
  }
}
