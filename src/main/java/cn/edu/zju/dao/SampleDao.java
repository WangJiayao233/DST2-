package cn.edu.zju.dao;

import cn.edu.zju.dbutils.DBUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class SampleDao extends BaseDao {

    public int save(String uploadedBy) {
        AtomicInteger key = new AtomicInteger();
        DBUtils.execSQL(connection -> {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into sample(created_at, uploaded_by) values (?,?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setTimestamp(1, new Timestamp(new Date().getTime()));
                preparedStatement.setString(2, uploadedBy);
                key.set(preparedStatement.executeUpdate());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return key.get();
    }
}
