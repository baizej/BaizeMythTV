package org.baize.baizemythtv;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Navigation extends Activity {

    private static final String QUERY_URL = "http://rohan.baizehome.com:6547/Frontend/SendAction?Action=";
    private static final String TAG = Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonPress(View view) {
        String urlStr = null;
        switch (view.getId()) {
            case R.id.upButton:
                urlStr = QUERY_URL + "UP";
                break;
            case R.id.leftButton:
                urlStr = QUERY_URL + "LEFT";
                break;
            case R.id.selectButton:
                urlStr = QUERY_URL + "SELECT";
                break;
            case R.id.rightButton:
                urlStr = QUERY_URL + "RIGHT";
                break;
            case R.id.jumpRWNDButton:
                urlStr = QUERY_URL + "SEEKRWND";
                break;
            case R.id.downButton:
                urlStr = QUERY_URL + "DOWN";
                break;
            case R.id.jumpFFWDButton:
                urlStr = QUERY_URL + "SEEKFFWD";
                break;
            case R.id.backButton:
                urlStr = QUERY_URL + "ESCAPE";
                break;
            case R.id.playButton:
                urlStr = QUERY_URL + "PAUSE";
                break;
            case R.id.videosButton:
                urlStr = QUERY_URL + "Video+Default";
                break;
            case R.id.recordingsButton:
                urlStr = QUERY_URL + "TV+Recording+Playback";
                break;
            case R.id.zoneMinderButton:
                urlStr = QUERY_URL + "ZoneMinder+Live+View";
                break;
            case R.id.menuButton:
                urlStr = QUERY_URL + "MENU";
                break;
        }
        Log.d(TAG, urlStr);
        requestData(urlStr);

    }

    private void requestData(String uri){
        MyTask task = new MyTask();
        task.execute(uri);
    }

    private class MyTask extends AsyncTask<String, String, String> {

        protected String doInBackground(String... params){
            String content = HttpManager.getData(params[0]);
            return content;
        }

        protected void onPostExecute(String result){
            Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
        }
    }
}
