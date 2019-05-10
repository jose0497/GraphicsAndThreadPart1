/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Kirby;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author jose0
 */
public class MyPanel extends JPanel implements Runnable {

    private Thread thread;
    private Graphics buff;
    private Image path1;
    private Image path2;
    private Image path3;
    private Image path4;
    private Kirby kirbyCharacter1;
    private Kirby kirbyCharacter2;
    private Kirby kirbyCharacter3;
    private Kirby kirbyCharacter4;
    private Image finish;

    public MyPanel() throws FileNotFoundException, IOException {
        this.thread = new Thread(this);
        this.path1 = ImageIO.read(new FileInputStream("./src/assets/franja4.png"));
        this.path2 = ImageIO.read(new FileInputStream("./src/assets/franja3.png"));
        this.path3 = ImageIO.read(new FileInputStream("./src/assets/franja2.png"));
        this.path4 = ImageIO.read(new FileInputStream("./src/assets/franja1.png"));
        this.finish = ImageIO.read(new FileInputStream("./src/assets/finish.png"));
        this.kirbyCharacter1 = new Kirby("Juan",50, 50, 0, 40);
        this.kirbyCharacter2 = new Kirby("Rex",50, 200, 0, 70);
        this.kirbyCharacter3 = new Kirby("Max",50, 350, 0, 70);
        this.kirbyCharacter4 = new Kirby("Eliot",50, 500, 0, 100);
        this.kirbyCharacter1.start();
        this.kirbyCharacter2.start();
        this.kirbyCharacter3.start();
        this.kirbyCharacter4.start();
        this.thread.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension d = getSize();
        Image image = createImage(d.width, d.height);
        buff = image.getGraphics();
        draw(buff);
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void run() {
        long start;
        long elapsed;
        long wait;
        int fps = 60;
        long time = 2000 / fps;
        while (true) {
            try {

                start = System.nanoTime();
                elapsed = System.nanoTime() - start;
                wait = time - elapsed / 2000000;
                Thread.sleep(wait);
                draw(buff);
                repaint();

            } catch (InterruptedException ex) {
            }
        }
    }

    private void draw(Graphics g) {
        try {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 1000, 900);
            g.drawImage(path1, 0, 50, this);
            g.drawImage(path2, 0, 200, this);
            g.drawImage(path3, 0, 350, this);
            g.drawImage(path4, 0, 500, this);
            g.drawImage(finish, 600, -80, this);
            g.drawImage(kirbyCharacter1.getImage(), kirbyCharacter1.getX(), kirbyCharacter1.getY(), this);
            g.drawImage(kirbyCharacter2.getImage(), kirbyCharacter2.getX(), kirbyCharacter2.getY(), this);
            g.drawImage(kirbyCharacter3.getImage(), kirbyCharacter3.getX(), kirbyCharacter3.getY(), this);
            g.drawImage(kirbyCharacter4.getImage(), kirbyCharacter4.getX(), kirbyCharacter4.getY(), this);
        } catch (NullPointerException npe) {

        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(900, 703);
    }

}
