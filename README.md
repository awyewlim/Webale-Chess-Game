# Webale-Chess-Game
Webale chess is a special type of chess game with different rules and is played on a 7x8 board. It is implemented as a GUI-based Java Application based on the requirement of university assignment.

![](https://media.giphy.com/media/gdYl8fqwIW3N76cKjg/giphy.gif)

## Rules
<img align="left" src="https://github.com/awyewlim/Webale-Chess-Game/blob/master/Assets/SunR.png?raw=true" width="25" height="25">**Sun** - Can only move one step in any direction. The game ends when the Sun is captured by the other side.

<img align="left" src="https://github.com/awyewlim/Webale-Chess-Game/blob/master/Assets/ChevronR.png?raw=true" width="25" height="25">**Chevron** - Moves in an L shape: 2 steps in one direction then 1 step perpendicular to it. (Similar to the Knight in the normal chess.) It is the only piece that can skip over the other pieces.

<img align="left" src="https://github.com/awyewlim/Webale-Chess-Game/blob/master/Assets/TriangleR.png?raw=true" width="25" height="25">**Triangle** - Can move any number of steps diagonally.

<img align="left" src="https://github.com/awyewlim/Webale-Chess-Game/blob/master/Assets/PlusR.png?raw=true" width="25" height="25">**Plus** - Can move any number of steps up and down, or left and right.

<img align="left" src="https://github.com/awyewlim/Webale-Chess-Game/blob/master/Assets/ArrowR.png?raw=true" width="25" height="25">**Arrow** - Can only move 1 or 2 steps forward each time, but when it reaches the other edge of the board, it turns around and heads back in the opposite direction.


**After blue has moved 2 times, all the blue Triangles will turn into Pluses and vice versa. Similarly, after red has moved 2 times, all the red Triangles will turn into Plusses and vice versa. Then they will change again after the 4th move, 6th move, etc. This makes Webale chess different from normal chess games, because the pieces will transform like that.**

## How to run?
1. Download files from this repository
2. Open terminal/command prompt and set path to the file
```
  cd YOURFILEPATH
```
3. Compile and run `Main.java` with the following commands
```
  javac Main.java
  java Main
```
4. Enjoy playing :wink:
