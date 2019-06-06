CREATE TABLE [user]
(
  id_user  int identityprimary key,
  username varchar(150)  not nullunique,
  password varchar(150)  not null,
  email    varchar(150)  not nullunique,
  admin    bit default 0 not null
)
go
CREATE TABLE logging (
id_logging INTEGER PRIMARY KEY  IDENTITY NOT NULL,
[date] DATE NOT NULL,
[hour] TIME NOT NULL,
id_user INTEGER FOREIGN KEY REFERENCES [user](id_user) NOT NULL );

CREATE TABLE lekarz (
  id_lekarz INTEGER PRIMARY KEY NOT NULL IDENTITY(1,1),
  imie VARCHAR(32) NOT NULL,
  nazwisko VARCHAR(64) NOT NULL,
  specjalizacja VARCHAR(128) NOT NULL,
);

CREATE TABLE pacjent (
  id_pacjent INTEGER PRIMARY KEY NOT NULL IDENTITY(1,1),
  imie VARCHAR(32) NOT NULL,
  nazwisko VARCHAR(64) NOT NULL,
);

CREATE TABLE pokoj (
  id_pokoj INTEGER PRIMARY KEY NOT NULL IDENTITY(1,1),
  nr_pokoju INTEGER NOT NULL,
);

CREATE TABLE recepta (
  id_recepta INTEGER PRIMARY KEY NOT NULL IDENTITY(1,1),
  opis VARCHAR(1024) NOT NULL,
  data_wystawienia DATE NULL,
  id_pacjent INTEGER FOREIGN KEY REFERENCES pacjent(id_pacjent),
  id_lekarz INTEGER FOREIGN KEY REFERENCES lekarz(id_lekarz)
);

CREATE TABLE skierowanie (
  id_skierowanie INTEGER PRIMARY KEY NOT NULL IDENTITY(1,1),
  opis VARCHAR(1024) NOT NULL,
  data_wystawienia DATE NOT NULL,
    id_pacjent INTEGER FOREIGN KEY REFERENCES pacjent(id_pacjent),
  id_lekarz INTEGER FOREIGN KEY REFERENCES lekarz(id_lekarz)
);

CREATE TABLE wizyta (
  id_wizyta INTEGER PRIMARY KEY NOT NULL IDENTITY(1,1),
  data_wizyty DATETIME NOT NULL,
  id_pacjent INTEGER FOREIGN KEY REFERENCES pacjent(id_pacjent),
  id_lekarz INTEGER FOREIGN KEY REFERENCES lekarz(id_lekarz),
  id_pokoj INTEGER FOREIGN KEY REFERENCES pokoj(id_pokoj)
);

CREATE TABLE zwolnienie (
  id_zwolnienie INTEGER PRIMARY KEY NOT NULL IDENTITY(1,1),
  opis VARCHAR(1024) NOT NULL,
  data_wystawienia DATE NOT NULL,
  poczatek_zwolnienia DATE NOT NULL,
  koniec_zwolnienia DATE NOT NULL,
  id_pacjent INTEGER FOREIGN KEY REFERENCES pacjent(id_pacjent),
  id_lekarz INTEGER FOREIGN KEY REFERENCES lekarz(id_lekarz)
);

INSERT INTO pacjent (imie, nazwisko) VALUES
('Marcin', 'Borysiak'),
('Marcin', 'Borysiewicz'),
('Krzysztof', 'Bohdanowicz'),
('Stefan', 'Stefañski'),
('Mateusz', 'Wirzba'),
('Robert', 'Fidytek'),
('Maciej', 'Bela'),
('Adam', 'Mickiewicz'),
('Bartosz', 'Kordiañski'),
('Albert', 'Wyszyñski'),
('Mi³osz', 'Koss'),
('Stefan', 'Stefanowicz');

INSERT INTO lekarz(imie, nazwisko, specjalizacja) VALUES
('Mateusz', 'Wirzba', 'dermatolog'),
('Robert', 'Fidytek', 'dermatolog'),
('Maciej', 'Bela', 'endokrynolog'),
('Adam', 'Mickiewicz', 'endokrynolog'),
('Bartosz', 'Kordiañski', 'okulista'),
('Albert', 'Wyszyñski', 'pediatra'),
('Mi³osz', 'Koss', 'lekarz rodzinny'),
('Stefan', 'Stefanowicz', 'lekarz rodzinny');

INSERT INTO pokoj(nr_pokoju) VALUES
(22),
(23),
(55),
(56),
(120);

INSERT INTO wizyta(data_wizyty, id_lekarz, id_pacjent, id_pokoj) VALUES
('24-11-2019 10:34:00', 1, 1, 1),
('24-12-2024 09:43:12', 1, 2, 4),
('15-12-2034 11:45:21', 3, 3, 3),
('13-12-2035 13:41:21', 4, 4, 4),
('26-11-2019 10:34:00', 5, 5, 1),
('30-12-2024 09:43:12', 6, 6, 3),
('10-12-2034 11:45:21', 7, 7, 4),
('12-12-2035 13:41:21', 8, 8, 3),
('24-11-2019 10:34:00', 1, 8, 1),
('2-12-2024 09:43:12', 2, 9, 3),
('1-12-2034 11:45:21', 2, 10, 4),
('4-12-2035 13:41:21', 3, 11, 3),
('27-11-2019 10:34:00', 6, 12, 1),
('19-12-2024 09:43:12', 7, 2, 4),
('29-12-2034 11:45:21', 2, 1, 3),
('30-12-2035 13:41:21', 1, 2, 3);
GO
CREATE PROCEDURE wizyty AS
SELECT wizyta.id_pacjent, pacjent.nazwisko as pacjent_nazwisko, wizyta.data_wizyty, lekarz.nazwisko as lekarz_nazwisko, lekarz.specjalizacja as lekarz_specjalizacja , pokoj.id_pokoj
FROM wizyta INNER JOIN pacjent
                       on wizyta.id_pacjent = pacjent.id_pacjent
            INNER JOIN lekarz
                       on wizyta.id_lekarz = lekarz.id_lekarz
            INNER JOIN pokoj
                       on wizyta.id_pokoj = pokoj.id_pokoj;
GO
INSERT INTO recepta(opis, data_wystawienia, id_pacjent, id_lekarz) VALUES ('Fajna receptax', '10-11-2018 09:43:12', 6, 5);
GO
CREATE TRIGGER delete_user 
ON [user]
INSTEAD OF DELETE
AS
BEGIN
DELETE FROM logging WHERE logging.id_user IN (SELECT deleted.id_user FROM deleted);
DELETE FROM [user] WHERE id_user IN (SELECT deleted.id_user FROM deleted);
END
GO
INSERT INTO [user] (username, password, email, admin) VALUES ('normalUser', '1234', 'supermail@gmail.com', 0), ('normalUser2', '1234', 'supermail2@gmail.com', 0), ('normalUser4', '1234', 'supermail4@gmail.com', 0), ('normalUser3', '1234', 'supermail3@gmail.com', 0),
                                                             ('superUser', '1234', 'niesupermail@wp.pl', 1);
GO
