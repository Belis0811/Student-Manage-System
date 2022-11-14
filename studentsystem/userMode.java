package com.studentsystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class userMode {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();
        System.out.println("Welcome to the student manage system");
        Scanner sc = new Scanner(System.in);
        loop:while (true) {
            System.out.println("Please choose the number below: ");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Forgot Password");
            System.out.println("4. Exit");
            String choose = sc.next();
          switch (choose){
                case "1" -> Login(list);
                case "2" -> Register(list);
                case "3" -> ForgotPassword(list);
                case "4"->{
                    System.out.println("Exit");
                    break loop;
                }
                default -> System.out.println("No operation found");
            }
        }

    }

    private static void Login(ArrayList<User> list) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("Please input the username");
            String username = sc.next();
            boolean flag=contains(list,username);
            if(!flag){
                System.out.println("The username has not been registered, please register!");
                return;
            }
            System.out.println("Please input your password:");
            String password = sc.next();

            while (true) {
                String rightCode = getCode();
                System.out.println("The verification code is "+rightCode);
                System.out.println("Please input the verification code:");
                String code = sc.next();
                if(code.equalsIgnoreCase(rightCode)){
                    System.out.println("Correct");
                    break;
                }else{
                    System.out.println("Code is incorrect!");
                    continue;
                }
            }
            User userInfo=new User(username,password,null,null);
            boolean result = checkUserInfo(list, userInfo);
            if(result){
                System.out.println("Successfully login!");
                AdministratorMode stu = new AdministratorMode();
                stu.startSystem();
                break;
            }else{
                System.out.println("Login failed!");
                if(i==2){
                    System.out.println("The user "+username+" has been locked, please contact the admin");
                    return;
                }else{
                    System.out.println("The Username or password is incorrect! You have "+(2-i)+"chance(s) left!");
                }
            }
        }
    }

    private static void Register(ArrayList<User> list) {
        User u = new User();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Please input the username: ");
            String userName = sc.next();
            boolean flag1 = checkUsername(userName);
            if(!flag1){
                System.out.println("The format is invalidate!");
                continue;
            }
            boolean flag2 = contains(list,userName);
            if(flag2){
                System.out.println("The username "+userName +" is already existed!");
            }else{
                System.out.println("The username "+userName +" could be use!");
                u.setUserName(userName);
                break;
            }
        }
        while (true) {
            System.out.println("Please input your password:");
            String password = sc.next();
            System.out.println("Please input your password again:");
            String repassword=sc.next();
            if(!password.equals(repassword)){
                System.out.println("The password you enter for twice is not the same!");
                continue;
            }else{
                u.setPassword(password);
                break;
            }
        }
        while (true) {
            System.out.println("Please input your id:");
            String id = sc.next();
            boolean flag= checkID(id);
            if(flag){
                u.setId(id);
                break;
            }else{
                System.out.println("The id you input is invalidate!");
                continue;
            }
        }
        while (true) {
            System.out.println("Please input your phone number:");
            String number = sc.next();
            boolean flag = checkNumber(number);
            if(flag){
                u.setMobile(number);
                break;
            }else{
                System.out.println("The phone number is invalidate!");
                continue;
            }
        }
        list.add(u);
        System.out.println("Register complete!");
        printList(list);
    }

    private static void ForgotPassword(ArrayList<User> list) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Please input your username:");
        String username = sc.next();
        boolean flag = contains(list,username);
        if(!flag){
            System.out.println("The user does not exist!");
            return;
        }
        System.out.println("Please input your id: ");
        String id = sc.next();
        System.out.println("Please input your phone number: ");
        String number = sc.next();
        int index = findIndex(list,username);
        User user = list.get(index);
        if(!(user.getId().equalsIgnoreCase(id)&&user.getMobile().equals(number))){
            System.out.println("The info is incorrect, password modify failed!");
            return;
        }
        while (true) {
            System.out.println("Please input your new password:");
            String password = sc.next();
            System.out.println("Please input your password again:");
            String repassword=sc.next();
            if(!password.equals(repassword)){
                System.out.println("The password you enter for twice is not the same!");
                continue;
            }else{
                user.setPassword(password);
                System.out.println("Password has been modified!");
                break;
            }
        }
    }

    private static int findIndex(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if(user.getUserName().equals(username)){
                return i;
            }
        }
        return -1;
    }
    private static void printList(ArrayList<User> list) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            System.out.println("name: "+user.getUserName()+" password: "+user.getPassword()+
                    " id: "+ user.getId()+" mobile number: "+user.getMobile());
        }
    }
    private static boolean checkNumber(String number) {
        if(number.length()!=11){
            return false;
        }
        if(number.startsWith("0")){
            return false;
        }
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if(!(c>='0')&&(c<='9')){
                return false;
            }
        }
        return true;
    }
    private static boolean checkID(String id) {
        if(id.length()!=18){
            return false;
        }
        if(id.startsWith("0")){
            return false;
        }
        for (int i = 0; i < id.length()-1; i++) {
            char c = id.charAt(i);
            if(!(c>='0'&&c<='9')){
                return false;
            }
        }
        char end = id.charAt(id.length()-1);
        if((end>='0'&&end<='9')||(end=='x')||(end=='X')){
            return true;
        }else {
            return false;
        }
    }
    private static boolean contains(ArrayList<User> list, String userName) {
        for (int i = 0; i < list.size(); i++) {
            User u = list.get(i);
            String RightUsername = u.getUserName();
            if(RightUsername.equals(userName)){
                return true;
            }
        }
        return false;
    }
    private static boolean checkUsername(String userName) {
        int len = userName.length();
        if(len<3||len>15){
            return false;
        }
        for (int i = 0; i < userName.length(); i++) {
            char c = userName.charAt(i);
            if(!((c>='a'&&c<='z')||(c>='A'&&c<='Z')||(c>='0'&&c<='9'))){
                return false;
            }
        }
        int count=0;
        for (int i = 0; i < userName.length(); i++) {
            char c = userName.charAt(i);
            if((c>='a'&&c<='z')||(c>='A'&&c<='Z')){
                count++;
                break;
            }
        }
        return count>0;
    }
    private static String getCode(){
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char)('a'+i));
            list.add((char)('A'+i));
        }
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(list.size());
            char c = list.get(index);
            sb.append(c);
        }
        int number=r.nextInt(10);
        sb.append(number);
        char[] arr=sb.toString().toCharArray();
        int randomIndex=r.nextInt(arr.length);
        char temp = arr[randomIndex];
        arr[randomIndex]=arr[arr.length-1];
        arr[arr.length-1]=temp;
        return new String(arr);
    }
    private static boolean checkUserInfo(ArrayList<User> list, User userInfo) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getUserName().equals(userInfo.getUserName()) && user.getPassword().equals(userInfo.getPassword())) {
                return true;
            }
        }
        return false;
    }

}
