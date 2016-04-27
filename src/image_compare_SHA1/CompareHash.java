package image_compare_SHA1;

import jxl.write.*;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import jxl.read.biff.BiffException;

public class CompareHash {
	
	SHA1 hasher = new SHA1();
	byte[] zero = new byte[Constant.blocksize];  //zero block
	
	public void compareHash() throws BiffException, IOException, RowsExceededException, WriteException, NoSuchAlgorithmException{
		
		//initialize zero block
		for(int j=0; j< Constant.blocksize; j++)
			zero[j]=0;
		
		//the start time
		Constant.starttime = System.currentTimeMillis();
		
		//generate hash table for migrated image
		System.out.println("**********************Migrated Image**************************");
		ImageInfo info_m = new ImageInfo();
		generateHash(Constant.migrate_image, Constant.hashtable_m, info_m);
		
		Constant.totalblocks_m = info_m.totalblocks;
		
		int dupblocks_m = Constant.totalblocks_m - Constant.hashtable_m.size();
		System.out.println("Stored blocks: "+Constant.hashtable_m.size());
		System.out.println("Duplicated blocks: "+ dupblocks_m);
		System.out.println("Duplication size: "+ dupblocks_m*Constant.size/1024+"MB");
		System.out.println("Duplication ratio: "+(double)dupblocks_m/Constant.totalblocks_m);
		
    	//generate hash table for repository images
		System.out.println("**********************Repository Images**************************");
    	for(int i=0; i<Constant.imagenum; i++)
    	{
    		System.out.println("-----"+Constant.repository_image[i]+"-----");
    		ImageInfo info_r = new ImageInfo();
    		generateHash(Constant.repository_image[i], Constant.hashtable_r, info_r);
    		Constant.t_blocks += info_r.totalblocks;
    		Constant.z_blocks += info_r.zeroblocks;
    	}
    	
    	Constant.endtimeHash = System.currentTimeMillis();
    	
    	int dupblocks_r = Constant.t_blocks - Constant.hashtable_r.size();
    	System.out.println("******Repository total information******");
    	System.out.println("Total blocks: "+Constant.t_blocks);
    	System.out.println("Zero blocks: "+Constant.z_blocks);
       	System.out.println("Stored blocks: "+Constant.hashtable_r.size());
       	System.out.println("Duplicated blocks: "+ dupblocks_m);
       	System.out.println("Duplication size: "+dupblocks_r*Constant.size/1024+"MB");
    	System.out.println("Duplication ratio: "+(double)dupblocks_r/Constant.t_blocks);
    	
		
		//calculate  similarity
    	//String tempstr = null;
		//LinkedList<Integer> templist = null;
		int similarblocks = 0;
    	for (HashMap.Entry<String, LinkedList<Integer>> entry : Constant.hashtable_m.entrySet()) {
    		if(Constant.hashtable_r.containsKey(entry.getKey()))
    			similarblocks++;
		}
    	
		Constant.endtimeCompare = System.currentTimeMillis();
		
		System.out.println("************************Similarity information********************************");
		int similarblocks_m = similarblocks + Constant.zeroblocks_m;
		int similarblocks_r = similarblocks + Constant.z_blocks;
		
		System.out.println("Similar blocks (without 0): "+ similarblocks);
		System.out.println("-----------------");
		System.out.println("Similar size (migrated): "+ similarblocks_m*Constant.size/1024+"MB.");
		System.out.println("Similarity ratio (migrated): "+ (double)(similarblocks_m)/Constant.totalblocks_m);
		System.out.println("-----------------");
		System.out.println("Similar size (repository): "+ (similarblocks_r)*Constant.size/1024+"MB.");
		System.out.println("Similarity ratio (repository): "+ (double)(similarblocks_r)/Constant.t_blocks);
		System.out.println("******************************************************************************");
		
		//output time information
		Long hashtime = Constant.endtimeHash-Constant.starttime;
		Long comparetime = Constant.endtimeCompare - Constant.endtimeHash;
		System.out.println("Hashing time:"+hashtime);
		System.out.println("Comparing time:"+comparetime);

		return;
	   }
     
	   //generate node list according to hashtable
	    public void  generateHash(String file, HashMap<String, LinkedList<Integer>> ht, ImageInfo iinfo) throws IOException, NoSuchAlgorithmException{
	    	
	    	File file_in = new File(file);
		    Long size = file_in.length();
			InputStream reader = new FileInputStream(file_in);
			
			iinfo.totalblocks = (int)(size/Constant.blocksize);
			if((size%Constant.blocksize) != 0)
				iinfo.totalblocks++;
			System.out.println("Total blocks: "+iinfo.totalblocks);
			
			byte[] bb = new byte[Constant.blocksize];			
			int currentposition = 0;
			int zblocks = 0;
			String tempstr = null;
			//System.out.println("The location is: "+location);
			while(currentposition < iinfo.totalblocks)
			{
				reader.read(bb);

				if(Arrays.equals(bb, zero))
					zblocks++;
				else
				{
					tempstr = new String(bb);
					String digest = hasher.sha1(tempstr);
					if(ht.containsKey(digest))
						ht.get(digest).add(currentposition);
					else
					{
						LinkedList<Integer> newlist = new LinkedList<Integer>();
						newlist.add(currentposition);
						ht.put(digest, newlist);
					}
				}
				currentposition ++;
				//System.out.println(position);
			}
			iinfo.zeroblocks = zblocks;
			System.out.println("Zero blocks: "+ zblocks);
			//System.out.println("----------------------------");

			reader.close();
			return;
	    }
}
