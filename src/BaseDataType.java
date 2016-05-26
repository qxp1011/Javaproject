import javax.swing.text.html.HTMLDocument;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by 小平 on 2016/5/23.
 */
public class BaseDataType {

    //JAVA的基本数据类型测试


    public static void main(String[] args) throws Exception
    {
        short is=0;//短整型，16个bit
        int i=0;//整数，32个bit
        long l=0l;//长整数，64个bit
        float f=0.0f;//单精度，32个bit
        double d=0.0d;//双精度，64个bit
        //bigDecimal

        byte b=0x00;//8个bit，[-128,127]，将byte进行数学运算时，自动转为int
        char c=0x0001;//16个bit,[0,65535]，将char进行数学运算时，自动转为int
        boolean bl=true;

        String s="ABCDE";


        int [] a;

        List<String> sLinkedList=new LinkedList<String>();//队列结构，插入和删除速度快，访问慢
       Queue<String> queue= (Queue<String>)sLinkedList;//转换成Queue
        queue.offer("1");
        queue.offer("2");
        queue.offer("3");
        queue.offer("4");
        queue.offer("5");

        sLinkedList.add("A");
        sLinkedList.add("B");
        sLinkedList.add("C");
        sLinkedList.add("D");
        sLinkedList.add("E");

        String stmp=sLinkedList.get(0);


        stmp=queue.peek();//查看队头，但不出队
        stmp=queue.poll();//出队

        sLinkedList.remove(0);
        sLinkedList.remove("E");

        sLinkedList.clear();



        Stack<String> stack=new Stack<String >();
        stack.push("!");
        stack.push("@");
        stack.push("#");
        stack.push("$");
        stack.push("%");

        stack.add("1");
        stack.add("2");
        stack.add("3");
        stack.add("4");
        stack.add("5");

        stmp=stack.peek();//查看，但不出栈
        stmp=stack.pop();//出栈


        List<String> sArrayList=new ArrayList<String>();//长度可变的数组，随机访问，插入和删除慢
        sArrayList.add("a");
        sArrayList.add("b");
        sArrayList.add("c");
        sArrayList.add("d");
        sArrayList.add("e");

        stmp=sArrayList.get(0);
        sArrayList.remove(0);
        sArrayList.remove("e");

        sArrayList.clear();


        //Set不允许出现重复数据，除HashSet外，还有TreeSet类型，作为Set的变体，在加入数据时自动排序
        //Set不保证集合中元素的顺序，可以包含一个null值
        Set<String> set=new HashSet<String>();

        set.add("1");
        set.add("2");
        set.add("3");
        set.add("4");
        set.add("5");

        Iterator it=set.iterator();
        while (it.hasNext())
        {
            stmp=(String) it.next();
        }

        //注意，由于顺序未知，不保证删除时的顺序同添加时的顺序一致
        set.remove(0);
        set.remove("5");
        set.remove(set.size()-1);

        set.clear();


        //Map为键值对类型
        Map<String,String> map=new HashMap<String, String>();
        map.put("Numbers","12345");
        map.put("char","abcde");
        stmp=map.get("Numbers");

        //遍历
        for(Object obj:map.keySet())
        {
            Object val=map.get(obj);
        }


        //阻塞队列，神器！！
        BlockingQueue<String> blockingQueue=new LinkedBlockingQueue<>();

        System.out.println("i="+i+"\n");

    }

}
