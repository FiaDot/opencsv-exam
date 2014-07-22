
public class Item {
	private String key;
	private int val;
	
	// 생성사 없어도 무관	 
	public Item() {
		
	}
	
	// 생성사 없어도 무관	 
	public Item(String key, int val) {
		this.key = key;
		this.val = val;		
	}
	

	public String toString() {
		return key + "=" + String.valueOf(val);		
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}
	
}
