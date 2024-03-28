package model;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * The HistogramCreator class provides methods for creating
 * a histogram image from an input image.
 */
public class HistogramCreator {

  /**
   * Creates a histogram image from the provided Image object.
   *
   * @param channels The three red, green and blue channels.
   * @return A BufferedImage representing the histogram.
   */
  public static Pixel[][] createHistogramImage(int[][] channels) {
    int maxFrequency = getMaxFrequency(channels[0], channels[1], channels[2]);


    BufferedImage histogramImage = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = histogramImage.createGraphics();
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, 256, 256);

    drawGrid(g2d);

    drawHistogram(g2d, channels[0], maxFrequency, Color.RED);
    drawHistogram(g2d, channels[1], maxFrequency, Color.GREEN);
    drawHistogram(g2d, channels[2], maxFrequency, Color.BLUE);

    g2d.dispose();
    return convertToPixelsArray(histogramImage);
  }

  /**
   * Draws a grid on the given Graphics2D object.
   *
   * @param g2d The Graphics2D object to draw on.
   */
  private static void drawGrid(Graphics2D g2d) {
    g2d.setColor(Color.LIGHT_GRAY);
    for (int i = 0; i < 256; i += 16) {
      g2d.drawLine(i, 0, i, 255); // vertical lines
      g2d.drawLine(0, i, 255, i); // horizontal lines
    }
  }

  /**
   * Draws a histogram on the given Graphics2D object using the provided frequency data and color.
   *
   * @param g2d          The Graphics2D object to draw on.
   * @param frequency    The frequency data for the histogram.
   * @param maxFrequency The maximum frequency among all color channels.
   * @param color        The color of the histogram.
   */
  private static void drawHistogram(Graphics2D g2d, int[] frequency,
                                    int maxFrequency, Color color) {
    g2d.setColor(color);
    int prevX = 0;
    int prevY = 255;

    for (int i = 0; i < frequency.length; i++) {
      int x = i;
      int y = (int) ((frequency[i] / (double) maxFrequency) * 255);

      g2d.drawLine(prevX, prevY, x, 255 - y);

      prevX = x;
      prevY = 255 - y;
    }
  }

  /**
   * Finds the maximum frequency among the provided frequency arrays.
   *
   * @param frequencies Arrays containing frequency data.
   * @return The maximum frequency.
   */
  private static int getMaxFrequency(int[]... frequencies) {
    int max = 0;
    for (int[] frequency : frequencies) {
      for (int value : frequency) {
        if (value > max) {
          max = value;
        }
      }
    }
    return max;
  }

  private static Pixel[][] convertToPixelsArray(BufferedImage bufferedImage) {
    int width = bufferedImage.getWidth();
    int height = bufferedImage.getHeight();
    Pixel[][] pixels = new Pixel[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int rgb = bufferedImage.getRGB(x, y);
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = (rgb) & 0xFF;
        pixels[x][y] = new Pixel(red, green, blue);
      }
    }
    return pixels;
  }
}
