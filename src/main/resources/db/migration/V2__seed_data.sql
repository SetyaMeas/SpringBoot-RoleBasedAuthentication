insert into tbl_role (id, name) values
(1, 'ROLE_ADMIN'), (2, 'ROLE_USER');

insert into tbl_permission (id, name, resource, action) values
(1, 'VIEW USER', 'USER', 'READ'),
(2, 'CREATE USER', 'USER', 'WRITE'),
(3, 'DELETE USER', 'USER', 'DELETE'),
(4, 'EDIT USER', 'USER', 'EDIT');

insert into tbl_role_permission (role_id, permission_id) values 
(1, 2),
(1, 3),
(1, 4),
(2, 1);
