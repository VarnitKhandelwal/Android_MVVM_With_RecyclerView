package com.android.varnit.engineer_ai_assignment.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.varnit.engineer_ai_assignment.R;
import com.android.varnit.engineer_ai_assignment.adapter.UserAdapter;
import com.android.varnit.engineer_ai_assignment.databinding.ActivityMainBinding;
import com.android.varnit.engineer_ai_assignment.utils.EndlessRecyclerOnScrollListener;
import com.android.varnit.engineer_ai_assignment.viewmodel.MainViewModel;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private ActivityMainBinding activityMainBinding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setupUserList(activityMainBinding.userRecyclerView);
        setupObserver(mainViewModel);
    }

    private void initDataBinding() {
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this);
        activityMainBinding.setMainViewModel(mainViewModel);
    }

    private void setupUserList(RecyclerView userRecyclerView) {
        UserAdapter adapter = new UserAdapter();
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        userRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        userRecyclerView.setAdapter(adapter);

        userRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                if (mainViewModel.hasMore)
                    addDataToList();
            }
        });
    }

    private void addDataToList() {
        mainViewModel.fetchUserList();
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainViewModel.reset();
    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof MainViewModel) {
            UserAdapter userAdapter = (UserAdapter) activityMainBinding.userRecyclerView.getAdapter();
            MainViewModel mainViewModel = (MainViewModel) observable;
            userAdapter.setUserList(mainViewModel.getUserList());
        }
    }
}
