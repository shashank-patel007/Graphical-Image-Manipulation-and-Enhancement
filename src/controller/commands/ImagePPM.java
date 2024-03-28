package controller.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import model.Image;
import model.Pixel;

/**
 * The ImagePPM class is a concrete implementation of the ImageParserInterface
 * specifically designed for loading and saving PPM (Portable Pixmap) image files.
 * It provides the functionality to load and save PPM image data.
 */
public class ImagePPM implements ImageParserInterface {

  private final String path;

  /**
   * Constructs a new ImagePPM object with the
   * specified path to the PPM image file.
   *
   * @param path The path to the PPM image file.
   */
  public ImagePPM(String path) throws IllegalArgumentException {
    if (path == null || path.isEmpty()) {
      throw new IllegalArgumentException("path cannot be empty or null");
    }
    this.path = path;
  }


  /**
   * Loads image data from the specified PPM file and returns it as an Image object.
   *
   * @return The loaded Image object.
   * @throws IOException if there are issues reading the image,
   *                     parsing the PPM format,
   *                     or the file format is unsupported.
   */
  @Override
  public Image load() throws IOException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(path));
    } catch (IOException e) {
      throw new IOException("Error reading image from path: " + path, e);
    }

    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    if (!sc.hasNext() || !sc.next().equals("P3")) {
      throw new IOException("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    if (maxValue > 255) {
      throw new IOException("Unsupported color depth. Maximum value should be 255.");
    }

    Pixel[][] pixels = new Pixel[width][height];

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {

        int red = sc.nextInt();
        int green = sc.nextInt();
        int blue = sc.nextInt();

        Pixel pixel = new Pixel(red, green, blue);
        pixels[x][y] = pixel;
      }
    }
    return new Image(pixels);
  }

  /**
   * Saves the provided Image object to the specified path in PPM format.
   *
   * @param path  The path where the image should be saved in PPM format.
   * @param image The Image object to be saved.
   * @throws IOException if there are issues during the saving
   *                     process or if the image data is null.
   */
  @Override
  public void save(String path, Image image) throws IOException {
    if (image.getPixels() == null) {
      throw new IOException("No image data to save.");
    }

    int width = image.getPixels().length;
    int height = image.getPixels()[0].length;

    try (FileWriter writer = new FileWriter(new File(path))) {
      writer.write("P3\n");
      writer.write(width + " " + height + "\n");
      writer.write("255\n");

      for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
          Pixel pixel = image.getPixels()[x][y];
          writer.write(pixel.getRed() + " " + pixel.getGreen() + " " + pixel.getBlue() + " ");
        }
        writer.write("\n");
      }
    }
  }
}
