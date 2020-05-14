package edu.miu.cs545.group01.online.market.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ReviewModel {
    @NotBlank(message = "{reviewmodel.comment}")
    private String comment;
    @Min(value = 1, message = "{reviewmodel.stars}")
    @Max(value = 5, message = "{reviewmodel.stars}")
    private int stars;

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
