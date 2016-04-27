package image_compare_SHA1;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ImageComparerSHA1 {

	public static void main(String[] args) throws RowsExceededException, BiffException, WriteException, NoSuchAlgorithmException, IOException {
		// TODO Auto-generated method stub
		//return help information
		if(args[0].equals("--help"))
		{
			System.out.println("This program is to compare two image files with SHA1.");
			System.out.println("Parameter list:");
			System.out.println("	-the image name of migrated VM");
			System.out.println("	-the image number in the target repository");
			System.out.println("	-the image names in the target repository");
			System.out.println("	-block size. For example, 4 is 4KB");
			return;
		}
		
		System.out.println("***********************Input information*************************");
		Constant.migrate_image=args[0];
		System.out.println("Migrated image: "+Constant.migrate_image);
		
		Constant.imagenum = Integer.parseInt(args[1]);
		System.out.println("Repository: "+Constant.imagenum+" images.");
		
		Constant.repository_image =  new String[Constant.imagenum];
		Constant.totalblocks_r = new int[Constant.imagenum];
		Constant.zeroblocks_r = new int[Constant.imagenum];
		System.out.print("Repository images: ");
		for(int i=0; i<Constant.imagenum; i++)
		{
			Constant.repository_image[i] = args[i+2];
			System.out.print(Constant.repository_image[i]+" ");
		}
		System.out.println();
				
		Constant.size = Integer.parseInt(args[Constant.imagenum+2]);
		System.out.println("Blocksize: "+Constant.size+"K");
		Constant.blocksize=Constant.size*1024;
		
		
		//System.out.println("The start time is: "+starttime);
		CompareHash comparer = new CompareHash();
		//CompareHashWithJava comparer = new CompareHashWithJava();
		comparer.compareHash();
		
		//PrintResult printer = new PrintResult();
		//printer.printNodelistinfo();
	}

}
