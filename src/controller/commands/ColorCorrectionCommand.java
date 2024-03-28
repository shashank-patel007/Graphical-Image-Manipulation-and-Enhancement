package controller.commands;

import java.util.Optional;

import model.ImageModelInterface;

/**
 * The ColorCorrectionCommand class represents a command for performing color correction
 * on an image. It extends the AbstractSplitCommand class and implements the
 * image manipulation logic by invoking the color correction operation on the
 * specified ImageModelInterface instance.
 */
public class ColorCorrectionCommand extends AbstractSplitCommand {

  /**
   * Constructs a ColorCorrectionCommand with the specified parameters.
   *
   * @param model                The ImageModelInterface instance to perform color correction on.
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image after color correction.
   * @param splitPercentage      An optional parameter specifying
   *                             the percentage of split for the image.
   */
  public ColorCorrectionCommand(ImageModelInterface model, String imageName,
                                String destinationImageName,
                                Optional<Double> splitPercentage) {
    super(model, imageName, destinationImageName, splitPercentage);
  }

  /**
   * This method must be implemented by subclasses to define the specific image
   * manipulation logic. It may throw an Exception to indicate an error.
   *
   * @throws Exception if an error occurs during image processing.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.colorCorrectionCommand(this.imageName,
            this.destinationImageName, this.splitPercentage);
  }
}
