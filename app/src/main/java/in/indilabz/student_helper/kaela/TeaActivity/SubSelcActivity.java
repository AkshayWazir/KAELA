package in.indilabz.student_helper.kaela.TeaActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.indilabz.student_helper.kaela.Interfaces.TeaSelecInter;
import in.indilabz.student_helper.kaela.R;
import in.indilabz.student_helper.kaela.TeaActivity.adapter.SubSelecAdapter;
import in.indilabz.student_helper.kaela.TeaActivity.moTea.ClasObjTea;
import in.indilabz.student_helper.kaela.TeaActivity.moTea.SubObj;

public class SubSelcActivity extends AppCompatActivity implements TeaSelecInter {
    ArrayList<SubObj> selSubs;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_selc);
        recyclerView = findViewById(R.id.id_tea_sel_rcv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SubSelecAdapter adapter = new SubSelecAdapter(getData(), this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    ArrayList<ClasObjTea> getData() {
        ArrayList<ClasObjTea> arrayList = new ArrayList<>();
        ArrayList<SubObj> subs = new ArrayList<>();
        SubObj sub1 = new SubObj();
        arrayList.add(new ClasObjTea("5", subs));
        return arrayList;
    }

    @Override
    public void addSub(String subId) {

    }

    @Override
    public void removeSub(String subId) {

    }
}
