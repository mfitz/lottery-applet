/**
 *    Copyright 2013 Michael Fitzmaurice
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.michaelfitzmaurice.lottery;

import java.awt.Graphics;
import java.awt.Image;

/**
 * @author Michael Fitzmaurice, September 2001
 */
public class LotteryBall implements Runnable {
    
    // the background on which this ball will be drawn
    private LotteryCanvas canvas;
    
    // the on-screen representation of this ball
    private Image image;
    
    // the number this ball represents
    private int value;
    
    // the position on screen where this ball ends up (x remains the same
    // throughout)
    private int finalYPosition;
    
    // rate at which this ball travels between start & end points
    private int dropSpeed;
    
    // the current position of this ball
    private int xPosition, yPosition;
    
    // time between updates in run()
    private static final int SLEEP_TIME = 10;
    
    private Thread thread;

    public LotteryBall(Image img, 
                        int value, 
                        int xPos, 
                        int yPos, 
                        int endY,
                        int speed, 
                        LotteryCanvas canvas) {
        this.image = img;
        this.value = value;
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.finalYPosition = endY;
        this.dropSpeed = speed;
        this.canvas = canvas;
    }

    public void drop() {
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        try {
            // to ensure the balls fall in the right order,
            // i.e. leftmost, 2nd leftmost, 3rd leftmost, etc., with
            // an appropriate pause before each one begins its journey
            Thread.sleep(8 * xPosition);
        } catch (InterruptedException ie) {
            // do nothing
        }

        while (yPosition < finalYPosition) {
            yPosition += dropSpeed;
            canvas.redrawScene();

            try {
                // give other threads (balls) a chance to update
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException ie) {
                // do nothing
            }
        }
    }

    public void drawSelf(Graphics g) {
        g.drawImage(image, xPosition, yPosition, canvas);
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        StringBuffer sBuf = new StringBuffer();

        sBuf.append(super.toString());
        sBuf.append("\nValue:\t\t " + value);
        sBuf.append("\nSpeed:\t\t " + dropSpeed);
        sBuf.append("\nSleep:\t\t " + SLEEP_TIME);
        sBuf.append("\nDestination:\t " + xPosition + ", " + finalYPosition);

        return sBuf.toString();
    }

    /**
     * Compares this LotteryBall for equality with another Java Object. The 2
     * objects are considered equal only if they are both instances of
     * LotteryBall representing the same integral values, i.e. the result
     * returned by calling <code>getValue()</code> on both LotteryBalls is the
     * same. No comparison of the images used by the 2 LotteryBalls is made,
     * hence 2 LotteryBalls can be considered equal by this method even if they
     * have different on-screen appearances.
     * 
     * @param obj the reference object with which to compare
     * 
     * @return true if this object is the same as <code>obj</code>,
     *         otherwise false.
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (this.getClass() == obj.getClass()) {
            LotteryBall other = (LotteryBall) obj;

            if (this.getValue() == other.getValue()) {
                return true;
            }
        }

        return false;
    }
}