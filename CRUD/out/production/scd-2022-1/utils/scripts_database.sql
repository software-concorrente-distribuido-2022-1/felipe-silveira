CREATE DATABASE "scd-2022-01"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE TABLE public.endereco
(
    rua text,
    bairro text,
    cidade text,
    cep text,
    id serial NOT NULL,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.endereco
    OWNER to postgres;

CREATE TABLE public.trabalho
(
    id serial NOT NULL,
    cargo text,
    salario double precision,
    CONSTRAINT trabalho_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.trabalho
    OWNER to postgres;

CREATE TABLE public.pessoa
(
    id serial NOT NULL,
    endereco_id bigint,
    trabalho_id bigint,
    idade bigint,
    nome text,
    cpf text,
    CONSTRAINT endereco_id_fk FOREIGN KEY (endereco_id)
        REFERENCES public.endereco (id) MATCH SIMPLE,
    CONSTRAINT trabalho_id_fk FOREIGN KEY (trabalho_id)
        REFERENCES public.trabalho (id) MATCH SIMPLE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.pessoa
    OWNER to postgres;


CREATE TABLE public.usuarios
(
    id serial NOT NULL,
    username text,
    password text,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.usuarios
    OWNER to postgres;


CREATE TABLE public.logacesso
(
    id serial NOT NULL,
    user_id bigint NOT NULL,
    data text NOT NULL,
    CONSTRAINT logacesso_pkey PRIMARY KEY (id),
	 CONSTRAINT user_id_fk FOREIGN KEY (user_id)
        REFERENCES public.usuarios (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.logacesso
    OWNER to postgres;



CREATE TABLE public.logoperacao
(
    id serial NOT NULL,
    user_id bigint NOT NULL,
    data text NOT NULL,
	operacao text NOT NULL,
	tabela text NOT NULL,
	dados_inserido text,
	dados_anteriores text,
    CONSTRAINT logoperacao_pkey PRIMARY KEY (id),
	 CONSTRAINT user_id_fk FOREIGN KEY (user_id)
        REFERENCES public.usuarios (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.logacesso
    OWNER to postgres;