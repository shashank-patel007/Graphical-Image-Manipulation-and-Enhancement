package controller.commands;

import java.util.Optional;

import model.ImageModelInterface;

/**
 * The ValueComponentCommand class is a concrete implementation
 * of AbstractBaseCommand. It represents a command that processes
 * an image by extracting the value component and saving the result
 * as a new image using an ImageModel.
 * The value component represents the brightness information in
 * an image while ignoring the color information.
 */
public class ValueComponentCommand extends AbstractSplitCommand {

  /**
   * Constructs a new ValueComponentCommand with the
   * given ImageModel, source image name, and destination image name.
   *
   * @param model                The ImageModel to be used for image manipulation.
   * @param imageName            The name of the source image from
   *                             which the value component will be extracted.
   * @param destinationImageName The name of the destination image to
   *                             save the value component.
   */
  public ValueComponentCommand(ImageModelInterface model, String imageName,
                               String destinationImageName,
                               Optional<Double> splitPercentage) {
    super(model, imageName, destinationImageName, splitPercentage);
  }

  /**
   * Processes the image by extracting the value component and
   * saving the result as a new image using the ImageModel.
   *
   * @throws Exception if there are issues during the value component operation.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.valueComponentCommand(this.imageName, this.destinationImageName,
            this.splitPercentage);
  }
}
