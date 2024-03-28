package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The HaarWaveletTransform class provides methods for performing the Haar wavelet transform
 * and its inverse on a 2D array, as well as calculating a threshold value based on a given
 * percentage of unique absolute values within a set of channels.
 */
public class HaarWaveletTransform {

  /**
   * Applies the Haar wavelet transform on a given sequence.
   *
   * @param s The input sequence.
   * @return The transformed sequence.
   */
  private List<Double> avgDiffTransform(List<Double> s) {
    List<Double> avg = new ArrayList<>();
    List<Double> diff = new ArrayList<>();
    double sqrtTwo = Math.sqrt(2);

    for (int i = 0; i < s.size(); i += 2) {
      double a = s.get(i);
      double b = (i + 1 < s.size()) ? s.get(i + 1) : 0;

      double average = ((a + b) / sqrtTwo);
      double minus = ((a - b) / sqrtTwo);

      double roundAvg = Math.round(average * 100.0) / 100.0;
      double roundMinus = Math.round(minus * 100.0) / 100.0;

      avg.add(roundAvg);
      diff.add(roundMinus);
    }
    avg.addAll(diff);
    return avg;
  }


  /**
   * Applies the inverse Haar wavelet transform on a given sequence.
   *
   * @param s The input sequence.
   * @return The original sequence.
   */
  private List<Double> avgDiffInverseTransform(List<Double> s) {
    List<Double> originalSequence = new ArrayList<>();
    int halfSize = s.size() / 2;
    double sqrtTwo = Math.sqrt(2);

    for (int i = 0; i < halfSize; i++) {
      double a = s.get(i);
      double b = s.get(i + halfSize);

      double average = ((a + b) / sqrtTwo);
      double minus = ((a - b) / sqrtTwo);

      double roundAvg = Math.round(average * 100.0) / 100.0;
      double roundMinus = Math.round(minus * 100.0) / 100.0;

      originalSequence.add(roundAvg);
      originalSequence.add(roundMinus);
    }
    return originalSequence;
  }

  /**
   * Pads a 2D array to the nearest power of two.
   *
   * @param x The input 2D array.
   * @return The padded 2D array.
   */
  private double[][] padArray(double[][] x) {
    int width = x.length;
    int height = x[0].length;
    int newDim = powerOfTwo(Math.max(width, height));

    double[][] paddedArray = new double[newDim][newDim];
    for (int i = 0; i < width; i++) {
      System.arraycopy(x[i], 0, paddedArray[i], 0, height);
    }
    return paddedArray;
  }

  /**
   * Finds the nearest power of two greater than or equal to a given number.
   *
   * @param number The input number.
   * @return The nearest power of two.
   */
  private int powerOfTwo(int number) {
    int power = 1;
    while (power < number) {
      power = power << 1;
    }
    return power;
  }


  /**
   * Transforms a given sequence using the Haar wavelet transform.
   *
   * @param s The input sequence.
   * @param l The length of the sequence.
   * @return The transformed sequence.
   */
  private List<Double> transform(List<Double> s, int l) {
    List<Double> transformedS = new ArrayList<>(s);
    int m = l;
    while (m > 1) {
      List<Double> temp = avgDiffTransform(transformedS.subList(0, m));
      for (int i = 0; i < m; i++) {
        transformedS.set(i, temp.get(i));
      }
      m /= 2;
    }
    return transformedS;
  }

  /**
   * Inverts a transformed sequence using the inverse Haar wavelet transform.
   *
   * @param transformedSequence The transformed sequence.
   * @param l                   The length of the sequence.
   * @return The original sequence.
   */
  private List<Double> invert(List<Double> transformedSequence, int l) {
    List<Double> originalSequence = new ArrayList<>(transformedSequence);
    int m = 2;
    while (m <= l) {
      List<Double> temp = avgDiffInverseTransform(originalSequence.subList(0, m));
      for (int i = 0; i < m; i++) {
        originalSequence.set(i, temp.get(i));
      }
      m *= 2;
    }
    return originalSequence;
  }

  /**
   * Performs the Haar wavelet transform on a 2D array.
   *
   * @param x The input 2D array.
   * @return The transformed 2D array.
   */
  public double[][] haar(double[][] x) {
    x = padArray(x);
    int c = x.length;
    while (c > 1) {
      for (int i = 0; i < c; i++) {
        List<Double> row = new ArrayList<>();
        for (int j = 0; j < c; j++) {
          row.add(x[i][j]);
        }
        List<Double> transformedRow = this.transform(row, c);
        for (int j = 0; j < c; j++) {
          x[i][j] = transformedRow.get(j);
        }
      }
      for (int j = 0; j < c; j++) {
        List<Double> col = new ArrayList<>();
        for (int i = 0; i < c; i++) {
          col.add(x[i][j]);
        }
        List<Double> transformedCol = this.transform(col, c);
        for (int i = 0; i < c; i++) {
          x[i][j] = transformedCol.get(i);
        }
      }
      c = c / 2;
    }
    return x;
  }


  /**
   * Removes padding from a 2D array to restore its original dimensions.
   *
   * @param x              The padded 2D array to be unpadded.
   * @param originalWidth  The original width of the array before padding.
   * @param originalHeight The original height of the array before padding.
   * @return The unpadded 2D array with dimensions specified by originalWidth and originalHeight.
   */
  private double[][] unpadArray(double[][] x, int originalWidth, int originalHeight) {
    double[][] unpaddedArray = new double[originalWidth][originalHeight];
    for (int i = 0; i < originalWidth; i++) {
      System.arraycopy(x[i], 0, unpaddedArray[i], 0, originalHeight);
    }
    return unpaddedArray;
  }

  /**
   * Removes padding and performs the inverse Haar wavelet transform on a 2D array.
   *
   * @param x              The transformed 2D array.
   * @param originalWidth  The original width of the array.
   * @param originalHeight The original height of the array.
   * @return The inverse transformed 2D array.
   */
  public double[][] inverseHaar(double[][] x, int originalWidth, int originalHeight) {
    int c = 2;
    int s = x.length;
    while (c <= s) {
      for (int j = 0; j < c; j++) {
        List<Double> col = new ArrayList<>();
        for (int i = 0; i < c; i++) {
          col.add(x[i][j]);
        }
        List<Double> invertedCol = invert(col, c);
        for (int i = 0; i < c; i++) {
          x[i][j] = invertedCol.get(i);
        }
      }
      for (int i = 0; i < c; i++) {
        List<Double> row = new ArrayList<>();
        for (int j = 0; j < c; j++) {
          row.add(x[i][j]);
        }
        List<Double> invertedRow = invert(row, c);
        for (int j = 0; j < c; j++) {
          x[i][j] = invertedRow.get(j);
        }
      }
      c = c * 2;
    }
    return unpadArray(x, originalWidth, originalHeight);
  }

  /**
   * Calculates a threshold value based on a given percentage of unique absolute values
   * within a set of channels.
   *
   * @param channels   The input channels.
   * @param percentage The percentage of unique absolute values to consider.
   * @return The calculated threshold value.
   */
  public double calculateThreshold(double[][][] channels, double percentage) {
    if (percentage == 100.0) {
      return Double.MAX_VALUE;
    }

    Set<Double> uniqueValues = new HashSet<>();
    for (double[][] channel : channels) {
      for (double[] array : channel) {
        for (double value : array) {
          uniqueValues.add(Math.abs(value));
        }
      }
    }

    List<Double> sortedUniqueValues = new ArrayList<>(uniqueValues);
    Collections.sort(sortedUniqueValues);

    int thresholdIndex = (int) ((sortedUniqueValues.size() - 1) * (percentage / 100));
    return sortedUniqueValues.get(thresholdIndex);
  }

}
