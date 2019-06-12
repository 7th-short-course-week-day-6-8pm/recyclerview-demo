package com.rathana.recyclerveiwdemo.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

public class Inbox implements Parcelable {

    private int id;
    private String contact;
    private String name;
    private String content;
    private String date;
    private @DrawableRes int starIcon;

    private String subject;


    public Inbox() {}

    public Inbox(String contact,String subject, String content,
                 String date,@DrawableRes int startIcon) {
        this.contact = contact;
        this.subject=subject;
        this.content = content;
        this.date = date;
        this.starIcon=startIcon;
    }

    protected Inbox(Parcel in) {
        id = in.readInt();
        contact = in.readString();
        name = in.readString();
        content = in.readString();
        date = in.readString();
        subject = in.readString();
        starIcon=in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(contact);
        dest.writeString(name);
        dest.writeString(content);
        dest.writeString(date);
        dest.writeString(subject);
        dest.writeInt(starIcon);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Inbox> CREATOR = new Creator<Inbox>() {
        @Override
        public Inbox createFromParcel(Parcel in) {
            return new Inbox(in);
        }

        @Override
        public Inbox[] newArray(int size) {
            return new Inbox[size];
        }
    };

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStarIcon() {
        return starIcon;
    }

    public void setStarIcon(int starIcon) {
        this.starIcon = starIcon;
    }
}
