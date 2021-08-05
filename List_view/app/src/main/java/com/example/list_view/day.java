package com.example.list_view;

public class day {

        private String day;
        private String description;
        private  int image;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }
        @Override
        public  String toString(){
            return day;
        }

        public day(String day, String description, int image) {
            this.day = day;
            this.description = description;
            this.image = image;
        }

    }

