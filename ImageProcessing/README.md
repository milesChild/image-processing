# processableImageImpl-processing

*Version 1: June 9, 2022*
 
## Contributors:
> Miles Child - *Northeastern University, Khoury CSC College & D'Amore McKim BS 2025*
> 
> Aashir Khan - *Northeastern University, Khoury CSC & CSSH 2025*

## Project Description

This is a basic processableImageImpl processing program which offers the client various options for loading, manipulating, and saving images. PPM images can be loaded into the program and various processableImageImpl-manipulating operations can be conducted on them through the use of console inputs. Examples of operations include multiple flipping operations, dim/brighten operations, and varoius color-conversion operations. A more detailed list of operations, how to use them via the console, and examples of processableImageImpl-manipulation will follow.

***

# Model, View, Controller Overview:

In this section, we will conduct a thorough overview of the purposes and uses of each of the interfaces and classes for the model, view, and controller. Note: This project makes use of the **command design pattern**, and an overview of the *commands* package will be found in the Controller section. 

## Model:

Our model includes the following:

- ImageProcessingModel interface
- ImageProcessingModelImpl class
- Pixel class
- PPMImage class

**ImageProcessingModel Interface:**

Interface for an ImageProcessingModel which contains the operations that can be conducted on images in any model implementation. Implementations will have a HashMap of known images that can be operated on, and the operations which change the appearance of images will call upon a particular PPM processableImageImpl that is stored in the model's knownImages map.

**ImageProcessingModelImpl Class:**

The core implementation of the ImageProcessingModel interface. This model stores a hashmap of images - the *imageLibrary* - which holds all the images that users can conduct operations on. Images can be added to the imageLibrary via use of the *load(String path, String name)* method. The model also stores various methods for operations that can be conducted on the images. Each of these methods delegates actual manipulation to the PPMImage class.

**Pixel Class:**

This class represents a Pixel, which is referenced throughout the entire rest of the project. PPM images are made up of pixels, each of which has a Red, Green, and Blue component. RGB values are stored in the format _0-255, 0-255, 0-255_, 255 being the maximum value for any particular color component. Users can also specify an alternate _maxValue_ for the color component.

**PPMImage Class:**

Class to represent a PPM (Portable Pix Map) processableImageImpl, which stores all the pixel values in a 2D array of Pixels of a specified height and width. This program (currently) only supports manipulation of PPM-type images. This class stores all the actual processableImageImpl-manipulation operations that can be conducted by the user; each operation will directly manipulate the 2D array of pixels, _pixelGrid_.

## View:

Our view includes the following:

- ImageProcessingView interface
- ImageTextViewImpl class

**ImageProcessingView interface:**

Interface that will be implemented by the different view types (currently, we only support a simple text viewing system for the client, but we anticipate more intricate view systems for better UX). This interface holds all of the methods that are common across all view implementations. 

**ImageTextViewImpl class:**

Implementation of the processableImageImpl view class which offers a primitive method of displaying user commands and their results as strings on the command line. This class will accept orders from the controller in the form of strings to display to the user.


## Controller:

Our controller includes the following:

- ImageProcessingController interface
- ImageProcessingControllerImpl class
- commands package

**ImageProcessingController interface:**

Interface that will be implemented by all of the controller implementations (currently, only one supported) and will hold all of the methods that must be 
common amongst all implementations. Includes a runProgram() method that enables the client to pass in commands that will be transmitted to the model for processableImageImpl-manipulation, loading, and saving. User can _quit_ the program at any time by entering "q".

**ImageProcessingControllerImpl class:**

Primary implementation of the controller interface. This controller class is constructed with an ImageProcessingModel (referenced when conducting operations), a Readable for user input, an ImageProcessingView to transmit information to the user, and a map of known commands. 

**commands package:**

This package includes various command objects that represent different manipulative-operations that can be conducted by the user. For example, the _Brighten_ class represents a brightening command that will increase the brightness of any processableImageImpl by the specified brightness-quotient and save it to a new file.

The commands package has an ImageProcessingCommand interface that holds the execute(ImageProcessingModel model) method. This is the method that each of the class-implementations of this interface use to interact with the model. Each implementation represents a different type of command that the user can use when interacting with the program. 

This package also includes an AbstractCommand class which has abstracted out the redundant parts of the individual command implementations for code-reuse and simplicity.

**Commands:**

Load, save, or manipulate an processableImageImpl, either adding or pulling it from the model's _imageLibrary_.

> **Brighten:** allows user to brighten an processableImageImpl by a certain value and save it to a new name.
> 
> **Dim:** allows user to dim an processableImageImpl by a certain value and save it to a new name.
> 
> **Grayscale:** allows the user to convert an processableImageImpl to the specified grayscale type and save it to a new name.
> 
> **HorizontalFlip:** allows the user to horizontally-flip an processableImageImpl and save it under a new name.
> 
> **VerticalFlip:** allows the user to vertically-flip an processableImageImpl and save it under a new name.
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
