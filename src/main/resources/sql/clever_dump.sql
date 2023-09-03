--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

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
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account (
    id bigint NOT NULL,
    balance double precision DEFAULT 0 NOT NULL
);


ALTER TABLE public.account OWNER TO postgres;

--
-- Name: account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.account_id_seq OWNER TO postgres;

--
-- Name: account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.account_id_seq OWNED BY public.account.id;


--
-- Name: bank; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bank (
    id bigint NOT NULL,
    name character varying(100)
);


ALTER TABLE public.bank OWNER TO postgres;

--
-- Name: bank_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bank_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bank_id_seq OWNER TO postgres;

--
-- Name: bank_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bank_id_seq OWNED BY public.bank.id;


--
-- Name: transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transaction (
    id bigint NOT NULL,
    amount double precision DEFAULT 0,
    account_id bigint
);


ALTER TABLE public.transaction OWNER TO postgres;

--
-- Name: transaction_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transaction_id_seq OWNER TO postgres;

--
-- Name: transaction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transaction_id_seq OWNED BY public.transaction.id;


--
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    id bigint NOT NULL,
    name character varying(100),
    account_id bigint,
    bank_id bigint
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_id_seq OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_id_seq OWNED BY public."user".id;


--
-- Name: account id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account ALTER COLUMN id SET DEFAULT nextval('public.account_id_seq'::regclass);


--
-- Name: bank id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bank ALTER COLUMN id SET DEFAULT nextval('public.bank_id_seq'::regclass);


--
-- Name: transaction id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction ALTER COLUMN id SET DEFAULT nextval('public.transaction_id_seq'::regclass);


--
-- Name: user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);


--
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.account (id, balance) FROM stdin;
1	91
2	64
3	62
4	96
5	96
6	94
7	95
8	66
9	57
10	84
11	57
12	56
13	50
14	72
15	79
16	70
17	97
18	96
19	64
20	71
21	52
22	93
23	58
24	72
25	75
26	79
27	51
28	67
29	99
30	88
31	78
32	91
33	58
34	92
35	96
36	78
37	66
38	99
39	83
40	60
\.


--
-- Data for Name: bank; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bank (id, name) FROM stdin;
1	Otcom
2	Regrant
3	Redhold
4	Kanlam
5	Lotlux
6	Mat Lam Tam
7	Bamity
8	Tempsoft
9	Vagram
10	Bitchip
11	BelWeb
19	Alfa
21	Alfa
24	Otcom
25	Alfa2
\.


--
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transaction (id, amount, account_id) FROM stdin;
1	34	25
2	42	29
3	41	26
4	45	37
5	38	1
6	39	32
7	50	35
8	34	18
9	46	30
10	42	32
11	33	15
12	39	5
13	48	23
14	50	9
15	38	21
16	32	25
17	33	7
18	43	1
19	42	6
20	34	21
21	49	3
22	43	17
23	42	26
24	49	33
25	47	28
26	41	3
27	34	17
28	45	32
29	32	10
30	36	4
31	41	37
32	44	11
33	45	32
34	37	6
35	44	4
36	38	27
37	50	14
38	38	3
39	32	36
40	32	32
41	49	40
42	42	27
43	45	31
44	43	15
45	47	30
46	41	39
47	33	3
48	49	12
49	48	3
50	50	2
51	39	29
52	38	33
53	45	14
54	47	33
55	48	19
56	40	9
57	31	31
58	37	6
59	37	10
60	50	25
61	32	1
62	37	40
63	37	26
64	46	17
65	41	15
66	32	15
67	35	3
68	31	19
69	43	30
70	32	11
71	39	1
72	33	36
73	49	20
74	34	18
75	39	17
76	43	24
77	47	33
78	45	11
79	47	20
80	50	35
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (id, name, account_id, bank_id) FROM stdin;
1	Violette	1	3
2	Jessica	2	3
3	Vincenz	3	6
4	Amalita	4	5
5	Carmelia	5	2
6	Jen	6	8
7	Natka	7	2
8	Karlotte	8	2
9	Becca	9	5
10	Mindy	10	7
11	Merrick	11	5
12	Garret	12	2
13	Ford	13	10
14	Garrick	14	8
15	Garvey	15	2
16	Madelaine	16	10
17	Rafael	17	4
18	Ted	18	10
19	Euell	19	10
20	Rodolfo	20	7
21	Ramsay	21	2
22	Dollie	22	10
23	Alla	23	9
24	Georgena	24	5
25	Myrwyn	25	2
26	Papagena	26	2
27	Loy	27	5
28	Fritz	28	5
29	Nananne	29	9
30	Harcourt	30	2
31	Rhoda	31	7
32	Giffard	32	3
33	Beau	33	1
34	Esmeralda	34	2
35	Paulita	35	3
36	Marni	36	5
37	Myer	37	7
38	Haven	38	1
39	Jeane	39	8
40	Arvin	40	3
\.


--
-- Name: account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.account_id_seq', 1, false);


--
-- Name: bank_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bank_id_seq', 27, true);


--
-- Name: transaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transaction_id_seq', 1, false);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_seq', 1, false);


--
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- Name: bank bank_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bank
    ADD CONSTRAINT bank_pkey PRIMARY KEY (id);


--
-- Name: transaction transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: transaction account_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT account_id FOREIGN KEY (account_id) REFERENCES public.account(id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;


--
-- Name: user account_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT account_id FOREIGN KEY (account_id) REFERENCES public.account(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- Name: user bank_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT bank_id FOREIGN KEY (bank_id) REFERENCES public.bank(id) ON UPDATE RESTRICT ON DELETE RESTRICT NOT VALID;


--
-- PostgreSQL database dump complete
--

