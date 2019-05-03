import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution
{
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner command1 = new Scanner(System.in);

        HashMap<String, String> phoneBook = new HashMap<>();
        Set<Map.Entry<String, String>> entrySet = phoneBook.entrySet();

        phoneBook.put("123", "Bobby");
        phoneBook.put("435", "Olly");
        phoneBook.put("789", "Wolly");
        phoneBook.put("233", "Elly");

        for(;;)
        {
            System.out.println("Пожалуйста, введите номер или имя абонента:");
            //String command = reader.readLine().trim();

            if(command1.hasNext("LIST"))
            {
               phoneBook.entrySet().stream()
                       .sorted(Map.Entry.<String, String>comparingByValue())
                       .forEach(System.out::println);
                break;
            }
            //Проверяем наличие номера в телефонной книге
            else if(command1.hasNextInt())
            {
                String number = command1.nextLine();
                if(phoneBook.containsKey(number))
                {
                    System.out.println("Номер телефона " + number + " принадлежит " + phoneBook.get(number));
                }
                else if(!phoneBook.containsKey(number))
                {
                    System.out.println("Номер телефона в базе отсутствует. Введите имя абонента: ");
                    String name = reader.readLine().trim();
                    phoneBook.put(number, name);
                }
            }
            //Проверяем наличие имени в телефонной книге
            else if(command1.hasNextLine())
            {
                String name = command1.nextLine();
                int count = 0;
                for(Map.Entry<String, String> pair : entrySet)
                {
                    if(name.equals(pair.getValue()))
                    {
                        String number = pair.getKey();
                        System.out.println("Абаноненту с именем " + name + " принадлежит номер: " + number);
                        count++;
                    }
                }
                if(count == 0)
                {
                    System.out.println("Номер телефона в базе отсутствует. Введите номер абонента: ");
                    String number = reader.readLine().trim();
                    phoneBook.put(number, name);
                }
            }
        }
    }
}
