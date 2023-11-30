BEGIN;

DROP TABLE IF EXISTS public.transaction, public.user, public.account, public.bank;

CREATE TABLE IF NOT EXISTS public.transaction
(
    id bigserial NOT NULL,
    amount double precision DEFAULT 0,
    account_id bigint,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.account
(
    id bigserial NOT NULL,
    balance double precision NOT NULL DEFAULT 0,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.user
(
    id bigserial NOT NULL,
    name VARCHAR(100),
    account_id bigint,
    bank_id bigint,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.bank
(
    id bigserial NOT NULL,
    name VARCHAR(100),
    account_number VARCHAR(20) UNIQUE,
    location VARCHAR(255),
    balance DECIMAL(10, 2),
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.transaction
    ADD CONSTRAINT account_id FOREIGN KEY (account_id)
    REFERENCES public.account (id) MATCH SIMPLE
    ON UPDATE RESTRICT
    ON DELETE RESTRICT
    NOT VALID;


ALTER TABLE IF EXISTS public.user
    ADD CONSTRAINT account_id FOREIGN KEY (account_id)
    REFERENCES public.account (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE
    NOT VALID;


ALTER TABLE IF EXISTS public.user
    ADD CONSTRAINT bank_id FOREIGN KEY (bank_id)
    REFERENCES public.bank (id) MATCH SIMPLE
    ON UPDATE RESTRICT
    ON DELETE RESTRICT
    NOT VALID;

insert into bank (id, name, account_number, location, balance) values (1, 'Little, Koelpin and Effertz', 26, '03078 Butternut Terrace', 377);
insert into bank (id, name, account_number, location, balance) values (2, 'Leuschke-Jacobi', 7, '855 Lindbergh Terrace', 557);
insert into bank (id, name, account_number, location, balance) values (3, 'Wyman, Torphy and Morar', 9, '5155 Kim Drive', 539);
insert into bank (id, name, account_number, location, balance) values (4, 'Smitham LLC', 27, '5866 La Follette Park', 458);
insert into bank (id, name, account_number, location, balance) values (5, 'Carter-Mann', 37, '906 Melrose Plaza', 647);
insert into bank (id, name, account_number, location, balance) values (6, 'Collier LLC', 80, '84675 Kedzie Hill', 54);
insert into bank (id, name, account_number, location, balance) values (7, 'Beahan-Treutel', 20, '8218 Cherokee Street', 546);
insert into bank (id, name, account_number, location, balance) values (8, 'Abshire and Sons', 81, '2 Morning Circle', 991);
insert into bank (id, name, account_number, location, balance) values (9, 'Kuhlman Group', 69, '849 Brickson Park Circle', 170);
insert into bank (id, name, account_number, location, balance) values (10, 'Hilll, Dare and Kiehn', 14, '0 Reinke Parkway', 755);
insert into bank (id, name, account_number, location, balance) values (11, 'Volkman-McKenzie', 2, '7040 Vernon Park', 517);
insert into bank (id, name, account_number, location, balance) values (12, 'Osinski, Waelchi and Brekke', 93, '03 Lillian Center', 941);
insert into bank (id, name, account_number, location, balance) values (13, 'Hammes Inc', 34, '8287 Ronald Regan Street', 242);
insert into bank (id, name, account_number, location, balance) values (14, 'Bednar-Reynolds', 57, '238 Little Fleur Terrace', 270);
insert into bank (id, name, account_number, location, balance) values (15, 'Goldner Inc', 15, '41 Jenifer Center', 543);
insert into bank (id, name, account_number, location, balance) values (16, 'Satterfield and Sons', 79, '2610 Dennis Plaza', 737);
insert into bank (id, name, account_number, location, balance) values (17, 'Heller Inc', 45, '26 Tony Center', 815);
insert into bank (id, name, account_number, location, balance) values (18, 'Armstrong-Torp', 84, '3 Emmet Crossing', 242);
insert into bank (id, name, account_number, location, balance) values (19, 'Dietrich, Lockman and Herman', 65, '25 Dryden Way', 367);
insert into bank (id, name, account_number, location, balance) values (20, 'Konopelski-Hessel', 94, '32665 Westport Plaza', 253);

insert into account (id, balance) values (1, 91);
insert into account (id, balance) values (2, 64);
insert into account (id, balance) values (3, 62);
insert into account (id, balance) values (4, 96);
insert into account (id, balance) values (5, 96);
insert into account (id, balance) values (6, 94);
insert into account (id, balance) values (7, 95);
insert into account (id, balance) values (8, 66);
insert into account (id, balance) values (9, 57);
insert into account (id, balance) values (10, 84);
insert into account (id, balance) values (11, 57);
insert into account (id, balance) values (12, 56);
insert into account (id, balance) values (13, 50);
insert into account (id, balance) values (14, 72);
insert into account (id, balance) values (15, 79);
insert into account (id, balance) values (16, 70);
insert into account (id, balance) values (17, 97);
insert into account (id, balance) values (18, 96);
insert into account (id, balance) values (19, 64);
insert into account (id, balance) values (20, 71);
insert into account (id, balance) values (21, 52);
insert into account (id, balance) values (22, 93);
insert into account (id, balance) values (23, 58);
insert into account (id, balance) values (24, 72);
insert into account (id, balance) values (25, 75);
insert into account (id, balance) values (26, 79);
insert into account (id, balance) values (27, 51);
insert into account (id, balance) values (28, 67);
insert into account (id, balance) values (29, 99);
insert into account (id, balance) values (30, 88);
insert into account (id, balance) values (31, 78);
insert into account (id, balance) values (32, 91);
insert into account (id, balance) values (33, 58);
insert into account (id, balance) values (34, 92);
insert into account (id, balance) values (35, 96);
insert into account (id, balance) values (36, 78);
insert into account (id, balance) values (37, 66);
insert into account (id, balance) values (38, 99);
insert into account (id, balance) values (39, 83);
insert into account (id, balance) values (40, 60);

insert into transaction (id, amount, account_id) values (1, 34, 25);
insert into transaction (id, amount, account_id) values (2, 42, 29);
insert into transaction (id, amount, account_id) values (3, 41, 26);
insert into transaction (id, amount, account_id) values (4, 45, 37);
insert into transaction (id, amount, account_id) values (5, 38, 1);
insert into transaction (id, amount, account_id) values (6, 39, 32);
insert into transaction (id, amount, account_id) values (7, 50, 35);
insert into transaction (id, amount, account_id) values (8, 34, 18);
insert into transaction (id, amount, account_id) values (9, 46, 30);
insert into transaction (id, amount, account_id) values (10, 42, 32);
insert into transaction (id, amount, account_id) values (11, 33, 15);
insert into transaction (id, amount, account_id) values (12, 39, 5);
insert into transaction (id, amount, account_id) values (13, 48, 23);
insert into transaction (id, amount, account_id) values (14, 50, 9);
insert into transaction (id, amount, account_id) values (15, 38, 21);
insert into transaction (id, amount, account_id) values (16, 32, 25);
insert into transaction (id, amount, account_id) values (17, 33, 7);
insert into transaction (id, amount, account_id) values (18, 43, 1);
insert into transaction (id, amount, account_id) values (19, 42, 6);
insert into transaction (id, amount, account_id) values (20, 34, 21);
insert into transaction (id, amount, account_id) values (21, 49, 3);
insert into transaction (id, amount, account_id) values (22, 43, 17);
insert into transaction (id, amount, account_id) values (23, 42, 26);
insert into transaction (id, amount, account_id) values (24, 49, 33);
insert into transaction (id, amount, account_id) values (25, 47, 28);
insert into transaction (id, amount, account_id) values (26, 41, 3);
insert into transaction (id, amount, account_id) values (27, 34, 17);
insert into transaction (id, amount, account_id) values (28, 45, 32);
insert into transaction (id, amount, account_id) values (29, 32, 10);
insert into transaction (id, amount, account_id) values (30, 36, 4);
insert into transaction (id, amount, account_id) values (31, 41, 37);
insert into transaction (id, amount, account_id) values (32, 44, 11);
insert into transaction (id, amount, account_id) values (33, 45, 32);
insert into transaction (id, amount, account_id) values (34, 37, 6);
insert into transaction (id, amount, account_id) values (35, 44, 4);
insert into transaction (id, amount, account_id) values (36, 38, 27);
insert into transaction (id, amount, account_id) values (37, 50, 14);
insert into transaction (id, amount, account_id) values (38, 38, 3);
insert into transaction (id, amount, account_id) values (39, 32, 36);
insert into transaction (id, amount, account_id) values (40, 32, 32);
insert into transaction (id, amount, account_id) values (41, 49, 40);
insert into transaction (id, amount, account_id) values (42, 42, 27);
insert into transaction (id, amount, account_id) values (43, 45, 31);
insert into transaction (id, amount, account_id) values (44, 43, 15);
insert into transaction (id, amount, account_id) values (45, 47, 30);
insert into transaction (id, amount, account_id) values (46, 41, 39);
insert into transaction (id, amount, account_id) values (47, 33, 3);
insert into transaction (id, amount, account_id) values (48, 49, 12);
insert into transaction (id, amount, account_id) values (49, 48, 3);
insert into transaction (id, amount, account_id) values (50, 50, 2);
insert into transaction (id, amount, account_id) values (51, 39, 29);
insert into transaction (id, amount, account_id) values (52, 38, 33);
insert into transaction (id, amount, account_id) values (53, 45, 14);
insert into transaction (id, amount, account_id) values (54, 47, 33);
insert into transaction (id, amount, account_id) values (55, 48, 19);
insert into transaction (id, amount, account_id) values (56, 40, 9);
insert into transaction (id, amount, account_id) values (57, 31, 31);
insert into transaction (id, amount, account_id) values (58, 37, 6);
insert into transaction (id, amount, account_id) values (59, 37, 10);
insert into transaction (id, amount, account_id) values (60, 50, 25);
insert into transaction (id, amount, account_id) values (61, 32, 1);
insert into transaction (id, amount, account_id) values (62, 37, 40);
insert into transaction (id, amount, account_id) values (63, 37, 26);
insert into transaction (id, amount, account_id) values (64, 46, 17);
insert into transaction (id, amount, account_id) values (65, 41, 15);
insert into transaction (id, amount, account_id) values (66, 32, 15);
insert into transaction (id, amount, account_id) values (67, 35, 3);
insert into transaction (id, amount, account_id) values (68, 31, 19);
insert into transaction (id, amount, account_id) values (69, 43, 30);
insert into transaction (id, amount, account_id) values (70, 32, 11);
insert into transaction (id, amount, account_id) values (71, 39, 1);
insert into transaction (id, amount, account_id) values (72, 33, 36);
insert into transaction (id, amount, account_id) values (73, 49, 20);
insert into transaction (id, amount, account_id) values (74, 34, 18);
insert into transaction (id, amount, account_id) values (75, 39, 17);
insert into transaction (id, amount, account_id) values (76, 43, 24);
insert into transaction (id, amount, account_id) values (77, 47, 33);
insert into transaction (id, amount, account_id) values (78, 45, 11);
insert into transaction (id, amount, account_id) values (79, 47, 20);
insert into transaction (id, amount, account_id) values (80, 50, 35);

insert into public.user (id, name, account_id, bank_id) values (1, 'Violette', 1, 3);
insert into public.user (id, name, account_id, bank_id) values (2, 'Jessica', 2, 3);
insert into public.user (id, name, account_id, bank_id) values (3, 'Vincenz', 3, 6);
insert into public.user (id, name, account_id, bank_id) values (4, 'Amalita', 4, 5);
insert into public.user (id, name, account_id, bank_id) values (5, 'Carmelia', 5, 2);
insert into public.user (id, name, account_id, bank_id) values (6, 'Jen', 6, 8);
insert into public.user (id, name, account_id, bank_id) values (7, 'Natka', 7, 2);
insert into public.user (id, name, account_id, bank_id) values (8, 'Karlotte', 8, 2);
insert into public.user (id, name, account_id, bank_id) values (9, 'Becca', 9, 5);
insert into public.user (id, name, account_id, bank_id) values (10, 'Mindy', 10, 7);
insert into public.user (id, name, account_id, bank_id) values (11, 'Merrick', 11, 5);
insert into public.user (id, name, account_id, bank_id) values (12, 'Garret', 12, 2);
insert into public.user (id, name, account_id, bank_id) values (13, 'Ford', 13, 10);
insert into public.user (id, name, account_id, bank_id) values (14, 'Garrick', 14, 8);
insert into public.user (id, name, account_id, bank_id) values (15, 'Garvey', 15, 2);
insert into public.user (id, name, account_id, bank_id) values (16, 'Madelaine', 16, 10);
insert into public.user (id, name, account_id, bank_id) values (17, 'Rafael', 17, 4);
insert into public.user (id, name, account_id, bank_id) values (18, 'Ted', 18, 10);
insert into public.user (id, name, account_id, bank_id) values (19, 'Euell', 19, 10);
insert into public.user (id, name, account_id, bank_id) values (20, 'Rodolfo', 20, 7);
insert into public.user (id, name, account_id, bank_id) values (21, 'Ramsay', 21, 2);
insert into public.user (id, name, account_id, bank_id) values (22, 'Dollie', 22, 10);
insert into public.user (id, name, account_id, bank_id) values (23, 'Alla', 23, 9);
insert into public.user (id, name, account_id, bank_id) values (24, 'Georgena', 24, 5);
insert into public.user (id, name, account_id, bank_id) values (25, 'Myrwyn', 25, 2);
insert into public.user (id, name, account_id, bank_id) values (26, 'Papagena', 26, 2);
insert into public.user (id, name, account_id, bank_id) values (27, 'Loy', 27, 5);
insert into public.user (id, name, account_id, bank_id) values (28, 'Fritz', 28, 5);
insert into public.user (id, name, account_id, bank_id) values (29, 'Nananne', 29, 9);
insert into public.user (id, name, account_id, bank_id) values (30, 'Harcourt', 30, 2);
insert into public.user (id, name, account_id, bank_id) values (31, 'Rhoda', 31, 7);
insert into public.user (id, name, account_id, bank_id) values (32, 'Giffard', 32, 3);
insert into public.user (id, name, account_id, bank_id) values (33, 'Beau', 33, 1);
insert into public.user (id, name, account_id, bank_id) values (34, 'Esmeralda', 34, 2);
insert into public.user (id, name, account_id, bank_id) values (35, 'Paulita', 35, 3);
insert into public.user (id, name, account_id, bank_id) values (36, 'Marni', 36, 5);
insert into public.user (id, name, account_id, bank_id) values (37, 'Myer', 37, 7);
insert into public.user (id, name, account_id, bank_id) values (38, 'Haven', 38, 1);
insert into public.user (id, name, account_id, bank_id) values (39, 'Jeane', 39, 8);
insert into public.user (id, name, account_id, bank_id) values (40, 'Arvin', 40, 3);

END;
