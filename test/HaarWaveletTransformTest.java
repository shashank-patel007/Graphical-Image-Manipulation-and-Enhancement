import org.junit.Before;
import org.junit.Test;

import model.HaarWaveletTransform;

import static org.junit.Assert.assertEquals;

/**
 * test class for HaarWaveletTransform compression.
 */
public class HaarWaveletTransformTest {

  private HaarWaveletTransform haarWaveletTransform;


  /**
   * initializing object before running test.
   */
  @Before
  public void setup() {
    haarWaveletTransform = new HaarWaveletTransform();
  }

  /**
   * Testing Haar method.
   */
  @Test
  public void testHaar() {

    HaarWaveletTransform haarWaveletTransform = new HaarWaveletTransform();

    double[][] test = {
            {1.0, 5.0, 6.0},
            {4.0, 2.0, 3.0},
            {0.0, 4.5, 7.0}
    };

    double[][] expected = {
            {5.99, 4.5, -2.3, 5.66},
            {2.26, 3.49, 0.88, 0.71},
            {1.06, -1.06, -3.0, 1.5},
            {4.07, -0.88, -2.25, 3.5}
    };

    double[][] result = haarWaveletTransform.haar(test);

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j], 0.01);
      }
    }
  }


  /**
   * Testing Haar method with padding.
   */
  @Test
  public void testHaarWithPadding() {

    double[][] test = {
            {1.0, 5.0, 6.0},
            {4.0, 2.0, 3.0},
    };

    double[][] expected = {
            {6.0, 4.5, -0.71, 3.18},
            {0.0, 0.0, -0.71, 3.18},
            {1.06, -1.06, -3.0, 1.5},
            {0.0, 0.0, 0.0, 0.0}
    };

    double[][] result = haarWaveletTransform.haar(test);

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j], result[i][j], 0.01);
      }
    }
  }

  /**
   * Testing inverseHaar method.
   */
  @Test
  public void testInverseHaar() {

    double[][] result = {
            {5.99, 4.5, -2.3, 5.66},
            {2.26, 3.49, 0.88, 0.71},
            {1.06, -1.06, -3.0, 1.5},
            {4.07, -0.88, -2.25, 3.5}
    };


    double[][] expectedInverse = {
            {1.0, 5.0, 6.0},
            {4.0, 2.0, 3.0},
            {0.0, 4.5, 7.0}
    };
    double[][] inverseResult = haarWaveletTransform.inverseHaar(result,
            expectedInverse.length, expectedInverse[0].length);

    for (int i = 0; i < expectedInverse.length; i++) {
      for (int j = 0; j < expectedInverse[0].length; j++) {
        assertEquals(expectedInverse[i][j], inverseResult[i][j], 0.01);
      }
    }
  }


  /**
   * Testing inverseHaar method with UnPadding.
   */
  @Test
  public void testInverseHaarWithUnPadding() {

    double[][] result = {
            {6.0, 4.5, -0.71, 3.18},
            {0.0, 0.0, -0.71, 3.18},
            {1.06, -1.06, -3.0, 1.5},
            {0.0, 0.0, 0.0, 0.0}
    };


    double[][] expectedInverse = {
            {1.0, 5.0, 6.0},
            {4.0, 2.0, 3.0}
    };
    double[][] inverseResult = haarWaveletTransform.inverseHaar(result,
            expectedInverse.length, expectedInverse[0].length);

    for (int i = 0; i < expectedInverse.length; i++) {
      for (int j = 0; j < expectedInverse[0].length; j++) {
        assertEquals(inverseResult[i][j], expectedInverse[i][j], 0.01);
      }
      System.out.println();
    }
  }


  /**
   * CalculateThreshold method, percentage = 100.
   */
  @Test
  public void testCalculateThreshold() {
    double[][][] channels = {
      {
        {150, 0, 250},
        {0, 255, 10},
        {230, 125, 75}
      },
      {
        {100, 120, 0},
        {0, 255, 100},
        {130, 190, 20}
      },
      {
        {0, 180, 255},
        {0, 255, 200},
        {100, 0, 200}
      }
    };

    int percentage = 50;
    double threshold = haarWaveletTransform.calculateThreshold(channels, percentage);

    double expectedThreshold = 130;
    assertEquals(expectedThreshold, threshold, 0.01);
  }

  /**
   * CalculateThreshold method, percentage = 100.
   */
  @Test
  public void testCalculateThresholdPercentage100() {
    double[][][] channels = {
      {
        {150, 0, 250},
        {0, 255, 10},
        {230, 125, 75}
      },
      {
        {100, 120, 0},
        {0, 255, 100},
        {130, 190, 20}
      },
      {
        {0, 180, 255},
        {0, 255, 200},
        {100, 0, 200}
      }
    };

    int percentage = 100;
    double threshold = haarWaveletTransform.calculateThreshold(channels, percentage);

    double expectedThreshold = Double.MAX_VALUE;
    assertEquals(expectedThreshold, threshold, 0.01);
  }

}