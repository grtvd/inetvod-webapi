--//////////////////////////////////////////////////////////////////////////////
-- Copyright © 2005-2006 iNetVOD, Inc. All Rights Reserved.
-- iNetVOD Confidential and Proprietary.  See LEGAL.txt.
--//////////////////////////////////////////////////////////////////////////////

use inetvod

--//////////////////////////////////////////////////////////////////////////////

insert into Member (MemberID, FirstName, LastName) values ('12c6f4b3-b19d-4f41-a270-26afe8fe703b','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('3f4f3672-68fc-4b03-8fb2-d7c778ef0715','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('e4c5781a-e022-485b-990b-b7afbacdd4fd','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('14e97b29-f0ee-4e78-ae68-0dacbe6db438','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('a0c54310-d3e0-48d7-a84e-f81751d77d7c','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('d95b331f-c694-4386-b670-e34f0e7138f6','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('ca00a537-f1bc-4e26-bc9b-850481591c12','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('0145eb45-f201-453a-94fb-e8065dcf8dac','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('9e9fd478-a7f1-4abd-afa3-0c28e5a15efa','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('f8114ad8-e40e-41af-8b04-396a829f1c4c','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('6a61b3b5-80c2-4a21-9ab6-faf689d3bb3e','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('054a08c4-4842-4a6d-82be-85fd9652582b','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('31962729-e890-4ca5-b669-1b85b8c19049','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('55804433-8f02-43ee-97e3-765cf2ad6262','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('3c50087b-89c3-41d2-944b-fd55687ea248','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('f4c026ee-1d73-47d8-ac17-e97a099b4009','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('8fc091b3-c088-4fd2-9d74-a75f91cfca6f','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('f0d291a6-2ef7-4575-9289-736d322039a4','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('0bd77bea-23fe-4682-be58-d9fd3d363281','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('cb060986-c5cf-4f2d-b74a-cc3d80edf2f7','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('40e74d73-1f44-4acb-9096-571e8b39f857','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('ee3a9011-e5d4-45e4-93e2-1728686510c7','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('644ea5a5-5c20-4829-9001-21cad3db411e','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('4ab9de9f-8fb8-4aa8-b888-7420ca3b2e5b','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('c8bfb589-a8f9-4522-bdf5-eed42c8cb8c9','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('c1dccff7-586f-41d0-aa7c-9c3b7bcba2fb','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('27aedf9c-98a7-4c8c-9e46-a53da0345d69','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('a09cf4b8-0f23-43e8-b055-898d663b9254','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('35601a26-40ff-4b7d-9985-af795009f7cc','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('64ea1aa6-3a3a-4a42-8306-9a7194168882','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('8243e73f-b089-452c-acf4-a4ea2519d229','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('c2600546-96b3-4aec-bacf-666d0b382715','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('6a43a648-5a7f-45c6-9689-183b6234920d','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('04539722-92ed-420f-ada6-2af1568037f7','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('8eca0fcf-8809-4ca1-b77f-513c61f35e13','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('b9e64d39-58f0-4987-ab05-20d58aa2d24f','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('a9448d73-0031-44ca-9192-ae52ae2cd3a3','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('3663ab27-fb55-405c-a494-d4753641ebff','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('aaca3b8e-06f6-4d68-88c6-d5455b15f5a4','CES','2005')
insert into Member (MemberID, FirstName, LastName) values ('d3d785f1-4869-4531-bdd1-ef680bb53b48','CES','2005')
go

--//////////////////////////////////////////////////////////////////////////////

SET IDENTITY_INSERT MemberLogon ON

insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('12c6f4b3-b19d-4f41-a270-26afe8fe703b','100000001@INETVOD.ORG','100000001@inetvod.org','314021','100000001','314021', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('3f4f3672-68fc-4b03-8fb2-d7c778ef0715','100000002@INETVOD.ORG','100000002@inetvod.org','156039','100000002','156039', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('e4c5781a-e022-485b-990b-b7afbacdd4fd','100000003@INETVOD.ORG','100000003@inetvod.org','594069','100000003','594069', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('14e97b29-f0ee-4e78-ae68-0dacbe6db438','100000004@INETVOD.ORG','100000004@inetvod.org','126102','100000004','126102', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('a0c54310-d3e0-48d7-a84e-f81751d77d7c','100000005@INETVOD.ORG','100000005@inetvod.org','028059','100000005','028059', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('d95b331f-c694-4386-b670-e34f0e7138f6','100000006@INETVOD.ORG','100000006@inetvod.org','071912','100000006','071912', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('ca00a537-f1bc-4e26-bc9b-850481591c12','100000007@INETVOD.ORG','100000007@inetvod.org','651872','100000007','651872', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('0145eb45-f201-453a-94fb-e8065dcf8dac','100000008@INETVOD.ORG','100000008@inetvod.org','239914','100000008','239914', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('9e9fd478-a7f1-4abd-afa3-0c28e5a15efa','100000009@INETVOD.ORG','100000009@inetvod.org','024969','100000009','024969', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('f8114ad8-e40e-41af-8b04-396a829f1c4c','100000010@INETVOD.ORG','100000010@inetvod.org','224666','100000010','224666', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('6a61b3b5-80c2-4a21-9ab6-faf689d3bb3e','100000011@INETVOD.ORG','100000011@inetvod.org','866692','100000011','866692', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('054a08c4-4842-4a6d-82be-85fd9652582b','100000012@INETVOD.ORG','100000012@inetvod.org','211751','100000012','211751', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('31962729-e890-4ca5-b669-1b85b8c19049','100000013@INETVOD.ORG','100000013@inetvod.org','849025','100000013','849025', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('55804433-8f02-43ee-97e3-765cf2ad6262','100000014@INETVOD.ORG','100000014@inetvod.org','309542','100000014','309542', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('3c50087b-89c3-41d2-944b-fd55687ea248','100000015@INETVOD.ORG','100000015@inetvod.org','469816','100000015','469816', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('f4c026ee-1d73-47d8-ac17-e97a099b4009','100000016@INETVOD.ORG','100000016@inetvod.org','919623','100000016','919623', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('8fc091b3-c088-4fd2-9d74-a75f91cfca6f','100000017@INETVOD.ORG','100000017@inetvod.org','364958','100000017','364958', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('f0d291a6-2ef7-4575-9289-736d322039a4','100000018@INETVOD.ORG','100000018@inetvod.org','124200','100000018','124200', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('0bd77bea-23fe-4682-be58-d9fd3d363281','100000019@INETVOD.ORG','100000019@inetvod.org','456768','100000019','456768', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('cb060986-c5cf-4f2d-b74a-cc3d80edf2f7','100000020@INETVOD.ORG','100000020@inetvod.org','441843','100000020','441843', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('40e74d73-1f44-4acb-9096-571e8b39f857','100000021@INETVOD.ORG','100000021@inetvod.org','838795','100000021','838795', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('ee3a9011-e5d4-45e4-93e2-1728686510c7','100000022@INETVOD.ORG','100000022@inetvod.org','378767','100000022','378767', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('644ea5a5-5c20-4829-9001-21cad3db411e','100000023@INETVOD.ORG','100000023@inetvod.org','463580','100000023','463580', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('4ab9de9f-8fb8-4aa8-b888-7420ca3b2e5b','100000024@INETVOD.ORG','100000024@inetvod.org','980819','100000024','980819', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('c8bfb589-a8f9-4522-bdf5-eed42c8cb8c9','100000025@INETVOD.ORG','100000025@inetvod.org','593844','100000025','593844', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('c1dccff7-586f-41d0-aa7c-9c3b7bcba2fb','100000026@INETVOD.ORG','100000026@inetvod.org','148619','100000026','148619', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('27aedf9c-98a7-4c8c-9e46-a53da0345d69','100000027@INETVOD.ORG','100000027@inetvod.org','632904','100000027','632904', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('a09cf4b8-0f23-43e8-b055-898d663b9254','100000028@INETVOD.ORG','100000028@inetvod.org','637219','100000028','637219', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('35601a26-40ff-4b7d-9985-af795009f7cc','100000029@INETVOD.ORG','100000029@inetvod.org','346955','100000029','346955', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('64ea1aa6-3a3a-4a42-8306-9a7194168882','100000030@INETVOD.ORG','100000030@inetvod.org','311744','100000030','311744', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('8243e73f-b089-452c-acf4-a4ea2519d229','100000031@INETVOD.ORG','100000031@inetvod.org','379281','100000031','379281', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('c2600546-96b3-4aec-bacf-666d0b382715','100000032@INETVOD.ORG','100000032@inetvod.org','312397','100000032','312397', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('6a43a648-5a7f-45c6-9689-183b6234920d','100000033@INETVOD.ORG','100000033@inetvod.org','836893','100000033','836893', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('04539722-92ed-420f-ada6-2af1568037f7','100000034@INETVOD.ORG','100000034@inetvod.org','424110','100000034','424110', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('8eca0fcf-8809-4ca1-b77f-513c61f35e13','100000035@INETVOD.ORG','100000035@inetvod.org','335076','100000035','335076', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('b9e64d39-58f0-4987-ab05-20d58aa2d24f','100000036@INETVOD.ORG','100000036@inetvod.org','600848','100000036','600848', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('a9448d73-0031-44ca-9192-ae52ae2cd3a3','100000037@INETVOD.ORG','100000037@inetvod.org','695495','100000037','695495', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('3663ab27-fb55-405c-a494-d4753641ebff','100000038@INETVOD.ORG','100000038@inetvod.org','228381','100000038','228381', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('aaca3b8e-06f6-4d68-88c6-d5455b15f5a4','100000039@INETVOD.ORG','100000039@inetvod.org','039200','100000039','039200', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
insert into MemberLogon (MemberID, EmailKey, Email, Password, LogonID, PIN, SecretQuestion, SecretAnswer, TermsAcceptedOn, TermsAcceptedVersion) values ('d3d785f1-4869-4531-bdd1-ef680bb53b48','100000040@INETVOD.ORG','100000040@inetvod.org','507661','100000040','507661', 'What''s your favoriate color?', 'red', '1999-12-31T23:59:59', '0.0.0.0')
go

SET IDENTITY_INSERT MemberLogon OFF

--//////////////////////////////////////////////////////////////////////////////
