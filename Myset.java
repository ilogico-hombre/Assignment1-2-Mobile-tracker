import java.util.*;

public class Myset {
 Linklist LL1=new Linklist();

public Boolean isEmpty(){
return LL1.isEmpty();
}

public Boolean IsMember(Object o){
     return LL1.contains(o);
}

public void Insert (Object o){
LL1.add(o);
}

public void Delete(Object o){
LL1.remove(o);
}

public Myset Union(Myset a){
for(int j=0;j<a.LL1.size();j++)
{
if(LL1.contains(a.LL1.get(j)))
a.LL1.remove(a.LL1.get(j));
}
for(int j=0;j<LL1.size();j++)
{
a.LL1.add(LL1.get(j));
}
return a;

}

public Myset Intersection(Myset a)
{
for(int j=0;j<a.LL1.size();j++)
{
if(a.LL1.contains(LL1.get(j))){
a.LL1.add(a.LL1.get(j));
a.LL1.remove(a.LL1.get(j));
}
if(!(a.LL1.contains(LL1.get(j))))
a.LL1.remove(a.LL1.get(j));
}
return a;
}

}



