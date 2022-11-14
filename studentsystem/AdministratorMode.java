package com.studentsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class AdministratorMode {
    public static void startSystem() {
        ArrayList<Student> list =new ArrayList<>();
        System.out.println("===========Welcome to the Student manage system============");
       loop: while (true) {//ctrl+alt+t
            System.out.println("1.Add student");
            System.out.println("2.Delete student");
            System.out.println("3.Modify student");
            System.out.println("4.Inquire student");
            System.out.println("5.Exit");
            System.out.println("Please input your choice:");
            Scanner sc= new Scanner(System.in);
            String choose = sc.next();
            switch (choose){
                case "1" -> addStudent(list);
                case "2" -> delStudent(list);
                case "3" -> modStudent(list);
                case "4" -> inqStudent(list);
                case "5" -> {
                    System.out.println("Exit");
                    break loop;         //same as system.exit(0);
                }
                default -> System.out.println("No such option");
            }
        }

    }

    //add stu
    public static void addStudent( ArrayList<Student> list){

        Student s = new Student();

        Scanner sc = new Scanner(System.in);
        String id= null;
        while (true) {
            System.out.println("Please input the student id: ");
            id = sc.next();
            boolean flag=judge(list,id);
            if(flag){
                System.out.println("The id has been existed, please reenter!");
            }else{
                s.setId(id);
                break;
            }
        }

        System.out.println("Please input the student name: ");
        String name=sc.next();
        s.setName(name);

        System.out.println("Please input the student age: ");
        int age = sc.nextInt();
        s.setAge(age);

        System.out.println("Please input the student address: ");
        String address = sc.next();
        s.setAddress(address);

        list.add(s);
        System.out.println("The info has been added!");
    }

    //del stu
    public static void delStudent( ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the id you want to delete: ");
        String id=sc.next();
        int index=getIndex(list,id);
        if(index>=0){
            list.remove(index);
            System.out.println("the student with the id: "+ id +" has been deleted" );
        }else {
            System.out.println("id does not exist, fail to delete");
        }
    }

    //modify stu
    public static void modStudent( ArrayList<Student> list){

        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the id you want to modify: ");
        String id=sc.next();
        int index=getIndex(list,id);
        if(index>=0){
            choice: while (true) {
                Student stu=list.get(index);
                System.out.println("Current Student info is listed below:");
                System.out.println("id\t\tname\t\tage\t\taddress");
                System.out.println(stu.getId()+"\t\t"+stu.getName()+"\t\t\t"+stu.getAge()+"\t\t"+stu.getAddress());

                System.out.println("Please select which data you want to modify: ");
                System.out.println("1.name");
                System.out.println("2.age");
                System.out.println("3.address");
                System.out.println("4.exit");

                String choose = sc.next();
                switch (choose) {
                    case "1" -> {
                                    System.out.println("Please input the new name: ");
                                    String newName = sc.next();
                                    stu.setName(newName);
                    }
                    case "2" -> {
                                    System.out.println("Please input the new age: ");
                                    int newAge = sc.nextInt();
                                    stu.setAge(newAge);
                    }
                    case "3" -> {
                                    System.out.println("Please input the new address: ");
                                    String newAddress = sc.next();
                                    stu.setAddress(newAddress);
                    }
                    case "4" -> {
                                     break choice;
                    }
                    default -> {
                                     System.out.println("System error, please contact the technician");
                    }
                }
            }
        }else {
            System.out.println("id does not exist, fail to modify");
        }
    }

    //inquire stu
    public static void inqStudent( ArrayList<Student> list){

       if(list.size()==0){
           System.out.println("No student detected, please add the data!");
           return;
       }
        System.out.println("id\t\tname\t\tage\t\taddress");
       for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
           System.out.println(stu.getId()+"\t\t"+stu.getName()+"\t\t\t"+stu.getAge()+"\t\t"+stu.getAddress());
        }
    }

    public static boolean judge( ArrayList<Student> list,String id){
       /* for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            String sid = stu.getId();
            if(sid.equals(id)){
                return true;
            }
        }
        return false;*/
        return getIndex(list,id)>=0;
    }

    public static int getIndex( ArrayList<Student> list,String id){
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            String sid = stu.getId();
            if(sid.equals(id)){
                return i;
            }
        }
        return -1;
    }

}
