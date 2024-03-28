package controller.commands;

import model.ImageModelInterface;

/**
 * The AbstractBaseCommand class is an abstract base class for implementing
 * commands that manipulate images using an ImageModel. Subclasses of this
 * class must provide an implementation for the abstract method processImage(),
 * which defines the specific image manipulation logic.
 */
public abstract class AbstractBaseCommand implements CommandInterface {

  protected String destinationImageName;
  protected String imageName;
  protected ImageModelInterface model;

  protected String redImageName;
  protected String greenImageName;
  protected String blueImageName;

  /**
   * Constructs a new AbstractBaseCommand with the given ImageModel and image name.
   *
   * @param model                The ImageModel to be used for image manipulation.
   * @param imageName            The name of the source image to be processed.
   * @param destinationImageName The name of the destination image to be processed.
   */
  public AbstractBaseCommand(ImageModelInterface model, String imageName,
                             String destinationImageName) {
    this.imageName = imageName;
    this.model = model;
    this.destinationImageName = destinationImageName;
  }

  /**
   * Constructs a new CombineCommand with the given ImageModel, destination image names
   * for the red, green, and blue color components, and the source image name.
   *
   * @param model          The ImageModel to be used for image manipulation.
   * @param imageName      The name of the source image to be combined.
   * @param redImageName   The name of the image for
   *                       the red color component.
   * @param greenImageName The name of the image for the
   *                       green color component.
   * @param blueImageName  The name of the image for the
   *                       blue color component.
   */
  public AbstractBaseCommand(ImageModelInterface model, String imageName, String redImageName,
                             String greenImageName, String blueImageName) {
    this.imageName = imageName;
    this.model = model;
    this.redImageName = redImageName;
    this.greenImageName = greenImageName;
    this.blueImageName = blueImageName;
  }

  /**
   * Executes the image manipulation process defined in the processImage() method.
   * Subclasses must implement processImage() to define the specific image
   * manipulation logic. If an exception occurs during execution, the method
   * returns false; otherwise, it returns true.
   *
   * @return true if the execution was successful, false otherwise.
   */
  @Override
  public boolean execute() {
    boolean status;
    try {
      this.processImage();
      status = true;
    } catch (Exception e) {
      status = false;
    }
    return status;
  }

  /**
   * This method must be implemented by subclasses to define the specific image
   * manipulation logic. It may throw an Exception to indicate an error.
   *
   * @throws Exception if an error occurs during image processing.
   */
  protected abstract void processImage() throws Exception;

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
