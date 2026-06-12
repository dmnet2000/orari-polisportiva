CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    phone_number VARCHAR(20),
    role VARCHAR(20) NOT NULL DEFAULT 'USER',
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE palestre (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) UNIQUE NOT NULL,
    descrizione TEXT,
    indirizzo VARCHAR(255) NOT NULL,
    citta VARCHAR(100),
    cap VARCHAR(10),
    telefono VARCHAR(20),
    email VARCHAR(100),
    num_campi INTEGER NOT NULL DEFAULT 1,
    attiva BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE discipline (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) UNIQUE NOT NULL,
    descrizione TEXT,
    num_giocatori_min INTEGER,
    num_giocatori_max INTEGER,
    attiva BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE gruppi (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descrizione TEXT,
    disciplina_id BIGINT NOT NULL,
    num_membri INTEGER,
    istruttore VARCHAR(100),
    attivo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (disciplina_id) REFERENCES discipline(id)
);

CREATE TABLE orari (
    id SERIAL PRIMARY KEY,
    palestra_id BIGINT NOT NULL,
    gruppo_id BIGINT NOT NULL,
    numero_campo INTEGER,
    giorno_settimana VARCHAR(20) NOT NULL,
    ora_inizio TIME NOT NULL,
    ora_fine TIME NOT NULL,
    capienza_massima INTEGER,
    posti_disponibili INTEGER,
    attivo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (palestra_id) REFERENCES palestre(id),
    FOREIGN KEY (gruppo_id) REFERENCES gruppi(id)
);

CREATE TABLE prenotazioni (
    id SERIAL PRIMARY KEY,
    orario_id BIGINT NOT NULL,
    utente_id BIGINT NOT NULL,
    stato VARCHAR(20) NOT NULL DEFAULT 'CONFERMATA',
    numero_posti INTEGER NOT NULL DEFAULT 1,
    note TEXT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (orario_id) REFERENCES orari(id),
    FOREIGN KEY (utente_id) REFERENCES users(id)
);

CREATE INDEX idx_gruppi_disciplina ON gruppi(disciplina_id);
CREATE INDEX idx_orari_palestra ON orari(palestra_id);
CREATE INDEX idx_orari_gruppo ON orari(gruppo_id);
CREATE INDEX idx_prenotazioni_orario ON prenotazioni(orario_id);
CREATE INDEX idx_prenotazioni_utente ON prenotazioni(utente_id);
