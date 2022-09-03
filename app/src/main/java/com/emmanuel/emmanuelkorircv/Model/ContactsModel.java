package com.emmanuel.emmanuelkorircv.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactsModel implements Parcelable {
    //creating variables for our different fields.
    private String Name;
    private String Email;
    private String Date;
    private String Message;
    private String ContactId;
    private Long TimeStamp;




    //getters
    public String getName() {
        return Name;
    }
    public String getEmail() {
        return Email;
    }
    public String getMessage() {
        return Message;
    }
    public String getDate() {
        return Date;
    }
    public String getContactId() {
        return ContactId;
    }
    public Long getTimeStamp() {
        return TimeStamp;
    }


    //setters
    public void setName(String name) {
        this.Name = name;
    }
    public void setEmail(String email) {
        this.Email = email;
    }
    public void setDate(String date) {
        this.Date = date;
    }
    public void setMessage(String message) {
        this.Message = message;
    }
    public void setContactId(String contactid) {
        this.ContactId = contactid;
    }
    public void setTimeStamp(Long timeStamp) {
        this.TimeStamp = timeStamp;
    }


    //creating an empty constructor.
    public ContactsModel() {

    }

    protected ContactsModel(Parcel in) {
        Name = in.readString();
        Email = in.readString();
        Message = in.readString();
        Date = in.readString();
        ContactId= in.readString();
        TimeStamp= in.readLong();
    }

    public static final Creator<ContactsModel> CREATOR = new Creator<ContactsModel>() {
        @Override
        public ContactsModel createFromParcel(Parcel in) {
            return new ContactsModel(in);
        }

        @Override
        public ContactsModel[] newArray(int size) {
            return new ContactsModel[size];
        }
    };



    public ContactsModel(String Name, String Email, String Mobile_Number, String Location, String Services, String Date, String Message, String ContactId, Long TimeStamp)  {
        this.Name = Name;
        this.Email = Email;
        this.Message = Message;
        this.Date = Date;
        this.ContactId =ContactId;
        this.TimeStamp = TimeStamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Email);
        dest.writeString(Message);
        dest.writeString(Date);
        dest.writeString(ContactId);
        dest.writeLong(TimeStamp);
    }
}
