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

    private String maChatLieu;
    private String tenChatLieu;

    public ChatLieu() {
    }

    public ChatLieu(String maChatLieu, String tenChatLieu) {
        this.maChatLieu = maChatLieu;
        this.tenChatLieu = tenChatLieu;
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

    public Object[] toDataRow() {
        return new Object[]{this.maChatLieu, this.tenChatLieu};
    }
}
