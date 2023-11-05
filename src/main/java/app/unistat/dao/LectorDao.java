package app.unistat.dao;

import app.unistat.model.Lector;

import java.util.List;

public interface LectorDao {
    Lector add(Lector lector);

    List<String> globalSearch(String template);
}
