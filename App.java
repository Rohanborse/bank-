package com.demo;

import java.util.Scanner;

import com.dao.UserDao;
import com.model.User;

public class App {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("-----------------Bella chaw Bank By Prof.--------------");
		System.out.println("1. Create Account");
		System.out.println("2. Existing Account");
		System.out.println("3. Exit");
		System.out.println("----------------------------------------");
		System.out.print("Enter Choice:");
		int n = sc.nextInt();
		switch (n) {
		case 1:
			User u = new User();
			System.out.print("Enter Your Name:");
			u.setUacname(sc.next());
			System.out.print("Enter Yor A/c Type:");
			u.setUactype(sc.next());
			System.out.print("Enter Yor Opening Balance:");
			u.setUacbalance(sc.nextDouble());
			int a[] = new UserDao().createAccount(u);
			System.out.println("Dear " + u.getUacname());
			System.out.println("Your A/c Created with A/c No:" + a[0]);
			System.out.println("Your A/c Pin No:" + a[1]);
			System.out.println("Your Opening Balance is:" + u.getUacbalance());
			break;
		case 2:
			System.out.print("Enter A/c No:");
			int uacno = sc.nextInt();
			System.out.print("Enter Pin no:");
			int uacpin = sc.nextInt();
			if (new UserDao().validateAccount(uacno, uacpin)) {
				System.out.println("-----------------Bella Chaw Bank--------------");
				System.out.println("1. Deposite Amount");
				System.out.println("2. Withdrawl Amount");
				System.out.println("3. Check Balance");
				System.out.println("4. A/c Details");
				System.out.println("5. Exit");
				System.out.println("----------------------------------------");
				System.out.print("Enter Choice:");
				n = sc.nextInt();
				switch (n) {
				case 1:
					System.out.print("Enter Your Deposite Amount");
					double amount = sc.nextDouble();
					double amount1 = new UserDao().depositeAmount(uacno, uacpin, amount);
					System.out.println(amount + " deposited successfully");
					System.out.println("Your current Balance is:" + amount1);
					break;
				case 2:
					System.out.print("Enter Your Withdrawl Amount");
					amount = sc.nextDouble();
					amount1 = new UserDao().withdrawlAmount(uacno, uacpin, amount);
					if (amount1 != 0) {
						System.out.println(amount + " withdrawl successfully");
						System.out.println("Your current Balance is:" + amount1);
					}
					break;
				case 3:
					amount = new UserDao().checkBalance(uacno, uacpin);
					System.out.println("Your updated balance is:"+amount);
					break;
				case 4:
					u = new UserDao().diplayAccountDetails(uacno, uacpin);
					System.out.println("A/c Holder Name:"+u.getUacname());
					System.out.println("A/c Type:"+u.getUactype());
					System.out.println("A/c No:"+u.getUacid());
					System.out.println("A/c Balance:"+u.getUacbalance());
					break;
				default:
				}
			} else
				System.out.println("Incorrect credential!!!");
			break;
		default:
			System.out.println("Thank you for using HDFC Bank");
		}

	}
}
