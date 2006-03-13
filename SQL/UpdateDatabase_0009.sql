--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2005-2006 iNetVOD, Inc. All Rights Reserved.
-- iNetVOD Confidential and Proprietary.  See LEGAL.txt.
--//////////////////////////////////////////////////////////////////////////////

use [iNetVOD]
GO

--//////////////////////////////////////////////////////////////////////////////

BEGIN TRANSACTION

--//////////////////////////////////////////////////////////////////////////////

delete from ShowCategory where CategoryID = 'featured'

insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
select lower(convert(varchar(64), newid())), ShowID, 'featured' from Show
where (name = 'One Step Beyond: Night of April 14th') and (episodename is null)

insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
select lower(convert(varchar(64), newid())), ShowID, 'featured' from Show
where (name = 'Aces and Eights') and (episodename is null)

insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
select lower(convert(varchar(64), newid())), ShowID, 'featured' from Show
where (name = 'Vibrating Maid, The') and (episodename is null)

insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
select lower(convert(varchar(64), newid())), ShowID, 'featured' from Show
where (name = 'Campaigns in Europe') and (episodename  = 'Code Name: Overlord')

insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
select lower(convert(varchar(64), newid())), ShowID, 'featured' from Show
where (name = 'Scarlet Letter, The') and (episodename is null)

insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
select lower(convert(varchar(64), newid())), ShowID, 'featured' from Show
where (name = 'In the Beach House') and (episodename is null)

insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
select lower(convert(varchar(64), newid())), ShowID, 'featured' from Show
where (name = 'Behind the Scenes with Roxanna') and (episodename is null)

insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
select lower(convert(varchar(64), newid())), ShowID, 'featured' from Show
where (name = 'The Three Stooges') and (episodename = 'Malice in the Palace')

insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
select lower(convert(varchar(64), newid())), ShowID, 'featured' from Show
where (name = 'Capture, The') and (episodename is null)

insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
select lower(convert(varchar(64), newid())), ShowID, 'featured' from Show
where (name = 'Cains Cutthroats') and (episodename is null)

insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
select lower(convert(varchar(64), newid())), ShowID, 'featured' from Show
where (name = 'The Matrix Revolutions') and (episodename is null)

insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
select lower(convert(varchar(64), newid())), ShowID, 'featured' from Show
where (name = 'Robinson Crusoe Of Clipper Island Ep.10') and (episodename is null)

insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
select lower(convert(varchar(64), newid())), ShowID, 'featured' from Show
where (name = 'Flash Gordon Conquers the Universe: Ep. 1') and (episodename is null)

insert into ShowCategory (ShowCategoryID, ShowID, CategoryID)
select lower(convert(varchar(64), newid())), ShowID, 'featured' from Show
where (name = 'An Erotic Vampire in Paris') and (episodename is null)

GO

--//////////////////////////////////////////////////////////////////////////////

COMMIT

--//////////////////////////////////////////////////////////////////////////////
--//////////////////////////////////////////////////////////////////////////////
