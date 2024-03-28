package controller.commands;

/**
 * The CommandGroup class represents a pair of CommandInterface
 * objects, typically used to store a preview command
 * and an apply command in the context of image
 * processing operations.
 */
public class CommandGroup {
  private final CommandInterface previewCommand;
  private final CommandInterface applyCommand;

  /**
   * Constructs a CommandGroup with the specified preview and apply commands.
   *
   * @param previewCommand The CommandInterface for previewing an operation.
   * @param applyCommand   The CommandInterface for applying an operation.
   */
  public CommandGroup(CommandInterface previewCommand, CommandInterface applyCommand) {
    this.previewCommand = previewCommand;
    this.applyCommand = applyCommand;
  }

  /**
   * Gets the preview command.
   *
   * @return The CommandInterface for previewing an operation,
   *         or null if no preview command is set.
   */
  public CommandInterface getPreviewCommand() {
    return previewCommand;
  }

  /**
   * Gets the apply command.
   *
   * @return The CommandInterface for applying an operation,
   *         or null if no apply command is set.
   */
  public CommandInterface getApplyCommand() {
    return applyCommand;
  }

  /**
   * Checks if a preview command is present.
   *
   * @return True if a preview command is set, false otherwise.
   */
  public boolean hasPreview() {
    return previewCommand != null;
  }

  /**
   * Checks if an apply command is present.
   *
   * @return True if an apply command is set, false otherwise.
   */
  public boolean hasApply() {
    return applyCommand != null;
  }
}

