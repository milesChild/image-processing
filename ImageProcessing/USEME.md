# processableImageImpl-processing

*Version 2: June 17, 2022*
 
## Contributors:
> Miles Child - *Northeastern University, Khoury CSC College & D'Amore McKim BS 2025*
> 
> Aashir Khan - *Northeastern University, Khoury CSC & CSSH 2025*

## Project Description

This is a basic processableImageImpl processing program which offers the client various options for loading, manipulating, and saving images. Images of various types can be loaded into the program and various processableImageImpl-manipulating operations can be conducted on them through the use of console inputs. Examples of operations include multiple flipping operations, dim/brighten operations, and varoius color-conversion operations. A more detailed list of operations, how to use them via the console, and examples of processableImageImpl-manipulation will follow.

***

## Command Instructions for Use

Commands can be made either as string commands made interactively with the program using the ImageProcessingMain class or as commands made in a text file that can be passed into the ImageProcessingMain class.

**Commands:**

Load, save, or manipulate an processableImageImpl, either adding or pulling it from the model's _imageLibrary_.

> **Brighten:** allows user to brighten an processableImageImpl by a certain value and save it to a new name.
> 
> **Dim:** allows user to dim an processableImageImpl by a certain value and save it to a new name.
> 
> **HorizontalFlip:** allows the user to horizontally-flip an processableImageImpl and save it under a new name.
> 
> **VerticalFlip:** allows the user to vertically-flip an processableImageImpl and save it under a new name.
> 
> **Blur:** allows the user to apply a blurring filter to an processableImageImpl and save it under a new name.
> 
> **Sharpen:** allows the user to apply a sharpening filter to an processableImageImpl and save it under a new name.
> 
> **Grayscale:** allows the user to apply a grayscale conversion to an processableImageImpl and save it under a new name. The user can either apply a simple grayscaling filter to the image or specify a specific grayscaling type for the image they wish to filter.
> 
> **Sepia:** allows the user to apply a sepia filter to a processableImageImpl and save it under a new name.
> 
> **Load:** allows the user to load in a new processableImageImpl from the specified processableImageImpl-path and add it to the current _imageLibrary_.
> 
> **Save:** allows the user to save an processableImageImpl that exists in the _imageLibrary_ to the specified processableImageImpl-path.
> 

***

# Interacting With Program:

- First, the user should navigate to the _ImageProcessingMain_ class and run the main method. This will prompt the program to run.

- Now the user can load in images, manipulate them, and save them to their device.

**Type Instructions:**

The syntax the user should use when using the console to interact with the program.

> **Common references:**
> 
> - *processableImageImpl-path:* the path of the processableImageImpl on the user's device. For example, ImageProcessing/src/Henock.ppm
> 
> - *processableImageImpl-name:* the name that will henceforth be how the user should refer to the processableImageImpl. For example, HenockImage
> 

**Loading an processableImageImpl:**

`load processableImageImpl-path processableImageImpl-name`

**Saving an processableImageImpl:**

`save processableImageImpl-path processableImageImpl-name`

**Horizontally flipping an processableImageImpl:**

`horizontal-flip processableImageImpl-name new-processableImageImpl-name`

**Vertically flipping an processableImageImpl:**

`vertical-flip processableImageImpl-name new-processableImageImpl-name`

**Brighten an processableImageImpl:**

`brighten value processableImageImpl-name new-processableImageImpl-name`

**Dim an processableImageImpl:**

`dim value processableImageImpl-name new-processableImageImpl-name`

**Grayscale an processableImageImpl:**

`grayscale grayscaleChoice processableImageImpl-name new-processableImageImpl-name`

- See below for the different types of grayscaleChoice commands that can be used.

**Blur an processableImageImpl:**

`blur processableImageImpl-name new-processableImageImpl-name`

**Sharpen an processableImageImpl:**

`sharpen processableImageImpl-name new-processableImageImpl-name`

**Sepia an processableImageImpl:**

`sepia processableImageImpl-name new-processableImageImpl-name`

**Quit:**

type 'q' or 'quit' to quit the program.

**_GrayscaleChoice_ options:**

Note: The following grayscaleChoices should be typed as-is as arguments into the program.

- red
- green
- blue
- value
- intensity
- luma
- transformation (applies a grayscale filter)

**Conditions**

- Adhere to the syntax found above
- At least one image must be loaded in to the program using the load function before any manipulation or saving can be done
- Images cannot be saved to the client's device if there are no images in the imageLibrary (i.e. the user has not loaded any images into the program).

**Example Interaction**

`load ImageProcessing/Henock.ppm henock`

`brighten 10 henock henock-bright`

`save ImageProcessing/HenockBright.ppm henock-bright`

*The above code will load the processableImageImpl Henock.ppm from the ImageProcessing project folder, brighten it by 10, and save it in the ImageProcessing project folder under the name HenockBright.ppm*

***

**Contact:**
- Miles: child.m@northeastern.edu
- Aashir: khan.aas@northeastern.edu

Note: The processableImageImpl provided in the code for grading purposes is our own processableImageImpl. We give ourselves permission to use our own processableImageImpl.
