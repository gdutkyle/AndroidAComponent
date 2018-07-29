package com.example.hao_wu.room;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by hao_wu on 2018/7/28.
 */
@Dao
public interface UserDao {
    @Query("select * from user")
    List<User> loadAll();

    @Query("select * from user where uid in(:userids)")
    List<User> loadAllByUserId(int... userids);

    @Query("select * from user where userName like :name And last_name like :lastName ")
    User loadOneUserByNameAndLastName(String name, String lastName);

    @Insert
    void insertAll(User... users);

    @Delete
    void deleteUser(User user);


    @Query("DELETE FROM user")
    void deleteUserAll();

    @Query("SELECT * FROM user ORDER BY uid LIMIT 5")
    LiveData<List<User>> loadFiveUsers();
}
