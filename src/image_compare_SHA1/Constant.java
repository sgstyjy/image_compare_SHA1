package image_compare_SHA1;

import java.util.HashMap;
import java.util.LinkedList;

public class Constant {
	public static int imagenum = 0;   //the image number in the target repository
	public static String migrate_image="";
	public static String repository_image[];
	public static int totalblocks_m=0;
	public static int zeroblocks_m=0;
	public static int[] totalblocks_r;   //total blocks for each image in the repository
	public static int[] zeroblocks_r;    //zero blocks for each image in the repository
	public static int t_blocks=0;      //total blocks in the repository without deduplication
	public static int z_blocks=0;     //zero blocks in the repository
	
	public static int size = 4;
	public static int blocksize=4*1024;
	public static int PRIME = 10007;      //one big prime number, it is used for deciding the node list length
	public static int similar = 0;  //the similar block number
	
	public static Long starttime;
	public static Long endtimeHash;
	public static Long endtimeCompare;
	
	//public static String hashtable1="";
	//public static String hashtable2="";
	//public static String compareresult="";
	//public static int size = 4;
	//public static int COLUMNS = 10000;
	//public static int HASH_METHOD = 0;
	//public static int totalblocks1=0;
	//public static int totalblocks2=0;
	//public static int[]  nodenumlistbkdr = new int[Constant.PRIME];     //the node number of each position in the list for the BKDR table
	//public static int[]  nodenumlistap = new int[Constant.PRIME];       //the node number of each position in the list for the AP table
	//public static int[]  nodenumlist = new int[Constant.PRIME]; 
	//public static Node[]  hashlist = new Node[Constant.PRIME]; 
	public static HashMap<String, LinkedList<Integer>> hashtable_m=new HashMap<String, LinkedList<Integer>> ();
	public static HashMap<String, LinkedList<Integer>> hashtable_r=new HashMap<String, LinkedList<Integer>> ();
}
