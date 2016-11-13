package com.metodista.pap.ssm.utils;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public final class AndroidUtil {

    public static final int EDIT_TEXT = 0;
    public static final int TEXT_VIEW = 1;

    public static String getTextStringFromField(int idField, int typeField, AppCompatActivity activity) {
        String textString = "";

        try {
            if (typeField == EDIT_TEXT) {
                textString = getTextStringEditText(idField, activity);
            } else if (typeField == TEXT_VIEW) {
                textString = getTextStringTextView(idField, activity);
            } else {
                textString = "";
            }

        } catch (Exception e) {
            textString = "";
        }

        return textString;
    }

    public static void setTextStringFromField(int idField, int typeField, String text, AppCompatActivity activity) {
        try {
            if (typeField == EDIT_TEXT) {
                setTextStringEditText(idField, text, activity);
            } else if (typeField == TEXT_VIEW) {
                setTextStringTextView(idField, text, activity);
            } else {
                return;
            }

        } catch (Exception e) {
            return;
        }
    }

    public static void showShortMessage(String msg, AppCompatActivity activity) {
        (Toast.makeText(activity, msg, Toast.LENGTH_SHORT)).show();
    }

    public static void showLongMessage(String msg, AppCompatActivity activity) {
        (Toast.makeText(activity, msg, Toast.LENGTH_LONG)).show();
    }

    private static String getTextStringEditText(int idField, AppCompatActivity activity) {
        return ((EditText) activity.findViewById(idField)).getText().toString();
    }

    private static String getTextStringTextView(int idField, AppCompatActivity activity) {
        return ((TextView) activity.findViewById(idField)).getText().toString();
    }

    private static void setTextStringEditText(int idField, String text, AppCompatActivity activity) {
        ((EditText) activity.findViewById(idField)).setText(text);
    }

    private static void setTextStringTextView(int idField, String text, AppCompatActivity activity) {
        ((TextView) activity.findViewById(idField)).setText(text);
    }
}
