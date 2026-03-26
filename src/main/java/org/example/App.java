package org.example;

import org.example.service.MemberService;
import org.example.service.QuestionService;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        MemberService mservice = new MemberService();
        QuestionService qservice = new QuestionService();

        boolean isNotExit = true;

        while(isNotExit){

            System.out.println("\n1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            System.out.print("Enter your choice : ");
            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch(choice){

                case 1:{
                    adminMenu(sc, qservice);
                    break;
                }

                case 2:{
                    userMenu(sc, mservice);
                    break;
                }

                case 3:{
                    isNotExit = false;
                    break;
                }

                default:{
                    System.out.println("Enter correct option.");
                }
            }
        }
    }

    private static void adminMenu(Scanner sc, QuestionService service){


        boolean isNotExit = true;

        while(isNotExit){

            System.out.println("\n1. Add Question");
            System.out.println("2. Update Question");
            System.out.println("3. View Questions");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice : ");
            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch(choice){

                case 1:{
                    service.addQuestion();
                    break;
                }

                case 2:{
                    service.updateQuestion();
                    break;
                }

                case 3:{
                    service.displayQuestion();
                    break;
                }

                case 4:{
                    isNotExit = false;
                    break;
                }

                default:{
                    System.out.println("Enter correct option.");
                }
            }
        }
    }

    private static void userMenu(Scanner sc, MemberService service){


        boolean isNotExit = true;

        while(isNotExit){

            System.out.println("\n1. Register User");
            System.out.println("2. Give Exam");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice : ");
            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch(choice){

                case 1:{
                    service.registerUser();
                    break;
                }

                case 2:{
                    service.attendExam();
                    break;
                }

                case 3:{
                    isNotExit = false;
                    break;
                }

                default:{
                    System.out.println("Enter correct option.");
                }
            }
        }
    }
}
