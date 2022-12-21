show databases;

use openbeerdb

show tables;

select * from beers;
select * from breweries;
select * from breweries_geocode;
select * from categories;
select * from styles;

create view breweries_style as
	select s.id, s.style_name, br.name, br.website
		from breweries br
		join beers b
		on br.id = b.brewery_id
		join styles s
		on s.id = b.style_id
		order by br.name
        
drop view breweries_style;

select * from openbeerdb.breweries_style

select * from breweries_style
where id = 4;




