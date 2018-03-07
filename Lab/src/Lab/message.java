package Lab;

import java.io.Serializable;

public class message implements Serializable {
  
	int sq;
    String pl;
    String ck;
   
  
    public message(int sq, String pl, String ck){
        this.sq=sq;
        this.pl=pl;
        this.ck=ck;
    }
        public int sqe(){
            return sq;
        }
        
        public String pld(){
            return pl;
        }
        
        public String chk(){
            return ck;
        }
        
        
        public void setsequence(int a){
            this.sq=a;
        }
        public void setpayload(String b){
            this.pl=b;
        }
        public void setchecksum(String c){
            this.ck=c;
        }
}

