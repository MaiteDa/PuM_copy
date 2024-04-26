package Übung7.Snake;

import javax.swing.*;

import java.awt.*;

public class UI extends JFrame{
    private static final Color RED        = new Color(220, 20, 50);
    private static final Color BLACK      = new Color(10, 10, 10);
    private static final Color WHITE      = new Color(220, 220, 220);
    private static final Color BACKGROUND = new Color(22,22,22);
    private Gameplay model;

    /*
     * The UI is made with the Java Swing library.
     * This class extends the JFrame-class and inherits most of its features.
     * It abstracts the work needed for your OS to display a window.
     */
    public UI(){
        // sets the screen resolution at start, feel free to adjust
        int width  = 1000, height = 1000;
        // Define the look and behavior of the window
        setBackground(BACKGROUND);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Snake");
        setResizable(true);
        setSize(width, height);
        setLocationRelativeTo(null);

        // Define the inner Panel which will render the game itself
        JPanel panel = new JPanel(){
            /*
             * This method is automatically called, once the repaint() method is called
             * We define a square area inside the window, which we divide into a grid.
             * Each grid cell corresponds to a cell in our model.
             */
            protected void paintComponent(Graphics g){
                if(model == null) return;

                /*
                 * Find the bounds of the area, on which we want to render the game
                 */

                double fieldmargin = .95;
                int fieldWidth  = (int) (getWidth()  * fieldmargin);
                int fieldHeight = (int) (getHeight() * fieldmargin);

                int size = model.getFieldSize();
                int fieldSize = Math.min(fieldWidth,fieldHeight);
                int cellSize = fieldSize / size;
                // center the field
                int fieldX = (getWidth()  - fieldSize)/2;
                int fieldY = (getHeight() - fieldSize)/2;

                /*
                 * Render the field cell-by-cell
                 * Each cell is drawn as a filled rectangle of a given color
                 * You can change the colors to your liking
                 */

                Cell[][] field = model.getField();

                for (int row = 0; row < size; row++){
                    for (int col = 0; col < size; col++) {
                        // set the color of the brush
                        Color c = switch(field[row][col]){
                            case APPLE -> RED;
                            case SNAKE -> WHITE;
                            case EMPTY -> BLACK;
                        };
                        g.setColor(c);
                        // Draw the cell
                        g.fillRect(row*cellSize + fieldX, col*cellSize + fieldY, cellSize, cellSize);
                    }
                }
            }
        };

        // add the panel to this window
        panel.setSize(width, height);
        add(panel);

        // become visible and request mouse and key focus
        setVisible(true);
        requestFocus();
    }

    public void setModel(Gameplay model) { this.model = model; }

}

