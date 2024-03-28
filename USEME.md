# USEME - GRIME: Graphical Image Manipulation and Enhancement - User Guide

## Loading an Image
  - Step 1: Click on the 'Load Image' button.
  - Step 2: Navigate to the image file (supports PPM/JPG/PNG formats) and select it.
  - Step 3: The image will be displayed in the main view area.


## Saving an Image
  - Step 1: After editing, click the 'Save Image' button.
  - Step 2: Choose the desired format as the file extension (PPM, JPG, PNG) and save location.
  - Step 3: Name your file with extension mentioned above and click 'Save'.


## Visualizing RGB Components
  - Step 1: Click on either Red, Green or Blue button
  - Step 2: The image with the respective selected component will be displayed.
  - Step 3: Click on Confirm button to apply the filter on the image or cancel button to exit preview.


## Flipping the Image
  - Step 1:
    - Option 1: Horizontal-Flip - Click on this button to flip the loaded image horizonatlly.
    - Option 2: Vertical-Flip - Click on this button to flip the loaded image vertically.
  - Step 2: Click on Confirm button to apply the flip on the image or cancel button to exit preview.


## Applying Blur
  - Step 1: Click on the 'Blur' button from the menu.
  - Step 3: Enter a split percentage on which you want to preview blur effect.
  - Step 3: Click on Confirm button to apply the filter on entire image or cancel button to exit preview.


## Sharpening the Image
  - Step 1: Click on the 'Sharpen' button from the menu.
  - Step 3: Enter a split percentage on which you want to preview sharpening effect.
  - Step 3: Click on Confirm button to apply the filter on entire image or cancel button to exit preview.


## Converting to Greyscale
  - Step 1: Click on the 'Luma' button from the menu.
  - Step 3: Enter a split percentage on which you want to preview luma effect.
  - Step 3: Click on Confirm button to apply the filter on entire image or cancel button to exit preview.


## Applying Sepia Tone
  - Step 1: Click on the 'Sepia' button from the menu.
  - Step 3: Enter a split percentage on which you want to preview sepia effect.
  - Step 3: Click on Confirm button to apply the filter on entire image or cancel button to exit preview.


## Viewing with Compression Artifacts
  - Step 1: Choose 'Compression Artifacts' from the menu.
  - Step 2: Enter the desired compression factor.


## Color Correction
  - Step 1: Click on the 'Color-Corrected' button from the menu.
  - Step 3: Enter a split percentage on which you want to preview color correction effect.
  - Step 3: Click on Confirm button to apply the filter on entire image or cancel button to exit preview.


## Adjusting Levels
  - Step 1: Click on the 'Adjust-Levels' button from the menu.
  - Step 3: Enter values for bright, mid and white and a split percentage on which you want to preview levels adjust effect.
  - Step 3: Click on Confirm button to apply the filter on entire image or cancel button to exit preview.


## Split View Functionality
  - This functionality is a part of Blur, Color-Correction, Luma, Sepia and Sharpen features.
  - Whenever these function buttons are selected, a split percentage is asked as input which enables a preview of that feature on the specified percetange of the image.

## Running Script Commands:

### Running a Script at Program Start:
To execute a script on start of the application, use the following syntax:
```bash
    java -jar Assignment6.jar -file <filename>
```
- Use the script file `testNewFeaturesJar.txt` in the `res` folder of the project.
- Run the jar file by using the command `java -jar Assignment6.jar -file testNewFeaturesJar.txt`, to test the newly added features in the assignment-5.
- Run the jar file by using the command `java -jar Assignment6.jar -file testFeaturesJar.txt`, to test all the features in the assignment-4 and assignment-5.
- The application will sequentially execute commands from `testNewFeaturesJar.txt`, storing the results to the specified directory and exit program execution.

### Running the Program in Console View:
A user can run the program in console view using the below syntax:

```bash
    java -jar Assignment6.jar -text
```

### Running the Program in GUI View:
A user can run the program in GUI view using the below syntax:

```bash
    java -jar Assignment6.jar
```

OR

A user can be simply click of Assignment6.jar to open GUI.

### Running a Script After Program Start in Console View:
A user can also run a script after the application has started by following the below steps:

```bash
    run <filename>
```

- Place the script file (example: commands.txt) in the `res` folder of your project.
- Run the `jar` file by using the command `java -jar program.jar`.
- When prompted for commands, type out: `run res/commands.txt`.
- The application will sequentially execute commands from `commands.txt`, outputting results to the specified directory.