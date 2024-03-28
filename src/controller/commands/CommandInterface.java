package controller.commands;

/**
 * The CommandInterface is an interface that defines a contract for implementing
 * commands in the context of a command pattern. Classes that implement this
 * interface should provide an implementation for the execute() method, which
 * represents the action to be taken when the command is executed.
 * The execute() method should return a boolean value to indicate the success
 * or failure of the command execution.
 */
public interface CommandInterface {
  /**
   * Executes the command and performs a specific action.
   * @return true if the command execution was successful, false otherwise.
   *
   * @throws Exception if model throws error while image processing.
   */
  boolean execute() throws Exception;
}
