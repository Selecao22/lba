drop table if exists "exam_type", "journal", "mark", "student", "subject", "study_group", "study_plan";

create table "study_group"
(
    "id" identity not null primary key,
    "name" text default null
);

create table "subject"
(
    "id" identity not null primary key,
    "name" text default null,
    "short_name" text default null
);

create table "exam_type"
(
    "id" identity not null primary key,
    "type" text default null
);

create table "mark"
(
    "id" identity not null primary key,
    "name" text default null,
    "value" text default null
);

create table "study_plan"
(
    "id" identity not null primary key,
    "subject_id" int,
    "exam_type_id" int,
    foreign key("subject_id") references "subject"("id") on delete set null on update cascade,
    foreign key("exam_type_id") references "exam_type"("id") on delete set null on update cascade
);

create table "student"
(
    "id" identity not null primary key,
    "surname" text default null,
    "name" text default null,
    "second_name" text default null,
    "study_group_id" int,
    foreign key("study_group_id") references "study_group"("id") on delete set null on update cascade
);

create table "journal"
(
    "id" identity not null primary key,
    "student_id" int,
    "study_plan_id" int,
    "in_time" boolean not null default true,
    "count" int,
    "mark_id" int,
    foreign key("student_id") references "student"("id") on delete set null on update cascade,
    foreign key("study_plan_id") references "study_plan"("id") on delete set null on update cascade,
    foreign key("mark_id") references "mark"("id") on delete set null on update cascade
);


insert into "study_group" ("name")
values ('Группа 1');

insert into "student" ("surname", "name", "second_name", "study_group_id")
values ('Языков', 'Никита', 'Григорьевич', '1');

insert into "subject"
values  (1, 'Проектирование информационных систем', 'ПрИС'),
        (2, 'Системы искусственного интеллекта', 'СИИ'),
        (3, 'Программная инженерия', 'ПИ'),
        (4, 'Национальная система информационной безопасности', 'НСИБ'),
        (5, 'Системный анализ', 'СисАнал'),
        (6, 'Распределенные базы данных', 'РБД'),
        (7, 'Системное программное обеспечение', 'СПО');

insert into "exam_type"
values  (1, 'Экзамен'),
        (2, 'Зачет'),
        (3, 'Дифференцированный зачёт'),
        (4, 'Курсовая');

insert into "study_plan"
values  (1, 1, 1),
        (2, 1, 4),
        (3, 2, 1),
        (4, 3, 1),
        (5, 4, 2),
        (6, 5, 1),
        (7, 6, 2),
        (8, 7, 1);

insert into "mark"
values  (1, 'Отлично', 5),
        (2, 'Хорошо', 4),
        (3, 'Удовлетворительно', 3),
        (4, 'Неудовлетворительно', 2),
        (5, 'Зачет', 'з'),
        (6, 'Незачет', 'н'),
        (7, 'Неявка', '');

insert into "journal" ("student_id", "study_plan_id", "count", "mark_id")
values  (1, 1, 1, 1),
        (1, 2, 1, 1),
        (1, 3, 1, 1),
        (1, 4, 1, 5),
        (1, 5, 1, 5),
        (1, 6, 1, 1);