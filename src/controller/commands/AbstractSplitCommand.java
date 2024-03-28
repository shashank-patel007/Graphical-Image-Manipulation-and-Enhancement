package controller.commands;

import java.util.Optional;

import model.ImageModelInterface;

/**
 * An abstract class representing a split command for
 * manipulating images, extending AbstractBaseCommand.
 * Subclasses must implement the method to define the
 * specific behavior of splitting an image. The split
 * percentage is optional and determines the portion in which the image needs to be split.
 */
public abstract class AbstractSplitCommand extends AbstractBaseCommand {
  protected Optional<Double> splitPercentage;

  /**
   * Constructs a new AbstractBaseCommand with the given ImageModel and image name.
   *
   * @param model                The ImageModel to be used for image manipulation.
   * @param imageName            The name of the source image to be processed.
   * @param destinationImageName The name of the destination image to be processed.
   * @param splitPercentage      Percentage value that needs to be split the image in.
   */
  public AbstractSplitCommand(ImageModelInterface model, String imageName,
                              String destinationImageName,
                              Optional<Double> splitPercentage) {
    super(model, imageName, destinationImageName);
    this.splitPercentage = splitPercentage;
  }

  @Override
  protected abstract void processImage() throws Exception;
}
