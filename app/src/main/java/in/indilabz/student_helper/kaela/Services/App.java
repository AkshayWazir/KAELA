package in.indilabz.student_helper.kaela.Services;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String channel_1_id = "1";

    @Override
    public void onCreate() {
        super.onCreate();
        createChannel();
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(
                    channel_1_id,
                    "Student Activty Reminder",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("User Student Activity Changed");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

}
