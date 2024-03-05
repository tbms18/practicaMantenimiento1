package bank;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class BankAccountTest {
    
    @Test
    @DisplayName("Este método debería devolver true si saca dinero correctamente")
    public void withdraw_enoughMoney_returnTrue(){
        BankAccount cuenta = new BankAccount(100);
        assertTrue(cuenta.withdraw(50));
    }

    @Test
    @DisplayName("Este método debería devolver false si no puede sacar dinero")
    public void withdraw_notEnoughMoney_returnFalse(){
        BankAccount cuenta = new BankAccount(100);
        assertFalse(cuenta.withdraw(101));
    }

    @Test
    @DisplayName("Este método debería devolver true si puede sacar todo el dinero de la cuenta")
    public void withdraw_withdrawAllMoney_returnTrue(){
        BankAccount cuenta = new BankAccount(100);
        assertTrue(cuenta.withdraw(100));
    }

    @Test
    @DisplayName("Este método debería lanzar una excepción al intentar depositar una cantidad negativa")
    public void deposit_negativeAmount_throwsException(){
        BankAccount cuenta = new BankAccount(100);
        assertThrows(IllegalArgumentException.class, () -> cuenta.deposit(-20));
    }

    @Test
    @DisplayName("Este método debería depositar la cantidad de dinero")
    public void deposit_positiveAmount_equals(){
        BankAccount cuenta = new BankAccount(100);
        assertEquals(cuenta.deposit(100), 200);
    }

    @Test
    @DisplayName("Este método debería devolver la cantidad de dinero que hay en la cuenta")
    public void getBalance_positiveAmount_throwsException(){
        BankAccount cuenta = new BankAccount(0);
        int balance = cuenta.deposit(100);
        assertEquals(balance, cuenta.getBalance());
    }

    @Test
    @DisplayName("Este método debería devolver el correcto cálculo del pago mensual de un préstamo")
    public void payment_isCalculatedCorrectly_equals(){
        BankAccount cuenta = new BankAccount(100);
        double total_amount = 120000, interest = 1.3;
        int npayments = 36;
        double res = total_amount*(interest*Math.pow((1+interest), npayments)/(Math.pow((1+interest), npayments)-1));
        assertEquals(res, cuenta.payment(total_amount, interest, npayments));
    }

    @Test
    @DisplayName("Este método debería devolver el cálculo de la cantidad que falta por pagar de un préstamo durante el último mes")
    public void pending_lastMonth_equals(){
        BankAccount cuenta = new BankAccount(100);
        double amount = 1200, interest = 1.3; int npayments = 36, month = 0;
        assertEquals(amount, cuenta.pending(amount, interest, npayments, month));
    }

    @Test
    @DisplayName("Este método debería devolver el cálculo de la cantidad que falta por pagar de un préstamo")
    public void pending_normalCase_equals(){
        BankAccount cuenta = new BankAccount(100);
        double amount = 1200, interest = 1.3; int npayments = 36, month = 1;
        double res = 1199.9999999927768;
        assertEquals(res, cuenta.pending(amount, interest, npayments, month));
    }
}