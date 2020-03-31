/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package password.ui;

import password.bl.PasswordBL;

/**
 *
 * @author Denis Imeri
 */
public class PasswordUI {
    public static void main(String[] args) {
        PasswordBL pwd = new PasswordBL();
        pwd.generatePasswd(10);
        System.out.println(pwd.getPasswd());
    }
}
