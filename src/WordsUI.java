import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;

/**
 * A display to present a running Words program, along with UI elements to allow the user to customize the display (e.g., pan, zoom). 
 */
public class WordsUI {
	JFrame window;						// The entire window containing the UI
	JPanel panel;						// The entire content of the window
	JPanel grid;						// The portion of the content showing the grid of cells
	JPanel buttons;						// The portion of the content showing the buttons
	
	int windowWidth, windowHeight;		// Dimensions of the window in pixels
	int boardSize; 						// Dimensions (square) of the board in pixels
	int numCells;						// Number of rows (columns) in the board (must be odd)
	static final int minCells = 11;		// Must be odd so that there is a cell in the center
	static final int maxCells = 31;		// Must be odd so that there is a cell in the center
	static final int zoomStep = 2;		// Must be even so that there is a cell in the center
	static final int moveStep = 2;		
	int xCenterCell, yCenterCell;		// The board coordinates of the cell depicted in the center
	
	static final int topPadding = 20;	// Padding at the top of the window before the board in pixels
	
	private class Grid extends JPanel {
		int cellSize;					// Dimensions of a cell in pixels
		
		/**
		 * Draws a given cell from the grid at given pixel coordinates.
		 * 
		 * @param g2 The Graphics2D context in which to draw
		 * @param xCenter The x pixel position where the cell should be centered
		 * @param yCenter The y pixel position where the cell should be centered
		 * @param xCell The x coordinate of the cell (i.e., column) in the grid
		 * @param yCell The y coordinate of the cell (i.e., row) in the grid
		 */
		private void drawCell(Graphics2D g2, int xCenter, int yCenter, int xCell, int yCell) {
			g2.setPaint(new Color(128, 128, 128));
			g2.setStroke(new BasicStroke());
			
			g2.drawRect(xCenter - cellSize/2, yCenter - cellSize/2, cellSize, cellSize);
			
			// TODO: Look up the information for this cell to render its contents
			// For now, just render one cell differently so we can see it
			if (xCell == 0 && yCell == 0)
				g2.fillRect(xCenter - cellSize/2, yCenter - cellSize/2, cellSize, cellSize);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g;
			
			// Background region
			int x1, y1, x2, y2;			// Boundaries of the board in pixels
			int cx, cy;					// Center of the board in pixel
			
			x1 = (windowWidth - boardSize)/2;
			y1 = topPadding;
			x2 = x1 + boardSize;
			y2 = y1 + boardSize;
			cx = (x1 + x2)/2;
			cy = (y1 + y2)/2;
			cellSize = (x2 - x1) / numCells;
			
			g2.setPaint(new Color(255, 255, 255));
			g2.fillRect(x1, y1, x2-x1, y2-y1);
			
			// Draw each cell
			for (int i = -(numCells-1)/2; i <= (numCells-1)/2; i++)
				for (int j = -(numCells-1)/2; j <= (numCells-1)/2; j++)
					drawCell(g2, cx + i*cellSize, cy + j*cellSize, xCenterCell + i, yCenterCell + j);
		}
	}
	
	private class Buttons extends JPanel {
		Button up, down, left, right, zoomIn, zoomOut;
		Handler handler;
		
		private class Handler implements ActionListener {
		   public void actionPerformed(ActionEvent e) {
			   if (e.getSource() == up) {
				   yCenterCell += moveStep;
			   } else if (e.getSource() == down) {
				   yCenterCell -= moveStep;
			   } else if (e.getSource() == left) {
				   xCenterCell += moveStep;
			   } else if (e.getSource() == right) {
				   xCenterCell -= moveStep;
			   } else if (e.getSource() == zoomIn) {
				   numCells -= zoomStep;
				   numCells = numCells < minCells ? minCells : numCells;
			   } else if (e.getSource() == zoomOut) {
				   numCells += zoomStep;
				   numCells = numCells > maxCells ? maxCells : numCells;
			   }
			   
			   grid.repaint();
		   }
		}
		
		Buttons() {
			handler = new Handler();
			
			up = new Button("Up");
			this.add(up);
			up.addActionListener(handler);
			
			down = new Button("Down");
			this.add(down);
			down.addActionListener(handler);
			
			left = new Button("Left");
			this.add(left);
			left.addActionListener(handler);
			
			right = new Button("Right");
			this.add(right);
			right.addActionListener(handler);
			
			zoomIn = new Button("Zoom In");
			this.add(zoomIn);
			zoomIn.addActionListener(handler);
			
			zoomOut = new Button("Zoom Out");
			this.add(zoomOut);
			zoomOut.addActionListener(handler);
		}
	}
	
	WordsUI() {
		window = new JFrame();
		panel = new JPanel();
		grid = new Grid();
		buttons = new Buttons();
		
		windowWidth = 750;		// Default windowWidth
		windowHeight = 750;		// Default windowHeight
		boardSize = 650;		// Default boardSize
		numCells = 20;			// Default numCells
		xCenterCell = 0;
		yCenterCell = 0;
		
		panel.setLayout(new BorderLayout());
		panel.add(grid, BorderLayout.CENTER);
		panel.add(buttons, BorderLayout.SOUTH);
		
		window.setContentPane(panel);
		window.setSize(windowWidth,windowHeight);
		window.setLocation(500,100);
		window.setVisible(true);
		window.setResizable(false);
	}
}
