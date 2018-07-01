package noobChain;

import java.util.Date;

public class Block {

	public String hash;
	public String previousHash;
	private String data; //our data will be a simple message.
	private long timeStamp; //as number of milliseconds since 1/1/1970.
	private int nonce;

	//Block Constructor.
	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}
	/*
	 * Now lets use our applySha256 helper, in a new method in the Block class, 
	 * to calculate the hash. We must calculate the hash from all parts of the block 
	 * we don’t want to be tampered with. 
	 * So for our block we will include the previousHash, the data and timeStamp.
	 */
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) +
				data 
				);
		return calculatedhash;
	}
	/*
	 * The mineBlock() method takes in an int called difficulty, 
	 * this is the number of 0’s they must solve for. 
	 * Low difficulty like 1 or 2 can be solved nearly instantly on most computers, 
	 * i’d suggest something around 4–6 for testing. 
	 * At the time of writing Litecoin’s difficulty is around 442,592.
	 * 
	 */
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
}
