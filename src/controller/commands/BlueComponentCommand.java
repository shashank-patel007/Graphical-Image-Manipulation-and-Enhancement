package controller.commands;

import model.ImageModelInterface;

/**
 * The BlueComponentCommand class is a concrete implementation of AbstractBaseCommand.
 * It represents a command that processes an image by extracting the blue component
 * and saving it as a new image using an ImageModel.
 */
public class BlueComponentCommand extends AbstractBaseCommand {

  /**
   * Constructs a new BlueComponentCommand with the
   * given ImageModel, source image name, and destination image name.
   *
   * @param model                The ImageModel to be used for image manipulation.
   * @param imageName            The name of the source image to extract the blue component from.
   * @param destinationImageName The name of the destination image to save the blue component.
   */
  public BlueComponentCommand(ImageModelInterface model, String imageName,
                              String destinationImageName) {
    super(model, imageName, destinationImageName);
  }

  /**
   * Processes the image by extracting the blue component
   * and saving it as a new image using the ImageModel.
   *
   * @throws Exception if there are issues during the blue component operation.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.blueComponentCommand(this.imageName, this.destinationImageName);
  }
}
