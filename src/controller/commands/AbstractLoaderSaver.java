package controller.commands;

import model.ImageModelInterface;

/**
 * The AbstractLoaderSaver class is an abstract base class that extends AbstractBaseCommand.
 * It is intended for classes that perform loading and saving of images using an ImageModel.
 * Subclasses of this class must implement the specific loading and saving logic.
 */
public abstract class AbstractLoaderSaver extends AbstractBaseCommand {

  /**
   * Constructs a new AbstractLoaderSaver with the given ImageModel, source image name,
   * and destination image name.
   *
   * @param model                The ImageModel to be used for image manipulation.
   * @param imageName            The name of the source image to be loaded or saved.
   * @param destinationImageName The name of the destination image to be saved.
   */
  public AbstractLoaderSaver(ImageModelInterface model, String imageName,
                             String destinationImageName) {
    super(model, imageName, destinationImageName);
  }

  /**
   * Retrieves the image format (file extension) from a given path.
   *
   * @param path The path to the image file.
   * @return The image format as a string (e.g., "png", "jpg").
   */
  protected String getImageFormat(String path) throws IllegalArgumentException {
    if (path == null || path.isEmpty()) {
      throw new IllegalArgumentException("Invalid path!");
    }
    String[] tokens = path.split("\\.");
    return tokens[tokens.length - 1];
  }

  /**
   * Gets an instance of a specific ImageParserInterface implementation based on the
   * image format extracted from the provided path.
   *
   * @param path The path to the image file.
   * @return An instance of the corresponding ImageParserInterface implementation.
   * @throws UnsupportedOperationException if the image format is not supported.
   */
  protected ImageParserInterface getFormattedImage(String path)
          throws UnsupportedOperationException {
    String format = this.getImageFormat(path);
    switch (format) {
      case "png":
        return new ImagePNG(path);
      case "jpg":
        return new ImageJPG(path);
      case "ppm":
        return new ImagePPM(path);
      case "jpeg":
        return new ImageJPEG(path);
      default:
        throw new UnsupportedOperationException("Invalid File Format");
    }
  }

}
