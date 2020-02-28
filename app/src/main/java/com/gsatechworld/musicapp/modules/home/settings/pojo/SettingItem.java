package com.gsatechworld.musicapp.modules.home.settings.pojo;

public class SettingItem {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private String settingName;
    private int settingDrawableID;

    /* ------------------------------------------------------------- *
     * Constructor
     * ------------------------------------------------------------- */

    public SettingItem(String settingName, int settingDrawableID) {
        this.settingName = settingName;
        this.settingDrawableID = settingDrawableID;
    }

    /* ------------------------------------------------------------- *
     * Getters
     * ------------------------------------------------------------- */

    public String getSettingName() {
        return settingName;
    }

    public int getSettingDrawableID() {
        return settingDrawableID;
    }
}
