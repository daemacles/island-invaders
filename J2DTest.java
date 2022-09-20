// package com.daemacles;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

class Entity {
  private ImageIcon icon;
  private Rectangle location;
  private boolean active;

  Entity(String icon_path) {
    icon = new ImageIcon(icon_path);
    location = new Rectangle(0, 0, icon.getIconWidth(), icon.getIconHeight());
  }

  void doDrawing(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.drawImage(icon.getImage(), location.x, location.y, location.x + location.width,
        location.y + location.height, 0, 0, location.width, location.height, null);

    if (active) {
      g2d.setPaint(Color.RED);
      g2d.drawRect(location.x, location.y, location.width, location.height);
    }
  }

  void setActive(boolean is_active) {
    active = is_active;
  }

  final Rectangle getLocation() {
    return location;
  }

  void setLocation(int x, int y) {
    location.x = x;
    location.y = y;
  }
}

class Surface extends JPanel implements ActionListener {
  private final int DELAY = 33; // 1000/33 = 30 frames per second
  private Timer timer;

  // private BufferedImage wall;
  // private TexturePaint wall_p;

  private ImageIcon tree;

  private Entity bush;

  private int x = 10;
  private int y = 10;

  public Surface() {
    initTimer();
    loadImages();

    ImgCursor img_cursor = new ImgCursor();
    addMouseListener(img_cursor);
    addMouseMotionListener(img_cursor);
  }

  private void initTimer() {
    timer = new Timer(DELAY, this);
    timer.start();
  }

  public Timer getTimer() {
    return timer;
  }

  private void loadImages() {
    tree = new ImageIcon("sprites/tree.png");
    bush = new Entity("sprites/bush.png");
    // try {
    //  wall = ImageIO.read(new File("sprites/wall.png"));
    //  wall_p = new TexturePaint(wall, new Rectangle(0, 0, wall.getWidth(), wall.getHeight()));
    //} catch(IOException ex) {
    //  Logger.getLogger(Surface.class.getName()).log(
    //      Level.SEVERE, null, ex);
    //}
  }

  private void doDrawing(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();

    // g2d.drawString("Java 2D", 50, 50);
    // g2d.setPaint(Color.blue);

    // int w = getWidth();
    // int h = getHeight();

    // Random r = new Random();

    // for (int i=0; i < 2000; i++) {
    //   int x = Math.abs(r.nextInt()) % w;
    //   int y = Math.abs(r.nextInt()) % h;
    //   g2d.drawLine(x, y, x, y);
    //}

    //                             ,-destination---. ,-source-------.
    g2d.drawImage(tree.getImage(), x, y, x + 30, y + 30, x, y, x + 30, y + 30, this);

    // g2d.setPaint(wall_p);
    // g2d.fillRect(250, 15, 90, 60);

    g2d.dispose();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    doDrawing(g);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    repaint();
  }

  class ImgCursor implements MouseInputListener {
    public void mouseEntered(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {
      x = e.getX();
      y = e.getY();
    }
  }
}

public class J2DTest extends JFrame {
  public J2DTest() {
    initUI();
  }

  private void initUI() {
    final Surface surface = new Surface();
    add(surface);

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        Timer timer = surface.getTimer();
        timer.stop();
      }
    });

    setTitle("Figure Textures");
    setSize(350, 250);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        J2DTest ex = new J2DTest();
        ex.setVisible(true);
      }
    });
  }
}
