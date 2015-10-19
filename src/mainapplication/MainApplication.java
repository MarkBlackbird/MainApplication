/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainapplication;

import networking.NetworkHandler;

/**
 *
 * @author kosma
 */
public class MainApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NetworkHandler nh = new NetworkHandler(10800);
    }
    
}
