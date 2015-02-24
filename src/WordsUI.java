import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WordsUI {
	JFrame window;
	JPanel panel;
	JPanel grid;
	JPanel buttons;
	
	int windowWidth, windowHeight;		// Dimensions of the window in pixels
	int boardSize; 						// Dimensions (square) of the board in pixels
	int numCells;						// Number of rows (columns) in the board (must be odd)
	static final int minCells = 11;
	static final int maxCells = 31;
	static final int zoomStep = 2;		// Must be even
	int xCenterCell, yCenterCell;		// The board coordinates of the cell depicted in the center
	static final int moveStep = 2;
	
	private class Grid extends JPanel {
		int cellSize;					// Dimensions of a cell in pixels
		
		private void DrawCell(Graphics2D g2, int xCenter, int yCenter, int xCell, int yCell) {
			g2.setPaint(new Color(128, 128, 128));
			g2.setStroke(new BasicStroke());
			
			g2.drawRect(xCenter - cellSize/2, yCenter - cellSize/2, cellSize, cellSize);
			
			if (xCell == 0 && yCell == 0)
				g2.fillRect(xCenter - cellSize/2, yCenter - cellSize/2, cellSize, cellSize);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g;
			
			// Background region
			int x1,y1,x2,y2;			// Boundaries of the board
			int cx,cy;					// Center of the board
			
			x1 = (windowWidth - boardSize)/2;
			y1 = 20;
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
					DrawCell(g2, cx + i*cellSize, cy + j*cellSize, xCenterCell + i, yCenterCell + j);
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
		
		windowWidth = 750;
		windowHeight = 750;
		boardSize = 650;
		numCells = 20;
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
