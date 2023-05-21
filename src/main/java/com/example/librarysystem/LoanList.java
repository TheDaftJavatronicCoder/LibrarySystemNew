package com.example.librarysystem;

import java.util.ArrayList;

public class LoanList {

    private static ArrayList<String> loanList = new ArrayList<String>();

    public static ArrayList<String> getList(){
        return loanList;
    }

    public static void addToList(String loan){
        loanList.add(loan);
    }


    public static void clearList(){
        loanList.clear();
    }

}
