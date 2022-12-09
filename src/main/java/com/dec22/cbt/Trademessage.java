package com.dec22.cbt;

import jakarta.persistence.*;

@Entity
@Table(name = "trademessages")
public class Trademessage {
    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Column(name = "message", length = Integer.MAX_VALUE)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender")
    private Userdetail sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver")
    private Userdetail receiver;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Userdetail getSender() {
        return sender;
    }

    public void setSender(Userdetail sender) {
        this.sender = sender;
    }

    public Userdetail getReceiver() {
        return receiver;
    }

    public void setReceiver(Userdetail receiver) {
        this.receiver = receiver;
    }

}