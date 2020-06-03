package in.indilabz.student_helper.kaela.Services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import in.indilabz.student_helper.kaela.R;
import in.indilabz.student_helper.kaela.TeacherMainScreen;

public class TeacherJobScheduler extends JobService {
    private boolean jobCancelled = false;
    String destination;

    @Override
    public boolean onStartJob(JobParameters params) {
        checkForChanges(params);
        destination = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        return true;
    }

    private void checkForChanges(final JobParameters jobParameters) {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            destination = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            FirebaseFirestore.getInstance()
                    .collection("EVENTS")
                    .document(destination)
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            if (e != null) {
                                Toast.makeText(TeacherJobScheduler.this, "Error Occured", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (documentSnapshot.exists()) {
                                boolean bool = documentSnapshot.getBoolean("NOTIFY");
                                if (bool) {
                                    Map<String, Object> map = new HashMap<>();
                                    map.put("NOTIFY", false);
                                    showNotification();
                                    FirebaseFirestore.getInstance()
                                            .collection("EVENTS")
                                            .document(destination)
                                            .set(map);
                                }
                            }
                        }
                    });
        }

    }

    private void showNotification() {
        Intent intent = new Intent(this, TeacherMainScreen.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
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


    @Override
    public boolean onStopJob(JobParameters params) {
        jobCancelled = true;
        return true;
    }
}
