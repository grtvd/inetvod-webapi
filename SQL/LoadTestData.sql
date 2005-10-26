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

insert into Provider (ProviderID, Name)
values ('internetvideos', 'Internet Videos')
insert into Provider (ProviderID, Name)
values ('moviesmovies', 'Movies, Movies')
insert into Provider (ProviderID, Name)
values ('vodflicks', 'VOD Flicks')
insert into Provider (ProviderID, Name)
values ('excellentvideos', 'Excellent Videos')

--insert into Provider (ProviderID, Name)
--values ('cinemanow', 'CinemaNow')
--insert into Provider (ProviderID, Name)
--values ('movieflix', 'MovieFlix')
--insert into Provider (ProviderID, Name)
--values ('movielink', 'MoiveLink')
--insert into Provider (ProviderID, Name)
--values ('divxopen', 'DivX Open Video System')
--insert into Provider (ProviderID, Name)
--values ('divxafter', 'DivX After Dark')
--insert into Provider (ProviderID, Name)
--values ('vanguard', 'Vanguard Indie Films')
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
values ('987654321', 'Robert', 'Davidson', '1000 Hoy Cir.', null, 'Collegeville', 'PA', '19426', 'USA', '610-489-4459', '1966-10-27')
go

--//////////////////////////////////////////////////////////////////////////////

insert MemberPrefs (MemberID, IncludeAdult)
values ('987654321', 'PromptPassword')
go

--//////////////////////////////////////////////////////////////////////////////

insert into SerialNumber (SerialNumberID, Active, MemberID, PIN)
values ('A', 1, '987654321', '123456')
insert into SerialNumber (SerialNumberID, Active, MemberID, PIN)
values ('B', 0, null, null)
insert into SerialNumber (SerialNumberID, Active, MemberID, PIN)
values ('C', 0, null, null)
go

--//////////////////////////////////////////////////////////////////////////////

insert into Show (ShowID, Name, EpisodeName, EpisodeNumber, ReleasedOn, ReleasedYear,
	Description, RunningMins, PictureURL, RatingID, IsAdult)
values ('123456788', 'The Matrix', null, null, null, 1999,
	'A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against the controllers of it.',
	136, null, 'r', 0)
insert into Show (ShowID, Name, EpisodeName, EpisodeNumber, ReleasedOn, ReleasedYear,
	Description, RunningMins, PictureURL, RatingID, IsAdult)
values ('123456789', 'The Matrix Reloaded', null, null, null, 2003,
	'Neo and the rebel leaders estimate that they have 72 hours until 250,000 probes discover Zion and destroy it and its inhabitants. During this, Neo must decide how he can save Trinity from a dark fate in his dreams.',
	138, null, 'r', 0)
insert into Show (ShowID, Name, EpisodeName, EpisodeNumber, ReleasedOn, ReleasedYear,
	Description, RunningMins, PictureURL, RatingID, IsAdult)
values ('123456790', 'The Matrix Revolutions', null, null, null, 2003,
	'War breaks out on the scorched Earth as the machines invade Zion. Whereas Reloaded is about life, Revolutions addresses death.',
	129, null, 'r', 0)
go

--//////////////////////////////////////////////////////////////////////////////

insert into ShowProvider (ShowProviderID, ShowID, ProviderID, ProviderShowID, ShowCost_ShowCostType,
	ShowCost_Money_CurrencyID, ShowCost_Money_Amount, ShowCost_Description, RentalHours)
values ('1615afa3dd814836a39284a341dd7c48', '123456788', 'internetvideos', 'cn123',
	'PayPerView', 'USD', 3.95, '$3.95', 24)
insert into ShowProvider (ShowProviderID, ShowID, ProviderID, ProviderShowID, ShowCost_ShowCostType,
	ShowCost_Money_CurrencyID, ShowCost_Money_Amount, ShowCost_Description, RentalHours)
values ('b37cf9c47d134db4a93679f14d56b304', '123456788', 'moviesmovies', 'mf123',
	'Free', null, null, 'Free', 72)
insert into ShowProvider (ShowProviderID, ShowID, ProviderID, ProviderShowID, ShowCost_ShowCostType,
	ShowCost_Money_CurrencyID, ShowCost_Money_Amount, ShowCost_Description, RentalHours)
values ('8b1854b101ff47348a23391024fc5be4', '123456789', 'vodflicks', 'cn124',
	'PayPerView', 'USD', 4.95, '$4.95', 24)
insert into ShowProvider (ShowProviderID, ShowID, ProviderID, ProviderShowID, ShowCost_ShowCostType,
	ShowCost_Money_CurrencyID, ShowCost_Money_Amount, ShowCost_Description, RentalHours)
values ('9b1854b101ff47348a23391024fc5be4', '123456790', 'excellentvideos', 'mf124',
	'PayPerView', 'USD', 2.95, '$2.95', 48)
go

--//////////////////////////////////////////////////////////////////////////////

insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
values ('ae6a448485e64950b77e5099dfdf3dd5', '123456788', 'action')
insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
values ('a358c2f9e4914f1f99e92739421de1a0', '123456788', 'thriller')
insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
values ('b358c2f9e4914f1f99e92739421de1a0', '123456788', 'scifi')

insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
values ('6c9671714637416e8c22acdfeffefabe', '123456789', 'drama')
insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
values ('f133f196722943ffa94208fcd1cf0142', '123456789', 'thriller')
insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
values ('e133f196722943ffa94208fcd1cf0142', '123456789', 'scifi')

insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
values ('1133f196722943ffa94208fcd1cf0142', '123456790', 'action')
insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
values ('a133f196722943ffa94208fcd1cf0142', '123456790', 'thriller')
insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
values ('b133f196722943ffa94208fcd1cf0142', '123456790', 'scifi')
insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
values ('c133f196722943ffa94208fcd1cf0142', '123456790', 'featured')
go

--//////////////////////////////////////////////////////////////////////////////

--insert into RentedShow (RentedShowID, MemberID, ShowID, ProviderID, ShowURL, ShowCost_ShowCostType, ShowCost_Money_CurrencyID, ShowCost_Money_Amount, ShowCost_Description, RentalHours, RentedOn, AvailableUntil)
--values ('babdaa4a-bfa3-4a2f-a9fe-97dec5751658', '987654321', '123456788', 'cinemanow', 'http://www.cinemanow.com/Show.asp?ID=cn123',
--	'PayPerView', 'USD', 4.95, '$4.95', 24, '2003-11-24 16:00:00', '2003-12-24 16:00:00')
--go
