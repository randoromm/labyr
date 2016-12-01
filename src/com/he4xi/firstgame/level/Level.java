package com.he4xi.firstgame.level;

import com.he4xi.firstgame.entity.Entity;
import com.he4xi.firstgame.entity.emitter.Emitter;
import com.he4xi.firstgame.entity.emitter.ParticleEmitter;
import com.he4xi.firstgame.entity.particles.Particle;
import com.he4xi.firstgame.entity.projectiles.Projectile;
import com.he4xi.firstgame.graphics.Display;
import com.he4xi.firstgame.level.tile.Tile;

import java.util.ArrayList;

/**
 * The main class for level control
 * Created on 28.09.2016.
 *
 * @author Rando Rommot
 * @version 0.1
 */

/*
* Level class holds an list of tiles, and it organizes which tiles must be rendered
* Tiles render themselves, level class doesn't call it out. Specific tile class (ex. GrassTile.java)
* calls the render method for tiles and the method itself is in Display class.
* Level class can't be ABSTRACT because i want to instantiate some methods through level class directly,
* not through its subclasses.
*/
public class Level {

    protected int width, height;
    protected int[] tileInt; // Tile ID's (which index for which Tile)
    protected int[] tiles;

    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private ArrayList<Particle> particles = new ArrayList<>();

    /**
     * Constructor to create an array for available tiles (indexes) and to generate a random level.
     * @param width Chosen width for the level to  be generated (in tiles).
     * @param height Chosen height for the level to be generated (in tiles).
     */
    public Level(int width, int height) { // Load/generate a random level (1st constructor)
        this.width = width; // Level width in tiles
        this.height = height; // Level height in tiles
        tileInt = new int[width * height]; // Array for tiles ( it holds ID's of tiles (integer))
        generateLevel();
    }

    /**
     * Constructor to load a level from a file.
     * @param path Path to the level file.
     */
    public Level(String path) { // Load/generate level from file (2nd constructor)
        loadLevel(path);
        generateLevel();
    }

    /**
     * Method to generate a level. Blueprint/template for specific level classes.
     */
    protected void generateLevel() {} // Protected instead of private to overwrite methods etc...

    /**
     * Method to load the level from the chosen level file.
     * @param path Path to the level file.
     */
    protected void loadLevel(String path) {}

    private void time() {}

    /**
     * Method to update the level.
     * Updates entities and projectiles.
     */
    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).isRemoved()) entities.remove(i);
            else entities.get(i).update();
        }

        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).isRemoved()) projectiles.remove(i);
            else projectiles.get(i).update();
        }

        for (int i = 0; i < particles.size(); i++) {
            if (particles.get(i).isRemoved()) particles.remove(i);
            else particles.get(i).update();
        }
    } // Updates our level (for bots, AI, enemies, entities that move etc..)

    /**
     * Default method to render a level and make sure that only visible tiles are rendered.
     * (Collect the tiles and display them in correct order and correct position).
     * @param xScroll Offset from centre of the screen (where the player is) to the left of the screen (in pixels).
     * @param yScroll Offset from centre of the screen (where the player is) to the top of the screen (in pixels).
     * @param display Object of the main display/screen class. (to use render method in specific tile class).
     */
    public void render(int xScroll, int yScroll, Display display) {
        display.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4; // ( xSc / 2^4 ) jumping from pixel precision to tile precision
        int x1 = (xScroll + display.width + 16) >> 4; // rightmost display side (vertical tiles)
        int y0 = yScroll >> 4; // top side of display (horizontal tiles)
        int y1 = (yScroll + display.height + 16) >> 4; // bottom. Adding 16 to avoid black borders

        for (int y = y0; y < y1; y++) { // make sure that only tiles that are visible, get rendered
            for(int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, display); // render tile at x, y pos (in tile precision)
            }
        }

        for (Entity e : entities) {
            e.render(display);
        }

        for (Projectile p : projectiles) {
            p.render(display);
        }

        for (Particle p : particles) {
            p.render(display);
        }
    }

    /**
     * Method to give available tiles an index in array, and return them if called.
     * @param x Position of tile index on X-Axis (in tile precision)
     * @param y Position of tile index on Y-Axis (in tile precision)
     * @return The tile at the index defined by it's x and y position (in tile precision)
     */
    public Tile getTile(int x, int y) {
        // Grass = 0xff00ff00
        // GrassHigh = 0xff003300
        // Bush = 0xff009900
        // FlowerPurple = 0xff9900ff
        // FlowerYellow = 0xffffff00
        // Rock = 0xff999966
        // Wall = 0xff000000
        // Plate1 = 0xff00ccff
        // Plate2 = 0xff0099ff
        // Plate3 = 0xff0066ff

        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.nullTile;
        if (tiles[x + y * width] == 0xff00ff00) return Tile.mainGrass;
        if (tiles[x + y * width] == 0xff003300) return Tile.mainGrassHigh;
        if (tiles[x + y * width] == 0xff9900ff) return Tile.mainFlowerPurple;
        if (tiles[x + y * width] == 0xffffff00) return Tile.mainFlowerYellow;
        if (tiles[x + y * width] == 0xff999966) return Tile.mainRock;
        if (tiles[x + y * width] == 0xff009900) return Tile.mainBush;
        if (tiles[x + y * width] == 0xff000000) return Tile.mainWall;
        if (tiles[x + y * width] == 0xff00ccff) return Tile.mainPlate1;
        if (tiles[x + y * width] == 0xff0099ff) return Tile.mainPlate2;
        if (tiles[x + y * width] == 0xff0066ff) return Tile.mainPlate3;
        return Tile.nullTile;
    }

    /**
     * Method to detect collision between entities and tiles.
     * @param x X position of entity.
     * @param y Y position of entity.
     * @param xA Location after following update on xAxis.
     * @param yA Location after following update on yAxis.
     * @param xS Collision box width.
     * @param yS Collision box height.
     * @param xO Offset of collision box on xAxis.
     * @param yO Offset of collision box on yAxis.
     * @return True if the next tile is solid.
     */
    public boolean tileCollision(double x, double y, double xA, double yA, int xS, int yS, int xO, int yO) {
        boolean colliding = false;

        // Check for all four corners of a tile.
        for(int corner = 0; corner < 4; corner++) {
            // Corner % 2  = (left corners)even&zero: 0 or  (right corners)odd: 1
            // Corner / 2 =  (top corners)zero&one: 0 or (bottom corners)two&three: 1
            // xc = ((pos + 1px in moving direction) + left/right corners * collision box width +/- offset) / tile size
            // Dividing by 16 to get into tile precision.
            int xc = ((int)(x + xA) + corner % 2 * xS - xO) / 16;
            int yc = ((int)(y + yA) + corner / 2 * yS - yO) / 16;

            if (getTile(xc, yc).solid()) colliding = true;
        }

        return colliding;
    }

    /**
     * Getter for projectiles list.
     * @return ArrayList of projectiles.
     */
    public ArrayList<Projectile> getProjectiles() { return projectiles; }

    /**
     * Method to add entities to ArrayList of specific entities.
     * @param e Entity to be added to the list.
     */
    public void addEntity(Entity e) {
        e.initLevel(this); // Initialises level in Entity class.
        if (e instanceof Projectile) {
            projectiles.add((Projectile) e);
        } else if (e instanceof Particle) {
            particles.add((Particle) e);
        } else {
            entities.add(e);
        }
    }
}
