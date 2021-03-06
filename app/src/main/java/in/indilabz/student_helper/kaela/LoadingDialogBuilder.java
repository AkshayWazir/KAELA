package in.indilabz.student_helper.kaela;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

public class LoadingDialogBuilder {
    Context ctx;
    AlertDialog alertDialog;

    public LoadingDialogBuilder(Context ctx) {
        this.ctx = ctx;
    }

    public void dialogRaise() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
        final View view1 = LayoutInflater.from(ctx).inflate(R.layout.layout_alert_progress, null);
        alert.setView(view1);
        alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    public void dialogDismiss(){
        alertDialog.dismiss();
    }
}
