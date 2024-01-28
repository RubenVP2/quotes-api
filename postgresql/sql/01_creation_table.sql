-- Ce fichier va créer la table qui va recevoir les données en provenance du fichier data.csv
-- Il sera passé en paramètre au service postgresql retrouve dans le fichier docker-compose.yml
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

ALTER SCHEMA public OWNER TO pg_database_owner;

COMMENT ON SCHEMA public IS 'standard public schema';

CREATE TABLE IF NOT EXISTS public.quotes (
    id serial PRIMARY KEY ,
    quote text,
    author text,
    category text
);

ALTER TABLE public.quotes OWNER TO postgres;