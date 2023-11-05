package app.unistat.service;

import app.unistat.model.Lector;
import java.util.List;

public interface LectorService {
    Lector add(Lector lector);

    List<String> globalSearch(String template);
}
