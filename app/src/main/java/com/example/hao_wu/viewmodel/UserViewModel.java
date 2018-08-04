package com.example.hao_wu.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.hao_wu.room.AppDataBase;
import com.example.hao_wu.room.User;
import com.example.hao_wu.utils.APPUtils;

import java.util.List;

/**
 * Created by hao_wu on 2018/7/29.
 */

public class UserViewModel extends AndroidViewModel {
    public MutableLiveData<String> userLiveData = new MutableLiveData<>();
    private AppDataBase appDataBase;

    public UserViewModel(@NonNull Application application) {
        super(application);
        initDataBase();
    }


    public void initDataBase() {
        if (appDataBase == null) {
            appDataBase = Room.databaseBuilder(APPUtils.getContext(), AppDataBase.class, "database_name").build();
        }
    }

    public void loadFiveUsers(Activity activity) {
        appDataBase.userDao().loadFiveUsers().observe((LifecycleOwner) activity, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                if (users == null) {
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
                    sb.append("====================\n");
                    sb.append("uid:" + user.getUid() + "\n " + user.getUserName() + "\n" + user.getLastName() + "\n");
                    sb.append("\n");
                }
                userLiveData.setValue(sb.toString());
            }
        });
    }

    public void insertOneData() {
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... strings) {
                User user = new User();
                user.setUid((int) (System.currentTimeMillis()));
                user.setLastName("lastName-" + user.hashCode());
                user.setUserName("userName=" + user.hashCode());
                appDataBase.userDao().insertAll(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(APPUtils.getContext(), "insert success", Toast.LENGTH_SHORT).show();
            }
        }.execute();


    }

    public void deleteAllDatas() {
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... strings) {
                appDataBase.userDao().deleteUserAll();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }.execute();
    }

    public void queryAllDatas() {
        new AsyncTask<String, Void, Void>() {
            List<User> users = null;

            @Override
            protected Void doInBackground(String... strings) {
                users = appDataBase.userDao().loadAll();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (users == null) {
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
                    sb.append("====================\n");
                    sb.append("uid:" + user.getUid() + "\n " + user.getUserName() + "\n" + user.getLastName() + "\n");
                    sb.append("\n");
                }
                userLiveData.setValue(sb.toString());


            }
        }.execute();
    }

}
