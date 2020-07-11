package com.gsatechworld.musicapp.modules.fcm;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gsatechworld.musicapp.R;
import com.gsatechworld.musicapp.modules.home.HomeActivity;
import com.gsatechworld.musicapp.modules.login.LoginActivity;
import com.gsatechworld.musicapp.utilities.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.gsatechworld.musicapp.utilities.Constants.FCM_TOKEN;
import static com.gsatechworld.musicapp.utilities.Constants.MyPREFERENCES;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

   // private NotificationUtils notificationUtils;

    private String event_id;


    @SuppressLint("LongLogTag")
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d("MyFirebaseMessagingService", "inside data: ");
        if (remoteMessage == null)
            return;

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Notification Body: " + remoteMessage.getNotification().getBody());
            handleNotification(remoteMessage.getNotification().getBody());
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("MyFirebaseMessagingService", "inside data: ");
            Log.d(TAG, "Data Payload: " + remoteMessage.getData().toString());
            try {
                Log.e("MyFirebaseMessagingService", "inside data: try");
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                handleDataMessage(json);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }
    }

    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "FCM token: " + token);
        // Saving reg id to shared preferences
        storeRegIdInPref(token);
    }


    private void handleNotification(String message) {

        storeNotifications(new NotificationItem(message, message));
       // Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        //PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, null, 0);

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "100";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_MAX);

            //Configure Notification Channel
            notificationChannel.setDescription("Jyeshta");
            notificationChannel.enableLights(true);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(),
                NOTIFICATION_CHANNEL_ID)
                .setContentTitle(getApplicationContext().getString(R.string.app_name))
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle())
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setSmallIcon(R.drawable.ic_user)
                .setAutoCancel(true);

        if (notificationManager != null) {
            notificationManager.notify(1, notificationBuilder.build());
        }
    }

    @SuppressLint("LongLogTag")
    private void handleDataMessage(JSONObject json) {
        Log.e(TAG, "push json: " + json.toString());
        String title = null;
        String subTitle;
        int article_id = 0;
        String imageUrlLogo = null;
        String article_img = null;
        String imageUrlBanner = null;
        String article_desc = null;
        try {
            Log.e("MyFirebaseMessagingService", "inside data: handleDataMessage try");
            JSONObject data = json.getJSONObject("data");
            String identifier = data.getString("identifier");
//            if(identifier.equalsIgnoreCase(Constant.Article)){
//
//
//            } else {
//                title = data.getString("title");
//            }

            storeNotifications(new NotificationItem(title, "",
                    identifier, String.valueOf(article_id), article_img));

            Log.e(TAG, "title: " + title);
            Log.e(TAG, "imageUrlLogo: " + imageUrlLogo);
            Log.e(TAG, "imageUrlBanner: " + imageUrlBanner);

                // check for image attachment
            Intent intent = null;
                if(identifier.equalsIgnoreCase(Constants.StudentRequest)) {
                    Log.e("MyFirebaseMessagingService", "inside data: showNotificationForFollow");
                    // image is present, show notification with image
                   // intent = new Intent(getApplicationContext(), HomeActivity.class);
//                    intent.putExtra(Constant.ArticleId, article_id);
//                    intent.putExtra(Constant.ArticleTitle, title);
//                    intent.putExtra(Constant.ArticleImage, article_img);
//                    intent.putExtra(Constant.Article_desc, article_desc);
                } else if(identifier.equalsIgnoreCase(Constants.PaymentRequest)) {
                   // intent = new Intent(getApplicationContext(), HomeActivity.class);
                }

            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

            NotificationManager notificationManager = (NotificationManager) getApplicationContext().
                    getSystemService(Context.NOTIFICATION_SERVICE);
            String NOTIFICATION_CHANNEL_ID = "101";

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new
                        NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_MAX);

                //Configure Notification Channel
                notificationChannel.setDescription("Jyeshta");
                notificationChannel.enableLights(true);
                notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                notificationChannel.enableVibration(true);

                if (notificationManager != null) {
                    notificationManager.createNotificationChannel(notificationChannel);
                }
            }

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(),
                    NOTIFICATION_CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText(title)
                    .setContentIntent(pendingIntent)
                    .setWhen(System.currentTimeMillis())
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setStyle(new NotificationCompat.BigTextStyle())
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setSmallIcon(R.drawable.ic_user)
                    .setAutoCancel(true);


            if (notificationManager != null) {
                notificationManager.notify(1, notificationBuilder.build());
            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    private void storeRegIdInPref(String token) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(MyPREFERENCES, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(FCM_TOKEN, token);
        editor.commit();
    }

    private void storeNotifications(NotificationItem notificationItem) {
//        List<NotificationItem> notificationItemList = null;
//        SharedPreferences prefrence_notifi = getApplicationContext().getSharedPreferences(PREF_NAME, 0);
//        SharedPreferences prefrence = getApplicationContext().getSharedPreferences(PREF_NAME, 0);
//        if (notificationItem != null) {
//                Gson gson = new Gson();
//                String json = prefrence_notifi.getString(NotificationList, null);
//                Type type = new TypeToken<HashMap<String, List<NotificationItem>>>(){}.getType();
//                HashMap<String, List<NotificationItem>> notificationItemListMap = gson.fromJson(json, type);
//
//                if(notificationItemListMap != null){
//                    notificationItemList = notificationItemListMap.get(String.valueOf(prefrence.getInt("UserId",0)));
//                }
//
//                if(notificationItemList != null && notificationItemList.size() != 0){
//                    notificationItemList.add(notificationItem);
//                } else {
//                    notificationItemList= new ArrayList<>();
//                    notificationItemList.add(notificationItem);
//                }
//                HashMap<String, List<NotificationItem>> notificationMapListMap = new HashMap<String, List<NotificationItem>>();
//                notificationMapListMap.put(String.valueOf(prefrence.getInt("UserId",0)), notificationItemList);
//                SharedPreferences.Editor editor = prefrence_notifi.edit();
//                Gson gsonnew = new Gson();
//                String jsonNew = gsonnew.toJson(notificationMapListMap);
//                editor.putString(NotificationList, jsonNew);
//                editor.commit();
//        }
    }
}

