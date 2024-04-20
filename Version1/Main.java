package Version1;
import java.security.*;
public class Main {
    public static void main(String[] args) {
        Wallet w1 = new Wallet();
        Wallet w2 = new Wallet();

        w1.setKeypair();
        w2.setKeypair();
        w1.setBalance(100);
        w2.setBalance(100);

        System.err.println(w1.getPrivateKey().getEncoded());
        System.err.println(w1.getPublicKey().getEncoded());
        System.err.println(w2.getPrivateKey().getEncoded());
        System.err.println(w2.getPrivateKey().getAlgorithm());
        System.err.println(w2.getPublicKey().getEncoded());

        w1.sendMoney(10, w2.getPublicKey());

        System.out.println(w1.getBalance());  
    }
}
