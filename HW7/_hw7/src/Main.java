
import java.util.NavigableMap;


public class Main
{
    public static void main(String args[]){
        final Boolean q1 = Q1Test();
        final Boolean q2 = Q2Test();
        if (q1 == q2 == Boolean.TRUE) {
            System.out.println("\n\nYour tests is done. " );
            return;
        }
    }
    public static Boolean Q1Test(){

        NavigableMap<String,String> turkey = new BinaryNavMap<String,String>();
        turkey.put("uskudar","istanbul");
        turkey.put("kadıkoy","istanbul");
        turkey.put("cekirge","bursa");
        turkey.put("gebze","kocaeli");
        turkey.put("niksar","tokat");
        turkey.put("kecıoren","ankara");
        turkey.put("aksaray","istanbul");
        turkey.put("foca","izmir");
        turkey.put("manavgat","antalya");
        turkey.put("kahta","adıyaman");
        turkey.put("biga","canakkale");

        System.out.println("\n\nQ1 TEST:\n");

        System.out.println("The original set odds is " + turkey);
        NavigableMap<String,String> m = turkey.subMap("uskudar",true,"gebze",false);
        System.out.println("The ordered set m is " + m);
        System.out.println("Lower entry is " + turkey.lowerEntry("foca"));
        System.out.println("Lower key is " + turkey.lowerKey("foca"));
        System.out.println("Floor entry is " + turkey.floorEntry("foca"));
        System.out.println("Floor key is " + turkey.floorKey("foca"));
        System.out.println("Celling entry is " + turkey.ceilingEntry("foca"));
        System.out.println("Celling key is " + turkey.ceilingKey("foca"));
        System.out.println("Higher entry is " + turkey.higherEntry("foca"));
        System.out.println("Higher key is " + turkey.higherKey("foca"));
        System.out.println("First entry is " + turkey.firstEntry());
        System.out.println("First key is " + turkey.firstKey());
        System.out.println("Last entry is " + turkey.lastEntry());
        System.out.println("Last key is " + turkey.lastKey());
        System.out.println("First entry is removed. First entry:" + turkey.pollFirstEntry());
        System.out.println("The original set odds is " + turkey);
        System.out.println("Last entry is removed. Last entry:" + turkey.pollLastEntry());
        System.out.println("The original set odds is " + turkey);
        System.out.println("Descending order map " + turkey.descendingMap());
        System.out.println("Navigable key set " + turkey.navigableKeySet());
        System.out.println("Descending key set " + turkey.descendingKeySet());
        System.out.println("Sub map:" + turkey.subMap("kadıkoy","kahta"));
        System.out.println("Head map:" + turkey.headMap("aksaray",false));
        System.out.println("Tail map:" + turkey.tailMap("aksaray",false));
        return Boolean.TRUE;

    }
    public static Boolean Q2Test(){
        HashMap<String,String> turkey=new HashTableChaining<String,String>();
        turkey.put("edremit","balikesir");
        turkey.put("edremit","van");
        turkey.put("kemalpasa","bursa");
        turkey.put("kemalpasa","izmir");
        turkey.put("ortakoy","istanbul");//we assume a district
        turkey.put("ortakoy","aksaray");
        turkey.put("ortakoy","corum");
        turkey.put("kecıoren","ankara");
        turkey.put("pinarbasi","kastamonu");
        turkey.put("pinarbasi","kayseri");
        turkey.put("eregli","konya");
        turkey.put("eregli","tekirdag");
        turkey.put("eregli","zonguldak");
        turkey.put("golbasi","adıyaman");
        turkey.put("golbasi","ankara");
        turkey.put("biga","canakkale");

        System.out.println("\n\nQ2 TEST:\n");
        System.out.println("pinarbasi ilcesine sahip olan iller: " + turkey.get("pinarbasi"));
        System.out.println("Turkiye size" + turkey.size());
        System.out.println("Pinarbasi ilcesine sahip bir entry silindi.");
        turkey.remove("pinarbasi");
        System.out.println("Turkiye size" + turkey.size());
        System.out.println("pinarbasi ilcesine sahip olan iller: " + turkey.get("pinarbasi"));
        System.out.println("golbasi ilcesine sahip olan iller: " + turkey.get("golbasi"));
        System.out.println("Golbasi ilcesine sahip bir entry silindi.");
        turkey.remove("golbasi");
        System.out.println("Turkiye size" + turkey.size());
        System.out.println("golbasi ilcesine sahip olan iller: " + turkey.get("golbasi"));
        System.out.println("cayirova, kocaeli listeye eklendi.");
        turkey.put("cayirova","kocaeli");
        System.out.println("Turkiye size" + turkey.size());
        System.out.println("cayirova ilcesine sahip olan iller: " + turkey.get( "cayirova"));
        System.out.println("cayirova, mardin listeye eklendi.");
        turkey.put("cayirova","mardin");
        System.out.println("cayirova ilcesine sahip olan iller: " + turkey.get( "cayirova"));
        System.out.println("Turkiye size" + turkey.size());


        return Boolean.TRUE;
    }


}
