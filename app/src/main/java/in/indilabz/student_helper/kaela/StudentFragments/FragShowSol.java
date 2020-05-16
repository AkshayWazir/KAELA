package in.indilabz.student_helper.kaela.StudentFragments;

import android.content.Context;
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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import in.indilabz.student_helper.kaela.Interfaces.QuesInter;
import in.indilabz.student_helper.kaela.ModelObjects.AdaUnsQueObj;
import in.indilabz.student_helper.kaela.PublicLinks;
import in.indilabz.student_helper.kaela.R;
import in.indilabz.student_helper.kaela.TeacherFragments.adptersTeach.AdaUnsQue;

public class FragShowSol extends Fragment {
    private QuesInter ctx;
    private AdaUnsQue adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_frag_show_sol, container, false);
        RecyclerView recyclerView = layout.findViewById(R.id.id_stu_sol);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AdaUnsQue(getContext());
        adapter.setInteract(ctx);
        recyclerView.setAdapter(adapter);
        SharedPreferences preferences = getContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
        setupAdas(preferences.getString("EMAIL", ""));
        return layout;
    }

    public void setCtx(QuesInter ctx) {
        this.ctx = ctx;
    }

    private void setupAdas(final String studentId) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PublicLinks.SOLVED_UNSOLVED_FETCH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            ArrayList<AdaUnsQueObj> unsolvQues = new ArrayList<>();
                            String success = jsonObject.getString("result");
                            if (!success.equals("-1")) {
                                for (int i = 0; i < jsonObject.getJSONArray("dat").length(); i++) {
                                    JSONObject obj = jsonObject.getJSONArray("dat").getJSONObject(i);
                                    unsolvQues.add(getObj(obj));
                                }
                                adapter.setObjects(unsolvQues);
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
                    final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
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
                params.put("stu_id", studentId);
                params.put("CONTEXT", "1");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        requestQueue.add(stringRequest);
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
}
