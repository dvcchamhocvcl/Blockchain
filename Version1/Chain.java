package Version1;
import java.security.*;
import java.util.ArrayList;

public class Chain {
    private ArrayList<Block> blockchain; 
    private static final Chain instance = new Chain();

    private Chain(){
        this.blockchain = new ArrayList<Block>();
        Transaction genesisTransaction = new Transaction(0,null,null);
        byte[] genesisHash = null;
        Block genesisBlock = new Block(genesisHash,genesisTransaction);
        blockchain.add(genesisBlock);

    }
    
    public static Chain getInstance(){
        return instance;
    }

    public byte[] getLastBlockHash(){
        byte[] hash = null;
        try {
            hash = blockchain.get(blockchain.size()-1).getHash();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: "+e.getMessage());
        }
        return hash;

    }
    
    private boolean signatureValidate(String message, byte[]signed, PublicKey key)
    
    throws InvalidKeyException,NoSuchAlgorithmException,SignatureException{
        byte[] messageByte = message.getBytes();
        Signature signature = Signature.getInstance("DSA");
        signature.initVerify(key);
        signature.update(messageByte);
        if (signature.verify(signed)) {
            return true;
        } else {
            return false;
        }
    }
    
    public void addBlock(Block Block,String message, byte[]signed, PublicKey key)
        throws SignatureException,InvalidKeyException,NoSuchAlgorithmException{
        boolean isValid = false;
        try {
            isValid = signatureValidate(message, signed, key);
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException e) {
            System.err.println("Error: "+e.getMessage());
        }
        
        if (isValid) {
            blockchain.add(Block);
        } else {
            
        }
    }

}
