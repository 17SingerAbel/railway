package railway;

/**
 * The (x,y) location on the Track.
 */
public class GridLoc {

  /**
   * The row of the location.
   */
  private int row;
  /**
   * The col of the location.
   */
  private int col;

  /**
   * The location in Grid.
   */
  public GridLoc(int r, int c) {
    row = r;
    col = c;
  }

  /**
   * Get the row of the location.
   *
   * @return row.
   */
  int getRow() {
    return row;
  }

  /**
   * Get the column of the location
   *
   * @return column.
   */
  int getCol() {
    return col;
  }

  /**
   * Add row by 1.
   */
  void addRow() {
    row++;
  }

  /**
   * Add column by 1.
   */
  void addCol() {
    col++;
  }

  /**
   * Minus row by 1.
   */
  void delRow() {
    row--;
  }

  /**
   * Minus column by 1.
   */
  void delCol() {
    col--;
  }

  @Override
  public String toString() {
    return (row + " " + col);
  }

}

