/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chat;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    private static ChatRoom instance = new ChatRoom();
    private List<Message> messages = new ArrayList<>();

    private ChatRoom() {}

    public static ChatRoom getInstance() {
        return instance;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }
}
