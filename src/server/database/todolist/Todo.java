package server.database.todolist;

import java.io.Serializable;

public class Todo implements Serializable {
        private static final long serialVersionUID = 1L;
        private int id;
        private String item;

        public Todo(){}

        public int getID() {
            return id;
        }

        public void setID(int id) {
            this.id = id;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }
}
