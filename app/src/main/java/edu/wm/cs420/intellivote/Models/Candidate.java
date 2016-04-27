package edu.wm.cs420.intellivote.Models;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.List;

import edu.wm.cs420.intellivote.R;

public class Candidate implements Serializable {

    public String name;
    public String icon;
    public String description;
    public String bio;
    public float matchRate;
    public Boolean isFavorite;

    public Boolean abortionMatch;
    public String abortionDescription;
    public String abortionFull;

    public Boolean gunMatch;
    public String gunDescription;
    public String gunFull;

    public Boolean marriageMatch;
    public String marriageDescription;
    public String marriageFull;

    public Boolean energyMatch;
    public String energyDescription;
    public String energyFull;

    public Boolean healthMatch;
    public String healthDescription;
    public String healthFull;

    public Candidate() {}
}
