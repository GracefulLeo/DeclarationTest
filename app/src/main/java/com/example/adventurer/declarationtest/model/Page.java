
package com.example.adventurer.declarationtest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Page {

    @SerializedName("batchSize")
    @Expose
    private Integer batchSize;
    @SerializedName("totalItems")
    @Expose
    private Integer totalItems;

}
