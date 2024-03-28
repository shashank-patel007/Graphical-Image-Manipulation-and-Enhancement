package controller.commands;

import model.ImageModelInterface;

/**
 * The RGBSplit class extends the functionality of AbstractBaseCommand to split
 * an image into its constituent red, green, and blue color components.
 * Each color component is saved as a separate image.
 */
public class RGBSplit extends AbstractBaseCommand {

  /**
   * Constructs a new CombineCommand with the given ImageModel, destination image names
   * for the red, green, and blue color components, and the source image name.
   *
   * @param model          The ImageModel to be used for image manipulation.
   * @param imageName      The name of the source image to split.
   * @param redImageName   The name of the red image.
   * @param greenImageName The name of the green image.
   * @param blueImageName  The name of the blue image.
   */
  public RGBSplit(ImageModelInterface model,
                  String imageName,
                  String redImageName,
                  String greenImageName,
                  String blueImageName) {

    super(model, imageName, redImageName, greenImageName, blueImageName);
  }

  /**
   * Executes the RGB split operation by passing parameters to the ImageModel to process
   * the source image and generate separate images for the red, green, and blue color
   * components.
   *
   * @throws Exception if there is an issue during the image splitting process.
   */
  @Override
  protected void processImage() throws Exception {
    this.model.rgbSplitCommand(this.redImageName, this.greenImageName,
            this.blueImageName, this.imageName);
  }
}
