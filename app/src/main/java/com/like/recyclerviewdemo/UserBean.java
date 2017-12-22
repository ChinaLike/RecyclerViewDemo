package com.like.recyclerviewdemo;

import android.os.Parcel;

import com.felipecsl.asymmetricgridview.AsymmetricItem;

/**
 * Created by like on 2017/12/21.
 */

public class UserBean implements AsymmetricItem {
    private String url;

    private int columnSpan;
    private int rowSpan;
    private int position;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "url='" + url + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public int getColumnSpan() {
        return columnSpan;
    }

    @Override
    public int getRowSpan() {
        return rowSpan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(columnSpan);
        parcel.writeInt(rowSpan);
        parcel.writeInt(position);
    }
}
