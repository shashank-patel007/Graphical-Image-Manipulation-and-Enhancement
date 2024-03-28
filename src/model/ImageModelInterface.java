package model;

import java.util.Optional;

/**
 * The ImageModelInterface is an interface that defines a set of
 * methods for managing and manipulating images.
 * Classes that implement this interface provide functionality for
 * image processing and manipulation.
 */
public interface ImageModelInterface {

  /**
   * Adds an image to the model with the given name.
   *
   * @param name  The name or identifier for the image.
   * @param image The ImageInterface object to be added to the model.
   */
  void addImage(String name, Image image);

  /**
   * Extracts the red component of the specified image and
   * creates a new image with only the red component.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where
   *                             the red component will be stored.
   */
  void redComponentCommand(String imageName, String destinationImageName);

  /**
   * Extracts the green component of the specified image and
   * creates a new image with only the green component.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where
   *                             the green component will be stored.
   */
  void greenComponentCommand(String imageName, String destinationImageName);

  /**
   * Extracts the blue component of the specified image and
   * creates a new image with only the blue component.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where
   *                             the blue component will be stored.
   */
  void blueComponentCommand(String imageName, String destinationImageName);

  /**
   * Creates a new image where all color components (red, green, and blue)
   * have the same value.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image.
   */
  void valueComponentCommand(String imageName, String destinationImageName,
                             Optional<Double> splitPercentage);

  /**
   * Creates a new image where each pixel's color components have the
   * same average value, representing the grayscale intensity.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where the
   *                             grayscale intensity image will be stored.
   * @param splitPercentage      a percentage value for splitting the image.
   */
  void intensityComponentCommand(String imageName, String destinationImageName,
                                 Optional<Double> splitPercentage);

  /**
   * Creates a new image where each pixel's color components are
   * transformed to represent the luma (brightness) of the image.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where
   *                             the luma image will be stored.
   * @param splitPercentage      a percentage value for splitting the image.
   */
  void lumaComponentCommand(String imageName, String destinationImageName,
                            Optional<Double> splitPercentage);

  /**
   * Brightens the image by adding a specified increment to the
   * red, green, and blue components of each pixel.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image
   *                             where the brightened image will be stored.
   * @param increment            The increment to be added to the red, green,
   *                             and blue components.
   */
  void brightenCommand(String imageName, String destinationImageName, int increment);

  /**
   * Combines three images (red, green, and blue channels) into a single color image.
   *
   * @param imageRed   The name of the source image for the red channel.
   * @param imageGreen The name of the source image for the green channel.
   * @param imageBlue  The name of the source image for the blue channel.
   * @param imageName  The name of the destination image where the combined
   *                   image will be stored.
   */
  void combineCommand(String imageRed, String imageGreen, String imageBlue, String imageName);

  /**
   * Applies a blur filter to the image, creating a new image with a blurred appearance.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where
   *                             the blurred image will be stored.
   * @param splitPercentage      a percentage value for splitting the image.
   */
  void blurCommand(String imageName, String destinationImageName,
                   Optional<Double> splitPercentage);

  /**
   * Applies a sharpening filter to the image, creating a new image with enhanced sharpness.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where
   *                             the sharpened image will be stored.
   * @param splitPercentage      a percentage value for splitting the image.
   */
  void sharpenCommand(String imageName, String destinationImageName,
                      Optional<Double> splitPercentage);


  /**
   * Applies a sepia tone filter to the image, giving it a warm, brownish tint.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where the
   *                             sepia-toned image will be stored.
   * @param splitPercentage      a percentage value for splitting the image.
   */
  void sepiaCommand(String imageName, String destinationImageName,
                    Optional<Double> splitPercentage);

  /**
   * Flips the specified image vertically and stores the result in the destination image.
   *
   * @param imageName            The name of the source image to be vertically flipped.
   * @param destinationImageName The name of the destination image where the flipped
   *                             image will be stored.
   */
  void verticalFlipCommand(String imageName, String destinationImageName);

  /**
   * Flips the specified image horizontally and stores the result in the destination image.
   *
   * @param imageName            The name of the source image to be horizontally flipped.
   * @param destinationImageName The name of the destination image where the flipped
   *                             image will be stored.
   */
  void horizontalFlipCommand(String imageName, String destinationImageName);

  /**
   * Checks whether an image with the specified name exists in the image map.
   *
   * @param imageName The name of the image to check for existence.
   * @return true if an image with the given name exists in the image map, false otherwise.
   */
  boolean imageExists(String imageName);

  /**
   * Retrieves the image with the specified image name from the image map.
   *
   * @param imageName The name of the image to retrieve.
   * @return The ImageInterface object associated with the given image name.
   */
  Image getImage(String imageName);

  /**
   * Splits an image into its RGB components and stores them as separate images in the model.
   *
   * @param redImageName   The name for the red component image.
   * @param greenImageName The name for the green component image.
   * @param blueImageName  The name for the blue component image.
   * @param imageName      The name of the source image to be split.
   */
  void rgbSplitCommand(String redImageName, String greenImageName,
                       String blueImageName, String imageName);

  /**
   * Compresses the specified image using the Haar Wavelet Transform and stores the result
   * in the destination image with the specified compression percentage.
   *
   * @param imageName            The name of the source image to be compressed.
   * @param destinationImageName The name of the destination image where the compressed
   *                             image will be stored.
   * @param percentage           The compression percentage.
   */
  void compressImage(String imageName, String destinationImageName, double percentage);

  /**
   * Displays the histogram of the specified image and stores the result
   * in the destination image.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where
   *                             the histogram image will be stored.
   */
  int[][] histogramCommand(String imageName, String destinationImageName) throws Exception;

  /**
   * Applies color correction to the specified image and stores the result
   * in the destination image with the specified split percentage.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where
   *                             the color-corrected image will be stored.
   * @param splitPercentage      An optional percentage value for splitting the image.
   */
  void colorCorrectionCommand(String imageName, String destinationImageName,
                              Optional<Double> splitPercentage);

  /**
   * Adjusts the levels of the specified image and stores the result
   * in the destination image with the specified parameters and split percentage.
   *
   * @param b                    The black point level.
   * @param m                    The mid-point level.
   * @param w                    The white point level.
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where
   *                             the color-corrected image will be stored.
   * @param splitPercentage      An optional percentage value for splitting the image.
   */
  void levelsAdjustmentCommand(int b, int m, int w, String imageName,
                               String destinationImageName, Optional<Double> splitPercentage);
}
