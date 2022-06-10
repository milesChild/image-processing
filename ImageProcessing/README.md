# image-processing

*Version 1: June 9, 2022*
 
## Contributors:
> Miles Child - *Northeastern University, Khoury CSC College & D'Amore McKim BS 2025*
> 
> Aashir Khan - *Northeastern University, Khoury CSC & CSSH 2025*

## Project Description

This is a basic image processing program which offers the client various options for loading, manipulating, and saving images. PPM images can be loaded into the program and various image-manipulating operations can be conducted on them through the use of console inputs. Examples of operations include multiple flipping operations, dim/brighten operations, and varoius color-conversion operations. A more detailed list of operations, how to use them via the console, and examples of image-manipulation will follow.

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

Interface for an ImageProcessingModel which contains the operations that can be conducted on images in any model implementation. Implementations will have a HashMap of known images that can be operated on, and the operations which change the appearance of images will call upon a particular PPM image that is stored in the model's knownImages map.

**ImageProcessingModelImpl Class:**

The core implementation of the ImageProcessingModel interface. This model stores a hashmap of images - the *imageLibrary* - which holds all the images that users can conduct operations on. Images can be added to the imageLibrary via use of the *load(String path, String name)* method. The model also stores various methods for operations that can be conducted on the images. Each of these methods delegates actual manipulation to the PPMImage class.

**Pixel Class:**

This class represents a Pixel, which is referenced throughout the entire rest of the project. PPM images are made up of pixels, each of which has a Red, Green, and Blue component. RGB values are stored in the format _0-255, 0-255, 0-255_, 255 being the maximum value for any particular color component. Users can also specify an alternate _maxValue_ for the color component.

**PPMImage Class:**

Class to represent a PPM (Portable Pix Map) image, which stores all the pixel values in a 2D array of Pixels of a specified height and width. This program (currently) only supports manipulation of PPM-type images. This class stores all the actual image-manipulation operations that can be conducted by the user; each operation will directly manipulate the 2D array of pixels, _pixelGrid_.

## View:

Our view includes the following:

- ImageProcessingView interface
- ImageTextViewImpl class

**ImageProcessingView interface:**

Interface that will be implemented by the different view types (currently, we only support a simple text viewing system for the client, but we anticipate more intricate view systems for better UX). This interface holds all of the methods that are common across all view implementations. 

**ImageTextViewImpl class:**

Implementation of the image view class which offers a primitive method of displaying user commands and their results as strings on the command line. This class will accept orders from the controller in the form of strings to display to the user.


## Controller:

Our controller includes the following:

- ImageProcessingController interface
- ImageProcessingControllerImpl class
- commands package

**ImageProcessingController interface:**

Interface that will be implemented by all of the controller implementations (currently, only one supported) and will hold all of the methods that must be 
common amongst all implementations. Includes a runProgram() method that enables the client to pass in commands that will be transmitted to the model for image-manipulation, loading, and saving. User can _quit_ the program at any time by entering "q".

**ImageProcessingControllerImpl class:**

Primary implementation of the controller interface. This controller class is constructed with an ImageProcessingModel (referenced when conducting operations), a Readable for user input, an ImageProcessingView to transmit information to the user, and a map of known commands. 

**commands package:**

This package includes various command objects that represent different manipulative-operations that can be conducted by the user. For example, the _Brighten_ class represents a brightening command that will increase the brightness of any image by the specified brightness-quotient and save it to a new file.

The commands package has an ImageProcessingCommand interface that holds the execute(ImageProcessingModel model) method. This is the method that each of the class-implementations of this interface use to interact with the model. Each implementation represents a different type of command that the user can use when interacting with the program. 

This package also includes an AbstractCommand class which has abstracted out the redundant parts of the individual command implementations for code-reuse and simplicity.

**Commands:**

Load, save, or manipulate an image, either adding or pulling it from the model's _imageLibrary_.

> **Brighten:** allows user to brighten an image by a certain value and save it to a new name.
> 
> **Dim:** allows user to dim an image by a certain value and save it to a new name.
> 
> **Grayscale:** allows the user to convert an image to the specified grayscale type and save it to a new name.
> 
> **HorizontalFlip:** allows the user to horizontally-flip an image and save it under a new name.
> 
> **VerticalFlip:** allows the user to vertically-flip an image and save it under a new name.
> 
> **Load:** allows the user to load in a new image from the specified image-path and add it to the current _imageLibrary_.
> 
> **Save:** allows the user to save an image that exists in the _imageLibrary_ to the specified image-path.
> 

***

# Interacting With Program:

- First, the user should navigate to the _ImageProcessingMain_ class and run the main method. This will prompt the program to run.

- Now the user can load in images, manipulate them, and save them to their device.

**Type Instructions:**

The syntax the user should use when using the console to interact with the program.

> **Common references:**
> 
> - *image-path:* the path of the image on the user's device. For example, ImageProcessing/src/Henock.ppm
> 
> - *image-name:* the name that will henceforth be how the user should refer to the image. For example, HenockImage
> 

**Loading an image:**

`load image-path image-name`

**Saving an image:**

`save image-path image-name`

**Horizontally flipping an image:**

`horizontal-flip image-name new-image-name`

**Vertically flipping an image:**

`vertical-flip image-name new-image-name`

**Brighten an image:**

`brighten value image-name new-image-name`

**Dim an image:**

`dim value image-name new-image-name`

**Grayscale an image:**

`grayscale grayscaleChoice image-name new-image-name`

**Example Interaction**

`load ImageProcessing/Henock.ppm henock`

`brighten 10 henock henock-bright`

`save ImageProcessing/HenockBright.ppm henock-bright`

*The above code will load the image Henock.ppm from the ImageProcessing project folder, brighten it by 10, and save it in the ImageProcessing project folder under the name HenockBright.ppm*

***

**Contact:**
- Miles: child.m@northeastern.edu
- Aashir: khan.aas@northeastern.edu

Note: The image provided in the code for grading purposes is our own image. We give ourselves permission to use our own image.
