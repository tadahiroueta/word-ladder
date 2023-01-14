import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordLadder2 {

    public static void main(String[] args) throws FileNotFoundException
    {
        File input = new File("input.txt"), dictionary = new File("dictionary.txt");
        Scanner st = new Scanner(input), s = new Scanner(dictionary), user = new Scanner(System.in);
        System.out.print("Pick a ladder (0 - 15)): ");
        int ladNum = user.nextInt();
        for (int i = 0; i < ladNum; i++)
        {
            st.nextLine();
        }
        String start = st.next(), end = st.next();
        int l = start.length();
        Queue<Stack<String>> ladders = new LinkedList<>();
        Queue<String> sub = new LinkedList<>();
        String n = s.next();
        while (s.hasNext())
        {
            if (n.length() == l)
            {
                sub.add(n);
            }
            n = s.next();
        }
        Stack<String> begin = new Stack<String>();
        begin.push(start);
        ladders.add(begin);
        while (ladders.size() != 0)
        {
            Stack<String> testing = ladders.poll();
            String testString1 = testing.peek();
            if (testString1.equalsIgnoreCase(end))
            {
                System.out.print("We found a ladder! ");
                System.out.println(testing);
                System.exit(0);
            }
            int sL = sub.size();
            for (int i = 0; i < sL; i++)
            {
                String ne = sub.poll();
                int charDC = 0;
                for (int i2 = 0; i2 < ne.length(); i2++)
                {
                    String c1 = testString1.substring(i2, i2 + 1), c2 = ne.substring(i2, i2 + 1);
                    if (!(c1.equalsIgnoreCase(c2)))
                    {
                        charDC++;
                    }
                }
                if (charDC == 1)
                {
                    Stack<String> add = new Stack<String>();
                    String[] tempAdd = new String[testing.size()];
                    for (int i3 = 0; i3 < tempAdd.length; i3++)
                    {
                        tempAdd[i3] = testing.pop();
                    }
                    for (int i3 = 0; i3 < tempAdd.length; i3++)
                    {
                        add.push(tempAdd[tempAdd.length - i3 - 1]);
                        testing.push(tempAdd[tempAdd.length - i3 - 1]);
                    }
                    add.push(ne);
                    ladders.add(add);
                }
                else
                {
                    sub.add(ne);
                }
            }
        }
    }
}
