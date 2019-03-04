package mg.studio.weatherappdesign;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DownloadUpdate().execute();
    }

    public void btnClick(View view) {
        new DownloadUpdate().execute();
    }


    private class DownloadUpdate extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            String stringUrl = "https://api.seniverse.com/v3/weather/daily.json?key=drjblkkesptxtlir&location=chongqing&language=en&unit=c&start=0&days=7";
            HttpURLConnection urlConnection = null;
            BufferedReader reader;

            try {
                URL url = new URL(stringUrl);

                // Create the request to get the information from the server, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Mainly needed for debugging
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                //The temperature
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String weather_conditions) {
            if(weather_conditions==null||weather_conditions=="")
            {
                return;
            }
            //Update the weather conditions displayed
            int startIndex=0;
            int day0= weather_conditions.indexOf("date",startIndex);
            startIndex=day0+1;
            int day1= weather_conditions.indexOf("date",startIndex);
            startIndex=day1+1;
            int day2= weather_conditions.indexOf("date",startIndex);
            startIndex=day2+1;
            int day3= weather_conditions.indexOf("date",startIndex);
            startIndex=day3+1;
            int day4= weather_conditions.indexOf("date",startIndex);
            startIndex=day4+1;
            int day5= weather_conditions.indexOf("date",startIndex);
            startIndex=day5+1;
            int day6= weather_conditions.indexOf("date",startIndex);
            startIndex=day6+1;

            int tem_begin0=weather_conditions.indexOf("high",day0)+7;
            int tem_end0=weather_conditions.indexOf("low",day0)-3;
            String s="";

            if(day0!=-1&&day1!=-1&&day2!=-1) {
                ((TextView) findViewById(R.id.tv_location)).setText(weather_conditions.substring(weather_conditions.indexOf("name") + 7, weather_conditions.indexOf("country") - 3));
                ((TextView) findViewById(R.id.tv_date)).setText(weather_conditions.substring(weather_conditions.indexOf("date") + 7, weather_conditions.indexOf("text_day") - 3));
                ((TextView) findViewById(R.id.temperature_of_the_day)).setText(weather_conditions.substring(tem_begin0, tem_end0));

                s = weather_conditions.substring(weather_conditions.indexOf("text_day", day0) + 11, weather_conditions.indexOf("code_day", day0) - 3);
                if ((s.indexOf("Cloud") != -1) || s.indexOf("cloud") != -1 || s.indexOf("overcast") != -1 || s.indexOf("Overcast") != -1) {
                    ((ImageView) findViewById(R.id.img_weather_condition)).setImageDrawable(getResources().getDrawable(R.drawable.partly_sunny_small));
                } else if ((s.indexOf("Rain") != -1) || s.indexOf("rain") != -1) {
                    ((ImageView) findViewById(R.id.img_weather_condition)).setImageDrawable(getResources().getDrawable(R.drawable.rainy_small));
                } else if ((s.indexOf("Sunny") != -1) || s.indexOf("sunny") != -1) {
                    ((ImageView) findViewById(R.id.img_weather_condition)).setImageDrawable(getResources().getDrawable(R.drawable.sunny_small));
                } else {
                    ((ImageView) findViewById(R.id.img_weather_condition)).setImageDrawable(getResources().getDrawable(R.drawable.unkown_small));
                }

                s = weather_conditions.substring(weather_conditions.indexOf("text_day", day1) + 11, weather_conditions.indexOf("code_day", day1) - 3);
                if ((s.indexOf("Cloud") != -1) || s.indexOf("cloud") != -1 || s.indexOf("overcast") != -1 || s.indexOf("Overcast") != -1) {
                    ((ImageView) findViewById(R.id.ima_day1)).setImageDrawable(getResources().getDrawable(R.drawable.partly_sunny_small));
                } else if ((s.indexOf("Rain") != -1) || s.indexOf("rain") != -1) {
                    ((ImageView) findViewById(R.id.ima_day1)).setImageDrawable(getResources().getDrawable(R.drawable.rainy_small));
                } else if ((s.indexOf("Sunny") != -1) || s.indexOf("sunny") != -1) {
                    ((ImageView) findViewById(R.id.ima_day1)).setImageDrawable(getResources().getDrawable(R.drawable.sunny_small));
                } else {
                    ((ImageView) findViewById(R.id.ima_day1)).setImageDrawable(getResources().getDrawable(R.drawable.unkown_small));
                }

                s = weather_conditions.substring(weather_conditions.indexOf("text_day", day2) + 11, weather_conditions.indexOf("code_day", day2) - 3);
                if ((s.indexOf("Cloud") != -1) || s.indexOf("cloud") != -1 || s.indexOf("overcast") != -1 || s.indexOf("Overcast") != -1) {
                    ((ImageView) findViewById(R.id.ima_day2)).setImageDrawable(getResources().getDrawable(R.drawable.partly_sunny_small));
                } else if ((s.indexOf("Rain") != -1) || s.indexOf("rain") != -1) {
                    ((ImageView) findViewById(R.id.ima_day2)).setImageDrawable(getResources().getDrawable(R.drawable.rainy_small));
                } else if ((s.indexOf("Sunny") != -1) || s.indexOf("sunny") != -1) {
                    ((ImageView) findViewById(R.id.ima_day2)).setImageDrawable(getResources().getDrawable(R.drawable.sunny_small));
                } else {
                    ((ImageView) findViewById(R.id.ima_day2)).setImageDrawable(getResources().getDrawable(R.drawable.unkown_small));
                }
            }
            if(day3!=-1&&day4!=-1) {
                s = weather_conditions.substring(weather_conditions.indexOf("text_day", day3) + 11, weather_conditions.indexOf("code_day", day3) - 3);
                if ((s.indexOf("Cloud") != -1) || s.indexOf("cloud") != -1 || s.indexOf("overcast") != -1 || s.indexOf("Overcast") != -1) {
                    ((ImageView) findViewById(R.id.ima_day3)).setImageDrawable(getResources().getDrawable(R.drawable.partly_sunny_small));
                } else if ((s.indexOf("Rain") != -1) || s.indexOf("rain") != -1) {
                    ((ImageView) findViewById(R.id.ima_day3)).setImageDrawable(getResources().getDrawable(R.drawable.rainy_small));
                } else if ((s.indexOf("Sunny") != -1) || s.indexOf("sunny") != -1) {
                    ((ImageView) findViewById(R.id.ima_day3)).setImageDrawable(getResources().getDrawable(R.drawable.sunny_small));
                } else {
                    ((ImageView) findViewById(R.id.ima_day3)).setImageDrawable(getResources().getDrawable(R.drawable.unkown_small));
                }

                s = weather_conditions.substring(weather_conditions.indexOf("text_day", day4) + 11, weather_conditions.indexOf("code_day", day4) - 3);
                if ((s.indexOf("Cloud") != -1) || s.indexOf("cloud") != -1 || s.indexOf("overcast") != -1 || s.indexOf("Overcast") != -1) {
                    ((ImageView) findViewById(R.id.ima_day4)).setImageDrawable(getResources().getDrawable(R.drawable.partly_sunny_small));
                } else if ((s.indexOf("Rain") != -1) || s.indexOf("rain") != -1) {
                    ((ImageView) findViewById(R.id.ima_day4)).setImageDrawable(getResources().getDrawable(R.drawable.rainy_small));
                } else if ((s.indexOf("Sunny") != -1) || s.indexOf("sunny") != -1) {
                    ((ImageView) findViewById(R.id.ima_day4)).setImageDrawable(getResources().getDrawable(R.drawable.sunny_small));
                } else {
                    ((ImageView) findViewById(R.id.ima_day4)).setImageDrawable(getResources().getDrawable(R.drawable.unkown_small));
                }
            }

            String[] week = {"SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"};
            String day="";
            int dayIndex=0;
            Date date=null;
            Calendar calendar=null;
            if(day0!=-1&&day1!=-1&&day2!=-1) {
                date = new Date(Date.parse(weather_conditions.substring(weather_conditions.indexOf("date", day0) + 7, weather_conditions.indexOf("text_day", day0) - 3).replace('-', '/')));
                calendar = Calendar.getInstance();
                calendar.setTime(date);
                dayIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                day = week[dayIndex];
                ((TextView) findViewById(R.id.text_today)).setText(day);

                date = new Date(Date.parse(weather_conditions.substring(weather_conditions.indexOf("date", day1) + 7, weather_conditions.indexOf("text_day", day1) - 3).replace('-', '/')));
                calendar.setTime(date);
                dayIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                day = week[dayIndex];
                ((TextView) findViewById(R.id.text_day1)).setText(day);

                date = new Date(Date.parse(weather_conditions.substring(weather_conditions.indexOf("date", day2) + 7, weather_conditions.indexOf("text_day", day2) - 3).replace('-', '/')));
                calendar.setTime(date);
                dayIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                day = week[dayIndex];
                ((TextView) findViewById(R.id.text_day2)).setText(day);
            }
            if(day3!=-1&&day4!=-1) {
                date = new Date(Date.parse(weather_conditions.substring(weather_conditions.indexOf("date", day3) + 7, weather_conditions.indexOf("text_day", day3) - 3).replace('-', '/')));
                calendar.setTime(date);
                dayIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                day = week[dayIndex];
                ((TextView) findViewById(R.id.text_day3)).setText(day);

                date = new Date(Date.parse(weather_conditions.substring(weather_conditions.indexOf("date", day4) + 7, weather_conditions.indexOf("text_day", day4) - 3).replace('-', '/')));
                calendar.setTime(date);
                dayIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                day = week[dayIndex];
                ((TextView) findViewById(R.id.text_day4)).setText(day);
            }
            Toast toast=new Toast(MainActivity.this);
            toast=Toast.makeText(MainActivity.this,"",Toast.LENGTH_LONG);
            if(day0!=-1&&day1!=-1&&day2!=-1)
            {
                toast.setText("The weather conditions was updated successfully!");
            }
            else
            {
                toast.setText("The weather conditions update failed!");
            }
            toast.show();
        }
    }
}
