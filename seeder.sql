USE helping_hand_db;

insert into roles (role_id, role)
values (1, 'ADMIN');
insert into roles (role_id, role)
values (2, 'USER');

insert into categories(category_id, type)
values (1, 'indoor');
insert into categories(category_id, type)
values (2, 'outdoor');
insert into categories(category_id, type)
values(3, 'other');