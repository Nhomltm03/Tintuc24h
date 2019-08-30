package com.example.tintuc24h.ui.main;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tintuc24h.MainActivity;
import com.example.tintuc24h.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment {

    Button buttonSearch;
    EditText edtSerach;
    TextView tvCity , tvStatus , tvSunrise, tvSunset,
             tvTemp, tvWind, tvHumidity, tvPresure;

    public WeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_weather, container, false);

        edtSerach = (EditText)rootView.findViewById(R.id.edtSearch);
        buttonSearch= (Button) rootView.findViewById(R.id.btnSearch);
        tvCity = (TextView) rootView.findViewById(R.id.tvCity);
        tvStatus = (TextView) rootView.findViewById(R.id.tvStatus);
        tvTemp = (TextView) rootView.findViewById(R.id.tvTemp);
        tvSunrise = (TextView) rootView.findViewById(R.id.sunrise);
        tvSunset = (TextView) rootView.findViewById(R.id.sunset);
        tvWind = (TextView) rootView.findViewById(R.id.wind);
        tvHumidity = (TextView) rootView.findViewById(R.id.humidity);
        tvPresure = (TextView) rootView.findViewById(R.id.pressure);
        getWeatherData("hanoi");

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = edtSerach.getText().toString();
                getWeatherData(city);

            }
        });


        return rootView;
    }


    private  void getWeatherData(final String data){

        RequestQueue requestQueue = Volley.newRequestQueue((MainActivity)getActivity());
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + data + "&units=metric&appid=27a77fcad68bf8df89e0124c586f0d30";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String cityName = jsonObject.getString("name");
                            tvCity.setText(cityName);
                            JSONArray jsonArrayweather = jsonObject.getJSONArray("weather");
                            JSONObject jsonObjectweather = jsonArrayweather.getJSONObject(0);
                            String status = jsonObjectweather.getString("description");
                            tvStatus.setText(status);

                            JSONObject jsonObjectwind = jsonObject.getJSONObject("wind");
                            String wind = jsonObjectwind.getString("speed");
                            tvWind.setText(wind + "m/s");

                            JSONObject jsonObjectSys = jsonObject.getJSONObject("sys");
                            String sunrise = jsonObjectSys.getString("sunrise");
                            String sunset = jsonObjectSys.getString("sunset");
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
                            long sunriseLong  = Long.valueOf(sunrise);
                            long sunsetLong = Long.valueOf(sunset);
                            Date sunriseDate = new Date( sunriseLong * 1000L);
                            Date sunsetDate = new Date( sunsetLong *1000L);
                            String formatSunrise = simpleDateFormat.format(sunriseDate);
                            String formatSunset = simpleDateFormat.format(sunsetDate);
                            tvSunrise.setText(formatSunrise);
                            tvSunset.setText(formatSunset);

                            JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                            String temp = jsonObjectMain.getString("temp");
                            tvTemp.setText(temp+"°C");

                            JSONObject jsonObjectHumidity = jsonObject.getJSONObject("main");
                            String humidity = jsonObjectHumidity.getString("humidity");
                            tvHumidity.setText(humidity+"%");

                            JSONObject jsonObjectPresure = jsonObject.getJSONObject("main");
                            String presure = jsonObjectPresure.getString("pressure");
                            tvPresure.setText(presure);

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getBaseContext(), "Không lấy được dữ liệu thời tiết",
                                Toast.LENGTH_SHORT).show();

                    }
                });
        requestQueue.add(stringRequest);

    }

}
