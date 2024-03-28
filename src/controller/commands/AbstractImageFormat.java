package controller.commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Image;
import model.Pixel;


/**
 * The ImageFormat class is an abstract base class that
 * implements the ImageParserInterface for loading and saving images
 * in various formats. Subclasses should extend this class
 * and provide specific implementations for handling image formats.
 */
public abstract class AbstractImageFormat implements ImageParserInterface {

  private String path;

  /**
   * Constructs a new ImageFormat object with the
   * specified path to the image file.
   *
   * @param path The path to the image file.
   */
  public AbstractImageFormat(String path) throws IllegalArgumentException {
    if (path == null || path.isEmpty()) {
      throw new IllegalArgumentException("Path cannot be empty or null");
    }
    this.path = path;
  }

  /**
   * Loads an image from the specified file path and returns it as an Image object.
   *
   * @return The loaded Image object.
   * @throws IOException              if there are issues reading the image or
   *                                  the file format is unsupported.
   */
  @Override
  public Image load() throws IOException {
    BufferedImage bufferedImage;
    try {
      bufferedImage = ImageIO.read(new File(path));
    } catch (IOException e) {
      throw new IOException("Error reading image from path: " + path, e);
    }

    if (bufferedImage == null) {
      throw new IOException("Unsupported image format or invalid file: " + path);
    }

    int width = bufferedImage.getWidth();
    int height = bufferedImage.getHeight();
    Pixel[][] pixels = new Pixel[width][height];

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int rgb = bufferedImage.getRGB(x, y);
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;

        Pixel pixel = new Pixel(red, green, blue);
        pixels[x][y] = pixel;
      }
    }
    return new Image(pixels);
  }

  /**
   * Saves the provided Image object to the specified file path.
   *
   * @param path  The path where the image should be saved.
   * @param image The Image object to be saved.
   * @throws Exception if there are issues saving the
   *                   image or the file format is unsupported.
   */
  @Override
  public void save(String path, Image image) throws Exception {
    if (path == null || path.trim().isEmpty()) {
      throw new IllegalArgumentException("Path cannot be null or empty");
    }

    int width = image.getPixels().length;
    int height = image.getPixels()[0].length;

    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Pixel pixel = image.getPixels()[x][y];
        int rgb = (pixel.getRed() << 16) | (pixel.getGreen() << 8) | pixel.getBlue();
        bufferedImage.setRGB(x, y, rgb);
      }
    }

    String fileExtension = path.substring(path.lastIndexOf('.') + 1);

    try {
      ImageIO.write(bufferedImage, fileExtension, new File(path));
    } catch (FileNotFoundException e) {
      throw new Exception("Invalid Path!");
    } catch (Exception e) {
      throw new Exception("Error saving image to path: " + path);
    }
  }
}
