package controller.commands;

import java.io.IOException;

import model.Image;
import model.ImageModelInterface;

/**
 * The LoadCommand class is a concrete implementation of
 * AbstractLoaderSaver and represents a command for loading an
 * image from a specified path and saving it using an ImageModel.
 * It utilizes an ImageParserInterface to load the image from the
 * given path and then adds it to the model.
 */
public class LoadCommand extends AbstractLoaderSaver {

  /**
   * Constructs a new LoadCommand with the given ImageModel, path, and name.
   *
   * @param model The ImageModel to be used for image storage and manipulation.
   * @param path  The path from which the image will be loaded.
   * @param name  The name to be associated with the loaded image.
   */
  public LoadCommand(ImageModelInterface model, String path, String name) {
    super(model, name, path);
  }

  /**
   * Processes the image by loading it from the specified path using an ImageParserInterface,
   * and then adds it to the ImageModel using the given name.
   *
   * @throws IOException if there are issues during the
   *                     loading process or if the file format is unsupported.
   */
  @Override
  protected void processImage() throws Exception {
    ImageParserInterface parser = this.getFormattedImage(this.destinationImageName);
    Image image = parser.load();
    model.addImage(this.imageName, image);
  }
}
