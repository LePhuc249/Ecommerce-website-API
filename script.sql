--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3
-- Dumped by pg_dump version 13.3

-- Started on 2021-07-21 15:51:19

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
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 3213 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 200 (class 1259 OID 25994)
-- Name: table_name_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.table_name_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.table_name_id_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 26012)
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account (
    id integer DEFAULT nextval('public.table_name_id_seq'::regclass) NOT NULL,
    username character varying(30) NOT NULL,
    password character varying(100) NOT NULL,
    fullname character varying(50) NOT NULL,
    email character varying(50) NOT NULL,
    phone character varying(20) NOT NULL,
    create_date timestamp without time zone DEFAULT LOCALTIMESTAMP,
    update_date timestamp without time zone,
    status integer
);


ALTER TABLE public.account OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 26045)
-- Name: account_address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account_address (
    id integer DEFAULT nextval('public.table_name_id_seq'::regclass) NOT NULL,
    account_id integer NOT NULL,
    address character varying(100) NOT NULL
);


ALTER TABLE public.account_address OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 26265)
-- Name: account_order; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account_order (
    id integer DEFAULT nextval('public.table_name_id_seq'::regclass) NOT NULL,
    customer_id integer NOT NULL,
    create_date timestamp without time zone DEFAULT LOCALTIMESTAMP,
    update_date timestamp without time zone,
    date_delivery character varying(20) NOT NULL,
    payment_method integer NOT NULL,
    total_price double precision NOT NULL,
    status integer NOT NULL,
    coupon_id integer
);


ALTER TABLE public.account_order OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 26030)
-- Name: account_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account_role (
    account_id integer NOT NULL,
    role_id integer NOT NULL
);


ALTER TABLE public.account_role OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 26004)
-- Name: account_status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account_status (
    id integer DEFAULT nextval('public.table_name_id_seq'::regclass) NOT NULL,
    status character varying(20) NOT NULL
);


ALTER TABLE public.account_status OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 26464)
-- Name: brand; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.brand (
    id bigint NOT NULL,
    name character varying(255),
    organization bigint
);


ALTER TABLE public.brand OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 26707)
-- Name: cart; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cart (
    id bigint NOT NULL,
    create_date timestamp without time zone,
    update_date timestamp without time zone,
    customer_id bigint
);


ALTER TABLE public.cart OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 26712)
-- Name: cart_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cart_item (
    id bigint NOT NULL,
    amount integer,
    price real,
    cart_id bigint,
    product_id bigint NOT NULL,
    CONSTRAINT cart_item_amount_check CHECK ((amount >= 0))
);


ALTER TABLE public.cart_item OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 26137)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id integer DEFAULT nextval('public.table_name_id_seq'::regclass) NOT NULL,
    name character varying(20) NOT NULL,
    brand integer,
    parent_category integer,
    create_date timestamp without time zone DEFAULT LOCALTIMESTAMP,
    update_date timestamp without time zone,
    create_by integer,
    isdeleted boolean DEFAULT false
);


ALTER TABLE public.category OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 26245)
-- Name: coupons; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.coupons (
    id integer DEFAULT nextval('public.table_name_id_seq'::regclass) NOT NULL,
    code character varying(10) NOT NULL,
    discount_amount integer NOT NULL,
    product_discount integer NOT NULL,
    description character varying(50) NOT NULL,
    expiration_date character varying(15) NOT NULL,
    create_by integer,
    create_date timestamp without time zone DEFAULT LOCALTIMESTAMP,
    update_date timestamp without time zone,
    isdeleted boolean DEFAULT false
);


ALTER TABLE public.coupons OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 26318)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 26070)
-- Name: image; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.image (
    id integer DEFAULT nextval('public.table_name_id_seq'::regclass) NOT NULL,
    url character varying(200) NOT NULL,
    description character varying(200) NOT NULL,
    create_date timestamp without time zone DEFAULT LOCALTIMESTAMP,
    update_date timestamp without time zone,
    create_by integer,
    isdeleted boolean DEFAULT false
);


ALTER TABLE public.image OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 26292)
-- Name: order_detail; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.order_detail (
    id integer DEFAULT nextval('public.table_name_id_seq'::regclass) NOT NULL,
    order_id integer NOT NULL,
    item_id integer NOT NULL,
    amount integer NOT NULL,
    price double precision NOT NULL,
    item_property character varying(100) NOT NULL,
    create_date timestamp without time zone DEFAULT LOCALTIMESTAMP
);


ALTER TABLE public.order_detail OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 26215)
-- Name: order_status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.order_status (
    id integer DEFAULT nextval('public.table_name_id_seq'::regclass) NOT NULL,
    name character varying(50) NOT NULL,
    create_by integer NOT NULL,
    create_date timestamp without time zone DEFAULT LOCALTIMESTAMP,
    isdeleted boolean DEFAULT false
);


ALTER TABLE public.order_status OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 26469)
-- Name: organization; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organization (
    id bigint NOT NULL,
    create_by integer,
    create_date timestamp without time zone,
    isdeleted boolean,
    name character varying(255),
    update_date timestamp without time zone,
    image bigint
);


ALTER TABLE public.organization OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 26113)
-- Name: organization_address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organization_address (
    id integer DEFAULT nextval('public.table_name_id_seq'::regclass) NOT NULL,
    organization_id integer NOT NULL,
    address character varying(100) NOT NULL
);


ALTER TABLE public.organization_address OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 26230)
-- Name: payment_method; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payment_method (
    id integer DEFAULT nextval('public.table_name_id_seq'::regclass) NOT NULL,
    name character varying(50) NOT NULL,
    create_by integer NOT NULL,
    create_date timestamp without time zone DEFAULT LOCALTIMESTAMP,
    isdeleted boolean DEFAULT false
);


ALTER TABLE public.payment_method OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 26162)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer DEFAULT nextval('public.table_name_id_seq'::regclass) NOT NULL,
    name character varying(50) NOT NULL,
    short_description character varying(20) NOT NULL,
    description character varying(100) NOT NULL,
    price double precision NOT NULL,
    create_date timestamp without time zone DEFAULT LOCALTIMESTAMP,
    update_date timestamp without time zone,
    brand integer NOT NULL,
    quantity integer NOT NULL,
    category integer,
    counter integer DEFAULT 0,
    isdeleted boolean DEFAULT false
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 26184)
-- Name: product_image; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_image (
    product_id integer NOT NULL,
    image_id integer NOT NULL
);


ALTER TABLE public.product_image OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 25996)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id integer DEFAULT nextval('public.table_name_id_seq'::regclass) NOT NULL,
    name character varying(10) NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 3190 (class 0 OID 26012)
-- Dependencies: 203
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.account VALUES (3, 'manager1', 'manager1', 'Manager1', 'manager1@gmail.com', '0000000001', '2021-06-29 10:31:24.148543', NULL, 2);
INSERT INTO public.account VALUES (4, 'manager2', 'manager2', 'Manager2', 'manager2@gmail.com', '0000000002', '2021-06-29 10:31:24.148543', NULL, 2);
INSERT INTO public.account VALUES (5, 'manager3', 'manager3', 'Manager3', 'manager3@gmail.com', '0000000003', '2021-06-29 10:31:24.148543', NULL, 2);
INSERT INTO public.account VALUES (6, 'phucldh', '123456', 'Le Dac Hoang Phuc', 'phucldh@gmail.com', '0000000004', '2021-06-29 10:31:24.148543', NULL, 2);
INSERT INTO public.account VALUES (7, 'bachnv', '123456', 'Nguyen Viet Bach', 'bachnv@gmail.com', '0000000006', '2021-06-29 10:31:24.148543', NULL, 2);
INSERT INTO public.account VALUES (8, 'huylng', '123456', 'Le Nguyen Gia Huy', 'huylng@gmail.com', '0000000007', '2021-06-29 10:31:24.148543', NULL, 2);
INSERT INTO public.account VALUES (9, 'huyvl', '123456', 'Vu Lam Huy', 'huyvl@gmail.com', '0000000008', '2021-06-29 10:31:24.148543', NULL, 2);
INSERT INTO public.account VALUES (10, 'trucntt', '123456', 'Nguyen Thi Thanh Truc', 'trucntt@gmail.com', '0000000009', '2021-06-29 10:31:24.148543', NULL, 2);
INSERT INTO public.account VALUES (11, 'linhvtt', '123456', 'Vu Thi Thuy Linh', 'linhvtt@gmail.com', '0000000010', '2021-06-29 10:31:24.148543', NULL, 2);
INSERT INTO public.account VALUES (12, 'xoanntt', '123456', 'Nguyen Thi Thanh Xoan', 'xoanntt@gmail.com', '0000000011', '2021-06-29 10:31:24.148543', NULL, 2);
INSERT INTO public.account VALUES (13, 'tramlnq', '123456', 'Le Nguyen Quynh Tram', 'tramlnq@gmail.com', '0000000012', '2021-06-29 10:31:24.148543', NULL, 2);
INSERT INTO public.account VALUES (14, 'longnh', '123456', 'Nguyen Hoang Long', 'longnh@gmail.com', '0000000014', '2021-06-29 10:31:24.148543', NULL, 2);
INSERT INTO public.account VALUES (15, 'dunglt', '123456', 'Luu Tuan Dung', 'dunglt@gmail.com', '0000000015', '2021-06-29 10:31:24.148543', NULL, 2);
INSERT INTO public.account VALUES (16, 'taind', '123456', 'Nguyen Duc Tai', 'taind@gmail.com', '0000000016', '2021-06-29 10:31:24.148543', NULL, 2);
INSERT INTO public.account VALUES (17, 'chitm', '123456', 'Truong My Chi', 'chitm@gmail.com', '0000000017', '2021-06-29 10:31:24.148543', NULL, 2);
INSERT INTO public.account VALUES (18, 'hoangpd', '123456', 'Pham Duc Hoang', 'hoangpd@gmail.com', '0000000018', '2021-06-29 10:31:24.148543', NULL, 2);
INSERT INTO public.account VALUES (19, 'baohc', '123456', 'Huynh Chau Bao', 'baohc@gmail.com', '0000000019', '2021-06-29 10:31:24.148543', NULL, 2);
INSERT INTO public.account VALUES (20, 'antd', '123456', 'Tran Dinh An', 'antd@gmail.com', '0000000013', '2021-06-29 10:31:24.148543', NULL, 2);
INSERT INTO public.account VALUES (1, 'admin', 'admin', 'Admin', 'admin@gmail.com', '0772008835', '2021-06-29 10:31:24.148543', NULL, 2);
INSERT INTO public.account VALUES (2, 'manager', 'manager', 'Manager', 'manager@gmail.com', '0000000000', '2021-06-29 10:31:24.148543', NULL, 2);
INSERT INTO public.account VALUES (139, 'customerAccount', '$2a$10$qDNmsfLz3wZMYArH1t8P2.IGabRnXzUmM5/ZqNsnOZxgQo1YTjoYu', 'Customer Account', 'customerAccount@gmail.com', '9090909090', '2021-07-21 10:17:12.862', NULL, 1);
INSERT INTO public.account VALUES (142, 'customerAccount2', '$2a$10$/E3E5jYF.Sz9Ln8TBsipBOvn7g1Sd3HH5dWpTmzCIx3Dt6W9w6BFa', 'Customer Account2', 'customerAccount2@gmail.com', '90909090909', '2021-07-21 11:57:02.525', NULL, 1);


--
-- TOC entry 3192 (class 0 OID 26045)
-- Dependencies: 205
-- Data for Name: account_address; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.account_address VALUES (1, 1, 'Sai Gon');
INSERT INTO public.account_address VALUES (2, 2, 'Sai Gon');
INSERT INTO public.account_address VALUES (3, 3, 'Sai Gon');
INSERT INTO public.account_address VALUES (4, 4, 'Sai Gon');
INSERT INTO public.account_address VALUES (5, 5, 'Sai Gon');
INSERT INTO public.account_address VALUES (6, 6, 'Sai Gon Tan Phu');
INSERT INTO public.account_address VALUES (7, 7, 'Sai Gon Binh Thanh');
INSERT INTO public.account_address VALUES (8, 8, 'Binh Duong');
INSERT INTO public.account_address VALUES (9, 9, 'Binh Phuoc');
INSERT INTO public.account_address VALUES (10, 10, 'Sai Gon Quan 9');
INSERT INTO public.account_address VALUES (11, 11, 'Binh Phuoc');
INSERT INTO public.account_address VALUES (12, 12, 'Gia Lai');
INSERT INTO public.account_address VALUES (13, 13, 'Binh Thuan');
INSERT INTO public.account_address VALUES (14, 14, 'Sai Gon Tan Binh');
INSERT INTO public.account_address VALUES (15, 15, 'Sai Gon Tan Phu');
INSERT INTO public.account_address VALUES (16, 16, 'Sai Gon Tan Binh');
INSERT INTO public.account_address VALUES (17, 17, 'Sai Gon Quan 9');
INSERT INTO public.account_address VALUES (19, 19, 'Sai Gon Quan 9');
INSERT INTO public.account_address VALUES (20, 20, 'Sai Gon Quan 10');
INSERT INTO public.account_address VALUES (18, 18, 'Sai Gon Quan 9');


--
-- TOC entry 3201 (class 0 OID 26265)
-- Dependencies: 214
-- Data for Name: account_order; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3191 (class 0 OID 26030)
-- Dependencies: 204
-- Data for Name: account_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.account_role VALUES (2, 3);
INSERT INTO public.account_role VALUES (3, 3);
INSERT INTO public.account_role VALUES (4, 3);
INSERT INTO public.account_role VALUES (5, 3);
INSERT INTO public.account_role VALUES (6, 1);
INSERT INTO public.account_role VALUES (7, 1);
INSERT INTO public.account_role VALUES (8, 1);
INSERT INTO public.account_role VALUES (9, 1);
INSERT INTO public.account_role VALUES (10, 1);
INSERT INTO public.account_role VALUES (11, 1);
INSERT INTO public.account_role VALUES (12, 1);
INSERT INTO public.account_role VALUES (13, 1);
INSERT INTO public.account_role VALUES (14, 1);
INSERT INTO public.account_role VALUES (15, 1);
INSERT INTO public.account_role VALUES (16, 1);
INSERT INTO public.account_role VALUES (17, 1);
INSERT INTO public.account_role VALUES (18, 1);
INSERT INTO public.account_role VALUES (19, 1);
INSERT INTO public.account_role VALUES (20, 1);
INSERT INTO public.account_role VALUES (1, 2);
INSERT INTO public.account_role VALUES (139, 1);
INSERT INTO public.account_role VALUES (142, 1);


--
-- TOC entry 3189 (class 0 OID 26004)
-- Dependencies: 202
-- Data for Name: account_status; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.account_status VALUES (1, 'Not activated');
INSERT INTO public.account_status VALUES (2, 'Activated');
INSERT INTO public.account_status VALUES (3, 'Locked');


--
-- TOC entry 3204 (class 0 OID 26464)
-- Dependencies: 217
-- Data for Name: brand; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.brand VALUES (1, 'Actiso', 1);
INSERT INTO public.brand VALUES (2, 'Anbelebe', 2);
INSERT INTO public.brand VALUES (3, 'Bean Boozled', 3);
INSERT INTO public.brand VALUES (4, 'Beck', 4);
INSERT INTO public.brand VALUES (5, 'Bluecap', 5);
INSERT INTO public.brand VALUES (6, 'Budweiser', 6);
INSERT INTO public.brand VALUES (7, 'CafeViet', 7);
INSERT INTO public.brand VALUES (8, 'Chuppa', 8);
INSERT INTO public.brand VALUES (9, 'Corona', 9);
INSERT INTO public.brand VALUES (10, 'Cozy', 10);
INSERT INTO public.brand VALUES (11, 'Dilmah', 11);
INSERT INTO public.brand VALUES (12, 'Dynamine', 12);
INSERT INTO public.brand VALUES (13, 'Eclipse', 13);
INSERT INTO public.brand VALUES (14, 'Fres', 14);
INSERT INTO public.brand VALUES (15, 'G7', 15);
INSERT INTO public.brand VALUES (16, 'Giaocolam', 16);
INSERT INTO public.brand VALUES (17, 'Greentea', 17);
INSERT INTO public.brand VALUES (18, 'Halida', 18);
INSERT INTO public.brand VALUES (19, 'Happycooky', 19);
INSERT INTO public.brand VALUES (20, 'Happysoya', 20);
INSERT INTO public.brand VALUES (21, 'Kcoffee', 21);
INSERT INTO public.brand VALUES (22, 'Kingoil', 22);
INSERT INTO public.brand VALUES (23, 'Lipton', 23);
INSERT INTO public.brand VALUES (24, 'Matcha', 24);
INSERT INTO public.brand VALUES (25, 'Milo', 25);
INSERT INTO public.brand VALUES (26, 'Mm', 26);
INSERT INTO public.brand VALUES (27, 'Neptune', 27);
INSERT INTO public.brand VALUES (28, 'Nest', 28);
INSERT INTO public.brand VALUES (29, 'Oliveextra', 29);
INSERT INTO public.brand VALUES (30, 'Olongtea', 30);
INSERT INTO public.brand VALUES (31, 'Phuclong', 31);
INSERT INTO public.brand VALUES (32, 'Play', 32);
INSERT INTO public.brand VALUES (33, 'RoyalBlendy', 33);
INSERT INTO public.brand VALUES (34, 'Samarai', 34);
INSERT INTO public.brand VALUES (35, 'Simply', 35);
INSERT INTO public.brand VALUES (36, 'Snack', 36);
INSERT INTO public.brand VALUES (37, 'Tiger', 37);
INSERT INTO public.brand VALUES (38, 'Warrior', 38);
INSERT INTO public.brand VALUES (39, 'Cafesaigon', 39);


--
-- TOC entry 3206 (class 0 OID 26707)
-- Dependencies: 219
-- Data for Name: cart; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cart VALUES (159, NULL, NULL, 1);


--
-- TOC entry 3207 (class 0 OID 26712)
-- Dependencies: 220
-- Data for Name: cart_item; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3195 (class 0 OID 26137)
-- Dependencies: 208
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.category VALUES (1, 'Beer', 4, NULL, '2021-06-29 10:31:33.809518', NULL, 2, false);
INSERT INTO public.category VALUES (2, 'Beer', 5, NULL, '2021-06-29 10:31:33.809518', NULL, 2, false);
INSERT INTO public.category VALUES (3, 'Beer', 6, NULL, '2021-06-29 10:31:33.809518', NULL, 2, false);
INSERT INTO public.category VALUES (4, 'Beer', 9, NULL, '2021-06-29 10:31:33.809518', NULL, 2, false);
INSERT INTO public.category VALUES (5, 'Beer', 18, NULL, '2021-06-29 10:31:33.809518', NULL, 2, false);
INSERT INTO public.category VALUES (6, 'Beer', 37, NULL, '2021-06-29 10:31:33.809518', NULL, 2, false);
INSERT INTO public.category VALUES (7, 'Candy', 25, NULL, '2021-06-29 10:31:33.809518', NULL, 3, false);
INSERT INTO public.category VALUES (8, 'Candy', 26, NULL, '2021-06-29 10:31:33.809518', NULL, 3, false);
INSERT INTO public.category VALUES (9, 'Candy', 32, NULL, '2021-06-29 10:31:33.809518', NULL, 3, false);
INSERT INTO public.category VALUES (10, 'Candy', 2, NULL, '2021-06-29 10:31:33.809518', NULL, 3, false);
INSERT INTO public.category VALUES (11, 'Candy', 3, NULL, '2021-06-29 10:31:33.809518', NULL, 3, false);
INSERT INTO public.category VALUES (12, 'Candy', 8, NULL, '2021-06-29 10:31:33.809518', NULL, 3, false);
INSERT INTO public.category VALUES (13, 'Candy', 12, NULL, '2021-06-29 10:31:33.809518', NULL, 3, false);
INSERT INTO public.category VALUES (14, 'Candy', 13, NULL, '2021-06-29 10:31:33.809518', NULL, 3, false);
INSERT INTO public.category VALUES (15, 'Candy', 14, NULL, '2021-06-29 10:31:33.809518', NULL, 3, false);
INSERT INTO public.category VALUES (16, 'Coffee', 7, NULL, '2021-06-29 10:31:33.809518', NULL, 4, false);
INSERT INTO public.category VALUES (17, 'Coffee', 15, NULL, '2021-06-29 10:31:33.809518', NULL, 4, false);
INSERT INTO public.category VALUES (18, 'Coffee', 21, NULL, '2021-06-29 10:31:33.809518', NULL, 4, false);
INSERT INTO public.category VALUES (19, 'Coffee', 28, NULL, '2021-06-29 10:31:33.809518', NULL, 4, false);
INSERT INTO public.category VALUES (20, 'Coffee', 39, NULL, '2021-06-29 10:31:33.809518', NULL, 4, false);
INSERT INTO public.category VALUES (21, 'Energy Drink', 38, NULL, '2021-06-29 10:31:33.809518', NULL, 5, false);
INSERT INTO public.category VALUES (22, 'Energy Drink', 34, NULL, '2021-06-29 10:31:33.809518', NULL, 5, false);
INSERT INTO public.category VALUES (23, 'Oil', 19, NULL, '2021-06-29 10:31:33.809518', NULL, 4, false);
INSERT INTO public.category VALUES (24, 'Oil', 20, NULL, '2021-06-29 10:31:33.809518', NULL, 4, false);
INSERT INTO public.category VALUES (25, 'Oil', 22, NULL, '2021-06-29 10:31:33.809518', NULL, 4, false);
INSERT INTO public.category VALUES (26, 'Oil', 29, NULL, '2021-06-29 10:31:33.809518', NULL, 4, false);
INSERT INTO public.category VALUES (27, 'Oil', 27, NULL, '2021-06-29 10:31:33.809518', NULL, 4, false);
INSERT INTO public.category VALUES (28, 'Oil', 35, NULL, '2021-06-29 10:31:33.809518', NULL, 4, false);
INSERT INTO public.category VALUES (29, 'Snack', 36, NULL, '2021-06-29 10:31:33.809518', NULL, 5, false);
INSERT INTO public.category VALUES (30, 'Tea', 10, NULL, '2021-06-29 10:31:33.809518', NULL, 2, false);
INSERT INTO public.category VALUES (31, 'Tea', 17, NULL, '2021-06-29 10:31:33.809518', NULL, 2, false);
INSERT INTO public.category VALUES (32, 'Tea', 16, NULL, '2021-06-29 10:31:33.809518', NULL, 2, false);
INSERT INTO public.category VALUES (33, 'Tea', 1, NULL, '2021-06-29 10:31:33.809518', NULL, 2, false);
INSERT INTO public.category VALUES (34, 'Tea', 11, NULL, '2021-06-29 10:31:33.809518', NULL, 2, false);
INSERT INTO public.category VALUES (35, 'Tea', 33, NULL, '2021-06-29 10:31:33.809518', NULL, 2, false);
INSERT INTO public.category VALUES (36, 'Tea', 31, NULL, '2021-06-29 10:31:33.809518', NULL, 2, false);
INSERT INTO public.category VALUES (37, 'Tea', 30, NULL, '2021-06-29 10:31:33.809518', NULL, 2, false);
INSERT INTO public.category VALUES (38, 'Tea', 24, NULL, '2021-06-29 10:31:33.809518', NULL, 2, false);
INSERT INTO public.category VALUES (39, 'Tea', 23, NULL, '2021-06-29 10:31:33.809518', NULL, 2, false);


--
-- TOC entry 3200 (class 0 OID 26245)
-- Dependencies: 213
-- Data for Name: coupons; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.coupons VALUES (1, 'ABC10', 10, 1, 'Milo Cube sale 10%', '2022-05-01', 2, '2021-07-08 21:05:40.668352', NULL, false);
INSERT INTO public.coupons VALUES (3, 'ABC15', 15, 19, 'Happy Soya Oil sale 15%', '2022-05-01', 4, '2021-07-08 21:05:40.668352', NULL, false);
INSERT INTO public.coupons VALUES (2, 'ABC5', 5, 16, 'G7 Coffee sale 5%', '2022-05-01', 3, '2021-07-08 21:05:40.668352', NULL, false);
INSERT INTO public.coupons VALUES (4, 'ABC20', 20, 35, 'Phuc Long Green Tea sale 20%', '2022-05-01', 5, '2021-07-08 21:05:40.668352', NULL, false);
INSERT INTO public.coupons VALUES (5, 'ABC25', 25, 35, 'Phuc Long Green Tea sale 25%', '2022-05-01', 2, '2021-07-08 21:05:40.668352', NULL, false);
INSERT INTO public.coupons VALUES (6, 'DEF10', 10, 37, 'Royal Blendy Milk Tea sale 10%', '2022-05-01', 3, '2021-07-08 21:05:40.668352', NULL, false);
INSERT INTO public.coupons VALUES (7, 'DEF5', 5, 1, 'Milo Cube sale 5%', '2022-05-01', 4, '2021-07-08 21:05:40.668352', NULL, false);
INSERT INTO public.coupons VALUES (8, 'DEF15', 15, 37, 'Royal Blendy Milk Tea sale 15%', '2022-05-01', 5, '2021-07-08 21:05:40.668352', NULL, false);
INSERT INTO public.coupons VALUES (9, 'DEF20', 20, 19, 'Happy Soya Oil sale 20%', '2022-05-01', 2, '2021-07-08 21:05:40.668352', NULL, false);


--
-- TOC entry 3193 (class 0 OID 26070)
-- Dependencies: 206
-- Data for Name: image; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.image VALUES (1, 'img/MiloCube.jpg', 'Milo Cube', '2021-07-14 21:09:55.831233', NULL, 1, false);
INSERT INTO public.image VALUES (2, 'img/CozyGold.jpg', 'Cozy Gold', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (3, 'img/CozyHuongChanh.jpg', 'Cozy Huong Chanh', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (4, 'img/CozyHuongDau.jpg', 'Cozy Huong Dau', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (5, 'img/CozyHuongNhai.jpg', 'Cozy Huong Nhai', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (6, 'img/CozyHuongSen.jpg', 'Cozy Huong Sen', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (7, 'img/CozyHuongTao.jpg', 'Cozy Huong Tao', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (8, 'img/CozyMatcha.jpg', 'Cozy Matcha', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (9, 'img/CozyTraXanh.jpg', 'Cozy Tra Xanh', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (10, 'img/DillmahGold.jpg', 'Dillmah Gold', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (11, 'img/DILMAHDEN.jpg', 'DILMAH DEN', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (12, 'img/ActisoGalaxy.jpg', 'Actiso Galaxy', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (13, 'img/dynamite.jpg', 'Dynamite', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (14, 'img/esclipse.jpg', 'Esclipse', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (15, 'img/FresCandy.jpg', 'Fres Candy', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (16, 'img/G7Cafe.jpg', 'G7 Cafe', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (17, 'img/GiaoCoLam.jpg', 'Giao Co Lam', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (18, 'img/GreenTea.jpg', 'Green Tea', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (19, 'img/happisoya.jpg', 'Happy Soya', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (20, 'img/happykoki.jpg', 'Happykoki', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (21, 'img/HongTraCozy.jpg', 'Hong Tra Cozy', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (22, 'img/KCoffe.jpg', 'K Coffe', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (23, 'img/Atiso.jpg', 'Atiso', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (24, 'img/KeoChups.jpg', 'Keo Chups', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (25, 'img/kingOil.jpg', 'King Oil', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (26, 'img/LiptonHop.jpg', 'Lipton Hop', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (27, 'img/LiptonXoai.jpg', 'Lipton Xoai', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (28, 'img/M&M.jpg', 'M&M', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (29, 'img/MatchaLatte.jpg', 'Matcha Latte', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (30, 'img/neptune.jpg', 'Neptune', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (31, 'img/NescafeGreen.jpg', 'Nes cafe Green', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (32, 'img/NesteaChanh.jpg', 'Nestea Chanh', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (33, 'img/oliveExtra.jpg', 'Olive Extra', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (34, 'img/BeanBoolzed.jpg', 'Bean Boolzed', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (35, 'img/PhucLongTea.jpg', 'Phuc Long Tea', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (36, 'img/Play.jpg', 'Play', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (37, 'img/RoyalBlendy.jpg', 'Royal Blendy', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (38, 'img/samurai.jpg', 'Samurai', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (39, 'img/simply.jpg', 'Simply', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (40, 'img/SnackBBQ.jpg', 'Snack BBQ', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (41, 'img/SnackCorn.jpg', 'Snack Corn', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (42, 'img/tiger.jpg', 'Tiger', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (43, 'img/bluecap.jpg', 'Bluecap', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (44, 'img/corona.jpg', 'Corona', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (45, 'img/Becks Ice.jpg', 'Becks Ice', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (46, 'img/halida.jpg', 'Halida', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (47, 'img/olongtea.jpg', 'Olong tea', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (48, 'img/warrior.jpg', 'Warrior', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (49, 'img/unnamed.jpg', 'unnamed', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (50, 'img/Bud.jpg', 'Bud', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (51, 'img/CafeSaiGon.jpg', 'Cafe Sai Gon', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (52, 'img/CafeViet.jpg', 'Cafe Viet', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (53, 'img/CozyDao.jpg', 'Cozy Dao', '2021-07-14 21:10:09.295088', NULL, 1, false);
INSERT INTO public.image VALUES (54, 'img/actisologo.png', 'Actiso logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (55, 'img/Anbelebelogo.jpg', 'Anbelebe logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (56, 'img/BeanBoozledlogo.png', 'Bean Boozled logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (57, 'img/becklogo.png', 'Beck logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (58, 'img/bluecaplogo.jpg', 'Bluecap logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (59, 'img/Budweiserlogo.png', 'Budweiser logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (60, 'img/CafeVietlogo.png', 'CafeViet logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (61, 'img/chuppalogo.png', 'Chuppa logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (62, 'img/coronalogo.jpg', 'Corona logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (63, 'img/cozylogo.jpg', 'Cozy logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (64, 'img/Dilmahlogo.jpg', 'Dilmah logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (65, 'img/dynaminelogo.png', 'Dynamine logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (66, 'img/Eclipse Logo.png', 'Eclipse Logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (67, 'img/freslogo.jpg', 'Fres logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (68, 'img/g7logo.png', 'G7 logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (69, 'img/giaocolamlogo.png', 'Giaocolam logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (70, 'img/greentealogo.jpg', 'Greentea logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (71, 'img/halidalogo.jpg', 'Halida logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (72, 'img/happycookylogo.jpg', 'Happycooky logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (73, 'img/happysoyalogo.jpg', 'Happysoya logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (74, 'img/kcoffeelogo.png', 'Kcoffee logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (75, 'img/kingoillogo.png', 'Kingoil logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (76, 'img/liptonlogo.jpg', 'Lipton logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (77, 'img/matchalogo.jpg', 'Matcha logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (78, 'img/milologo.png', 'Milo logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (79, 'img/mmlogo.png', 'Mm logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (80, 'img/neptunelogo.jpg', 'Neptune logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (81, 'img/nestlogo.png', 'Nest logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (82, 'img/oliveextralogo.png', 'Oliveextra logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (83, 'img/olongtealogo.png', 'Olongtea logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (84, 'img/phuclonglogo.jpg', 'Phuclong logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (85, 'img/playlogo.png', 'Play logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (86, 'img/RoyalBlendylogo.jpg', 'RoyalBlendy logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (87, 'img/Samarailogo.jpg', 'Samarai logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (88, 'img/Simplylogo.jpg', 'Simply logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (89, 'img/snacklogo.png', 'Snack logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (90, 'img/tigerlogo.png', 'Tiger logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (91, 'img/warriorlogo.png', 'Warrior logo', '2021-07-14 21:55:44.860864', NULL, 1, false);
INSERT INTO public.image VALUES (92, 'img/cafesaigonlogo.jpg', 'Cafesaigon logo', '2021-07-14 21:55:44.860864', NULL, 1, false);


--
-- TOC entry 3202 (class 0 OID 26292)
-- Dependencies: 215
-- Data for Name: order_detail; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3198 (class 0 OID 26215)
-- Dependencies: 211
-- Data for Name: order_status; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.order_status VALUES (1, 'Cancel', 1, '2021-06-29 10:36:03.462238', false);
INSERT INTO public.order_status VALUES (2, 'Confirm', 1, '2021-06-29 10:36:03.462238', false);
INSERT INTO public.order_status VALUES (3, 'Finish', 1, '2021-06-29 10:36:03.462238', false);
INSERT INTO public.order_status VALUES (4, 'Processing', 1, '2021-06-29 10:36:03.462238', false);


--
-- TOC entry 3205 (class 0 OID 26469)
-- Dependencies: 218
-- Data for Name: organization; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.organization VALUES (1, 1, '2021-07-14 22:14:55.218536', false, 'Actiso', NULL, 54);
INSERT INTO public.organization VALUES (2, 1, '2021-07-14 22:14:55.218536', false, 'Anbelebe', NULL, 55);
INSERT INTO public.organization VALUES (3, 1, '2021-07-14 22:14:55.218536', false, 'Bean Boozled', NULL, 56);
INSERT INTO public.organization VALUES (4, 1, '2021-07-14 22:14:55.218536', false, 'Beck', NULL, 57);
INSERT INTO public.organization VALUES (5, 1, '2021-07-14 22:14:55.218536', false, 'Bluecap', NULL, 58);
INSERT INTO public.organization VALUES (6, 1, '2021-07-14 22:14:55.218536', false, 'Budweiser', NULL, 59);
INSERT INTO public.organization VALUES (7, 1, '2021-07-14 22:14:55.218536', false, 'CafeViet', NULL, 60);
INSERT INTO public.organization VALUES (8, 1, '2021-07-14 22:14:55.218536', false, 'Chuppa', NULL, 61);
INSERT INTO public.organization VALUES (9, 1, '2021-07-14 22:14:55.218536', false, 'Corona', NULL, 62);
INSERT INTO public.organization VALUES (10, 1, '2021-07-14 22:14:55.218536', false, 'Cozy', NULL, 63);
INSERT INTO public.organization VALUES (11, 1, '2021-07-14 22:14:55.218536', false, 'Dilmah', NULL, 64);
INSERT INTO public.organization VALUES (12, 1, '2021-07-14 22:14:55.218536', false, 'Dynamine', NULL, 65);
INSERT INTO public.organization VALUES (13, 1, '2021-07-14 22:14:55.218536', false, 'Eclipse', NULL, 66);
INSERT INTO public.organization VALUES (14, 1, '2021-07-14 22:14:55.218536', false, 'Fres', NULL, 67);
INSERT INTO public.organization VALUES (15, 1, '2021-07-14 22:14:55.218536', false, 'G7', NULL, 68);
INSERT INTO public.organization VALUES (16, 1, '2021-07-14 22:14:55.218536', false, 'Giaocolam', NULL, 69);
INSERT INTO public.organization VALUES (17, 1, '2021-07-14 22:14:55.218536', false, 'Greentea', NULL, 70);
INSERT INTO public.organization VALUES (18, 1, '2021-07-14 22:14:55.218536', false, 'Halida', NULL, 71);
INSERT INTO public.organization VALUES (19, 1, '2021-07-14 22:14:55.218536', false, 'Happycooky', NULL, 72);
INSERT INTO public.organization VALUES (20, 1, '2021-07-14 22:14:55.218536', false, 'Happysoya', NULL, 73);
INSERT INTO public.organization VALUES (21, 1, '2021-07-14 22:14:55.218536', false, 'Kcoffee', NULL, 74);
INSERT INTO public.organization VALUES (22, 1, '2021-07-14 22:14:55.218536', false, 'Kingoil', NULL, 75);
INSERT INTO public.organization VALUES (23, 1, '2021-07-14 22:14:55.218536', false, 'Lipton', NULL, 76);
INSERT INTO public.organization VALUES (24, 1, '2021-07-14 22:14:55.218536', false, 'Matcha', NULL, 77);
INSERT INTO public.organization VALUES (25, 1, '2021-07-14 22:14:55.218536', false, 'Milo', NULL, 78);
INSERT INTO public.organization VALUES (26, 1, '2021-07-14 22:14:55.218536', false, 'Mm', NULL, 79);
INSERT INTO public.organization VALUES (27, 1, '2021-07-14 22:14:55.218536', false, 'Neptune', NULL, 80);
INSERT INTO public.organization VALUES (28, 1, '2021-07-14 22:14:55.218536', false, 'Nest', NULL, 81);
INSERT INTO public.organization VALUES (29, 1, '2021-07-14 22:14:55.218536', false, 'Oliveextra', NULL, 82);
INSERT INTO public.organization VALUES (30, 1, '2021-07-14 22:14:55.218536', false, 'Olongtea', NULL, 83);
INSERT INTO public.organization VALUES (31, 1, '2021-07-14 22:14:55.218536', false, 'Phuclong', NULL, 84);
INSERT INTO public.organization VALUES (32, 1, '2021-07-14 22:14:55.218536', false, 'Play', NULL, 85);
INSERT INTO public.organization VALUES (33, 1, '2021-07-14 22:14:55.218536', false, 'RoyalBlendy', NULL, 86);
INSERT INTO public.organization VALUES (34, 1, '2021-07-14 22:14:55.218536', false, 'Samarai', NULL, 87);
INSERT INTO public.organization VALUES (35, 1, '2021-07-14 22:14:55.218536', false, 'Simply', NULL, 88);
INSERT INTO public.organization VALUES (36, 1, '2021-07-14 22:14:55.218536', false, 'Snack', NULL, 89);
INSERT INTO public.organization VALUES (37, 1, '2021-07-14 22:14:55.218536', false, 'Tiger', NULL, 90);
INSERT INTO public.organization VALUES (38, 1, '2021-07-14 22:14:55.218536', false, 'Warrior', NULL, 91);
INSERT INTO public.organization VALUES (39, 1, '2021-07-14 22:14:55.218536', false, 'Cafesaigon', NULL, 92);


--
-- TOC entry 3194 (class 0 OID 26113)
-- Dependencies: 207
-- Data for Name: organization_address; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.organization_address VALUES (1, 1, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (2, 2, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (3, 3, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (4, 4, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (5, 5, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (6, 6, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (7, 7, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (8, 8, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (9, 9, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (10, 10, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (11, 11, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (12, 12, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (13, 13, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (14, 14, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (15, 15, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (16, 16, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (17, 17, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (18, 18, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (19, 19, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (20, 20, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (21, 21, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (22, 22, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (23, 23, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (24, 24, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (25, 25, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (26, 26, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (27, 27, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (28, 28, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (29, 29, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (30, 30, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (31, 31, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (32, 32, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (33, 33, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (34, 34, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (35, 35, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (36, 36, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (37, 37, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (38, 38, 'Ho Chi Minh City');
INSERT INTO public.organization_address VALUES (39, 39, 'Ho Chi Minh City');


--
-- TOC entry 3199 (class 0 OID 26230)
-- Dependencies: 212
-- Data for Name: payment_method; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.payment_method VALUES (2, 'Internet Banking', 1, '2021-06-29 10:36:35.093481', false);
INSERT INTO public.payment_method VALUES (1, 'Pay On Delivery', 1, '2021-06-29 10:36:35', false);


--
-- TOC entry 3196 (class 0 OID 26162)
-- Dependencies: 209
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product VALUES (1, 'Milo Cube', 'Can use with milk', 'Can use with milk', 12, '2021-06-29 10:47:50.395456', NULL, 25, 200, 7, 1, false);
INSERT INTO public.product VALUES (2, 'Cozy Gold ', 'Gold tea from cozy ', 'Gold tea from cozy with leaf tea from Buon Me Thuoc', 3, '2021-06-29 10:47:50.395456', NULL, 10, 200, 30, 2, false);
INSERT INTO public.product VALUES (3, 'Cozy lemon', 'Lemon tea cozy ', 'Lemon tea from cozy with no thing different with normal lemon tea', 1.5, '2021-06-29 10:47:50.395456', NULL, 10, 200, 30, 3, false);
INSERT INTO public.product VALUES (4, 'Strawberry Cozy', 'Cozy strawberry ', 'Cozy with strawberry make from strawberry Da Lat', 2, '2021-06-29 10:47:50.395456', NULL, 10, 200, 30, 4, false);
INSERT INTO public.product VALUES (5, 'Cozy Flower', 'Have special ', 'Have special is no thing', 2, '2021-06-29 10:47:50.395456', NULL, 10, 200, 30, 5, false);
INSERT INTO public.product VALUES (6, 'Cozy Lotus', 'Cozy lotus', 'Cozy with lotus seed', 2.5, '2021-06-29 10:47:50.395456', NULL, 10, 200, 30, 6, false);
INSERT INTO public.product VALUES (7, 'Cozy Apple', 'With apple ', 'With apple every thing will get higher price', 5, '2021-06-29 10:47:50.395456', NULL, 10, 199, 30, 1, false);
INSERT INTO public.product VALUES (8, 'Cozy Matcha', 'Make Japan ', 'Make in Japan ', 3, '2021-06-29 10:47:50.395456', NULL, 10, 200, 30, 1, false);
INSERT INTO public.product VALUES (9, 'Green Tea Cozy', 'Green Tea', 'Green Tea', 1.5, '2021-06-29 10:47:50.395456', NULL, 10, 200, 30, 2, false);
INSERT INTO public.product VALUES (10, 'Dilmah Tea Gold Edition', 'Make milk tea', 'Can use with milk to make milk tea', 5, '2021-06-29 10:47:50.395456', NULL, 11, 200, 34, 3, false);
INSERT INTO public.product VALUES (11, 'Dilmah Normal Edition', 'No special', 'No thing special', 3, '2021-06-29 10:47:50.395456', NULL, 11, 199, 34, 1, false);
INSERT INTO public.product VALUES (12, 'Actiso Galaxy', 'Candy mint', 'Candy mint', 2, '2021-06-29 10:47:50.395456', NULL, 32, 200, 9, 4, false);
INSERT INTO public.product VALUES (13, 'Dynamine Candy', 'Mint chocolate', 'Mint candy with chocolate', 2, '2021-06-29 10:47:50.395456', NULL, 12, 249, 13, 1, false);
INSERT INTO public.product VALUES (14, 'Esclipe Candy', 'draw esclipe', 'Eat one and you can draw esclipe without ruler', 2, '2021-06-29 10:47:50.395456', NULL, 13, 190, 14, 5, false);
INSERT INTO public.product VALUES (15, 'Fres Candy', 'feel better', 'Read the line on the candy it can make you feel better', 2, '2021-06-29 10:47:50.395456', NULL, 14, 195, 15, 1, false);
INSERT INTO public.product VALUES (16, 'G7 Coffe', '7 hours fly', '7 hours fly', 2, '2021-06-29 10:47:50.395456', NULL, 15, 200, 17, 3, false);
INSERT INTO public.product VALUES (17, 'GiaoCoLamTea', 'with corona', 'Giao Co Lam Tea from Chinna with corona', 1, '2021-06-29 10:47:50.395456', NULL, 16, 200, 32, 4, false);
INSERT INTO public.product VALUES (18, 'Green Tea ', 'Tea Bao Loc', 'Green Tea from Bao Loc', 3.5, '2021-06-29 10:47:50.395456', NULL, 17, 190, 31, 2, false);
INSERT INTO public.product VALUES (19, 'Happy Soya Oil', 'Oil KFC', 'Oil can use to make chicken KFC', 5, '2021-06-29 10:47:50.395456', NULL, 20, 200, 24, 5, false);
INSERT INTO public.product VALUES (20, 'Happy Cooky', 'Oil Lotteria', 'Oil using in Lotteria', 4, '2021-06-29 10:47:50.395456', NULL, 19, 200, 7, 23, false);
INSERT INTO public.product VALUES (21, 'Pink Tea Cozy ', 'Pink tea', 'Pink tea can you to make you sleep better', 2.5, '2021-06-29 10:47:50.395456', NULL, 10, 200, 30, 3, false);
INSERT INTO public.product VALUES (22, 'K Coffee', 'Coffee animal', 'Coffee from shit of animal', 7.5, '2021-06-29 10:47:50.395456', NULL, 21, 184, 18, 1, false);
INSERT INTO public.product VALUES (23, 'Atiso Tea', 'leaf atiso ', 'Make from leaf atiso from Viet Nam', 2, '2021-06-29 10:47:50.395456', NULL, 1, 200, 33, 2, false);
INSERT INTO public.product VALUES (24, 'Chuppachup', 'many color', 'Candy with many color', 1, '2021-06-29 10:47:50.395456', NULL, 8, 185, 12, 5, false);
INSERT INTO public.product VALUES (25, 'King Oil', 'Tuong Phat Oil', 'Same with Tuong Phat Oil', 4.5, '2021-06-29 10:47:50.395456', NULL, 22, 189, 25, 1, false);
INSERT INTO public.product VALUES (26, 'Lipton Box Tet''s', 'normal box', 'No special from normal box', 3, '2021-06-29 10:47:50.395456', NULL, 23, 188, 9, 39, false);
INSERT INTO public.product VALUES (27, 'Lipton Mango', 'Mango tea', 'Mango tea', 1.5, '2021-06-29 10:47:50.395456', NULL, 23, 200, 9, 39, false);
INSERT INTO public.product VALUES (28, 'M&M Candy', 'Socola Candy', 'Socola Candy', 2.5, '2021-06-29 10:47:50.395456', NULL, 26, 200, 8, 5, false);
INSERT INTO public.product VALUES (29, 'Matcha Latte ', 'Latte Tea', 'Matcha Latte Tea', 5, '2021-06-29 10:47:50.395456', NULL, 24, 200, 38, 3, false);
INSERT INTO public.product VALUES (30, 'Neptune Oil', 'chicken and more', 'Use with chicken and more', 6, '2021-06-29 10:47:50.395456', NULL, 27, 200, 27, 2, false);
INSERT INTO public.product VALUES (31, 'Green Nest Cafe', 'Cafe Viet', 'Cafe Viet Nest Green', 2, '2021-06-29 10:47:50.395456', NULL, 28, 200, 19, 3, false);
INSERT INTO public.product VALUES (32, 'Nest Lemon Tea', 'Lemon Tea', 'Lemon Tea', 2, '2021-06-29 10:47:50.395456', NULL, 28, 200, 19, 4, false);
INSERT INTO public.product VALUES (33, 'Olive Extra Oil', 'Olive Oil', 'Olive Oil', 8, '2021-06-29 10:47:50.395456', NULL, 29, 200, 26, 5, false);
INSERT INTO public.product VALUES (34, 'Bean Boozled', 'make you scary', 'Been with many thing make you scary', 15, '2021-06-29 10:47:50.395456', NULL, 3, 200, 11, 2, false);
INSERT INTO public.product VALUES (35, 'Phuc Long Green Tea', 'Phuc Long Tea ', 'Phuc Long Tea ', 6, '2021-06-29 10:47:50.395456', NULL, 31, 200, 36, 4, false);
INSERT INTO public.product VALUES (36, 'Play Candy', 'Play fresh candy', 'fresh candy', 4, '2021-06-29 10:47:50.395456', NULL, 32, 199, 9, 1, false);
INSERT INTO public.product VALUES (37, 'Royal Blendy Milk Tea ', 'Milk tea', 'Milk tea', 7, '2021-06-29 10:47:50.395456', NULL, 33, 200, 35, 4, false);
INSERT INTO public.product VALUES (38, 'Samarai Engnergy Drink', 'Strawberry', 'Samurai Strawberry', 1, '2021-06-29 10:47:50.395456', NULL, 34, 200, 22, 3, false);
INSERT INTO public.product VALUES (39, 'Simply Oil', 'lipit animal', 'Oil from lipit from animal', 5, '2021-06-29 10:47:50.395456', NULL, 35, 200, 28, 7, false);
INSERT INTO public.product VALUES (40, 'Snack BBQ', 'Snack BBQ', 'Snack from potato cook BBQ', 2, '2021-06-29 10:47:50.395456', NULL, 36, 199, 29, 1, false);
INSERT INTO public.product VALUES (41, 'Snack Corn', 'Snack corn', 'Snack make from potato with corn', 1, '2021-06-29 10:47:50.395456', NULL, 36, 190, 29, 1, false);
INSERT INTO public.product VALUES (42, 'Tiger Beer', 'Tiger beer', 'Tiger beer', 2, '2021-06-29 10:47:50.395456', NULL, 37, 200, 6, 2, false);
INSERT INTO public.product VALUES (43, 'Blue Cap', 'Blue cap beer', 'Blue cap beer', 2.5, '2021-06-29 10:47:50.395456', NULL, 5, 200, 2, 5, false);
INSERT INTO public.product VALUES (45, 'Beck Ice', 'fly higher', 'Beer from Viet Nam make you fly higher than bird', 2, '2021-06-29 10:47:50.395456', NULL, 4, 190, 1, 4, false);
INSERT INTO public.product VALUES (46, 'Halida', 'Halida beer', 'Halida beer', 2, '2021-06-29 10:47:50.395456', NULL, 18, 198, 5, 2, false);
INSERT INTO public.product VALUES (47, 'Olong tea Plus', 'Olong tea', 'Olong tea', 1, '2021-06-29 10:47:50.395456', NULL, 30, 179, 37, 3, false);
INSERT INTO public.product VALUES (48, 'Warior', 'Warrior', 'Warrior', 11, '2021-06-29 10:47:50.395456', NULL, 38, 189, 21, 4, false);
INSERT INTO public.product VALUES (49, 'Anbelebe', 'Anbelebe', 'Anbelebe', 2, '2021-06-29 10:47:50.395456', NULL, 2, 200, 10, 1, false);
INSERT INTO public.product VALUES (50, 'Budweiser', 'Beer from Germany', 'Beer Germany', 1.5, '2021-06-29 10:47:50.395456', NULL, 6, 200, 3, 5, false);
INSERT INTO public.product VALUES (51, 'Cafe Sai Gon', 'Cafe Sai Gon ', 'Cafe for Sai Gon people', 2, '2021-06-29 10:47:50.395456', NULL, 39, 200, 20, 6, false);
INSERT INTO public.product VALUES (52, 'Cafe Viet', 'wake up all day', 'Cafe Viet wake you up all day', 1.5, '2021-06-29 10:47:50.395456', NULL, 7, 190, 16, 3, false);
INSERT INTO public.product VALUES (53, 'Cozy Peach', 'Cozy peach ', 'Cozy tea include peach ', 2, '2021-06-29 10:47:50.395456', NULL, 10, 200, 30, 1, false);
INSERT INTO public.product VALUES (44, 'Corona', 'Corona beer', 'Corona beer', 3.5, '2021-06-29 10:47:50.395456', NULL, 9, 200, 4, 3, false);


--
-- TOC entry 3197 (class 0 OID 26184)
-- Dependencies: 210
-- Data for Name: product_image; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product_image VALUES (1, 1);
INSERT INTO public.product_image VALUES (2, 2);
INSERT INTO public.product_image VALUES (3, 3);
INSERT INTO public.product_image VALUES (4, 4);
INSERT INTO public.product_image VALUES (5, 5);
INSERT INTO public.product_image VALUES (6, 6);
INSERT INTO public.product_image VALUES (7, 7);
INSERT INTO public.product_image VALUES (8, 8);
INSERT INTO public.product_image VALUES (9, 9);
INSERT INTO public.product_image VALUES (10, 10);
INSERT INTO public.product_image VALUES (11, 11);
INSERT INTO public.product_image VALUES (12, 12);
INSERT INTO public.product_image VALUES (13, 13);
INSERT INTO public.product_image VALUES (14, 14);
INSERT INTO public.product_image VALUES (15, 15);
INSERT INTO public.product_image VALUES (16, 16);
INSERT INTO public.product_image VALUES (17, 17);
INSERT INTO public.product_image VALUES (18, 18);
INSERT INTO public.product_image VALUES (19, 19);
INSERT INTO public.product_image VALUES (20, 20);
INSERT INTO public.product_image VALUES (21, 21);
INSERT INTO public.product_image VALUES (22, 22);
INSERT INTO public.product_image VALUES (23, 23);
INSERT INTO public.product_image VALUES (24, 24);
INSERT INTO public.product_image VALUES (25, 25);
INSERT INTO public.product_image VALUES (26, 26);
INSERT INTO public.product_image VALUES (27, 27);
INSERT INTO public.product_image VALUES (28, 28);
INSERT INTO public.product_image VALUES (29, 29);
INSERT INTO public.product_image VALUES (30, 30);
INSERT INTO public.product_image VALUES (31, 31);
INSERT INTO public.product_image VALUES (32, 32);
INSERT INTO public.product_image VALUES (33, 33);
INSERT INTO public.product_image VALUES (34, 34);
INSERT INTO public.product_image VALUES (35, 35);
INSERT INTO public.product_image VALUES (36, 36);
INSERT INTO public.product_image VALUES (37, 37);
INSERT INTO public.product_image VALUES (38, 38);
INSERT INTO public.product_image VALUES (39, 39);
INSERT INTO public.product_image VALUES (40, 40);
INSERT INTO public.product_image VALUES (41, 41);
INSERT INTO public.product_image VALUES (42, 42);
INSERT INTO public.product_image VALUES (43, 43);
INSERT INTO public.product_image VALUES (44, 44);
INSERT INTO public.product_image VALUES (45, 45);
INSERT INTO public.product_image VALUES (46, 46);
INSERT INTO public.product_image VALUES (47, 47);
INSERT INTO public.product_image VALUES (48, 48);
INSERT INTO public.product_image VALUES (49, 49);
INSERT INTO public.product_image VALUES (50, 50);
INSERT INTO public.product_image VALUES (51, 51);
INSERT INTO public.product_image VALUES (52, 52);
INSERT INTO public.product_image VALUES (53, 53);


--
-- TOC entry 3188 (class 0 OID 25996)
-- Dependencies: 201
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.role VALUES (1, 'Customer');
INSERT INTO public.role VALUES (2, 'Admin');
INSERT INTO public.role VALUES (3, 'Manager');


--
-- TOC entry 3214 (class 0 OID 0)
-- Dependencies: 216
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 160, true);


--
-- TOC entry 3215 (class 0 OID 0)
-- Dependencies: 200
-- Name: table_name_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.table_name_id_seq', 39, true);


--
-- TOC entry 2965 (class 2606 OID 26018)
-- Name: account account_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_email_key UNIQUE (email);


--
-- TOC entry 3011 (class 2606 OID 26271)
-- Name: account_order account_order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_order
    ADD CONSTRAINT account_order_pkey PRIMARY KEY (id);


--
-- TOC entry 2967 (class 2606 OID 26020)
-- Name: account account_phone_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_phone_key UNIQUE (phone);


--
-- TOC entry 2969 (class 2606 OID 26024)
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- TOC entry 2974 (class 2606 OID 26034)
-- Name: account_role account_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_role
    ADD CONSTRAINT account_role_pkey PRIMARY KEY (account_id, role_id);


--
-- TOC entry 2960 (class 2606 OID 26009)
-- Name: account_status account_status_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_status
    ADD CONSTRAINT account_status_key UNIQUE (status);


--
-- TOC entry 2962 (class 2606 OID 26011)
-- Name: account_status account_status_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_status
    ADD CONSTRAINT account_status_pkey PRIMARY KEY (id);


--
-- TOC entry 2971 (class 2606 OID 26022)
-- Name: account account_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_username_key UNIQUE (username);


--
-- TOC entry 2977 (class 2606 OID 26050)
-- Name: account_address address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- TOC entry 3017 (class 2606 OID 26636)
-- Name: brand brand_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.brand
    ADD CONSTRAINT brand_key UNIQUE (organization);


--
-- TOC entry 3019 (class 2606 OID 26468)
-- Name: brand brand_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.brand
    ADD CONSTRAINT brand_pkey PRIMARY KEY (id);


--
-- TOC entry 3028 (class 2606 OID 26717)
-- Name: cart_item cart_item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart_item
    ADD CONSTRAINT cart_item_pkey PRIMARY KEY (id);


--
-- TOC entry 3025 (class 2606 OID 26711)
-- Name: cart cart_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_pkey PRIMARY KEY (id);


--
-- TOC entry 2986 (class 2606 OID 26146)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 3005 (class 2606 OID 26252)
-- Name: coupons coupons_code_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coupons
    ADD CONSTRAINT coupons_code_key UNIQUE (code);


--
-- TOC entry 3008 (class 2606 OID 26254)
-- Name: coupons coupons_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coupons
    ADD CONSTRAINT coupons_pkey PRIMARY KEY (id);


--
-- TOC entry 2980 (class 2606 OID 26077)
-- Name: image image_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);


--
-- TOC entry 2996 (class 2606 OID 26222)
-- Name: order_status order_status_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_status
    ADD CONSTRAINT order_status_name_key UNIQUE (name);


--
-- TOC entry 2998 (class 2606 OID 26224)
-- Name: order_status order_status_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_status
    ADD CONSTRAINT order_status_pkey PRIMARY KEY (id);


--
-- TOC entry 3014 (class 2606 OID 26298)
-- Name: order_detail orderdetail_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_detail
    ADD CONSTRAINT orderdetail_pkey PRIMARY KEY (id);


--
-- TOC entry 2983 (class 2606 OID 26118)
-- Name: organization_address organization_address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization_address
    ADD CONSTRAINT organization_address_pkey PRIMARY KEY (id);


--
-- TOC entry 3022 (class 2606 OID 26473)
-- Name: organization organization_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT organization_pkey PRIMARY KEY (id);


--
-- TOC entry 3001 (class 2606 OID 26237)
-- Name: payment_method payment_method_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment_method
    ADD CONSTRAINT payment_method_name_key UNIQUE (name);


--
-- TOC entry 3003 (class 2606 OID 26239)
-- Name: payment_method payment_method_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment_method
    ADD CONSTRAINT payment_method_pkey PRIMARY KEY (id);


--
-- TOC entry 2991 (class 2606 OID 26188)
-- Name: product_image product_image_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_image
    ADD CONSTRAINT product_image_pkey PRIMARY KEY (product_id, image_id);


--
-- TOC entry 2989 (class 2606 OID 26170)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 2956 (class 2606 OID 26003)
-- Name: role role_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_key UNIQUE (name);


--
-- TOC entry 2958 (class 2606 OID 26001)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- TOC entry 2993 (class 2606 OID 26317)
-- Name: product_image uk_tivpmk2l4ujptj6oc99dlday6; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_image
    ADD CONSTRAINT uk_tivpmk2l4ujptj6oc99dlday6 UNIQUE (image_id);


--
-- TOC entry 2975 (class 1259 OID 26649)
-- Name: account_address_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX account_address_index ON public.account_address USING btree (id, account_id, address);


--
-- TOC entry 3009 (class 1259 OID 26650)
-- Name: account_order_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX account_order_index ON public.account_order USING btree (id);


--
-- TOC entry 3015 (class 1259 OID 26651)
-- Name: brand_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX brand_index ON public.brand USING btree (id, name, organization);


--
-- TOC entry 3023 (class 1259 OID 26718)
-- Name: cart_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX cart_index ON public.cart USING btree (id, customer_id);


--
-- TOC entry 3026 (class 1259 OID 26719)
-- Name: cart_item_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX cart_item_index ON public.cart_item USING btree (id, cart_id, product_id);


--
-- TOC entry 2984 (class 1259 OID 26654)
-- Name: category_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX category_index ON public.category USING btree (id, name, brand);


--
-- TOC entry 3006 (class 1259 OID 26655)
-- Name: coupons_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX coupons_index ON public.coupons USING btree (id, code, product_discount);


--
-- TOC entry 2978 (class 1259 OID 26656)
-- Name: image_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX image_index ON public.image USING btree (id, url);


--
-- TOC entry 2963 (class 1259 OID 26648)
-- Name: index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX index ON public.account_status USING btree (id);


--
-- TOC entry 2972 (class 1259 OID 26647)
-- Name: mulitindex1; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX mulitindex1 ON public.account USING btree (id, username, password, email);


--
-- TOC entry 3012 (class 1259 OID 26657)
-- Name: order_detail_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX order_detail_index ON public.order_detail USING btree (id, order_id, item_id);


--
-- TOC entry 2994 (class 1259 OID 26658)
-- Name: order_status_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX order_status_index ON public.order_status USING btree (id, name);


--
-- TOC entry 2981 (class 1259 OID 26660)
-- Name: organization_address_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX organization_address_index ON public.organization_address USING btree (id, organization_id);


--
-- TOC entry 3020 (class 1259 OID 26659)
-- Name: organization_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX organization_index ON public.organization USING btree (id, name, image);


--
-- TOC entry 2999 (class 1259 OID 26661)
-- Name: payment_method_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX payment_method_index ON public.payment_method USING btree (id, name);


--
-- TOC entry 2987 (class 1259 OID 26662)
-- Name: product_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX product_index ON public.product USING btree (id, name, brand, category);


--
-- TOC entry 2954 (class 1259 OID 26663)
-- Name: role_index; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX role_index ON public.role USING btree (id, name);


--
-- TOC entry 3055 (class 2606 OID 26725)
-- Name: cart_item fk1uobyhgl1wvgt1jpccia8xxs3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart_item
    ADD CONSTRAINT fk1uobyhgl1wvgt1jpccia8xxs3 FOREIGN KEY (cart_id) REFERENCES public.cart(id);


--
-- TOC entry 3030 (class 2606 OID 26035)
-- Name: account_role fk_account; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_role
    ADD CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES public.account(id);


--
-- TOC entry 3036 (class 2606 OID 26147)
-- Name: category fk_account; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT fk_account FOREIGN KEY (create_by) REFERENCES public.account(id);


--
-- TOC entry 3042 (class 2606 OID 26225)
-- Name: order_status fk_account; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_status
    ADD CONSTRAINT fk_account FOREIGN KEY (create_by) REFERENCES public.account(id);


--
-- TOC entry 3043 (class 2606 OID 26240)
-- Name: payment_method fk_account; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment_method
    ADD CONSTRAINT fk_account FOREIGN KEY (create_by) REFERENCES public.account(id);


--
-- TOC entry 3044 (class 2606 OID 26255)
-- Name: coupons fk_account; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coupons
    ADD CONSTRAINT fk_account FOREIGN KEY (create_by) REFERENCES public.account(id);


--
-- TOC entry 3046 (class 2606 OID 26272)
-- Name: account_order fk_account; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_order
    ADD CONSTRAINT fk_account FOREIGN KEY (customer_id) REFERENCES public.account(id);


--
-- TOC entry 3029 (class 2606 OID 26025)
-- Name: account fk_account_status; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT fk_account_status FOREIGN KEY (status) REFERENCES public.account_status(id);


--
-- TOC entry 3032 (class 2606 OID 26051)
-- Name: account_address fk_address; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_address
    ADD CONSTRAINT fk_address FOREIGN KEY (account_id) REFERENCES public.account(id);


--
-- TOC entry 3035 (class 2606 OID 26637)
-- Name: category fk_brand; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT fk_brand FOREIGN KEY (brand) REFERENCES public.brand(id);


--
-- TOC entry 3039 (class 2606 OID 26171)
-- Name: product fk_category; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_category FOREIGN KEY (category) REFERENCES public.category(id);


--
-- TOC entry 3033 (class 2606 OID 26078)
-- Name: image fk_image_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT fk_image_id FOREIGN KEY (create_by) REFERENCES public.account(id);


--
-- TOC entry 3041 (class 2606 OID 26194)
-- Name: product_image fk_image_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_image
    ADD CONSTRAINT fk_image_id FOREIGN KEY (image_id) REFERENCES public.image(id);


--
-- TOC entry 3034 (class 2606 OID 26610)
-- Name: organization_address fk_organization_address; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization_address
    ADD CONSTRAINT fk_organization_address FOREIGN KEY (organization_id) REFERENCES public.organization(id);


--
-- TOC entry 3037 (class 2606 OID 26157)
-- Name: category fk_parent_category; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT fk_parent_category FOREIGN KEY (parent_category) REFERENCES public.category(id);


--
-- TOC entry 3047 (class 2606 OID 26277)
-- Name: account_order fk_payment; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_order
    ADD CONSTRAINT fk_payment FOREIGN KEY (payment_method) REFERENCES public.payment_method(id);


--
-- TOC entry 3045 (class 2606 OID 26260)
-- Name: coupons fk_product; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coupons
    ADD CONSTRAINT fk_product FOREIGN KEY (product_discount) REFERENCES public.product(id);


--
-- TOC entry 3048 (class 2606 OID 26282)
-- Name: account_order fk_product; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_order
    ADD CONSTRAINT fk_product FOREIGN KEY (coupon_id) REFERENCES public.coupons(id);


--
-- TOC entry 3050 (class 2606 OID 26299)
-- Name: order_detail fk_product; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_detail
    ADD CONSTRAINT fk_product FOREIGN KEY (item_id) REFERENCES public.product(id);


--
-- TOC entry 3040 (class 2606 OID 26189)
-- Name: product_image fk_product_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_image
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3031 (class 2606 OID 26040)
-- Name: account_role fk_role; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_role
    ADD CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- TOC entry 3049 (class 2606 OID 26287)
-- Name: account_order fk_status; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_order
    ADD CONSTRAINT fk_status FOREIGN KEY (status) REFERENCES public.order_status(id);


--
-- TOC entry 3038 (class 2606 OID 26642)
-- Name: product fk_supplier; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_supplier FOREIGN KEY (brand) REFERENCES public.brand(id);


--
-- TOC entry 3051 (class 2606 OID 26304)
-- Name: order_detail fk_userorder; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_detail
    ADD CONSTRAINT fk_userorder FOREIGN KEY (order_id) REFERENCES public.account_order(id);


--
-- TOC entry 3054 (class 2606 OID 26720)
-- Name: cart fkbfc6w62eqifpktg1xrftoqoan; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT fkbfc6w62eqifpktg1xrftoqoan FOREIGN KEY (customer_id) REFERENCES public.account(id);


--
-- TOC entry 3052 (class 2606 OID 26474)
-- Name: brand fkhd413877k8dfig0c150y4a0d2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.brand
    ADD CONSTRAINT fkhd413877k8dfig0c150y4a0d2 FOREIGN KEY (organization) REFERENCES public.organization(id);


--
-- TOC entry 3056 (class 2606 OID 26730)
-- Name: cart_item fkjcyd5wv4igqnw413rgxbfu4nv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart_item
    ADD CONSTRAINT fkjcyd5wv4igqnw413rgxbfu4nv FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3053 (class 2606 OID 26484)
-- Name: organization fkr88ivsbxxchdfwinxdfag3rno; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT fkr88ivsbxxchdfwinxdfag3rno FOREIGN KEY (image) REFERENCES public.image(id);


-- Completed on 2021-07-21 15:51:19

--
-- PostgreSQL database dump complete
--

