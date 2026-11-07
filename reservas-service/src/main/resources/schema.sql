CREATE EXTENSION IF NOT EXISTS btree_gist;

ALTER TABLE reservas
    DROP CONSTRAINT IF EXISTS reservas_estado_check;

ALTER TABLE reservas
    ADD CONSTRAINT reservas_estado_check
    CHECK (estado IN ('PENDIENTE', 'CONFIRMADA', 'CANCELADA', 'COMPLETADA', 'RECHAZADA'));


ALTER TABLE reservas
    DROP CONSTRAINT IF EXISTS reservas_sin_solapamiento;

ALTER TABLE reservas
    ADD CONSTRAINT reservas_sin_solapamiento
    EXCLUDE USING gist(
        profesional_id WITH =,
        tsrange(fecha_hora_inicio, fecha_hora_fin) WITH &&
    )
    WHERE (estado NOT IN ('CANCELADA', 'RECHAZADA'));