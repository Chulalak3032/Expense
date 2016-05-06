package org.usablelabs.duedo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.Date;
import java.util.List;

@Table(name = "Expense")
public class Expense extends Model {

    @Column(name = "date")
    public String date;

    @Column(name = "v1")
    public boolean v1;

    @Column(name = "v2")
    public boolean v2;

    @Column(name = "v3")
    public boolean v3;

    @Column(name = "v4")
    public boolean v4;

    @Column(name = "v5")
    public boolean v5;

    @Column(name = "v6")
    public String v6;

    @Column(name = "createdAt", index = true)
    public Date createdAt = null;

    @Column(name = "updatedAt", index = true)
    public Date updatedAt = null;

    public static List<Expense> getAll() {
        return new Select().from(Expense.class).orderBy("updatedAt DESC").execute();
    }

    public void saveWithTimestamp() {
        Date now = new Date();
        updatedAt = now;
        if (createdAt == null)
            createdAt = now;
        save();
    }
}
