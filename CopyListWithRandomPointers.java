import java.util.HashMap;

//Time Complexity : O(n)
//Space Complexity : O(1)
public class CopyListWithRandomPointers {	
	// Definition for a Node.
	static class Node {
	    int val;
	    Node next;
	    Node random;
	    public Node(int val) {
	        this.val = val;
	        this.next = null;
	        this.random = null;
	    }
	}    
   
	public Node copyRandomList(Node head) {
    	//Mutate linked list with deep copy nodes next to node itself
        Node curr= head;      
        while(curr!=null){
            Node newNode= new Node(curr.val);
            newNode.next= curr.next;
            curr.next= newNode;
            curr= curr.next.next;
        }
        //Attach Random Pointers
        curr= head;
        while(curr!=null){
            if(curr.random!=null) 
                curr.next.random= curr.random.next;
            curr=curr.next.next;
        }
        //Split original list and deep copy
        curr= head;
        Node copyHead=null;
        if(head!=null){
            copyHead=head.next;
            Node copyCurr= copyHead;
            while(curr!=null){
                curr.next= curr.next.next;
                if(copyCurr.next!=null) copyCurr.next= copyCurr.next.next;
                curr= curr.next;
                copyCurr= copyCurr.next;
            }
        }
        return copyHead;       
    }
      
	
	// Driver code to test above
	public static void main (String[] args) {
		CopyListWithRandomPointers ob= new CopyListWithRandomPointers();
		Node node1= new Node(7);
		Node node2= new Node(13);
		Node node3= new Node(11);
		Node node4= new Node(10);
		Node node5= new Node(1);
		
		node1.next= node2;
		node2.next= node3;
		node3.next= node4;
		node4.next= node5;
		
		node2.random= node1;		
		node3.random= node5;
		node4.random= node3;
		node5.random= node1;
		
		Node copy= ob.copyRandomList(node1);
		System.out.print("Deep copy of the given linked list : "+copy.val);			
		while(copy.next!=null) {
			System.out.print("->"+copy.next.val);
			copy=copy.next;
		}
	}
}
