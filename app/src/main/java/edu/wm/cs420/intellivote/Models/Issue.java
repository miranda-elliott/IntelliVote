package edu.wm.cs420.intellivote.Models;

import java.io.Serializable;
import java.util.List;

public class Issue implements Serializable {

    public String name;
    public String icon;
    public String description;
    public String history;
    public Boolean isFavorite;

    public Issue() {}
}
