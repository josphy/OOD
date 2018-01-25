Homework 05: Command-dispatching Ballworld with Inter-ball Interactions
Team Member: Zheng You, Haoshan Zou

1. How to run the program?
Simply create a new java project in Eclipse using the soure codes (*.java) in
the src folder. After successfully created the project, click the green button
"run" in the Toolbars to execute the program. A runnable JAR file can be
generated through File -> export -> Java -> Runnable JAR file.


2. Describe what options are available and what your program is doing.
The GUI program has three functions: create shapes with specified update
strategy and paint strategy, create switchers whose update and paint strategies can be changed later, and
clear all the shapes on the canvas.

On top of the GUI program, there are four panels and one button: Update
Strategy panel, Make Shapes panel, Switcher Controls panel, Clear All button,
and Shape panel.

--------------------------------------------------------------------------------
Update Stategy panel:
This panel is used to add an update strategy to the system. The allowed update
strategies are:
Regular Balls:
 - Breathing
 - Color
 - Curve
 - Drunken
 - Spawn
 - Straight
 - Wander
Interaction Balls with combined criteria and interaction behavior:
 - Collide
 - Flash
 - Infect
 - Spawn
Balls that only detect certain criteria:
 - Overlap
 - SimilarColor
 - StayAway
Balls that only installs interact strategy (combine with criteria to interact):
 - Eat
 - Escape
 - Freeze
 - Inverse
 - Kill

Type in one of the names from above and click "Add to lists", the name typed in
will be added to the two droplists in the Make Shapes panel to the right. The
default update strategy which is pre-typed in the text field is Straight.
--------------------------------------------------------------------------------
Shape panel:
The reason why Shape panel is discussed before Make Shapes panel is because the
latter one relies on the Shape panel to function. This panel is used to add
shapes to the system. The allowed shapes are:
 - Ball
 - Ellipse
 - Fish1
 - Fish2
 - SwimFish
 - NiceFish
 - Planet
 - MarioSonic
 - Rectangle
 - Square

Type in one of the names from above and click "Add to List", the name type in
will be added to the droplist below "Add to List" button. The default shape
which is pre-typed in the text field is Ball.
--------------------------------------------------------------------------------
Make Shapes panel:
This panel is used to create a shape with specified update strategy on the
canvas below and combine update strategies. First choose a strategy in the top
droplist below and a shape in the droplist of the Shape panel, then click
"Make Selected Shape", a shape with designated update strategy will display
on the canvas below. Continuously click the "Make Selected Shape" button will
create more shapes on the canvas.

Choose two update strategies in both the top and bottom droplists and click
"Combine!"; a combination of the two strategies will be added to both droplists.
The combination can be used to create another combination later.
--------------------------------------------------------------------------------
Switcher Control
This panel is used to create switchers and change switcher behaviors. Click
"Make Switcher" and a shape chosen from the droplist of the Shape panel with
Straight update strategy will be created on the canvas. The default update
strategy for switchers is Straight. Choose a strategy in the top droplist of
the Make Shapes panel and click "Switch!". All previously created switchers will
change its update strategy to the one chosen.
--------------------------------------------------------------------------------
Clear All Button
This button is used to clear all shapes on the canvas.
--------------------------------------------------------------------------------


3. Postscript
There are FOUR UML diagrams created for this homework. Corresponding png image files are in uml_pic folder.
 - MVC.uml
   It is located in the src directory, which represents the
   model-view-controller model of the system.
 - paintstrategy.uml
   It is located in the src/model/paint/paintstrategy directory, which depicts
   the relationships of different kinds of paint strategies.
 - updatestrategy.uml
   It is located in the src/model/updatestrategy directory, which describes the
   relationships of different kinds of update strategies.
 - shapefactory.uml
   It is located in the src/model/paint/shapefactory directory, which presents
   the relationships of different kinds of shape factories.


Quote from the requirements from Homework 5 web page

Add an elastic collision strategy.  

Add at least 3 ball-to-ball interactions based on some sort of interaction criteria, where the architecture clearly separates the criteria and the interaction behaviors (e.g. should be dynamically combinable), with at least one each of the following:
 - Interaction criteria is that the balls must have collided, i.e. are overlapping. 
 - Interaction criteria is something other than having collided. 

---------------------
Interact Strategy related:

Strategies that both collide and interact:
- CollideUpdateStrategy: Elastic collision behavior. Uses nudging for rebound positioning.
- FlashUpdateStrategy: When two balls overlap, both of which will flash while overlapping.
- InfectUpdateStrategy: When two balls overlap, make the other ball also an infect ball. Recover to a straight ball after a period of time.
- SpawnUpdateStrategy: Spawn new balls when the ball contacts another one.

Strategies that just detect an interaction and invoke the interaction strategies of the interacting balls.
- OverlapUpdateStrategy: Detects whether two balls overlap.
- SimilarColorUpdateStrategy: Detects whether two balls have similar colors and overlap.
- StayAwayUpdateStrategy: Detect if two balls are less than 20 distance away.

Strategies that only install interaction strategies and thus at least be combined with a strategy that detects an interaction
- EatUpdateStrategy: Eat another ball
- EscapeUpdateStrategy: Escape to a random location
- FreezeUpdateStrategy: Freeze another ball
- InverseUpdateStrategy: Set another ball  to its opposite velocity
- KillUpdateStrategy: Kill another ball
