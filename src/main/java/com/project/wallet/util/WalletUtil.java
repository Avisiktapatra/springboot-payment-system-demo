package com.project.wallet.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WalletUtil {

    static double chargeAmount = 0.2;
    static double commissionAmount = 0.05;

    public static String hashcode(String password){
        int hash = 7;
        hash = 31 * hash + (password == null ? 0 : password.hashCode());
        return String.valueOf(hash);
    }

    public static String getCurrentTimestamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public static double computeTransferCharges(double initialAmount){

        double finalChargeAmount =  (chargeAmount * initialAmount)/100;
        double finalCommissionAmount = (commissionAmount * initialAmount)/100;

        double finalAmount = finalChargeAmount + finalCommissionAmount;
        return finalAmount;
    }
}
