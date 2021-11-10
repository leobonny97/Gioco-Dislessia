Drop database if exists dislessiagioco;
create database dislessiagioco;
use dislessiagioco;
set global max_allowed_packet=100*1024*1024;

drop table if exists Frase;

create table if not exists Frase(
id integer auto_increment primary key,
livello integer not null
);

drop table if exists Parola;

create table if not exists Parola(
id integer auto_increment primary key,
parola varchar(70) not null
);

drop table if exists Giocatore;

create table if not exists Giocatore(
username varchar(16) primary key,
password varchar(16) not null,
progresso integer not null
);

drop table if exists Errore;

create table if not exists Errore(
id integer auto_increment primary key,
frase integer not null,
parola integer not null,
distrattore integer not null,
giocatore varchar(16) not null,
foreign key (frase) references Frase(id)
on update cascade
on delete cascade,
foreign key (parola) references Parola(id)
on update cascade
on delete cascade,
foreign key (distrattore) references Parola(id)
on update cascade
on delete cascade,
foreign key (giocatore) references Giocatore(username)
on update cascade
on delete cascade
);

drop table if exists Composizione;

create table if not exists Composizione(
id integer auto_increment primary key,
frase integer not null,
parola integer not null,
ordine integer not null,
foreign key (frase) references Frase(id)
on update cascade
on delete cascade,
foreign key (parola) references Parola(id)
on update cascade
on delete cascade
);

drop table if exists Associazione;

create table if not exists Associazione(
id integer auto_increment primary key,
parola integer not null,
distrattore integer not null,
foreign key (parola) references Parola(id)
on update cascade
on delete cascade,
foreign key (distrattore) references Parola(id)
on update cascade
on delete cascade
);