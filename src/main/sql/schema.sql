
create table dosing_guideline
(
    id varchar(100) not null,
    obj_cls varchar(100) null,
    name varchar(100) null,
    recommendation tinyint(1) null,
    drug_id varchar(100) null,
    source varchar(100) null,
    summary_markdown varchar(2000) null,
    text_markdown text null,
    raw longtext null,
    constraint dosing_guideline_id_uindex
        unique (id)
);

alter table dosing_guideline
    add primary key (id);

create table drug
(
    id varchar(100) not null,
    name varchar(500) null,
    obj_cls varchar(100) null,
    drug_url varchar(100) null,
    biomarker tinyint(1) null,
    constraint drug_id_uindex
        unique (id)
);

alter table drug
    add primary key (id);

create table drug_label
(
    id varchar(100) not null,
    name varchar(200) null,
    obj_cls varchar(100) null,
    alternate_drug_available tinyint(1) null,
    dosing_information tinyint(1) null,
    prescribing_markdown varchar(2000) null,
    source varchar(100) null,
    text_markdown varchar(4000) null,
    summary_markdown varchar(1000) null,
    raw text null,
    drug_id varchar(100) null,
    constraint drug_label_id_uindex
        unique (id)
);

alter table drug_label
    add primary key (id);
