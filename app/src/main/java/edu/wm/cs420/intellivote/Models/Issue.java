package edu.wm.cs420.intellivote.Models;

import java.io.Serializable;
import java.util.List;

public class Issue implements Serializable {

    public String name;
    public int icon;
    public String description;
    public String history;
    public List<String> candidates;
    public List<String> news;
    public float matchRate;
    public Boolean isFavorite;

    public Issue(String name, int icon, String description, String history, List<String> candidates, List<String> news, float matchRate, Boolean isFavorite) {
        this.name = name;
        this.icon = icon;
        this.description = description;
        this.history = history;
        this.candidates = candidates;
        this.news = news;
        this.matchRate = matchRate;
        this.isFavorite = isFavorite;
    }
}
