package space.wanhu.www.recyclerviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import adapter.MyAdapter;
import utils.DividerItemDecoration;
import utils.ToastUtil;

public class MainActivity extends AppCompatActivity {
    //工具对象
    private MainActivity context;
    private List<String> mDatas;

    //布局对象
    private RecyclerView mRecyclerView;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
        initData();
        initViewOpe();
    }

    /**
     * 事件处理
     */
    private void initViewOpe() {

        //短按和长按提醒Item位置
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                ToastUtil.show(context, "点击了" + position);
            }

            @Override
            public void onLongClick(View view, int position) {
                ToastUtil.show(context, "长按了" + position);

            }
        });
    }

    /**
     * 处理数据逻辑
     */
    private void initData() {

        //获得数据源
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add((char) i + "");
        }

        //使用adapter
        adapter = new MyAdapter(context, mDatas);
        mRecyclerView.setAdapter(adapter);

        //设置RecyclerView的布局方向
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        //设置Item之间的分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL_LIST));

        //设置Item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 获取布局组件
     */
    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //获取菜单布局加载器
        MenuInflater inflater = context.getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu1://垂直ListView
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                break;
            case R.id.menu2://水平ListView
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                break;
            case R.id.menu3://水平GridView
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.menu4://垂直GridView
                mRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));
                break;
            case R.id.menu5://瀑布流布局
                startActivity(new Intent(context, ActivityForWaterfall.class));
                break;
            case R.id.delete://删除
                adapter.delete(1);
                break;
            case R.id.add://添加
                adapter.add(1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
