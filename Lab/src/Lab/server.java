package Lab;
import java.net.*;
import java.util.Arrays;
import java.io.*;
public class server {
    public static void main(String args[]){
       
    	int sequence;
        String payload;
        String checksum;
        
         try{
             ServerSocket s=new ServerSocket(9999);
             Socket ss=s.accept();
             System.out.println("Server has been connected");
             
             
             ObjectInputStream objinput=new ObjectInputStream(ss.getInputStream());
             ObjectOutputStream objoutput=new ObjectOutputStream(ss.getOutputStream());
             message msg=(message)objinput.readObject();
             
             sequence = msg.sqe();
             payload = msg.pld();
             checksum = msg.chk();
           
             //Checksum---------001100001111-------------------------------------------------------------------
             String cs;
             cs = checksum;
             int arr[] = new int[16];
            // cs = in.next();
             
            for(int i=0; i<16; i++)
            {
                arr[i] = cs.charAt(i) - 48;
            }
            
            int result[] = new int[4];
       
            int p = 3;

            for(int j=0; j<4; j++)
            {
                for(int k = p, i=3; k>=(p-3) && i>=0; k--, i--)
                {
                    result[i] = result[i] + arr[k];
                   
                    if(result[i]==2 && i>=1)
                    {
                        result[i] = 0;
                        result[i-1] = result[i-1] + 1;
                    }
                    
                    else if(result[i]==3 && i>=1)
                    {
                        result[i] = 1;
                        result[i-1] = result[i-1] + 1;
                    }
                    
                   if(result[0]==2)
                    {
                        result[0] = 0;
                        result[3] = result[3] + 1;
                        if (result[3]==2) {result[3] = 0; result[2] = result[2] + 1;}
                        if (result[2]==2) {result[2] = 0; result[1] = result[1] + 1;}
                        if (result[1]==2) {result[1] = 0; result[0] = result[0] + 1;}
                    }
                   else if(result[0]==3)
                   {
                       result[0] = 1;
                       result[3] = result[3] + 1;
                       if (result[3]==2) {result[3] = 0; result[2] = result[2] + 1;}
                       if (result[2]==2) {result[2] = 0; result[1] = result[1] + 1;}
                       if (result[1]==2) {result[1] = 0; result[0] = result[0] + 1;}
                   }
                   // System.out.print(result[i]);
                }
                p+=4;
            }
            
            /*System.out.println();
            for(int i=0; i<4; i++)
            {
                System.out.print(result[i]);
            }
            for(int i=0; i<4; i++)
            {
               if(result[i] == 0) result[i] = 1;
               else result[i] = 0;
            }
            /*System.out.println();
            for(int i=0; i<4; i++)
            {
                System.out.print(result[i]);
            }
            }*/
            
            checksum = Arrays.toString(result).replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
            //checksum = payload+checksum;
        //End checksum---------------------------------------------------------------------------------   
             
             msg.setsequence(sequence);
             msg.setpayload(payload);
             msg.setchecksum(checksum);
             objoutput.writeObject(msg);
             
             
         }
         catch(Exception e){
             System.out.println("Error thread"+e);
         }
         
          
    }
}
