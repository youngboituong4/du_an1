/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thinh
 */
public class ChatLieu {

    private int stt;
    private String maChatLieu;
    private String tenChatLieu;

    public ChatLieu() {
    }

    public ChatLieu(int stt, String maChatLieu, String tenChatLieu) {
        this.stt = stt;
        this.maChatLieu = maChatLieu;
        this.tenChatLieu = tenChatLieu;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getMaChatLieu() {
        return maChatLieu;
    }

    public void setMaChatLieu(String maChatLieu) {
        this.maChatLieu = maChatLieu;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    @Override
    public String toString() {
        return "ChatLieu{" + "stt=" + stt + ", maChatLieu=" + maChatLieu + ", tenChatLieu=" + tenChatLieu + '}';
    }

    public Object[] toDataRow() {
        return new Object[]{this.stt,this.maChatLieu, this.tenChatLieu};
    }
}
