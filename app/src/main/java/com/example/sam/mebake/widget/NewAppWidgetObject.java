package com.example.sam.mebake.widget;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sam on 1/5/18.
 */

public class NewAppWidgetObject implements Parcelable{

    private String widgetString;

    public NewAppWidgetObject(String widgetString){
        this.widgetString = widgetString;}

    public String getWidgetString(){return widgetString;}


    protected NewAppWidgetObject(Parcel in) {
        widgetString = in.readString();
    }

    @Override

    public int describeContents() {return 0;}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(widgetString);
    }

    public static final Creator<NewAppWidgetObject> CREATOR = new Creator<NewAppWidgetObject>() {
        @Override
        public NewAppWidgetObject createFromParcel(Parcel in) {
            return new NewAppWidgetObject(in);
        }

        @Override
        public NewAppWidgetObject[] newArray(int size) {
            return new NewAppWidgetObject[size];
        }
    };


}
