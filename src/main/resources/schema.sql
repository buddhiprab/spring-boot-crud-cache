create table if not exists delivery
(
  id                        int             auto_increment,
  pickup_name               varchar(100)    null,
  pickup_address            varchar(200)    null,
  pickup_datetime           datetime        null,
  pickup_contact_numbers    varchar(100)    null,
  pickup_comment            text            null,
  drop_name                 varchar(100)    null,
  drop_address              varchar(200)    null,
  drop_contact_numbers      varchar(45)     null,
  drop_comment              text            null,
  primary key (id)
);

create index idx_pdt on delivery (pickup_datetime);