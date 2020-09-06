--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-09-06 16:46:59

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
-- TOC entry 4 (class 2615 OID 24972)
-- Name: simedSch; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA "simedSch";


ALTER SCHEMA "simedSch" OWNER TO postgres;

--
-- TOC entry 2913 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA "simedSch"; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA "simedSch" IS 'Esquema principal de la base de datos SIMED';


--
-- TOC entry 215 (class 1259 OID 25065)
-- Name: EPS_SEQ; Type: SEQUENCE; Schema: simedSch; Owner: postgres
--

CREATE SEQUENCE "simedSch"."EPS_SEQ"
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE "simedSch"."EPS_SEQ" OWNER TO postgres;

SET default_tablespace = "pg_default";

SET default_table_access_method = heap;

--
-- TOC entry 204 (class 1259 OID 24981)
-- Name: EPS; Type: TABLE; Schema: simedSch; Owner: postgres; Tablespace: pg_default
--

CREATE TABLE "simedSch"."EPS" (
    "ID_EPS" smallint DEFAULT nextval('"simedSch"."EPS_SEQ"'::regclass) NOT NULL,
    "NOMBRE_EPS" character varying(100),
    "DIRECCION_EPS" character varying(100),
    "TELEFONO_EPS" character varying(100),
    "ESTADO_EPS" boolean
);


ALTER TABLE "simedSch"."EPS" OWNER TO postgres;

--
-- TOC entry 2914 (class 0 OID 0)
-- Dependencies: 204
-- Name: TABLE "EPS"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON TABLE "simedSch"."EPS" IS 'Empresa prestadora de Salud';


--
-- TOC entry 2915 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN "EPS"."ID_EPS"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."EPS"."ID_EPS" IS 'Identificador unico de registro ';


--
-- TOC entry 2916 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN "EPS"."NOMBRE_EPS"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."EPS"."NOMBRE_EPS" IS 'Nombre de la entidad prestadora de salud';


--
-- TOC entry 2917 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN "EPS"."DIRECCION_EPS"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."EPS"."DIRECCION_EPS" IS 'Direccion de ubicacion de la EPS';


--
-- TOC entry 2918 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN "EPS"."TELEFONO_EPS"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."EPS"."TELEFONO_EPS" IS 'Numero telefonico de EPS';


--
-- TOC entry 217 (class 1259 OID 25069)
-- Name: ESPECIALIDAD_SEQ; Type: SEQUENCE; Schema: simedSch; Owner: postgres
--

CREATE SEQUENCE "simedSch"."ESPECIALIDAD_SEQ"
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE "simedSch"."ESPECIALIDAD_SEQ" OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 25004)
-- Name: ESPECIALIDAD; Type: TABLE; Schema: simedSch; Owner: postgres; Tablespace: pg_default
--

CREATE TABLE "simedSch"."ESPECIALIDAD" (
    "ID_ESPECIALIDAD" smallint DEFAULT nextval('"simedSch"."ESPECIALIDAD_SEQ"'::regclass) NOT NULL,
    "NOMBRE_ESPECIALIDAD" character varying(100)
);


ALTER TABLE "simedSch"."ESPECIALIDAD" OWNER TO postgres;

--
-- TOC entry 2920 (class 0 OID 0)
-- Dependencies: 208
-- Name: TABLE "ESPECIALIDAD"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON TABLE "simedSch"."ESPECIALIDAD" IS 'Especialidad médica';


--
-- TOC entry 2921 (class 0 OID 0)
-- Dependencies: 208
-- Name: COLUMN "ESPECIALIDAD"."ID_ESPECIALIDAD"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."ESPECIALIDAD"."ID_ESPECIALIDAD" IS 'Identificador de Especialidad Médica';


--
-- TOC entry 2922 (class 0 OID 0)
-- Dependencies: 208
-- Name: COLUMN "ESPECIALIDAD"."NOMBRE_ESPECIALIDAD"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."ESPECIALIDAD"."NOMBRE_ESPECIALIDAD" IS 'Nombre de la especialidad Médica';


--
-- TOC entry 216 (class 1259 OID 25067)
-- Name: LUGAR_ATENCION_SEQ; Type: SEQUENCE; Schema: simedSch; Owner: postgres
--

CREATE SEQUENCE "simedSch"."LUGAR_ATENCION_SEQ"
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE "simedSch"."LUGAR_ATENCION_SEQ" OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 24999)
-- Name: LUGAR_ATENCION; Type: TABLE; Schema: simedSch; Owner: postgres; Tablespace: pg_default
--

CREATE TABLE "simedSch"."LUGAR_ATENCION" (
    "ID_LUGARES_ATENCION" smallint DEFAULT nextval('"simedSch"."LUGAR_ATENCION_SEQ"'::regclass) NOT NULL,
    "NOMBRE_LUGAR_ATENCION" character varying(100),
    "DIRECCION_LUGAR_ATENCION" character varying(100),
    "TELEFONO_LUGAR_ATENCION" character varying(20),
    "SITIO_WEB_LUGAR_ATENCION" character varying(100),
    "LATITUD_LUGAR_ATENCION" double precision,
    "LONGITUD_LUGAR_ATENCION" double precision
);


ALTER TABLE "simedSch"."LUGAR_ATENCION" OWNER TO postgres;

--
-- TOC entry 2924 (class 0 OID 0)
-- Dependencies: 207
-- Name: TABLE "LUGAR_ATENCION"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON TABLE "simedSch"."LUGAR_ATENCION" IS 'Lugares de atencion asociados a una EPS';


--
-- TOC entry 2925 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN "LUGAR_ATENCION"."ID_LUGARES_ATENCION"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."LUGAR_ATENCION"."ID_LUGARES_ATENCION" IS 'identificador unico de lugares de atención';


--
-- TOC entry 2926 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN "LUGAR_ATENCION"."NOMBRE_LUGAR_ATENCION"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."LUGAR_ATENCION"."NOMBRE_LUGAR_ATENCION" IS 'Nombre del lugar de atención';


--
-- TOC entry 2927 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN "LUGAR_ATENCION"."DIRECCION_LUGAR_ATENCION"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."LUGAR_ATENCION"."DIRECCION_LUGAR_ATENCION" IS 'Dirección del Lugar de atención';


--
-- TOC entry 2928 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN "LUGAR_ATENCION"."TELEFONO_LUGAR_ATENCION"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."LUGAR_ATENCION"."TELEFONO_LUGAR_ATENCION" IS 'Numero telefonico del Lugar de atención';


--
-- TOC entry 2929 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN "LUGAR_ATENCION"."SITIO_WEB_LUGAR_ATENCION"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."LUGAR_ATENCION"."SITIO_WEB_LUGAR_ATENCION" IS 'Sitio web del lugar de atención';


--
-- TOC entry 2930 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN "LUGAR_ATENCION"."LATITUD_LUGAR_ATENCION"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."LUGAR_ATENCION"."LATITUD_LUGAR_ATENCION" IS 'Latitud del lugar de atención';


--
-- TOC entry 2931 (class 0 OID 0)
-- Dependencies: 207
-- Name: COLUMN "LUGAR_ATENCION"."LONGITUD_LUGAR_ATENCION"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."LUGAR_ATENCION"."LONGITUD_LUGAR_ATENCION" IS 'Longitud del lugar de atención';


--
-- TOC entry 210 (class 1259 OID 25029)
-- Name: LUGAR_ATENCION_X_EPS; Type: TABLE; Schema: simedSch; Owner: postgres; Tablespace: pg_default
--

CREATE TABLE "simedSch"."LUGAR_ATENCION_X_EPS" (
    "ID_LUGARES_ATENCION" smallint NOT NULL,
    "ID_EPS" smallint NOT NULL
);


ALTER TABLE "simedSch"."LUGAR_ATENCION_X_EPS" OWNER TO postgres;

--
-- TOC entry 2933 (class 0 OID 0)
-- Dependencies: 210
-- Name: COLUMN "LUGAR_ATENCION_X_EPS"."ID_LUGARES_ATENCION"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."LUGAR_ATENCION_X_EPS"."ID_LUGARES_ATENCION" IS 'identificador unico de lugares de atención';


--
-- TOC entry 2934 (class 0 OID 0)
-- Dependencies: 210
-- Name: COLUMN "LUGAR_ATENCION_X_EPS"."ID_EPS"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."LUGAR_ATENCION_X_EPS"."ID_EPS" IS 'Identificador unico de registro ';


--
-- TOC entry 211 (class 1259 OID 25044)
-- Name: LUGAR_ATENCION_X_ESPECIALIDAD; Type: TABLE; Schema: simedSch; Owner: postgres; Tablespace: pg_default
--

CREATE TABLE "simedSch"."LUGAR_ATENCION_X_ESPECIALIDAD" (
    "ID_LUGARES_ATENCION" smallint NOT NULL,
    "ID_ESPECIALIDAD" smallint NOT NULL
);


ALTER TABLE "simedSch"."LUGAR_ATENCION_X_ESPECIALIDAD" OWNER TO postgres;

--
-- TOC entry 2936 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN "LUGAR_ATENCION_X_ESPECIALIDAD"."ID_LUGARES_ATENCION"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."LUGAR_ATENCION_X_ESPECIALIDAD"."ID_LUGARES_ATENCION" IS 'identificador unico de lugares de atención';


--
-- TOC entry 2937 (class 0 OID 0)
-- Dependencies: 211
-- Name: COLUMN "LUGAR_ATENCION_X_ESPECIALIDAD"."ID_ESPECIALIDAD"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."LUGAR_ATENCION_X_ESPECIALIDAD"."ID_ESPECIALIDAD" IS 'Identificador de Especialidad Médica';


--
-- TOC entry 213 (class 1259 OID 25061)
-- Name: PROPIEDAD_SEQ; Type: SEQUENCE; Schema: simedSch; Owner: postgres
--

CREATE SEQUENCE "simedSch"."PROPIEDAD_SEQ"
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE "simedSch"."PROPIEDAD_SEQ" OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 24991)
-- Name: PROPIEDAD; Type: TABLE; Schema: simedSch; Owner: postgres; Tablespace: pg_default
--

CREATE TABLE "simedSch"."PROPIEDAD" (
    "ID_PROPIEDAD" smallint DEFAULT nextval('"simedSch"."PROPIEDAD_SEQ"'::regclass) NOT NULL,
    "ESTADO_PROPIEDAD" boolean,
    "NOMBRE_PROPIEDAD" character varying(250),
    "VALOR_PROPIEDAD" character varying(2000)
);


ALTER TABLE "simedSch"."PROPIEDAD" OWNER TO postgres;

--
-- TOC entry 2939 (class 0 OID 0)
-- Dependencies: 206
-- Name: TABLE "PROPIEDAD"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON TABLE "simedSch"."PROPIEDAD" IS 'Tabla propiedades SIMED';


--
-- TOC entry 2940 (class 0 OID 0)
-- Dependencies: 206
-- Name: COLUMN "PROPIEDAD"."ID_PROPIEDAD"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."PROPIEDAD"."ID_PROPIEDAD" IS 'Propiedades de SIMED';


--
-- TOC entry 2941 (class 0 OID 0)
-- Dependencies: 206
-- Name: COLUMN "PROPIEDAD"."ESTADO_PROPIEDAD"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."PROPIEDAD"."ESTADO_PROPIEDAD" IS 'Estado de una propiedad 1 activo, 0 inactivo';


--
-- TOC entry 2942 (class 0 OID 0)
-- Dependencies: 206
-- Name: COLUMN "PROPIEDAD"."VALOR_PROPIEDAD"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."PROPIEDAD"."VALOR_PROPIEDAD" IS 'Contenido de la propiedad, puede ser un JSON, la cabecera de un correo electronico ';


--
-- TOC entry 218 (class 1259 OID 25075)
-- Name: tipo_identificacion_seq; Type: SEQUENCE; Schema: simedSch; Owner: postgres
--

CREATE SEQUENCE "simedSch".tipo_identificacion_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE "simedSch".tipo_identificacion_seq OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 25009)
-- Name: TIPO_IDENTIFICACION; Type: TABLE; Schema: simedSch; Owner: postgres; Tablespace: pg_default
--

CREATE TABLE "simedSch"."TIPO_IDENTIFICACION" (
    "ID_TIPO_IDENTIFICACION" smallint DEFAULT nextval('"simedSch".tipo_identificacion_seq'::regclass) NOT NULL,
    "NOMBRE_TIPO_IDENTIFICACION" character varying(100)
);


ALTER TABLE "simedSch"."TIPO_IDENTIFICACION" OWNER TO postgres;

--
-- TOC entry 2944 (class 0 OID 0)
-- Dependencies: 209
-- Name: TABLE "TIPO_IDENTIFICACION"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON TABLE "simedSch"."TIPO_IDENTIFICACION" IS 'Tipo de Identificación documento identidad puede ser CC, TI, CE, NUIP otros';


--
-- TOC entry 2945 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN "TIPO_IDENTIFICACION"."ID_TIPO_IDENTIFICACION"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."TIPO_IDENTIFICACION"."ID_TIPO_IDENTIFICACION" IS 'Identificador único de la tabla tipo de identificación';


--
-- TOC entry 2946 (class 0 OID 0)
-- Dependencies: 209
-- Name: COLUMN "TIPO_IDENTIFICACION"."NOMBRE_TIPO_IDENTIFICACION"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."TIPO_IDENTIFICACION"."NOMBRE_TIPO_IDENTIFICACION" IS 'Nombre del tipo de identificación';


--
-- TOC entry 214 (class 1259 OID 25063)
-- Name: TIPO_USUARIO_SEQ; Type: SEQUENCE; Schema: simedSch; Owner: postgres
--

CREATE SEQUENCE "simedSch"."TIPO_USUARIO_SEQ"
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE "simedSch"."TIPO_USUARIO_SEQ" OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 24986)
-- Name: TIPO_USUARIO; Type: TABLE; Schema: simedSch; Owner: postgres; Tablespace: pg_default
--

CREATE TABLE "simedSch"."TIPO_USUARIO" (
    "ID_TIPO_USUARIO" smallint DEFAULT nextval('"simedSch"."TIPO_USUARIO_SEQ"'::regclass) NOT NULL,
    "NOMBRE_TIPO_USUARIO" character varying(100)
);


ALTER TABLE "simedSch"."TIPO_USUARIO" OWNER TO postgres;

--
-- TOC entry 2948 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "TIPO_USUARIO"."ID_TIPO_USUARIO"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."TIPO_USUARIO"."ID_TIPO_USUARIO" IS 'Numero de identificacion de Usuarios';


--
-- TOC entry 2949 (class 0 OID 0)
-- Dependencies: 205
-- Name: COLUMN "TIPO_USUARIO"."NOMBRE_TIPO_USUARIO"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."TIPO_USUARIO"."NOMBRE_TIPO_USUARIO" IS 'Nombre del tipo Usuario';


--
-- TOC entry 212 (class 1259 OID 25059)
-- Name: USUARIO_SEQ; Type: SEQUENCE; Schema: simedSch; Owner: postgres
--

CREATE SEQUENCE "simedSch"."USUARIO_SEQ"
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    MAXVALUE 2147483647
    CACHE 1;


ALTER TABLE "simedSch"."USUARIO_SEQ" OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 24973)
-- Name: USUARIO; Type: TABLE; Schema: simedSch; Owner: postgres; Tablespace: pg_default
--

CREATE TABLE "simedSch"."USUARIO" (
    "ID_USUARIO" bigint DEFAULT nextval('"simedSch"."USUARIO_SEQ"'::regclass) NOT NULL,
    "NOMBRE_USUARIO" character varying,
    "APELLIDOS_USUARIO" character varying(100),
    "NUMERO_IDENTIFICACION_USUARIO" character varying(20),
    "PASSWORD_USUARIO" character varying(250),
    "CORREO_USUARIO" character varying(250),
    "ESTADO_USUARIO" boolean,
    "FECHA_CREACION_USUARIO" timestamp without time zone,
    "FECHA_MODIFICACION_USUARIO" timestamp without time zone,
    "ID_TIPO_IDENTIFICACION" smallint,
    "ID_TIPO_USUARIO" smallint,
    "ID_EPS" smallint
);


ALTER TABLE "simedSch"."USUARIO" OWNER TO postgres;

--
-- TOC entry 2951 (class 0 OID 0)
-- Dependencies: 203
-- Name: TABLE "USUARIO"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON TABLE "simedSch"."USUARIO" IS 'Tabla usuarios ';


--
-- TOC entry 2952 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN "USUARIO"."ID_USUARIO"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."USUARIO"."ID_USUARIO" IS 'Identificador unico de usuarios en SIMED';


--
-- TOC entry 2953 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN "USUARIO"."NOMBRE_USUARIO"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."USUARIO"."NOMBRE_USUARIO" IS 'Nombre de usuario SIMED';


--
-- TOC entry 2954 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN "USUARIO"."APELLIDOS_USUARIO"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."USUARIO"."APELLIDOS_USUARIO" IS 'Apellidos Usuario de SIMED';


--
-- TOC entry 2955 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN "USUARIO"."NUMERO_IDENTIFICACION_USUARIO"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."USUARIO"."NUMERO_IDENTIFICACION_USUARIO" IS 'Numero de documento del Usuario de SIMED';


--
-- TOC entry 2956 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN "USUARIO"."PASSWORD_USUARIO"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."USUARIO"."PASSWORD_USUARIO" IS 'Password de Usuario de SIMED';


--
-- TOC entry 2957 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN "USUARIO"."CORREO_USUARIO"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."USUARIO"."CORREO_USUARIO" IS 'Cuenta de Correo electronico del Usuario ';


--
-- TOC entry 2958 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN "USUARIO"."ESTADO_USUARIO"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."USUARIO"."ESTADO_USUARIO" IS 'Estado del Usuario, puede activo 1, inactivo 0';


--
-- TOC entry 2959 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN "USUARIO"."FECHA_CREACION_USUARIO"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."USUARIO"."FECHA_CREACION_USUARIO" IS 'Fecha de Creacion del registro ';


--
-- TOC entry 2960 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN "USUARIO"."FECHA_MODIFICACION_USUARIO"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."USUARIO"."FECHA_MODIFICACION_USUARIO" IS 'Fecha de modificacion dell registro de usuario';


--
-- TOC entry 2961 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN "USUARIO"."ID_TIPO_IDENTIFICACION"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."USUARIO"."ID_TIPO_IDENTIFICACION" IS 'Identificador único de la tabla tipo de identificación';


--
-- TOC entry 2962 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN "USUARIO"."ID_TIPO_USUARIO"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."USUARIO"."ID_TIPO_USUARIO" IS 'Numero de identificacion de Usuarios';


--
-- TOC entry 2963 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN "USUARIO"."ID_EPS"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON COLUMN "simedSch"."USUARIO"."ID_EPS" IS 'Identificador unico de registro ';


--
-- TOC entry 2893 (class 0 OID 24981)
-- Dependencies: 204
-- Data for Name: EPS; Type: TABLE DATA; Schema: simedSch; Owner: postgres
--

COPY "simedSch"."EPS" ("ID_EPS", "NOMBRE_EPS", "DIRECCION_EPS", "TELEFONO_EPS", "ESTADO_EPS") FROM stdin;
1	COMPENSAR	CARRERA 26 No 68-14	2459632	t
2	NUEVA_EPS	TV 15 No 18-96	4895632	t
3	Salud Total S.A. E.P.S.	Cra. 104 #14-50	4854555 	t
\.


--
-- TOC entry 2897 (class 0 OID 25004)
-- Dependencies: 208
-- Data for Name: ESPECIALIDAD; Type: TABLE DATA; Schema: simedSch; Owner: postgres
--

COPY "simedSch"."ESPECIALIDAD" ("ID_ESPECIALIDAD", "NOMBRE_ESPECIALIDAD") FROM stdin;
\.


--
-- TOC entry 2896 (class 0 OID 24999)
-- Dependencies: 207
-- Data for Name: LUGAR_ATENCION; Type: TABLE DATA; Schema: simedSch; Owner: postgres
--

COPY "simedSch"."LUGAR_ATENCION" ("ID_LUGARES_ATENCION", "NOMBRE_LUGAR_ATENCION", "DIRECCION_LUGAR_ATENCION", "TELEFONO_LUGAR_ATENCION", "SITIO_WEB_LUGAR_ATENCION", "LATITUD_LUGAR_ATENCION", "LONGITUD_LUGAR_ATENCION") FROM stdin;
\.


--
-- TOC entry 2899 (class 0 OID 25029)
-- Dependencies: 210
-- Data for Name: LUGAR_ATENCION_X_EPS; Type: TABLE DATA; Schema: simedSch; Owner: postgres
--

COPY "simedSch"."LUGAR_ATENCION_X_EPS" ("ID_LUGARES_ATENCION", "ID_EPS") FROM stdin;
\.


--
-- TOC entry 2900 (class 0 OID 25044)
-- Dependencies: 211
-- Data for Name: LUGAR_ATENCION_X_ESPECIALIDAD; Type: TABLE DATA; Schema: simedSch; Owner: postgres
--

COPY "simedSch"."LUGAR_ATENCION_X_ESPECIALIDAD" ("ID_LUGARES_ATENCION", "ID_ESPECIALIDAD") FROM stdin;
\.


--
-- TOC entry 2895 (class 0 OID 24991)
-- Dependencies: 206
-- Data for Name: PROPIEDAD; Type: TABLE DATA; Schema: simedSch; Owner: postgres
--

COPY "simedSch"."PROPIEDAD" ("ID_PROPIEDAD", "ESTADO_PROPIEDAD", "NOMBRE_PROPIEDAD", "VALOR_PROPIEDAD") FROM stdin;
\.


--
-- TOC entry 2898 (class 0 OID 25009)
-- Dependencies: 209
-- Data for Name: TIPO_IDENTIFICACION; Type: TABLE DATA; Schema: simedSch; Owner: postgres
--

COPY "simedSch"."TIPO_IDENTIFICACION" ("ID_TIPO_IDENTIFICACION", "NOMBRE_TIPO_IDENTIFICACION") FROM stdin;
1	CC
2	CE
3	TI
4	NUIP
\.


--
-- TOC entry 2894 (class 0 OID 24986)
-- Dependencies: 205
-- Data for Name: TIPO_USUARIO; Type: TABLE DATA; Schema: simedSch; Owner: postgres
--

COPY "simedSch"."TIPO_USUARIO" ("ID_TIPO_USUARIO", "NOMBRE_TIPO_USUARIO") FROM stdin;
1	ADMINISTRADOR
2	PREMIUM
3	ESTANDAR
\.


--
-- TOC entry 2892 (class 0 OID 24973)
-- Dependencies: 203
-- Data for Name: USUARIO; Type: TABLE DATA; Schema: simedSch; Owner: postgres
--

COPY "simedSch"."USUARIO" ("ID_USUARIO", "NOMBRE_USUARIO", "APELLIDOS_USUARIO", "NUMERO_IDENTIFICACION_USUARIO", "PASSWORD_USUARIO", "CORREO_USUARIO", "ESTADO_USUARIO", "FECHA_CREACION_USUARIO", "FECHA_MODIFICACION_USUARIO", "ID_TIPO_IDENTIFICACION", "ID_TIPO_USUARIO", "ID_EPS") FROM stdin;
1	Johan	Cespedes	123456	123456	j@simed.com	t	2004-10-19 10:23:54	2004-10-19 10:23:54	1	1	1
2	Jhon	Smith	456789	123456	smith@simed.com	t	2004-10-19 10:23:54	2004-10-19 10:23:54	2	1	1
3	Mar	R	45613	123456	m@simed.com.co	t	2020-09-02 00:00:00	2020-09-03 00:00:00	\N	\N	\N
\.


--
-- TOC entry 2965 (class 0 OID 0)
-- Dependencies: 215
-- Name: EPS_SEQ; Type: SEQUENCE SET; Schema: simedSch; Owner: postgres
--

SELECT pg_catalog.setval('"simedSch"."EPS_SEQ"', 2, true);


--
-- TOC entry 2966 (class 0 OID 0)
-- Dependencies: 217
-- Name: ESPECIALIDAD_SEQ; Type: SEQUENCE SET; Schema: simedSch; Owner: postgres
--

SELECT pg_catalog.setval('"simedSch"."ESPECIALIDAD_SEQ"', 1, false);


--
-- TOC entry 2967 (class 0 OID 0)
-- Dependencies: 216
-- Name: LUGAR_ATENCION_SEQ; Type: SEQUENCE SET; Schema: simedSch; Owner: postgres
--

SELECT pg_catalog.setval('"simedSch"."LUGAR_ATENCION_SEQ"', 1, false);


--
-- TOC entry 2968 (class 0 OID 0)
-- Dependencies: 213
-- Name: PROPIEDAD_SEQ; Type: SEQUENCE SET; Schema: simedSch; Owner: postgres
--

SELECT pg_catalog.setval('"simedSch"."PROPIEDAD_SEQ"', 1, false);


--
-- TOC entry 2969 (class 0 OID 0)
-- Dependencies: 214
-- Name: TIPO_USUARIO_SEQ; Type: SEQUENCE SET; Schema: simedSch; Owner: postgres
--

SELECT pg_catalog.setval('"simedSch"."TIPO_USUARIO_SEQ"', 3, true);


--
-- TOC entry 2970 (class 0 OID 0)
-- Dependencies: 212
-- Name: USUARIO_SEQ; Type: SEQUENCE SET; Schema: simedSch; Owner: postgres
--

SELECT pg_catalog.setval('"simedSch"."USUARIO_SEQ"', 3, true);


--
-- TOC entry 2971 (class 0 OID 0)
-- Dependencies: 218
-- Name: tipo_identificacion_seq; Type: SEQUENCE SET; Schema: simedSch; Owner: postgres
--

SELECT pg_catalog.setval('"simedSch".tipo_identificacion_seq', 4, true);


--
-- TOC entry 2744 (class 2606 OID 24985)
-- Name: EPS EPS_PK; Type: CONSTRAINT; Schema: simedSch; Owner: postgres; Tablespace: pg_default
--

ALTER TABLE ONLY "simedSch"."EPS"
    ADD CONSTRAINT "EPS_PK" PRIMARY KEY ("ID_EPS") WITH (fillfactor='10');


--
-- TOC entry 2972 (class 0 OID 0)
-- Dependencies: 2744
-- Name: CONSTRAINT "EPS_PK" ON "EPS"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON CONSTRAINT "EPS_PK" ON "simedSch"."EPS" IS 'Llave primarya Tabla EPS';


SET default_tablespace = 'pg_default';

--
-- TOC entry 2752 (class 2606 OID 25008)
-- Name: ESPECIALIDAD ID_ESPECIALIDAD_PK; Type: CONSTRAINT; Schema: simedSch; Owner: postgres
--

ALTER TABLE ONLY "simedSch"."ESPECIALIDAD"
    ADD CONSTRAINT "ID_ESPECIALIDAD_PK" PRIMARY KEY ("ID_ESPECIALIDAD");


--
-- TOC entry 2973 (class 0 OID 0)
-- Dependencies: 2752
-- Name: CONSTRAINT "ID_ESPECIALIDAD_PK" ON "ESPECIALIDAD"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON CONSTRAINT "ID_ESPECIALIDAD_PK" ON "simedSch"."ESPECIALIDAD" IS 'Llave primaria de Especialidades medicas';


SET default_tablespace = "pg_default";

--
-- TOC entry 2746 (class 2606 OID 24990)
-- Name: TIPO_USUARIO ID_USUARIO_PK; Type: CONSTRAINT; Schema: simedSch; Owner: postgres; Tablespace: pg_default
--

ALTER TABLE ONLY "simedSch"."TIPO_USUARIO"
    ADD CONSTRAINT "ID_USUARIO_PK" PRIMARY KEY ("ID_TIPO_USUARIO") WITH (fillfactor='10');


--
-- TOC entry 2974 (class 0 OID 0)
-- Dependencies: 2746
-- Name: CONSTRAINT "ID_USUARIO_PK" ON "TIPO_USUARIO"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON CONSTRAINT "ID_USUARIO_PK" ON "simedSch"."TIPO_USUARIO" IS 'Llave foranea de Usuarios';


--
-- TOC entry 2750 (class 2606 OID 25003)
-- Name: LUGAR_ATENCION LUGAR_ATENCION_PK; Type: CONSTRAINT; Schema: simedSch; Owner: postgres; Tablespace: pg_default
--

ALTER TABLE ONLY "simedSch"."LUGAR_ATENCION"
    ADD CONSTRAINT "LUGAR_ATENCION_PK" PRIMARY KEY ("ID_LUGARES_ATENCION") WITH (fillfactor='10');


--
-- TOC entry 2975 (class 0 OID 0)
-- Dependencies: 2750
-- Name: CONSTRAINT "LUGAR_ATENCION_PK" ON "LUGAR_ATENCION"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON CONSTRAINT "LUGAR_ATENCION_PK" ON "simedSch"."LUGAR_ATENCION" IS 'Llave primaria tabla lugares de atención';


SET default_tablespace = 'pg_default';

--
-- TOC entry 2756 (class 2606 OID 25033)
-- Name: LUGAR_ATENCION_X_EPS LUGAR_ATENCION_X_EPS_pk; Type: CONSTRAINT; Schema: simedSch; Owner: postgres
--

ALTER TABLE ONLY "simedSch"."LUGAR_ATENCION_X_EPS"
    ADD CONSTRAINT "LUGAR_ATENCION_X_EPS_pk" PRIMARY KEY ("ID_LUGARES_ATENCION", "ID_EPS");


SET default_tablespace = "pg_default";

--
-- TOC entry 2748 (class 2606 OID 24998)
-- Name: PROPIEDAD PROPIEDAD_PK; Type: CONSTRAINT; Schema: simedSch; Owner: postgres; Tablespace: pg_default
--

ALTER TABLE ONLY "simedSch"."PROPIEDAD"
    ADD CONSTRAINT "PROPIEDAD_PK" PRIMARY KEY ("ID_PROPIEDAD") WITH (fillfactor='10');


--
-- TOC entry 2976 (class 0 OID 0)
-- Dependencies: 2748
-- Name: CONSTRAINT "PROPIEDAD_PK" ON "PROPIEDAD"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON CONSTRAINT "PROPIEDAD_PK" ON "simedSch"."PROPIEDAD" IS 'Llave Primaria de la tabla Propiedades';


--
-- TOC entry 2754 (class 2606 OID 25013)
-- Name: TIPO_IDENTIFICACION TIPO_IDENTIFICACION_PK; Type: CONSTRAINT; Schema: simedSch; Owner: postgres; Tablespace: pg_default
--

ALTER TABLE ONLY "simedSch"."TIPO_IDENTIFICACION"
    ADD CONSTRAINT "TIPO_IDENTIFICACION_PK" PRIMARY KEY ("ID_TIPO_IDENTIFICACION") WITH (fillfactor='10');


--
-- TOC entry 2977 (class 0 OID 0)
-- Dependencies: 2754
-- Name: CONSTRAINT "TIPO_IDENTIFICACION_PK" ON "TIPO_IDENTIFICACION"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON CONSTRAINT "TIPO_IDENTIFICACION_PK" ON "simedSch"."TIPO_IDENTIFICACION" IS 'Llave primaria del tipo de identificación';


--
-- TOC entry 2742 (class 2606 OID 25086)
-- Name: USUARIO USUARIO_PK; Type: CONSTRAINT; Schema: simedSch; Owner: postgres; Tablespace: pg_default
--

ALTER TABLE ONLY "simedSch"."USUARIO"
    ADD CONSTRAINT "USUARIO_PK" PRIMARY KEY ("ID_USUARIO") WITH (fillfactor='10');


--
-- TOC entry 2978 (class 0 OID 0)
-- Dependencies: 2742
-- Name: CONSTRAINT "USUARIO_PK" ON "USUARIO"; Type: COMMENT; Schema: simedSch; Owner: postgres
--

COMMENT ON CONSTRAINT "USUARIO_PK" ON "simedSch"."USUARIO" IS 'llave primaria tabla usuario';


SET default_tablespace = 'pg_default';

--
-- TOC entry 2758 (class 2606 OID 25048)
-- Name: LUGAR_ATENCION_X_ESPECIALIDAD many_LUGAR_ATENCION_has_many_ESPECIALIDAD_pk; Type: CONSTRAINT; Schema: simedSch; Owner: postgres
--

ALTER TABLE ONLY "simedSch"."LUGAR_ATENCION_X_ESPECIALIDAD"
    ADD CONSTRAINT "many_LUGAR_ATENCION_has_many_ESPECIALIDAD_pk" PRIMARY KEY ("ID_LUGARES_ATENCION", "ID_ESPECIALIDAD");


--
-- TOC entry 2761 (class 2606 OID 25024)
-- Name: USUARIO EPS_fk; Type: FK CONSTRAINT; Schema: simedSch; Owner: postgres
--

ALTER TABLE ONLY "simedSch"."USUARIO"
    ADD CONSTRAINT "EPS_fk" FOREIGN KEY ("ID_EPS") REFERENCES "simedSch"."EPS"("ID_EPS") MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 2763 (class 2606 OID 25039)
-- Name: LUGAR_ATENCION_X_EPS EPS_fk; Type: FK CONSTRAINT; Schema: simedSch; Owner: postgres
--

ALTER TABLE ONLY "simedSch"."LUGAR_ATENCION_X_EPS"
    ADD CONSTRAINT "EPS_fk" FOREIGN KEY ("ID_EPS") REFERENCES "simedSch"."EPS"("ID_EPS") MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2765 (class 2606 OID 25054)
-- Name: LUGAR_ATENCION_X_ESPECIALIDAD ESPECIALIDAD_fk; Type: FK CONSTRAINT; Schema: simedSch; Owner: postgres
--

ALTER TABLE ONLY "simedSch"."LUGAR_ATENCION_X_ESPECIALIDAD"
    ADD CONSTRAINT "ESPECIALIDAD_fk" FOREIGN KEY ("ID_ESPECIALIDAD") REFERENCES "simedSch"."ESPECIALIDAD"("ID_ESPECIALIDAD") MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2762 (class 2606 OID 25034)
-- Name: LUGAR_ATENCION_X_EPS LUGAR_ATENCION_fk; Type: FK CONSTRAINT; Schema: simedSch; Owner: postgres
--

ALTER TABLE ONLY "simedSch"."LUGAR_ATENCION_X_EPS"
    ADD CONSTRAINT "LUGAR_ATENCION_fk" FOREIGN KEY ("ID_LUGARES_ATENCION") REFERENCES "simedSch"."LUGAR_ATENCION"("ID_LUGARES_ATENCION") MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2764 (class 2606 OID 25049)
-- Name: LUGAR_ATENCION_X_ESPECIALIDAD LUGAR_ATENCION_fk; Type: FK CONSTRAINT; Schema: simedSch; Owner: postgres
--

ALTER TABLE ONLY "simedSch"."LUGAR_ATENCION_X_ESPECIALIDAD"
    ADD CONSTRAINT "LUGAR_ATENCION_fk" FOREIGN KEY ("ID_LUGARES_ATENCION") REFERENCES "simedSch"."LUGAR_ATENCION"("ID_LUGARES_ATENCION") MATCH FULL ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2759 (class 2606 OID 25014)
-- Name: USUARIO TIPO_IDENTIFICACION_fk; Type: FK CONSTRAINT; Schema: simedSch; Owner: postgres
--

ALTER TABLE ONLY "simedSch"."USUARIO"
    ADD CONSTRAINT "TIPO_IDENTIFICACION_fk" FOREIGN KEY ("ID_TIPO_IDENTIFICACION") REFERENCES "simedSch"."TIPO_IDENTIFICACION"("ID_TIPO_IDENTIFICACION") MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 2760 (class 2606 OID 25019)
-- Name: USUARIO TIPO_USUARIO_fk; Type: FK CONSTRAINT; Schema: simedSch; Owner: postgres
--

ALTER TABLE ONLY "simedSch"."USUARIO"
    ADD CONSTRAINT "TIPO_USUARIO_fk" FOREIGN KEY ("ID_TIPO_USUARIO") REFERENCES "simedSch"."TIPO_USUARIO"("ID_TIPO_USUARIO") MATCH FULL ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 2919 (class 0 OID 0)
-- Dependencies: 204
-- Name: TABLE "EPS"; Type: ACL; Schema: simedSch; Owner: postgres
--

REVOKE ALL ON TABLE "simedSch"."EPS" FROM postgres;
GRANT ALL ON TABLE "simedSch"."EPS" TO postgres WITH GRANT OPTION;


--
-- TOC entry 2923 (class 0 OID 0)
-- Dependencies: 208
-- Name: TABLE "ESPECIALIDAD"; Type: ACL; Schema: simedSch; Owner: postgres
--

REVOKE ALL ON TABLE "simedSch"."ESPECIALIDAD" FROM postgres;
GRANT ALL ON TABLE "simedSch"."ESPECIALIDAD" TO postgres WITH GRANT OPTION;


--
-- TOC entry 2932 (class 0 OID 0)
-- Dependencies: 207
-- Name: TABLE "LUGAR_ATENCION"; Type: ACL; Schema: simedSch; Owner: postgres
--

REVOKE ALL ON TABLE "simedSch"."LUGAR_ATENCION" FROM postgres;
GRANT ALL ON TABLE "simedSch"."LUGAR_ATENCION" TO postgres WITH GRANT OPTION;


--
-- TOC entry 2935 (class 0 OID 0)
-- Dependencies: 210
-- Name: TABLE "LUGAR_ATENCION_X_EPS"; Type: ACL; Schema: simedSch; Owner: postgres
--

GRANT ALL ON TABLE "simedSch"."LUGAR_ATENCION_X_EPS" TO postgres WITH GRANT OPTION;


--
-- TOC entry 2938 (class 0 OID 0)
-- Dependencies: 211
-- Name: TABLE "LUGAR_ATENCION_X_ESPECIALIDAD"; Type: ACL; Schema: simedSch; Owner: postgres
--

GRANT ALL ON TABLE "simedSch"."LUGAR_ATENCION_X_ESPECIALIDAD" TO postgres WITH GRANT OPTION;


--
-- TOC entry 2943 (class 0 OID 0)
-- Dependencies: 206
-- Name: TABLE "PROPIEDAD"; Type: ACL; Schema: simedSch; Owner: postgres
--

REVOKE ALL ON TABLE "simedSch"."PROPIEDAD" FROM postgres;
GRANT ALL ON TABLE "simedSch"."PROPIEDAD" TO postgres WITH GRANT OPTION;


--
-- TOC entry 2947 (class 0 OID 0)
-- Dependencies: 209
-- Name: TABLE "TIPO_IDENTIFICACION"; Type: ACL; Schema: simedSch; Owner: postgres
--

REVOKE ALL ON TABLE "simedSch"."TIPO_IDENTIFICACION" FROM postgres;
GRANT ALL ON TABLE "simedSch"."TIPO_IDENTIFICACION" TO postgres WITH GRANT OPTION;


--
-- TOC entry 2950 (class 0 OID 0)
-- Dependencies: 205
-- Name: TABLE "TIPO_USUARIO"; Type: ACL; Schema: simedSch; Owner: postgres
--

REVOKE ALL ON TABLE "simedSch"."TIPO_USUARIO" FROM postgres;
GRANT ALL ON TABLE "simedSch"."TIPO_USUARIO" TO postgres WITH GRANT OPTION;


--
-- TOC entry 2964 (class 0 OID 0)
-- Dependencies: 203
-- Name: TABLE "USUARIO"; Type: ACL; Schema: simedSch; Owner: postgres
--

REVOKE ALL ON TABLE "simedSch"."USUARIO" FROM postgres;
GRANT ALL ON TABLE "simedSch"."USUARIO" TO postgres WITH GRANT OPTION;


-- Completed on 2020-09-06 16:46:59

--
-- PostgreSQL database dump complete
--

