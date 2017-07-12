package railway;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;

/**
 * A Track Panel.
 */
public class TrackPanel extends Panel {
  // ------------------------------------------------------------------
  // The following items are used for double buffering.
  /** The buffer in which to draw the image. */
  //used for double buffering.
  private Image backBuffer;
  /** The graphics context to use when double buffering. */
  private Graphics backG;
  /**
   * The grid of rails.
   */
  private Rail[][] rails;

  /**
   * Add rails to panel.
   */
  void addToPanel(Rail[][] r) {
    rails = r;
    setLayout(new GridLayout(rails.length, rails[0].length, 0, 0));
    for (int row = 0; row < rails.length; row++) {
      for (int col = 0; col < rails[0].length; col++) {
        add("", rails[row][col]);
      }
    }
  }

  /**
   * Paint the display.
   */
  @Override
  public void paint(Graphics graph) {
    update(graph);
  }

  @Override
  public Insets insets() {
    return new Insets(10, 10, 10, 10);
  }


  /**
   * Update the display; tell all my Tracks to update themselves.
   */
  @Override
  public void update(Graphics graph) {
    // Get my width and height.
    int w = getBounds().width;
    int h = getBounds().height;
    // If we don't yet have an Image, create one.
    if (backBuffer == null
        || backBuffer.getWidth(null) != w
        || backBuffer.getHeight(null) != h) {
      backBuffer = createImage(w, h);
      if (backBuffer != null) {
        // If we have a backG, it belonged to an old Image.
        // Get rid of it.
        if (backG != null) {
          backG.dispose();
        }
        backG = backBuffer.getGraphics();
      }
    }
    if (backBuffer != null) {
      // Fill in the Graphics context backG.
      graph.setColor(Color.white);
      graph.fillRect(0, 0, w, h);
      // Now copy the new image to g.
      // g.drawImage(backBuffer, 0, 0, null);
    }
  }
}

