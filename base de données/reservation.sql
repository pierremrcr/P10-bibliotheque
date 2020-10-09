CREATE SEQUENCE public.reservation_id_seq;

CREATE TABLE public.reservation (
                id INTEGER NOT NULL DEFAULT nextval('public.reservation_id_seq'),
                date_resa DATE NOT NULL,
                num_position_resa INTEGER NOT NULL,
                livre_id INTEGER NOT NULL,
                compte_id INTEGER NOT NULL,
                CONSTRAINT reservation_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.reservation_id_seq OWNED BY public.reservation.id;

ALTER TABLE public.reservation ADD CONSTRAINT livre_reservation_fk
FOREIGN KEY (livre_id)
REFERENCES public.livre (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.reservation ADD CONSTRAINT compte_reservation_fk
FOREIGN KEY (compte_id)
REFERENCES public.compte (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;