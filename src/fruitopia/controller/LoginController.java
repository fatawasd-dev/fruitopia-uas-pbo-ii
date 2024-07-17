/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fruitopia.controller;

import fruitopia.model.LoginDAO;

/**
 *
 * @author Asani
 */
public class LoginController {
    private LoginDAO model;
    
    public LoginController(LoginDAO model) {
        this.model = model;
    }
    
    public boolean loginUser(String email, String password) {
        boolean isValidUser = model.validateUser(email, password);
        return isValidUser;
    }
}
