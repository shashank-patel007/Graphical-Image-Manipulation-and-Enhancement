package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import model.strategy.ColorCorrectionStrategy;
import model.strategy.IntensityStrategy;
import model.strategy.LevelAdjustmentStrategy;
import model.strategy.LumaStrategy;
import model.strategy.SharpenStrategy;
import model.strategy.SplitDecorator;
import model.strategy.SplitStrategy;
import model.strategy.BlurStrategy;
import model.strategy.SepiaStrategy;
import model.strategy.ValueStrategy;

/**
 * This class represents the model for managing and manipulating images.
 * It stores images in a map and provides various image processing methods.
 */
public class ImageModel implements ImageModelInterface {

  private final Map<String, Image> imageMap;

  /**
   * Constructs an ImageModel object, initializing an empty map to store images.
   */
  public ImageModel() {
    this.imageMap = new HashMap<>();
  }


  /**
   * Adds an image to the model with the given name.
   *
   * @param name  The name or identifier for the image.
   * @param image The Image object to be added to the model.
   */
  @Override
  public void addImage(String name, Image image) {
    this.imageMap.put(name, image);
  }


  /**
   * Extracts the red component of the specified image and
   * creates a new image with only the red component.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where
   *                             the red component will be stored.
   */
  @Override
  public void redComponentCommand(String imageName, String destinationImageName) {
    if (!imageExists(imageName)) {
      throw new IllegalArgumentException("Image does not exist!");
    }
    Image image = this.imageMap.get(imageName);
    Image newImage = image.redComponent();
    this.addImage(destinationImageName, newImage);
  }

  /**
   * Extracts the green component of the specified image and
   * creates a new image with only the green component.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where
   *                             the green component will be stored.
   */
  @Override
  public void greenComponentCommand(String imageName, String destinationImageName) {
    if (!imageExists(imageName)) {
      throw new IllegalArgumentException("Image does not exist!");
    }
    Image image = this.imageMap.get(imageName);
    Image newImage = image.greenComponent();
    this.addImage(destinationImageName, newImage);
  }

  /**
   * Extracts the blue component of the specified image and
   * creates a new image with only the blue component.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where
   *                             the blue component will be stored.
   */
  @Override
  public void blueComponentCommand(String imageName, String destinationImageName) {
    if (!imageExists(imageName)) {
      throw new IllegalArgumentException("Image does not exist!");
    }
    Image image = this.imageMap.get(imageName);
    Image newImage = image.blueComponent();
    this.addImage(destinationImageName, newImage);
  }


  /**
   * Creates a new image where all color components (red, green, and blue)
   * have the same value which is the maximum pixel of all (rgb).
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image.
   */
  @Override
  public void valueComponentCommand(String imageName, String destinationImageName,
                                    Optional<Double> splitPercentage) {
    if (!imageExists(imageName)) {
      throw new IllegalArgumentException("Image does not exist!");
    }
    Image image = this.imageMap.get(imageName);
    SplitStrategy valueStrategy = new ValueStrategy();

    if (splitPercentage.isPresent()) {
      valueStrategy = new SplitDecorator(valueStrategy, splitPercentage.get());
    }

    Image newImage = image.applyFilter(valueStrategy);
    this.addImage(destinationImageName, newImage);
  }

  /**
   * Creates a new image where each pixel's color components have the
   * same average value, representing the grayscale intensity.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where the
   *                             grayscale intensity image will be stored.
   * @param splitPercentage      a percentage value for splitting the image.
   */
  @Override
  public void intensityComponentCommand(String imageName, String destinationImageName,
                                        Optional<Double> splitPercentage) {
    if (!imageExists(imageName)) {
      throw new IllegalArgumentException("Image does not exist!");
    }
    Image image = this.imageMap.get(imageName);
    SplitStrategy intensityStrategy = new IntensityStrategy();

    if (splitPercentage.isPresent()) {
      intensityStrategy = new SplitDecorator(intensityStrategy, splitPercentage.get());
    }

    Image newImage = image.applyFilter(intensityStrategy);
    this.addImage(destinationImageName, newImage);
  }

  /**
   * Creates a new image where each pixel's color components are
   * transformed to represent the luma (brightness) of the image.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where
   *                             the luma image will be stored.
   * @param splitPercentage      a percentage value for splitting the image.
   */
  @Override
  public void lumaComponentCommand(String imageName, String destinationImageName,
                                   Optional<Double> splitPercentage) {
    if (!imageExists(imageName)) {
      throw new IllegalArgumentException("Image does not exist!");
    }

    Image image = this.imageMap.get(imageName);
    SplitStrategy lumaStrategy = new LumaStrategy();

    if (splitPercentage.isPresent()) {
      lumaStrategy = new SplitDecorator(lumaStrategy, splitPercentage.get());
    }

    Image newImage = image.applyFilter(lumaStrategy);
    this.addImage(destinationImageName, newImage);
  }

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
  @Override
  public void brightenCommand(String imageName, String destinationImageName, int increment) {
    if (!imageExists(imageName)) {
      throw new IllegalArgumentException("Image does not exist!");
    }
    Image image = this.imageMap.get(imageName);
    Image newImage = image.brighten(increment);
    this.addImage(destinationImageName, newImage);
  }

  /**
   * Combines three images (red, green, and blue channels) into a single color image.
   *
   * @param imageRedName         The name of the source image for the red channel.
   * @param imageGreenName       The name of the source image for the green channel.
   * @param imageBlueName        The name of the source image for the blue channel.
   * @param destinationImageName The name of the destination image where the combined
   *                             image will be stored.
   */
  @Override
  public void combineCommand(String imageRedName,
                             String imageGreenName,
                             String imageBlueName,
                             String destinationImageName) {

    if (!imageExists(imageRedName) || !imageExists(imageGreenName)
            || !imageExists(imageBlueName)) {
      throw new IllegalArgumentException("One or More Images does not exist!");
    }

    Image imageRed = this.imageMap.get(imageRedName);
    Image imageGreen = this.imageMap.get(imageGreenName);
    Image imageBlue = this.imageMap.get(imageBlueName);

    int widthRed = imageRed.getPixels().length;
    int heightRed = imageRed.getPixels()[0].length;

    int widthGreen = imageGreen.getPixels().length;
    int heightGreen = imageGreen.getPixels()[0].length;

    int widthBlue = imageBlue.getPixels().length;
    int heightBlue = imageBlue.getPixels()[0].length;

    if ((widthRed != widthGreen) || (widthGreen != widthBlue)
            || (heightRed != heightGreen) || (heightGreen != heightBlue)) {
      throw new IllegalArgumentException("All images must have same dimensions!");
    }
    Pixel[][] pixels = new Pixel[widthRed][heightRed];

    for (int x = 0; x < widthRed; x++) {
      for (int y = 0; y < heightRed; y++) {

        Pixel imageRedPixel = imageRed.getPixels()[x][y];
        Pixel imageGreenPixel = imageGreen.getPixels()[x][y];
        Pixel imageBluePixel = imageBlue.getPixels()[x][y];

        int red = imageRedPixel.getRed();
        int green = imageGreenPixel.getGreen();
        int blue = imageBluePixel.getBlue();

        Pixel destPixel = new Pixel(red, green, blue);
        pixels[x][y] = destPixel;
      }
    }
    this.addImage(destinationImageName, new Image(pixels));
  }

  /**
   * Applies a blur filter to the image, creating a new image with a blurred appearance.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where
   *                             the blurred image will be stored.
   * @param splitPercentage      a percentage value for splitting the image.
   */
  @Override
  public void blurCommand(String imageName, String destinationImageName,
                          Optional<Double> splitPercentage) {
    if (!imageExists(imageName)) {
      throw new IllegalArgumentException("Image does not exist!");
    }
    Image image = this.imageMap.get(imageName);
    SplitStrategy blurStrategy = new BlurStrategy();

    if (splitPercentage.isPresent()) {
      blurStrategy = new SplitDecorator(blurStrategy, splitPercentage.get());
    }

    Image newImage = image.applyFilter(blurStrategy);
    this.addImage(destinationImageName, newImage);
  }


  /**
   * Applies a sharpening filter to the image, creating a new image with enhanced sharpness.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where
   *                             the sharpened image will be stored.
   * @param splitPercentage      a percentage value for splitting the image.
   */
  @Override
  public void sharpenCommand(String imageName, String destinationImageName,
                             Optional<Double> splitPercentage) {
    if (!imageExists(imageName)) {
      throw new IllegalArgumentException("Image does not exist!");
    }
    Image image = this.imageMap.get(imageName);
    SplitStrategy sharpenStrategy = new SharpenStrategy();

    if (splitPercentage.isPresent()) {
      sharpenStrategy = new SplitDecorator(sharpenStrategy, splitPercentage.get());
    }
    Image newImage = image.applyFilter(sharpenStrategy);
    this.addImage(destinationImageName, newImage);
  }

  /**
   * Applies a sepia tone filter to the image, giving it a warm, brownish tint.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where the
   *                             sepia-toned image will be stored.
   * @param splitPercentage      The Percentage value in which image to split.
   */
  @Override
  public void sepiaCommand(String imageName, String destinationImageName,
                           Optional<Double> splitPercentage) {
    if (!imageExists(imageName)) {
      throw new IllegalArgumentException("Image does not exist!");
    }
    Image image = this.imageMap.get(imageName);
    SplitStrategy sepiaStrategy = new SepiaStrategy();

    if (splitPercentage.isPresent()) {
      sepiaStrategy = new SplitDecorator(sepiaStrategy, splitPercentage.get());
    }

    Image newImage = image.applyFilter(sepiaStrategy);
    this.addImage(destinationImageName, newImage);
  }


  /**
   * Flips the specified image vertically and stores the result in the destination image.
   *
   * @param imageName            The name of the source image to be vertically flipped.
   * @param destinationImageName The name of the destination image where the flipped
   *                             image will be stored.
   */
  @Override
  public void verticalFlipCommand(String imageName, String destinationImageName) {
    if (!imageExists(imageName)) {
      throw new IllegalArgumentException("Image does not exist!");
    }
    Image image = this.imageMap.get(imageName);
    Image newImage = image.verticalFlip();
    this.addImage(destinationImageName, newImage);
  }

  /**
   * Flips the specified image horizontally and stores the result in the destination image.
   *
   * @param imageName            The name of the source image to be horizontally flipped.
   * @param destinationImageName The name of the destination image where the flipped
   *                             image will be stored.
   */
  @Override
  public void horizontalFlipCommand(String imageName, String destinationImageName) {
    if (!imageExists(imageName)) {
      throw new IllegalArgumentException("Image does not exist!");
    }
    Image image = this.imageMap.get(imageName);
    Image newImage = image.horizontalFlip();
    this.addImage(destinationImageName, newImage);
  }

  /**
   * Splits an image into its RGB components and stores them as separate images in the model.
   *
   * @param redImageName   The name for the red component image.
   * @param greenImageName The name for the green component image.
   * @param blueImageName  The name for the blue component image.
   * @param imageName      The name of the source image to be split.
   */
  @Override
  public void rgbSplitCommand(String redImageName, String greenImageName,
                              String blueImageName, String imageName) {
    this.redComponentCommand(imageName, redImageName);
    this.greenComponentCommand(imageName, greenImageName);
    this.blueComponentCommand(imageName, blueImageName);
  }

  /**
   * Compresses the specified image using the Haar Wavelet Transform and stores the result
   * in the destination image with the specified compression percentage.
   *
   * @param imageName            The name of the source image to be compressed.
   * @param destinationImageName The name of the destination image where the compressed
   *                             image will be stored.
   * @param percentage           The compression percentage.
   */
  @Override
  public void compressImage(String imageName, String destinationImageName, double percentage) {
    if (!imageExists(imageName)) {
      throw new IllegalArgumentException("Image does not exist!");
    }

    if (percentage < 0 || percentage > 100) {
      throw new IllegalArgumentException("Invalid percentage entered!");
    }

    Image image = this.imageMap.get(imageName);
    Image compressedImage = image.compress(percentage);
    this.addImage(destinationImageName, compressedImage);
  }

  /**
   * Displays the histogram of the specified image and stores the result
   * in the destination image.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where
   *                             the histogram image will be stored.
   */
  @Override
  public int[][] histogramCommand(String imageName, String destinationImageName) throws Exception {
    if (!this.imageExists(imageName)) {
      throw new Exception("Image does not exists!");
    }
    Image image = this.getImage(imageName);
    return image.histogram();
  }

  /**
   * Applies color correction to the specified image and stores the result
   * in the destination image with the specified split percentage.
   *
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where
   *                             the color-corrected image will be stored.
   * @param splitPercentage      A percentage value for splitting the image.
   */
  @Override
  public void colorCorrectionCommand(String imageName, String destinationImageName,
                                     Optional<Double> splitPercentage) {
    if (!imageExists(imageName)) {
      throw new IllegalArgumentException("Image does not exist!");
    }
    Image image = this.imageMap.get(imageName);
    SplitStrategy colorCorrectionStrategy = new ColorCorrectionStrategy();

    if (splitPercentage.isPresent()) {
      colorCorrectionStrategy = new SplitDecorator(colorCorrectionStrategy, splitPercentage.get());
    }

    Image newImage = image.applyFilter(colorCorrectionStrategy);
    this.addImage(destinationImageName, newImage);
  }

  /**
   * Adjusts the levels of the specified image and stores the result
   * in the destination image with the specified parameters and split percentage.
   *
   * @param b                    The black point level.
   * @param m                    The mid point level.
   * @param w                    The white point level.
   * @param imageName            The name of the source image.
   * @param destinationImageName The name of the destination image where
   *                             the color-corrected image will be stored.
   * @param splitPercentage      A percentage value for splitting the image.
   */
  @Override
  public void levelsAdjustmentCommand(int b, int m, int w, String imageName,
                                      String destinationImageName,
                                      Optional<Double> splitPercentage) {
    if (!imageExists(imageName)) {
      throw new IllegalArgumentException("Image does not exist!");
    }
    if (!(b >= 0 && b < m && m < w && w <= 255)) {
      throw new IllegalArgumentException("Enter valid values for b, m and w");
    }
    Image image = this.imageMap.get(imageName);
    SplitStrategy levelAdjustmentStrategy = new LevelAdjustmentStrategy(b, m, w);

    if (splitPercentage.isPresent()) {
      levelAdjustmentStrategy = new SplitDecorator(levelAdjustmentStrategy, splitPercentage.get());
    }

    Image newImage = image.applyFilter(levelAdjustmentStrategy);
    this.addImage(destinationImageName, newImage);
  }

  /**
   * Checks whether an image with the specified name exists in the image map.
   *
   * @param imageName The name of the image to check for existence.
   * @return true if an image with the given name exists in the image map, false otherwise.
   */
  @Override
  public boolean imageExists(String imageName) {
    return this.imageMap.containsKey(imageName);
  }

  /**
   * Retrieves the image with the specified image name from the image map.
   *
   * @param imageName The name of the image to retrieve.
   * @return The Image object associated with the given image name.
   */
  @Override
  public Image getImage(String imageName) {
    return this.imageMap.get(imageName);
  }

}
