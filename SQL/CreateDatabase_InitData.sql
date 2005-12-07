use inetvod

--//////////////////////////////////////////////////////////////////////////////

delete from RentedShow
delete from ShowProvider
delete from ShowCategory
delete from Show
delete from Provider
delete from Category
delete from Rating
delete from SerialNumber
delete from MemberPrefs
delete from Member
go

--//////////////////////////////////////////////////////////////////////////////

insert into Provider (ProviderID, Name, RequestURL, AdminUserID, AdminPassword)
values ('internetvideos', 'Internet Videos', 'http://api.inetvod.com/provider_internetvideos/providerapi', 'super', 'superpassword')

insert into Provider (ProviderID, Name, RequestURL, AdminUserID, AdminPassword)
values ('moviesmovies', 'Movies, Movies', 'http://api.inetvod.com/provider_moviesmovies/providerapi', 'super', 'superpassword')

insert into Provider (ProviderID, Name, RequestURL, AdminUserID, AdminPassword)
values ('vodflicks', 'VOD Flicks', 'http://api.inetvod.com/provider_vodflicks/providerapi', 'super', 'superpassword')

insert into Provider (ProviderID, Name, RequestURL, AdminUserID, AdminPassword)
values ('excellentvideos', 'Excellent Videos', 'http://api.inetvod.com/provider_excellentvideos/providerapi', 'super', 'superpassword')

insert into Provider (ProviderID, Name, RequestURL, AdminUserID, AdminPassword)
values ('mlb', 'MLB.com', 'http://api.inetvod.com/provider_mlb/providerapi', 'super', 'superpassword')

go

--//////////////////////////////////////////////////////////////////////////////

insert into Category (CategoryID, Name)
values ('action', 'Action')
insert into Category (CategoryID, Name)
values ('classics', 'Classics')
insert into Category (CategoryID, Name)
values ('comedy', 'Comedy')
insert into Category (CategoryID, Name)
values ('drama', 'Drama')
insert into Category (CategoryID, Name)
values ('family', 'Family')
insert into Category (CategoryID, Name)
values ('featured', 'Featured')
insert into Category (CategoryID, Name)
values ('romance', 'Romance')
insert into Category (CategoryID, Name)
values ('scifi', 'Sci-Fi')
insert into Category (CategoryID, Name)
values ('sports', 'Sports')
insert into Category (CategoryID, Name)
values ('tv', 'TV')
insert into Category (CategoryID, Name)
values ('thriller', 'Thriller')
insert into Category (CategoryID, Name)
values ('trailers', 'Trailers')
insert into Category (CategoryID, Name)
values ('western', 'Western')
go

--//////////////////////////////////////////////////////////////////////////////

insert into Rating (RatingID, Name)
values ('g', 'G')
insert into Rating (RatingID, Name)
values ('pg', 'PG')
insert into Rating (RatingID, Name)
values ('pg13', 'PG-13')
insert into Rating (RatingID, Name)
values ('r', 'R')
insert into Rating (RatingID, Name)
values ('nc17', 'NC-17')
insert into Rating (RatingID, Name)
values ('tv7', 'TV-7')
insert into Rating (RatingID, Name)
values ('tvy7', 'TV-Y7')
insert into Rating (RatingID, Name)
values ('tvy7fv', 'TV-Y7-FV')
insert into Rating (RatingID, Name)
values ('tvg', 'TV-G')
insert into Rating (RatingID, Name)
values ('tvpg', 'TV-PG')
insert into Rating (RatingID, Name)
values ('tv14', 'TV-14')
insert into Rating (RatingID, Name)
values ('tvma', 'TV-MA')
go

--//////////////////////////////////////////////////////////////////////////////

insert Member (MemberID, FirstName, LastName, AddrStreet1, AddrStreet2, City, State, PostalCode, Country, Phone, BirthDate)
values ('f2c3e739-85c9-4b61-b906-230986c656c5', 'Robert', 'Davidson', '1000 Hoy Cir.', null, 'Collegeville', 'PA', '19426', 'USA', '610-489-4459', '1966-10-27')
go

--//////////////////////////////////////////////////////////////////////////////

insert MemberPrefs (MemberID, IncludeAdult)
values ('f2c3e739-85c9-4b61-b906-230986c656c5', 'PromptPassword')
go

--//////////////////////////////////////////////////////////////////////////////

insert into SerialNumber (SerialNumberID, Active, MemberID, PIN)
values ('1', 1, 'f2c3e739-85c9-4b61-b906-230986c656c5', '123456')
go

--//////////////////////////////////////////////////////////////////////////////
