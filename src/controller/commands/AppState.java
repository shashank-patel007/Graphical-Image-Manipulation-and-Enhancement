package controller.commands;

/**
 * Enumeration of possible states for the image processing application.
 * - NO_IMAGE_LOADED: Indicates that no image is currently loaded.
 * - IMAGE_LOADED: Indicates that an image is currently loaded.
 * - IMAGE_SAVED: Indicates that the current image has been saved.
 */
public enum AppState {
  NO_IMAGE_LOADED,
  IMAGE_LOADED,
  IMAGE_SAVED
}
