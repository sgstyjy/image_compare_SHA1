package image_compare_SHA1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class PrintResult {
	
	public void printNodelistinfo() throws IOException{
		
		FileOutputStream fs = new FileOutputStream(new File("hashlist.txt"));
		PrintStream writer = new PrintStream(fs);
		//Map m = new Map();
		
		int maxNode = 0;
		String tempstr = null;
		LinkedList<Integer> templist = null;
		int num = 0;
		for (HashMap.Entry<String, LinkedList<Integer>> entry : Constant.hashtable_m.entrySet()) {
			tempstr = entry.getKey();
			templist = entry.getValue();
			num = templist.size();
			if(num>maxNode)
				maxNode=num;
			
			writer.print(tempstr +" ("+num+") ");
			for(int i=0; i<num; i++)
			{
				writer.print(templist.get(i)+" | ");
			}
			writer.println();
		}
		writer.println("The max number of node in each entry is: " +maxNode);
		writer.println("The total entry is: " +Constant.hashtable_m.size());
		
		/*
		for(int i=0; i<Constant.PRIME; i++)
		{
			//print out the total node at this location
			writer.print(i+ " ("+Constant.nodenumlist[i]+")   ");
			
			Node tempNode = hashlinks[i];
			while(tempNode != null)
			{
				//print out the block number of this node
				writer.print(tempNode.hashvalue +" | ");
				tempNode = tempNode.next;
			}
			writer.println();
		}
		*/
		fs.close();
	}

}
