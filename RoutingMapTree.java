
import java.io.PrintStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bhavesh
 */
public class RoutingMapTree {
    Exchange rootnode;
    
    RoutingMapTree(){
    rootnode =new Exchange(0);    
    }
    public Boolean containsNode(Exchange a){
        return rootnode.listchild.contains(a);
 
   }
public void switchOn(MobilePhone a, Exchange b){
    a.switchOn();
    a.lead=b ;
    Exchange test= b ;
    while(test!=null)
    {
        test.residentSet().Insert(a);
         test=test.parent();
    }
    //a.lead= b ;
}


public void switchOff(MobilePhone a){
//if(a.status()==true)
a.switchOff();
if(rootnode.residentSet().IsMember(a)){
rootnode.residentSet().Delete(a);
    int i;
for(i=0;i<rootnode.listchild.size();i++)
    rootnode.subtree(i).switchOff(a);
}
}
public String performAction(String actionMessage){
   String message="";
   String S1="addExchange";
    String S2="switchOnMobile";
    String S3="switchOffMobile";
    String S4="queryNthChild";
    String S5="queryMobilePhoneSet";
    String S6="queryFindPhone";
    String S7="queryLowestRouter";
    String S8="findCallPath";
    String S9="movephone";
    
    int countspace=0;
    String a1="",a2="",a3="";
    int i=0;
    while(i<actionMessage.length())
    {
        if(actionMessage.charAt(i)==' '){
            countspace++;
        }
        else if(countspace==0)
        a1=a1+actionMessage.charAt(i);
        else if(countspace==1)
            a2=a2+actionMessage.charAt(i);
        else 
            a3=a3+actionMessage.charAt(i);
        
        i++;
    }
    if(a1.equals(S1))
    {
        int a=Integer.parseInt(a2);
        int b=Integer.parseInt(a3);
        Exchange new1=new Exchange(b);
        findex(a).listchild.add(new1);
        new1.Parent=findex(a);
    }
 if(a1.equals(S2)){
      int a=Integer.parseInt(a2);
        int b=Integer.parseInt(a3);
        if(findmo(a)!=null) switchOff(findmo(a)) ;
     MobilePhone new1=new MobilePhone(a);
     if (findex(b)!=null){
         switchOn(new1,findex(b));
        // new1.lead=findex(b) ;
    }
     else System.out.println("Error- No exchange with identifier b");
 }
        if(a1.equals(S3)){
            int a=Integer.parseInt(a2);
            if(findmo(a)!=null)
            switchOff(findmo(a));
        
            else
            System.out.println("Error- No mobile phone with identifier a");
            
        }
        if(a1.equals(S4)){
            message=actionMessage+": ";
             int a=Integer.parseInt(a2);
        int b=Integer.parseInt(a3);
            if(findex(a).child(b)==null)
                System.out.println("Error - No b child of Exchange a");
            
        message=actionMessage+": " +findex(a).child(b).returnidentifier();
             
        }
        if(a1.equals(S5))
        {
            message=actionMessage+": ";
            
         int a=Integer.parseInt(a2);
        MobilePhoneSet MPS=new MobilePhoneSet();
        MPS=findex(a).residentSet();
        for(i=0;i<MPS.LL1.size()-1;i++)
            message=message + ((MobilePhone)MPS.LL1.get(i)).number()+", ";
        message=message + ((MobilePhone)MPS.LL1.get(i)).number();
        }    
        if(a1.equals(S6))
        {
            try
            {int a=Integer.parseInt(a2);
            if(findmo(a)!=null)
                    {
            MobilePhone M1=new MobilePhone(a);
            
            Exchange E1=findPhone(M1);
            int c=E1.returnidentifier();
            message=actionMessage+": " +c;    
        }
            else System.out.println("Error - No mobile phone with identifier "+a+" found in the network");
            }
            catch(IllegalArgumentException e)
            {
                int a=Integer.parseInt(a2);
                message=actionMessage+": "+"Error - No mobile phone with identifier "+a+" found in the network";
            }
        }
        
        if(a1.equals(S7))
        {
            int a=Integer.parseInt(a2);
            int b=Integer.parseInt(a3);
           Exchange EE1=findex(a) ;
           Exchange EE2=findex(b) ;
            int d=lowestRouter(EE1,EE2).returnidentifier();
            message=actionMessage+": "+d;
        }
        
        if(a1.equals(S8)){
             int a=Integer.parseInt(a2);
             int b=Integer.parseInt(a3);
            String c="";
            if(findmo(a)!=null && findmo(b)!=null)
            {
            MobilePhone M1= findmo(a);
            MobilePhone M2= findmo(b);
            ExchangeList Q=routeCall(M1,M2) ;
           
            for(int k=0;k<Q.size();k++)
            {
                Exchange trial =(Exchange) Q.get(k) ;
               c=c+trial.returnidentifier() ;
               if(k!=Q.size()-1) c=c+", " ;
               
            }
                       message="queryFindCallPath"+ " "+a+" " +b+": "+c;
            }
            else if(findmo(a)==null)
                System.out.println("Error - Mobile phone with identifier "+a+" is currently switched off");
            else if(findmo(b)==null)
                System.out.println("Error - Mobile phone with identifier "+b+" is currently switched off");
        }
        if(a1.equals(S9)){
         int a=Integer.parseInt(a2);
         int b=Integer.parseInt(a3);   
        MobilePhone MM=findmo(a);
        Exchange EE=findex(b);
       
        movePhone(MM,EE);
        }
    return message;
}
public MobilePhone findmo(int a){
    for(int i=0;i<rootnode.residentSet().LL1.size();i++)
        
    {
        if(((MobilePhone)rootnode.residentSet().LL1.get(i)).number()==a){ 
            return (MobilePhone)rootnode.residentSet().LL1.get(i);
        }
    }
    return null;
}
public Exchange findex(int a){
    if(rootnode.returnidentifier()==a) return rootnode;
    for(int i=0;i<rootnode.listchild.size();i++){
        if(rootnode.subtree(i).findex(a)!=null) return rootnode.subtree(i).findex(a);
    }
    return null;
}
public Exchange findPhone(MobilePhone m){
   try{
       int a=m.number();
   
    if(rootnode.residentSet().LL1.contains(m)==false)
        throw new IllegalArgumentException("Error - No mobile phone with identifier "+a+" found in the network");
    else return m.location();
    }
    catch(Exception e){}
   return null;
}
public Exchange lowestRouter(Exchange a, Exchange b){
   
    if(a==b) return a;
    else return lowestRouter(a.parent(),b.parent()) ;
    }
    
public ExchangeList routeCall(MobilePhone a, MobilePhone b)
{
   
    
    ExchangeList EL1=new ExchangeList();

    ExchangeList EL2=new ExchangeList();   
    
   try{
    Exchange l1=a.location();
    Exchange l2=b.location();
    Exchange p1=a.location();
    Exchange p2=b.location();
   
    int i=0;
  
   
   while(l1!=lowestRouter(p1,p2))
   {
       
       EL1.add(l1);
       l1=l1.parent();
       
   }
       
   while(l2!=lowestRouter(p1,p2))
   {
       EL2.add(l2);
       l2=l2.parent();
   }
   EL1.add(lowestRouter(p1,p2)) ;
   
   for(int j=0;j<EL2.size();j++)
   {
    EL1.add(EL2.get(EL2.size()-j-1));
    }
   
   return EL1;
   }
   catch(Exception e){System.out.println("ohfuck") ;
   return EL1 ;} 
}
public void movePhone(MobilePhone a, Exchange b)
{
   
    MobilePhone test = new MobilePhone(a.number()) ;
    switchOff(a);
    switchOn(test,b);
    if(b.returnidentifier()==1) {
    MobilePhone yes= (MobilePhone) findex(1).set1.LL1.get(4) ;
           System.out.println(yes.number()) ;
    }
    
}


}







        