--------------------------------------------------------
--  DDL for Table T_PLAYER
--------------------------------------------------------

CREATE TABLE "T_PLAYER"
(
    "ID" Integer not null,
    "FIRST_NAME" VARCHAR(255),
    "LAST_NAME" VARCHAR(255)
);

--------------------------------------------------------
--  Constraints for Table T_PLAYER
--------------------------------------------------------
ALTER TABLE "T_PLAYER" ADD CONSTRAINT "PK_PLAYER" PRIMARY KEY ("ID");
