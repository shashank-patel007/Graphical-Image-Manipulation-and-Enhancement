package controller.commands;

import model.HistogramCreator;
import model.Image;
import model.ImageModelInterface;
import model.Pixel;

/**
 * The HistogramCommand class represents a command for generating and adding a histogram image
 * based on the color channels of a source image. It extends the AbstractBaseCommand class
 * and implements the image manipulation logic by invoking the histogramCommand operation on
 * the specified ImageModelInterface instance.
 */
public class HistogramCommand extends AbstractBaseCommand {

  /**
   * Constructs a HistogramCommand with the specified parameters.
   *
   * @param model                The ImageModelInterface instance
   *                             to perform histogram generation on.
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image after histogram generation.
   */
  public HistogramCommand(ImageModelInterface model, String imageName,
                          String destinationImageName) {
    super(model, imageName, destinationImageName);
  }

  /**
   * This method must be implemented by subclasses to define the specific image
   * manipulation logic. It may throw an Exception to indicate an error.
   *
   * @throws Exception if an error occurs during image processing.
   */
  @Override
  protected void processImage() throws Exception {
    this.histogramCommand(this.imageName, this.destinationImageName);
  }

  /**
   * Generates a histogram image based on the color channels of the source image,
   * adds the generated image to the model, and associates it with the destination image name.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image for the histogram.
   * @throws Exception if an error occurs during histogram generation or image processing.
   */
  public void histogramCommand(String imageName, String destinationImageName)
          throws Exception {
    int[][] channels = this.model.histogramCommand(imageName, destinationImageName);
    if (channels != null) {
      Pixel[][] histogram = HistogramCreator.createHistogramImage(channels);
      Image histImage = new Image(histogram);
      this.model.addImage(destinationImageName, histImage);
    }
  }
}
