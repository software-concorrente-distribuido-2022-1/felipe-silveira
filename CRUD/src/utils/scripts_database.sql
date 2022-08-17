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

INSERT INTO public.usuarios(
	username, password)
	VALUES ('1', '1'),
	 ('2', '2'),
	 ('3', '3'),
	 ('4', '4'),
	 ('5', '5'),
	 ('6', '6'),
	 ('7', '7'),
	 ('8', '8'),
	 ('9', '9'),
	 ('10', '10'),
	 ('11', '11'),
	 ('12', '12'),
	 ('13', '13'),
	 ('14', '14'),
	 ('15', '15'),
	 ('16', '16'),
	 ('17', '17'),
	 ('18', '18'),
	 ('19', '19'),
	 ('20', '20'),
	 ('21', '21'),
	 ('22', '22'),
	 ('23', '23'),
	 ('24', '24'),
	 ('25', '25'),
	 ('26', '26'),
	 ('27', '27'),
	 ('28', '28'),
	 ('29', '29'),
	 ('30', '30'),
	 ('31', '31'),
	 ('32', '32'),
	 ('33', '33'),
	 ('34', '34'),
	 ('35', '35'),
	 ('36', '36'),
	 ('37', '37'),
	 ('38', '38'),
	 ('39', '39'),
	 ('40', '40'),
	 ('41', '41'),
	 ('42', '42'),
	 ('43', '43'),
	 ('44', '44'),
	 ('45', '45'),
	 ('46', '46'),
	 ('47', '47'),
	 ('48', '48'),
	 ('49', '49'),
	 ('50', '50');