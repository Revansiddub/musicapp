package com.gsatechworld.musicapp.modules.fcm;

public class NotificationItem {

    String notificationType;
    String title;
    String subTitle;
    String image;
    String id;

    public NotificationItem(String title, String subTitle, String identifier, String id,
                            String image) {
        this.title = title;
        this.subTitle = subTitle;
        this.notificationType = identifier;
        this.id = id;
        this.image = image;
    }

    public NotificationItem(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }
}
