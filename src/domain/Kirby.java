/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author jose0
 */
public class Kirby extends Character{
    private int x;
    private int count;
    private ImageIO imageIO;
    ArrayList<Image> sprite;
    private int sleepTime;
    private String name;

    public Kirby(String name, int x, int y, int imgNum, int sleep) throws FileNotFoundException, IOException {
        super(x, y, imgNum);
        this.x = 50;
        this.count = 10;
        this.sleepTime = sleep;
        this.name = name;
        setSprite();
    }

    public void setSprite() throws FileNotFoundException, IOException {
        sprite = super.getSprite();
        for (int i = 0; i <= 9; i++) {
            sprite.add(imageIO.read(new FileInputStream("src/assets/Running" + i + ".png")));
        }
        for (int i = 0; i <= 9; i++) {
            sprite.add(imageIO.read(new FileInputStream("src/assets/ReverseRunning" + i + ".png")));

        }
        super.setSprite(sprite);
    }

    @Override
    public void run() {
        try {
            
            ArrayList<Image> sprite = super.getSprite();
            super.setImage(sprite.get(0));
            boolean flag = true;
            while (flag) {
                Thread.sleep(800);
                try {
                    move(73, 1);
                    //move(60, 2);
                    flag = false;
                    
                } catch (InterruptedException ex) {
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Kirby.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void move(int repeat, int dir) throws InterruptedException {
        int countSprite = 0;
        int countSpriteR = 10;

        if (dir == 1) {
            for (int i = 0; i <= repeat; i++) {
                Thread.sleep(this.sleepTime);
                super.setX(x);
                super.setImage(sprite.get(countSprite));
                x += 10;
                if (countSprite == 9) {
                    countSprite = 0;
                }else{
                countSprite++;
                }
            }
        }

        if (dir == 2) {
            for (int i = 0; i <= repeat; i++) {
                Thread.sleep(70);
                super.setX(x);
                super.setImage(sprite.get(countSpriteR));
                x -= 10;
                if (countSpriteR == 19) {
                    countSpriteR = 10;
                } else {
                    countSpriteR++;
                }
            }
        }
    }
}
