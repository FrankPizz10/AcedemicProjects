# AcedemicProjects - Animations

The goal of this project is to intake a text file containing animations in the format: motion disk1 1 190 180 20 30 0 49 90  1 190 180 20 30 0 49 90. In this way the program receives animations containg information on starting position, color, size and time of a given shape along with the values at the end of the animation. The program uses a model view controller design in order to produce a visual rendering of the text input. The program is also capable of outtputting a textual and svg output using different view implementations.

The program parses the input data and stores the information in the model. Each shape is saved as an IShape which contains its name and current properties. IAnimations contain a shape along with the starting and ending values. The model contains a HashMap with IShapes mapped to an ArrayList of IAnimations. The visual view uses JFrames and and Canvas to create the animation. The Canvas is repainted every tick and a timer continously runs. Each tick we use a function linearInterpolate to find the values of a shape at a given time using linear interpolation. We use the starting and ending values from the model for each shape if our time is within the range of the animation. We apply linearinterpolate and then draw these values to the Canvas. The result is a continuous motion animation depicting the shapes moving across a pop up screen.

# Program Arguements

To watch the animation of a hanoi tower included in this repo enter the following arguement in program arguements:
-in <directory>\hanoi.txt -view visual -speed 100

-in represents the input file adress
-view can be either visual, textual, or svg text
-speed is the animation rate the higher the number the slower the animation


