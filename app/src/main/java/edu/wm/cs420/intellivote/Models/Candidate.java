package edu.wm.cs420.intellivote.Models;

import java.io.Serializable;
import java.util.List;

public class Candidate implements Serializable {

    public String name;
    public int icon;
    public String description;
    public String bio;
    public List<String> issues;
    public List<String> news;
    public float matchRate;
    public Boolean isFavorite;

    public Candidate(String name, int icon, String description, String bio, List<String> issues, List<String> news, float matchRate, Boolean isFavorite) {
        this.name = name;
        this.icon = icon;
        this.description = description;
        this.bio = bio;
        this.issues = issues;
        this.news = news;
        this.matchRate = matchRate;
        this.isFavorite = isFavorite;
    }
}
