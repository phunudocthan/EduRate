/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;
import  java.util.UUID;
/**
 *
 * @author Bang
 */
public class VerifyCode {
    public static String generateVerificationCode(){
        UUID uuid=UUID.randomUUID();
        return uuid.toString();
    }
}
