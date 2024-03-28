package controller.commands;

import model.ImageModelInterface;

/**
 * The CompressCommand class represents a command for
 * compressing an image by a specified percentage. It extends
 * the AbstractBaseCommand class and implements the image compression logic
 * by invoking the compressImage operation on the specified
 * ImageModelInterface instance.
 */
public class CompressCommand extends AbstractBaseCommand {
  private double percentage;

  /**
   * Constructs a CompressCommand with the specified parameters.
   *
   * @param model                The ImageModelInterface instance to perform image compression on.
   * @param percentage           The percentage by which the image should be compressed.
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image after compression.
   */
  public CompressCommand(ImageModelInterface model, double percentage,
                         String imageName, String destinationImageName) {
    super(model, imageName, destinationImageName);
    this.percentage = percentage;
  }

  /**
   * This method must be implemented by subclasses to define
   * the specific image manipulation logic. It invokes the
   * compressImage operation on the
   * ImageModelInterface instance, passing the source image
   * name, destination image name, and compression percentage.
   *
   * @throws Exception if an error occurs during image processing.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.compressImage(imageName, destinationImageName, this.percentage);
  }
}
