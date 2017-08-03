import java.util.*;
import java.io.*;

public class CountPixels {
	
	public ArrayList<ArrayList<Pixel>> frame;
	public int frameSize;
	boolean[][] checkList;
	ArrayList<Blob> blobs;
	int count = 0;
	
	public void read() {
	// reads input from test files and initializes variables
		try{
			String test = "test2.txt";
			File file = new File(test);
			frame = new ArrayList<ArrayList<Pixel>>();
			
			Scanner sc = new Scanner(file);
			frameSize = Integer.parseInt(sc.nextLine());
			
			blobs = new ArrayList<Blob>();
			checkList = new boolean[frameSize][frameSize];
			for(int yPos = 0; yPos < frameSize; yPos++) {
				for(int xPos = 0; xPos < frameSize; xPos++) {
					checkList[yPos][xPos] = false;
				}
			}
			
			int row = 0;
			while(sc.hasNext()){
				String[] temp = sc.nextLine().split(",");
				ArrayList<Pixel> rowData = new ArrayList<Pixel>();
				for(int i=0; i<temp.length; i++) {
					rowData.add(new Pixel(row, i, Integer.parseInt(temp[i])));
				}
				frame.add(rowData);
				row++;
			}
			
			System.out.println(frame);
			System.out.println("Read Complete!");
			sc.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void blobify() {
	// iterate frame to perform addToBlob function for making blobs of the frame
		int count1 = 0;
		int count2 = 0; 
		for(int yPos = 0; yPos < frameSize; yPos++) {
			for(int xPos = 0; xPos < frameSize; xPos++) {
				if(!checkList[yPos][xPos]) {
					blobs.add(addToBlob(yPos, xPos, new Blob()));
					count1++;
				}
				count2++;
			}
		}
		System.out.println("Count1 = " + count1);
		System.out.println("Count2 = " + count2);
	}
	
	public Blob addToBlob(int yPos, int xPos, Blob b) {
	// groups adjacent pixels with the same value to form a blob
		if(yPos >= 0 && yPos < frameSize && xPos >= 0 && xPos < frameSize){
			if(!checkList[yPos][xPos]) {
				Pixel p = frame.get(yPos).get(xPos);
				System.out.println(p);
				count++;
				boolean canAdd = b.add(p);
				if(canAdd) {
					checkList[yPos][xPos] = true;
					addToBlob(yPos + 1, xPos, b);
					addToBlob(yPos - 1, xPos, b);
					addToBlob(yPos + 1, xPos + 1, b);
					addToBlob(yPos - 1, xPos - 1, b);
					addToBlob(yPos, xPos + 1, b);
					addToBlob(yPos, xPos - 1, b);
					addToBlob(yPos - 1, xPos + 1, b);
					addToBlob(yPos + 1, xPos - 1, b);

				}
			}
		}
		return b;
	}
		
	public void printBlobs() {
		for(Blob b: blobs) {
			System.out.println(b);
		}
	}
	
	public void run() {
		read();
		blobify();
		printBlobs();
		System.out.println("Count = " + count);
		System.out.println("End Here");
	}
	
	public static void main(String[] args) {
		CountPixels cp = new CountPixels();
		System.out.println("Test Count Pixels");
		cp.run();
	}
	
}
