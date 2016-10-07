package com.example.administrator.pullrefresh;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List; 

public class MainActivity extends AppCompatActivity {
    private PullToRefreshListView lv;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (PullToRefreshListView) findViewById(R.id.mylv);

        List<String> arr =new ArrayList<String>();
        arr.add("jikexue");
        arr.add("joe");
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr);
        lv.setAdapter(adapter);
        //设置刷新监听事件
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new AsyncTask<Void,Void,Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }

                    //刷新出来数据，每次刷新都会出来新的数据
                    protected void onPostExecute(Void result){
                        adapter.addAll("hello","大家好！！！");
                        //通知listview加载完成了
                        lv.onRefreshComplete();
                    }

                }.execute();
            }
        });
    }
}
