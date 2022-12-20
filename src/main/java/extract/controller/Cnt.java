package extract.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class Cnt {

    @Autowired
    Gson gson;

}
