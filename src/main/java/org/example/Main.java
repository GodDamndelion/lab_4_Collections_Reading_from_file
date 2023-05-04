package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        ArrayList<Person> arrayList = task();
        for (Person person : arrayList)
        {
            person.print();
            System.out.println("=====================");
        }
    }

    /**
     * Считывает построчно данные из файла
     * @return ArrayList в котором содержатся объекты типа Person
     */
    static ArrayList<Person> task()
    {
        char separator = ';';
        ArrayList<Person> arrayList = new ArrayList<>();
        File file = new File("foreign_names.csv");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file)))
        {
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null)
            {
                int index = 0, pID = 0, elementType = 0, length = line.length();
                String pName = "", pSex = "", dName = "", pBirthday = "", tmp = "";
                double pSalary;
                while (index < length)
                {
                    if (line.charAt(index) != separator)
                    {
                        tmp += line.charAt(index);
                    } else {
                        if (elementType == 0 && pID == 0)
                            pID = Integer.parseInt(tmp);
                        else if (elementType == 1 && pName.equals(""))
                            pName = tmp;
                        else if (elementType == 2 && pSex.equals(""))
                            pSex = tmp;
                        else if (elementType == 3 && pBirthday.equals(""))
                            pBirthday = tmp;
                        else if (elementType == 4 && dName.equals(""))
                            dName = tmp;
                        tmp = "";
                        elementType++;
                    }
                    index++;
                }
                pSalary = Integer.parseInt(tmp);
                Person person = new Person(pID, pName, pSex, dName, pSalary, pBirthday);
                arrayList.add(person);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return arrayList;
    }
}

/**
 * Класс "Человек"
 */
final class Person
{
    private final int ID;
    private final String Name;
    private final String Sex;
    private final Department department;
    private final double Salary;
    private final String Birthday;

    /**
     * Конструктор с параметрами
     * @param ID - входной ID
     * @param Name - входное имя
     * @param Sex - входной пол
     * @param departmentName - входное название подразделения
     * @param Salary - входное значение зарплаты
     * @param Birthday - входная дата дня рождения
     */
    Person(int ID, String Name, String Sex, String departmentName, double Salary, String Birthday)
    {
        this.ID = ID;
        this.Name = Name;
        this.Sex = Sex;
        this.department = new Department(departmentName);
        this.Salary = Salary;
        this.Birthday = Birthday;
    }

    /**
     * Метод для печтаи в консоль
     */
    void print()
    {
        System.out.println("ID: " + ID);
        System.out.println("Name: " + Name);
        System.out.println("Sex: " + Sex);
        System.out.println("Salary: " + Salary);
        System.out.println("Birthday: " + Birthday);
        System.out.println("Department: ");
        department.print();
    }
}

/**
 * Класс "Подразделение"
 */
final class Department
{
    private final int ID;
    private final String Name;

    /**
     * Конструктор с параметром
     * @param Name - входное название подразделения
     */
    Department(String Name)
    {
        this.Name = Name;
        Random random = new Random();
        ID = random.nextInt(1000000);
    }

    /**
     * Метод для печтаи в консоль
     */
    void print()
    {
        System.out.println("ID: " + ID);
        System.out.println("Name: " + Name);
    }
}
