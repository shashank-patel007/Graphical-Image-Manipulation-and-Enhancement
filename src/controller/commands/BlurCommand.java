package controller.commands;

import java.util.Optional;

import model.ImageModelInterface;

/**
 * The BlurCommand class is a concrete implementation of AbstractBaseCommand.
 * It represents a command that processes an image by applying a blur effect
 * and saving the result as a new image using an ImageModel.
 */
public class BlurCommand extends AbstractSplitCommand {

  /**
   * Constructs a new BlurCommand with the given ImageModel, source image name, and
   * destination image name.
   *
   * @param model                The ImageModel to be used for image manipulation.
   * @param imageName            The name of the source image to apply the blur effect to.
   * @param destinationImageName The name of the destination image to save the blurred result.
   * @param splitPercentage      The Percentage value in which image to split.
   */
  public BlurCommand(ImageModelInterface model, String imageName, String destinationImageName,
                     Optional<Double> splitPercentage) {
    super(model, imageName, destinationImageName, splitPercentage);
  }

  /**
   * Processes the image by applying a blur effect and
   * saving the result as a new image using the ImageModel.
   *
   * @throws Exception if there are issues during the blur operation.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.blurCommand(this.imageName, this.destinationImageName, this.splitPercentage);
  }
}
