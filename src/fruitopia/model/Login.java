/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fruitopia.model;

/**
 *
 * @author Asani
 */
public class Login {
    private int id;
    private String nama;
    private String email;
    private String password;
    private String alamat;
    private String phone;
    
    public Login() {}
    
    public Login(int id, String nama, String email, String password, String alamat, String phone) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.alamat = alamat;
        this.phone = phone;
    }
}
