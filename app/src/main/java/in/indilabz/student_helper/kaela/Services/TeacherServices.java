package in.indilabz.student_helper.kaela.Services;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import in.indilabz.student_helper.kaela.R;
import in.indilabz.student_helper.kaela.TeacherMainScreen;

public class TeacherServices extends JobIntentService {

    private String check_destination;

    public static void enqueWork(Context context, Intent intent) {
        enqueueWork(context, TeacherServices.class, 1234, intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public boolean onStopCurrentWork() {
        return true;
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        check_destination = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        FirebaseFirestore.getInstance()
                .collection("EVENTS")
                .document(check_destination)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Toast.makeText(TeacherServices.this, "Error Occured", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (documentSnapshot.exists()) {
                            boolean bool = documentSnapshot.getBoolean("NOTIFY");
                            if (bool) {

                                Map<String, Object> map = new HashMap<>();
                                map.put("NOTIFY", false);

                                FirebaseFirestore
                                        .getInstance()
                                        .collection("EVENTS")
                                        .document(check_destination)
                                        .set(map)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                showNotification();
                                            }
                                        });
                            }
                        }
                    }
                });
    }

    private void showNotification() {
        Intent intent = new Intent(this, TeacherMainScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManagerCompat nmc = NotificationManagerCompat.from(getApplicationContext());
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        long[] vibrate = {0, 100, 200, 300};

        Notification notification = new NotificationCompat.Builder(getApplicationContext(), "1")
                .setSmallIcon(R.drawable.ic_mail)
                .setContentTitle("New Question")
                .setContentText("A new Question has just been asked by you")
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .setVibrate(vibrate)
                .setSound(alarmSound)
                .setContentIntent(contentIntent)
                .build();

        nmc.notify(1, notification);
    }
}
