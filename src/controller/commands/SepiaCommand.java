package controller.commands;

import java.util.Optional;

import model.ImageModelInterface;

/**
 * The SepiaCommand class is a concrete implementation of AbstractBaseCommand.
 * It represents a command that processes an image by applying a sepia effect
 * and saving the result as a new image using an ImageModel.
 * Sepia is a brownish-gray tint that gives images an old-fashioned, vintage appearance.
 */
public class SepiaCommand extends AbstractSplitCommand {
  /**
   * Constructs a new SepiaCommand with the given ImageModel, source image name,
   * and destination image name.
   *
   * @param model                The ImageModel to be used for image manipulation.
   * @param imageName            The name of the source image to which
   *                             the sepia effect will be applied.
   * @param destinationImageName The name of the destination image to save
   *                             the sepia-processed image.
   * @param splitPercentage      The Percentage value in which image to split.
   */
  public SepiaCommand(ImageModelInterface model, String imageName, String destinationImageName,
                      Optional<Double> splitPercentage) {
    super(model, imageName, destinationImageName, splitPercentage);
  }

  /**
   * Processes the image by applying a sepia effect and
   * saving the result as a new image using the ImageModel.
   *
   * @throws Exception if there are issues during the sepia operation.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.sepiaCommand(this.imageName, this.destinationImageName, splitPercentage);
  }
}
