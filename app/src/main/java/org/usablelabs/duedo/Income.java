package org.usablelabs.duedo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.Date;
import java.util.List;

@Table(name = "Tasks")
public class Income extends Model {

    @Column(name = "title")
    public String title;

    @Column(name = "content")
    public String content;

    @Column(name = "v8")
    public boolean v8;

    @Column(name = "v9")
    public boolean v9;

    @Column(name = "dueAt", index = true)
    public Date dueAt = null;

    @Column(name = "createdAt", index = true)
    public Date createdAt = null;

    @Column(name = "updatedAt", index = true)
    public Date updatedAt = null;

    public static List<Income> getAll() {
        return new Select().from(Income.class).orderBy("updatedAt DESC").execute();
    }

    public void saveWithTimestamp() {
        Date now = new Date();
        updatedAt = now;
        if (createdAt == null)
            createdAt = now;
        save();
    }
}
