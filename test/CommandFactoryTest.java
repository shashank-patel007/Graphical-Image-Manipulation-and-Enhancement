import org.junit.Before;
import org.junit.Test;

import controller.commands.CommandFactory;
import controller.commands.CommandGroup;
import model.ImageModelInterface;
import model.MockModel;
import view.GUIInterface;
import view.MockView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


/**
 * JUnit test class for the CommandFactory class.
 */
public class CommandFactoryTest {

  private CommandFactory commandFactory;

  /**
   * Set up the test environment by creating instances of MockModel,
   * MockView, and CommandFactory.
   */
  @Before
  public void setUp() {
    ImageModelInterface model = new MockModel();
    GUIInterface view = new MockView();
    commandFactory = new CommandFactory(model, view);
  }

  /**
   * test for InvokeLoadCommand.
   */
  @Test
  public void testInvokeLoadCommand() {
    CommandGroup commandGroup = commandFactory.invokeCommand("load");

    assertNotNull(commandGroup);
    assertNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("LoadCommand", commandGroup.getApplyCommand().toString());
  }

  /**
   * test for InvokeSaveCommand.
   */
  @Test
  public void testInvokeSaveCommand() {
    CommandGroup commandGroup = commandFactory.invokeCommand("load");

    assertNotNull(commandGroup);
    assertNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("LoadCommand", commandGroup.getApplyCommand().toString());

    commandGroup = commandFactory.invokeCommand("save");

    assertNotNull(commandGroup);
    assertNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("SaveCommand", commandGroup.getApplyCommand().toString());
  }

  /**
   * test for InvokeBlurCommand.
   */
  @Test
  public void testInvokeBlurCommand() {
    CommandGroup commandGroup = commandFactory.invokeCommand("load");

    assertNotNull(commandGroup);
    assertNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("LoadCommand", commandGroup.getApplyCommand().toString());

    commandGroup = commandFactory.invokeCommand("blur");

    assertNotNull(commandGroup);
    assertNotNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("BlurCommand", commandGroup.getApplyCommand().toString());
  }

  /**
   * test for InvokeSepiaCommand.
   */
  @Test
  public void testInvokeSepiaCommand() {
    CommandGroup commandGroup = commandFactory.invokeCommand("load");

    assertNotNull(commandGroup);
    assertNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("LoadCommand", commandGroup.getApplyCommand().toString());

    commandGroup = commandFactory.invokeCommand("sepia");

    assertNotNull(commandGroup);
    assertNotNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("SepiaCommand", commandGroup.getApplyCommand().toString());
  }

  /**
   * test for InvokeLumaCommand.
   */
  @Test
  public void testInvokeLumaCommand() {
    CommandGroup commandGroup = commandFactory.invokeCommand("load");

    assertNotNull(commandGroup);
    assertNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("LoadCommand", commandGroup.getApplyCommand().toString());

    commandGroup = commandFactory.invokeCommand("luma");

    assertNotNull(commandGroup);
    assertNotNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("LumaComponentCommand", commandGroup.getApplyCommand().toString());
  }

  /**
   * test for InvokeRedComponentCommand.
   */
  @Test
  public void testInvokeRedComponentCommand() {
    CommandGroup commandGroup = commandFactory.invokeCommand("load");

    assertNotNull(commandGroup);
    assertNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("LoadCommand", commandGroup.getApplyCommand().toString());

    commandGroup = commandFactory.invokeCommand("red");

    assertNotNull(commandGroup);
    assertNotNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("RedComponentCommand", commandGroup.getApplyCommand().toString());
  }

  /**
   * test for InvokeGreenComponentCommand.
   */
  @Test
  public void testInvokeGreenComponentCommand() {
    CommandGroup commandGroup = commandFactory.invokeCommand("load");

    assertNotNull(commandGroup);
    assertNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("LoadCommand", commandGroup.getApplyCommand().toString());

    commandGroup = commandFactory.invokeCommand("green");

    assertNotNull(commandGroup);
    assertNotNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("GreenComponentCommand", commandGroup.getApplyCommand().toString());
  }

  /**
   * test for InvokeBlueComponentCommand.
   */
  @Test
  public void testInvokeBlueComponentCommand() {
    CommandGroup commandGroup = commandFactory.invokeCommand("load");

    assertNotNull(commandGroup);
    assertNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("LoadCommand", commandGroup.getApplyCommand().toString());

    commandGroup = commandFactory.invokeCommand("blue");

    assertNotNull(commandGroup);
    assertNotNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("BlueComponentCommand", commandGroup.getApplyCommand().toString());
  }

  /**
   * test for InvokeHorizontalFlipCommand.
   */
  @Test
  public void testInvokeHorizontalFlipCommand() {
    CommandGroup commandGroup = commandFactory.invokeCommand("load");

    assertNotNull(commandGroup);
    assertNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("LoadCommand", commandGroup.getApplyCommand().toString());

    commandGroup = commandFactory.invokeCommand("horizontal-flip");

    assertNotNull(commandGroup);
    assertNotNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("HorizontalFlipCommand", commandGroup.getApplyCommand().toString());
  }

  /**
   * test for InvokeVerticalFlipCommand.
   */
  @Test
  public void testInvokeVerticalFlipCommand() {
    CommandGroup commandGroup = commandFactory.invokeCommand("load");

    assertNotNull(commandGroup);
    assertNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("LoadCommand", commandGroup.getApplyCommand().toString());

    commandGroup = commandFactory.invokeCommand("vertical-flip");

    assertNotNull(commandGroup);
    assertNotNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("VerticalFlipCommand", commandGroup.getApplyCommand().toString());
  }

  /**
   * test for InvokeAdjustedLevelsCommand.
   */
  @Test
  public void testInvokeAdjustedLevelsCommand() {
    CommandGroup commandGroup = commandFactory.invokeCommand("load");

    assertNotNull(commandGroup);
    assertNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("LoadCommand", commandGroup.getApplyCommand().toString());

    commandGroup = commandFactory.invokeCommand("adjust-levels");

    assertNotNull(commandGroup);
    assertNotNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("LevelsAdjustmentCommand", commandGroup.getApplyCommand().toString());
  }

  /**
   * test for InvokeColorCorrectedCommand.
   */
  @Test
  public void testInvokeColorCorrectedCommand() {
    CommandGroup commandGroup = commandFactory.invokeCommand("load");

    assertNotNull(commandGroup);
    assertNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("LoadCommand", commandGroup.getApplyCommand().toString());

    commandGroup = commandFactory.invokeCommand("color-corrected");

    assertNotNull(commandGroup);
    assertNotNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("ColorCorrectionCommand", commandGroup.getApplyCommand().toString());
  }

  /**
   * test for InvokeCompressCommand.
   */
  @Test
  public void testInvokeCompressCommand() {
    CommandGroup commandGroup = commandFactory.invokeCommand("load");

    assertNotNull(commandGroup);
    assertNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("LoadCommand", commandGroup.getApplyCommand().toString());

    commandGroup = commandFactory.invokeCommand("compress");

    assertNotNull(commandGroup);
    assertNotNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("CompressCommand", commandGroup.getApplyCommand().toString());
  }

  /**
   * test for InvokeSharpenCommand.
   */
  @Test
  public void testInvokeSharpenCommand() {
    CommandGroup commandGroup = commandFactory.invokeCommand("load");

    assertNotNull(commandGroup);
    assertNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("LoadCommand", commandGroup.getApplyCommand().toString());

    commandGroup = commandFactory.invokeCommand("sharpen");

    assertNotNull(commandGroup);
    assertNotNull(commandGroup.getPreviewCommand());
    assertNotNull(commandGroup.getApplyCommand());
    assertEquals("SharpenCommand", commandGroup.getApplyCommand().toString());
  }

}