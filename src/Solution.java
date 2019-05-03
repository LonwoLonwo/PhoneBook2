import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution
{
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        HashMap<String, String> phoneBook = new HashMap<>();
        Set<Map.Entry<String, String>> entrySet = phoneBook.entrySet();

        phoneBook.put("123", "Bobby");
        phoneBook.put("435", "Olly");
        phoneBook.put("789", "Wolly");
        phoneBook.put("233", "Elly");

        for(;;)
        {
            System.out.println("Пожалуйста, введите номер или имя абонента:");
            String command = reader.readLine().trim();

            if(command.equals("LIST"))
            {
               phoneBook.entrySet().stream()
                       .sorted(Map.Entry.<String, String>comparingByValue())
                       .forEach(System.out::println);
            }
            //Проверяем наличие номера в телефонной книге
            else if(command.equals("\\[0-9]+"))
            {
                String number = command;
                if(phoneBook.containsKey(number))
                {
                    System.out.println(phoneBook.get(number));
                }
                else if(!phoneBook.containsKey(number))
                {
                    System.out.println("Номер телефона в базе отсутствует. Введите имя абонента: ");
                    String name = reader.readLine().trim();
                    phoneBook.put(number, name);
                }
            }
            //Проверяем наличие имени в телефонной книге
            else if(command.equals("\\w+"))
            {
                String name = command;
                int count = 0;
                for(Map.Entry<String, String> pair : entrySet)
                {
                    if(name.equals(pair.getValue()))
                    {
                        String number = pair.getKey();
                        System.out.println(number);
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
