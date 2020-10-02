create sequence "EPS_SEQ"
	minvalue 0
	maxvalue 2147483647;

alter sequence "EPS_SEQ" owner to postgres;

create sequence "ESPECIALIDAD_SEQ"
	minvalue 0
	maxvalue 2147483647;

alter sequence "ESPECIALIDAD_SEQ" owner to postgres;

create sequence "LUGAR_ATENCION_SEQ"
	minvalue 0
	maxvalue 2147483647;

alter sequence "LUGAR_ATENCION_SEQ" owner to postgres;

create sequence "PROPIEDAD_SEQ"
	minvalue 0
	maxvalue 2147483647;

alter sequence "PROPIEDAD_SEQ" owner to postgres;

create sequence tipo_identificacion_seq
	minvalue 0
	maxvalue 2147483647;

alter sequence tipo_identificacion_seq owner to postgres;

create sequence "TIPO_USUARIO_SEQ"
	minvalue 0
	maxvalue 2147483647;

alter sequence "TIPO_USUARIO_SEQ" owner to postgres;

create sequence "USUARIO_SEQ"
	minvalue 0
	maxvalue 2147483647;

alter sequence "USUARIO_SEQ" owner to postgres;

create table if not exists "EPS"
(
	"ID_EPS" smallint default nextval('"simedSch"."EPS_SEQ"'::regclass) not null
		constraint "EPS_PK"
			primary key,
	"NOMBRE_EPS" varchar(100),
	"DIRECCION_EPS" varchar(100),
	"TELEFONO_EPS" varchar(100),
	"ESTADO_EPS" boolean
);

comment on table "EPS" is 'Empresa prestadora de Salud';

comment on column "EPS"."ID_EPS" is 'Identificador unico de registro ';

comment on constraint "EPS_PK" on "EPS" is 'Llave primarya Tabla EPS';

comment on column "EPS"."NOMBRE_EPS" is 'Nombre de la entidad prestadora de salud';

comment on column "EPS"."DIRECCION_EPS" is 'Direccion de ubicacion de la EPS';

comment on column "EPS"."TELEFONO_EPS" is 'Numero telefonico de EPS';

alter table "EPS" owner to postgres;

create table if not exists "ESPECIALIDAD"
(
	"ID_ESPECIALIDAD" smallint default nextval('"simedSch"."ESPECIALIDAD_SEQ"'::regclass) not null
		constraint "ID_ESPECIALIDAD_PK"
			primary key,
	"NOMBRE_ESPECIALIDAD" varchar(100)
);

comment on table "ESPECIALIDAD" is 'Especialidad médica';

comment on column "ESPECIALIDAD"."ID_ESPECIALIDAD" is 'Identificador de Especialidad Médica';

comment on constraint "ID_ESPECIALIDAD_PK" on "ESPECIALIDAD" is 'Llave primaria de Especialidades medicas';

comment on column "ESPECIALIDAD"."NOMBRE_ESPECIALIDAD" is 'Nombre de la especialidad Médica';

alter table "ESPECIALIDAD" owner to postgres;

create table if not exists "LUGAR_ATENCION"
(
	"ID_LUGARES_ATENCION" smallint default nextval('"simedSch"."LUGAR_ATENCION_SEQ"'::regclass) not null
		constraint "LUGAR_ATENCION_PK"
			primary key,
	"NOMBRE_LUGAR_ATENCION" varchar(100),
	"DIRECCION_LUGAR_ATENCION" varchar(100),
	"TELEFONO_LUGAR_ATENCION" varchar(20),
	"SITIO_WEB_LUGAR_ATENCION" varchar(100),
	"LATITUD_LUGAR_ATENCION" double precision,
	"LONGITUD_LUGAR_ATENCION" double precision,
	"ESTADO_LUGAR_ATENCION" boolean
);

comment on table "LUGAR_ATENCION" is 'Lugares de atencion asociados a una EPS';

comment on column "LUGAR_ATENCION"."ID_LUGARES_ATENCION" is 'identificador unico de lugares de atención';

comment on constraint "LUGAR_ATENCION_PK" on "LUGAR_ATENCION" is 'Llave primaria tabla lugares de atención';

comment on column "LUGAR_ATENCION"."NOMBRE_LUGAR_ATENCION" is 'Nombre del lugar de atención';

comment on column "LUGAR_ATENCION"."DIRECCION_LUGAR_ATENCION" is 'Dirección del Lugar de atención';

comment on column "LUGAR_ATENCION"."TELEFONO_LUGAR_ATENCION" is 'Numero telefonico del Lugar de atención';

comment on column "LUGAR_ATENCION"."SITIO_WEB_LUGAR_ATENCION" is 'Sitio web del lugar de atención';

comment on column "LUGAR_ATENCION"."LATITUD_LUGAR_ATENCION" is 'Latitud del lugar de atención';

comment on column "LUGAR_ATENCION"."LONGITUD_LUGAR_ATENCION" is 'Longitud del lugar de atención';

alter table "LUGAR_ATENCION" owner to postgres;

create table if not exists "LUGAR_ATENCION_X_EPS"
(
	"ID_LUGARES_ATENCION" smallint not null
		constraint "LUGAR_ATENCION_fk"
			references "LUGAR_ATENCION"
				on update cascade on delete restrict,
	"ID_EPS" smallint not null
		constraint "EPS_fk"
			references "EPS"
				on update cascade on delete restrict,
	constraint "LUGAR_ATENCION_X_EPS_pk"
		primary key ("ID_LUGARES_ATENCION", "ID_EPS")
);

comment on column "LUGAR_ATENCION_X_EPS"."ID_LUGARES_ATENCION" is 'identificador unico de lugares de atención';

comment on column "LUGAR_ATENCION_X_EPS"."ID_EPS" is 'Identificador unico de registro ';

alter table "LUGAR_ATENCION_X_EPS" owner to postgres;

create table if not exists "LUGAR_ATENCION_X_ESPECIALIDAD"
(
	"ID_LUGARES_ATENCION" smallint not null
		constraint "LUGAR_ATENCION_fk"
			references "LUGAR_ATENCION"
				on update cascade on delete restrict,
	"ID_ESPECIALIDAD" smallint not null
		constraint "ESPECIALIDAD_fk"
			references "ESPECIALIDAD"
				on update cascade on delete restrict,
	constraint "many_LUGAR_ATENCION_has_many_ESPECIALIDAD_pk"
		primary key ("ID_LUGARES_ATENCION", "ID_ESPECIALIDAD")
);

comment on column "LUGAR_ATENCION_X_ESPECIALIDAD"."ID_LUGARES_ATENCION" is 'identificador unico de lugares de atención';

comment on column "LUGAR_ATENCION_X_ESPECIALIDAD"."ID_ESPECIALIDAD" is 'Identificador de Especialidad Médica';

alter table "LUGAR_ATENCION_X_ESPECIALIDAD" owner to postgres;

create table if not exists "PROPIEDAD"
(
	"ID_PROPIEDAD" smallint default nextval('"simedSch"."PROPIEDAD_SEQ"'::regclass) not null
		constraint "PROPIEDAD_PK"
			primary key,
	"ESTADO_PROPIEDAD" boolean,
	"NOMBRE_PROPIEDAD" varchar(250),
	"VALOR_PROPIEDAD" varchar(2000)
);

comment on table "PROPIEDAD" is 'Tabla propiedades SIMED';

comment on column "PROPIEDAD"."ID_PROPIEDAD" is 'Propiedades de SIMED';

comment on constraint "PROPIEDAD_PK" on "PROPIEDAD" is 'Llave Primaria de la tabla Propiedades';

comment on column "PROPIEDAD"."ESTADO_PROPIEDAD" is 'Estado de una propiedad 1 activo, 0 inactivo';

comment on column "PROPIEDAD"."VALOR_PROPIEDAD" is 'Contenido de la propiedad, puede ser un JSON, la cabecera de un correo electronico ';

alter table "PROPIEDAD" owner to postgres;

create table if not exists "TIPO_IDENTIFICACION"
(
	"ID_TIPO_IDENTIFICACION" smallint default nextval('"simedSch".tipo_identificacion_seq'::regclass) not null
		constraint "TIPO_IDENTIFICACION_PK"
			primary key,
	"NOMBRE_TIPO_IDENTIFICACION" varchar(100)
);

comment on table "TIPO_IDENTIFICACION" is 'Tipo de Identificación documento identidad puede ser CC, TI, CE, NUIP otros';

comment on column "TIPO_IDENTIFICACION"."ID_TIPO_IDENTIFICACION" is 'Identificador único de la tabla tipo de identificación';

comment on constraint "TIPO_IDENTIFICACION_PK" on "TIPO_IDENTIFICACION" is 'Llave primaria del tipo de identificación';

comment on column "TIPO_IDENTIFICACION"."NOMBRE_TIPO_IDENTIFICACION" is 'Nombre del tipo de identificación';

alter table "TIPO_IDENTIFICACION" owner to postgres;

create table if not exists "TIPO_USUARIO"
(
	"ID_TIPO_USUARIO" smallint default nextval('"simedSch"."TIPO_USUARIO_SEQ"'::regclass) not null
		constraint "ID_USUARIO_PK"
			primary key,
	"NOMBRE_TIPO_USUARIO" varchar(100)
);

comment on column "TIPO_USUARIO"."ID_TIPO_USUARIO" is 'Numero de identificacion de Usuarios';

comment on constraint "ID_USUARIO_PK" on "TIPO_USUARIO" is 'Llave foranea de Usuarios';

comment on column "TIPO_USUARIO"."NOMBRE_TIPO_USUARIO" is 'Nombre del tipo Usuario';

alter table "TIPO_USUARIO" owner to postgres;

create table if not exists "USUARIO"
(
	"ID_USUARIO" bigint default nextval('"simedSch"."USUARIO_SEQ"'::regclass) not null
		constraint "USUARIO_PK"
			primary key,
	"NOMBRE_USUARIO" varchar,
	"APELLIDOS_USUARIO" varchar(100),
	"NUMERO_IDENTIFICACION_USUARIO" varchar(20),
	"PASSWORD_USUARIO" varchar(250),
	"CORREO_USUARIO" varchar(250),
	"ESTADO_USUARIO" boolean,
	"FECHA_CREACION_USUARIO" timestamp,
	"FECHA_MODIFICACION_USUARIO" timestamp,
	"ID_TIPO_IDENTIFICACION" smallint
		constraint "TIPO_IDENTIFICACION_fk"
			references "TIPO_IDENTIFICACION"
				on update cascade on delete set null,
	"ID_TIPO_USUARIO" smallint
		constraint "TIPO_USUARIO_fk"
			references "TIPO_USUARIO"
				on update cascade on delete set null,
	"ID_EPS" smallint
		constraint "EPS_fk"
			references "EPS"
				on update cascade on delete set null
);

comment on table "USUARIO" is 'Tabla usuarios ';

comment on column "USUARIO"."ID_USUARIO" is 'Identificador unico de usuarios en SIMED';

comment on constraint "USUARIO_PK" on "USUARIO" is 'llave primaria tabla usuario';

comment on column "USUARIO"."NOMBRE_USUARIO" is 'Nombre de usuario SIMED';

comment on column "USUARIO"."APELLIDOS_USUARIO" is 'Apellidos Usuario de SIMED';

comment on column "USUARIO"."NUMERO_IDENTIFICACION_USUARIO" is 'Numero de documento del Usuario de SIMED';

comment on column "USUARIO"."PASSWORD_USUARIO" is 'Password de Usuario de SIMED';

comment on column "USUARIO"."CORREO_USUARIO" is 'Cuenta de Correo electronico del Usuario ';

comment on column "USUARIO"."ESTADO_USUARIO" is 'Estado del Usuario, puede activo 1, inactivo 0';

comment on column "USUARIO"."FECHA_CREACION_USUARIO" is 'Fecha de Creacion del registro ';

comment on column "USUARIO"."FECHA_MODIFICACION_USUARIO" is 'Fecha de modificacion dell registro de usuario';

comment on column "USUARIO"."ID_TIPO_IDENTIFICACION" is 'Identificador único de la tabla tipo de identificación';

comment on column "USUARIO"."ID_TIPO_USUARIO" is 'Numero de identificacion de Usuarios';

comment on column "USUARIO"."ID_EPS" is 'Identificador unico de registro ';

alter table "USUARIO" owner to postgres;

