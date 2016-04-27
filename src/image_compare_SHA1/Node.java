package image_compare_SHA1;

public class Node {
    int blocknum;  //the block number
    //long hashvalue;   //the hash value of this block
    String hashvalue;
    Node next;
    
    public void setBlocknum(int bn){
 	        this.blocknum = bn;
    }
    public void setHashvalue(String hv){
 	        this.hashvalue = hv;
    }
    public void setNext(Node  nt){
 	        this.next = nt;
    }
    public int getBlocknum(){
 	         return this.blocknum;
    }
    public String getHashvalue(){
 	        return this.hashvalue;
    }
    public Node getNext(){
 	        return this.next;
    }
}
