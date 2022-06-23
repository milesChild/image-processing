# flow-in-the-field sandboxNEU challenge

## Contributor:
> Miles Child - *Northeastern University, Khoury CSC College & D'Amore McKim BS 2025*

## Project Description

This program is designed to work with user data from the app "Flow In The Field", which is an app used by the NU Psychology Department that allows researchers to investigate the flow state. This program uses a PULL request (using postman) to take a JSON file of user data and converts it into another JSON file of statistics, generated based on that user data.

***

## Design Choices

> My program executes the data initialization (from the GET request), passes that data to a model that can manipulate it into formatted statistics, then converts the statistic data into a new JSON to be POSTed back to the endpoint used with the GET. All of this execution occurs in the `main` class.
> 
> My model is essentially divided into two parts: raw data and model data. The raw data is in the format that exactly matches the format of the JSON file from the GET request. Objects of the model data have slightly different fields to allow for statistic generation. All of these objects communicate through the `Data` interface.
> 
> Every object of the Data interface has a method to convert to raw data or to model data. This allows me to convert the original data from the JSON into workable data (model data) then back when I want to generate the JSON with the statistics.
> 
> I used google's GSON library for converting to/from Json. This library allows you to create objects that dictate formatting rules when you convert to a JSON file; this is how I got the non-existing data values to be "N/A" when converting to the JSON.

# Interacting With Program:

- First, navigate to the _main_ class.

- Now, in the `deserialize` method, change the path of the file that you want to take in a JSON file of data from.

- Now, just run the main and the JSON of statistics will be generated and placed as "post.json" in the src folder

## Improvements I would make

> I notice that there is a substantial amount of code re-use between the rawData and modelData side. If I were to take more time, I would find a way to combine these sides. I would probably make one Data interface and a new Statistic interface. The objects of these interfaces would all have methods to convert to and from raw/manipulable data. 
> 
> My access modifiers are absolutely not perfect and given more time I would clean my code up to ensure that each method/class has/exposes only the necessary information. 

**Contact:**
- Miles: child.m@northeastern.edu