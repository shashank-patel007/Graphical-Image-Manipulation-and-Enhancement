package controller.commands;

import java.util.Optional;

import model.ImageModelInterface;

/**
 * The IntensityComponentCommand class is a concrete
 * implementation of AbstractBaseCommand. It represents a command
 * that processes an image by extracting the intensity component
 * and saving the result as a new image using an ImageModel.
 * The intensity component represents the luminance or brightness information of an image.
 */
public class IntensityComponentCommand extends AbstractSplitCommand {

  /**
   * The IntensityComponentCommand class is a concrete
   * implementation of AbstractBaseCommand. It represents a command
   * that processes an image by extracting the intensity component and saving
   * the result as a new image using an ImageModel. The intensity component
   * represents the luminance or brightness information of an image.
   */
  public IntensityComponentCommand(ImageModelInterface model, String imageName,
                                   String destinationImageName,
                                   Optional<Double> splitPercentage) {
    super(model, imageName, destinationImageName, splitPercentage);
  }

  /**
   * Processes the image by extracting the intensity
   * component and saving the result as a new image
   * using the ImageModel.
   *
   * @throws Exception if there are issues during the intensity component operation.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.intensityComponentCommand(this.imageName, this.destinationImageName,
            this.splitPercentage);
  }
}
