import java.util.*;

public class Solution
{
    public static void main(String[] args)  {
        Scanner command1 = new Scanner(System.in);

        TreeMap<String, String> phoneBook = new TreeMap<>();
        Set<Map.Entry<String, String>> entrySet = phoneBook.entrySet();

        for(;;)
        {
            System.out.println("Пожалуйста, введите номер или имя абонента:");

            if(command1.hasNext("EXIT")){
                break;
            }
            else if(command1.hasNext("LIST"))
            {
               /*phoneBook.entrySet().stream()
                       .sorted(Map.Entry.<String, String>comparingByValue())
                       .forEach(System.out::println);*/
               for(Map.Entry entry: phoneBook.entrySet()){
                   System.out.println("Имя абонента: " + entry.getKey() + ", номер абонента: " + entry.getValue());
               }
               break;
            }
            //Проверяем наличие номера в телефонной книге
            else if(command1.hasNext("[+()0-9-\\s]+"))
            {
                String consoleNumber = command1.nextLine().trim();
                String number = consoleNumber.replaceAll("[^0-9]+", "");
                int count = 0;
                for(Map.Entry<String, String> pair : entrySet)
                {
                    if(number.equals(pair.getValue()))
                    {
                        String name = pair.getKey();
                        System.out.println("Номер телефона " + number + " принадлежит абоненту: " + name);
                        count++;
                    }
                }
                if(count == 0){
                    System.out.println("Номер телефона в базе отсутствует. Введите имя абонента: ");
                    String name = command1.nextLine().trim();
                    if(phoneBook.containsKey(name))
                    {
                        System.out.println("Номер телефона " + number + "уже принадлежит абоненту: " + phoneBook.get(name));
                    }
                    else if(!phoneBook.containsKey(name))
                    {
                        phoneBook.put(name, number);
                        System.out.println("Номер телефона и имя абонента добавлены в базу.");
                    }
                }
            }
            //Проверяем наличие имени в телефонной книге
            else if(command1.hasNext("[A-Za-zА-Яа-яЁё\\s]+"))
            {
                String name = command1.nextLine().trim();
                if(phoneBook.containsKey(name))
                    {
                        System.out.println("Абоненту с именем " + name + " принадлежит номер: " + phoneBook.get(name));
                    }

                else if(!phoneBook.containsKey(name))
                {
                    System.out.println("Имя абонента в базе отсутствует. Введите номер абонента: ");
                    String consoleNumber = command1.nextLine();
                    String number = consoleNumber.replaceAll("[^0-9]+", "");
                    int count = 0;
                    for(Map.Entry<String, String> pair : entrySet)
                    {
                        if(number.equals(pair.getValue()))
                        {
                            System.out.println("Номер телефона " + number + " уже принадлежит абоненту: " + pair.getKey());
                            count++;
                        }
                    }
                    if(count == 0)
                    {
                        phoneBook.put(name, number);
                        System.out.println("Номер телефона и имя абонента добавлены в базу.");
                    }
                }
            }
        }

        command1.close();
    }
}
