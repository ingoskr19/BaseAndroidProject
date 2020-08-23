package co.com.android.baseproject.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

import dagger.hilt.android.qualifiers.ActivityContext;

import static android.content.Context.MODE_PRIVATE;
import static co.com.android.baseproject.util.Constants.PREFERENCE_NAME;

/**
 * Created By oscar.vergara on 8/08/2020
 */
public class PreferencesUtil {

    private Context activity;
    private static SharedPreferences sharedPreferences;

    public PreferencesUtil(@ActivityContext Context context) {
        this.activity = context;
    }

    @SuppressWarnings({"unchecked"})
    public <T> void setPreference(String name, T value){
        sharedPreferences = activity.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);

        if(sharedPreferences.contains(name)){
            sharedPreferences.edit().remove(name).apply();
        }

        if(value instanceof Boolean) {
            sharedPreferences.edit().putBoolean(name, (Boolean) value).apply();
        } else if(value instanceof String) {
            sharedPreferences.edit().putString(name, (String) value).apply();
        } else if(value instanceof Long) {
            sharedPreferences.edit().putLong(name, (Long) value).apply();
        } else if(value instanceof Integer) {
            sharedPreferences.edit().putInt(name, (Integer) value).apply();
        } else if(value instanceof Float) {
            sharedPreferences.edit().putFloat(name, (Float) value).apply();
        } else if(value instanceof Set) {
            sharedPreferences.edit().putStringSet(name, (Set<String>) value).apply();
        }
    }

    @SuppressWarnings({"unchecked"})
    public <T> Object getPreference(String name, T defaultValue){
        sharedPreferences = activity.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);

        if(defaultValue instanceof Boolean) {
            return sharedPreferences.getBoolean(name, (Boolean) defaultValue);
        } else if(defaultValue instanceof String) {
            return sharedPreferences.getString(name, (String) defaultValue);
        } else if(defaultValue instanceof Long) {
            return Long.getLong(String.valueOf(sharedPreferences.getLong(name, (Long) defaultValue)));
        } else if(defaultValue instanceof Integer) {
            return Integer.getInteger(String.valueOf(sharedPreferences.getInt(name, (Integer) defaultValue)));
        } else if(defaultValue instanceof Float) {
            return sharedPreferences.getFloat(name, (Float) defaultValue);
        } else if(defaultValue instanceof Set) {
            return sharedPreferences.getStringSet(name, (Set<String>) defaultValue);
        }
        return null;
    }

    public void removePreference(String name){
        sharedPreferences = activity.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        sharedPreferences.edit().remove(name).apply();
    }

}
