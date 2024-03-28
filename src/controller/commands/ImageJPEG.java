package controller.commands;


/**
 * The ImageJPEG class is a concrete implementation of the ImageFormat class specifically
 * designed for loading and saving JPEG image files. It extends the functionality of
 * the abstract ImageFormat class, enabling the handling of JPEG image formats.
 */
public class ImageJPEG extends AbstractImageFormat {

  /**
   * Constructs a new ImageJPEG object with the
   * specified path to the JPEG image file.
   *
   * @param path The path to the JPEG image file.
   */
  public ImageJPEG(String path) {
    super(path);
  }
}
