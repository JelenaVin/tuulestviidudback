-- Kustutab public schema (mis põhimõtteliselt kustutab kõik tabelid)
DROP SCHEMA IF EXISTS tuulest CASCADE;
-- Loob uue public schema vajalikud õigused
CREATE SCHEMA tuulest
-- taastab vajalikud andmebaasi õigused
    GRANT ALL ON SCHEMA tuulest TO postgres;
GRANT ALL ON SCHEMA tuulest TO PUBLIC;
