package com.zsgs.chandru.interviewpanel.exceptionmessage;

import com.zsgs.chandru.interviewpanel.colortext.Color;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionHandling {

    private static Scanner scan = new Scanner(System.in);
    public static void printErrorMessage(String message) {
        System.out.println( Color.ANSI_RED +
                "\n=================================" +
                "\n=             ERROR             =" +
                "\n\t" + message +
                "\n=        Please Try Again       =" +
                "\n=================================" +
                Color.ANSI_RESET );
    }
    public static int getIntInput() {
        int input = 0;
        try {
            input = scan.nextInt();
        } catch (InputMismatchException e) {
            printErrorMessage("Invalid input! Please enter an integer.");
            scan.nextLine(); // Clear input buffer
            return 0;
        }
        return input;
    }
}
