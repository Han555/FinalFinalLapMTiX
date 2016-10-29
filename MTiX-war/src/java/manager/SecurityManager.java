/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author Student-ID
 */
public class SecurityManager {

    private static final Random random = new SecureRandom();
    private final String DEFAULT_HASH_ALGORITHM_NAME = "MD5";

    public SecurityManager() {
    }
    
    
    
    public static byte[] getNextSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    
    public String byteArrayToHexString(byte[] bytes)
    {
        int lo = 0;
        int hi = 0;
        String hexString = "";

        for (int i = 0; i < bytes.length; i++)
        {
            lo = bytes[i];
            lo = lo & 0xff;
            hi = lo >> 4;
            lo = lo & 0xf;
            if (hi == 0)
            {
                hexString += "0";
            }
            else if (hi == 1)
            {
                hexString += "1";
            }
            else if (hi == 2)
            {
                hexString += "2";
            }
            else if (hi == 3)
            {
                hexString += "3";
            }
            else if (hi == 4)
            {
                hexString += "4";
            }
            else if (hi == 5)
            {
                hexString += "5";
            }
            else if (hi == 6)
            {
                hexString += "6";
            }
            else if (hi == 7)
            {
                hexString += "7";
            }
            else if (hi == 8)
            {
                hexString += "8";
            }
            else if (hi == 9)
            {
                hexString += "9";
            }
            else if (hi == 10)
            {
                hexString += "a";
            }
            else if (hi == 11)
            {
                hexString += "b";
            }
            else if (hi == 12)
            {
                hexString += "c";
            }
            else if (hi == 13)
            {
                hexString += "d";
            }
            else if (hi == 14)
            {
                hexString += "e";
            }
            else if (hi == 15)
            {
                hexString += "f";
            }
            
            if (lo == 0)
            {
                hexString += "0";
            }
            else if (lo == 1)
            {
                hexString += "1";
            }
            else if (lo == 2)
            {
                hexString += "2";
            }
            else if (lo == 3)
            {
                hexString += "3";
            }
            else if (lo == 4)
            {
                hexString += "4";
            }
            else if (lo == 5)
            {
                hexString += "5";
            }
            else if (lo == 6)
            {
                hexString += "6";
            }
            else if (lo == 7)
            {
                hexString += "7";
            }
            else if (lo == 8)
            {
                hexString += "8";
            }
            else if (lo == 9)
            {
                hexString += "9";
            }
            else if (lo == 10)
            {
                hexString += "a";
            }
            else if (lo == 11)
            {
                hexString += "b";
            }
            else if (lo == 12)
            {
                hexString += "c";
            }
            else if (lo == 13)
            {
                hexString += "d";
            }
            else if (lo == 14)
            {
                hexString += "e";
            }
            else if (lo == 15)
            {
                hexString += "f";
            }
        }
        
        return hexString;
    }
    
    public String doMD5Hashing(String stringToHash)
    {
        MessageDigest md = null;
        byte[] hashSum = null;
        
        try
        {
            md = MessageDigest.getInstance(DEFAULT_HASH_ALGORITHM_NAME);
            hashSum = md.digest(stringToHash.getBytes());
        }
        catch (Exception ex)
        {
            System.err.println("********** Exception: " + ex);
        }

        if (hashSum != null)
        {
            return byteArrayToHexString(hashSum);
        }
        else
        {
            return null;
        }
    }
}
