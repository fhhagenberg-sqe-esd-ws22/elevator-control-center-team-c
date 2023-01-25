# Graphical User Interface with JavaFx

## Developer:
## - Alexander Rupp
## - Julian Schwarzinger
## - Sebastian Fragner

#
## Description

People are transported in a building with the help of elevators. Different buildings have a different amount of elevators and floors. Beside the mechanical parts that make the elevator work, some kind of control center is needed.

Our assignment is, to create a GUI that represents a ECC (elevator control center). How this system has to work and which specific assignments need to be fulfilled is listed in the document "./doc/Assignment-Project.pdf".


## Testing concept

The application has to be tested to guarantee good quality for our software. So every part of the application has to be tested.

- We started first with simple JUnit tests with single classes.
- Those were extended to integration tests for more complex classes.
- FxRobot was used as a finalization to guarantee correct binding and visialization.
- To test the visualization without the API, MockObjects have been created.
- Code reviews were continuously used while developing due to pull requests with at least one reviewer.
- We experienced pair programming too, when for example a developer got stuck in a problem. In that case we met online as quick as possible and discussed the situation.

As a final check before a successful pull request to the master branch, SonarCloud checks our source code with predefined QualityGates.

In general we used white box / black box testing, unit tests, mock objects and FxRobots.

## Start Application

Please download the application to your preferred folder. First start the simulator with maximal 8 floors and 5 elevators. Then open a terminal, go to the directory where the previous downloaded file is located and run the ECC java application with the following command:

```
java-jar <filename>
```
