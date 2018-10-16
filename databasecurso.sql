--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.10
-- Dumped by pg_dump version 10.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: address; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.address (
    id integer NOT NULL,
    cep character varying(255),
    district character varying(255),
    name character varying(255),
    number integer,
    city_id integer,
    client_id integer
);


--
-- Name: address_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.address_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: address_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.address_id_seq OWNED BY public.address.id;


--
-- Name: category; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.category (
    id integer NOT NULL,
    name character varying(80)
);


--
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;


--
-- Name: city; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.city (
    id integer NOT NULL,
    name character varying(255),
    state_id integer
);


--
-- Name: city_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.city_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: city_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.city_id_seq OWNED BY public.city.id;


--
-- Name: client; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.client (
    id integer NOT NULL,
    client_type integer,
    email character varying(255),
    name character varying(255)
);


--
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.client_id_seq OWNED BY public.client.id;


--
-- Name: client_orders; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.client_orders (
    id integer NOT NULL,
    date timestamp without time zone,
    client_id integer,
    delivery_address_id integer
);


--
-- Name: client_orders_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.client_orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: client_orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.client_orders_id_seq OWNED BY public.client_orders.id;


--
-- Name: credit_card_payment; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.credit_card_payment (
    instalments integer,
    order_id integer NOT NULL
);


--
-- Name: ordered_item; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.ordered_item (
    discount double precision,
    price double precision,
    quantity integer,
    order_id integer NOT NULL,
    product_id integer NOT NULL
);


--
-- Name: payment; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.payment (
    order_id integer NOT NULL,
    status integer
);


--
-- Name: product; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying(255),
    value double precision
);


--
-- Name: product_category; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.product_category (
    product_id integer NOT NULL,
    category_id integer NOT NULL
);


--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- Name: state; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.state (
    id integer NOT NULL,
    name character varying(255)
);


--
-- Name: state_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.state_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: state_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.state_id_seq OWNED BY public.state.id;


--
-- Name: telefone; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.telefone (
    client_id integer NOT NULL,
    contact_numbers character varying(255)
);


--
-- Name: ticket_payment; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.ticket_payment (
    deadline_date timestamp without time zone,
    payment_date timestamp without time zone,
    order_id integer NOT NULL
);


--
-- Name: address id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.address ALTER COLUMN id SET DEFAULT nextval('public.address_id_seq'::regclass);


--
-- Name: category id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);


--
-- Name: city id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.city ALTER COLUMN id SET DEFAULT nextval('public.city_id_seq'::regclass);


--
-- Name: client id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.client ALTER COLUMN id SET DEFAULT nextval('public.client_id_seq'::regclass);


--
-- Name: client_orders id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.client_orders ALTER COLUMN id SET DEFAULT nextval('public.client_orders_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- Name: state id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.state ALTER COLUMN id SET DEFAULT nextval('public.state_id_seq'::regclass);


--
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.address (id, cep, district, name, number, city_id, client_id) FROM stdin;
1	43343343	Massa	Rua TX	3	2	1
2	8787878	Farol	Rua TY	55	2	1
3	545445	Primavera	Rua MY	155	3	2
\.


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.category (id, name) FROM stdin;
1	asdfasdf
2	asdfasdf
3	asdfasdfo
4	asdfasdf
5	asdfas
6	ghgh e Cia
7	kjkjhjg
8	dtydg
\.


--
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.city (id, name, state_id) FROM stdin;
1	Urbelândia	1
2	Maceió	2
3	Arapiraca	2
\.


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.client (id, client_type, email, name) FROM stdin;
1	1	thiagobrunoms@gmail.com	Thiago de Sales
2	1	miryansoaresrocha@gmail.com	Míryan Soares
\.


--
-- Data for Name: client_orders; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.client_orders (id, date, client_id, delivery_address_id) FROM stdin;
1	2017-09-30 10:32:00	1	1
2	2017-10-10 19:35:00	2	3
\.


--
-- Data for Name: credit_card_payment; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.credit_card_payment (instalments, order_id) FROM stdin;
6	1
\.


--
-- Data for Name: ordered_item; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.ordered_item (discount, price, quantity, order_id, product_id) FROM stdin;
0	2000	1	1	1
0	80	2	1	3
100	800	1	2	2
\.


--
-- Data for Name: payment; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.payment (order_id, status) FROM stdin;
1	2
2	1
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.product (id, name, value) FROM stdin;
1	Computador	2000
2	Mouse	30.3999999999999986
3	Processador	1230.33999999999992
4	Geladeira	2345.34000000000015
\.


--
-- Data for Name: product_category; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.product_category (product_id, category_id) FROM stdin;
1	1
2	1
3	1
4	2
\.


--
-- Data for Name: state; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.state (id, name) FROM stdin;
1	Minas Gerais
2	Alagoas
\.


--
-- Data for Name: telefone; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.telefone (client_id, contact_numbers) FROM stdin;
1	82999431690
1	8233262884
2	82999328297
\.


--
-- Data for Name: ticket_payment; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.ticket_payment (deadline_date, payment_date, order_id) FROM stdin;
2017-10-20 00:00:00	\N	2
\.


--
-- Name: address_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.address_id_seq', 3, true);


--
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.category_id_seq', 8, true);


--
-- Name: city_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.city_id_seq', 3, true);


--
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.client_id_seq', 2, true);


--
-- Name: client_orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.client_orders_id_seq', 2, true);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.product_id_seq', 4, true);


--
-- Name: state_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.state_id_seq', 2, true);


--
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: city city_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT city_pkey PRIMARY KEY (id);


--
-- Name: client_orders client_orders_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.client_orders
    ADD CONSTRAINT client_orders_pkey PRIMARY KEY (id);


--
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- Name: credit_card_payment credit_card_payment_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.credit_card_payment
    ADD CONSTRAINT credit_card_payment_pkey PRIMARY KEY (order_id);


--
-- Name: ordered_item ordered_item_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ordered_item
    ADD CONSTRAINT ordered_item_pkey PRIMARY KEY (order_id, product_id);


--
-- Name: payment payment_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (order_id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: state state_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.state
    ADD CONSTRAINT state_pkey PRIMARY KEY (id);


--
-- Name: ticket_payment ticket_payment_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ticket_payment
    ADD CONSTRAINT ticket_payment_pkey PRIMARY KEY (order_id);


--
-- Name: client uk_bfgjs3fem0hmjhvih80158x29; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT uk_bfgjs3fem0hmjhvih80158x29 UNIQUE (email);


--
-- Name: product_category fk2k3smhbruedlcrvu6clued06x; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT fk2k3smhbruedlcrvu6clued06x FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: payment fk4b9oo309jpimby44a7rpbbvec; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.payment
    ADD CONSTRAINT fk4b9oo309jpimby44a7rpbbvec FOREIGN KEY (order_id) REFERENCES public.client_orders(id);


--
-- Name: credit_card_payment fk4c47tpj4p88u9ty289jd32lrw; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.credit_card_payment
    ADD CONSTRAINT fk4c47tpj4p88u9ty289jd32lrw FOREIGN KEY (order_id) REFERENCES public.payment(order_id);


--
-- Name: city fk6p2u50v8fg2y0js6djc6xanit; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.city
    ADD CONSTRAINT fk6p2u50v8fg2y0js6djc6xanit FOREIGN KEY (state_id) REFERENCES public.state(id);


--
-- Name: address fk7156ty2o5atyuy9f6kuup9dna; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT fk7156ty2o5atyuy9f6kuup9dna FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- Name: ticket_payment fk7my973qoq08e4pl2gxj69fwxg; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ticket_payment
    ADD CONSTRAINT fk7my973qoq08e4pl2gxj69fwxg FOREIGN KEY (order_id) REFERENCES public.payment(order_id);


--
-- Name: ordered_item fkaq0vwxoytp1o7acx7bljwr4u; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ordered_item
    ADD CONSTRAINT fkaq0vwxoytp1o7acx7bljwr4u FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- Name: telefone fkcqv3tgoihbusyar7eow6b3uvj; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT fkcqv3tgoihbusyar7eow6b3uvj FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- Name: client_orders fkjwct7foag4dx0nrmipr5w5prx; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.client_orders
    ADD CONSTRAINT fkjwct7foag4dx0nrmipr5w5prx FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- Name: product_category fkkud35ls1d40wpjb5htpp14q4e; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT fkkud35ls1d40wpjb5htpp14q4e FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- Name: address fkpo044ng5x4gynb291cv24vtea; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT fkpo044ng5x4gynb291cv24vtea FOREIGN KEY (city_id) REFERENCES public.city(id);


--
-- Name: ordered_item fkqoyq7rqganaujcurda49r86n1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ordered_item
    ADD CONSTRAINT fkqoyq7rqganaujcurda49r86n1 FOREIGN KEY (order_id) REFERENCES public.client_orders(id);


--
-- Name: client_orders fkw536esoxaxiuwvxpi14rrqeh; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.client_orders
    ADD CONSTRAINT fkw536esoxaxiuwvxpi14rrqeh FOREIGN KEY (delivery_address_id) REFERENCES public.address(id);


--
-- PostgreSQL database dump complete
--

