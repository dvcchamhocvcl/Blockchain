package Version1;

import java.util.Date;
import java.security.*;

public class Block {
    // private String hashIndex;
    private byte[] previousHash;
    private long timestamp;
    private Transaction Transaction;

    public Block(byte[] previousHash, Transaction Transaction){
        Date date = new Date();
        this.timestamp = date.getTime();
        this.previousHash = previousHash;
        this.Transaction = Transaction;
    }
    public Transaction getTransaction(){
        return this.Transaction;
    }
    public long getTime(){
        return this.timestamp;
    }
    public byte[] getHash() throws NoSuchAlgorithmException {
        String data = "{%s%d%s}";
        byte[] dataByte = String.format(data, this.previousHash, this.timestamp, this.Transaction).getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(dataByte);   
    }



}
