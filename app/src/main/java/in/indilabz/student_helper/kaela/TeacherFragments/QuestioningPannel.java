package in.indilabz.student_helper.kaela.TeacherFragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import in.indilabz.student_helper.kaela.Interfaces.QuesInter;
import in.indilabz.student_helper.kaela.ModelObjects.AdaUnsQueObj;
import in.indilabz.student_helper.kaela.Networking.MySingleton;
import in.indilabz.student_helper.kaela.PublicLinks;
import in.indilabz.student_helper.kaela.R;
import in.indilabz.student_helper.kaela.TeacherFragments.adptersTeach.AdaSolQue;
import in.indilabz.student_helper.kaela.TeacherFragments.adptersTeach.AdaUnsQue;

import static android.content.Context.MODE_PRIVATE;


public class QuestioningPannel extends Fragment {
    private RecyclerView unRecView, soRecView;
    private AdaSolQue ada1;
    private AdaUnsQue ada2;
    private QuesInter ctx;
    private BubbleNavigationConstraintView bottomNav;
    StringRequest stringRequest;

    public void setCtx(QuesInter ctx) {
        this.ctx = ctx;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questioning_pannel, container, false);
        unRecView = view.findViewById(R.id.id_unsolved);
        soRecView = view.findViewById(R.id.id_solved);
        bottomNav = view.findViewById(R.id.id_tea_ask_bar);

        soRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        unRecView.setLayoutManager(new LinearLayoutManager(getContext()));

        ada1 = new AdaSolQue(getContext());
        ada2 = new AdaUnsQue(getContext());

        ada1.setInteract(ctx);
        ada2.setInteract(ctx);

        unRecView.setAdapter(ada2);
        soRecView.setAdapter(ada1);


        SharedPreferences sharedPref = Objects.requireNonNull(getContext()).getSharedPreferences("USER", MODE_PRIVATE);
        String mail = sharedPref.getString("TEACH_ID", "");
        setupAdas(mail);

        if (savedInstanceState == null) {
            bottomNav.setCurrentActiveItem(0);
            unRecView.setVisibility(View.VISIBLE);
        }

        bottomNav.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (position) {
                    case (0):
                        unRecView.setVisibility(View.VISIBLE);
                        soRecView.setVisibility(View.GONE);
                        break;
                    case (1):
                        unRecView.setVisibility(View.GONE);
                        soRecView.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        return view;
    }

    private void setupAdas(final String teacherId) {
        for (int i =0;i<1;i++){
            System.out.println(i);
        }
        stringRequest = new StringRequest(Request.Method.POST, PublicLinks.SOLVED_UNSOLVED_FETCH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);

                            ArrayList<AdaUnsQueObj> unsolvQues = new ArrayList<>();
                            ArrayList<AdaUnsQueObj> solvQues = new ArrayList<>();

                            String success = jsonObject.getString("result");

                            if (!success.equals("-1")) {
                                for (int i = 0; i < jsonObject.getJSONArray("dat").length(); i++) {
                                    JSONObject obj = jsonObject.getJSONArray("dat").getJSONObject(i);
                                    if (!obj.getString("solved").equals("-1")) {
                                        solvQues.add(getObj(obj)); // returns the Object
                                    } else {
                                        unsolvQues.add(getObj(obj));
                                    }
                                }
                                ada1.setObjects(solvQues);
                                ada2.setObjects(unsolvQues);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getContext(), "Failed TO Load " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Try Again : " + error.getMessage(), Toast.LENGTH_LONG).show();
                        raiseAgain();
                    }
                }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
                    if (cacheEntry == null) {
                        cacheEntry = new Cache.Entry();
                    }
                    final long cacheHitButRefreshed = 3 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
                    final long cacheExpired =60 * 1000; // in 24 hours this cache entry expires completely
                    long now = System.currentTimeMillis();
                    final long softExpire = now + cacheHitButRefreshed;
                    final long ttl = now + cacheExpired;
                    cacheEntry.data = response.data;
                    cacheEntry.softTtl = softExpire;
                    cacheEntry.ttl = ttl;
                    String headerValue;
                    headerValue = response.headers.get("Date");
                    if (headerValue != null) {
                        cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    headerValue = response.headers.get("Last-Modified");
                    if (headerValue != null) {
                        cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    cacheEntry.responseHeaders = response.headers;
                    final String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(jsonString, cacheEntry);
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("teach_id", teacherId);
                params.put("CONTEXT","0");
                return params;
            }
        };

        MySingleton.getInstance(getContext().getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private AdaUnsQueObj getObj(JSONObject obj) throws JSONException {
        AdaUnsQueObj obj1 = new AdaUnsQueObj();
        obj1.setQues(obj.getString("ques_title"));
        obj1.setDesc(obj.getString("ques_desc"));
        obj1.setId(obj.getString("stu_id"));
        obj1.setQuesId(obj.getString("ques_id"));
        obj1.setName(obj.getString("stu_name"));
        return obj1;
    }

    void raiseAgain(){
        MySingleton.getInstance(getContext().getApplicationContext()).addToRequestQueue(stringRequest);
    }

}
