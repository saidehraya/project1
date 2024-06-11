package com.example.registerlogindemo;

import android.os.Parcel;
import android.os.Parcelable;

public class Car implements Parcelable {
    private String name;
    private int year;
    private int price;
    private String imageUrl;
    private int vehicleId;
    private String model;
    private String availableStart;
    private String availableEnd;
    private int ownerId;

    private int availability;

    protected Car(Parcel in) {
        // Read data from parcel
        name = in.readString();
        year = in.readInt();
        price = in.readInt();
        imageUrl = in.readString();
        vehicleId = in.readInt();
        model = in.readString();
        availableStart = in.readString();
        availableEnd = in.readString();
        ownerId = in.readInt();
        availability =in.readInt();
    }

    public Car() {
        // Default constructor required for calls to DataSnapshot.getValue(Car.class)
    }

    public Car(String name, int year, int price, String imageUrl, int vehicleId, String model, String availableStart, String availableEnd, int ownerId) {
        this.name = name;
        this.year = year;
        this.price = price;
        this.imageUrl = imageUrl;
        this.vehicleId = vehicleId;
        this.model = model;
        this.availableStart = availableStart;
        this.availableEnd = availableEnd;
        this.ownerId = ownerId;
        this.availability = availability;
    }

    public Car(String name, int year, int price, String imageUrl, int vehicleId, String model, String availableStart, String availableEnd, int ownerId, int availability) {
        this.name = name;
        this.year = year;
        this.price = price;
        this.imageUrl = imageUrl;
        this.vehicleId = vehicleId;
        this.model = model;
        this.availableStart = availableStart;
        this.availableEnd = availableEnd;
        this.ownerId = ownerId;
        this.availability = availability;
    }

    // Getter and Setter for availability
    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }


    // Getters and setters for all fields


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = (int) price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAvailableStart() {
        return availableStart;
    }

    public void setAvailableStart(String availableStart) {
        this.availableStart = availableStart;
    }

    public String getAvailableEnd() {
        return availableEnd;
    }

    public void setAvailableEnd(String availableEnd) {
        this.availableEnd = availableEnd;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    // Parcelable implementation

    public static final Parcelable.Creator<Car> CREATOR = new Parcelable.Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // Write data to parcel
        dest.writeString(name);
        dest.writeInt(year);
        dest.writeInt(price);
        dest.writeString(imageUrl);
        dest.writeInt(vehicleId);
        dest.writeString(model);
        dest.writeString(availableStart);
        dest.writeString(availableEnd);
        dest.writeInt(ownerId);
        dest.writeInt(availability);
    }
}
