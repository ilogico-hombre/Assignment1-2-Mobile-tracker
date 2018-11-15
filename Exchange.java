public class Exchange{
private int identifier;
public Exchange Parent;
ExchangeList listchild=new ExchangeList();
public MobilePhoneSet set1=new MobilePhoneSet();

Exchange(int number)
{
identifier=number;

}

public int returnidentifier(){
return identifier;
}

public Exchange parent(){
return Parent;
}

public int numChildren(){
return listchild.size();
}


public Exchange child(int i){
return (Exchange) listchild.get(i);
}


public Boolean isRoot(){
    return Parent==null;
}


public RoutingMapTree subtree(int i){
    RoutingMapTree a = new RoutingMapTree();
    a.rootnode=this.child(i);
    return a;
}



public MobilePhoneSet residentSet(){
return set1;
}
}




