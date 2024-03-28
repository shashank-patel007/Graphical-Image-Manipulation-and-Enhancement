package controller.commands;

import java.util.Optional;

import model.ImageModelInterface;

/**
 * The LumaComponentCommand class is a concrete implementation of AbstractBaseCommand.
 * It represents a command that processes an image by extracting the luma component
 * and saving the result as a new image using an ImageModel.
 * The luma component represents the grayscale or brightness information of an image.
 *
 * @author Your Name
 * @version 1.0
 */
public class LumaComponentCommand extends AbstractSplitCommand {

  /**
   * Constructs a new LumaComponentCommand with the given
   * ImageModel, source image name, and destination image name.
   *
   * @param model                The ImageModel to be used for image manipulation.
   * @param imageName            The name of the source image from
   *                             which the luma component
   *                             will be extracted.
   * @param destinationImageName The name of the destination image
   *                             to save the luma component.
   */
  public LumaComponentCommand(ImageModelInterface model, String imageName,
                              String destinationImageName,
                              Optional<Double> splitPercentage) {
    super(model, imageName, destinationImageName, splitPercentage);
  }

  /**
   * Processes the image by extracting the luma component and
   * saving the result as a new image using the ImageModel.
   *
   * @throws Exception if there are issues during the luma component operation.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.lumaComponentCommand(this.imageName, this.destinationImageName,
            this.splitPercentage);
  }
}
