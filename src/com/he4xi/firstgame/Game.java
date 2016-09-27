package com.he4xi.firstgame;

import com.he4xi.firstgame.graphics.Display;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by rando on 26.09.16.
 * blablabla
 */

/*
* Canvas - blank rectangle on screen that we can manipulate and draw on
* Extending basically makes Game a subclass for Canvas class.
* This way we can inherit almost all things from Canvas class.
*/
public class Game extends Canvas implements Runnable {

    public static int width = 300;

    // adjusts the heigth based on width and aspect ratio
    public static int heigth = width / 16 * 9;

    // Scales the resolution up, uses less resources, has this pixelated feel
    public static int scale = 3;

    /*
    * Basically creating a sub process, to do multiple things simultaneously
    * One thread already runs the program, the following
    * gameThread is a thread for the game.
    * Creating a new thread object so i can manipulate with it.
    */
    private Thread gameThread;
    private JFrame frame;
    private boolean running = false; // indicator for gameloop

    private Display display;

    /*
    * To handle all the data of each pixel on screen
    * BufferedImage class can help us.
    * (We are dealing with 300 * 162 = 48600 pixels here)
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
    * then everything in constructor will be ran
    *
    * If we first run our game, everything in there will happen
    */
    public Game() {
        Dimension resolution = new Dimension(width * scale, heigth * scale);
        setPreferredSize(resolution); // Method of class Canvas

        display = new Display(width, heigth);

        frame = new JFrame(); // creates a new instance of JFrame
    }

    // Synchronized - to prevent thread interferences and
    // memory consistency errors. It ensures there are no overlaps.
    public synchronized void start() {
        running = true;

        // Implementing Runnable allows us to use "this"
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

    public void run() { // for runnable
        // Game loop, to keep everything running
        while (running) {
            update(); // 60 times per second to ensure consistency
            render(); // Rendering is unlimited
        }
    }

    public void update() {
        // TODO
    }

    public void render() {
        // Object for buffer strategy ( It comes with Canvas )
        BufferStrategy bfS = getBufferStrategy();
        if (bfS == null) {
            createBufferStrategy(3); // Triple buffering (Two back buffers)
            return;
        }

        display.render();

        // Sets every pixel of pixel[] array equal to display.pixel[] array
        // This way we can manipulate the pixels in Display class.
        for (int i = 0; i < pixels.length; i++)
            pixels[i] = display.pixels[i];

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
        game.frame.setTitle("First Game");
        game.frame.add(game); // adds component (subclass of canvas: Game) to our frame
        game.frame.pack(); // Makes frame size same as of our component (Game class)
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Else the program would keep running on close.
        game.frame.setLocationRelativeTo(null); // Runs the window @ centre of the screen
        game.frame.setVisible(true);

        game.start();
    }


}
