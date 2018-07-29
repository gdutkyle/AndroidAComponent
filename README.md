# 谷歌开源的Android Architecture Compontents Demo
这个小demo主要是用于实践AAC框架。  
主要结构是：ViewModel+LiveData+Room  
功能点：
1 插入一条数据  
![插入数据后的界面展示](https://i.imgur.com/o1aZp84.png)  
2 清空数据  
![清空数据](https://i.imgur.com/V3hTZ4V.png)  
3 查询所有的数据  
![查询所有数据](https://i.imgur.com/SDpHzFn.png)  

最出彩的地方：  

     @Query("SELECT * FROM user ORDER BY uid LIMIT 5")  
    LiveData<List<User>> loadFiveUsers();

通过loadFiveUsers()方法可以返回当前的数据库前五的数据项，所以，当这个数据和liveData绑定的时候，只要数据库里面的数据发生改变，那么就会触发onchange，把数据库最新的状态返回给view层。完美！！！  

    viewModel.loadFiveUsers().observe(MainActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
                    sb.append("====================\n");
                    sb.append("uid:" + user.getUid() + "\n " + user.getUserName() + "\n" + user.getLastName() + "\n");
                    sb.append("\n");
                }
                tv_values.setText(sb.toString());
            }
        });  
