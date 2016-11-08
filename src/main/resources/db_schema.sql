CREATE TABLE IF NOT EXISTS public."User"
(
  id serial NOT NULL,
  username text NOT NULL,
  password text NOT NULL,
  PRIMARY KEY (id)
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."User"
  OWNER to postgres;


CREATE TABLE IF NOT EXISTS public."Question"
(
  id serial NOT NULL,
  "testId" integer NOT NULL,
  question_text text,
  answercount integer NOT NULL,
  PRIMARY KEY (id)
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Question"
  OWNER to postgres;