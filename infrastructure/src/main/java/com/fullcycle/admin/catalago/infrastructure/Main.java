package com.fullcycle.admin.catalago.infrastructure;

import com.fullcycle.admin.catalago.application.UseCase;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Word!");
        System.out.println(new UseCase().execute());
    }
}