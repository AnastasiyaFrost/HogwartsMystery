ALTER TABLE student
    ADD CONSTRAINT age_constraint CHECK (age > 10);

ALTER TABLE student
ADD CONSTRAINT myUniqueConstraint UNIQUE (name);
ALTER TABLE student
ALTER COLUMN name SET NOT NULL;

ALTER TABLE faculty
    ADD CONSTRAINT name_color_unique UNIQUE (name, color);

ALTER TABLE student
    ALTER COLUMN age SET DEFAULT '11';

