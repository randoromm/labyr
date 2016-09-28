package com.he4xi.firstgame;

import com.he4xi.firstgame.graphics.Display;
import com.he4xi.firstgame.input.KeyInput;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Main game class
 *
 * Created on 26.09.16.
 * @author Rando Rommot
 * @version 0.1
 */

/*
* Canvas - blank rectangle on screen that we can manipulate and draw on
* Extending basically makes Game a subclass for Canvas class. ("is a" relationship)
* This way we can inherit almost all things from Canvas class.
* Runnable - basically a type of class (Runnable is an Interface)
* that can be put into a thread, describing what the thread is supposed to do.
*/
public class Game extends Canvas implements Runnable {

    public static int width = 300;

    // adjusts the heigth based on width and aspect ratio
    public static int heigth = width / 16 * 9;

    // Scales the resolution up, uses less resources, has this pixelated feel
    public static int scale = 3;

    public static String windowName = "First Game";

    /*
    * Basically creating a sub process, to do multiple things simultaneously
    * One thread already runs the program, the following
    * gameThread is a thread for the game.
    * Creating a new thread object so i can manipulate with it.
    */
    private Thread gameThread;
    private JFrame frame;
    private Display display;
    private KeyInput key;
    private boolean running = false; // indicator for game loop

    /*
    * To handle all the data of each pixel on screen
    * BufferedImage class can help us.
    * (We are dealing with roughly 300 * 168 = 50400 pixels here)
    * The following is object for our final screen (the rendered image)
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

    /*
    * Constructor - if there's a instance of Game class
    * then everything in constructor will be ran first
    *
    * (ex. If an object Game newGame = new Game(); would be created)
    * (In this case, this class only has one instance (initial))
    */
    public Game() {
        Dimension windowSize = new Dimension(width * scale, heigth * scale);
        setPreferredSize(windowSize); // Method of class Canvas

        display = new Display(width, heigth);
        key = new KeyInput();
        frame = new JFrame(); // creates a new instance of JFrame

        addKeyListener(key);
    }

     /*
     * Synchronized - to prevent thread interferences and
     * memory consistency errors. It ensures that two threads
     * can't access the method simultaneously and avoids overlapping
     */
    public synchronized void start() {
        running = true;

        // Implementing Runnable allows us to use "this" and run a thread
        // the following runs the run method.
        gameThread = new Thread(this, "Display");
        gameThread.start(); // Starting the thread
    }

    public synchronized void stop() {
        running = false;
        try {
            gameThread.join(); // joins threads together/stops them
        } catch (InterruptedException e) {
            // prints the backtrace to the standard error stream
            e.printStackTrace();
        }
    }

    public void run() { // for Runnable
        long lastTime = System.nanoTime(); // Current time the system is at
        long timer = System.currentTimeMillis();

        // One second is 1 billion nanoseconds.
        // Divide it by how many times in a second you want to update.
        final double tsq = 1000000000.0 / 60.0;
        double deltaTime = 0;
        int frames = 0;
        int updates = 0; // Should be 60 at all times

        // Game loop, to keep everything running
        // NTS: Test the nanoTime thing in separate class, get a deeper understanding
        while (running) {
            long glTime = System.nanoTime();
            deltaTime += (glTime - lastTime) / tsq; // Change in time divided by our quotient
            lastTime = glTime;
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

    int x = 0, y = 0;
    public void update() {
        key.update();
        if (key.up) y--;
        if (key.down) y++;
        if (key.right) x++;
        if (key.left) x--;
    }

    public void render() {
        // Object for buffer strategy ( It comes with Canvas )
        BufferStrategy bfS = getBufferStrategy();
        if (bfS == null) {
            createBufferStrategy(3); // Triple buffering (Two back buffers)
            return;
        }

        display.clear(); // order is important, it cleans screen each loop and then renders new image
        display.render(x, y);

        // Sets every pixel of pixel[] array equal to display.pixel[] array
        // This way we can manipulate the pixels in Display class.
        System.arraycopy(display.pixels, 0, pixels, 0, pixels.length);

        Graphics g = bfS.getDrawGraphics(); // Linking buffers and graphics
        g.setColor(new Color(255, 227, 113)); // Sets the graphical color (applies to following)
        g.fillRect(0, 0, getWidth(), getHeight()); // getWidth/Height are methods of canvas
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null); // Draws object image on top of the rectangle b4
        g.dispose(); // After we render every frame, we want to remove the graphics of that frame

        // Since we cant keep buffers in memory forever, we need to swap buffers
        // We also need to display the buffer before it's freed from memory.
        bfS.show();
    }

    public static void main(String[] args) { // the genesis :). Beginning of all.
        // I make an object out of our class:
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(Game.windowName);
        game.frame.add(game); // adds component Canvas "Game" (Game "is a" Canvas) to our frame
        game.frame.pack(); // Makes frame size same as of our component (SetPreferredSize in constructor)
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Else the program would keep running on close.
        game.frame.setLocationRelativeTo(null); // Runs the window @ centre of the screen
        game.frame.setVisible(true);

        game.start();
    }


}
