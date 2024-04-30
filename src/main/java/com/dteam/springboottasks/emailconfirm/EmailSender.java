package com.dteam.springboottasks.emailconfirm;

public interface EmailSender {
    void send(String to, String email);
}
