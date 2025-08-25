CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS public.rol (
  id_rol UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  nombre VARCHAR(50) NOT NULL,
  descripcion TEXT
);

CREATE TABLE IF NOT EXISTS public.usuario (
  id_usuario UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  nombres VARCHAR(100) NOT NULL,
  apellidos VARCHAR(100) NOT NULL,
  fecha_nacimiento DATE NOT NULL,
  direccion VARCHAR(255),
  telefono VARCHAR(20),
  correo_electronico VARCHAR(150) UNIQUE NOT NULL,
  salario_base NUMERIC(15, 2) NOT NULL CHECK (
    salario_base >= 0
    AND salario_base <= 15000000
  ),
  documento_identidad VARCHAR(20),
  id_rol UUID NOT NULL REFERENCES rol (id_rol),
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);