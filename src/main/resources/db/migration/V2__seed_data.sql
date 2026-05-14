insert into tbl_role (id, name) values
(1, 'ROLE_ADMIN'), (2, 'ROLE_USER');

insert into tbl_permission (id, name, resource, action) values
(1, 'VIEW TASK', 'TASK', 'READ'),
(2, 'CREATE TASK', 'TASK', 'WRITE'),
(3, 'DELETE TASK', 'TASK', 'DELETE'),
(4, 'EDIT TASK', 'TASK', 'EDIT'),
(5, 'VIEW USER', 'USER', 'READ'),
(6, 'CREATE USER', 'USER', 'WRITE'),
(7, 'DELETE USER', 'USER', 'DELETE'),
(8, 'EDIT USER', 'USER', 'EDIT');

insert into tbl_role_permission (role_id, permission_id) values 
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(2, 5);
