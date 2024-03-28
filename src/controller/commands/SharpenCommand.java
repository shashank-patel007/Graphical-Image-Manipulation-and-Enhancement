package controller.commands;

import java.util.Optional;

import model.ImageModelInterface;

/**
 * The SharpenCommand class is a concrete implementation of AbstractBaseCommand.
 * It represents a command that processes an image by applying a sharpening effect
 * and saving the result as a new image using an ImageModel.
 * Sharpening enhances the edges and details in an image, making it appear more focused.
 */
public class SharpenCommand extends AbstractSplitCommand {

  /**
   * Constructs a new SharpenCommand with the given ImageModel, source image name,
   * and destination image name.
   *
   * @param model                The ImageModel to be used for image manipulation.
   * @param imageName            The name of the source image to which the
   *                             sharpening effect will be applied.
   * @param destinationImageName The name of the destination image to save
   *                             the sharpened image.
   */
  public SharpenCommand(ImageModelInterface model, String imageName, String destinationImageName,
                        Optional<Double> splitPercentage) {
    super(model, imageName, destinationImageName, splitPercentage);
  }

  /**
   * Processes the image by applying a sharpening effect
   * and saving the result as a new image using the ImageModel.
   *
   * @throws Exception if there are issues during the sharpen operation.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.sharpenCommand(this.imageName, this.destinationImageName, this.splitPercentage);
  }
}
