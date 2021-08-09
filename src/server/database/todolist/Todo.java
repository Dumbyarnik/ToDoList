package server.database.todolist;
/*
 * Class created on 30.07.2021
 * Class is used to store information about todo
 * */

import java.io.Serializable;
import java.util.Date;

public class Todo implements Serializable {
        private static final long serialVersionUID = 1L;
        private int id;
        private Date date;
        private String status;
        private String item;
        private long days;

        public Todo(){}

        public Todo(int id, String item, String status, Date date,long days){
        this.id = id;
        this.item = item;
        this.status = status;
        this.date = date;
        this.days=days;
    }

        public String getStatus() {
        return status;
    }
        public void setStatus(String status) {
        this.status = status;
    }

        public Date getDate() {
        return date;
    }
        public void setDate(Date date) {
        this.date = date;
    }

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }

        public String getItem() {
            return item;
        }
        public void setItem(String item) {
            this.item = item;
        }

        public long getDays() { return days; }
        public void setDays(long days) { this.days = days; }

        @Override
        public String toString(){
            return id + ", " + item + ", " + status + ", " + date;
        }
}
