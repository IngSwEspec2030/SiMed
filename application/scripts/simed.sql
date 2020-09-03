-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- PostgreSQL version: 9.2
-- Project Site: pgmodeler.com.br
-- Model Author: ---

SET check_function_bodies = false;
-- ddl-end --

-- object: adminsimed | type: ROLE --
CREATE ROLE adminsimed WITH 
	ENCRYPTED PASSWORD 'simed';
COMMENT ON ROLE adminsimed IS 'administrado Base de datos SIMED';
-- ddl-end --
-- ddl-end --

-- Tablespaces creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: "simedTS" | type: TABLESPACE --
-- CREATE TABLESPACE "simedTS"
-- 	OWNER adminsimed
-- 	LOCATION 'C:\FileSystemSimed';
-- -- ddl-end --
-- 


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: new_database | type: DATABASE --
-- CREATE DATABASE new_database
-- ;
-- -- ddl-end --
-- 

-- object: "simedSch" | type: SCHEMA --
CREATE SCHEMA "simedSch";
ALTER SCHEMA "simedSch" OWNER TO adminsimed;
COMMENT ON SCHEMA "simedSch" IS 'Esquema principal de la base de datos SIMED';
-- ddl-end --
-- ddl-end --

SET search_path TO pg_catalog,public,"simedSch";
-- ddl-end --

-- object: "simedSch"."USUARIO" | type: TABLE --
CREATE TABLE "simedSch"."USUARIO"(
	"ID_USUARIO" int2 NOT NULL,
	"NOMBRE_USUARIO" varchar,
	"APELLIDOS_USUARIO" varchar(100),
	"NUMERO_IDENTIFICACION_USUARIO" varchar(20),
	"PASSWORD_USUARIO" varchar(250),
	"CORREO_USUARIO" varchar(250),
	"ESTADO_USUARIO" boolean,
	"FECHA_CREACION_USUARIO" timestamp,
	"FECHA_MODIFICACION_USUARIO" timestamp,
	"ID_TIPO_IDENTIFICACION" int2,
	"ID_TIPO_USUARIO" int2,
	"ID_EPS" int2,
	CONSTRAINT "USUARIO_PK" PRIMARY KEY ("ID_USUARIO")
	WITH (FILLFACTOR = 10)
	USING INDEX TABLESPACE "simedTS"

)
TABLESPACE "simedTS";
-- ddl-end --
COMMENT ON TABLE "simedSch"."USUARIO" IS 'Tabla usuarios ';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."USUARIO"."ID_USUARIO" IS 'Identificador unico de usuarios en SIMED';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."USUARIO"."NOMBRE_USUARIO" IS 'Nombre de usuario SIMED';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."USUARIO"."APELLIDOS_USUARIO" IS 'Apellidos Usuario de SIMED';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."USUARIO"."NUMERO_IDENTIFICACION_USUARIO" IS 'Numero de documento del Usuario de SIMED';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."USUARIO"."PASSWORD_USUARIO" IS 'Password de Usuario de SIMED';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."USUARIO"."CORREO_USUARIO" IS 'Cuenta de Correo electronico del Usuario ';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."USUARIO"."ESTADO_USUARIO" IS 'Estado del Usuario, puede activo 1, inactivo 0';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."USUARIO"."FECHA_CREACION_USUARIO" IS 'Fecha de Creacion del registro ';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."USUARIO"."FECHA_MODIFICACION_USUARIO" IS 'Fecha de modificacion dell registro de usuario';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."USUARIO"."ID_TIPO_IDENTIFICACION" IS 'Identificador único de la tabla tipo de identificación';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."USUARIO"."ID_TIPO_USUARIO" IS 'Numero de identificacion de Usuarios';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."USUARIO"."ID_EPS" IS 'Identificador unico de registro ';
-- ddl-end --
COMMENT ON CONSTRAINT "USUARIO_PK" ON "simedSch"."USUARIO" IS 'llave primaria tabla usuario';
-- ddl-end --
ALTER TABLE "simedSch"."USUARIO" OWNER TO adminsimed;
-- ddl-end --

-- object: "simedSch"."EPS" | type: TABLE --
CREATE TABLE "simedSch"."EPS"(
	"ID_EPS" int2 NOT NULL,
	"NOMBRE_EPS" varchar(100),
	"DIRECCION_EPS" varchar(100),
	"TELEFONO_EPS" varchar(100),
	CONSTRAINT "EPS_PK" PRIMARY KEY ("ID_EPS")
	WITH (FILLFACTOR = 10)
	USING INDEX TABLESPACE "simedTS"

)
TABLESPACE "simedTS";
-- ddl-end --
COMMENT ON TABLE "simedSch"."EPS" IS 'Empresa prestadora de Salud';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."EPS"."ID_EPS" IS 'Identificador unico de registro ';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."EPS"."NOMBRE_EPS" IS 'Nombre de la entidad prestadora de salud';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."EPS"."DIRECCION_EPS" IS 'Direccion de ubicacion de la EPS';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."EPS"."TELEFONO_EPS" IS 'Numero telefonico de EPS';
-- ddl-end --
COMMENT ON CONSTRAINT "EPS_PK" ON "simedSch"."EPS" IS 'Llave primarya Tabla EPS';
-- ddl-end --
ALTER TABLE "simedSch"."EPS" OWNER TO adminsimed;
-- ddl-end --

-- object: "simedSch"."TIPO_USUARIO" | type: TABLE --
CREATE TABLE "simedSch"."TIPO_USUARIO"(
	"ID_TIPO_USUARIO" int2 NOT NULL,
	"NOMBRE_TIPO_USUARIO" varchar(100),
	CONSTRAINT "ID_USUARIO_PK" PRIMARY KEY ("ID_TIPO_USUARIO")
	WITH (FILLFACTOR = 10)
	USING INDEX TABLESPACE "simedTS"

)
TABLESPACE "simedTS";
-- ddl-end --
COMMENT ON COLUMN "simedSch"."TIPO_USUARIO"."ID_TIPO_USUARIO" IS 'Numero de identificacion de Usuarios';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."TIPO_USUARIO"."NOMBRE_TIPO_USUARIO" IS 'Nombre del tipo Usuario';
-- ddl-end --
COMMENT ON CONSTRAINT "ID_USUARIO_PK" ON "simedSch"."TIPO_USUARIO" IS 'Llave foranea de Usuarios';
-- ddl-end --
ALTER TABLE "simedSch"."TIPO_USUARIO" OWNER TO adminsimed;
-- ddl-end --

-- object: "simedSch"."PROPIEDAD" | type: TABLE --
CREATE TABLE "simedSch"."PROPIEDAD"(
	"ID_PROPIEDAD" int2 NOT NULL,
	"ESTADO_PROPIEDAD" boolean,
	"NOMBRE_PROPIEDAD" varchar(250),
	"VALOR_PROPIEDAD" varchar(2000),
	CONSTRAINT "PROPIEDAD_PK" PRIMARY KEY ("ID_PROPIEDAD")
	WITH (FILLFACTOR = 10)
	USING INDEX TABLESPACE "simedTS"

)
TABLESPACE "simedTS";
-- ddl-end --
COMMENT ON TABLE "simedSch"."PROPIEDAD" IS 'Tabla propiedades SIMED';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."PROPIEDAD"."ID_PROPIEDAD" IS 'Propiedades de SIMED';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."PROPIEDAD"."ESTADO_PROPIEDAD" IS 'Estado de una propiedad 1 activo, 0 inactivo';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."PROPIEDAD"."VALOR_PROPIEDAD" IS 'Contenido de la propiedad, puede ser un JSON, la cabecera de un correo electronico ';
-- ddl-end --
COMMENT ON CONSTRAINT "PROPIEDAD_PK" ON "simedSch"."PROPIEDAD" IS 'Llave Primaria de la tabla Propiedades';
-- ddl-end --
ALTER TABLE "simedSch"."PROPIEDAD" OWNER TO adminsimed;
-- ddl-end --

-- object: "simedSch"."LUGAR_ATENCION" | type: TABLE --
CREATE TABLE "simedSch"."LUGAR_ATENCION"(
	"ID_LUGARES_ATENCION" int2 NOT NULL,
	"NOMBRE_LUGAR_ATENCION" varchar(100),
	"DIRECCION_LUGAR_ATENCION" varchar(100),
	"TELEFONO_LUGAR_ATENCION" varchar(20),
	"SITIO_WEB_LUGAR_ATENCION" varchar(100),
	"LATITUD_LUGAR_ATENCION" float,
	"LONGITUD_LUGAR_ATENCION" float,
	CONSTRAINT "LUGAR_ATENCION_PK" PRIMARY KEY ("ID_LUGARES_ATENCION")
	WITH (FILLFACTOR = 10)
	USING INDEX TABLESPACE "simedTS"

)
TABLESPACE "simedTS";
-- ddl-end --
COMMENT ON TABLE "simedSch"."LUGAR_ATENCION" IS 'Lugares de atencion asociados a una EPS';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."LUGAR_ATENCION"."ID_LUGARES_ATENCION" IS 'identificador unico de lugares de atención';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."LUGAR_ATENCION"."NOMBRE_LUGAR_ATENCION" IS 'Nombre del lugar de atención';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."LUGAR_ATENCION"."DIRECCION_LUGAR_ATENCION" IS 'Dirección del Lugar de atención';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."LUGAR_ATENCION"."TELEFONO_LUGAR_ATENCION" IS 'Numero telefonico del Lugar de atención';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."LUGAR_ATENCION"."SITIO_WEB_LUGAR_ATENCION" IS 'Sitio web del lugar de atención';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."LUGAR_ATENCION"."LATITUD_LUGAR_ATENCION" IS 'Latitud del lugar de atención';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."LUGAR_ATENCION"."LONGITUD_LUGAR_ATENCION" IS 'Longitud del lugar de atención';
-- ddl-end --
COMMENT ON CONSTRAINT "LUGAR_ATENCION_PK" ON "simedSch"."LUGAR_ATENCION" IS 'Llave primaria tabla lugares de atención';
-- ddl-end --
ALTER TABLE "simedSch"."LUGAR_ATENCION" OWNER TO adminsimed;
-- ddl-end --

-- object: "simedSch"."ESPECIALIDAD" | type: TABLE --
CREATE TABLE "simedSch"."ESPECIALIDAD"(
	"ID_ESPECIALIDAD" int2 NOT NULL,
	"NOMBRE_ESPECIALIDAD" varchar(100),
	CONSTRAINT "ID_ESPECIALIDAD_PK" PRIMARY KEY ("ID_ESPECIALIDAD")

)
TABLESPACE "simedTS";
-- ddl-end --
COMMENT ON TABLE "simedSch"."ESPECIALIDAD" IS 'Especialidad médica';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."ESPECIALIDAD"."ID_ESPECIALIDAD" IS 'Identificador de Especialidad Médica';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."ESPECIALIDAD"."NOMBRE_ESPECIALIDAD" IS 'Nombre de la especialidad Médica';
-- ddl-end --
COMMENT ON CONSTRAINT "ID_ESPECIALIDAD_PK" ON "simedSch"."ESPECIALIDAD" IS 'Llave primaria de Especialidades medicas';
-- ddl-end --
ALTER TABLE "simedSch"."ESPECIALIDAD" OWNER TO adminsimed;
-- ddl-end --

-- object: "simedSch"."TIPO_IDENTIFICACION" | type: TABLE --
CREATE TABLE "simedSch"."TIPO_IDENTIFICACION"(
	"ID_TIPO_IDENTIFICACION" int2 NOT NULL,
	"NOMBRE_TIPO_IDENTIFICACION" varchar(100),
	CONSTRAINT "TIPO_IDENTIFICACION_PK" PRIMARY KEY ("ID_TIPO_IDENTIFICACION")
	WITH (FILLFACTOR = 10)
	USING INDEX TABLESPACE "simedTS"

)
TABLESPACE "simedTS";
-- ddl-end --
COMMENT ON TABLE "simedSch"."TIPO_IDENTIFICACION" IS 'Tipo de Identificación documento identidad puede ser CC, TI, CE, NUIP otros';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."TIPO_IDENTIFICACION"."ID_TIPO_IDENTIFICACION" IS 'Identificador único de la tabla tipo de identificación';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."TIPO_IDENTIFICACION"."NOMBRE_TIPO_IDENTIFICACION" IS 'Nombre del tipo de identificación';
-- ddl-end --
COMMENT ON CONSTRAINT "TIPO_IDENTIFICACION_PK" ON "simedSch"."TIPO_IDENTIFICACION" IS 'Llave primaria del tipo de identificación';
-- ddl-end --
ALTER TABLE "simedSch"."TIPO_IDENTIFICACION" OWNER TO adminsimed;
-- ddl-end --

-- object: "TIPO_IDENTIFICACION_fk" | type: CONSTRAINT --
ALTER TABLE "simedSch"."USUARIO" ADD CONSTRAINT "TIPO_IDENTIFICACION_fk" FOREIGN KEY ("ID_TIPO_IDENTIFICACION")
REFERENCES "simedSch"."TIPO_IDENTIFICACION" ("ID_TIPO_IDENTIFICACION") MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE NOT DEFERRABLE;
-- ddl-end --


-- object: "TIPO_USUARIO_fk" | type: CONSTRAINT --
ALTER TABLE "simedSch"."USUARIO" ADD CONSTRAINT "TIPO_USUARIO_fk" FOREIGN KEY ("ID_TIPO_USUARIO")
REFERENCES "simedSch"."TIPO_USUARIO" ("ID_TIPO_USUARIO") MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE NOT DEFERRABLE;
-- ddl-end --


-- object: "EPS_fk" | type: CONSTRAINT --
ALTER TABLE "simedSch"."USUARIO" ADD CONSTRAINT "EPS_fk" FOREIGN KEY ("ID_EPS")
REFERENCES "simedSch"."EPS" ("ID_EPS") MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE NOT DEFERRABLE;
-- ddl-end --


-- object: "simedSch"."many_LUGAR_ATENCION_has_many_EPS" | type: TABLE --
CREATE TABLE "simedSch"."many_LUGAR_ATENCION_has_many_EPS"(
	"ID_LUGARES_ATENCION" int2,
	"ID_EPS" int2,
	CONSTRAINT "many_LUGAR_ATENCION_has_many_EPS_pk" PRIMARY KEY ("ID_LUGARES_ATENCION","ID_EPS")

)
TABLESPACE "simedTS";
-- ddl-end --
COMMENT ON COLUMN "simedSch"."many_LUGAR_ATENCION_has_many_EPS"."ID_LUGARES_ATENCION" IS 'identificador unico de lugares de atención';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."many_LUGAR_ATENCION_has_many_EPS"."ID_EPS" IS 'Identificador unico de registro ';
-- ddl-end --
-- ddl-end --

-- object: "LUGAR_ATENCION_fk" | type: CONSTRAINT --
ALTER TABLE "simedSch"."many_LUGAR_ATENCION_has_many_EPS" ADD CONSTRAINT "LUGAR_ATENCION_fk" FOREIGN KEY ("ID_LUGARES_ATENCION")
REFERENCES "simedSch"."LUGAR_ATENCION" ("ID_LUGARES_ATENCION") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE NOT DEFERRABLE;
-- ddl-end --


-- object: "EPS_fk" | type: CONSTRAINT --
ALTER TABLE "simedSch"."many_LUGAR_ATENCION_has_many_EPS" ADD CONSTRAINT "EPS_fk" FOREIGN KEY ("ID_EPS")
REFERENCES "simedSch"."EPS" ("ID_EPS") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE NOT DEFERRABLE;
-- ddl-end --


-- object: "simedSch"."many_LUGAR_ATENCION_has_many_ESPECIALIDAD" | type: TABLE --
CREATE TABLE "simedSch"."many_LUGAR_ATENCION_has_many_ESPECIALIDAD"(
	"ID_LUGARES_ATENCION" int2,
	"ID_ESPECIALIDAD" int2,
	CONSTRAINT "many_LUGAR_ATENCION_has_many_ESPECIALIDAD_pk" PRIMARY KEY ("ID_LUGARES_ATENCION","ID_ESPECIALIDAD")

)
TABLESPACE "simedTS";
-- ddl-end --
COMMENT ON COLUMN "simedSch"."many_LUGAR_ATENCION_has_many_ESPECIALIDAD"."ID_LUGARES_ATENCION" IS 'identificador unico de lugares de atención';
-- ddl-end --
COMMENT ON COLUMN "simedSch"."many_LUGAR_ATENCION_has_many_ESPECIALIDAD"."ID_ESPECIALIDAD" IS 'Identificador de Especialidad Médica';
-- ddl-end --
-- ddl-end --

-- object: "LUGAR_ATENCION_fk" | type: CONSTRAINT --
ALTER TABLE "simedSch"."many_LUGAR_ATENCION_has_many_ESPECIALIDAD" ADD CONSTRAINT "LUGAR_ATENCION_fk" FOREIGN KEY ("ID_LUGARES_ATENCION")
REFERENCES "simedSch"."LUGAR_ATENCION" ("ID_LUGARES_ATENCION") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE NOT DEFERRABLE;
-- ddl-end --


-- object: "ESPECIALIDAD_fk" | type: CONSTRAINT --
ALTER TABLE "simedSch"."many_LUGAR_ATENCION_has_many_ESPECIALIDAD" ADD CONSTRAINT "ESPECIALIDAD_fk" FOREIGN KEY ("ID_ESPECIALIDAD")
REFERENCES "simedSch"."ESPECIALIDAD" ("ID_ESPECIALIDAD") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE NOT DEFERRABLE;
-- ddl-end --


-- object: "simedSch"."USUARIO_SEQ" | type: SEQUENCE --
CREATE SEQUENCE "simedSch"."USUARIO_SEQ"
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
ALTER SEQUENCE "simedSch"."USUARIO_SEQ" OWNER TO adminsimed;
-- ddl-end --

-- object: "simedSch"."PROPIEDAD_SEQ" | type: SEQUENCE --
CREATE SEQUENCE "simedSch"."PROPIEDAD_SEQ"
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
ALTER SEQUENCE "simedSch"."PROPIEDAD_SEQ" OWNER TO adminsimed;
-- ddl-end --

-- object: "simedSch"."TIPO_USUARIO_SEQ" | type: SEQUENCE --
CREATE SEQUENCE "simedSch"."TIPO_USUARIO_SEQ"
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
ALTER SEQUENCE "simedSch"."TIPO_USUARIO_SEQ" OWNER TO adminsimed;
-- ddl-end --

-- object: "simedSch"."EPS_SEQ" | type: SEQUENCE --
CREATE SEQUENCE "simedSch"."EPS_SEQ"
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
ALTER SEQUENCE "simedSch"."EPS_SEQ" OWNER TO adminsimed;
-- ddl-end --

-- object: "simedSch"."LUGAR_ATENCION_SEQ" | type: SEQUENCE --
CREATE SEQUENCE "simedSch"."LUGAR_ATENCION_SEQ"
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
ALTER SEQUENCE "simedSch"."LUGAR_ATENCION_SEQ" OWNER TO adminsimed;
-- ddl-end --

-- object: "simedSch"."ESPECIALIDAD_SEQ" | type: SEQUENCE --
CREATE SEQUENCE "simedSch"."ESPECIALIDAD_SEQ"
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
ALTER SEQUENCE "simedSch"."ESPECIALIDAD_SEQ" OWNER TO adminsimed;
-- ddl-end --

-- object: "simedSch"."TIPO_IDENTIFICACION_SEQ" | type: SEQUENCE --
CREATE SEQUENCE "simedSch"."TIPO_IDENTIFICACION_SEQ"
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
ALTER SEQUENCE "simedSch"."TIPO_IDENTIFICACION_SEQ" OWNER TO adminsimed;
-- ddl-end --


