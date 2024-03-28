package controller.commands;


/**
 * The ImageJPG class is a concrete implementation of the ImageFormat class specifically
 * designed for loading and saving JPG (JPEG) image files. It extends the functionality of
 * the abstract ImageFormat class, enabling the handling of JPG image formats.
 */
public class ImageJPG extends AbstractImageFormat {

  /**
   * Constructs a new ImageJPG object with the
   * specified path to the JPG (JPEG) image file.
   *
   * @param path The path to the JPG (JPEG) image file.
   */
  public ImageJPG(String path) {
    super(path);
  }
}
