package edu.unicauca.api;

import edu.unicauca.models.Conductor;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiConductor {
    @GET("character/1")
    Call<Conductor> getData();
}
