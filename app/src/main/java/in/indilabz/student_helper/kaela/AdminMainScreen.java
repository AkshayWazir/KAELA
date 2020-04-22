package in.indilabz.student_helper.kaela;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import in.indilabz.student_helper.kaela.R;

public class AdminMainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_screen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_stu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.id_logout):
                SharedPreferences prefs = this.getSharedPreferences("USER", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("NAME", "");
                editor.putString("EMAIL", "");
                editor.putInt("TYPE", -1);
                editor.commit();
                startActivity(new Intent(this, IntroductionActivity.class));
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
