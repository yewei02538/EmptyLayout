package me.weyye.emptylayout;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.weyye.emptylayout.adapter.MyAdapter;
import me.weyye.library.EmptyLayout;

public class MainActivity extends Activity {

    private EmptyLayout emptyLayout;
    private RecyclerView recyclerView;
    private List<String> list = new ArrayList<>();
    private MyAdapter adapter;
    private SwipeRefreshLayout srl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadData();
    }

    private Handler mHandler = new Handler();


    private void initView() {
        emptyLayout = (EmptyLayout) findViewById(R.id.emptyLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        srl = (SwipeRefreshLayout) findViewById(R.id.srl);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter = new MyAdapter(list));
        //绑定
        emptyLayout.bindView(recyclerView);
        emptyLayout.setOnButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新加载数据
                loadData();
            }
        });
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(false);
                loadData();
            }
        });
    }

    private void loadData() {
        //模拟加载数据
        emptyLayout.showLoading("正在加载，请稍后");
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //为了防止重复调用
                mHandler.removeCallbacks(this);
                Random r = new Random();
                int res = r.nextInt(10);

                if (res % 2 == 0) {
                    // 失败
                    emptyLayout.showError("加载失败，点击重新加载"); // 显示失败
                } else {
                    // 成功
                    emptyLayout.showSuccess();
                    for (int i = 0; i < 15; i++) {
                        list.add("测试" + i);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        }, 3000);
    }
}
