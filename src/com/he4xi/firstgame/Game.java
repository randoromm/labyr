package com.he4xi.firstgame;

import com.he4xi.firstgame.entity.mob.PlayerMob;
import com.he4xi.firstgame.graphics.Display;
import com.he4xi.firstgame.input.KeyInput;
import com.he4xi.firstgame.level.Level;
import com.he4xi.firstgame.level.MainLevel;
import com.he4xi.firstgame.level.RandomLevel;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Main game class.
 *
 * Created on 26.09.16.
 * @author Rando Rommot
 * @version 0.1
 */

/*
* Canvas - blank rectangle on screen that we can manipulate and draw on
* Extending basically makes Game a subclass for Canvas class. ("is a" relationship)
* This way we can inherit almost all things from Canvas class.
* Runnable - basically a type of class (Runnable is an Interface.
* that can be put into a thread, describing what the thread is supposed to do.
*/
public class Game extends Canvas implements Runnable {

    /** Width of the resolution. */
    public static int width = 300;

    /** Height of the resolution. */
    public static int heigth = width / 16 * 9; // Adjusts the height based on width and aspect ratio.

    /** Scale for resolution */
    public static int scale = 3; // Scales the resolution up, uses less resources, has this pixelated feel.

    /** Name of the JFrame window **/
    public static String windowName = "First Game";

    /*
    * Basically creating a sub process, to do multiple things simultaneously
    * One thread already runs the program, the following
    * gameThread is a thread for the game.
    * Creating a new thread object so i can manipulate with it.
    */
    private Thread gameThread; // Can be used thanks to runnable (run() is a function from Thread.java).
    private JFrame frame;
    private Display display;
    private KeyInput key;
    private Level level; // Please only have 1 level loaded at time :D
    private PlayerMob player;
    private boolean running = false; // indicator for game loop

    /*
    * To handle all the data of each pixel on screen
    * BufferedImage class can help us.
    * (We are dealing with roughly 300 * 168 = 50400 pixels here)
    * The following is object for our final screen (the rendered image).
    */
    private BufferedImage image = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_RGB);

    /*
    * Raster - Rectangular array of pixels that we can write color data to
    * getDataBuffer - converts the Raster to DataBuffer
    * (DataBufferInt) - casts the DataBuffer to DataBuffer integers
    * Basically we are converting our image object into an array of integers
    * which we can modify and thus create an image.
    */
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    /**
     * Constructor for Game class. Object of this class is basically canvas.
     * In here certain variables are set and necessary objects are created.
     */
    public Game() {
        /*
        * Constructor - if there's a instance of Game class
        * then everything in constructor will be ran first.
        */
        Dimension windowSize = new Dimension(width * scale, heigth * scale);
        setPreferredSize(windowSize); // Sets game component to right dimension.

        display = new Display(width, heigth);
        key = new KeyInput();
        frame = new JFrame(); // Creates a new instance of JFrame.
        level = new MainLevel("/level.png");
        player = new PlayerMob(12 * 16, 6 * 16, key);

        addKeyListener(key);
    }

    /**
     * This is starting method for the game.
     * It runs/starts the thread(gameThread) made specifically for the game
     * and sets boolean 'running' to true.
     */
    public synchronized void start() {
        /*
         * Synchronized - to prevent thread interferences and
         * memory consistency errors. It ensures that two threads
         * can't access the method simultaneously and avoids overlapping
         */
        running = true;

        // Implementing Runnable allows us to run a thread.
        // The following runs the run() method.
        gameThread = new Thread(this, "Display");
        gameThread.start(); // Starting the thread
    }

    /**
     * This is stopping method for the game.
     * It joins/kills the game thread and sets boolean 'running' to false.
     */
    public synchronized void stop() {
        running = false;
        try {
            gameThread.join(); // joins threads together/stops them
        } catch (InterruptedException e) {
            // prints the backtrace to the standard error stream
            e.printStackTrace();
        }
    }

    /**
     * This is run() method for gameThread.
     * This is the core method of the whole game. In this method the game loop runs,
     * which deals with updating and rendering. It ensures that updates happen 60 times in a second,
     * while rendering speed is unlimited and dependant on machine.
     * It makes sure that gameplay speed is consistent for every machine,
     * but seems smoother for better machines due to higher frame rate.
     */
    public void run() {
        long Time2 = System.nanoTime(); // Current time the system is at.
        long timer = System.currentTimeMillis(); // For FPS and UPS counter.

        // 1 second is 1 billion nanoseconds.
        // Divide it by how many times in a second you want to update.
        final double tsq = 1000000000.0 / 60.0;
        double deltaTime = 0;
        int frames = 0;
        int updates = 0; // Should be 60 at all times
        requestFocus(); // Method that focuses canvas when thread is run (don't have to click to be able to move)

        // Game loop, to keep everything running
        // NTS: Test the nanoTime thing in separate class, get a deeper understanding!!!
        while (running) {
            long Time1 = System.nanoTime();
            deltaTime += (Time1 - Time2) / tsq; // Change in time divided by our quotient
            Time2 = Time1;
            while (deltaTime >= 1) {
                update(); // 60 times per second to ensure consistency
                updates++; // Count UPS
                deltaTime--;
            }
            render(); // Rendering is unlimited
            frames++; // Count FPS

            if (System.currentTimeMillis() - timer > 1000) { // 1000ms is 1s, this will happen once in second
                timer += 1000;
                System.out.println(updates + " UPS" + ", " + frames + " FPS");
                frame.setTitle(windowName + " | " + updates + " UPS" + ", " + frames + " FPS");
                updates = 0; // reset updates to 0
                frames = 0; // reset updates to 0
            }
        } // End of game loop
        stop();
    }

    /**
     * This is the main update method. It merges together all the specific update methods from other classes.
     */
    public void update() {
        key.update();
        player.update();
    }

    /**
     * This is the main render method. It implements buffering strategy (in this case triple buffering).
     * Graphics and buffers are linked in this method. Before every frame the display/screen gets cleared with
     * display.clear() method. This method then renders a single frame on screen using *.render() methods of different
     * classes and merging them together (so they can be rendered in every frame). After rendering a frame all graphics
     * are disposed because they are not needed anymore.
     * This method is designed to be used in the game loop to render a picture/frame unlimited times in a second.
     */
    public void render() {
        // Object for buffer strategy.
        BufferStrategy bfS = getBufferStrategy();
        if (bfS == null) {
            createBufferStrategy(3); // Triple buffering (Two back buffers).
            return;
        }

        display.clear(); // Order is important, it cleans screen each loop and then renders new image
        int xScroll = player.x -  width / 2; // To make player centre of screen (render stuff around player).
        int yScroll = player.y -  heigth / 2;
        level.render(xScroll, yScroll, display);
        player.render(display);
//        display.render(x, y);

        // Copy all the pixels from display.pixels[] to this.pixels[].
        System.arraycopy(display.pixels, 0, pixels, 0, pixels.length);

        Graphics g = bfS.getDrawGraphics(); // Linking buffers and graphics
//        g.fillRect(0, 0, getWidth(), getHeight()); // getWidth/Height are methods of canvas (can be deleted).
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null); // Draws the pixels to canvas.
        g.setColor(new Color(255, 227, 113)); // Sets the graphical color (applies to following) (can be deleted).
        g.setFont(new Font ("Verdana", 0, 50));
        g.drawString("X: " + player.x + " Y: " + player.y, 450, 400);
        g.dispose(); // After we render every frame, we want to remove the graphics of that frame.

        // Since we cant keep buffers in memory forever, we need to swap buffers
        // We also need to display the buffer before it's freed from memory.
        bfS.show();
    }

    /**
     * Main method of the program where frame is setup and game thread is started through start() method.
     */
    public static void main(String[] args) { // the genesis :). Beginning of all.
        // I make an object that symbolizes our game component/canvas:
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(windowName);
        game.frame.add(game); // adds component Canvas "Game" (Game "is a" Canvas) to our frame
        game.frame.pack(); // Makes frame size same as of our component (SetPreferredSize in constructor)
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Else the program would keep running on close.
        game.frame.setLocationRelativeTo(null); // Runs the window @ centre of the screen
        game.frame.setVisible(true); // Runs the window on top of other windows.

        game.start();
    }
}
