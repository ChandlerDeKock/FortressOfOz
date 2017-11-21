
public class BFSnode {
	
	private boolean mark;
	Location location = null;
	BFSnode prev = null;
	
	public BFSnode ( Location location){
		
		this.location = location;
		
		
	}
	public Location getLocation(){
		
		return location;
	}
	public BFSnode getPrev(){
		
		return prev;
	}
	public void setPrev(BFSnode prev){
		
		this.prev = prev;
	}
	public void setMarked(boolean marked){
		
		mark = marked;
	}
	public boolean getMarked(){
		
		return mark;
	}
}
