package beerdb.application.beer.repositories;

public class Queries {

    public static final String SQL_SELECT_STYLES = "select id, style_name from styles order by style_name asc";
    public static final String SQL_SELECT_BREWERIES_BY_STYLE = "select * from breweries_style where id = ?";
    
}