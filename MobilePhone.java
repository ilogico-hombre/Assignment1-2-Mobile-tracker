public class MobilePhone{
private int id;
private boolean status=true;
public Exchange lead=null ;

public MobilePhone(int number)
{
id = number;
status=true;
}

public int number()
{
return id;
}

public Boolean status()
{
    return status==true;
}

public void switchOn(){
if (status==false)
status=true;
}

public void switchOff(){
if(status==true)
status=false;
}

public Exchange location() throws Exception
{
if(status==true)
return lead;
else 
throw new Exception("Phone is switched off");
}
}


