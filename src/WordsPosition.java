
public class WordsPosition {
	public int x;
	public int y;
	
	public WordsPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public WordsPosition(double x, double y) {
		this.x = (int) x;
		this.y = (int) y;
	}
}
