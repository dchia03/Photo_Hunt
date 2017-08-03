import java.util.*;

public class Blob {
	
	public ArrayList<Pixel> blob;
	public int size;
	public int value;
	public String shape; 
	
	public Blob() {
		blob = new ArrayList<Pixel>();
		size = 0;
		value = 0;
		shape = "nothing";
	}
	
	public boolean add(Pixel p) {
		if(size == 0 || value == p.getValue()) {
			blob.add(p);
			value = p.getValue();
			size++;
			return true;
		}
		else 
			return false;
	}
	
	public boolean remove(Pixel p) {
		if(blob.remove(p)) {
			size--;
			return true;
		}
		else {
			return false;
		}
	}
	
	public int getSize() {
		return size;
	}
	
	public int getValue() {
		return value;
	}

	public int[][] toFrame(){
	// turns the blob into the best fit frame
		int frameSize = 0;
		for(Pixel p: blob) {
			frameSize = Math.max(Math.max(frameSize, p.getYpos()), p.getXpos());
		}
		int[][] frame = new int[frameSize][frameSize];
		
		for(int i = 0; i < frameSize; i++) {
			for(int j = 0; j < frameSize; j++) {
				frame[i][j] = 0;
			}
		}
		
		for(Pixel p: blob) {
			frame[p.getYpos()][p.getXpos()] = p.getValue();
		}
						
		return frame;
	}
	
	public void shapeDetect(Blob b) {
	// check if blob is in a predefined shape
		int[] widths = findWidths();
		int[] heights = findHeights();
		
		int numHeights = 0;
		int numWidths = 0;
		
		for(int i = 0; i < size; i++) {
			if(widths[i] > 1) {
				numWidths++;
			}
			if(heights[i] > 1) {
				numHeights++;
			}
		}
		
		
		
	}
	
	public int[] findWidths() {
		int[] widths = new int[size];
		int[][] frame = toFrame();
				
		for(int y = 0; y < size; y++) {
			int x = 0;
			int width = 0;
			while(frame[y][x] > 0) {
				width++;
				x++;
			}
			widths[y] = width;
		}
		
		return widths;
	}
	
	public int[] findHeights() {
		int[] heights = new int[size];
		int[][] frame = toFrame();
		
		for(int x = 0; x < size; x++) {
			int y = 0;
			int height = 0;
			while(frame[y][x] > 0) {
				height++;
				y++;
			}
			heights[y] = height;
		}
		
		return heights;
	}
	
	public String toString() {
		return "{" + shape + ", " +  blob.toString() + "}";
	}
	
}
