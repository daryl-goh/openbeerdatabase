package beerdb.application.beer.repositories;

import static beerdb.application.beer.repositories.Queries.SQL_SELECT_STYLES;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import beerdb.application.beer.models.BeerStyle;

@Repository
public class StylesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<BeerStyle> getStyles() {

        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECT_STYLES);

        List<BeerStyle> styles = new LinkedList<>();
        while (rs.next())
            styles.add(BeerStyle.create(rs));

        return styles;
    }
    
}