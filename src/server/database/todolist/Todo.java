package server.database.todolist;

import java.io.Serializable;
import java.util.Date;

public class Todo implements Serializable {
        private static final long serialVersionUID = 1L;
        private int id;
        private Date date;
        private String status;
        private String item;


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



    public Todo(){}

    public Todo(int id, String item){
            this.id = id;
            this.item = item;
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

        @Override
        public String toString(){
            return item + ", " + status + ", " + date;
        }

}
