package controller.commands;

import java.io.IOException;

import model.Image;

/**
 * The ImageParserInterface is an interface that defines a contract for classes
 * that can load and save image data from and to various sources. Implementing classes
 * should provide concrete implementations for loading and saving image data.
 */
public interface ImageParserInterface {

  /**
   * Loads image data from a source and returns it as an Image object.
   *
   * @return The loaded Image object.
   * @throws IOException if there are issues during the loading process.
   */
  Image load() throws IOException;

  /**
   * Saves the provided Image object to a specified path.
   *
   * @param path  The path where the image should be saved.
   * @param image The Image object to be saved.
   * @throws Exception if there are issues during the saving process.
   */
  void save(String path, Image image) throws Exception;

}
