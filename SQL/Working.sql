use inetvod

select * from Provider
select * from Category
select * from Rating
select * from Member
select * from SerialNumber
select * from Show
select * from ShowProvider
select * from ShowCategory
select * from RentedShow

--delete from Provider
--delete from Member where MemberID = '987654321'

sp_Member_Get '987654321'
sp_SerialNumber_Get 'B'
sp_Show_Get '1040'
sp_Show_GetByRentedShowMemberID '987654321'
sp_RentedShow_GetByMemberID '987654321'
sp_ShowCategory_GetByShowID '123456788'
sp_ShowCategory_GetByRentedShowMemberID '987654321'

use inetvod

select distinct(EpisodeName) from show

select * from Show
where showid = '767'
order by ShowID

update Show set
Name = '~Friends',
EpisodeName = 'The One Where Rachel''s Sister Baby-Sits',
EpisodeNumber = '327',
ReleasedOn = '2003-10-30',
ReleasedYear = 2003,
RunningMins = 30,
Description = 'Rachel convinces her sister (Christina Applegate) to baby-sit over Ross'' protests; Joey writes a letter to an adoption agency on Monica and Chandler''s behalf.'
where showid = '767'
--delete from Show


select * from ShowProvider
--delete from ShowProvider

sp_ShowProvider_Get '2', 'movieflix'


select * from ShowCategory
where showid = '767'
order by ShowID, CategoryID
--delete from ShowCategory
sp_ShowCategory_GetByShowID '2'

insert ShowCategory (ShowID, CategoryID)
values ('767', 'classics')
insert ShowCategory (ShowID, CategoryID)
values ('767', 'action')
insert ShowCategory (ShowID, CategoryID)
values ('767', 'thriller')

insert ShowCategory (ShowID, CategoryID)
select ShowID, 'action' from Show
where (EpisodeName = 'Action')
or (EpisodeName = 'Adventure')
or (EpisodeName = 'Martial Arts')
or (EpisodeName = 'War')
or (EpisodeName = 'WWII')

insert ShowCategory (ShowID, CategoryID)
select ShowID, 'classics' from Show
where (EpisodeName = 'Classic')
or (EpisodeName = 'Classic TV')
or (EpisodeName = 'Silent')

insert ShowCategory (ShowID, CategoryID)
select ShowID, 'comedy' from Show
where (EpisodeName = 'Comedy')

insert ShowCategory (ShowID, CategoryID)
select ShowID, 'drama' from Show
where (EpisodeName = 'Biography')
or (EpisodeName = 'Black Culture')
or (EpisodeName = 'Captioned')
or (EpisodeName = 'Documentary')
or (EpisodeName = 'Drama')
or (EpisodeName = 'Film Noir')
or (EpisodeName = 'Foreign')
or (EpisodeName = 'Literature')

insert ShowCategory (ShowID, CategoryID)
select ShowID, 'family' from Show
where (EpisodeName = 'Cartoons')
or (EpisodeName = 'Family')
or (EpisodeName = 'Musical')
or (EpisodeName = 'Religion')

insert ShowCategory (ShowID, CategoryID)
select ShowID, 'romance' from Show
where (EpisodeName = 'Romance')

insert ShowCategory (ShowID, CategoryID)
select ShowID, 'scifi' from Show
where (EpisodeName = 'Sci-Fi')

insert ShowCategory (ShowID, CategoryID)
select ShowID, 'tv' from Show
where (EpisodeName = 'Serials')

insert ShowCategory (ShowID, CategoryID)
select ShowID, 'thriller' from Show
where (EpisodeName = 'Horror')
or (EpisodeName = 'Mystery')
or (EpisodeName = 'Thriller')

insert ShowCategory (ShowID, CategoryID)
select ShowID, 'trailers' from Show
where (EpisodeName = 'Trailers')

insert ShowCategory (ShowID, CategoryID)
select ShowID, 'western' from Show
where (EpisodeName = 'B-Western')
or (EpisodeName = 'Westerns')


update Show set IsAdult = 1
where (EpisodeName = 'After Hours')


--update Show set EpisodeName = null

Action
Adventure
After Hours
B-Western
Biography
Black Culture
Captioned
Cartoons
Classic
Classic TV
Comedy
Documentary
Drama
Family
Film Noir
Foreign
Horror
Literature
Martial Arts
Musical
Mystery
Religion
Romance
Sci-Fi
Serials
Silent
Thriller
Trailers
War
Westerns
WWII
