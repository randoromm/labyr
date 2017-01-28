# My first bigger Java project - Labyr #

This was a course project for my Java course in Estonian Information Technology College. The project is still not finished because the capacity of this project was way too optimistic because of the course length and my insufficient skills back then. I'm still glad i chose this game as a project because it quickly taught me a lot about programming fundamentals. I decided to write almost everything from scratch and therefore i only used standard java libraries and wrote my own engine for the game.

### Basic Idea ###

The game was intended to be top-down rougelike game, where each level would be a labyrinth. In the labyrinth you face different obstacles and enemies you have to kill. In the end of each level/labyrinth there's a boss fight.

### Current state ###

Even though i didn't finish the game, the game engine is in state where continuing wouldn't be too hard. I wrote a proper tile and sprite system for the game and made loading textures/sprites easy. Levels can be designed in MS Paint. Each pixel represents a tile on the map. Tiles can be differentiated by colors of the pixels.

Currently the game has 1 test level. The only mob right now is the player. The player sprites are animated (walking animations). You can move the player with arrow keys or 'WASD'. I've already implemented collision detection, so you cant walk through solid tiles. I also implemented pixel perfect shooting mechanism to the game. You can shoot little bullets by clicking Mouse1. The bullets also have collision detection with solid tiles which could be improved a little bit. Finally i tried to implement particle effects. I managed to get them working but i haven't finished implementing proper particle physics.

### Future? ###

I plan on continuing working on this project, but for now i have moved on to other projects to improve my programming skills.