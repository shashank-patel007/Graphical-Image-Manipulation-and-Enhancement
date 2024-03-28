package controller.commands;

import model.Image;
import model.ImageModelInterface;

/**
 * The SaveCommand class is a concrete implementation of AbstractLoaderSaver
 * and represents a command for saving an image from the model to a
 * specified path using an ImageParserInterface. It checks if
 * the image exists in the model's image map and, if so, saves it to the specified destination.
 * This class is responsible for saving images to various file formats.
 */
public class SaveCommand extends AbstractLoaderSaver {

  /**
   * Constructs a new SaveCommand with the given ImageModel, path, and name.
   *
   * @param model The ImageModel to be used for image storage and manipulation.
   * @param path  The path to which the image will be saved.
   * @param name  The name of the image in the model to be saved.
   */
  public SaveCommand(ImageModelInterface model, String path, String name) {
    super(model, name, path);
  }

  /**
   * Processes the image by checking if it exists in the
   * model's image map. If the image exists, it uses an ImageParserInterface
   * to save the image to the specified destination.
   *
   * @throws Exception if there are issues during the saving process
   *                   or if the image does not exist
   *                   in the model's image map.
   */
  @Override
  protected void processImage() throws Exception {
    if (!this.model.imageExists(this.imageName)) {
      throw new Exception("Image does not exists!");
    } else {
      ImageParserInterface parser = this.getFormattedImage(this.destinationImageName);
      Image image = this.model.getImage(this.imageName);
      parser.save(this.destinationImageName, image);
    }
  }
}
