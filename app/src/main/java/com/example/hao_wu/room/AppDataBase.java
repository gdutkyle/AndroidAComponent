package com.example.hao_wu.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by hao_wu on 2018/7/28.
 */
@Database(entities = {User.class}, version = 1, exportSchema=false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UserDao userDao();
}
