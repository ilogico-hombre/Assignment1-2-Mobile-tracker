/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bhavesh
 */
class Node
{
    protected Object data;
    protected Node link;
 
    /*  Constructor  */
    public Node()
    {
        link = null;
        data = 0;
    }    
    /*  Constructor  */
    public Node(Object d,Node n)
    {
        data = d;
        link = n;
    }    
    /*  Function to set link to next Node  */
    public void setLink(Node n)
    {
        link = n;
    }    
    /*  Function to set data to current Node  */
    public void setData(Object d)
    {
        data = d;
    }    
    /*  Function to get link to next node  */
    public Node getLink()
    {
        return link;
    }    
    /*  Function to get data from current Node  */
    public Object getData()
    {
        return data;
    }
}
 
class Linklist
{
    protected Node start;
    protected Node end ;
    public int size ;
 
    /*  Constructor  */
    public Linklist()
    {
        start = null;
        end = null;
        size = 0;
    }
    /*  Function to check if list is empty  */
    public boolean isEmpty()
    {
        return start == null;
    }
    /*  Function to get size of list  */
    public int size()
    {
        return size;
    }    
    /*  Function to insert an element at end  */
    public boolean add(Object val)
    {
        Node nptr = new Node(val,null);    
        size++ ;    
        if(start == null) 
        {
            start = nptr;
            end = start;
        }
        else 
        {
            end.setLink(nptr);
            end = nptr;
        }
        return true;
    }
    /*  Function to insert an element at position  */
    public void add(int pos, Object val )
    {
        Node nptr = new Node(val, null);                
        Node ptr = start;
        pos = pos - 1 ;
        for (int i = 1; i < size; i++) 
        {
            if (i == pos) 
            {
                Node tmp = ptr.getLink() ;
                ptr.setLink(nptr);
                nptr.setLink(tmp);
                break;
            }
            ptr = ptr.getLink();
        }
        size++ ;
    }
    public boolean remove(Object val) {
    	 Node ptr = start;
    	 int test=0;
    	 if(ptr.getData()==val) {
    		 start=start.getLink();
    		 test=1;}
    	 else {
    	 while(ptr.getLink()!=null) {
    		 Node temp=ptr;
    		 temp=temp.getLink();
    		 if(temp.getData()==val) {
    			 ptr.setLink(temp.getLink());
    			 test=1;
    			 break;
    		 }
    		 ptr=ptr.getLink();
    	  }
    	 }
    	 if(test==1) {
    		 size--;
    		 return true;
    	 }
    	 else return false;
    }
    public boolean contains(Object val) {
    	Node ptr=start;
    	int test=0;
    	while(ptr!=null) {
    		if(ptr.getData()==val) {
    			test=1;
    			break;}
    		ptr=ptr.getLink();
    	}
    	if(test==1)
    		return true;
    	else return false;
    }
    public Object get(int i) {
    	Node ptr= start;
    	int count;
    	if(i<0||i>=size)
    		return null;
    	for(count=0;count<i && ptr.getLink()!=null;count++) {
    		ptr=ptr.getLink();
    	}
        if(ptr==null) return null;
    	return ptr.getData();
    }
}
