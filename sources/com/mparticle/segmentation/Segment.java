package com.mparticle.segmentation;

import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;

public class Segment {
    String[] endpoints;

    /* renamed from: id */
    int f3813id;
    String name;

    public Segment(int id, String name2, String endpointBlob) {
        this.f3813id = id;
        this.name = name2;
        try {
            JSONArray jSONArray = new JSONArray(endpointBlob);
            this.endpoints = new String[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                this.endpoints[i] = jSONArray.getString(i);
            }
        } catch (JSONException e) {
        }
    }

    public int getId() {
        return this.f3813id;
    }

    public String getName() {
        return this.name;
    }

    public String[] getEndpoints() {
        if (this.endpoints != null) {
            return this.endpoints;
        }
        return new String[0];
    }

    public String toString() {
        return "Segment ID:  " + this.f3813id + ", " + "Name: " + this.name + ", " + "Endpoints: " + ((this.endpoints == null || this.endpoints.length <= 0) ? "None specified" : Arrays.toString(this.endpoints));
    }
}
