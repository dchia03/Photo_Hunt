import java.util.*;

public class Blob {
	
	public ArrayList<Pixel> blob;
	public int size;
	public int value;
	
	public Blob() {
		blob = new ArrayList<Pixel>();
		size = 0;
		value = 0;
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
	
	public String toString() {
		return blob.toString();
	}
	
}
