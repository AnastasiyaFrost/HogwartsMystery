-- liquibase formatted sql
-- changeset agrebneva:1
CREATE INDEX student_name_idx ON student (name);
-- changeset agrebneva:2
CREATE INDEX faculty_name_color_idx ON faculty (name, color);