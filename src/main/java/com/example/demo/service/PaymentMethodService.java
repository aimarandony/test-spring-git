package com.example.demo.service;

import com.example.demo.model.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PaymentMethodService {

    public List<PaymentMethod> getSortedPaymentMethods() {
        List<CardCredit> cardCreditList = getCardCreditList();
        List<Account> accountList = getAccountList();

        // Ordenar las listas: Primero AH, luego CC, y luego alfabéticamente por nombre
        accountList.sort(Comparator
                .comparing((Account acc) -> acc.getType() == AccountType.AH ? 0 : 1)
                .thenComparing(Account::getName));

        cardCreditList.sort(Comparator.comparing(CardCredit::getName));

        // Convertir listas en PaymentMethod usando Stream
        return Stream.concat(
                cardCreditList.stream().map(this::convertToPaymentMethod),
                accountList.stream()
//                        .filter(account -> Set.of(AccountType.AH, AccountType.CC).contains(account.getType()))
                        .map(this::convertToPaymentMethod)
        ).collect(Collectors.toList());
    }

    private PaymentMethod convertToPaymentMethod(CardCredit cardCredit) {
        return new PaymentMethod(cardCredit.getName(), cardCredit.getNumber());
    }

    private PaymentMethod convertToPaymentMethod(Account account) {
        return new PaymentMethod(account.getName(), account.getNumber());
    }

    private List<CardCredit> getCardCreditList() {
        return new ArrayList<>(List.of(
                new CardCredit("B Card Credit 01", "******111"),
                new CardCredit("D Card Credit 02", "******112"),
                new CardCredit("A Card Credit 03", "******113")
        ));
    }

    private List<Account> getAccountList() {
        return new ArrayList<>(List.of(
                new Account("C Account 01 AH", "*** *** *11", AccountType.AH),
                new Account("C Account 02 CC", "*** *** *12", AccountType.CC),
                new Account("D Account 03 AH", "*** *** *13", AccountType.AH),
                new Account("A Account 04 CC", "*** *** *14", AccountType.CC),
                new Account("A Account 05 AH", "*** *** *15", AccountType.AH)
        ));
    }
}