package Version1;
import java.security.*;
import java.util.Date;

public class Transaction {
    private double amount;
    PublicKey Payer, Payee;
    long timestamp;
    public Transaction(double amount,PublicKey Payer, PublicKey Payee){
        Date date = new Date();
        this.timestamp = date.getTime();
        this.amount = amount;
        this.Payer = Payer;
        this.Payee = Payee;
    }

    public String getMessage(){
        String json = "{\"timestamp\": %d \"amount\": %f,  \"Payer\":\"%s\"  ,\"Payee\": \"%s\"}";
        String transaction = String.format(json,this.timestamp, 
                                                this.amount, 
                                                this.Payee.getEncoded().toString(), 
                                                this.Payee.getEncoded().toString());
        return transaction;
    }


    public double getAmount(){
        return this.amount;
    }
    public PublicKey getPayer(){
        return this.Payer;
    }
    public PublicKey getPayee(){
        return this.Payee;
    }
}