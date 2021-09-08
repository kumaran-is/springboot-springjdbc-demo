DROP TABLE IF EXISTS public.student cascade;

DROP SEQUENCE IF EXISTS public.student_sequence;

CREATE SEQUENCE public.student_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.student_sequence
    OWNER TO kumaraniyyasamysrinivasan;
    
CREATE TABLE IF NOT EXISTS public.student
(
    id bigint NOT NULL DEFAULT nextval('student_sequence'::regclass),
    dob date,
    email character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT student_pkey PRIMARY KEY (id)
);

ALTER TABLE public.student
    OWNER to kumaraniyyasamysrinivasan;