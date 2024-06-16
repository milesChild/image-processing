# Image Processing

*Version 1: June 9, 2022*
 
## Contributors:
> Miles Child - *Northeastern University, Khoury CSC College & D'Amore McKim BS 2025*
> 
> Aashir Khan - *Northeastern University, Khoury CSC & CSSH 2025*

## Project Description

This is a Image processing program which offers the client various options for loading, manipulating, and saving images. Images of various types can be loaded into the program and various Image-manipulating operations can be conducted on them through the use of console inputs. Examples of operations include multiple flipping operations, dim/brighten operations, and various color-conversion operations. A more detailed list of operations, how to use them via the console, and examples of Image-manipulation will follow.

***

## Design Changes from Assignment 5 & New Design Decisions
- For the Downscale implementation, we did not change much of our code, we simply added a new method in the ImageProcessingModel and ProcessableImage interfaces/classes and added some extra helper methods in the ProcessableImage class.
- For the Partial Image Manipulation implementation, we did not change much of our code as well. We added a new command that extends AbstractCommand called PartialManipulation. Then we added a method in the ImageProcessingModel interface/class to handle this command. We also added two helper methods in the ProcessableImage class that help selectively edit an image. 

## Design Changes from Assignment 4 & New Design Decisions

> Taking feedback from A4 into account and in an attempt to abstract as much code as possible, we renamed our PPMImage class to ProcessableImageImpl class and made an interface, ProcessableImage that holds all of the common commands.
> 
> Also in the ProcessableImageImpl class, we modified the constructors to be able to support loading in of any file type from a path. The constructor calls on the appropriate helper based on the image type the user wants to load in.
> 
> As soon as an image is loaded in, it is automatically converted into a ProcessableImageImpl object. This removes the necessity of having individual objects for each of the different file types that a user may load in.
> 
> We decided to do image I/O in the controller implementation rather than the model. (i.e. we moved the load and save methods into the controller and out of the model)

# Model, View, Controller Overview:

In this section, we will conduct a thorough overview of the purposes and uses of each of the interfaces and classes for the model, view, and controller. Note: This project makes use of the **command design pattern**, and an overview of the *commands* package will be found in the Controller section. 

## Model:

Our model includes the following:

- ImageProcessingModel interface
- ImageProcessingModelImpl class
- Pixel class
- ProcessableImage interface
- ProcessableImageImpl class


**ImageProcessingModel Interface:**

Interface for an ImageProcessingModel which contains the operations that can be conducted on images in any model implementation. Implementations will have a HashMap of known images that can be operated on, and the operations which change the appearance of images will call upon a particular PPM Image that is stored in the model's knownImages map.

**ImageProcessingModelImpl Class:**

The core implementation of the ImageProcessingModel interface. This model stores a hashmap of images - the *imageLibrary* - which holds all the images that users can conduct operations on. Images can be added to the imageLibrary via use of the *load(String path, String name)* method. The model also stores various methods for operations that can be conducted on the images. Each of these methods delegates actual manipulation to the PPMImage class.

**Pixel Class:**

This class represents a Pixel, which is referenced throughout the entire rest of the project. PPM images are made up of pixels, each of which has a Red, Green, and Blue component. RGB values are stored in the format _0-255, 0-255, 0-255_, 255 being the maximum value for any particular color component. Users can also specify an alternate _maxValue_ for the color component.

**ProcessableImage Interface:**

The interface to represent a processable image, which is the common image type that is used by the program when actual operations are conducted on an image. Regardless of the file type of image loaded into the program, it will automatically be converted (upon the load command) into a _ProcessableImageImpl_. This interface supports the ability to load and save various common types of image files and holds the common methods that an Image object will need for image manipulation.

**ProcessableImageImpl Class:**

Class to represent an image, which stores all the pixel values in a 2D array of Pixels of a specified height and width. This class stores all the actual image-manipulation operations that can be conducted by the user each operation will directly manipulate the 2D array of pixels, _pixelGrid_.

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
common amongst all implementations. Includes a runProgram() method that enables the client to pass in commands that will be transmitted to the model for Image-manipulation, loading, and saving. User can _quit_ the program at any time by entering "q".

**ImageProcessingControllerImpl class:**

Primary implementation of the controller interface. This controller class is constructed with an ImageProcessingModel (referenced when conducting operations), a Readable for user input, an ImageProcessingView to transmit information to the user, and a map of known commands. 

**commands package:**

This package includes various command objects that represent different manipulative-operations that can be conducted by the user. For example, the _Brighten_ class represents a brightening command that will increase the brightness of any Image by the specified brightness-quotient and save it to a new file.

The commands package has an ImageProcessingCommand interface that holds the execute(ImageProcessingModel model) method. This is the method that each of the class-implementations of this interface use to interact with the model. Each implementation represents a different type of command that the user can use when interacting with the program. 

This package also includes an AbstractCommand class which has abstracted out the redundant parts of the individual command implementations for code-reuse and simplicity.

**Commands:**

Load, save, or manipulate an Image, either adding or pulling it from the model's _imageLibrary_.

> **Brighten:** allows user to brighten an Image by a certain value and save it to a new name.
> 
> **Dim:** allows user to dim an Image by a certain value and save it to a new name.
> 
> **HorizontalFlip:** allows the user to horizontally-flip an Image and save it under a new name.
> 
> **VerticalFlip:** allows the user to vertically-flip an Image and save it under a new name.
> 
> **Blur:** allows the user to apply a blurring filter to an Image and save it under a new name.
> 
> **Sharpen:** allows the user to apply a sharpening filter to an Image and save it under a new name.
> 
> **Grayscale:** allows the user to apply a grayscale conversion to an Image and save it under a new name. The user can either apply a simple grayscaling filter to the image or specify a specific grayscaling type for the image they wish to filter.
> 
> **Sepia:** allows the user to apply a sepia filter to a Image and save it under a new name.
> 
> **Load:** allows the user to load in a new Image from the specified Image and add it to the current _imageLibrary_.
> 
> **Save:** allows the user to save an Image that exists in the _imageLibrary_ to the specified Image-path.
> 

***

# Interacting With Program:

- First, the user should navigate to the _ImageProcessingMain_ class and run the main method. This will prompt the program to run.

- Now the user can load in images, manipulate them, and save them to their device.

**Type Instructions:**

The syntax the user should use when using the console to interact with the program.

> **Common references:**
> 
> - *Image-path:* the path of the Image on the user's device. For example, ImageProcessing/src/Henock.ppm
> 
> - *Image-name:* the name that will henceforth be how the user should refer to the Image. For example, HenockImage
> 

**Loading an Image:**

`load processableImageImpl-path processableImageImpl-name`

**Saving an Image:**

`save processableImageImpl-path processableImageImpl-name`

**Horizontally flipping an Image:**

`horizontal-flip processableImageImpl-name new-processableImageImpl-name`

**Vertically flipping an Image:**

`vertical-flip processableImageImpl-name new-processableImageImpl-name`

**Brighten an Image:**

`brighten value processableImageImpl-name new-processableImageImpl-name`

**Dim an Image:**

`dim value processableImageImpl-name new-processableImageImpl-name`

**Grayscale an Image:**

`grayscale grayscaleChoice processableImageImpl-name new-processableImageImpl-name`

**Blur an Image:**

`blur processableImageImpl-name new-processableImageImpl-name`

**Sharpen an Image:**

`sharpen processableImageImpl-name new-processableImageImpl-name`

**Sepia an Image:**

`sepia processableImageImpl-name new-processableImageImpl-name`

**Example Interaction**

`load ImageProcessing/Henock.ppm henock`

`brighten 10 henock henock-bright`

`save ImageProcessing/HenockBright.ppm henock-bright`

*The above code will load the Image Henock.ppm from the ImageProcessing project folder, brighten it by 10, and save it in the ImageProcessing project folder under the name HenockBright.ppm*

***

**Contact:**
- Miles: child.m@northeastern.edu
- Aashir: khan.aas@northeastern.edu
