package controller.commands;

import model.ImageModelInterface;

/**
 * The GreenComponentCommand class is a concrete implementation of AbstractBaseCommand.
 * It represents a command that processes an image by extracting the green component
 * and saving it as a new image using an ImageModel.
 */
public class GreenComponentCommand extends AbstractBaseCommand {


  /**
   * Constructs a new GreenComponentCommand with the given
   * ImageModel, source image name, and destination image name.
   *
   * @param model                The ImageModel to be used for
   *                             image manipulation.
   * @param imageName            The name of the source image to extract
   *                             the green component from.
   * @param destinationImageName The name of the destination image to save
   *                             the green component.
   */
  public GreenComponentCommand(ImageModelInterface model, String imageName,
                               String destinationImageName) {
    super(model, imageName, destinationImageName);
  }

  /**
   * Processes the image by extracting the green component and
   * saving it as a new image using the ImageModel.
   *
   * @throws Exception if there are issues during the green component operation.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.greenComponentCommand(this.imageName, this.destinationImageName);
  }
}
