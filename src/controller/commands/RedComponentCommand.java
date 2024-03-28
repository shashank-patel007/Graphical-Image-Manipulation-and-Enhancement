package controller.commands;

import model.ImageModelInterface;

/**
 * The RedComponentCommand class is a concrete implementation of AbstractBaseCommand.
 * It represents a command that processes an image by extracting the red component
 * and saving the result as a new image using an ImageModel.
 * The red component represents the red color channel of an image.
 */
public class RedComponentCommand extends AbstractBaseCommand {

  /**
   * Constructs a new RedComponentCommand with the given ImageModel, source image name,
   * and destination image name.
   *
   * @param model                The ImageModel to be used for image manipulation.
   * @param imageName            The name of the source image from
   *                             which the red component will be extracted.
   * @param destinationImageName The name of the destination image to
   *                             save the red component.
   */
  public RedComponentCommand(ImageModelInterface model, String imageName,
                             String destinationImageName) {
    super(model, imageName, destinationImageName);
  }

  /**
   * Processes the image by extracting the red component
   * and saving the result as a new image using the ImageModel.
   *
   * @throws Exception if there are issues during the red component operation.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.redComponentCommand(this.imageName, this.destinationImageName);
  }
}
