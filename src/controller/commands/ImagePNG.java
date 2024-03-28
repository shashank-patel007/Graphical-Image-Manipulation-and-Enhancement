package controller.commands;


/**
 * The ImagePNG class is a concrete implementation of the ImageFormat class specifically
 * designed for loading and saving PNG image files. It extends the functionality of
 * the abstract ImageFormat class, enabling the handling of PNG image formats.
 *
 */
public class ImagePNG extends AbstractImageFormat {

  /**
   * Constructs a new ImagePNG object with the
   * specified path to the PNG image file.
   *
   * @param path The path to the PNG image file.
   */
  public ImagePNG(String path) {
    super(path);
  }
}
