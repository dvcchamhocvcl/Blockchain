package Version1;
import Version1.Exceptions.*;
import java.security.*;
public class Wallet {
    private double balance;
    private PublicKey publicKey;
    private PrivateKey privateKey;
    
    public Wallet() {
        // this.publicKey = publicKey;
        // this.privateKey = privateKey;
    }
    public byte[] signatureGenerate(String message, PrivateKey key) 
    throws InvalidKeyException,NoSuchAlgorithmException,SignatureException {
        Signature signature = Signature.getInstance("DSA"); 
        signature.initSign(key);
        byte[] bytemessage = message.getBytes();
        signature.update(bytemessage); 
        return signature.sign(); 
    }

    public KeyPair generatKeyPair()throws NoSuchAlgorithmException{
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, secureRandom);
        KeyPair pair = generator.generateKeyPair();
        return pair;
    }

    public void setKeypair(){
        try {
            KeyPair kp = generatKeyPair();
            this.privateKey = kp.getPrivate();
            this.publicKey = kp.getPublic();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Failed to generate keypairs"+e.getMessage());
        }
    }

    public void sendMoney(double amount, PublicKey payeeKey){
        try {
            if(this.balance < amount){
                throw new InsufficientBalanceException("not enough balance");
            }

            Chain chain = Chain.getInstance();
            Transaction transaction = new Transaction(amount, this.publicKey, payeeKey);
            Block block = new Block(chain.getLastBlockHash(), transaction);
            
            String transactionMessage = transaction.getMessage();
            byte[] signature = null;

            try {
                signature = signatureGenerate(transactionMessage, this.privateKey);
                try {
                    chain.addBlock(block, transactionMessage, signature, payeeKey);
                    balanceChange(0-transaction.getAmount());
                } catch (Exception e) {
                    System.err.println("Failed to add new block| Message: "+e.getMessage());
                }
            } catch (InvalidKeyException        e) {
                System.err.println("Error InvalidKeyException: "+e.getMessage());
            }catch (SignatureException          e) {
                System.err.println("Error SignatureException: "+e.getMessage());
            }catch (NoSuchAlgorithmException    e) {
                System.err.println("Error NoSuchAlgorithmException: "+e.getMessage());
            }

        } catch (InsufficientBalanceException e){
            System.err.println("Error insuffcient balance: "+e.getMessage());
        }
    }   

    private void balanceChange(double change){
        balance += change;
    }
// Getters
    public PublicKey getPublicKey(){
        return this.publicKey;
    }
    public double getBalance(){
        return this.balance;
    }

/*********
 * Sandbox Zone
 */

    public PrivateKey getPrivateKey(){
        return this.privateKey;
        
    }

    public void setBalance(float balance){
        this.balance = balance;
    }
}
