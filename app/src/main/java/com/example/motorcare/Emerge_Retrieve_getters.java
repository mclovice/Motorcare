package com.example.motorcare;

public class Emerge_Retrieve_getters {

String Problem, Location, Contact;

    public Emerge_Retrieve_getters() {
    }

    public Emerge_Retrieve_getters(String emerge_problem, String emerge_location, String emerge_contact) {

        Problem = emerge_problem;
        Location = emerge_location;
        Contact = emerge_contact;

    }

        public String getProblem() {
            return Problem;
        }

        public void setProblem(String emerge_problem) {
            Problem = emerge_problem;
        }

        public String getLocation() {
            return Location;
        }

        public void setLocation(String emerge_location) {
            Location = emerge_location;
        }

        public String getContact() {
            return Contact;
        }

        public void setContact(String emerge_contact) {
            Contact = emerge_contact;
        }
    }



