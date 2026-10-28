CREATE EXTENSION IF NOT EXISTS btree_gist;

ALTER TABLE reservas
    DROP CONSTRAINT IF EXISTS reservas_sin_solapamiento;

ALTER TABLE reservas
    ADD CONSTRAINT reservas_sin_solapamiento
    EXCLUDE USING gist(
        profesional_id WITH =,
        tsrange(fecha_hora_inicio, fecha_hora_fin) WITH &&
    )
    WHERE (estado <> 'CANCELADA');