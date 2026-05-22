--
-- PostgreSQL database dump
--

-- Dumped from database version 16.4
-- Dumped by pg_dump version 16.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: order_management; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE order_management WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Romanian_Romania.1252';


ALTER DATABASE order_management OWNER TO postgres;

\connect order_management

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    email character varying(100) NOT NULL,
    address character varying(100) NOT NULL,
    age integer NOT NULL,
    CONSTRAINT check_name CHECK (((email)::text ~~ '%_@_%._%'::text))
);


ALTER TABLE public.client OWNER TO postgres;

--
-- Name: client_client_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.client_client_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.client_client_id_seq OWNER TO postgres;

--
-- Name: client_client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.client_client_id_seq OWNED BY public.client.id;


--
-- Name: log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.log (
    id integer NOT NULL,
    client_name character varying(255),
    product_name character varying(255),
    quantity integer,
    total_price double precision,
    order_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.log OWNER TO postgres;

--
-- Name: log_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.log_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.log_id_seq OWNER TO postgres;

--
-- Name: log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.log_id_seq OWNED BY public.log.id;


--
-- Name: order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."order" (
    id integer NOT NULL,
    client character varying(100) NOT NULL,
    product character varying(100) NOT NULL,
    nr_of_products integer NOT NULL,
    total_price double precision NOT NULL
);


ALTER TABLE public."order" OWNER TO postgres;

--
-- Name: order_order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.order_order_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.order_order_id_seq OWNER TO postgres;

--
-- Name: order_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.order_order_id_seq OWNED BY public."order".id;


--
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    stock integer,
    price double precision
);


ALTER TABLE public.product OWNER TO postgres;

--
-- Name: product_product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.product_product_id_seq OWNER TO postgres;

--
-- Name: product_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_product_id_seq OWNED BY public.product.id;


--
-- Name: client id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client ALTER COLUMN id SET DEFAULT nextval('public.client_client_id_seq'::regclass);


--
-- Name: log id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.log ALTER COLUMN id SET DEFAULT nextval('public.log_id_seq'::regclass);


--
-- Name: order id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order" ALTER COLUMN id SET DEFAULT nextval('public.order_order_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_product_id_seq'::regclass);


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.client VALUES (1, 'Edouard Athridge', 'eathridge0@fema.gov', '79962 Schurz Point', 83);
INSERT INTO public.client VALUES (2, 'Rory Leckenby', 'rleckenby1@list-manage.com', '0 Messerschmidt Hill', 50);
INSERT INTO public.client VALUES (3, 'Sophia Shout', 'sshout2@china.com.cn', '612 Myrtle Street', 19);
INSERT INTO public.client VALUES (4, 'Lev MacAlester', 'lmacalester3@goo.gl', '6 Hazelcrest Drive', 44);
INSERT INTO public.client VALUES (5, 'Peta Sansum', 'psansum4@addtoany.com', '2 Harper Drive', 47);
INSERT INTO public.client VALUES (6, 'Wilfred Demetr', 'wdemetr5@multiply.com', '87 Blackbird Drive', 56);
INSERT INTO public.client VALUES (7, 'Kissiah Willoughley', 'kwilloughley6@weather.com', '4 Raven Center', 21);
INSERT INTO public.client VALUES (8, 'Madelin Becker', 'mbecker7@cafepress.com', '435 Boyd Parkway', 80);
INSERT INTO public.client VALUES (9, 'Jacki McAllister', 'jmcallister8@goo.ne.jp', '17 Sutteridge Place', 55);
INSERT INTO public.client VALUES (11, 'Dagmar Vasyukhin', 'dvasyukhina@angelfire.com', '65211 Jenifer Circle', 39);
INSERT INTO public.client VALUES (12, 'Gilberto Sawforde', 'gsawfordeb@spiegel.de', '578 Delaware Terrace', 61);
INSERT INTO public.client VALUES (13, 'Arnold McCreary', 'amccrearyc@zimbio.com', '286 Cherokee Street', 48);
INSERT INTO public.client VALUES (14, 'Randall Di Maria', 'rdid@unesco.org', '86 Tony Hill', 34);
INSERT INTO public.client VALUES (15, 'Annette Burberye', 'aburberyee@nps.gov', '6796 Atwood Plaza', 19);
INSERT INTO public.client VALUES (16, 'Nickie Hackey', 'nhackeyf@mail.ru', '85 Weeping Birch Place', 47);
INSERT INTO public.client VALUES (17, 'Laural Swendell', 'lswendellg@hugedomains.com', '6606 Alpine Street', 59);
INSERT INTO public.client VALUES (18, 'Ted Featherby', 'tfeatherbyh@dailymotion.com', '69 Parkside Road', 45);
INSERT INTO public.client VALUES (19, 'Putnem Baxster', 'pbaxsteri@dot.gov', '2554 Huxley Plaza', 73);
INSERT INTO public.client VALUES (20, 'Del Rubega', 'drubegaj@omniture.com', '57 Alpine Center', 40);
INSERT INTO public.client VALUES (21, 'Demeter Holywell', 'dholywellk@studiopress.com', '6 Starling Park', 78);
INSERT INTO public.client VALUES (22, 'Orv Colleton', 'ocolletonl@shareasale.com', '2 Service Pass', 85);
INSERT INTO public.client VALUES (23, 'Celka Hinstock', 'chinstockm@lulu.com', '772 Magdeline Place', 81);
INSERT INTO public.client VALUES (24, 'Guinevere Guidelli', 'gguidellin@mozilla.com', '32 Shasta Parkway', 65);
INSERT INTO public.client VALUES (25, 'Katrine Cooksey', 'kcookseyo@diigo.com', '94 Aberg Plaza', 73);
INSERT INTO public.client VALUES (26, 'Maure Strongman', 'mstrongmanp@mediafire.com', '1917 Burning Wood Lane', 37);
INSERT INTO public.client VALUES (27, 'Lisbeth Loadwick', 'lloadwickq@clickbank.net', '27097 Summit Street', 16);
INSERT INTO public.client VALUES (28, 'Karalee Anthiftle', 'kanthiftler@msn.com', '31334 Talmadge Way', 34);
INSERT INTO public.client VALUES (29, 'Rosabella Okey', 'rokeys@bing.com', '1549 Kinsman Trail', 28);
INSERT INTO public.client VALUES (30, 'Rolfe Jakubowski', 'rjakubowskit@skype.com', '46169 Shoshone Junction', 47);
INSERT INTO public.client VALUES (31, 'Minta Vanes', 'mvanesu@nyu.edu', '5171 Washington Trail', 59);
INSERT INTO public.client VALUES (32, 'Meade Kmicicki', 'mkmicickiv@theglobeandmail.com', '0 Barnett Plaza', 55);
INSERT INTO public.client VALUES (33, 'Rheta Blackaller', 'rblackallerw@theguardian.com', '6811 Brickson Park Park', 54);
INSERT INTO public.client VALUES (34, 'Ev Osgordby', 'eosgordbyx@goodreads.com', '94466 Loftsgordon Point', 65);
INSERT INTO public.client VALUES (35, 'Rodi Riepel', 'rriepely@canalblog.com', '06 Bobwhite Pass', 34);
INSERT INTO public.client VALUES (36, 'Fraze Limon', 'flimonz@sitemeter.com', '5 Maywood Parkway', 74);
INSERT INTO public.client VALUES (37, 'Irma Devonish', 'idevonish10@360.cn', '98680 Merry Pass', 73);
INSERT INTO public.client VALUES (38, 'Sutherlan O''Henery', 'sohenery11@webmd.com', '1 Surrey Parkway', 64);
INSERT INTO public.client VALUES (39, 'Chicky Orfeur', 'corfeur12@sogou.com', '2672 Glacier Hill Pass', 85);
INSERT INTO public.client VALUES (40, 'Ronnie Markel', 'rmarkel13@tmall.com', '71164 Lindbergh Pass', 78);
INSERT INTO public.client VALUES (41, 'Staffard Giacobo', 'sgiacobo14@sphinn.com', '476 Packers Junction', 52);
INSERT INTO public.client VALUES (42, 'Roselin Allison', 'rallison15@com.com', '27102 Bayside Avenue', 49);
INSERT INTO public.client VALUES (43, 'Yasmin Hirche', 'yhirche16@reddit.com', '4501 Manitowish Circle', 65);
INSERT INTO public.client VALUES (44, 'Alphonso Prettyjohn', 'aprettyjohn17@feedburner.com', '28320 Main Junction', 36);
INSERT INTO public.client VALUES (45, 'Tymon Voaden', 'tvoaden18@amazon.com', '6505 Linden Crossing', 55);
INSERT INTO public.client VALUES (46, 'Leora Cradock', 'lcradock19@wsj.com', '6 Everett Point', 67);
INSERT INTO public.client VALUES (47, 'Sheilakathryn Heijne', 'sheijne1a@drupal.org', '5667 Huxley Crossing', 40);
INSERT INTO public.client VALUES (48, 'Karine Jeanenet', 'kjeanenet1b@mozilla.com', '88 Raven Plaza', 29);
INSERT INTO public.client VALUES (49, 'Amandie Claibourn', 'aclaibourn1c@jimdo.com', '03 Dorton Park', 35);
INSERT INTO public.client VALUES (50, 'Peadar Worters', 'pworters1d@imdb.com', '66 Golf Course Parkway', 52);
INSERT INTO public.client VALUES (51, 'Angelique Vido', 'avido1e@a8.net', '0834 Mayfield Trail', 40);
INSERT INTO public.client VALUES (52, 'Peter Mattisson', 'pmattisson1f@miitbeian.gov.cn', '04 Center Place', 34);
INSERT INTO public.client VALUES (53, 'Kevyn Menicomb', 'kmenicomb1g@issuu.com', '91295 Nova Lane', 85);
INSERT INTO public.client VALUES (54, 'Vance Reddie', 'vreddie1h@issuu.com', '99 Ohio Parkway', 69);
INSERT INTO public.client VALUES (55, 'Mackenzie Careswell', 'mcareswell1i@newsvine.com', '04 Forster Center', 70);
INSERT INTO public.client VALUES (56, 'Frederick Hayler', 'fhayler1j@hatena.ne.jp', '54 Havey Trail', 67);
INSERT INTO public.client VALUES (57, 'Mireille Abatelli', 'mabatelli1k@irs.gov', '40 South Trail', 70);
INSERT INTO public.client VALUES (58, 'Alexina Robichon', 'arobichon1l@oracle.com', '047 New Castle Place', 21);
INSERT INTO public.client VALUES (59, 'Heidi Meas', 'hmeas1m@blogs.com', '59268 Texas Center', 55);
INSERT INTO public.client VALUES (60, 'Ame Cogdon', 'acogdon1n@woothemes.com', '55146 Cascade Junction', 79);
INSERT INTO public.client VALUES (61, 'Boot Purdie', 'bpurdie1o@yahoo.co.jp', '4041 Eagle Crest Junction', 45);
INSERT INTO public.client VALUES (62, 'Diena Whanstall', 'dwhanstall1p@samsung.com', '17 Hoepker Center', 53);
INSERT INTO public.client VALUES (63, 'Rae Holywell', 'rholywell1q@bloomberg.com', '92973 Butternut Junction', 66);
INSERT INTO public.client VALUES (64, 'Marietta Danet', 'mdanet1r@slideshare.net', '864 Sycamore Crossing', 38);
INSERT INTO public.client VALUES (65, 'Dareen Hirsthouse', 'dhirsthouse1s@godaddy.com', '014 Starling Point', 84);
INSERT INTO public.client VALUES (66, 'Nicholle Holliar', 'nholliar1t@berkeley.edu', '5 Graedel Point', 16);
INSERT INTO public.client VALUES (67, 'Giorgi Skarin', 'gskarin1u@sakura.ne.jp', '99144 Old Shore Parkway', 81);
INSERT INTO public.client VALUES (68, 'Wildon Villiers', 'wvilliers1v@storify.com', '5476 Coleman Center', 52);
INSERT INTO public.client VALUES (69, 'Symon Biasi', 'sbiasi1w@bravesites.com', '7301 Green Ridge Alley', 76);
INSERT INTO public.client VALUES (70, 'Yetta Blance', 'yblance1x@nsw.gov.au', '4383 New Castle Crossing', 49);
INSERT INTO public.client VALUES (71, 'Sybille Beswick', 'sbeswick1y@1688.com', '3348 Browning Parkway', 55);
INSERT INTO public.client VALUES (72, 'Bryna Burhouse', 'bburhouse1z@mediafire.com', '079 Banding Terrace', 69);
INSERT INTO public.client VALUES (73, 'Ramonda Tiptaft', 'rtiptaft20@ebay.com', '1267 Arrowood Way', 35);
INSERT INTO public.client VALUES (74, 'Rosalinde Faughnan', 'rfaughnan21@gravatar.com', '44 Armistice Parkway', 56);
INSERT INTO public.client VALUES (75, 'Berne Coo', 'bcoo22@usgs.gov', '52027 Sunfield Hill', 41);
INSERT INTO public.client VALUES (76, 'Tammy Eckert', 'teckert23@house.gov', '397 Randy Park', 38);
INSERT INTO public.client VALUES (77, 'Haydon Beaze', 'hbeaze24@jimdo.com', '79308 Mayer Avenue', 71);
INSERT INTO public.client VALUES (78, 'Bernardina Yakubov', 'byakubov25@e-recht24.de', '8747 John Wall Court', 79);
INSERT INTO public.client VALUES (79, 'Gussi Berthot', 'gberthot26@state.gov', '6 Dryden Pass', 34);
INSERT INTO public.client VALUES (80, 'Rachelle Charlin', 'rcharlin27@auda.org.au', '28 Old Gate Way', 17);
INSERT INTO public.client VALUES (81, 'Jessee Nisbith', 'jnisbith28@hao123.com', '6259 Veith Circle', 22);
INSERT INTO public.client VALUES (82, 'Elmira Scullard', 'escullard29@newsvine.com', '33840 Old Shore Lane', 35);
INSERT INTO public.client VALUES (83, 'Hewitt Bassick', 'hbassick2a@cdc.gov', '67183 Acker Trail', 54);
INSERT INTO public.client VALUES (84, 'Franchot Bordone', 'fbordone2b@scientificamerican.com', '12415 Moulton Crossing', 26);
INSERT INTO public.client VALUES (85, 'Ezechiel Menel', 'emenel2c@earthlink.net', '837 Donald Place', 83);
INSERT INTO public.client VALUES (86, 'Gerrie Simms', 'gsimms2d@surveymonkey.com', '80 Main Terrace', 85);
INSERT INTO public.client VALUES (87, 'Marty Giovannelli', 'mgiovannelli2e@samsung.com', '8401 Roth Trail', 79);
INSERT INTO public.client VALUES (88, 'Ruthann Prugel', 'rprugel2f@dell.com', '7097 Onsgard Trail', 29);
INSERT INTO public.client VALUES (89, 'Carrissa McGeagh', 'cmcgeagh2g@flickr.com', '570 South Terrace', 17);
INSERT INTO public.client VALUES (90, 'Caldwell Roches', 'croches2h@google.de', '721 Loomis Circle', 54);
INSERT INTO public.client VALUES (91, 'Christan Croston', 'ccroston2i@vistaprint.com', '05 6th Avenue', 68);
INSERT INTO public.client VALUES (92, 'Quintin Radki', 'qradki2j@springer.com', '09 Petterle Pass', 42);
INSERT INTO public.client VALUES (93, 'Reamonn Baudrey', 'rbaudrey2k@1und1.de', '1 Center Circle', 76);
INSERT INTO public.client VALUES (94, 'Dennison McKoy', 'dmckoy2l@constantcontact.com', '69 Shasta Terrace', 34);
INSERT INTO public.client VALUES (95, 'Cori Du Hamel', 'cdu2m@nydailynews.com', '64948 Havey Place', 83);
INSERT INTO public.client VALUES (96, 'Mavra Sherwen', 'msherwen2n@berkeley.edu', '15977 Meadow Vale Parkway', 67);
INSERT INTO public.client VALUES (97, 'Mari Perch', 'mperch2o@alexa.com', '5468 Ludington Court', 21);
INSERT INTO public.client VALUES (98, 'Estrellita Rogans', 'erogans2p@gmpg.org', '489 Cardinal Plaza', 41);
INSERT INTO public.client VALUES (99, 'Jolee Van Niekerk', 'jvan2q@goo.ne.jp', '14 Harper Hill', 57);
INSERT INTO public.client VALUES (100, 'Martguerita Markie', 'mmarkie2r@delicious.com', '88 Melrose Point', 85);
INSERT INTO public.client VALUES (101, 'Jenda Purslow', 'jpurslow2s@slashdot.org', '77 Cody Alley', 78);
INSERT INTO public.client VALUES (102, 'Clair McWhan', 'cmcwhan2t@walmart.com', '771 Brentwood Pass', 83);
INSERT INTO public.client VALUES (103, 'Stacy Scrivener', 'sscrivener2u@altervista.org', '72743 Bay Lane', 23);
INSERT INTO public.client VALUES (104, 'Trudy Alder', 'talder2v@youtu.be', '26619 Hayes Trail', 36);
INSERT INTO public.client VALUES (105, 'Murvyn Clampin', 'mclampin2w@dagondesign.com', '60167 Lukken Lane', 39);
INSERT INTO public.client VALUES (106, 'Aggi Wedlake', 'awedlake2x@telegraph.co.uk', '1 Daystar Place', 73);
INSERT INTO public.client VALUES (107, 'Emory Sunner', 'esunner2y@php.net', '89425 Mifflin Alley', 32);
INSERT INTO public.client VALUES (108, 'Arleen Pavlata', 'apavlata2z@pbs.org', '70 Cottonwood Plaza', 50);
INSERT INTO public.client VALUES (109, 'Simmonds Sandle', 'ssandle30@goo.ne.jp', '70 Kensington Center', 31);
INSERT INTO public.client VALUES (110, 'Denna Wathen', 'dwathen31@alibaba.com', '171 Vernon Parkway', 19);
INSERT INTO public.client VALUES (111, 'Dacy Castell', 'dcastell32@time.com', '57147 Spaight Terrace', 67);
INSERT INTO public.client VALUES (112, 'Josi MacAlinden', 'jmacalinden33@bluehost.com', '42417 Mayfield Place', 65);
INSERT INTO public.client VALUES (113, 'Alastair Redan', 'aredan34@yale.edu', '5 Macpherson Hill', 64);
INSERT INTO public.client VALUES (114, 'Augustina Whitty', 'awhitty35@last.fm', '19852 Hansons Crossing', 35);
INSERT INTO public.client VALUES (115, 'Jaclyn Lyver', 'jlyver36@vk.com', '972 Menomonie Drive', 54);
INSERT INTO public.client VALUES (116, 'Bridie Duro', 'bduro37@cpanel.net', '3844 Mosinee Pass', 39);
INSERT INTO public.client VALUES (117, 'Lorelei Waszczyk', 'lwaszczyk38@newsvine.com', '1 New Castle Road', 84);
INSERT INTO public.client VALUES (118, 'Livvie Whitnell', 'lwhitnell39@marketwatch.com', '7786 Mariners Cove Junction', 38);
INSERT INTO public.client VALUES (119, 'Grove Arbuckel', 'garbuckel3a@moonfruit.com', '44 Holmberg Avenue', 73);
INSERT INTO public.client VALUES (120, 'Trenton Di Frisco', 'tdi3b@businesswire.com', '225 La Follette Junction', 39);
INSERT INTO public.client VALUES (121, 'Turner Praten', 'tpraten3c@wikimedia.org', '23 Cody Junction', 69);
INSERT INTO public.client VALUES (122, 'Cornall Shew', 'cshew3d@oracle.com', '5 Farragut Circle', 61);
INSERT INTO public.client VALUES (123, 'Thorny Jandac', 'tjandac3e@dmoz.org', '06 Mosinee Trail', 33);
INSERT INTO public.client VALUES (124, 'Felecia Mcmanaman', 'fmcmanaman3f@ebay.co.uk', '8 Columbus Hill', 23);
INSERT INTO public.client VALUES (125, 'Deidre Engelmann', 'dengelmann3g@vistaprint.com', '8190 Almo Avenue', 71);
INSERT INTO public.client VALUES (126, 'Sebastien Reaman', 'sreaman3h@dmoz.org', '9760 Kedzie Hill', 76);
INSERT INTO public.client VALUES (127, 'Curr Gianinotti', 'cgianinotti3i@tinyurl.com', '054 Sheridan Place', 62);
INSERT INTO public.client VALUES (128, 'Wendel Domm', 'wdomm3j@is.gd', '76067 Scofield Crossing', 50);
INSERT INTO public.client VALUES (129, 'Tynan Shorto', 'tshorto3k@washington.edu', '6581 Oak Park', 79);
INSERT INTO public.client VALUES (130, 'Gretta Thurston', 'gthurston3l@vinaora.com', '5 Prairie Rose Parkway', 85);
INSERT INTO public.client VALUES (131, 'Becki Stather', 'bstather3m@comcast.net', '1595 Prairie Rose Circle', 73);
INSERT INTO public.client VALUES (132, 'Almeta Goathrop', 'agoathrop3n@purevolume.com', '984 School Trail', 76);
INSERT INTO public.client VALUES (133, 'Ron Litel', 'rlitel3o@hostgator.com', '7030 Kedzie Crossing', 44);
INSERT INTO public.client VALUES (134, 'Garth Gothard', 'ggothard3p@qq.com', '07417 Dryden Hill', 31);
INSERT INTO public.client VALUES (135, 'Amberly Fishly', 'afishly3q@google.co.uk', '813 Hanson Terrace', 29);
INSERT INTO public.client VALUES (136, 'Kin Favey', 'kfavey3r@wordpress.org', '65038 Crownhardt Circle', 32);
INSERT INTO public.client VALUES (137, 'Dareen Garroway', 'dgarroway3s@skype.com', '2 Fairfield Avenue', 25);
INSERT INTO public.client VALUES (138, 'Ransom Kelberman', 'rkelberman3t@opensource.org', '52794 Oakridge Court', 63);
INSERT INTO public.client VALUES (139, 'Son Wetherill', 'swetherill3u@stumbleupon.com', '9635 1st Drive', 56);
INSERT INTO public.client VALUES (140, 'Orin Danielut', 'odanielut3v@deliciousdays.com', '4257 Garrison Alley', 25);
INSERT INTO public.client VALUES (141, 'Avivah Hainey`', 'ahainey3w@multiply.com', '913 Vahlen Park', 26);
INSERT INTO public.client VALUES (142, 'Gwenny Neubigin', 'gneubigin3x@netlog.com', '12204 Evergreen Crossing', 39);
INSERT INTO public.client VALUES (143, 'Corabella Boxhill', 'cboxhill3y@github.io', '7 Forest Dale Crossing', 26);
INSERT INTO public.client VALUES (144, 'Eldridge Bilverstone', 'ebilverstone3z@networkadvertising.org', '8735 Colorado Street', 20);
INSERT INTO public.client VALUES (145, 'Margalo Dunlap', 'mdunlap40@go.com', '226 Messerschmidt Street', 64);
INSERT INTO public.client VALUES (146, 'Vivyanne Osband', 'vosband41@purevolume.com', '90 Bluejay Terrace', 36);
INSERT INTO public.client VALUES (147, 'Darsie Tozer', 'dtozer42@senate.gov', '16417 Moland Crossing', 46);
INSERT INTO public.client VALUES (148, 'Tallulah Edelman', 'tedelman43@furl.net', '23255 Kedzie Trail', 80);
INSERT INTO public.client VALUES (149, 'Julia Haxell', 'jhaxell44@mozilla.org', '7 Glendale Pass', 64);
INSERT INTO public.client VALUES (150, 'Erhard Cruce', 'ecruce45@webeden.co.uk', '63943 Bellgrove Park', 33);
INSERT INTO public.client VALUES (151, 'Vern Ziems', 'vziems46@imageshack.us', '702 Mcbride Crossing', 68);
INSERT INTO public.client VALUES (152, 'Cale Calam', 'ccalam47@google.com.au', '7 Fieldstone Lane', 21);
INSERT INTO public.client VALUES (153, 'Brady Delia', 'bdelia48@elpais.com', '907 La Follette Pass', 31);
INSERT INTO public.client VALUES (154, 'Crosby Delhay', 'cdelhay49@ask.com', '18494 Brown Place', 70);
INSERT INTO public.client VALUES (155, 'Forrest Humfrey', 'fhumfrey4a@surveymonkey.com', '214 Del Mar Trail', 44);
INSERT INTO public.client VALUES (156, 'Mehetabel Edgley', 'medgley4b@github.io', '46 Grasskamp Court', 31);
INSERT INTO public.client VALUES (157, 'Kirstyn Ticic', 'kticic4c@si.edu', '8677 Summer Ridge Park', 55);
INSERT INTO public.client VALUES (158, 'Josie Turner', 'jturner4d@mapquest.com', '964 Pleasure Plaza', 26);
INSERT INTO public.client VALUES (159, 'Nickolai Berthomieu', 'nberthomieu4e@cnet.com', '3 8th Road', 30);
INSERT INTO public.client VALUES (160, 'Christiano Elnaugh', 'celnaugh4f@gizmodo.com', '39911 Rigney Point', 39);
INSERT INTO public.client VALUES (161, 'Ellissa Piche', 'epiche4g@pinterest.com', '2 Warrior Drive', 66);
INSERT INTO public.client VALUES (162, 'Rock Corser', 'rcorser4h@theguardian.com', '212 Hallows Avenue', 38);
INSERT INTO public.client VALUES (163, 'Mayor Bolver', 'mbolver4i@addtoany.com', '77 Lakewood Park', 61);
INSERT INTO public.client VALUES (164, 'Dredi Biaggetti', 'dbiaggetti4j@meetup.com', '0275 Namekagon Place', 72);
INSERT INTO public.client VALUES (165, 'Amerigo Vick', 'avick4k@wikipedia.org', '96 Loftsgordon Pass', 72);
INSERT INTO public.client VALUES (166, 'Miranda Hollows', 'mhollows4l@berkeley.edu', '02 Mosinee Avenue', 49);
INSERT INTO public.client VALUES (167, 'Saudra Szimon', 'sszimon4m@samsung.com', '995 Norway Maple Circle', 72);
INSERT INTO public.client VALUES (168, 'Flore Leedes', 'fleedes4n@marketwatch.com', '3592 Crownhardt Circle', 58);
INSERT INTO public.client VALUES (169, 'Benedetto Mapson', 'bmapson4o@cornell.edu', '9 Roth Alley', 78);
INSERT INTO public.client VALUES (170, 'Tomasine Egdale', 'tegdale4p@amazon.co.jp', '8 Graceland Hill', 52);
INSERT INTO public.client VALUES (171, 'Cecilia Mandres', 'cmandres4q@cbsnews.com', '0353 Wayridge Trail', 64);
INSERT INTO public.client VALUES (172, 'Paco Dumberrill', 'pdumberrill4r@aol.com', '309 Old Gate Parkway', 56);
INSERT INTO public.client VALUES (173, 'Thelma Innocent', 'tinnocent4s@pcworld.com', '134 Rockefeller Trail', 54);
INSERT INTO public.client VALUES (174, 'Aarika MacConnulty', 'amacconnulty4t@over-blog.com', '42 Corben Circle', 64);
INSERT INTO public.client VALUES (175, 'Nadia Tremble', 'ntremble4u@aol.com', '781 Rusk Lane', 59);
INSERT INTO public.client VALUES (176, 'Ade Wyllcock', 'awyllcock4v@dropbox.com', '86633 Warner Way', 50);
INSERT INTO public.client VALUES (177, 'Audrye Chrispin', 'achrispin4w@google.de', '2 Roth Junction', 28);
INSERT INTO public.client VALUES (178, 'Katharyn Chamney', 'kchamney4x@chronoengine.com', '3772 Quincy Parkway', 48);
INSERT INTO public.client VALUES (179, 'Tobiah Wittleton', 'twittleton4y@dmoz.org', '7 Glacier Hill Alley', 85);
INSERT INTO public.client VALUES (180, 'August Alfonsetti', 'aalfonsetti4z@jiathis.com', '34 Sherman Place', 54);
INSERT INTO public.client VALUES (181, 'Elihu Millership', 'emillership50@indiegogo.com', '559 Bultman Circle', 35);
INSERT INTO public.client VALUES (182, 'Luciano Antoney', 'lantoney51@yale.edu', '89 Division Terrace', 18);
INSERT INTO public.client VALUES (183, 'Rurik Danit', 'rdanit52@amazon.co.uk', '88268 Linden Court', 42);
INSERT INTO public.client VALUES (184, 'Stephi McCarver', 'smccarver53@mediafire.com', '3 Onsgard Trail', 43);
INSERT INTO public.client VALUES (185, 'Eduard Freckelton', 'efreckelton54@skype.com', '8178 Straubel Parkway', 50);
INSERT INTO public.client VALUES (186, 'Aldin Frichley', 'africhley55@umn.edu', '81745 Merrick Alley', 63);
INSERT INTO public.client VALUES (187, 'Worthington Brydell', 'wbrydell56@washington.edu', '15396 Garrison Street', 43);
INSERT INTO public.client VALUES (188, 'La verne McEntagart', 'lverne57@goo.gl', '986 Birchwood Hill', 39);
INSERT INTO public.client VALUES (189, 'Mozelle Millimoe', 'mmillimoe58@twitpic.com', '491 Hoffman Crossing', 46);
INSERT INTO public.client VALUES (190, 'Carissa Blanden', 'cblanden59@statcounter.com', '72 Susan Plaza', 53);
INSERT INTO public.client VALUES (191, 'Roth Conochie', 'rconochie5a@altervista.org', '003 Burrows Trail', 59);
INSERT INTO public.client VALUES (192, 'Gordy McConway', 'gmcconway5b@whitehouse.gov', '4681 Roth Street', 84);
INSERT INTO public.client VALUES (193, 'Neel Toffoletto', 'ntoffoletto5c@github.io', '02 Kings Circle', 37);
INSERT INTO public.client VALUES (194, 'Martguerita Gamblin', 'mgamblin5d@wufoo.com', '23 Sundown Park', 69);
INSERT INTO public.client VALUES (195, 'Aurthur McCarlich', 'amccarlich5e@histats.com', '802 Nevada Alley', 31);
INSERT INTO public.client VALUES (196, 'Hillary Tumini', 'htumini5f@google.pl', '06949 Lighthouse Bay Street', 46);
INSERT INTO public.client VALUES (197, 'Mirabel Rivelin', 'mrivelin5g@patch.com', '010 Grayhawk Alley', 33);
INSERT INTO public.client VALUES (198, 'Gianna VanBrugh', 'gvanbrugh5h@php.net', '34 2nd Parkway', 27);
INSERT INTO public.client VALUES (199, 'Donelle Emmins', 'demmins5i@google.com.br', '3510 Hauk Avenue', 75);


--
-- Data for Name: log; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.log VALUES (1, 'Edouard Athridge', 'Noise Cancelling Headphones', 2, 179.9, '2026-05-21 08:08:51.95437');


--
-- Data for Name: order; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."order" VALUES (1, 'Edouard Athridge', 'Bluetooth Keyboard', 2, 59.98);
INSERT INTO public."order" VALUES (2, 'Rory Leckenby', 'USB-C Charger', 20, 309.8);
INSERT INTO public."order" VALUES (3, 'Donelle Emmins', 'tester1', 55, 3685);
INSERT INTO public."order" VALUES (4, 'Rory Leckenby', 'Gaming Monitor', 12, 2159.88);
INSERT INTO public."order" VALUES (5, 'Edouard Athridge', 'Noise Cancelling Headphones', 2, 179.9);


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product VALUES (56, 'External Hard Drive', 23, 64.99);
INSERT INTO public.product VALUES (57, 'Webcam 1080p', 18, 39.9);
INSERT INTO public.product VALUES (58, 'Ergonomic Office Chair', 9, 199);
INSERT INTO public.product VALUES (60, 'Mechanical Keyboard', 37, 84.95);
INSERT INTO public.product VALUES (61, 'Smartphone Holder', 29, 12.95);
INSERT INTO public.product VALUES (62, 'USB Hub 4-Port', 33, 14.99);
INSERT INTO public.product VALUES (63, 'Portable SSD 1TB', 41, 109.9);
INSERT INTO public.product VALUES (64, 'LED Desk Lamp', 26, 22.49);
INSERT INTO public.product VALUES (65, 'Laptop Cooling Pad', 15, 17.8);
INSERT INTO public.product VALUES (66, 'Wireless Earbuds', 47, 49.99);
INSERT INTO public.product VALUES (67, 'Smartwatch Charger', 12, 10);
INSERT INTO public.product VALUES (68, 'Dual Monitor Arm', 8, 59);
INSERT INTO public.product VALUES (69, 'Cable Management Box', 38, 16.75);
INSERT INTO public.product VALUES (70, 'Foldable Laptop Table', 22, 32.95);
INSERT INTO public.product VALUES (71, 'Mini Projector', 17, 129.99);
INSERT INTO public.product VALUES (72, 'Adjustable Phone Stand', 44, 11.49);
INSERT INTO public.product VALUES (73, 'Bluetooth Speaker', 59, 39.99);
INSERT INTO public.product VALUES (74, 'Tablet Stylus Pen', 31, 19.95);
INSERT INTO public.product VALUES (75, 'Desktop Microphone', 16, 54);
INSERT INTO public.product VALUES (76, 'HDMI Cable 2m', 63, 8.99);
INSERT INTO public.product VALUES (77, 'Wireless Router', 40, 74.99);
INSERT INTO public.product VALUES (78, 'Laptop Sleeve 15"', 36, 18.5);
INSERT INTO public.product VALUES (79, 'Screen Cleaning Kit', 25, 6.99);
INSERT INTO public.product VALUES (80, 'Power Strip with USB', 58, 21);
INSERT INTO public.product VALUES (81, 'Smart LED Bulb', 32, 14.25);
INSERT INTO public.product VALUES (82, 'Bluetooth Tracker', 21, 24);
INSERT INTO public.product VALUES (83, 'Portable Bluetooth Printer', 11, 99.99);
INSERT INTO public.product VALUES (84, 'Smart Plug', 30, 13.49);
INSERT INTO public.product VALUES (85, 'Wireless Presenter', 13, 17.99);
INSERT INTO public.product VALUES (86, 'Laptop Backpack', 24, 49.95);
INSERT INTO public.product VALUES (87, 'Streaming Webcam', 6, 58);
INSERT INTO public.product VALUES (88, 'Rechargeable Batteries', 35, 11.75);
INSERT INTO public.product VALUES (89, 'USB Desk Fan', 20, 9.49);
INSERT INTO public.product VALUES (90, 'Mouse Pad with Wrist Support', 43, 7.99);
INSERT INTO public.product VALUES (91, 'Digital Voice Recorder', 5, 39.5);
INSERT INTO public.product VALUES (92, 'External DVD Drive', 28, 33);
INSERT INTO public.product VALUES (93, 'Laser Pointer', 10, 14.99);
INSERT INTO public.product VALUES (94, 'Portable Power Bank', 46, 29.99);
INSERT INTO public.product VALUES (95, 'Laptop Privacy Screen', 39, 35.25);
INSERT INTO public.product VALUES (96, 'Smartphone Gimbal', 7, 119.9);
INSERT INTO public.product VALUES (97, 'HD Webcam Cover', 14, 4.5);
INSERT INTO public.product VALUES (98, 'USB-C to HDMI Adapter', 50, 16.99);
INSERT INTO public.product VALUES (99, 'Surge Protector', 48, 25);
INSERT INTO public.product VALUES (100, 'Portable Whiteboard', 3, 44);
INSERT INTO public.product VALUES (51, 'Wireless Mouse', 35, 19.99);
INSERT INTO public.product VALUES (52, 'Bluetooth Keyboard', 25, 29.99);
INSERT INTO public.product VALUES (53, 'USB-C Charger', 25, 15.49);
INSERT INTO public.product VALUES (59, 'Gaming Monitor', 49, 179.99);
INSERT INTO public.product VALUES (54, 'Noise Cancelling Headphones', 15, 89.95);


--
-- Name: client_client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.client_client_id_seq', 208, true);


--
-- Name: log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.log_id_seq', 2, true);


--
-- Name: order_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.order_order_id_seq', 5, true);


--
-- Name: product_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_product_id_seq', 107, true);


--
-- Name: client client_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pk PRIMARY KEY (id);


--
-- Name: client client_pk_2; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pk_2 UNIQUE (email);


--
-- Name: log log_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.log
    ADD CONSTRAINT log_pkey PRIMARY KEY (id);


--
-- Name: order order_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_pk PRIMARY KEY (id);


--
-- Name: product product_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pk PRIMARY KEY (id);


--
-- Name: product product_pk_2; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pk_2 UNIQUE (name);


--
-- PostgreSQL database dump complete
--

